package com.sofodev.armorplus.registry.item.tool;

import com.sofodev.armorplus.config.AvailabilityConfig;
import com.sofodev.armorplus.registry.item.tool.properties.tool.IAPTool;
import com.sofodev.armorplus.registry.RegistryContext;
import com.sofodev.armorplus.registry.item.tool.properties.tool.Tool;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolType.SWORD;
import static com.sofodev.armorplus.utils.ToolTipUtils.addBuffInformation;
import static com.sofodev.armorplus.utils.ToolTipUtils.isSneakDown;
import static com.sofodev.armorplus.utils.ToolTipUtils.showSneakInfo;

public class APSwordItem extends Item implements Tool {

    private final IAPTool mat;

    public APSwordItem(IAPTool mat) {
        super(RegistryContext.itemProperties().sword(mat.get(), (float) mat.get().attackDamageBonus() + SWORD.getDmg(), SWORD.getAttackSpeed()));
        this.mat = mat;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flagIn) {
        if (isSneakDown()) {
            addBuffInformation(stack, mat, tooltip, "on_hit", false, mat.config().enableWeaponEffects().get());
        } else if (!mat.getBuffInstances().get().isEmpty() || !mat.getHeldBuffs().isEmpty()) {
            showSneakInfo(tooltip, mat.getColor());
        }
        super.appendHoverText(stack, ctx, display, tooltip, flagIn);
    }

    @Override
    public Component getName(ItemStack stack) {
        return super.getName(stack).copy().withStyle(mat.getColor());
    }

    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level().isClientSide() && mat.config().enableWeaponEffects().get()) {
            WeaponEffects.hit(stack, mat, target, attacker);
        }
        super.hurtEnemy(stack, target, attacker);
    }

    public IAPTool getMat() {
        return this.mat;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet ignoredFeatureFlags) {
        return AvailabilityConfig.itemEnabled(this);
    }
}