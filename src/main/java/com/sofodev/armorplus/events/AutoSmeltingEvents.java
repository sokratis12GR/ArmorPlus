package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.item.tool.properties.tool.APToolMaterial;
import com.sofodev.armorplus.registry.item.tool.properties.tool.Tool;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.block.BreakBlockEvent;

import java.util.List;

/** Applies the COMMON config's "block|result" smelting recipes to Infused Lava tools. */
@EventBusSubscriber(modid = ArmorPlus.MODID)
public final class AutoSmeltingEvents {
    private AutoSmeltingEvents() {
    }

    @SubscribeEvent
    public static void onBreak(BreakBlockEvent event) {
        if (!(event.getLevel() instanceof ServerLevel level) || !ArmorPlusConfig.enableAutoSmelting.get()) return;

        Player player = event.getPlayer();
        if (player == null || player.isCreative() || player.isSpectator()) return;

        ItemStack tool = player.getMainHandItem();
        if (!(tool.getItem() instanceof Tool apTool)
                || apTool.getMat() != APToolMaterial.INFUSED_LAVA_MAT
                || !apTool.getMat().config().enableWeaponEffects().get()
                || !tool.isCorrectToolForDrops(event.getState())) return;

        // Silk Touch should preserve the original block instead of smelting it.
        if (tool.getEnchantments().entrySet().stream().anyMatch(entry ->
                entry.getKey().is(Enchantments.SILK_TOUCH) && entry.getIntValue() > 0)) return;

        // Do not replace blocks with block entities (inventories, machines, etc.).
        if (level.getBlockEntity(event.getPos()) != null) return;

        Identifier blockId = BuiltInRegistries.BLOCK.getKey(event.getState().getBlock());
        if (blockId == null) return;

        Identifier outputId = findResult(blockId, ArmorPlusConfig.autoSmeltingRecipes.get());
        if (outputId == null || !BuiltInRegistries.ITEM.containsKey(outputId)) return;
        Item result = BuiltInRegistries.ITEM.getValue(outputId);
        if (result == null) return;

        if (!level.destroyBlock(event.getPos(), false, player)) return;
        event.setCanceled(true);

        ItemStack output = new ItemStack(result);
        level.addFreshEntity(new ItemEntity(level, event.getPos().getX() + 0.5,
                event.getPos().getY() + 0.5, event.getPos().getZ() + 0.5, output));
        tool.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
    }

    /** Returns the result of the first "block|result" recipe matching the block, or null. */
    private static Identifier findResult(Identifier blockId, List<? extends String> recipes) {
        for (String recipe : recipes) {
            String[] parts = recipe.split("\\|", -1);
            if (parts.length != 2) continue;
            Identifier input = Identifier.tryParse(parts[0].trim());
            if (blockId.equals(input)) return Identifier.tryParse(parts[1].trim());
        }
        return null;
    }
}
