package com.sofodev.armorplus.registry.item.tool;

import com.sofodev.armorplus.registry.item.tool.properties.mace.IAPMace;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.registry.item.tool.properties.mace.DestructionShape.PLUS;
import static com.sofodev.armorplus.registry.item.tool.properties.mace.DestructionShape.SQUARE;
import static net.minecraft.tags.BlockTags.WITHER_IMMUNE;

public class APMaceItem extends Item {

    public final IAPMace mat;
    Random random = new Random();

    public APMaceItem(IAPMace mat, Item.Properties props) {
        super(props.sword(mat.get(), (float) mat.get().attackDamageBonus() + mat.getType().getDmg(), mat.getType().getAttackSpeed()));
        this.mat = mat;

    }

    @Override
    public Component getName(ItemStack stack) {
        return super.getName(stack).copy().withStyle(mat.getColor());
    }
//
//    @Override
//    public boolean shouldOverrideMultiplayerNbt() {
//        return super.shouldOverrideMultiplayerNbt();
//    }

    public boolean canAttackBlock(BlockState state, Level worldIn, BlockPos pos, Player player) {
        return !player.isCreative();
    }
//
//    @Override
//    public Rarity getRarity(ItemStack stack) {
//        return mat.getRarity();
//    }
//
//    @Override
//    public int getUseDuration(ItemStack stack) {
//        return (int) mat.getType().getChargeSpeed() * 2000;
//    }

//    @Override
//    public void releaseUsing(ItemStack stack, Level level, LivingEntity living, int timeLeft) {
//        if (living instanceof Player) {
//            Player player = (Player) living;
//            if (nbt != null && nbt.hasUUID("key")) {
//                int chargeTime = this.getUseDuration(stack) - timeLeft;
//                if (chargeTime >= mat.getType().getChargeSpeed()) {
//                    if (level instanceof ServerLevel serverLevel) {
//                        this.triggerAnim(player, GeoItem.getOrAssignId(player.getItemInHand(player.getUsedItemHand()), serverLevel), controllerName, "animation.mace.swing_attack");
//                        //check if the offhand is empty
//                        boolean isOffHandEmpty = player.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty();
//                        if (isOffHandEmpty) {
//                            BlockPos destructionPos = new BlockPos((int) player.position().x, (int) player.position().y, (int) player.position().z);
//                            Direction direction = player.getMotionDirection();
//                            boolean isNorthOrSouth = direction == NORTH || direction == SOUTH;
//                            boolean isWestOrEast = direction == EAST || direction == WEST;
//                            if (isNorthOrSouth) {
//                                this.executeDestruction(player, mat, level, stack, destructionPos, direction, true);
//                            } else if (isWestOrEast) {
//                                this.executeDestruction(player, mat, level, stack, destructionPos, direction, false);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (level instanceof ServerLevel) {
            ItemStack stack = player.getItemInHand(hand);
//            CompoundTag nbt = ;
//            if (nbt != null && nbt.hasUUID("key")) {
//                triggerAnim(player, GeoItem.getOrAssignId(player.getItemInHand(hand), serverLevel), controllerName, "animation.mace.hold_charge");
//            }
            if (stack.getDamageValue() >= stack.getMaxDamage() - 1) {
                return InteractionResult.FAIL;
            } else {
                player.startUsingItem(hand);
                return InteractionResult.CONSUME;
            }
        }
        return super.use(level, player, hand);
    }

//    @Override
//    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
//        Level level = entity.level();
//        if (level instanceof ServerLevel serverLevel) {
//            ItemStack itemStack = this.setTag(stack);
//            CompoundTag nbt = stack.getTag();
//            if (nbt != null && nbt.hasUUID("key")) {
//                triggerAnim(entity, GeoItem.getOrAssignId(entity.getItemInHand(entity.getUsedItemHand()), serverLevel), controllerName, "animation.mace.swing_attack");
//            }
//        }
//        return true;
//    }
//
//    public ItemStack setTag(ItemStack stack) {
//        CompoundTag tag = new CompoundTag();
//        UUID randomUUID = UUID.randomUUID();
//        if (tag == null) {
//            tag.putUUID("key", randomUUID);
//            (tag);
//        }
//        if (!tag.hasUUID("key")) {
//            tag.putUUID("key", randomUUID);
//        }
//        stack.capabi`(tag);
//        return stack;
//    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack p_41452_) {
        return ItemUseAnimation.NONE;
    }

    private void executeDestruction(Player player, IAPMace mat, Level world, ItemStack stack, BlockPos destructionPos, Direction direction, boolean flag) {
        this.destroyBlocksInLineDirectional(mat, world, destructionPos, direction, flag);
        int damage = mat.destructionRange();
        //Damages the item and executes the animation

        stack.hurtAndBreak(random.nextInt(damage) + (damage * damage), player, EquipmentSlot.MAINHAND);
        player.getCooldowns().addCooldown(stack, mat.cooldown() * 20);
        player.awardStat(Stats.ITEM_USED.get(this));
    }

    private void destroyBlocksInLineDirectional(IAPMace mat, Level world, BlockPos destructionPos, Direction direction, boolean isNorthOrSouth) {
        IntStream.range(0, mat.destructionRange()).forEach(offset -> {
            if (mat.hasAOEDestruction()) {
                BlockPos westOrNorth = isNorthOrSouth ? destructionPos.west() : destructionPos.north();
                BlockPos eastOrSouth = isNorthOrSouth ? destructionPos.east() : destructionPos.south();
                if (mat.getShape() == SQUARE) {
                    this.destroyBlockInLine(world, destructionPos, direction, offset);
                    this.destroyBlockInLine(world, westOrNorth, direction, offset);
                    this.destroyBlockInLine(world, eastOrSouth, direction, offset);
                } else if (mat.getShape() == PLUS) {
                    this.destroyBlockInLine(world, destructionPos, direction, offset);
                    this.destroyBlockSingleLine(world, westOrNorth, direction, offset);
                    this.destroyBlockSingleLine(world, eastOrSouth, direction, offset);
                }
            } else {
                this.destroyBlockSingleLine(world, destructionPos, direction, offset);
            }
        });
    }

    /**
     * Destroys blocks in a region of a line, but accounts double the "Y" pos in a line of sight depending on the offset.
     * Y = player.posY, Y+1, Y+2, basically to cover a 3x area in sight.
     *
     * @param world          the world object
     * @param destructionPos the original (starting) BlockPos.
     * @param direction      the direction the player is facing
     * @param offset         the offset value.
     */
    private void destroyBlockInLine(Level world, BlockPos destructionPos, Direction direction, int offset) {
        BlockPos pos = directionalOffset(destructionPos, direction, offset);
        this.destroyBlock(world, pos);
        this.destroyBlock(world, pos.above());
        this.destroyBlock(world, pos.above().above());
    }

    private void destroyBlockSingleLine(Level world, BlockPos destructionPos, Direction direction, int i) {
        this.destroyBlock(world, directionalOffset(destructionPos, direction, i).above());
    }

    /**
     * @param world the world object
     * @param pos   the BlockPos that we will use to destroy the block
     */
    private void destroyBlock(Level world, BlockPos pos) {
        if (!world.getBlockState(pos).is(WITHER_IMMUNE)) world.destroyBlock(pos, true);
    }

    /**
     * @param pos       The original destruction pos.
     * @param direction the direction the player is facing.
     * @param offset    the offset in blocks.
     * @return {@link BlockPos} the offset of the original destruction pos.
     */
    private BlockPos directionalOffset(BlockPos pos, Direction direction, int offset) {
        return pos.relative(direction, offset + 1);
    }

}
