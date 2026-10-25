package com.sofodev.armorplus.registry.item.tool;

import com.sofodev.armorplus.config.AvailabilityConfig;
import com.sofodev.armorplus.registry.item.tool.properties.tool.APToolType;
import com.sofodev.armorplus.registry.item.tool.properties.tool.IAPTool;
import com.sofodev.armorplus.registry.item.tool.properties.tool.Tool;
import net.minecraft.network.chat.Component;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;

public class APShovelItem extends ShovelItem implements Tool {
    private final IAPTool mat;


    public APShovelItem(IAPTool mat) {
        super(mat.get(), new Properties().attributes(ShovelItem.createAttributes(mat.get(),
                APToolType.SHOVEL.getDmg(), APToolType.SHOVEL.getAttackSpeed())));
        this.mat = mat;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet ignoredFeatureFlags) {
        return AvailabilityConfig.itemEnabled(this);
    }

    @Override
    public Component getName(ItemStack stack) {
        return super.getName(stack).copy().withStyle(mat.getColor());
    }

    @Override
    public IAPTool getMat() {
        return mat;
    }
}