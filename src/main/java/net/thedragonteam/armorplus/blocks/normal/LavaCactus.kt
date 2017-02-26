/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.normal

import net.minecraft.block.Block
import net.minecraft.block.BlockCactus
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.item.ItemBlock
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.DamageSource
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.common.EnumPlantType
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.iface.IModelHelper
import net.thedragonteam.armorplus.util.Utils.setName
import java.util.*

/**
 * net.thedragonteam.armorplus.blocks
 * ArmorPlus created by sokratis12GR on 8/15/2016.
 * - TheDragonTeam
 */
class LavaCactus : BlockCactus(), IModelHelper {
    init {
        this.defaultState = this.blockState.baseState.withProperty(BlockCactus.AGE, 0)
        this.tickRandomly = true
        this.setHardness(0.4f)
        this.unlocalizedName = setName("lava_cactus")
        this.setRegistryName("lava_cactus")
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks)
        GameRegistry.register(this)
        GameRegistry.register(ItemBlock(this), registryName)
    }

    override fun initModel() {
        this.initModel(this, registryName, 0)
    }

    override fun updateTick(worldIn: World, pos: BlockPos, state: IBlockState, rand: Random?) {
        val blockpos = pos.up()

        if (worldIn.isAirBlock(blockpos)) {
            var i: Int = 1

            while (worldIn.getBlockState(pos.down(i)).block === this) {
                ++i
            }

            if (i < 3) {
                val j = state.getValue(BlockCactus.AGE)

                when (j) {
                    15 -> {
                        worldIn.setBlockState(blockpos, this.defaultState)
                        val iblockstate = state.withProperty(BlockCactus.AGE, 0)
                        worldIn.setBlockState(pos, iblockstate, 4)
                        iblockstate.neighborChanged(worldIn, blockpos, this, pos)
                    }
                    else -> worldIn.setBlockState(pos, state.withProperty(BlockCactus.AGE, j + 1), 4)
                }
            }
        }
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getCollisionBoundingBox(blockState: IBlockState?, blockAccess: IBlockAccess?, pos: BlockPos?): AxisAlignedBB {
        return BlockCactus.CACTUS_AABB
    }

    @Suppress("OverridingDeprecatedMember")
    @SideOnly(Side.CLIENT)
    override fun getSelectedBoundingBox(state: IBlockState?, worldIn: World?, pos: BlockPos): AxisAlignedBB {
        return BlockCactus.CACTUS_COLLISION_AABB.offset(pos)
    }

    @Suppress("OverridingDeprecatedMember")
    override fun isFullCube(state: IBlockState?): Boolean {
        return false
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    @Suppress("OverridingDeprecatedMember")
    override fun isOpaqueCube(state: IBlockState?): Boolean {
        return false
    }

    @Suppress("OverridingDeprecatedMember")
    override fun canPlaceBlockAt(worldIn: World, pos: BlockPos): Boolean {
        return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos)
    }

    @Suppress("OverridingDeprecatedMember")
    override fun neighborChanged(state: IBlockState?, worldIn: World, pos: BlockPos, blockIn: Block?, blockPos: BlockPos?) {
        if (!this.canBlockStay(worldIn, pos)) worldIn.destroyBlock(pos, true)
    }

    override fun canBlockStay(worldIn: World, pos: BlockPos): Boolean {
        EnumFacing.Plane.VERTICAL
                .asSequence()
                .map { worldIn.getBlockState(pos.offset(it)).material }
                .filter { it.isSolid || it === Material.LAVA }
                .forEach { return true }

        val state = worldIn.getBlockState(pos.down())
        return state.block.canSustainPlant(state, worldIn, pos.down(), EnumFacing.UP, this) && !worldIn.getBlockState(pos.up()).material.isLiquid
    }

    /**
     * Called When an Entity Collided with the Block
     */
    override fun onEntityCollidedWithBlock(worldIn: World?, pos: BlockPos?, state: IBlockState?, entityIn: Entity) {
        entityIn.attackEntityFrom(DamageSource.CACTUS, 1.0f)
        entityIn.setFire(2)
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Suppress("OverridingDeprecatedMember")
    override fun getStateFromMeta(meta: Int): IBlockState {
        return this.defaultState.withProperty(BlockCactus.AGE, meta)
    }

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    override fun getMetaFromState(state: IBlockState): Int {
        return state.getValue(BlockCactus.AGE)
    }

    override fun getPlantType(world: IBlockAccess?, pos: BlockPos?): EnumPlantType {
        return EnumPlantType.Nether
    }

    override fun getPlant(world: IBlockAccess?, pos: BlockPos?): IBlockState {
        return defaultState
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, BlockCactus.AGE)
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    @Suppress("OverridingDeprecatedMember")
    override fun getMapColor(state: IBlockState?): MapColor {
        return MapColor.NETHERRACK
    }
}
