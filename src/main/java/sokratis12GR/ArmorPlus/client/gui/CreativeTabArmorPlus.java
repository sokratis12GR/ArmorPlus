package sokratis12GR.ArmorPlus.client.gui;

//Creates your creative tab.

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import sokratis12GR.ArmorPlus.ArmorPlus;
import sokratis12GR.ArmorPlus.armors.origin.LavaArmor;

public class CreativeTabArmorPlus extends CreativeTabs {

  public CreativeTabArmorPlus(String tabLabel) {
      super(tabLabel);
      setBackgroundImageName(ArmorPlus.MODID + ".png"); // Automagically has tab_ applied to it. Make sure you change the texture name.
  }

  public boolean hasSearchBar() {
      return true;
  }

  //The tab icon is what you return here.
  @Override
  public ItemStack getIconItemStack() {
      return new ItemStack(LavaArmor.chestplate);
  }

  @Override
  public Item getTabIconItem() {
      return new Item();
  }
}