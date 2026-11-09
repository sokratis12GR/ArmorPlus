package com.sofodev.armorplus.registry.item.tool;

import net.minecraft.world.flag.FeatureFlagSet;
import com.sofodev.armorplus.config.AvailabilityConfig;
import com.sofodev.armorplus.registry.item.tool.properties.tool.IAPTool;
import com.sofodev.armorplus.registry.item.tool.properties.tool.Tool;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolType.SHOVEL;
import static com.sofodev.armorplus.utils.ToolTipUtils.addBuffInformation;
import static com.sofodev.armorplus.utils.ToolTipUtils.isSneakDown;
import static com.sofodev.armorplus.utils.ToolTipUtils.showSneakInfo;

public class APShovelItem extends ShovelItem implements Tool {

    private final IAPTool mat;

    public APShovelItem(IAPTool mat) {
        super(mat.get(), new Item.Properties().attributes(ShovelItem.createAttributes(mat.get(), mat.get().getAttackDamageBonus() + SHOVEL.getDmg(), SHOVEL.getAttackSpeed())));
        this.mat = mat;
    }

    @Override
    public Component getName(ItemStack stack) {
        return super.getName(stack).copy().withStyle(mat.getColor());
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, List<Component> tooltip, TooltipFlag flagIn) {
        if (isSneakDown()) {
            addBuffInformation(stack, mat, tooltip, "on_hit", false, mat.config().enableWeaponEffects().get());
        } else if (!mat.getBuffInstances().get().isEmpty()) {
            showSneakInfo(tooltip, mat.getColor());
        }
        super.appendHoverText(stack, ctx, tooltip, flagIn);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity player) {
        if (!player.level().isClientSide && mat.config().enableWeaponEffects().get()) {
            mat.onBlockMined(stack, world, state, pos, player);
        }
        return super.mineBlock(stack, world, state, pos, player);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level().isClientSide && mat.config().enableWeaponEffects().get()) {
            WeaponEffects.hit(stack, mat, target, attacker);
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public IAPTool getMat() {
        return mat;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet ignoredFeatureFlags) {
        return AvailabilityConfig.itemEnabled(this);
    }
}
