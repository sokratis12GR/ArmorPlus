package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.item.tool.properties.tool.APToolMaterial;
import com.sofodev.armorplus.registry.item.tool.properties.tool.Tool;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

/** Handles the legacy parallel smelting config without changing registry state. */
@Mod.EventBusSubscriber(modid = ArmorPlus.MODID)
public final class AutoSmeltingEvents {
    private AutoSmeltingEvents() {}

    @SubscribeEvent
    public static void onBreak(BlockEvent.BreakEvent event) {
        if (!(event.getLevel() instanceof ServerLevel level) || !ArmorPlusConfig.enableAutoSmelting.get()) return;
        Player player = event.getPlayer();
        if (player == null || player.isCreative() || player.isSpectator()) return;
        ItemStack tool = player.getMainHandItem();
        if (!(tool.getItem() instanceof Tool apTool) || apTool.getMat() != APToolMaterial.INFUSED_LAVA_MAT) return;
        if (!apTool.getMat().config().enableWeaponEffects().get()) return;
        if (!tool.isCorrectToolForDrops(event.getState())) return;
        // Respect Silk Touch, which should not accidentally transform the target block.
        if (tool.getEnchantments().entrySet().stream().anyMatch(entry ->
                entry.getKey().is(Enchantments.SILK_TOUCH) && entry.getIntValue() > 0)) return;

        List<? extends String> inputs = ArmorPlusConfig.autoSmeltingInput.get();
        List<? extends String> outputs = ArmorPlusConfig.autoSmeltingOutput.get();
        ResourceLocation blockId = ForgeRegistries.BLOCKS.getKey(event.getState().getBlock());
        if (blockId == null) return;
        int index = inputs.indexOf(blockId.toString());
        if (index < 0 || index >= outputs.size()) return;
        ResourceLocation outputId = ResourceLocation.tryParse(outputs.get(index));
        if (outputId == null || !ForgeRegistries.ITEMS.containsKey(outputId)) return;
        Item result = ForgeRegistries.ITEMS.getValue(outputId);
        if (result == null) return;

        // Handle the break ourselves to avoid dropping both original and smelted results.
        // Never intercept block entities: inventories and custom break hooks must be preserved.
        if (level.getBlockEntity(event.getPos()) != null) return;
        event.setCanceled(true);
        if (!level.destroyBlock(event.getPos(), false, player)) return;
        ItemStack output = new ItemStack(result);
        level.addFreshEntity(new ItemEntity(level, event.getPos().getX() + .5, event.getPos().getY() + .5,
                event.getPos().getZ() + .5, output));
        tool.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
    }
}
