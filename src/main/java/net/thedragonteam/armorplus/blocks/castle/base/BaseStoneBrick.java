/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.blocks.castle.base;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.base.BaseBlock;
import net.thedragonteam.armorplus.blocks.castle.EnumStoneBrick;

public class BaseStoneBrick extends BaseBlock {

    public MapColor color;

    public BaseStoneBrick(EnumStoneBrick enumtypeIn) {
        super(Material.ROCK, enumtypeIn.getName() + "_stone_brick", 10.0F, 5.0F, "pickaxe", 0);
        this.color = enumtypeIn.getMapColor();
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), getRegistryName());
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state) {
        return color;
    }
}
