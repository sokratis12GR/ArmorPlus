package com.sofodev.armorplus.registry.item.special;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.List;
import java.util.function.Consumer;
import net.minecraft.world.item.component.TooltipDisplay;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.ChatFormatting.DARK_PURPLE;

public class SoulItem extends Item {

    private final boolean isBoss;
    private final String entity;

    public SoulItem(String entity) {
        super(com.sofodev.armorplus.registry.RegistryContext.itemProperties().rarity(Rarity.EPIC).fireResistant().stacksTo(16));
        this.entity = entity;
        this.isBoss = true;
    }

    public SoulItem(boolean isBoss, String entity) {
        super(com.sofodev.armorplus.registry.RegistryContext.itemProperties().rarity(Rarity.RARE).fireResistant().stacksTo(32));
        this.entity = entity;
        this.isBoss = isBoss;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flagIn) {
        Level level = ctx.level();
        if (level != null && level.isClientSide()) {
            if (entity != null && !entity.isEmpty()) {
                EntityType<?> value = BuiltInRegistries.ENTITY_TYPE.getValue(Identifier.parse(entity));
                if (value != null) {
                    Entity entity = value.create(level, EntitySpawnReason.SPAWN_ITEM_USE);
                    if (entity != null) {
                        tooltip.accept(translate(DARK_PURPLE, "tooltip.armorplus.soul", entity.getName()));
                    }
                    if (isBoss) {
                        tooltip.accept(translate(DARK_PURPLE, "tooltip.armorplus.boss_soul"));
                    }
                }
            }
        }
        super.appendHoverText(stack, ctx, display, tooltip, flagIn);
    }


    @Override
    public boolean isFoil(ItemStack stack) {
        return isBoss;
    }
}
