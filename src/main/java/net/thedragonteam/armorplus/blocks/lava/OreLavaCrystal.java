/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.lava;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.base.BlockBase;
import net.thedragonteam.armorplus.blocks.base.ToolType;
import net.thedragonteam.armorplus.iface.IModdedBlock;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.stream.IntStream;

import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class OreLavaCrystal extends BlockBase implements IModdedBlock {

    public OreLavaCrystal() {
        super(Material.ROCK, "ore_lava_crystal", 2000.0F, 25.0F, ToolType.PICKAXE, 3, 0.8F);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        Item item = lavaCrystal;
        Random rand;
        rand = world instanceof World ? ((World) world).rand : RANDOM;
        int count = quantityDropped(state, fortune, rand);
        IntStream.range(0, count).mapToObj(i -> getItemStack(item)).forEachOrdered(drops::add);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(0, "normal");
    }

    @Override
    public int quantityDropped(IBlockState blockstate, int fortune, @Nonnull Random random) {
        return 1 + random.nextInt(1 + fortune);
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    @Override
    @SuppressWarnings("deprecation")
    public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos) {
        return MapColor.RED;
    }
}
