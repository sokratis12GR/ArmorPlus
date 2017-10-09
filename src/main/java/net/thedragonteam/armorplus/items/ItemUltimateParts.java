/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.iface.IModdedItem;

import javax.annotation.Nonnull;
import java.util.stream.IntStream;

import static net.thedragonteam.armorplus.APConfig.theUltimateArmorItemNameColor;
import static net.thedragonteam.armorplus.util.Utils.setName;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class ItemUltimateParts extends Item implements IModdedItem {

    private String[] ultimatePartsNames = new String[]{
            "_helmet_right", "_helmet_middle", "_helmet_left",
            "_chestplate_right", "_chestplate_middle", "_chestplate_left",
            "_leggings_right", "_leggings_middle", "_leggings_left",
            "_boots_right", "_boots_middle", "_boots_left"};

    public ItemUltimateParts() {
        this.setHasSubtypes(true);
        this.setRegistryName("the_ultimate_part");
        this.setUnlocalizedName(setName("the_ultimate_part"));
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return getRarity("ULTIMATE", theUltimateArmorItemNameColor, "Ultimate");
    }

    @Override
    @Nonnull
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedNames(stack, ultimatePartsNames);
    }

    private String getUnlocalizedNames(ItemStack stack, String... names) {
        for (int i = 0; i < names.length; i++) {
            if (stack.getItemDamage() == i) {
                return super.getUnlocalizedName(stack) + names[i];
            }
        }
        return super.getUnlocalizedName();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (isInCreativeTab(tab)) {
            IntStream.range(0, ultimatePartsNames.length).mapToObj(i ->
                    getItemStack(this, i)
            ).forEachOrdered(subItems::add);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        IntStream.range(0, ultimatePartsNames.length).forEachOrdered(i ->
                this.initModel(ultimatePartsNames[i], "ultimate", i)
        );
    }
}
