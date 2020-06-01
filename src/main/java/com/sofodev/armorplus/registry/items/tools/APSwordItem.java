package com.sofodev.armorplus.registry.items.tools;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.blocks.special.SwordDisplayBlock;
import com.sofodev.armorplus.registry.blocks.special.SwordDisplayTile;
import com.sofodev.armorplus.registry.items.tools.properties.IAPTool;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;

import static com.sofodev.armorplus.registry.items.tools.properties.APToolType.SWORD;
import static com.sofodev.armorplus.utils.Utils.setRL;

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
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.world.isRemote) {
            Arrays.stream(mat.getBuffInstances()).forEach(instance -> instance.hitEntity(stack, target, attacker));
        }
        return super.hitEntity(stack, target, attacker);
    }

}
