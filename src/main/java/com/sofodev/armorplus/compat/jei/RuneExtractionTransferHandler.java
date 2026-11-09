package com.sofodev.armorplus.compat.jei;

import com.sofodev.armorplus.api.RuneData;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.network.FillAltarPayload;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.ModMenus;
import com.sofodev.armorplus.registry.rune.RuneAltarMenu;
import com.sofodev.armorplus.registry.rune.Runes;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.transfer.IRecipeTransferError;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandler;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JEI's recipe transfer button on the extraction screen: fills the three slots and selects the buff. A Blank Rune or catalyst
 * that a complete circle already holds isn't taken from the inventory.
 */
final class RuneExtractionTransferHandler implements IRecipeTransferHandler<RuneAltarMenu, RuneExtractionRecipe> {

    private final IRecipeTransferHandlerHelper helper;

    RuneExtractionTransferHandler(IRecipeTransferHandlerHelper helper) {
        this.helper = helper;
    }

    @Override
    public Class<? extends RuneAltarMenu> getContainerClass() {
        return RuneAltarMenu.class;
    }

    @Override
    public Optional<MenuType<RuneAltarMenu>> getMenuType() {
        return Optional.of(ModMenus.RUNE_ALTAR.get());
    }

    @Override
    public RecipeType<RuneExtractionRecipe> getRecipeType() {
        return RuneExtractionCategory.TYPE;
    }

    @Override
    public @Nullable IRecipeTransferError transferRecipe(RuneAltarMenu menu, RuneExtractionRecipe recipe, IRecipeSlotsView slots, Player player, boolean maxTransfer, boolean doTransfer) {
        RuneData rune = Runes.runeData(recipe.rune());
        if (rune == null) return this.helper.createInternalError();
        TransferPool pool = new TransferPool(menu, RuneAltarMenu.RESULT_SLOT);
        List<IRecipeSlotView> missing = new ArrayList<>();

        ItemStack gear = pool.take(stack -> canExtractFrom(recipe, rune, stack), 1);
        if (gear.isEmpty()) slots.findSlotByName(RuneExtractionCategory.GEAR_SLOT).ifPresent(missing::add);

        ItemStack blankRune = ItemStack.EMPTY;
        if (menu.onPedestals(stack -> stack.is(ModItems.BLANK_RUNE.get())) == 0) {
            blankRune = pool.take(stack -> stack.is(ModItems.BLANK_RUNE.get()), 1);
            if (blankRune.isEmpty()) slots.findSlotByName(RuneExtractionCategory.BLANK_RUNE_SLOT).ifPresent(missing::add);
        }

        ItemStack catalyst = ItemStack.EMPTY;
        if (!recipe.catalyst().isEmpty()) {
            int needed = recipe.catalyst().getCount() - menu.onPedestals(stack -> stack.is(recipe.catalyst().getItem()));
            if (needed > 0) {
                catalyst = pool.take(stack -> stack.is(recipe.catalyst().getItem()), needed);
                if (catalyst.isEmpty()) slots.findSlotByName(RuneExtractionCategory.CATALYST_SLOT).ifPresent(missing::add);
            }
        }

        if (!missing.isEmpty()) return this.helper.createUserErrorForMissingSlots(Component.translatable("jei.armorplus.transfer.missing"), missing);
        if (doTransfer) PacketDistributor.sendToServer(new FillAltarPayload(menu.containerId, 0, gear, blankRune, catalyst, ItemStack.EMPTY, rune.buffId()));
        return null;
    }

    /** The recipe lists fresh equipment, the player's own can have lost the effect or be too damaged. */
    private static boolean canExtractFrom(RuneExtractionRecipe recipe, RuneData rune, ItemStack stack) {
        if (recipe.gear().stream().noneMatch(listed -> stack.is(listed.getItem())) || Runes.isExtracted(stack, rune.buffId())) return false;
        return ArmorPlusConfig.destroySourceOnExtract.get() || Runes.survivesExtraction(stack);
    }
}
