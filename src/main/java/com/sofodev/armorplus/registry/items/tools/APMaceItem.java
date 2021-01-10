package com.sofodev.armorplus.registry.items.tools;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.tools.properties.mace.IAPMace;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.Animation;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.registry.items.tools.properties.mace.DestructionShape.PLUS;
import static com.sofodev.armorplus.registry.items.tools.properties.mace.DestructionShape.SQUARE;
import static net.minecraft.tags.BlockTags.WITHER_IMMUNE;
import static net.minecraft.util.Direction.*;
import static software.bernie.geckolib3.core.PlayState.CONTINUE;
import static software.bernie.geckolib3.core.PlayState.STOP;

public class APMaceItem extends SwordItem implements IAnimatable {

    public final IAPMace mat;
    public AnimationFactory factory = new AnimationFactory(this);
    public final String controllerName = "maceController";

    public APMaceItem(IAPMace mat, Item.Properties props) {
        super(mat.get(), (int) (mat.get().getAttackDamage() + mat.getType().getDmg()), mat.getType().getAttackSpeed(),
                props.group(ArmorPlus.AP_WEAPON_GROUP)
        );
        this.mat = mat;
    }

    public static int getIDFromStack(ItemStack stack) {
        return Objects.hash(stack.getItem(), stack.getCount(), stack.hasTag() ? stack.getTag().toString() : 1);
    }

    @Override
    public boolean shouldSyncTag() {
        return super.shouldSyncTag();
    }

    public static AnimationController getControllerForStack(AnimationFactory factory, ItemStack stack, String controllerName) {
        return factory.getOrCreateAnimationData(getIDFromStack(stack)).getAnimationControllers().get(controllerName);
    }

    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
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
    public void onPlayerStoppedUsing(ItemStack stack, World world, LivingEntity living, int timeLeft) {
        if (living instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) living;
            CompoundNBT nbt = stack.getTag();
            if (nbt != null && nbt.hasUniqueId("key")) {
                AnimationController<?> controller = getControllerForStack(this.factory, stack, this.controllerName);
                Animation currentAnimation = controller.getCurrentAnimation();
                int chargeTime = this.getUseDuration(stack) - timeLeft;
                if (chargeTime >= mat.getType().getChargeSpeed() && player.getCooldownPeriod() >= 1.0F) {
                    if (currentAnimation != null) {
                        currentAnimation.loop = false;
                    }
                    if (world.isRemote) {
                        this.swingAnimation(controller);
                    } else {
                        //check if the offhand is empty
                        boolean isOffHandEmpty = player.getItemStackFromSlot(EquipmentSlotType.OFFHAND).isEmpty();
                        if (isOffHandEmpty) {
                            BlockPos destructionPos = new BlockPos(player.getPosition());
                            Direction direction = player.getAdjustedHorizontalFacing();
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
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = this.setTag(player.getHeldItem(hand));
        CompoundNBT nbt = stack.getTag();
        if (nbt != null && nbt.hasUniqueId("key")) {
            AnimationController<?> controller = getControllerForStack(this.factory, stack, this.controllerName);
            if (world.isRemote) {
                this.chargeAnimation(controller);
                return ActionResult.resultPass(stack);
            }
        }
        if (stack.getDamage() >= stack.getMaxDamage() - 1) {
            return ActionResult.resultFail(stack);
        } else {
            player.setActiveHand(hand);
            return ActionResult.resultConsume(stack);
        }
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        World world = entity.world;
        ItemStack itemStack = this.setTag(stack);
        CompoundNBT nbt = stack.getTag();
        if (nbt != null && nbt.hasUniqueId("key")) {
            AnimationController<?> controller = getControllerForStack(this.factory, itemStack, this.controllerName);
            if (world.isRemote && entity instanceof PlayerEntity) {
                this.swingAnimation(controller);
                return true;
            }
        }

        return true;
    }

    private ItemStack setTag(ItemStack stack) {
        CompoundNBT tag = stack.getTag();
        UUID randomUUID = UUID.randomUUID();
        if (tag == null) {
            tag = new CompoundNBT();
            stack.setTag(tag);
        }
        if (!tag.hasUniqueId("key")) {
            tag.putUniqueId("key", randomUUID);
        }
        stack.setTag(tag);
        return stack;
    }


    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    public void chargeAnimation(AnimationController<?> controller) {
        if (controller.getAnimationState() == AnimationState.Stopped) {
            controller.setAnimation(new AnimationBuilder()
                    .addAnimation("animation.mace.charge", false)
                    .addAnimation("animation.mace.hold_charge", true));
        }
    }

    public void swingAnimation(AnimationController<?> controller) {
        if (controller.getAnimationState() == AnimationState.Stopped) {
            controller.markNeedsReload();
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.mace.swing_attack", false));
        }
    }

    private void executeDestruction(PlayerEntity player, IAPMace mat, World world, ItemStack stack, BlockPos destructionPos, Direction direction, boolean flag) {
        this.destroyBlocksInLineDirectional(mat, world, destructionPos, direction, flag);
        int damage = mat.destructionRange();
        //Damages the item and executes the animation
        stack.damageItem(random.nextInt(damage) + (damage * damage), player, (entity) -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));
        player.getCooldownTracker().setCooldown(stack.getItem(), mat.cooldown() * 20);
        player.addStat(Stats.ITEM_USED.get(this));
    }

    private void destroyBlocksInLineDirectional(IAPMace mat, World world, BlockPos destructionPos, Direction direction, boolean isNorthOrSouth) {
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
    private void destroyBlockInLine(World world, BlockPos destructionPos, Direction direction, int offset) {
        BlockPos pos = directionalOffset(destructionPos, direction, offset);
        this.destroyBlock(world, pos);
        this.destroyBlock(world, pos.up());
        this.destroyBlock(world, pos.up().up());
    }

    private void destroyBlockSingleLine(World world, BlockPos destructionPos, Direction direction, int i) {
        this.destroyBlock(world, directionalOffset(destructionPos, direction, i).up());
    }

    /**
     * @param world the world object
     * @param pos   the BlockPos that we will use to destroy the block
     */
    private void destroyBlock(World world, BlockPos pos) {
        if (!WITHER_IMMUNE.contains(world.getBlockState(pos).getBlock())) world.destroyBlock(pos, true);
    }

    /**
     * @param pos       The original destruction pos.
     * @param direction the direction the player is facing.
     * @param offset    the offset in blocks.
     * @return {@link BlockPos} the offset of the original destruction pos.
     */
    private BlockPos directionalOffset(BlockPos pos, Direction direction, int offset) {
        return pos.offset(direction, offset + 1);
    }

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        List<ItemStack> stackData = event.getExtraDataOfType(ItemStack.class);
        if (!stackData.isEmpty()) {
            ItemStack stack = stackData.get(0);
            if (!stack.isEmpty()) {
                return stack.isOnItemFrame() ? STOP : CONTINUE;
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
}