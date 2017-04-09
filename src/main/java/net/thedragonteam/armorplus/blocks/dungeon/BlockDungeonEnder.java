package net.thedragonteam.armorplus.blocks.dungeon;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.blocks.base.BlockBase;
import net.thedragonteam.armorplus.blocks.base.ToolType;
import net.thedragonteam.armorplus.registry.ModItems;
import org.jetbrains.annotations.NotNull;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * ArmorPlus - Kotlin created by sokratis12GR
 * - TheDragonTeam
 */
public class BlockDungeonEnder extends BlockBase {

    public BlockDungeonEnder(EnumEnderBlocks enderBlocks) {
        super(Material.ROCK, enderBlocks.getName(), 10000, 100, ToolType.PICKAXE, 4, enderBlocks.getLightLevel(), true);
    }

    @NotNull
    @SuppressWarnings("deprecation")
    @Override
    public MapColor getMapColor(IBlockState state) {
        return MapColor.PURPLE;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}
