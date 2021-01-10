package com.sofodev.armorplus.events;


import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.armors.APArmorItem;
import com.sofodev.armorplus.registry.items.armors.APArmorMaterial;
import com.sofodev.armorplus.registry.items.armors.IAPArmor;
import com.sofodev.armorplus.registry.items.tools.APMaceItem;
import com.sofodev.armorplus.registry.items.tools.properties.mace.APMaceType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.Random;

import static com.sofodev.armorplus.registry.items.tools.APMaceItem.getControllerForStack;
import static com.sofodev.armorplus.utils.ItemArmorUtility.areExactMatch;
import static com.sofodev.armorplus.utils.Utils.*;
import static net.minecraftforge.registries.ForgeRegistries.ENCHANTMENTS;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID)
public class ModGlobalEvents {

    public static final Random RAND = new Random();

    @SubscribeEvent
    public static void onPlayerTickEvent(TickEvent.PlayerTickEvent e) {
        PlayerEntity player = e.player;
        for (ItemStack stack : player.getArmorInventoryList()) {
            if (stack.getItem() instanceof APArmorItem) {
                APArmorItem item = ((APArmorItem) stack.getItem());
                IAPArmor mat = item.getMat();
                if (mat == APArmorMaterial.ENDER_DRAGON || mat == APArmorMaterial.SLAYER) {
                    if (canAllowFlight(player, areExactMatch(mat, player))) {
                        player.abilities.allowFlying = true;
                    } else {
                        player.abilities.allowFlying = false;
                        player.abilities.isFlying = false;
                    }
                }
            }
        }
        //  for (BuffInstance instance : APConfig.SERVER.BUFF_INSTANCE_LIST) {
        //      System.out.println(instance.toString());
        //  }
    }

    @SubscribeEvent
    public static void onAttackEntityEvent(AttackEntityEvent event) {
        World world = event.getEntityLiving().world;
        PlayerEntity player = event.getPlayer();
        Entity target = event.getTarget();
        float attackDamage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
        double movedDistance = player.distanceWalkedModified - player.prevDistanceWalkedModified;
        boolean isMace = false;
        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        if (player.isOnGround() && movedDistance < (double) player.getAIMoveSpeed() && stack.getItem() instanceof APMaceItem) {
            isMace = true;
        }

        if (isMace) {
            APMaceItem mace = (APMaceItem) stack.getItem();
            float sweepingDamage = 1.0F + APMaceType.getMaceSweepingRatio(mace.mat.getType()) * attackDamage;

            for (LivingEntity entity : world.getEntitiesWithinAABB(LivingEntity.class, target.getBoundingBox().grow(1.0D, 0.25D, 1.0D))) {
                boolean isNewTarget = entity != player && entity != target;
                boolean isValidTarget = !player.isOnSameTeam(entity) && (!(entity instanceof ArmorStandEntity) || !((ArmorStandEntity) entity).hasMarker());
                boolean isReachable = player.getDistanceSq(entity) < 15.0D;
                if (isNewTarget && isValidTarget && isReachable) {
                    double ratioX = MathHelper.sin(player.rotationYaw * ((float) Math.PI / 180F));
                    double ratioZ = -MathHelper.cos(player.rotationYaw * ((float) Math.PI / 180F));
                    entity.applyKnockback(0.4F, ratioX, ratioZ);
                    entity.attackEntityFrom(DamageSource.causePlayerDamage(player), sweepingDamage);
                }
            }
            if (world.isRemote) {
                mace.swingAnimation(getControllerForStack(mace.factory, stack, mace.controllerName));
            }
            world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F, 1.0F);
            player.spawnSweepParticles();
        }
    }

    @SubscribeEvent
    public static void onMobDeathEvent(LivingDropsEvent event) {
        boolean oneInFourChance = RAND.nextInt(4) == 0; // 1/4 chance
        int amountZeroToTwo = RAND.nextInt(3); //0..1..2
        int amountFourToSix = RAND.nextInt(3) + 4; //4+(0..1..2)
        LivingEntity entity = event.getEntityLiving();
        Entity trueSource = event.getSource().getTrueSource();
        boolean isSourcePlayer = trueSource instanceof ServerPlayerEntity;
        ServerPlayerEntity player;
        ItemStack heldItem;
        boolean hasSoulStealer = false;
        Map<Enchantment, Integer> enchantmentList;
        if (isSourcePlayer) {
            player = (ServerPlayerEntity) trueSource;
            heldItem = player.getHeldItemMainhand();
            if (!heldItem.isEmpty()) {
                enchantmentList = EnchantmentHelper.getEnchantments(heldItem);
                hasSoulStealer = enchantmentList.containsKey(ENCHANTMENTS.getValue(setRL("soul_stealer")));
            }
        }
        if (entity != null) {
            if (entity instanceof WitherEntity) {
                dropItem(entity, "wither_bone", amountFourToSix);
                if (hasSoulStealer) {
                    dropItem(entity, "soul_wither_boss", 1);
                }
            } else if (entity instanceof EnderDragonEntity) {
                dropItem(entity, "ender_dragon_scale", amountFourToSix);
                if (hasSoulStealer) {
                    dropItem(entity, "soul_ender_dragon", 1);
                }
            } else if (entity instanceof ElderGuardianEntity) {
                dropItem(entity, "guardian_scale", amountFourToSix);
                if (hasSoulStealer) {
                    dropItem(entity, "soul_elder_guardian", 1);
                }
            } else if (entity instanceof WitherSkeletonEntity) {
                dropItem(entity, "wither_bone", amountZeroToTwo);
                if (hasSoulStealer && oneInFourChance) {
                    dropItem(entity, "soul_wither_skeleton", 1);
                }
            } else if (entity instanceof GuardianEntity) {
                dropItem(entity, "guardian_scale", amountZeroToTwo);
                if (hasSoulStealer && oneInFourChance) {
                    dropItem(entity, "soul_guardian", 1);
                }
            } else if (entity instanceof EndermanEntity) {
                if (hasSoulStealer && oneInFourChance) {
                    dropItem(entity, "soul_enderman", 1);
                }
            } else if (entity instanceof BlazeEntity) {
                if (hasSoulStealer && oneInFourChance) {
                    dropItem(entity, "soul_blaze", 1);
                }
            }
        }
    }

    private static void dropItem(Entity entity, String item, int amount) {
        entity.entityDropItem(new ItemStack(getAPItem(item), amount));
    }
}