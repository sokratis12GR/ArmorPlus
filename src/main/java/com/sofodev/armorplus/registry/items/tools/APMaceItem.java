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
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Random;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.registry.items.tools.properties.mace.DestructionShape.PLUS;
import static com.sofodev.armorplus.registry.items.tools.properties.mace.DestructionShape.SQUARE;
import static net.minecraft.core.Direction.*;
import static net.minecraft.tags.BlockTags.WITHER_IMMUNE;
import static software.bernie.geckolib.core.object.PlayState.CONTINUE;
import static software.bernie.geckolib.core.object.PlayState.STOP;

public class APMaceItem extends SwordItem implements GeoItem {
    private static final RawAnimation ATTACK = RawAnimation.begin().thenPlay("animation.mace.attack");
    private static final RawAnimation SWIPE_ATTACK = RawAnimation.begin().thenPlay("animation.mace.swipe_attack");
    private static final RawAnimation SWING_ATTACK = RawAnimation.begin().thenPlay("animation.mace.swing_attack");
    private static final RawAnimation CHARGE = RawAnimation.begin().thenPlay("animation.mace.charge");
    private static final RawAnimation HOLD_CHARGE = RawAnimation.begin().thenPlay("animation.mace.hold_charge");

    public final IAPMace mat;
    public final String controllerName = "maceController";
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    Random random = new Random();

    public APMaceItem(IAPMace mat, Item.Properties props) {
        super(mat.get(), (int) (mat.get().getAttackDamageBonus() + mat.getType().getDmg()), mat.getType()
                .getAttackSpeed(), props);
        this.mat = mat;


        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private APMaceRenderer renderer;

            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                    this.renderer = new APMaceRenderer();
                }

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
    public void releaseUsing(ItemStack stack, Level level, LivingEntity living, int timeLeft) {
        if (living instanceof Player) {
            Player player = (Player) living;
            CompoundTag nbt = stack.getTag();
            if (nbt != null && nbt.hasUUID("key")) {
                int chargeTime = this.getUseDuration(stack) - timeLeft;
                if (chargeTime >= mat.getType().getChargeSpeed()) {
                    if (level instanceof ServerLevel serverLevel) {
                        this.triggerAnim(player, GeoItem.getOrAssignId(player.getItemInHand(player.getUsedItemHand()), serverLevel), controllerName, "animation.mace.swing_attack");
                        //check if the offhand is empty
                        boolean isOffHandEmpty = player.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty();
                        if (isOffHandEmpty) {
                            BlockPos destructionPos = new BlockPos((int) player.position().x, (int) player.position().y, (int) player.position().z);
                            Direction direction = player.getMotionDirection();
                            boolean isNorthOrSouth = direction == NORTH || direction == SOUTH;
                            boolean isWestOrEast = direction == EAST || direction == WEST;
                            if (isNorthOrSouth) {
                                this.executeDestruction(player, mat, level, stack, destructionPos, direction, true);
                            } else if (isWestOrEast) {
                                this.executeDestruction(player, mat, level, stack, destructionPos, direction, false);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level instanceof ServerLevel serverLevel) {
            ItemStack stack = this.setTag(player.getItemInHand(hand));
            CompoundTag nbt = stack.getTag();
            if (nbt != null && nbt.hasUUID("key")) {
                triggerAnim(player, GeoItem.getOrAssignId(player.getItemInHand(hand), serverLevel), controllerName, "animation.mace.hold_charge");
            }
            if (stack.getDamageValue() >= stack.getMaxDamage() - 1) {
                return InteractionResultHolder.fail(stack);
            } else {
                player.startUsingItem(hand);
                return InteractionResultHolder.consume(stack);
            }
        }
        return super.use(level, player, hand);
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        Level level = entity.level();
        if (level instanceof ServerLevel serverLevel) {
            ItemStack itemStack = this.setTag(stack);
            CompoundTag nbt = stack.getTag();
            if (nbt != null && nbt.hasUUID("key")) {
                triggerAnim(entity, GeoItem.getOrAssignId(entity.getItemInHand(entity.getUsedItemHand()), serverLevel), controllerName, "animation.mace.swing_attack");
            }
        }
        return true;
    }

    public ItemStack setTag(ItemStack stack) {
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

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add((new AnimationController(this, this.controllerName, 20, this::predicate)).triggerableAnim("swing_attack", SWING_ATTACK));
    }

    private <P extends Item & GeoAnimatable> PlayState predicate(AnimationState<P> event) {
        ItemStack stack = event.getData(DataTickets.ITEMSTACK);
        if (!stack.isEmpty()) {
            return stack.isFramed() ? STOP : CONTINUE;
        }
        return STOP;
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

//    @Override
//    public void onAnimationSync(int id, int state) {
//        if (state == 0) {
//            AnimationController<?> controller = GeckoLibUtil.getControllerForID(this.factory, id, this.controllerName);
//            if (controller.getAnimationState() == AnimationState.Stopped) {
//                controller.markNeedsReload();
//                controller.setAnimation(new AnimationBuilder().addAnimation("animation.mace.charge", false)
//                        .addAnimation("animation.mace.hold_charge", true));
//            }
//        }
//        if (state == 1) {
//            AnimationController<?> controller = GeckoLibUtil.getControllerForID(this.factory, id, this.controllerName);
//            if (controller.getAnimationState() == AnimationState.Stopped) {
//                controller.markNeedsReload();
//                controller.setAnimation((new AnimationBuilder()).addAnimation("animation.mace.swing_attack", false));
//            }
//        }
//
//    }

    @Override
    public double getTick(Object o) {
        return 0;
    }
}