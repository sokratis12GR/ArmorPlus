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
import net.thedragonteam.armorplus.util.Utils;

import javax.annotation.Nonnull;
import java.util.stream.IntStream;

import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.ultimate;
import static net.thedragonteam.armorplus.util.Utils.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class ItemUltimateParts extends Item implements IModdedItem {

    private String[] names = new String[]{
        "_helmet_right", "_helmet_middle", "_helmet_left",
        "_chestplate_right", "_chestplate_middle", "_chestplate_left",
        "_leggings_right", "_leggings_middle", "_leggings_left",
        "_boots_right", "_boots_middle", "_boots_left"};

    public ItemUltimateParts() {
        this.setHasSubtypes(true);
        this.setRegistryName(setRL("the_ultimate_part"));
        this.setTranslationKey(setName("the_ultimate_part"));
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return getRarity("ULTIMATE", ultimate.armor.itemNameColor, "Ultimate");
    }

    @Override
    @Nonnull
    public String getTranslationKey(ItemStack stack) {
        return Utils.getUnlocalizedNames(stack, super.getTranslationKey(stack), names);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (isInCreativeTab(tab)) {
            IntStream.range(0, names.length).mapToObj(
                meta -> getItemStack(this, meta)
            ).forEachOrdered(subItems::add);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        IntStream.range(0, names.length).forEachOrdered(
            meta -> this.initModel(names[meta], "ultimate", meta)
        );
    }
}
