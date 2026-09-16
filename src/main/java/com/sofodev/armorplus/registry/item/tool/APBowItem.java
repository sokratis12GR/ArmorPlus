package com.sofodev.armorplus.registry.item.tool;

import com.sofodev.armorplus.registry.item.tool.properties.tool.IAPTool;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

import static com.sofodev.armorplus.utils.ToolTipUtils.addBowDamageInformation;

public class APBowItem extends BowItem {

    private final IAPTool tool;

    public APBowItem(IAPTool tool) {
        super(new Properties().durability((int) (tool.get().getUses() * 0.5)));
        this.tool = tool;
    }

    @Override
    protected void shootProjectile(LivingEntity shooter, Projectile projectile, int index, float velocity, float inaccuracy, float angle, LivingEntity target) {
        if (projectile instanceof AbstractArrow arrow) {
            arrow.setBaseDamage(arrow.getBaseDamage() + tool.getBowDamageBonus());
        }

        super.shootProjectile(shooter, projectile, index, velocity, inaccuracy, angle, target);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, List<Component> tooltip, TooltipFlag flagIn) {
        addBowDamageInformation(tooltip, tool.getBowDamageBonus());

        super.appendHoverText(stack, ctx, tooltip, flagIn);
    }


    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return super.isValidRepairItem(toRepair, repair);
    }

    @Override
    public Component getName(ItemStack stack) {
        return super.getName(stack).copy().withStyle(tool.getColor());
    }
}