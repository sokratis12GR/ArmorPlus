package com.sofodev.armorplus.registry.item.armor;

import net.minecraft.world.item.equipment.ArmorType;

import com.sofodev.armorplus.config.AvailabilityConfig;
import com.sofodev.armorplus.registry.item.extra.Buff;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.DeBuff;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.flag.FeatureFlagSet;

import java.util.List;
import java.util.function.Consumer;
import net.minecraft.world.item.component.TooltipDisplay;

import static com.sofodev.armorplus.utils.ItemArmorUtility.areExactMatch;
import static com.sofodev.armorplus.utils.ToolTipUtils.*;
import static net.minecraft.ChatFormatting.*;

public class APArmorItem extends Item {

    private final IAPArmor mat;

    public APArmorItem(IAPArmor mat, ArmorType slot) {
        super(buildProperties(mat, slot));
        this.mat = mat;
    }

    private static Item.Properties buildProperties(IAPArmor mat, ArmorType slot) {
        Item.Properties props = mat.getProperties().stacksTo(1);
        props.humanoidArmor(mat.get(), slot).durability(mat.getDurability(slot));
        return mat.isImmuneToFire() ? props.fireResistant() : props;
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity owner, EquipmentSlot slot) {
        if (!(owner instanceof Player player) || !mat.config().enableArmorEffects().get()) return;

        List<BuffInstance> buffs = mat.getBuffInstances() != null ? mat.getBuffInstances().get() : List.of();
        if (buffs.isEmpty()) return;

        for (BuffInstance instance : buffs) {
            if (instance.getBuff() == Buff.NONE || instance.getBuff() instanceof DeBuff || !instance.isEnabled()) continue;

            if (instance.getBuff().requiresFullSet() && !areExactMatch(mat, player)) continue;

            if (instance.getBuff().isEffect()) {
                var effectHolder = instance.getBuff().getEffect();
                if (effectHolder == null) continue;
                var existing = player.getEffect(effectHolder);
                // night vision starts flashing below 11 seconds, so keep it topped up
                boolean needsRefresh = existing == null
                        || (effectHolder.value() == MobEffects.NIGHT_VISION.value() && existing.getDuration() <= 220);
                if (needsRefresh) {
                    instance.onInventoryTick(stack, level, player);
                }
            } else {
                instance.onInventoryTick(stack, level, player);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext level, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
        List<BuffInstance> buffs = mat.getBuffInstances().get();
        if (!buffs.isEmpty()) {
            if (isSneakDown()) {
                tooltip.accept(translate(YELLOW, "tooltip.armorplus.condition", mat.config().enableArmorEffects().get() ? "" : "(DISABLED)"));
                tooltip.accept(translate(GOLD, "tooltip.armorplus.condition.full_set"));
                tooltip.accept(translate(GREEN, "tooltip.armorplus.provides"));

                addBuffLines(tooltip, buffs);
            } else {
                showSneakInfo(tooltip, mat.getFormatting());
            }
        }
        super.appendHoverText(stack, level, display, tooltip, flag);
    }

    public IAPArmor getMat() { return mat; }

    @Override
    public boolean isEnabled(FeatureFlagSet ignoredFeatureFlags) {
        return AvailabilityConfig.itemEnabled(this);
    }

}
