package com.sofodev.armorplus.registry.items.tools;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.tools.properties.tool.IAPTool;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

import static com.sofodev.armorplus.registry.items.tools.properties.tool.APToolType.SWORD;
import static com.sofodev.armorplus.utils.ToolTipUtils.addBuffInformation;

public class APSwordItem extends SwordItem {

    private final IAPTool mat;

    public APSwordItem(IAPTool mat) {
        super(mat.get(), (int) (mat.get().getAttackDamage() + SWORD.getDmg()), SWORD.getAttackSpeed(), new Properties().group(ArmorPlus.AP_WEAPON_GROUP));
        this.mat = mat;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return mat.getRarity();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        addBuffInformation(mat, tooltip, "on_hit", false);
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.world.isRemote) {
            Arrays.stream(mat.getBuffInstances()).forEach(instance -> instance.hitEntity(stack, target, attacker));
        }
        return super.hitEntity(stack, target, attacker);
    }

}