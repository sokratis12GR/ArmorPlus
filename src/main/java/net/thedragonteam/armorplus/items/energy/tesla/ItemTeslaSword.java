/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.items.energy.tesla;

import com.google.common.collect.Multimap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.base.energy.tesla.BaseTeslaSword;
import net.thedragonteam.armorplus.util.ARPTeslaUtils;

import static net.thedragonteam.armorplus.ARPConfig.*;

public class ItemTeslaSword extends BaseTeslaSword {

    private float attackDamage;
    private ItemStack stack;

    public ItemTeslaSword() {
        super(ToolMaterial.DIAMOND, "tesla_sword", maxCapacitySword, inputSword, outputSword);
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return ArmorPlus.tabArmorplusTesla;
    }

    @Optional.Method(modid = "tesla")
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        ARPTeslaUtils.usePower(stack, costSword);

        return true;
    }

    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double) this.attackDamage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
        }

        return multimap;
    }

    @Optional.Method(modid = "tesla")
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        ARPTeslaUtils.usePower(stack, costSword);
        return true;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state) {
        return Items.DIAMOND_SWORD.canHarvestBlock(state);
    }

    @Optional.Method(modid = "tesla")
    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        if (ARPTeslaUtils.getStoredPower(stack) < costSword) {
            return 0.5F;
        }
        if (Items.WOODEN_SWORD.getStrVsBlock(stack, state) > 1.0F) {
            return 5.5F;
        } else {
            return super.getStrVsBlock(stack, state);
        }
    }
}