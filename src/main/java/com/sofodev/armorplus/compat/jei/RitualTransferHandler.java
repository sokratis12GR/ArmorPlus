package com.sofodev.armorplus.compat.jei;

import com.sofodev.armorplus.api.RuneData;
import com.sofodev.armorplus.api.SocketResult;
import com.sofodev.armorplus.network.FillAltarPayload;
import com.sofodev.armorplus.registry.ModMenus;
import com.sofodev.armorplus.registry.rune.RitualMenu;
import com.sofodev.armorplus.registry.rune.Runes;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.transfer.IRecipeTransferError;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandler;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JEI's recipe transfer button on the ritual screen: switches to the recipe's tab, fills the input slots and puts the catalysts
 * on empty pedestals. It never takes anything off a pedestal: what's already there is used, and it says so if there's no room.
 */
final class RitualTransferHandler implements IRecipeTransferHandler<RitualMenu, RitualRecipe> {

    private final IRecipeTransferHandlerHelper helper;

    RitualTransferHandler(IRecipeTransferHandlerHelper helper) {
        this.helper = helper;
    }

    @Override
    public Class<? extends RitualMenu> getContainerClass() {
        return RitualMenu.class;
    }

    @Override
    public Optional<MenuType<RitualMenu>> getMenuType() {
        return Optional.of(ModMenus.RITUAL_ALTAR.get());
    }

    @Override
    public IRecipeType<RitualRecipe> getRecipeType() {
        return RitualCategory.TYPE;
    }

    @Override
    public @Nullable IRecipeTransferError transferRecipe(RitualMenu menu, RitualRecipe recipe, IRecipeSlotsView slots, Player player, boolean maxTransfer, boolean doTransfer) {
        TransferPool pool = new TransferPool(menu, RitualMenu.RESULT_SLOT);
        RuneData rune = Runes.runeData(recipe.secondary());
        List<ItemStack> pedestals = menu.getPedestalItems();
        List<IRecipeSlotView> missing = new ArrayList<>();

        ItemStack first = pool.take(stack -> isFirstInput(recipe, rune, stack), 1);
        if (first.isEmpty()) slots.findSlotByName(RitualCategory.PRIMARY_SLOT).ifPresent(missing::add);

        // only socketing needs its rune in the slot, otherwise one that's on a pedestal is used
        boolean onPedestal = recipe.tab() != RitualMenu.Tab.SOCKET && pedestals.stream().anyMatch(stack -> rune != null && rune.equals(Runes.runeData(stack)));
        ItemStack second = onPedestal ? ItemStack.EMPTY : pool.take(stack -> rune != null && rune.equals(Runes.runeData(stack)), 1);
        if (second.isEmpty() && !onPedestal) slots.findSlotByName(RitualCategory.SECONDARY_SLOT).ifPresent(missing::add);

        ItemStack catalyst = ItemStack.EMPTY;
        int emptyPedestals = 0;
        int neededCatalysts = recipe.catalyst().getCount();
        for (ItemStack stack : pedestals) {
            if (stack.isEmpty()) emptyPedestals++;
            else if (stack.is(recipe.catalyst().getItem())) neededCatalysts -= stack.getCount();
        }
        if (!recipe.catalyst().isEmpty() && neededCatalysts > 0) {
            catalyst = pool.take(stack -> stack.is(recipe.catalyst().getItem()), neededCatalysts);
            if (catalyst.isEmpty()) {
                for (int pedestal = 0; pedestal < recipe.catalyst().getCount(); pedestal++) {
                    slots.findSlotByName(RitualCategory.catalystSlot(pedestal)).ifPresent(missing::add);
                }
            }
        }

        if (!missing.isEmpty()) return this.helper.createUserErrorForMissingSlots(Component.translatable("jei.armorplus.transfer.missing"), missing);
        if (catalyst.getCount() > emptyPedestals) return this.helper.createUserErrorWithTooltip(Component.translatable("jei.armorplus.transfer.no_room"));

        if (doTransfer) ClientPacketDistributor.sendToServer(new FillAltarPayload(menu.containerId, recipe.tab().ordinal(), first, second, catalyst, ""));
        return null;
    }

    /** The recipe lists fresh equipment, the player's own has to allow it too: free sockets, or the effect not yet at its limit. */
    private static boolean isFirstInput(RitualRecipe recipe, @Nullable RuneData rune, ItemStack stack) {
        if (rune == null) return false;
        return switch (recipe.tab()) {
            case SOCKET -> isListed(recipe, stack) && Runes.canSocket(stack, rune) == SocketResult.OK;
            case UPGRADE_RUNE -> rune.equals(Runes.runeData(stack));
            case UPGRADE_EFFECT -> isListed(recipe, stack) && Runes.canUpgradeNative(stack, rune);
        };
    }

    private static boolean isListed(RitualRecipe recipe, ItemStack stack) {
        return recipe.primary().stream().anyMatch(listed -> stack.is(listed.getItem()));
    }
}
