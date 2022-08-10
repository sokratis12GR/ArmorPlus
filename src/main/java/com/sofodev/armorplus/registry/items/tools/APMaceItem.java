package com.sofodev.armorplus.registry.items.tools;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.tools.properties.mace.IAPMace;
import com.sofodev.armorplus.registry.items.tools.render.APMaceRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.network.PacketDistributor;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.Animation;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.registry.items.tools.properties.mace.DestructionShape.PLUS;
import static com.sofodev.armorplus.registry.items.tools.properties.mace.DestructionShape.SQUARE;
import static net.minecraft.core.Direction.*;
import static net.minecraft.tags.BlockTags.WITHER_IMMUNE;
import static software.bernie.geckolib3.core.PlayState.CONTINUE;
import static software.bernie.geckolib3.core.PlayState.STOP;

public class APMaceItem extends SwordItem implements IAnimatable, ISyncable {

    public final IAPMace mat;
    public final String controllerName = "maceController";
    public AnimationFactory factory = new AnimationFactory(this);
    Random random = new Random();

    public APMaceItem(IAPMace mat, Item.Properties props) {
        super(mat.get(), (int) (mat.get().getAttackDamageBonus() + mat.getType().getDmg()), mat.getType()
                .getAttackSpeed(), props.tab(ArmorPlus.AP_WEAPON_GROUP));
        this.mat = mat;
        GeckoLibNetwork.registerSyncable(this);
    }

    @Override
    public String getSyncKey() {
        return mat.getName() + this.getClass().getName();
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IClientItemExtensions() {
            private final BlockEntityWithoutLevelRenderer renderer = new APMaceRenderer();

            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return this.renderer;
            }
        });
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return super.isValidRepairItem(toRepair, repair);
    }

    @Override
    public boolean shouldOverrideMultiplayerNbt() {
        return super.shouldOverrideMultiplayerNbt();
    }

    @Override
    public boolean canAttackBlock(BlockState state, Level worldIn, BlockPos pos, Player player) {
        return !player.isCreative();
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return mat.getRarity();
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return (int) mat.getType().getChargeSpeed() * 2000;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity living, int timeLeft) {
        if (living instanceof Player) {
            Player player = (Player) living;
            CompoundTag nbt = stack.getTag();
            if (nbt != null && nbt.hasUUID("key")) {
                AnimationController<?> controller = GeckoLibUtil.getControllerForStack(this.factory, stack, this.controllerName);
                Animation currentAnimation = controller.getCurrentAnimation();
                int chargeTime = this.getUseDuration(stack) - timeLeft;
                if (chargeTime >= mat.getType().getChargeSpeed()) {
                    if (currentAnimation != null) {
                        currentAnimation.loop = false;
                    }
                    if (!world.isClientSide) {
                        int id = GeckoLibUtil.guaranteeIDForStack(stack, (ServerLevel) world);
                        PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> {
                            return player;
                        });
                        GeckoLibNetwork.syncAnimation(target, this, id, 1);
                        //check if the offhand is empty
                        boolean isOffHandEmpty = player.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty();
                        if (isOffHandEmpty) {
                            BlockPos destructionPos = new BlockPos(player.position());
                            Direction direction = player.getMotionDirection();
                            boolean isNorthOrSouth = direction == NORTH || direction == SOUTH;
                            boolean isWestOrEast = direction == EAST || direction == WEST;
                            if (isNorthOrSouth) {
                                this.executeDestruction(player, mat, world, stack, destructionPos, direction, true);
                            } else if (isWestOrEast) {
                                this.executeDestruction(player, mat, world, stack, destructionPos, direction, false);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            ItemStack stack = this.setTag(player.getItemInHand(hand));
            CompoundTag nbt = stack.getTag();
            if (nbt != null && nbt.hasUUID("key")) {
                int id = GeckoLibUtil.guaranteeIDForStack(stack, (ServerLevel) world);
                PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> {
                    return player;
                });
                GeckoLibNetwork.syncAnimation(target, this, id, 0);
            }
            if (stack.getDamageValue() >= stack.getMaxDamage() - 1) {
                return InteractionResultHolder.fail(stack);
            } else {
                player.startUsingItem(hand);
                return InteractionResultHolder.consume(stack);
            }
        }
        return super.use(world, player, hand);
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        Level world = entity.level;
        if (!world.isClientSide) {
            ItemStack itemStack = this.setTag(stack);
            CompoundTag nbt = stack.getTag();
            if (nbt != null && nbt.hasUUID("key")) {
                AnimationController<?> controller = GeckoLibUtil.getControllerForStack(this.factory, itemStack, this.controllerName);
                int id = GeckoLibUtil.guaranteeIDForStack(stack, (ServerLevel) world);
                PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> {
                    return entity;
                });
                GeckoLibNetwork.syncAnimation(target, this, id, 1);
            }
        }
        return true;
    }

    private ItemStack setTag(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        UUID randomUUID = UUID.randomUUID();
        if (tag == null) {
            tag = new CompoundTag();
            tag.putUUID("key", randomUUID);
            stack.setTag(tag);
        }
        if (!tag.hasUUID("key")) {
            tag.putUUID("key", randomUUID);
        }
        stack.setTag(tag);
        return stack;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.NONE;
    }

    //    public void chargeAnimation(AnimationController<?> controller) {
    //        if (controller.getAnimationState() == AnimationState.Stopped) {
    //            controller.setAnimation(new AnimationBuilder()
    //                    .addAnimation("animation.mace.charge", false)
    //                    .addAnimation("animation.mace.hold_charge", true));
    //        }
    //    }
    //
    //    public void swingAnimation(AnimationController<?> controller) {
    //        if (controller.getAnimationState() == AnimationState.Stopped) {
    //            controller.markNeedsReload();
    //            controller.setAnimation(new AnimationBuilder().addAnimation("animation.mace.swing_attack", false));
    //        }
    //    }

    private void executeDestruction(Player player, IAPMace mat, Level world, ItemStack stack, BlockPos destructionPos, Direction direction, boolean flag) {
        this.destroyBlocksInLineDirectional(mat, world, destructionPos, direction, flag);
        int damage = mat.destructionRange();
        //Damages the item and executes the animation

        stack.hurtAndBreak(random.nextInt(damage) + (damage * damage), player, (entity) -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        player.getCooldowns().addCooldown(stack.getItem(), mat.cooldown() * 20);
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

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        List<ItemStack> stackData = event.getExtraDataOfType(ItemStack.class);
        if (!stackData.isEmpty()) {
            ItemStack stack = stackData.get(0);
            if (!stack.isEmpty()) {
                return stack.isFramed() ? STOP : CONTINUE;
            }
        }
        return STOP;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, this.controllerName, 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public void onAnimationSync(int id, int state) {
        if (state == 0) {
            AnimationController<?> controller = GeckoLibUtil.getControllerForID(this.factory, id, this.controllerName);
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.mace.charge", false)
                        .addAnimation("animation.mace.hold_charge", true));
            }
        }
        if (state == 1) {
            AnimationController<?> controller = GeckoLibUtil.getControllerForID(this.factory, id, this.controllerName);
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation((new AnimationBuilder()).addAnimation("animation.mace.swing_attack", false));
            }
        }

    }

}