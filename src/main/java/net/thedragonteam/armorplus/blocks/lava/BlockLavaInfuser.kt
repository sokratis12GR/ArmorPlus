/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.lava

import net.minecraft.block.BlockContainer
import net.minecraft.block.BlockHorizontal
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.SoundEvents
import net.minecraft.inventory.Container
import net.minecraft.inventory.InventoryHelper
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.blocks.base.ToolType
import net.thedragonteam.armorplus.client.gui.GuiHandler
import net.thedragonteam.armorplus.iface.IModdedBlock
import net.thedragonteam.armorplus.registry.ModBlocks.lavaInfuser
import net.thedragonteam.armorplus.registry.ModBlocks.lavaInfuserInfusing
import net.thedragonteam.armorplus.tileentity.TileEntityLavaInfuser
import net.thedragonteam.armorplus.util.Utils.setName
import net.thedragonteam.armorplus.util.Utils.setRL
import net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack
import java.util.*

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
class BlockLavaInfuser(name: String, private val isInfusing: Boolean) : BlockContainer(Material.ROCK), IModdedBlock {

    init {
        this.defaultState = this.blockState.baseState.withProperty(FACING, EnumFacing.NORTH)
        this.registryName = setRL(name)
        this.unlocalizedName = setName(name)
        this.setResistance(10000.0F)
        this.setHardness(2.5F)
        this.setHarvestLevel(ToolType.PICKAXE.tool, 1)
    }

    @SideOnly(Side.CLIENT)
    override fun initModel() {
        this.initModel(0)
    }

