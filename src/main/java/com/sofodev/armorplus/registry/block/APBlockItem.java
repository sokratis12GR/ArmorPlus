package com.sofodev.armorplus.registry.block;

import net.minecraft.world.flag.FeatureFlagSet;
import com.sofodev.armorplus.config.AvailabilityConfig;
import net.minecraft.world.item.BlockItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

import static com.sofodev.armorplus.utils.ToolTipUtils.addItemLore;

public class APBlockItem extends BlockItem {

    public APBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    public APBlockItem(Block blockIn) {
        super(blockIn, new Properties());
    }

    @Override
    public boolean isEnabled(FeatureFlagSet ignoredFeatureFlags) {
        return AvailabilityConfig.itemEnabled(this);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, List<Component> tooltip, TooltipFlag flag) {
        addItemLore(tooltip, this);
        super.appendHoverText(stack, ctx, tooltip, flag);
    }
}
