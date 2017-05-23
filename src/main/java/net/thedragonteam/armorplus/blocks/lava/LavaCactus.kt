/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.lava

import net.minecraft.block.BlockCactus

/**
 * net.thedragonteam.armorplus.blocks
 * ArmorPlus created by sokratis12GR on 8/15/2016.
 * - TheDragonTeam
 */
class LavaCactus : net.minecraft.block.BlockCactus(), net.thedragonteam.armorplus.iface.IModelHelper {
    init {
        this.defaultState = this.blockState.baseState.withProperty(net.minecraft.block.BlockCactus.AGE, 0)
        this.tickRandomly = true
        this.setHardness(0.4f)
        this.unlocalizedName = net.thedragonteam.armorplus.util.Utils.setName("lava_cactus")
        this.setRegistryName("lava_cactus")
        this.setCreativeTab(net.thedragonteam.armorplus.ArmorPlus.tabArmorplusBlocks)
        net.minecraftforge.fml.common.registry.GameRegistry.register(this)
        net.minecraftforge.fml.common.registry.GameRegistry.register(net.minecraft.item.ItemBlock(this), registryName)
    }

    override fun initModel() {
        this.initModel(this, registryName, 0)
    }

    override fun updateTick(worldIn: net.minecraft.world.World, pos: net.minecraft.util.math.BlockPos, state: net.minecraft.block.state.IBlockState, rand: java.util.Random?) {
        val blockpos = pos.up()

        if (worldIn.isAirBlock(blockpos)) {
            var i: Int = 1

            while (worldIn.getBlockState(pos.down(i)).block === this) {
                ++i
            }

            if (i < 3) {
                val j = state.getValue(net.minecraft.block.BlockCactus.AGE)

                when (j) {
                    15 -> {
                        worldIn.setBlockState(blockpos, this.defaultState)
                        val iblockstate = state.withProperty(net.minecraft.block.BlockCactus.AGE, 0)
                        worldIn.setBlockState(pos, iblockstate, 4)
                        iblockstate.neighborChanged(worldIn, blockpos, this, pos)
                    }
                    else -> worldIn.setBlockState(pos, state.withProperty(net.minecraft.block.BlockCactus.AGE, j + 1), 4)
                }
            }
        }
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getCollisionBoundingBox(blockState: net.minecraft.block.state.IBlockState?, blockAccess: net.minecraft.world.IBlockAccess?, pos: net.minecraft.util.math.BlockPos?): net.minecraft.util.math.AxisAlignedBB {
        return net.minecraft.block.BlockCactus.CACTUS_AABB
    }

    @Suppress("OverridingDeprecatedMember")
    @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
    override fun getSelectedBoundingBox(state: net.minecraft.block.state.IBlockState?, worldIn: net.minecraft.world.World?, pos: net.minecraft.util.math.BlockPos): net.minecraft.util.math.AxisAlignedBB {
        return net.minecraft.block.BlockCactus.CACTUS_COLLISION_AABB.offset(pos)
    }

    @Suppress("OverridingDeprecatedMember")
    override fun isFullCube(state: net.minecraft.block.state.IBlockState?): Boolean {
        return false
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    @Suppress("OverridingDeprecatedMember")
    override fun isOpaqueCube(state: net.minecraft.block.state.IBlockState?): Boolean {
        return false
    }

    @Suppress("OverridingDeprecatedMember")
    override fun canPlaceBlockAt(worldIn: net.minecraft.world.World, pos: net.minecraft.util.math.BlockPos): Boolean {
        return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos)
    }

    @Suppress("OverridingDeprecatedMember")
    override fun neighborChanged(state: net.minecraft.block.state.IBlockState?, worldIn: net.minecraft.world.World, pos: net.minecraft.util.math.BlockPos, blockIn: net.minecraft.block.Block?, blockPos: net.minecraft.util.math.BlockPos?) {
        if (!this.canBlockStay(worldIn, pos)) worldIn.destroyBlock(pos, true)
    }

    override fun canBlockStay(worldIn: net.minecraft.world.World, pos: net.minecraft.util.math.BlockPos): Boolean {
        net.minecraft.util.EnumFacing.Plane.VERTICAL
                .asSequence()
                .map { worldIn.getBlockState(pos.offset(it)).material }
                .filter { it.isSolid || it === net.minecraft.block.material.Material.LAVA }
                .forEach { return true }

        val state = worldIn.getBlockState(pos.down())
        return state.block.canSustainPlant(state, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this) && !worldIn.getBlockState(pos.up()).material.isLiquid
    }

    /**
     * Called When an Entity Collided with the Block
     */
    override fun onEntityCollidedWithBlock(worldIn: net.minecraft.world.World?, pos: net.minecraft.util.math.BlockPos?, state: net.minecraft.block.state.IBlockState?, entityIn: net.minecraft.entity.Entity) {
        entityIn.attackEntityFrom(net.minecraft.util.DamageSource.CACTUS, 1.0f)
        entityIn.setFire(2)
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Suppress("OverridingDeprecatedMember")
    override fun getStateFromMeta(meta: Int): net.minecraft.block.state.IBlockState {
        return this.defaultState.withProperty(net.minecraft.block.BlockCactus.AGE, meta)
    }

    @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
    override fun getBlockLayer(): net.minecraft.util.BlockRenderLayer {
        return net.minecraft.util.BlockRenderLayer.CUTOUT
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    override fun getMetaFromState(state: net.minecraft.block.state.IBlockState): Int {
        return state.getValue(net.minecraft.block.BlockCactus.AGE)
    }

    override fun getPlantType(world: net.minecraft.world.IBlockAccess?, pos: net.minecraft.util.math.BlockPos?): net.minecraftforge.common.EnumPlantType {
        return net.minecraftforge.common.EnumPlantType.Nether
    }

    override fun getPlant(world: net.minecraft.world.IBlockAccess?, pos: net.minecraft.util.math.BlockPos?): net.minecraft.block.state.IBlockState {
        return defaultState
    }

    override fun createBlockState(): net.minecraft.block.state.BlockStateContainer {
        return net.minecraft.block.state.BlockStateContainer(this, BlockCactus.AGE)
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    @Suppress("OverridingDeprecatedMember")
    override fun getMapColor(state: net.minecraft.block.state.IBlockState?): net.minecraft.block.material.MapColor {
        return net.minecraft.block.material.MapColor.NETHERRACK
    }
}
