/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.materials;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.iface.IModdedItem;
import net.thedragonteam.armorplus.util.ToolTipUtils;
import net.thedragonteam.armorplus.util.Utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.IntStream;

import static net.minecraft.util.text.TextFormatting.DARK_PURPLE;
import static net.minecraft.util.text.TextFormatting.ITALIC;
import static net.thedragonteam.armorplus.util.ToolTipUtils.isKeyDown;
import static net.thedragonteam.armorplus.util.Utils.setName;
import static net.thedragonteam.armorplus.util.Utils.setRL;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class ItemLavaCrystal extends Item implements IModdedItem {

    private String[] names = new String[]{"", "_infused"};

    private int[] burnTime = new int[]{20000, 22000};

    public ItemLavaCrystal() {
        this.setHasSubtypes(true);
        this.setRegistryName(setRL("lava_crystal"));
        this.setTranslationKey(setName("lava_crystal"));
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
        this.setMaxDamage(0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (isKeyDown()) {
            int i = stack.getMetadata();
            if (i == 0) {
                tooltip.add(ITALIC + "" + DARK_PURPLE + "Can be obtained by mining Ore Lava Crystal");
            } else if (i == 1) {
                tooltip.add(ITALIC + "" + DARK_PURPLE + "Can be created by infusing the Lava Crystal inside the Lava Infuser");
            }
        } else {
            ToolTipUtils.showInfo(tooltip, keyBindSneak, TextFormatting.GOLD);
        }
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return this.getRarity("LAVA_CRYSTAL", TextFormatting.GOLD, "Lava Crystalic");
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        if (itemStack.getItem() == this) {
            for (int i = 0; i < burnTime.length; i++) {
                if (itemStack.getItemDamage() == i) {
                    return burnTime[i];
                }
            }
        }
        return 0;
    }

    @Override
    @Nonnull
    public String getTranslationKey(ItemStack stack) {
        return Utils.getUnlocalizedNames(stack, super.getTranslationKey(stack), names);
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return super.getDurabilityForDisplay(stack);
    }

    @Override
    public boolean getShareTag() {
        return super.getShareTag();
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
            meta -> this.initModel(names[meta], "lava", meta)
        );
    }
}
