package com.sofodev.armorplus.registry.items.special;

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

import javax.annotation.Nullable;
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
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flagIn) {
        if (world != null && world.isClientSide) {
            if (entity != null && !entity.isEmpty()) {
                EntityType<?> value = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(entity));
                if (value != null) {
                    Entity entity = value.create(world);
                    if (entity != null) {
                        tooltip.add(translate(DARK_PURPLE, "tooltip.armorplus.soul", entity.getName()));
                    }
                    if (isBoss) {
                        tooltip.add(translate(DARK_PURPLE, "tooltip.armorplus.boss_soul"));
                    }
                }
            }
        }
        super.appendHoverText(stack, world, tooltip, flagIn);
    }

//    @Override
//    public int getRGBDurabilityForDisplay(ItemStack stack) {
//        return 0;
//    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return isBoss;
    }
}
