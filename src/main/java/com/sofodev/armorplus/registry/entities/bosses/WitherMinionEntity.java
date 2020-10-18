package com.sofodev.armorplus.registry.entities.bosses;

import com.sofodev.armorplus.registry.entities.bosses.data.MinionType;
import com.sofodev.armorplus.utils.Utils;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.Arrays;

import static com.sofodev.armorplus.registry.ModEntities.WITHERLING;
import static com.sofodev.armorplus.registry.entities.bosses.data.MinionType.*;
import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static java.lang.String.format;
import static net.minecraft.entity.SpawnReason.TRIGGERED;
import static net.minecraft.inventory.EquipmentSlotType.*;
import static net.minecraft.util.math.RayTraceResult.Type.ENTITY;
import static net.minecraft.util.text.TextFormatting.ITALIC;

/**
 * @author Sokratis Fotkatzikis
 **/
public class WitherMinionEntity extends FireballEntity {
    private static final DataParameter<Boolean> INVULNERABLE = EntityDataManager.createKey(WitherMinionEntity.class, DataSerializers.BOOLEAN);
    private EntityType<? extends WitherMinionEntity> type;

    public WitherMinionEntity(EntityType<? extends WitherMinionEntity> type, World world) {
        super(type, world);
        this.type = type;
    }

    public WitherMinionEntity(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        super(worldIn, shooter, accelX, accelY, accelZ);
    }

    @OnlyIn(Dist.CLIENT)
    public WitherMinionEntity(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(worldIn, x, y, z, accelX, accelY, accelZ);
    }

    @Override
    public EntitySize getSize(Pose poseIn) {
        return new EntitySize(0.3125F, 0.3125F, true);
    }

    private static void setDropChance(WitherlingEntity minion, EquipmentSlotType... slots) {
        Arrays.stream(slots).forEachOrdered(slot -> minion.setDropChance(slot, 0.0F));
    }

    /**
     * Return the motion factor for this projectile. The factor is multiplied by the original motion.
     */
    @Override
    protected float getMotionFactor() {
        return super.getMotionFactor();
    }

    /**
     * Returns true if the entity is on fire. Used by render to add the fire effect on rendering.
     */
    @Override
    public boolean isBurning() {
        return false;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (this.world.isRemote || getEntity() == null || !(result.getType() == ENTITY)) {
            return;
        }
        // Minion Data
        BlockPos blockPos = new BlockPos(result.getHitVec());
        String phaseText = "%sRise Minions, Rise!!!";
        int amountWarriorMax = 4;
        int amountArcherMax = 3;
        int amountPaladinMax = 2;
        int warriorBound = rand.nextInt(amountWarriorMax) + 1;
        int archerBound = rand.nextInt(amountArcherMax) + 1;
        int paladinBound = rand.nextInt(amountPaladinMax) + 1;
        // Spawning Mechanic
        if (checkPhase(1200, 0)) {
            for (int min = 0; min < warriorBound; min++) {
                if (checkPhase(1200.0F)) {
                    WitherlingEntity minionWarrior = create(blockPos);
                    this.setMinionStats(minionWarrior, WARRIOR_0);
                } else if (checkPhase(1000.0F)) {
                    WitherlingEntity minionWarrior = create(blockPos);
                    this.setMinionStats(minionWarrior, WARRIOR_1);
                } else {
                    WitherlingEntity minionWarrior = create(blockPos);
                    spawnMinions(minionWarrior, WARRIOR_2, WARRIOR_3, WARRIOR_4, WARRIOR_5);
                }
            }
        }
        if (checkPhase(800, 0)) {
            for (int min = 0; min < archerBound; min++) {
                WitherlingEntity minionArcher = create(blockPos);
                spawnMinions(minionArcher, ARCHER_1, ARCHER_2, ARCHER_3, ARCHER_4);
            }
        }
        if (checkPhase(400, 0)) {
            for (int min = 0; min < paladinBound; min++) {
                if (checkPhase(400.0F)) {
                    WitherlingEntity minionPaladin = create(blockPos);
                    this.setMinionStats(minionPaladin, PALADIN_1);
                } else if (checkPhase(200.0F)) {
                    WitherlingEntity minionPaladin = create(blockPos);
                    this.setMinionStats(minionPaladin, PALADIN_2);
                }
            }
        }
        if (result.getType() == ENTITY) {
            Entity entity = ((EntityRayTraceResult) result).getEntity();
            entity.sendMessage(translate(phaseText, ITALIC), entityUniqueID);
        }
        this.remove();
    }

