package com.sofodev.armorplus.registry.item.special;

import com.sofodev.armorplus.config.AvailabilityConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.Consumer;
import net.minecraft.world.item.component.TooltipDisplay;

import static com.sofodev.armorplus.utils.ToolTipUtils.addItemLore;
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
        if (entity != null && !entity.isEmpty()) {
            BuiltInRegistries.ENTITY_TYPE.getOptional(Identifier.parse(entity)).ifPresent(type -> {
                tooltip.accept(translate(DARK_PURPLE, "tooltip.armorplus.soul", type.getDescription()));
                if (isBoss) tooltip.accept(translate(DARK_PURPLE, "tooltip.armorplus.boss_soul"));
            });
        }
        addItemLore(tooltip, this);
        super.appendHoverText(stack, ctx, display, tooltip, flagIn);
    }


    @Override
    public boolean isFoil(ItemStack stack) {
        return isBoss;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet ignoredFeatureFlags) {
        return AvailabilityConfig.itemEnabled(this);
    }
}
