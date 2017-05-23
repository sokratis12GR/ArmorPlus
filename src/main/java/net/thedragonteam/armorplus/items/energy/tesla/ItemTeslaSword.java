/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.energy.tesla;

import com.google.common.collect.Multimap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.base.energy.tesla.BaseTeslaSword;
import net.thedragonteam.armorplus.util.APTeslaUtils;
import net.thedragonteam.thedragonlib.util.TextUtils;

import javax.annotation.Nonnull;
import java.util.List;

import static net.minecraft.util.text.TextFormatting.DARK_AQUA;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.util.APTeslaUtils.getMaxCapacity;
import static net.thedragonteam.armorplus.util.APTeslaUtils.getStoredPower;
import static net.thedragonteam.armorplus.util.ToolTipUtils.showInfo;

public class ItemTeslaSword extends BaseTeslaSword {

    private float attackDamage;
    private ItemStack stack;

    public ItemTeslaSword() {
        super(ToolMaterial.DIAMOND, "tesla_sword", maxEnergyCapacity[0], energyInput[0], energyOutput[0]);
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return ArmorPlus.tabArmorplusTesla;
    }

    @Method(modid = "tesla")
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        APTeslaUtils.usePower(stack, energyOutput[0]);
        return true;
    }

    @Override
    @Nonnull
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        switch (equipmentSlot) {
            case MAINHAND:
                multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double) this.attackDamage, 0));
                multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
                break;
        }

        return multimap;
    }

    @Method(modid = "tesla")
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        APTeslaUtils.usePower(stack, energyOutput[0]);
        return true;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state) {
        return Items.DIAMOND_SWORD.canHarvestBlock(state);
    }

    @Method(modid = "tesla")
    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        return getStoredPower(stack) < energyOutput[0] ? 0.5F : Items.WOODEN_SWORD.getStrVsBlock(stack, state) > 1.0F ? 5.5F : super.getStrVsBlock(stack, state);
    }

    @Method(modid = "tesla")
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        createTooltip(stack, tooltip);
    }

    @Method(modid = "tesla")
    @SideOnly(Side.CLIENT)
    private void createTooltip(ItemStack stack, List<String> tooltip) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add(TextUtils.INSTANCE.formattedText(DARK_AQUA, "tooltip.tesla.powerinfo", Long.toString(getStoredPower(stack)), Long.toString(getMaxCapacity(stack))));
            tooltip.add(TextUtils.INSTANCE.formattedText(DARK_AQUA, "tooltip.tesla.cost.hit", Long.toString(energyOutput[0])));
        } else {
            showInfo(tooltip, keyBindSneak, DARK_AQUA);
        }
    }
}