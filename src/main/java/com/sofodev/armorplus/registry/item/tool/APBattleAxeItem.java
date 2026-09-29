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
import java.util.function.Consumer;
import net.minecraft.world.item.component.TooltipDisplay;

import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolType.BATTLE_AXE;
import static com.sofodev.armorplus.utils.ToolTipUtils.*;

public class APBattleAxeItem extends AxeItem implements Tool {

    private final IAPTool mat;

    public APBattleAxeItem(IAPTool mat) {
        super(mat.get(), (float) mat.get().attackDamageBonus() + BATTLE_AXE.getDmg(), BATTLE_AXE.getAttackSpeed(), com.sofodev.armorplus.registry.RegistryContext.itemProperties());
        this.mat = mat;
    }

    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level().isClientSide() && mat.config().enableWeaponEffects().get()) {
            mat.getBuffInstances().get().forEach(instance -> instance.hitEntity(stack, target, attacker));
        }
        super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flagIn) {
        if (isSneakDown()) {
            addBuffInformation(mat, tooltip, "on_hit", false, mat.config().enableWeaponEffects().get());
        } else if (!mat.getBuffInstances().get().isEmpty()) {
            showSneakInfo(tooltip, mat.getColor());
        }
        super.appendHoverText(stack, ctx, display, tooltip, flagIn);
    }

    public IAPTool getMat() {
        return this.mat;
    }

    @Override
    public Component getName(ItemStack stack) {
        return super.getName(stack).copy().withStyle(mat.getColor());
    }
}