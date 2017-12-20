package net.thedragonteam.armorplus.entity.dungeon.wither.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

import static java.lang.String.format;
import static net.minecraft.init.Items.*;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.item.ItemStack.EMPTY;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.util.TextUtils.formatText;
import static net.thedragonteam.armorplus.util.Utils.emptyArmor;
import static net.thedragonteam.armorplus.util.Utils.getItemStacks;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
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

    private static void setDropChance(EntityWitherSkeleton minion, EntityEquipmentSlot... slots) {
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
        if (this.world.isRemote || shootingEntity == null || result.entityHit == null || !(result.entityHit instanceof EntityPlayer)) {
            return;
        }
        BlockPos blockPos = new BlockPos(result.entityHit);
        int warriorMax = 3, archerMax = 2, paladinMax = 1;
        int warriorBound = rand.nextInt(warriorMax - 1 + 1) + 1;
        int archerBound = rand.nextInt(archerMax - 1 + 1) + 1;
        int paladinBound = rand.nextInt(paladinMax);
        for (int i2 = 0; i2 < warriorBound; i2++) {
            EntityWitherSkeleton minionWarrior = new EntityWitherSkeleton(this.world);
            if (shootingEntity.getHealth() <= 1200.0F && shootingEntity.getHealth() > 1000.0F) {
                this.setMinionStats(minionWarrior, getItemStack(WOODEN_SWORD), EMPTY, emptyArmor);
            } else if (shootingEntity.getHealth() <= 1000.0F && shootingEntity.getHealth() > 800.0F) {
                this.setMinionStats(minionWarrior, "Warrior", 18.0D, getItemStack(STONE_SWORD), EMPTY, LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_LEGGINGS, LEATHER_BOOTS);
            } else if (shootingEntity.getHealth() <= 800.0F && shootingEntity.getHealth() > 600.0F) {
                this.setMinionStats(minionWarrior, "Warrior", 21.0D, getItemStack(GOLDEN_SWORD), EMPTY, GOLDEN_HELMET, GOLDEN_CHESTPLATE, GOLDEN_LEGGINGS, GOLDEN_BOOTS);
            } else if (shootingEntity.getHealth() <= 600.0F && shootingEntity.getHealth() > 400.0F) {
                this.setMinionStats(minionWarrior, "Warrior", 24.0D, getItemStack(IRON_SWORD), EMPTY, IRON_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS);
            } else if (shootingEntity.getHealth() <= 400.0F && shootingEntity.getHealth() > 200.0F) {
                this.setMinionStats(minionWarrior, "Warrior", 27.0D, getItemStack(DIAMOND_SWORD), EMPTY, DIAMOND_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS);
            } else if (shootingEntity.getHealth() <= 200.0F && shootingEntity.getHealth() > 0.0F) {
                this.setMinionStats(minionWarrior, "Warrior", 30.0D, getItemStack(DIAMOND_AXE), EMPTY, DIAMOND_HELMET, DIAMOND_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS);
            }
            minionWarrior.setPositionAndUpdate(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            minionWarrior.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(minionWarrior)), null);
            this.world.spawnEntity(minionWarrior);
        }
        for (int i1 = 0; i1 < archerBound; i1++) {
            EntityWitherSkeleton minionArcher = new EntityWitherSkeleton(this.world);
            if (shootingEntity.getHealth() <= 800.0F && shootingEntity.getHealth() > 600.0F) {
                this.setMinionStats(minionArcher, "Archer", 15.0D, getItemStack(BOW), EMPTY, LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_LEGGINGS, LEATHER_BOOTS);
            } else if (shootingEntity.getHealth() <= 600.0F && shootingEntity.getHealth() > 400.0F) {
                this.setMinionStats(minionArcher, "Archer", 16.0D, getItemStack(BOW), EMPTY, CHAINMAIL_HELMET, CHAINMAIL_CHESTPLATE, CHAINMAIL_LEGGINGS, CHAINMAIL_BOOTS);
            } else if (shootingEntity.getHealth() <= 400.0F && shootingEntity.getHealth() > 200.0F) {
                this.setMinionStats(minionArcher, "Archer", 17.0D, getItemStack(BOW), EMPTY, IRON_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS);
            } else if (shootingEntity.getHealth() <= 200.0F && shootingEntity.getHealth() > 0.0F) {
                this.setMinionStats(minionArcher, "Archer", 18.0D, getItemStack(BOW), EMPTY, DIAMOND_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS);
            }
            minionArcher.setPositionAndUpdate(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            minionArcher.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(minionArcher)), null);
            this.world.spawnEntity(minionArcher);
        }
        for (int i = 0; i < paladinBound; i++) {
            EntityWitherSkeleton minionPaladin = new EntityWitherSkeleton(this.world);
            if (shootingEntity.getHealth() <= 400.0F && shootingEntity.getHealth() > 200.0F) {
                this.setMinionStats(minionPaladin, "Paladin", 30.0D,
                    getItemStack(superStarSword), getItemStack(SHIELD), DIAMOND_HELMET, DIAMOND_CHESTPLATE, DIAMOND_LEGGINGS, DIAMOND_BOOTS
                );
            } else if (shootingEntity.getHealth() <= 200.0F && shootingEntity.getHealth() > 0.0F) {
                this.setMinionStats(minionPaladin, "Paladin", 35.0D,
                    getItemStack(superStarBattleAxe), getItemStack(SHIELD), superStarHelmet, superStarChestplate, superStarLeggings, superStarBoots
                );
            }
            minionPaladin.setPositionAndUpdate(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            minionPaladin.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(minionPaladin)), null);
            this.world.spawnEntity(minionPaladin);
        }
        result.entityHit.sendMessage(formatText(TextFormatting.RED, "%sRise Minions, Rise!!!", TextFormatting.ITALIC));
        this.setDead();
    }

    private void setMinionStats(EntityWitherSkeleton witherSkeleton, String type, double maxHealth, ItemStack mainHand, ItemStack offHand, Item... equipedArmor) {
        if (equipedArmor != null) {
            setMinionStats(witherSkeleton, type, maxHealth, mainHand, offHand, getItemStacks(equipedArmor));
        }
    }

    private void setMinionStats(EntityWitherSkeleton witherSkeleton, String type, double maxHealth, ItemStack mainHand, ItemStack offHand, ItemStack... equipedArmor) {
        setMinionStats(witherSkeleton, type, maxHealth, mainHand, offHand, getItemStacks(equipedArmor));
    }

    private void setMinionStats(EntityWitherSkeleton witherSkeleton, ItemStack mainHand, ItemStack offHand, ItemStack[] equipedArmor) {
        setMinionStats(witherSkeleton, "Warrior", 15.0D, mainHand, offHand, getItemStacks(equipedArmor));
    }

    private void setMinionStats(EntityWitherSkeleton minion, String type, double maxHealth, ItemStack mainHand, ItemStack offHand, NonNullList<ItemStack> equipedArmor) {
        minion.setCustomNameTag(format("%sSkeletal King's %s", TextFormatting.YELLOW, type));
        minion.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(maxHealth);
        minion.heal(minion.getMaxHealth());
        minion.setDropItemsWhenDead(false);
        minion.setItemStackToSlot(MAINHAND, mainHand);
        minion.setItemStackToSlot(OFFHAND, offHand);
        minion.setItemStackToSlot(HEAD, equipedArmor.get(0));
        minion.setItemStackToSlot(CHEST, equipedArmor.get(1));
        minion.setItemStackToSlot(LEGS, equipedArmor.get(2));
        minion.setItemStackToSlot(FEET, equipedArmor.get(3));
        setDropChance(minion, MAINHAND, OFFHAND, HEAD, CHEST, LEGS, FEET);
        minion.setAlwaysRenderNameTag(true);
        minion.setInvisible(false);
        minion.setEntityInvulnerable(false);
        minion.setCanPickUpLoot(true);
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