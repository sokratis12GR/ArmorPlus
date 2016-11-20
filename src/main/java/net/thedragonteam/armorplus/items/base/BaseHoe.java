/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.thedragonteam.armorplus.util.Utils.setName;

public class BaseHoe extends ItemHoe implements IItemHelper {

    public BaseHoe(ToolMaterial material, String name) {
        super(material);
        setUnlocalizedName(setName(name));
        setRegistryName(name);
        GameRegistry.register(this);
    }

    @Override
    public void getItemStack(ItemStack stack) {
    }

    @Override
    public void getItem(Item item) {
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(this);
    }

    @Override
    public Item getItem() {
        return this;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
