package com.sofodev.armorplus.registry.items.armors;

import com.sofodev.armorplus.registry.items.extras.Buff;
import com.sofodev.armorplus.registry.items.extras.BuffInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

import static com.sofodev.armorplus.utils.ItemArmorUtility.areExactMatch;
import static com.sofodev.armorplus.utils.RomanNumeralUtil.generate;
import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.ChatFormatting.*;

public class APArmorItem extends ArmorItem {

    private IAPArmor mat;

    public APArmorItem(IAPArmor mat, ArmorItem.Type slot) {
        super(mat.get(), slot, mat.isImmuneToFire()
                ? mat.getProperties().defaultDurability(mat.get().getDurabilityForType(slot)).fireResistant()
                : mat.getProperties().defaultDurability(mat.get().getDurabilityForType(slot)));
        this.mat = mat;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (!world.isClientSide) {
            if (mat.config().enableArmorEffects.get()) {
                if (mat.getBuffInstances().get() == null || mat.getBuffInstances().get().isEmpty()) return;
                mat.getBuffInstances().get().forEach(instance -> {
                    if (instance.getBuff() instanceof Buff && instance.isEnabled()) {
                        if (instance.getBuff().requiresFullSet()) {
                            if (areExactMatch(mat, player)) {
                                instance.onArmorTick(stack, world, player);
                            }
                        } else {
                            instance.onArmorTick(stack, world, player);
                        }
                    }
                });
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if (!mat.getBuffInstances().get().isEmpty()) {
            tooltip.add(translate(YELLOW, "tooltip.armorplus.condition", mat.config().enableArmorEffects.get() ? "" : "(DISABLED)"));
            tooltip.add(translate(GOLD, "tooltip.armorplus.condition.full_set"));
            tooltip.add(translate(GREEN, "tooltip.armorplus.provides"));
            for (BuffInstance buff : mat.getBuffInstances().get()) {
                if (buff.getBuff() == Buff.NONE) continue;
                int lvl = buff.getAmplifier() + 1;
                String theLvl = lvl > 0 ? " " + generate(lvl) : "";
                tooltip.add(translate(DARK_AQUA, "tooltip.armorplus.buff", buff.getTranslatedName(), theLvl));
            }
        }
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public ArmorMaterial getMaterial() {
        return mat.get();
    }

    public IAPArmor getMat() {
        return mat;
    }
}