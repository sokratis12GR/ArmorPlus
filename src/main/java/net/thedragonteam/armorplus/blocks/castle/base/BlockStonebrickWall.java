/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.castle.base;

import net.minecraft.block.BlockWall;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.iface.IModelHelper;

import javax.annotation.Nonnull;

import static net.thedragonteam.armorplus.util.Utils.INSTANCE;

public class BlockStonebrickWall extends BlockWall implements IModelHelper {

    private BlockStoneBrick stoneBrick;

    public BlockStonebrickWall(BlockStoneBrick modelBlock) {
        super(modelBlock);
        this.stoneBrick = modelBlock;
        this.setRegistryName(modelBlock.getName() + "_wall");
        this.setUnlocalizedName(INSTANCE.setName(modelBlock.getName() + "_wall"));
        this.setResistance(10F);
        this.setHardness(5F);
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks);
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), getRegistryName());
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public MapColor getMapColor(IBlockState state) {
        return this.stoneBrick.color;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(@Nonnull Item itemIn, CreativeTabs tab, @Nonnull NonNullList<ItemStack> list) {
        list.add(new ItemStack(itemIn, 1));
    }

    @Override
    public void initModel() {
        this.initModel(this, getRegistryName(), 0);
    }
}
