/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.materials;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.iface.IModdedItem;

import javax.annotation.Nonnull;
import java.util.stream.IntStream;

import static net.thedragonteam.armorplus.util.Utils.setName;
import static net.thedragonteam.armorplus.util.Utils.setRL;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class ItemMaterial extends Item implements IModdedItem {

    private String[] materialNames = new String[]{"chainmail", "guardian_scale", "wither_bone", "ender_dragon_scale", "the_ultimate_material"};

    public ItemMaterial() {
        this.setRegistryName(setRL("material"));
        this.setUnlocalizedName(setName("material"));
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
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedNames(stack, materialNames);
    }

    private String getUnlocalizedNames(ItemStack stack, String... names) {
        for (int i = 0; i < names.length; i++) {
            if (stack.getItemDamage() == i) {
                return super.getUnlocalizedName(stack) + "_" + names[i];
            }
        }
        return super.getUnlocalizedName();
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        switch (stack.getMetadata()) {
            case 0:
                return this.getRarity("CHAINMAIL", TextFormatting.GRAY, "Chainmail");
            case 1:
                return this.getRarity("GUARDIAN_SCALE", TextFormatting.AQUA, "Guardian Scale");
            case 2:
                return this.getRarity("WITHER_BONE", TextFormatting.WHITE, "Wither Bone");
            case 3:
                return this.getRarity("ENDER_DRAGON_SCALE", TextFormatting.DARK_PURPLE, "Ender Dragon Scale");
            case 4:
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
            this.initModel(setRL(materialNames[i]), "material", i)
        );
    }
}
