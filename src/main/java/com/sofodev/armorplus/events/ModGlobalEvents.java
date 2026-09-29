package com.sofodev.armorplus.events;


import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.item.armor.APArmorItem;
import com.sofodev.armorplus.registry.item.armor.IAPArmor;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.IBuff;
import com.sofodev.armorplus.registry.item.material.FrostCrystalItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

import static com.sofodev.armorplus.registry.item.extra.Buff.FLIGHT;
import static com.sofodev.armorplus.registry.item.extra.Buff.WATER_WEAKNESS;
import static com.sofodev.armorplus.utils.ItemArmorUtility.areExactMatch;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static net.minecraft.world.phys.Vec3.atBottomCenterOf;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID)
public class ModGlobalEvents {

    public static final Random RAND = new Random();
    public static int waterTicks = 0;
    public static int thunderingTicks = 0;
    private static final String ARMORPLUS_FLIGHT_TAG = "ArmorPlusFlight";
    private static final String ARMORPLUS_PREV_MAYFLY = "ArmorPlusPrevMayfly";

    private static boolean isFullArmorWithBuff(Player player, IBuff buff) {
        for (EquipmentSlot slot : new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}) {
            ItemStack stack = player.getItemBySlot(slot);
            if (stack.getItem() instanceof APArmorItem armor) {
                IAPArmor mat = armor.getMat();
                if (areExactMatch(mat, player) && mat.config().enableArmorEffects().get()) {
                    return mat.getBuffInstances().get().stream()
                            .map(BuffInstance::getBuff)
                            .anyMatch(b -> b.equals(buff));
                }
            }
        }
        return false;
    }

    private static void grantFlight(Player player) {
        if (player.isCreative() || player.isSpectator()) return;

        if (!player.getPersistentData().contains(ARMORPLUS_PREV_MAYFLY)) {
            player.getPersistentData().putBoolean(ARMORPLUS_PREV_MAYFLY, player.getAbilities().mayfly);
        }

        player.getAbilities().mayfly = true;
        player.getPersistentData().putBoolean(ARMORPLUS_FLIGHT_TAG, true);
        player.onUpdateAbilities();
    }

    private static void revokeFlight(Player player) {
        if (player.isCreative() || player.isSpectator()) return;

        boolean hadAPFlight = player.getPersistentData().getBoolean(ARMORPLUS_FLIGHT_TAG).orElse(false);
        if (!hadAPFlight) return;

        player.getPersistentData().remove(ARMORPLUS_FLIGHT_TAG);

        boolean prevMayfly = player.getPersistentData().getBoolean(ARMORPLUS_PREV_MAYFLY).orElse(false);
        player.getAbilities().mayfly = prevMayfly;

        if (player.getAbilities().flying) player.getAbilities().flying = false;

        // Remove stored previous state
        player.getPersistentData().remove(ARMORPLUS_PREV_MAYFLY);

        player.onUpdateAbilities();
    }

    private static void checkAndApplyFlight(Player player) {
        boolean hasOurFlight = isFullArmorWithBuff(player, FLIGHT);

        if (hasOurFlight) {
            grantFlight(player);
        } else {
            revokeFlight(player);
        }
    }

    private static void checkAndApplyBuffs(Player player) {
        checkAndApplyFlight(player);

        if (isFullArmorWithBuff(player, WATER_WEAKNESS) && player.isInWater() || player.isInWaterOrRain()) {
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 0, false, false, true));
        }
    }

    @SubscribeEvent
    public static void onPlayerTickEvent(PlayerTickEvent.Post e) {
        Player player = e.player();
        Level world = player.level();
        if (world.isClientSide()) return;

        checkAndApplyBuffs(player);

        if (!world.isThundering()) return;
        thunderingTicks++;
        if ((thunderingTicks + 1) % 20 != 0 || RAND.nextInt(100) + 1 != 100) return;

        // TODO 26.1: restore conductive vanilla-armor lightning behavior using equipment components.

    }

    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent e) {
        if (e.getEntity() instanceof Player player && !player.level().isClientSide()) {
            checkAndApplyBuffs(player);
        }
    }


    @SubscribeEvent
    public static void onStructByLightningEvent(EntityStruckByLightningEvent event) {
        if (!event.getEntity().level().isClientSide() && event.getEntity() instanceof ItemEntity entity) {
            Item item = entity.getItem().getItem();
            if (item instanceof FrostCrystalItem) {
                boolean infused = ((FrostCrystalItem) item).isInfused();
                if (!infused) {
                    FrostCrystalItem infusedCrystal = (FrostCrystalItem) getAPItem("infused_frost_crystal");
                    if (entity.level() instanceof ServerLevel server) entity.spawnAtLocation(server, new ItemStack(infusedCrystal, entity.getItem().getCount()), 1f);
                    entity.getItem().setCount(0);
                    event.getLightning().setVisualOnly(true);

                }
            }
        }
    }


}