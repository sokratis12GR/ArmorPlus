package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.registry.entities.bosses.SkeletalKingEntity;
import com.sofodev.armorplus.registry.entities.normal.WitherlingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.sofodev.armorplus.ArmorPlus.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModAttributes {

    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, MODID);

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.SKELETAL_KING.get(), SkeletalKingEntity.registerAttributes().build());
        event.put(ModEntities.WITHERLING.get(), WitherlingEntity.registerAttributes().build());
//        event.put(ModEntities.DEMONIC_DRAGON.get(), DemonicDragonEntity.registerAttributes().build());
//        event.put(ModEntities.FROST_WOLF.get(), FrostWolfEntity.registerAttributes().build());
//        event.put(ModEntities.FROST_WOLF_ALPHA.get(), FrostWolfAlphaEntity.registerAttributes().build());
//        event.put(ModEntities.BOREAS.get(), BoreasEntity.registerAttributes().build());
        //TODO Fix Mobs
//        event.validateEntityAttributes();
    }
}
