package com.sofodev.armorplus.registry.item.armor;

import com.sofodev.armorplus.registry.item.extra.Buff;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

import static com.sofodev.armorplus.utils.ItemArmorUtility.areExactMatch;
import static com.sofodev.armorplus.utils.RomanNumeralUtil.generate;
import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.ChatFormatting.*;

public class APArmorItem extends ArmorItem {

    private final IAPArmor mat;

    public APArmorItem(IAPArmor mat, ArmorItem.Type slot) {
        super(mat.get().get(), slot, mat.isImmuneToFire()
                ? mat.getProperties().fireResistant()
                : mat.getProperties());
        this.mat = mat;
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        if (level.isClientSide() || !mat.config().enableArmorEffects().get()) return;

        List<BuffInstance> buffs = mat.getBuffInstances() != null ? mat.getBuffInstances().get() : List.of();
        if (buffs.isEmpty()) return;

        for (BuffInstance instance : buffs) {
            if (!(instance.getBuff() instanceof Buff) || !instance.isEnabled()) continue;

            if (instance.getBuff().requiresFullSet() && !areExactMatch(mat, player)) continue;

            if (instance.getBuff().isEffect()) {
                if (!player.hasEffect(instance.getEffect().getEffect())) {
                    instance.onInventoryTick(stack, level, player);
                }
            } else {
                instance.onInventoryTick(stack, level, player);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext level, List<Component> tooltip, TooltipFlag flag) {
        List<BuffInstance> buffs = mat.getBuffInstances().get();
        if (!buffs.isEmpty()) {
            tooltip.add(translate(YELLOW, "tooltip.armorplus.condition", mat.config().enableArmorEffects().get() ? "" : "(DISABLED)"));
            tooltip.add(translate(GOLD, "tooltip.armorplus.condition.full_set"));
            tooltip.add(translate(GREEN, "tooltip.armorplus.provides"));

            for (BuffInstance buff : buffs) {
                if (buff.getBuff() == Buff.NONE) continue;
                int lvl = buff.getAmplifier() + 1;
                String roman = lvl > 0 ? " " + generate(lvl) : "";
                tooltip.add(translate(DARK_AQUA, "tooltip.armorplus.buff", buff.getTranslatedName(), roman));
            }
        }
        super.appendHoverText(stack, level, tooltip, flag);
    }

    @Override
    public Holder<ArmorMaterial> getMaterial() {
        return mat.get().get();
    }

    public IAPArmor getMat() {
        return mat;
    }
}