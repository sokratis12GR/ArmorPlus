/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.consumables;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.iface.IModelHelper;

import javax.annotation.Nonnull;

import static net.thedragonteam.armorplus.util.Utils.setName;

/**
 * net.thedragonteam.armorplus.items.consumables
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
public class RedstoneApple extends ItemFood implements IModelHelper {

    public RedstoneApple() {
        super(4, 2.0f, false);
        this.setHasSubtypes(true);
        this.setRegistryName("redstone_apple");
        this.setUnlocalizedName(setName("redstone_apple"));
        GameRegistry.register(this);
        this.setAlwaysEdible();
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return stack.getMetadata() > 0;
    }

    /**
     * Return an item rarity from EnumRarity
     */
    @Override
    @Nonnull
    public EnumRarity getRarity(ItemStack stack) {
        return stack.getMetadata() == 0 ? EnumRarity.RARE : EnumRarity.EPIC;
    }

    protected void onFoodEaten(ItemStack stack, World worldIn, @Nonnull EntityPlayer player) {
        if (!worldIn.isRemote)
            player.addPotionEffect(stack.getMetadata() > 0 ? new PotionEffect(MobEffects.SPEED, 6000, 1) : new PotionEffect(MobEffects.SPEED, Integer.MAX_VALUE, 1));
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(@Nonnull Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        subItems.add(new ItemStack(itemIn));
        subItems.add(new ItemStack(itemIn, 1, 1));

    }

    public void initModel() {
        for (int i = 0; i < 2; i++) {
            this.initModel(this, getRegistryName(), i);
        }
    }
}
