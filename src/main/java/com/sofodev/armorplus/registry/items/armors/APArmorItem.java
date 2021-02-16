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
import java.util.Arrays;
import java.util.List;

import static com.sofodev.armorplus.registry.items.armors.APArmorMaterial.*;
import static com.sofodev.armorplus.utils.ItemArmorUtility.areExactMatch;
import static com.sofodev.armorplus.utils.RomanNumeralUtil.generate;
import static com.sofodev.armorplus.utils.ToolTipUtils.addExperimentalItemInformation;
import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.util.text.TextFormatting.*;

public class APArmorItem extends ArmorItem {

    private final IAPArmor mat;

    public APArmorItem(IAPArmor mat, EquipmentSlotType slot) {
        super(mat.get(), slot, mat.isImmuneToFire()
                ? mat.getProperties().defaultMaxDamage(mat.get().getDurability(slot)).isImmuneToFire()
                : mat.getProperties().defaultMaxDamage(mat.get().getDurability(slot)));
        this.mat = mat;
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (!world.isRemote) {
            Arrays.stream(mat.getBuffInstances()).forEach(instance -> {
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

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (mat.getBuffInstances().length > 0) {
            tooltip.add(translate(YELLOW, "tooltip.armorplus.condition"));
            tooltip.add(translate(GOLD, "tooltip.armorplus.condition.full_set"));
            tooltip.add(translate(GREEN, "tooltip.armorplus.provides"));
            for (BuffInstance buff : mat.getBuffInstances()) {
                int lvl = buff.getAmplifier() + 1;
                String theLvl = lvl > 0 ? " " + generate(lvl) : "";
                tooltip.add(translate(DARK_AQUA, "tooltip.armorplus.buff", buff.getTranslatedName(), theLvl));
            }
        }
        if (mat.equals(FROST) || mat.equals(FROST_LAVA) || mat.equals(MANYULLYN) || mat.equals(ARDITE) || mat.equals(COBALT) || mat.equals(KNIGHT_SLIME) || mat.equals(PIG_IRON) || mat.equals(SLAYER)) {
            addExperimentalItemInformation(tooltip);
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public IArmorMaterial getArmorMaterial() {
        return mat.get();
    }

    public IAPArmor getMat() {
        return mat;
    }
}