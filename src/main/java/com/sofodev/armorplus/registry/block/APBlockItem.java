package com.sofodev.armorplus.registry.block;

import com.sofodev.armorplus.config.AvailabilityConfig;
import com.sofodev.armorplus.registry.RegistryContext;
import net.minecraft.network.chat.Component;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

import static com.sofodev.armorplus.utils.ToolTipUtils.addItemLore;

public class APBlockItem extends BlockItem {

    public APBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    public APBlockItem(Block blockIn) {
        super(blockIn, RegistryContext.itemProperties().useBlockDescriptionPrefix());
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
        addItemLore(tooltip, this);
        super.appendHoverText(stack, ctx, display, tooltip, flag);
    }

    @Override
    public boolean isEnabled(FeatureFlagSet ignoredFeatureFlags) {
        return AvailabilityConfig.itemEnabled(this);
    }
}