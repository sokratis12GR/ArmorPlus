/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.materials;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.iface.IModdedItem;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.stream.IntStream;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ItemMaterial extends Item implements IModdedItem {

    private String[] materialNames = new String[]{"chainmail", "guardian_scale", "wither_bone", "ender_dragon_scale", "the_ultimate_material"};

    public ItemMaterial() {
        this.setRegistryName(Utils.setRL("material"));
        this.setTranslationKey(Utils.setName("material"));
        this.setHasSubtypes(true);
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    //0 = Chainmail
    //1 = Guardian Scale
    //2 = Wither Bone
    //3 = Ender Dragon Scale
    //4 = The Ultimate Material
    @Override
    @Nonnull
    public String getTranslationKey(ItemStack stack) {
        return getUnlocalizedNames(stack, materialNames);
    }

    private String getUnlocalizedNames(ItemStack stack, String... names) {
        for (int i = 0; i < names.length; i++) {
            if (stack.getItemDamage() == i) {
                return super.getTranslationKey(stack) + "_" + names[i];
            }
        }
        return super.getTranslationKey();
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        int i = stack.getMetadata();
        if (i == 0) {
            return this.getRarity("CHAINMAIL", TextFormatting.GRAY, "Chainmail");
        } else if (i == 1) {
            return this.getRarity("GUARDIAN_SCALE", TextFormatting.AQUA, "Guardian Scale");
        } else if (i == 2) {
            return this.getRarity("WITHER_BONE", TextFormatting.WHITE, "Wither Bone");
        } else if (i == 3) {
            return this.getRarity("ENDER_DRAGON_SCALE", TextFormatting.DARK_PURPLE, "Ender Dragon Scale");
        } else if (i == 4) {
            return this.getRarity("THE_ULTIMATE_MATERIAL", TextFormatting.GREEN, "The Ultimate Material");
        }
        return this.getRarity("DEFAULT", TextFormatting.WHITE, "Default");
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (isInCreativeTab(tab)) {
            IntStream.range(0, materialNames.length).mapToObj(i ->
                getItemStack(this, i)
            ).forEachOrdered(subItems::add);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        IntStream.range(0, materialNames.length).forEachOrdered(i ->
            this.initModel(Utils.setRL(materialNames[i]), "material", i)
        );
    }
}
