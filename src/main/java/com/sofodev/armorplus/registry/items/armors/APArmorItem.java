package com.sofodev.armorplus.registry.items.armors;

import com.sofodev.armorplus.registry.items.extras.Buff;
import com.sofodev.armorplus.registry.items.extras.BuffInstance;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.sofodev.armorplus.utils.ItemArmorUtility.areExactMatch;
import static com.sofodev.armorplus.utils.RomanNumeralUtil.generate;
import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.util.text.TextFormatting.*;

public class APArmorItem extends ArmorItem {

    private IAPArmor mat;

    public APArmorItem(IAPArmor mat, EquipmentSlotType slot) {
        super(mat.get(), slot, mat.isImmuneToFire()
                ? mat.getProperties().defaultDurability(mat.get().getDurabilityForSlot(slot)).fireResistant()
                : mat.getProperties().defaultDurability(mat.get().getDurabilityForSlot(slot)));
        this.mat = mat;
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
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
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (!mat.getBuffInstances().get().isEmpty()) {
            tooltip.add(translate(YELLOW, "tooltip.armorplus.condition", mat.config().enableArmorEffects.get() ? "" : "(DISABLED)"));
            tooltip.add(translate(GOLD, "tooltip.armorplus.condition.full_set"));
            tooltip.add(translate(GREEN, "tooltip.armorplus.provides"));
            for (BuffInstance buff : mat.getBuffInstances().get()) {
                int lvl = buff.getAmplifier() + 1;
                String theLvl = lvl > 0 ? " " + generate(lvl) : "";
                tooltip.add(translate(DARK_AQUA, "tooltip.armorplus.buff", buff.getTranslatedName(), theLvl));
            }
        }
        //     }
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public IArmorMaterial getMaterial() {
        return mat.get();
    }

    public IAPArmor getMat() {
        return mat;
    }
}