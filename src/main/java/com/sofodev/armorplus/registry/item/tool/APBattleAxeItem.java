package com.sofodev.armorplus.registry.item.tool;

import com.sofodev.armorplus.registry.item.tool.properties.tool.APToolType;
import com.sofodev.armorplus.registry.item.tool.properties.tool.IAPTool;
import com.sofodev.armorplus.registry.item.tool.properties.tool.Tool;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolType.BATTLE_AXE;
import static com.sofodev.armorplus.utils.ToolTipUtils.addBuffInformation;

public class APBattleAxeItem extends AxeItem implements Tool {

    private final IAPTool mat;

    public APBattleAxeItem(IAPTool mat) {
        super(mat.get(), new Item.Properties().attributes(AxeItem.createAttributes(mat.get(),
                mat.get().getAttackDamageBonus() + BATTLE_AXE.getDmg(), BATTLE_AXE.getAttackSpeed())));
        this.mat = mat;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level().isClientSide && mat.config().enableWeaponEffects().get()) {
            mat.getBuffInstances().get().forEach(instance -> instance.hitEntity(stack, target, attacker));
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, List<Component> tooltip, TooltipFlag flagIn) {
        addBuffInformation(mat, tooltip, "on_hit", false, mat.config().enableWeaponEffects().get());
        super.appendHoverText(stack, ctx, tooltip, flagIn);
    }

    public IAPTool getMat() {
        return this.mat;
    }

    @Override
    public Component getName(ItemStack stack) {
        return super.getName(stack).copy().withStyle(mat.getColor());
    }
}