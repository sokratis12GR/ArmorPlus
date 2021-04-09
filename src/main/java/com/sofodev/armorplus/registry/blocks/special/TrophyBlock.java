package com.sofodev.armorplus.registry.blocks.special;

import com.sofodev.armorplus.registry.blocks.APBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;

public class TrophyBlock extends APBlock {

    public TrophyBlock() {
        super(Properties.copy(Blocks.SPAWNER));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TrophyTile();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        CompoundNBT tag = stack.getTag();
        TileEntity tile = world.getBlockEntity(pos);
        if (tag != null && tile instanceof TrophyTile) {
            TrophyTile trophy = (TrophyTile) tile;
            if (tag.contains("DisplayEntity", 10)) {
                trophy.setNextEntityData(new WeightedSpawnerEntity(1, tag.getCompound("DisplayEntity")));
            }
            if (tag.contains("EntityScale", 99)) {
                trophy.setEntityScale(tag.getFloat("EntityScale"));
            }
            trophy.save(tag);
            tile.setChanged();
            world.setBlockEntity(pos, tile);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable IBlockReader level, List<ITextComponent> tooltip, ITooltipFlag flag) {
        CompoundNBT tag = stack.getTagElement("BlockEntityTag");
        if (tag != null && tag.contains("DisplayEntity", 10) && tag.getCompound("DisplayEntity").contains("id", 8)) {
            ResourceLocation rl = new ResourceLocation(tag.getCompound("DisplayEntity").getString("id"));
            if (ForgeRegistries.ENTITIES.getValue(rl) != null) {
                Entity entity = ForgeRegistries.ENTITIES.getValue(rl).create((World) level);
                if (entity != null) {
                    tooltip.add(translate("tooltip.armorplus.trophy.dropped_by", entity.getName()));
                }
            }
        }
        super.appendHoverText(stack, level, tooltip, flag);
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        ItemStack itemstack = super.getPickBlock(state, target, world, pos, player);
        TrophyTile tileTrophy = (TrophyTile) world.getBlockEntity(pos);
        CompoundNBT tag = tileTrophy.save(new CompoundNBT());
        tileTrophy.setChanged();
        if (!tag.isEmpty()) {
            itemstack.addTagElement("BlockEntityTag", tag);
        }

        return super.getPickBlock(state, target, world, pos, player);
    }

    @Override
    public BlockRenderType getRenderShape(BlockState p_149645_1_) {
        return BlockRenderType.MODEL;
    }

}