    private void spawnMinions(WitherlingEntity minion, MinionType one, MinionType two, MinionType three, MinionType four) {
        if (checkPhase(800.0F)) {
            this.setMinionStats(minion, one);
        } else if (checkPhase(600.0F)) {
            this.setMinionStats(minion, two);
        } else if (checkPhase(400.0F)) {
            this.setMinionStats(minion, three);
        } else if (checkPhase(200.0F)) {
            this.setMinionStats(minion, four);
        }
    }

    private WitherlingEntity create(BlockPos pos) {
        WitherlingEntity minion = new WitherlingEntity(WITHERLING.get(), this.world);
        minion.targetSelector.addGoal(1, new HurtByTargetGoal(minion));
        minion.targetSelector.addGoal(2, new NearestAttackableTargetGoal(minion, LivingEntity.class, 0, false, false, SkeletalKingEntity.ANY_ENTITY));
        minion.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
        minion.onInitialSpawn((IServerWorld) world, this.world.getDifficultyForLocation(new BlockPos(minion.getPosition())), TRIGGERED, null, null);
        this.world.addEntity(minion);
        return minion;
    }

    private boolean checkPhase(float phase) {
        return true; //TODO: FIX THIS;
    }

    private boolean checkPhase(float phase, float rangeEnd) {
        return true; //TODO: FIX THIS;
    }

    //private boolean checkPhase(float phase) {
    //    return checkPhase(phase, phase - 200);
    //}

    //private boolean checkPhase(float phase, float rangeEnd) {
    //    float health = shootingEntity.getHealth();
    //    return health <= phase && health > (rangeEnd);
    //}

    private void setMinionStats(WitherlingEntity witherSkeleton, MinionType data) {
        setMinionStats(witherSkeleton, data.getName(), data.getHealth(), data.getWeapon(), data.getOffHand(), Utils.getItemStacks(data.getArmor()));
    }

    private void setMinionStats(WitherlingEntity minion, String type, double maxHealth, ItemStack mainHand, ItemStack offHand, NonNullList<ItemStack> equipedArmor) {
        minion.setCustomName(new StringTextComponent(format("%sSkeletal King's %s", TextFormatting.YELLOW, type)));
        minion.getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxHealth);
        minion.heal(minion.getMaxHealth());
        minion.setHealth(minion.getMaxHealth());
        //    minion.setDropItemsWhenDead(false);
        minion.setHeldItem(Hand.MAIN_HAND, mainHand);
        minion.setHeldItem(Hand.OFF_HAND, offHand);
        minion.setItemStackToSlot(HEAD, equipedArmor.get(0));
        minion.setItemStackToSlot(CHEST, equipedArmor.get(1));
        minion.setItemStackToSlot(LEGS, equipedArmor.get(2));
        minion.setItemStackToSlot(FEET, equipedArmor.get(3));
        setDropChance(minion, MAINHAND, OFFHAND, HEAD, CHEST, LEGS, FEET);
        minion.setCustomNameVisible(true);
        minion.setInvisible(false);
        minion.setInvulnerable(false);
        minion.setCanPickUpLoot(true);
        minion.forceSpawn = true;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(INVULNERABLE, false);
    }

    @Override
    protected boolean isFireballFiery() {
        return false;
    }

    @Override
    public EntityType<?> getType() {
        return this.type;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    // /**
    //  * Gets the entity that threw/created this entity.
    //  *
    //  * @return The owner instance, Null if none.
    //  */
    // @Override
    // public Entity getShooter() {
    //     return shooter;
    // }

    // /**
    //  * Sets the entity that threw/created this entity.
    //  *
    //  * @param entity The new thrower/creator.
    //  */
    // @Override
    // public void setThrower(Entity entity) {
    //     shooter = entity;
    // }
}