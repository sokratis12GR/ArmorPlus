package com.sofodev.armorplus.registry.blocks.special;

import com.sofodev.armorplus.registry.blocks.APBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;

public class TrophyBlock extends APBlock implements EntityBlock {

    public TrophyBlock() {
        super(Properties.copy(Blocks.SPAWNER));
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        CompoundTag tag = stack.getTag();
        BlockEntity tile = world.getBlockEntity(pos);
        if (tag != null && tile instanceof TrophyTile) {
            TrophyTile trophy = (TrophyTile) tile;
            if (tag.contains("DisplayEntity", 10)) {
                trophy.setNextEntityData(new SpawnData(tag.getCompound("DisplayEntity"), Optional.empty()));
            }
            if (tag.contains("EntityScale", 99)) {
                trophy.setEntityScale(tag.getFloat("EntityScale"));
            }
            trophy.saveAdditional(tag);
            tile.setChanged();
            world.setBlockEntity(tile);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag) {
        CompoundTag tag = stack.getTagElement("BlockEntityTag");
        if (tag != null && tag.contains("DisplayEntity", 10) && tag.getCompound("DisplayEntity").contains("id", 8)) {
            ResourceLocation rl = new ResourceLocation(tag.getCompound("DisplayEntity").getString("id"));
            if (ForgeRegistries.ENTITIES.getValue(rl) != null) {
                Entity entity = ForgeRegistries.ENTITIES.getValue(rl).create((Level) level);
                if (entity != null) {
                    tooltip.add(translate("tooltip.armorplus.trophy.dropped_by", entity.getName()));
                }
            }
        }
        super.appendHoverText(stack, level, tooltip, flag);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
        return super.getCloneItemStack(state, target, world, pos, player);
    }

//    @Override
//    public ItemStack getPickBlock(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
//        ItemStack itemstack = super.getPickBlock(state, target, world, pos, player);
//        TrophyTile tileTrophy = (TrophyTile) world.getBlockEntity(pos);
//        CompoundTag tag = tileTrophy.save(new CompoundTag());
//        tileTrophy.setChanged();
//        if (!tag.isEmpty()) {
//            itemstack.addTagElement("BlockEntityTag", tag);
//        }
//
//        return super.asBlock().getCloneItemStack(state, target, world, pos, player);
//    }

    @Override
    public RenderShape getRenderShape(BlockState p_60550_) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TrophyTile(pos, state);
    }
}
