package com.sofodev.armorplus.registry.item.tool;

import com.sofodev.armorplus.registry.item.tool.properties.tool.APToolType;
import com.sofodev.armorplus.registry.item.tool.properties.tool.IAPTool;
import com.sofodev.armorplus.registry.item.tool.properties.tool.Tool;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.function.Consumer;
import net.minecraft.world.item.component.TooltipDisplay;

import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolType.PICKAXE;
import static com.sofodev.armorplus.utils.ToolTipUtils.*;

public class APPickaxeItem extends Item implements Tool {

    private final IAPTool mat;

    public APPickaxeItem(IAPTool mat) {
        super(com.sofodev.armorplus.registry.RegistryContext.itemProperties().pickaxe(mat.get(), (float) mat.get().attackDamageBonus() + PICKAXE.getDmg(), PICKAXE.getAttackSpeed()));
        this.mat = mat;
    }

    @Override
    public Component getName(ItemStack stack) {
        return super.getName(stack).copy().withStyle(mat.getColor());
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

    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity player) {
        if (!player.level().isClientSide() && mat.config().enableWeaponEffects().get()) {
            mat.onBlockMined(stack, world, state, pos, player);
        }
        return super.mineBlock(stack, world, state, pos, player);
    }

    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level().isClientSide() && mat.config().enableWeaponEffects().get()) {
            mat.getBuffInstances().get().forEach(instance -> instance.hitEntity(stack, target, attacker));
        }
        super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public IAPTool getMat() {
        return this.mat;
    }
}