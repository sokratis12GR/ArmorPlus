package com.sofodev.armorplus.registry.items.tools;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.tools.properties.tool.IAPTool;
import com.sofodev.armorplus.registry.items.tools.properties.tool.Tool;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sofodev.armorplus.config.ArmorPlusConfig.autoSmeltingInput;
import static com.sofodev.armorplus.config.ArmorPlusConfig.autoSmeltingOutput;
import static com.sofodev.armorplus.registry.items.tools.properties.tool.APToolType.PICKAXE;
import static com.sofodev.armorplus.utils.ToolTipUtils.addBuffInformation;

public class APPickaxeItem extends PickaxeItem implements Tool {

    private final IAPTool mat;

    public APPickaxeItem(IAPTool mat) {
        super(mat.get(), (int) (mat.get().getAttackDamageBonus() + PICKAXE.getDmg()), PICKAXE.getAttackSpeed(), new Item.Properties());
        this.mat = mat;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return mat.getRarity();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        addBuffInformation(mat, tooltip, "on_hit", false, mat.config().enableWeaponEffects.get());
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity player) {
        if (!player.level.isClientSide && mat.config().enableWeaponEffects.get()) {
            mat.onBlockMined(stack, world, state, pos, player);
        }
        return super.mineBlock(stack, world, state, pos, player);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level.isClientSide && mat.config().enableWeaponEffects.get()) {
            mat.getBuffInstances().get().forEach(instance -> instance.hitEntity(stack, target, attacker));
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public IAPTool getMat() {
        return this.mat;
    }
}