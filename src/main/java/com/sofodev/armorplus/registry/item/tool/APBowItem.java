package com.sofodev.armorplus.registry.item.tool;

import com.sofodev.armorplus.config.AvailabilityConfig;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.RegistryContext;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.armorplus.registry.item.tool.properties.tool.IAPTool;
import com.sofodev.armorplus.registry.rune.Runes;
import com.sofodev.armorplus.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
import java.util.function.Consumer;
import net.minecraft.world.item.component.TooltipDisplay;

import static com.sofodev.armorplus.utils.ToolTipUtils.addBowDamageInformation;
import static com.sofodev.armorplus.utils.ToolTipUtils.addBuffLines;
import static com.sofodev.armorplus.utils.ToolTipUtils.addExtractedLines;
import static com.sofodev.armorplus.utils.ToolTipUtils.addDetailsHint;
import static com.sofodev.armorplus.utils.ToolTipUtils.clientPlayer;
import static com.sofodev.armorplus.utils.ToolTipUtils.effectsHeader;
import static com.sofodev.armorplus.utils.ToolTipUtils.isSneakDown;
import static com.sofodev.armorplus.utils.ToolTipUtils.showSneakInfo;

public class APBowItem extends BowItem {

    private final IAPTool tool;

    public APBowItem(IAPTool tool) {
        super(RegistryContext.itemProperties().durability((int) (tool.get().durability() * 0.5)));
        this.tool = tool;
    }

    public IAPTool getMat() {
        return tool;
    }

    @Override
    protected void shootProjectile(LivingEntity shooter, Projectile projectile, int index, float velocity, float inaccuracy, float angle, LivingEntity target) {
        if (projectile instanceof AbstractArrow arrow) {
            arrow.setBaseDamage(arrow.baseDamage + tool.getBowDamageBonus());
        }

        super.shootProjectile(shooter, projectile, index, velocity, inaccuracy, angle, target);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flagIn) {
        if (isSneakDown()) {
            addBowDamageInformation(tooltip, tool.getBowDamageBonus());
            List<BuffInstance> effects = Runes.nativeRangedRunes(stack).stream().map(Runes::resolve).toList();
            List<BuffInstance> extracted = Runes.extractedRangedBuffs(stack);
            if (!effects.isEmpty() || !extracted.isEmpty()) {
                boolean enabled = tool.config().enableWeaponEffects().get();
                tooltip.accept(effectsHeader("on_hit", enabled));
                Player player = clientPlayer();
                addBuffLines(tooltip, effects, ToolTipUtils::durationPrefix,
                        buff -> enabled && (player == null || !RangedEffects.isDisabled(player, BuffParser.idOf(buff.getBuff()))),
                        ToolTipUtils::rangedCost);
                addExtractedLines(tooltip, extracted);
                addDetailsHint(tooltip, effects, tool.getColor());
            }
        } else {
            showSneakInfo(tooltip, tool.getColor());
        }

        super.appendHoverText(stack, ctx, display, tooltip, flagIn);
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