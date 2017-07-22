package net.thedragonteam.armorplus.entity.dungeon.wither.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.util.Utils;

import java.util.Arrays;

import static java.lang.String.format;
import static net.minecraft.init.Items.*;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.item.ItemStack.EMPTY;
import static net.thedragonteam.armorplus.util.TextUtils.formatText;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * ArmorPlus - Kotlin created by sokratis12GR
 * - TheDragonTeam
 */
public class EntityWitherMinion extends EntityFireball implements IThrowableEntity {
    private static final DataParameter<Boolean> INVULNERABLE = EntityDataManager.createKey(EntityWitherMinion.class, DataSerializers.BOOLEAN);
    private Entity shooter;

    public EntityWitherMinion(World worldIn) {
        super(worldIn);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntityWitherMinion(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.setSize(0.3125F, 0.3125F);
        this.shooter = shooter;
    }

    @SideOnly(Side.CLIENT)
    public EntityWitherMinion(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(worldIn, x, y, z, accelX, accelY, accelZ);
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
        ItemStack[] emptyArmor = new ItemStack[4];
        Arrays.fill(emptyArmor, EMPTY);
        if (this.world.isRemote || shootingEntity == null || result.entityHit == null) {
            return;
        }
        if (result.entityHit instanceof EntityLivingBase) {
            BlockPos blockPos = new BlockPos(result.entityHit);
            for (int c = 0; c < rand.nextInt(3 - 1 + 1) + 1; c++) {
                EntityWitherSkeleton witherSkeleton = new EntityWitherSkeleton(this.world);
                if (shootingEntity.getHealth() <= 1200.0D && shootingEntity.getHealth() > 1000.0D) {
                    // 6 (1200 ; 1000)
                    this.setWitherMinionValues(witherSkeleton, 15.0D, getItemStack(WOODEN_SWORD), EMPTY, emptyArmor);
                } else if (shootingEntity.getHealth() <= 1000.0D && shootingEntity.getHealth() > 800.0D) {
                    // 5 (1000 ; 800)
                    this.setWitherMinionValues(witherSkeleton, 18.0D, getItemStack(STONE_SWORD), EMPTY,
                            LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_LEGGINGS, LEATHER_BOOTS
                    );
                } else if (shootingEntity.getHealth() <= 800.0D && shootingEntity.getHealth() > 600.0D) {
                    // 4 (800 ; 600) - Archers Addition
                    this.setWitherMinionValues(witherSkeleton, 21.0D, getItemStack(GOLDEN_SWORD), EMPTY,
                            GOLDEN_HELMET, GOLDEN_CHESTPLATE, GOLDEN_LEGGINGS, GOLDEN_BOOTS
                    );
                } else if (shootingEntity.getHealth() <= 600.0D && shootingEntity.getHealth() > 400.0D) {
                    // 3 (600 ; 400)
                    this.setWitherMinionValues(witherSkeleton, 24.0D, getItemStack(IRON_SWORD), EMPTY,
                            IRON_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS
                    );
                } else if (shootingEntity.getHealth() <= 400.0D && shootingEntity.getHealth() > 200.0D) {
                    // 2 (400 ; 200) - Paladin Addition
                    this.setWitherMinionValues(witherSkeleton, 27.0D, getItemStack(DIAMOND_SWORD), EMPTY,
                            DIAMOND_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS
                    );
                } else if (shootingEntity.getHealth() <= 200.0D && shootingEntity.getHealth() > 0.0D) {
                    // 1 (200 ; 0)
                    this.setWitherMinionValues(witherSkeleton, 30.0D, getItemStack(DIAMOND_AXE), EMPTY,
                            DIAMOND_HELMET, DIAMOND_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS
                    );
                }
                witherSkeleton.setPositionAndUpdate(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                witherSkeleton.setAlwaysRenderNameTag(true);
                witherSkeleton.setInvisible(false);
                witherSkeleton.setEntityInvulnerable(false);
                witherSkeleton.setCanPickUpLoot(true);
                witherSkeleton.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(witherSkeleton)), null);
                this.world.spawnEntity(witherSkeleton);
            }
            result.entityHit.sendMessage(formatText(TextFormatting.RED, "%sRise Minions, Rise!!!", TextFormatting.ITALIC));
        }
        this.setDead();
    }

    private void setDropChance(EntityWitherSkeleton minion, EntityEquipmentSlot... slots) {
        Arrays.stream(slots).forEachOrdered(slot -> minion.setDropChance(slot, 0.0F));
    }

    private void setWitherMinionValues(EntityWitherSkeleton minion,
                                       String customNameTag, double maxHealth, ItemStack mainHand, ItemStack offHand, NonNullList<ItemStack> equipedArmor) {
        minion.setCustomNameTag(customNameTag);
        minion.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(maxHealth);
        minion.heal(minion.getMaxHealth());
        minion.setDropItemsWhenDead(false);
        if (!minion.getHeldItem(EnumHand.MAIN_HAND).isEmpty()) {
            minion.setHeldItem(EnumHand.MAIN_HAND, EMPTY);
        }
        if (!minion.getHeldItem(EnumHand.OFF_HAND).isEmpty()) {
            minion.setHeldItem(EnumHand.OFF_HAND, EMPTY);
        }
        minion.setItemStackToSlot(MAINHAND, mainHand);
        minion.setItemStackToSlot(OFFHAND, offHand);
        minion.setItemStackToSlot(HEAD, equipedArmor.get(0));
        minion.setItemStackToSlot(CHEST, equipedArmor.get(1));
        minion.setItemStackToSlot(LEGS, equipedArmor.get(2));
        minion.setItemStackToSlot(FEET, equipedArmor.get(3));
        this.setDropChance(minion, MAINHAND, OFFHAND, HEAD, CHEST, LEGS, FEET);
    }

    private void setWitherMinionValues(EntityWitherSkeleton witherSkeleton, double maxHealth, ItemStack mainHand, ItemStack offHand, Item... equipedArmor) {
        this.setWitherMinionValues(witherSkeleton, format("%sSkeletal King's Minion", TextFormatting.YELLOW), maxHealth, mainHand, offHand, Utils.getItemStacks(equipedArmor));
    }

    private void setWitherMinionValues(EntityWitherSkeleton witherSkeleton, double maxHealth, ItemStack mainHand, ItemStack offHand, ItemStack... equipedArmor) {
        this.setWitherMinionValues(witherSkeleton, format("%sSkeletal King's Minion", TextFormatting.YELLOW), maxHealth, mainHand, offHand, Utils.getItemStacks(equipedArmor));
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