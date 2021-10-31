package com.sofodev.armorplus.registry.items.special;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;

import static com.sofodev.armorplus.ArmorPlus.AP_ITEM_GROUP;
import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.util.text.TextFormatting.DARK_PURPLE;

public class SoulItem extends Item {

    private final boolean isBoss;
    private final String entity;

    public SoulItem(String entity) {
        super(new Properties().tab(AP_ITEM_GROUP).rarity(Rarity.EPIC).fireResistant().stacksTo(16));
        this.entity = entity;
        this.isBoss = true;
    }

    public SoulItem(boolean isBoss, String entity) {
        super(new Properties().tab(AP_ITEM_GROUP).rarity(Rarity.RARE).fireResistant().stacksTo(32));
        this.entity = entity;
        this.isBoss = isBoss;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (world != null && world.isClientSide) {
            if (entity != null && !entity.isEmpty()) {
                EntityType<?> value = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(entity));
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

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return 0;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return isBoss;
    }
}
