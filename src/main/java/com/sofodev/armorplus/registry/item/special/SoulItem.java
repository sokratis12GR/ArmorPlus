package com.sofodev.armorplus.registry.item.special;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.ChatFormatting.DARK_PURPLE;

public class SoulItem extends Item {

    private final boolean isBoss;
    private final String entity;

    public SoulItem(String entity) {
        super(new Properties().rarity(Rarity.EPIC).fireResistant().stacksTo(16));
        this.entity = entity;
        this.isBoss = true;
    }

    public SoulItem(boolean isBoss, String entity) {
        super(new Properties().rarity(Rarity.RARE).fireResistant().stacksTo(32));
        this.entity = entity;
        this.isBoss = isBoss;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, List<Component> tooltip, TooltipFlag flagIn) {
        Level level = ctx.level();
        if (level != null && level.isClientSide) {
            if (entity != null && !entity.isEmpty()) {
                EntityType<?> value = ForgeRegistries.ENTITY_TYPES.getValue(ResourceLocation.parse(entity));
                if (value != null) {
                    Entity entity = value.create(level);
                    if (entity != null) {
                        tooltip.add(translate(DARK_PURPLE, "tooltip.armorplus.soul", entity.getName()));
                    }
                    if (isBoss) {
                        tooltip.add(translate(DARK_PURPLE, "tooltip.armorplus.boss_soul"));
                    }
                }
            }
        }
        super.appendHoverText(stack, ctx, tooltip, flagIn);
    }


    @Override
    public boolean isFoil(ItemStack stack) {
        return isBoss;
    }
}