    @Suppress("OverridingDeprecatedMember")
    override fun isOpaqueCube(state: IBlockState?): Boolean {
        return false
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    override fun getItemDropped(state: IBlockState?, rand: Random?, fortune: Int): Item {
        return Item.getItemFromBlock(lavaInfuser)
    }

    /**
     * Called after the block is set in the Chunk data, but before the Tile Entity is set
     */
    override fun onBlockAdded(worldIn: World?, pos: BlockPos?, state: IBlockState?) {
        if (worldIn != null && state != null && pos != null) {
            this.setDefaultFacing(worldIn, pos, state)
        }
    }

    private fun setDefaultFacing(worldIn: World, pos: BlockPos, state: IBlockState) {
        if (!worldIn.isRemote) {
            val iblockstate = worldIn.getBlockState(pos.north())
            val iblockstate1 = worldIn.getBlockState(pos.south())
            val iblockstate2 = worldIn.getBlockState(pos.west())
            val iblockstate3 = worldIn.getBlockState(pos.east())
            var enumfacing = state.getValue(FACING)

            when {
                enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock && !iblockstate1.isFullBlock -> enumfacing = EnumFacing.SOUTH
                enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock && !iblockstate.isFullBlock -> enumfacing = EnumFacing.NORTH
                enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock && !iblockstate3.isFullBlock -> enumfacing = EnumFacing.EAST
                enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock && !iblockstate2.isFullBlock -> enumfacing = EnumFacing.WEST
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2)
        }
    }

    @SideOnly(Side.CLIENT)
    override fun randomDisplayTick(stateIn: IBlockState?, worldIn: World?, pos: BlockPos?, rand: Random?) {
        if (this.isInfusing) {
            val enumfacing = stateIn!!.getValue(FACING)
            val d0 = pos!!.x.toDouble() + 0.5
            val d1 = pos.y.toDouble() + rand!!.nextDouble() * 6.0 / 16.0
            val d2 = pos.z.toDouble() + 0.5
            val d4 = rand.nextDouble() * 0.6 - 0.3

            if (rand.nextDouble() < 0.1) {
                worldIn!!.playSound(pos.x.toDouble() + 0.5, pos.y.toDouble(), pos.z.toDouble() + 0.5, SoundEvents.ENTITY_LIGHTNING_THUNDER, SoundCategory.BLOCKS, 1.0f, 1.0f, false)
            }

            when (enumfacing) {
                EnumFacing.WEST -> {
                    worldIn!!.spawnParticle(EnumParticleTypes.LAVA, d0, d1 + 1, d2 + d4, -0.5, 0.5, 0.0)
                    worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1 + 1, d2 + d4, -0.5, 0.5, 0.0)
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1 + 1, d2 + d4, -0.5, 0.5, 0.0)
                }
                EnumFacing.EAST -> {
                    worldIn!!.spawnParticle(EnumParticleTypes.LAVA, d0, d1 + 1, d2 + d4, 0.5, 0.5, 0.0)
                    worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1 + 1, d2 + d4, 0.5, 0.5, 0.0)
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1 + 1, d2 + d4, 0.5, 0.5, 0.0)
                }
                EnumFacing.NORTH -> {
                    worldIn!!.spawnParticle(EnumParticleTypes.LAVA, d0, d1 + 1, d2 + d4, 0.0, 0.5, -0.5)
                    worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1 + 1, d2 + d4, 0.0, 0.5, -0.5)
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1 + 1, d2 + d4, 0.0, 0.5, -0.5)
                }
                EnumFacing.SOUTH -> {
                    worldIn!!.spawnParticle(EnumParticleTypes.LAVA, d0, d1 + 1, d2 + d4, 0.0, 0.5, 0.5)
                    worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1 + 1, d2 + d4, 0.0, 0.5, 0.5)
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1 + 1, d2 + d4, 0.0, 0.5, 0.5)
                }
                else -> {
                }
            }
        }
    }

    /**
     * Called when the block is right clicked by a player.
     */
    override fun onBlockActivated(world: World?, pos: BlockPos?, state: IBlockState?, playerIn: EntityPlayer?, hand: EnumHand?, facing: EnumFacing?, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        if (!world!!.isRemote)
            playerIn!!.openGui(ArmorPlus.instance, GuiHandler.GUI_LAVA_INFUSER, world, pos!!.x, pos.y, pos.z)
        return true
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    override fun createNewTileEntity(worldIn: World, meta: Int): TileEntity? {
        return TileEntityLavaInfuser()
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    @Suppress("OverridingDeprecatedMember")
    override fun getStateForPlacement(worldIn: World?, pos: BlockPos?, facing: EnumFacing?, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase?): IBlockState {
        return this.defaultState.withProperty(FACING, placer!!.horizontalFacing.opposite)
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    override fun onBlockPlacedBy(worldIn: World?, pos: BlockPos?, state: IBlockState?, placer: EntityLivingBase?, stack: ItemStack?) {
        worldIn!!.setBlockState(pos!!, state!!.withProperty(FACING, placer!!.horizontalFacing.opposite), 2)

        if (stack!!.hasDisplayName()) {
            val tileentity = worldIn.getTileEntity(pos)

            if (tileentity is TileEntityLavaInfuser) {
                tileentity.setCustomInventoryName(stack.displayName)
            }
        }
    }

    /**
     * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
     */
    override fun breakBlock(worldIn: World, pos: BlockPos, state: IBlockState) {
        if (!keepInventory) {
            val tileentity = worldIn.getTileEntity(pos)

            if (tileentity is TileEntityLavaInfuser) {
                InventoryHelper.dropInventoryItems(worldIn, pos, (tileentity as TileEntityLavaInfuser?)!!)
                worldIn.updateComparatorOutputLevel(pos, this)
            }
        }

        super.breakBlock(worldIn, pos, state)
    }

    @Suppress("OverridingDeprecatedMember")
    override fun hasComparatorInputOverride(state: IBlockState?): Boolean {
        return true
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getComparatorInputOverride(blockState: IBlockState?, worldIn: World?, pos: BlockPos?): Int {
        return Container.calcRedstone(worldIn!!.getTileEntity(pos!!))
    }

    override fun getPickBlock(state: IBlockState?, target: RayTraceResult?, world: World?, pos: BlockPos?, player: EntityPlayer?): ItemStack {
        return getItemStack(lavaInfuser)
    }

    override fun getRenderType(state: IBlockState?): EnumBlockRenderType {
        return EnumBlockRenderType.MODEL
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Suppress("OverridingDeprecatedMember")
    override fun getStateFromMeta(meta: Int): IBlockState {
        var enumfacing = EnumFacing.getFront(meta)

        when (enumfacing.axis) {
            EnumFacing.Axis.Y -> enumfacing = EnumFacing.NORTH
            else -> {
            }
        }

        return this.defaultState.withProperty(FACING, enumfacing)
    }

    @Suppress("OverridingDeprecatedMember")
    override fun isFullBlock(state: IBlockState?): Boolean {
        return false
    }

    @Suppress("OverridingDeprecatedMember")
    override fun isBlockNormalCube(state: IBlockState?): Boolean {
        return false
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    override fun getMetaFromState(state: IBlockState): Int {
        return state.getValue(FACING).index
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    @Suppress("OverridingDeprecatedMember")
    override fun withRotation(state: IBlockState, rot: Rotation?): IBlockState {
        return state.withProperty(FACING, rot!!.rotate(state.getValue(FACING)))
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    @Suppress("OverridingDeprecatedMember")
    override fun withMirror(state: IBlockState, mirrorIn: Mirror?): IBlockState {
        return state.withRotation(mirrorIn!!.toRotation(state.getValue(FACING)))
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, FACING)
    }

    companion object {

        val FACING = BlockHorizontal.FACING!!
        private var keepInventory: Boolean = false

        fun setState(active: Boolean, worldIn: World, pos: BlockPos) {
            val iblockstate = worldIn.getBlockState(pos)
            val tileentity = worldIn.getTileEntity(pos)
            keepInventory = true

            when {
                active -> {
                    worldIn.setBlockState(pos, lavaInfuserInfusing.defaultState.withProperty(FACING, iblockstate.getValue(FACING)), 3)
                    worldIn.setBlockState(pos, lavaInfuserInfusing.defaultState.withProperty(FACING, iblockstate.getValue(FACING)), 3)
                }
                else -> {
                    worldIn.setBlockState(pos, lavaInfuser.defaultState.withProperty(FACING, iblockstate.getValue(FACING)), 3)
                    worldIn.setBlockState(pos, lavaInfuser.defaultState.withProperty(FACING, iblockstate.getValue(FACING)), 3)
                }
            }

            keepInventory = false

            if (tileentity != null) {
                tileentity.validate()
                worldIn.setTileEntity(pos, tileentity)
            }
        }
    }
}
