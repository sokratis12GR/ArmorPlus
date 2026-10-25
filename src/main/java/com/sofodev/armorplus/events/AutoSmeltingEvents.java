package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.item.tool.properties.tool.APToolMaterial;
import com.sofodev.armorplus.registry.item.tool.properties.tool.Tool;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.util.Result;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

/** Applies the COMMON config's parallel block/item lists to Infused Lava tools. */
@Mod.EventBusSubscriber(modid = ArmorPlus.MODID)
public final class AutoSmeltingEvents {
    private AutoSmeltingEvents() {
    }

    @SubscribeEvent
    public static void onBreak(BlockEvent.BreakEvent event) {
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

        List<? extends String> inputs = ArmorPlusConfig.autoSmeltingInput.get();
        List<? extends String> outputs = ArmorPlusConfig.autoSmeltingOutput.get();
        Identifier blockId = ForgeRegistries.BLOCKS.getKey(event.getState().getBlock());
        if (blockId == null) return;
        int index = inputs.indexOf(blockId.toString());
        if (index < 0 || index >= outputs.size()) return;

        Identifier outputId = Identifier.tryParse(outputs.get(index));
        if (outputId == null || !ForgeRegistries.ITEMS.containsKey(outputId)) return;
        Item result = ForgeRegistries.ITEMS.getValue(outputId);
        if (result == null) return;

        if (!level.destroyBlock(event.getPos(), false, player)) return;
        event.setResult(Result.DENY);

        ItemStack output = new ItemStack(result);
        level.addFreshEntity(new ItemEntity(level, event.getPos().getX() + 0.5,
                event.getPos().getY() + 0.5, event.getPos().getZ() + 0.5, output));
        tool.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
    }
}
