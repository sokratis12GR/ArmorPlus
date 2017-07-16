package net.thedragonteam.armorplus.entity.dungeon.wither.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static java.lang.String.format;
import static java.util.stream.IntStream.range;
import static net.thedragonteam.armorplus.util.TextUtils.formatText;

/**
 * ArmorPlus - Kotlin created by sokratis12GR
 * - TheDragonTeam
 */
public class EntityWitherMinion extends EntityFireball implements IThrowableEntity {
    private static final DataParameter<Boolean> INVULNERABLE = EntityDataManager.createKey(EntityWitherMinion.class, DataSerializers.BOOLEAN);

    private Entity shooter;
    private int spawnCount;

    public EntityWitherMinion(World worldIn) {
        super(worldIn);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntityWitherMinion(World worldIn, EntityLivingBase shooter, int spawnCount, double accelX, double accelY, double accelZ) {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.spawnCount = spawnCount;
        this.setSize(0.3125F, 0.3125F);
        this.shooter = shooter;
    }

    @SideOnly(Side.CLIENT)
    public EntityWitherMinion(World worldIn, int spawnCount, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(worldIn, x, y, z, accelX, accelY, accelZ);
        this.spawnCount = spawnCount;
        this.setSize(0.3125F, 0.3125F);
    }

    public static void registerFixesWitherMinion(DataFixer fixer) {
        EntityFireball.registerFixesFireball(fixer, "WitherMinion");
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

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.entityHit == null || this.world.isRemote) {
            return;
        }
        if (result.entityHit instanceof EntityLivingBase) {
            double posX = result.entityHit.posX;
            double posY = result.entityHit.posY;
            double posZ = result.entityHit.posZ;
            range(0, spawnCount).mapToObj(c -> new EntityWitherSkeleton(this.world)).forEachOrdered(witherSkeleton -> {
                witherSkeleton.removePotionEffect(MobEffects.WITHER);
                witherSkeleton.setPositionAndUpdate(posX, posY, posZ);
                witherSkeleton.setCustomNameTag(format("%sSkeletal King's Minion", TextFormatting.YELLOW));
                witherSkeleton.setAlwaysRenderNameTag(true);
                witherSkeleton.setInvisible(false);
                witherSkeleton.setEntityInvulnerable(false);
                witherSkeleton.setCanPickUpLoot(true);
                witherSkeleton.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(witherSkeleton)), null);
                this.world.spawnEntity(witherSkeleton);
            });
            result.entityHit.sendMessage(formatText(TextFormatting.RED, "%sRise Minions, Rise!!!", TextFormatting.ITALIC));
        }
        this.setDead();
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
    protected void entityInit() {
        this.dataManager.register(INVULNERABLE, false);
    }

    @Override
    protected boolean isFireballFiery() {
        return false;
    }

    /**
     * Gets the entity that threw/created this entity.
     *
     * @return The owner instance, Null if none.
     */
    @Override
    public Entity getThrower() {
        return shooter;
    }

    /**
     * Sets the entity that threw/created this entity.
     *
     * @param entity The new thrower/creator.
     */
    @Override
    public void setThrower(Entity entity) {
        shooter = entity;
    }
}