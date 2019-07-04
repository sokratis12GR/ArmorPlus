/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.api.caps.abilities.AbilityData;
import com.sofodev.armorplus.api.caps.ailments.Ailment;
import com.sofodev.armorplus.common.registry.enchantments.EnhancedEnchantment;
import com.sofodev.armorplus.common.registry.enchantments.FuriousEnchantment;
import com.sofodev.armorplus.common.registry.enchantments.LifeStealEnchantment;
import com.sofodev.armorplus.common.registry.potions.PotionEmpty;
import com.sofodev.armorplus.common.registry.sounds.SoundTrapTriggered;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import static com.sofodev.armorplus.api.caps.abilities.ImplementedAbilities.*;
import static com.sofodev.armorplus.api.caps.ailments.ImplementedAilments.AILMENT_REGISTRY;
import static com.sofodev.armorplus.common.config.ModConfig.Experimental.enableExperimentalMode;
import static com.sofodev.armorplus.common.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis
 **/
@EventBusSubscriber(modid = ArmorPlus.MODID)
public class RegistryEventHandler {

    @SubscribeEvent
    public static void registerCapability(Register.NewRegistry event) {
        if (enableExperimentalMode && ABILITY_REGISTRY == null) {
            ResourceLocation registryName = setRL("abilities");
            ABILITY_REGISTRY = new RegistryBuilder<AbilityData>().setType(AbilityData.class).setName(registryName).create();
        }
        if (AILMENT_REGISTRY == null) {
            ResourceLocation registryName = setRL("ailments");
            AILMENT_REGISTRY = new RegistryBuilder<Ailment>().setType(Ailment.class).setName(registryName).create();
        }
    }

    @SubscribeEvent
    public static void registerAbilities(Register<AbilityData> event) {
        if (enableExperimentalMode) {
            registerAbility(event,
                NONE, NIGHT_VISION, WATER_BREATHING, RESISTANCE, FIRE_RESISTANCE, HASTE, SPEED, JUMP_BOOST, REGENERATION, STRENGTH, INVISIBILITY, ABSORPTION,
                WITHER_PROOF, FLIGHT, STEP_ASSIST, BONUS_XP_ON_KILL, WALK_ON_LAVA, SWIMMING_SPEED
            );
        }
    }

    private static void registerAbility(Register<AbilityData> event, AbilityData... dataList) {
        for (AbilityData data : dataList) {
            IForgeRegistry<AbilityData> registry = event.getRegistry();
            if (!registry.containsValue(data)) {
                event.getRegistry().register(data);
            }
        }
    }

    /**
     * Enchantments
     */
    @SubscribeEvent
    public static void registerEnchantments(Register<Enchantment> event) {
        event.getRegistry().registerAll(new FuriousEnchantment(), new LifeStealEnchantment(), new EnhancedEnchantment());
    }

    /**
     * Potions
     */
    @SubscribeEvent
    public static void registerPotions(Register<Potion> event) {
        event.getRegistry().register(new PotionEmpty());
    }

    /**
     * Sounds
     */
    @SubscribeEvent
    public static void registerSounds(Register<SoundEvent> event) {
        event.getRegistry().register(new SoundTrapTriggered());
    }
}
