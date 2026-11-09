package com.sofodev.armorplus.registry.item.tool;

import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import com.sofodev.armorplus.config.AvailabilityConfig;
import com.sofodev.armorplus.registry.item.tool.properties.tool.IAPTool;
import com.sofodev.armorplus.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class APBowItem extends BowItem {

    private final IAPTool tool;

    public APBowItem(IAPTool tool) {
        super(new Properties().durability((int) (tool.get().getUses() * 0.5)));
        this.tool = tool;
    }

    public IAPTool getMat() {
        return tool;
    }

    @Override
    protected void shootProjectile(LivingEntity shooter, Projectile projectile, int index, float velocity, float inaccuracy, float angle, LivingEntity target) {
        if (projectile instanceof AbstractArrow arrow) {
            arrow.setBaseDamage(arrow.getBaseDamage() + tool.getBowDamageBonus());
        }

        super.shootProjectile(shooter, projectile, index, velocity, inaccuracy, angle, target);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        if (ToolTipUtils.isSneakDown()) {
            ToolTipUtils.addBowDamageInformation(tooltip, tool.getBowDamageBonus());
            List<BuffInstance> effects = Runes.nativeRangedRunes(stack).stream().map(Runes::resolve).toList();
            List<BuffInstance> extracted = Runes.extractedRangedBuffs(stack);
            if (!effects.isEmpty() || !extracted.isEmpty()) {
                boolean enabled = tool.config().enableWeaponEffects().get();
                tooltip.add(ToolTipUtils.effectsHeader("on_hit", enabled));
                Player player = ToolTipUtils.clientPlayer();
                ToolTipUtils.addBuffLines(tooltip, effects, ToolTipUtils::durationPrefix,
                        buff -> enabled && (player == null || !RangedEffects.isDisabled(player, BuffParser.idOf(buff.getBuff()))),
                        ToolTipUtils::rangedCost);
                ToolTipUtils.addExtractedLines(tooltip, extracted);
                ToolTipUtils.addDetailsHint(tooltip, effects, tool.getColor());
            }
        } else {
            ToolTipUtils.showSneakInfo(tooltip, tool.getColor());
        }

        super.appendHoverText(stack, context, tooltip, flag);
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return super.isValidRepairItem(toRepair, repair);
    }

    @Override
    public Component getName(ItemStack stack) {
        return super.getName(stack).copy().withStyle(tool.getColor());
    }

    @Override
    public boolean isEnabled(FeatureFlagSet ignoredFeatureFlags) {
        return AvailabilityConfig.itemEnabled(this);
    }
}
