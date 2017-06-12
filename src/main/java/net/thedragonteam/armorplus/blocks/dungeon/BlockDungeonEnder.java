package net.thedragonteam.armorplus.blocks.dungeon;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.blocks.base.BlockBase;
import net.thedragonteam.armorplus.blocks.base.ToolType;
import net.thedragonteam.armorplus.registry.ModSounds;

/**
 * ArmorPlus - Kotlin created by sokratis12GR
 * - TheDragonTeam
 */
public class BlockDungeonEnder extends BlockBase {

    private EnumEnderBlocks enderBlocks;

    public BlockDungeonEnder(EnumEnderBlocks enderBlocks) {
        super(Material.ROCK, enderBlocks.getName(), 10000, 100, ToolType.PICKAXE, 4, enderBlocks.getLightLevel(), true);
        this.enderBlocks = enderBlocks;
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess p_180659_2_, BlockPos p_180659_3_) {
        return MapColor.PURPLE;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (enderBlocks == EnumEnderBlocks.ENDER_STONE_TRAP) {
            worldIn.playSound(playerIn, pos, ModSounds.trap_triggered, SoundCategory.BLOCKS, 0.5F, 0.0F);
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}
