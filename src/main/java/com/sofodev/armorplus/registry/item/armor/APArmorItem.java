package com.sofodev.armorplus.registry.item.armor;

import net.minecraft.world.item.equipment.ArmorType;

import com.sofodev.armorplus.api.EffectScopes;
import com.sofodev.armorplus.config.ArmorPlusConfig.MaterialConfig;
import com.sofodev.armorplus.config.AvailabilityConfig;
import com.sofodev.armorplus.registry.item.extra.Buff;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.armorplus.registry.item.extra.DeBuff;
import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.flag.FeatureFlagSet;

import java.util.List;
import java.util.function.Consumer;
import net.minecraft.world.item.component.TooltipDisplay;

import static com.sofodev.armorplus.utils.ItemArmorUtility.areExactMatch;
import static com.sofodev.armorplus.utils.ToolTipUtils.addBuffLines;
import static com.sofodev.armorplus.utils.ToolTipUtils.addExtractedLines;
import static com.sofodev.armorplus.utils.ToolTipUtils.addDetailsHint;
import static com.sofodev.armorplus.utils.ToolTipUtils.clientPlayer;
import static com.sofodev.armorplus.utils.ToolTipUtils.effectsHeader;
import static com.sofodev.armorplus.utils.ToolTipUtils.instantCooldown;
import static com.sofodev.armorplus.utils.ToolTipUtils.isSneakDown;
import static com.sofodev.armorplus.utils.ToolTipUtils.showSneakInfo;

public class APArmorItem extends Item {

    private final IAPArmor mat;
    private final EquipmentSlot slot;

    public APArmorItem(IAPArmor mat, ArmorType slot) {
        super(buildProperties(mat, slot));
        this.mat = mat;
        this.slot = slot.getSlot();
    }

    private static Item.Properties buildProperties(IAPArmor mat, ArmorType slot) {
        Item.Properties props = mat.getProperties().stacksTo(1);
        props.humanoidArmor(mat.get(), slot).durability(mat.getDurability(slot));
        return mat.isImmuneToFire() ? props.fireResistant() : props;
    }

    // Set effects are applied once a second for all worn pieces together, see ArmorEffects and RuneEvents#onPlayerTick

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext level, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
        // effects bound to another armor piece ("effectSlots") are listed on that piece only
        List<BuffInstance> buffs = Runes.nativeBuffs(stack, mat.getBuffInstances().get()).stream().filter(buff -> ArmorEffects.shownOn(buff, slot)).toList();
        List<BuffInstance> extracted = Runes.extractedBuffs(stack, mat.getBuffInstances().get()).stream().filter(buff -> ArmorEffects.shownOn(buff, slot)).toList();
        if (!buffs.isEmpty() || !extracted.isEmpty()) {
            if (isSneakDown()) {
                boolean enabled = mat.config().enableArmorEffects().get();
                tooltip.accept(effectsHeader("set_effects", enabled));

                // "[3/4]": worn pieces of this set out of the ones the effect needs, "[0/1]": needs this piece worn.
                // Tooltips are rebuilt every frame, so this follows equipping, unequipping and breaking pieces
                Player player = clientPlayer();
                String source = mat.config() instanceof MaterialConfig cfg ? cfg.name() : "";
                addBuffLines(tooltip, buffs, buff -> ArmorEffects.progress(player, mat, buff),
                        buff -> enabled && (player == null || !EffectScopes.ARMOR.isDisabled(player, source, BuffParser.idOf(buff.getBuff()))),
                        buff -> instantCooldown(buff, false));
                addExtractedLines(tooltip, extracted);
                addDetailsHint(tooltip, buffs, mat.getFormatting());
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
