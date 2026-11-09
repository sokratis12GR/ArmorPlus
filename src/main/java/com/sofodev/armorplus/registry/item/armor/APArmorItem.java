package com.sofodev.armorplus.registry.item.armor;

import net.minecraft.world.flag.FeatureFlagSet;
import com.sofodev.armorplus.config.AvailabilityConfig;
import com.sofodev.armorplus.config.ArmorPlusConfig.MaterialConfig;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

import static com.sofodev.armorplus.utils.ToolTipUtils.addBuffLines;
import static com.sofodev.armorplus.utils.ToolTipUtils.addDetailsHint;
import static com.sofodev.armorplus.utils.ToolTipUtils.addExtractedLines;
import static com.sofodev.armorplus.utils.ToolTipUtils.clientPlayer;
import static com.sofodev.armorplus.utils.ToolTipUtils.effectsHeader;
import static com.sofodev.armorplus.utils.ToolTipUtils.instantCooldown;
import static com.sofodev.armorplus.utils.ToolTipUtils.isSneakDown;
import static com.sofodev.armorplus.utils.ToolTipUtils.showSneakInfo;

public class APArmorItem extends ArmorItem {

    private final IAPArmor mat;

    public APArmorItem(IAPArmor mat, ArmorItem.Type slot) {
        super(mat.get().get(), slot, buildProperties(mat, slot));
        this.mat = mat;
    }

    private static Item.Properties buildProperties(IAPArmor mat, ArmorItem.Type slot) {
        Item.Properties props = mat.getProperties().stacksTo(1);
        props.durability(mat.getDurability(slot));
        return mat.isImmuneToFire() ? props.fireResistant() : props;
    }

    // Set effects are applied for all worn pieces together, see ArmorEffects and ModGlobalEvents#onPlayerTickEvent

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        // effects bound to other armor pieces ("effectSlots") are listed on those pieces only
        EquipmentSlot slot = getEquipmentSlot();
        // at this item's levels (upgraded in the ritual), extracted effects listed struck through
        List<BuffInstance> buffs = Runes.nativeBuffs(stack, mat.getBuffInstances().get()).stream().filter(buff -> ArmorEffects.shownOn(buff, slot)).toList();
        List<BuffInstance> extracted = Runes.extractedBuffs(stack, mat.getBuffInstances().get()).stream().filter(buff -> ArmorEffects.shownOn(buff, slot)).toList();

        if (!buffs.isEmpty() || !extracted.isEmpty()) {
            if (isSneakDown()) {
                boolean enabled = mat.config().enableArmorEffects().get();
                tooltip.add(effectsHeader("set_effects", enabled));

                // "[3/4]": worn pieces of this set out of the ones the effect needs, "[0/1]": needs this piece worn.
                // Tooltips are rebuilt every frame, so this follows equipping, unequipping and breaking pieces
                Player player = clientPlayer();
                String source = mat.config() instanceof MaterialConfig cfg ? cfg.name() : "";
                addBuffLines(tooltip, buffs, buff -> ArmorEffects.progress(player, mat, buff),
                        buff -> enabled && (player == null || !ArmorEffects.isDisabled(player, ArmorEffects.toggleKey(source, BuffParser.idOf(buff.getBuff())))),
                        buff -> instantCooldown(buff, false));
                addExtractedLines(tooltip, extracted);
                addDetailsHint(tooltip, buffs, mat.getFormatting());
            } else {
                showSneakInfo(tooltip, mat.getFormatting());
            }
        }

        super.appendHoverText(stack, context, tooltip, flag);
    }


    @Override
    public Holder<ArmorMaterial> getMaterial() {
        return mat.get().get();
    }

    @Override
    public boolean isValidRepairItem(ItemStack currentStack, ItemStack repairStack) {
        return mat.get().get().value().repairIngredient().get().test(repairStack) || super.isValidRepairItem(currentStack, repairStack);
    }

    public IAPArmor getMat() {
        return mat;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet ignoredFeatureFlags) {
        return AvailabilityConfig.itemEnabled(this);
    }
}
