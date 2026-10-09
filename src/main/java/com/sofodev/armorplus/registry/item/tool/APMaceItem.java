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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;
import java.util.function.Consumer;
import com.geckolib.animatable.GeoItem;
import com.geckolib.animatable.client.GeoRenderProvider;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.AnimationController;
import com.geckolib.animation.object.PlayState;
import com.geckolib.animation.RawAnimation;
import com.geckolib.renderer.GeoItemRenderer;
import com.geckolib.model.DefaultedItemGeoModel;
import net.minecraft.core.registries.BuiltInRegistries;
import com.geckolib.util.GeckoLibUtil;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.registry.item.tool.properties.mace.DestructionShape.PLUS;
import static com.sofodev.armorplus.registry.item.tool.properties.mace.DestructionShape.SQUARE;
import static net.minecraft.tags.BlockTags.WITHER_IMMUNE;

public class APMaceItem extends Item implements GeoItem {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public final IAPMace mat;
    Random random = new Random();

    public APMaceItem(IAPMace mat, Item.Properties props) {
        super(props.sword(mat.get(), (float) mat.get().attackDamageBonus() + mat.getType().getDmg(), mat.getType().getAttackSpeed()));
        this.mat = mat;
        GeoItem.registerSyncedAnimatable(this);

    }

    @Override
    public Component getName(ItemStack stack) {
        return super.getName(stack).copy().withStyle(mat.getColor());
    }


    @Override
    public int getUseDuration(ItemStack stack, net.minecraft.world.entity.LivingEntity entity) {
        return 72000;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.isDamaged() && stack.getDamageValue() >= stack.getMaxDamage() - 1)
            return InteractionResult.FAIL;
        player.startUsingItem(hand);
        if (level instanceof ServerLevel serverLevel)
            triggerAnim(player, GeoItem.getOrAssignId(stack, serverLevel), "mace", "charge");
        return InteractionResult.CONSUME;
    }

    @Override
    public boolean releaseUsing(ItemStack stack, Level level, net.minecraft.world.entity.LivingEntity living, int timeLeft) {
        if (!(living instanceof Player player) || !(level instanceof ServerLevel serverLevel))
            return false;
        int chargedTicks = getUseDuration(stack, living) - timeLeft;
        if (chargedTicks < mat.getType().getChargeSpeed())
            return false;
        triggerAnim(player, GeoItem.getOrAssignId(stack, serverLevel), "mace", "attack");
        if (!player.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty())
            return true;
        Direction direction = player.getDirection();
        BlockPos origin = player.blockPosition();
        if (direction == Direction.NORTH || direction == Direction.SOUTH)
            executeDestruction(player, mat, level, stack, origin, direction, true);
        else if (direction == Direction.EAST || direction == Direction.WEST)
            executeDestruction(player, mat, level, stack, origin, direction, false);
        return true;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.NONE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("mace", 0, state -> PlayState.STOP)
                .triggerableAnim("charge", RawAnimation.begin().thenPlay("animation.mace.hold_charge"))
                .triggerableAnim("attack", RawAnimation.begin().thenPlay("animation.mace.swing_attack")));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoItemRenderer<APMaceItem> renderer;
            @Override
            public GeoItemRenderer<?> getGeoItemRenderer() {
                if (renderer == null) renderer = new GeoItemRenderer<>(new DefaultedItemGeoModel<>(BuiltInRegistries.ITEM.getKey(APMaceItem.this)));
                return renderer;
            }
        });
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
