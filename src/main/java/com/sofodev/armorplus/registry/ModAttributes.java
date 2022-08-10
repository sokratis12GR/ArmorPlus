package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.registry.entities.bosses.SkeletalKingEntity;
import com.sofodev.armorplus.registry.entities.normal.WitherlingEntity;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Optional;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.utils.Utils.setRL;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModAttributes {

    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, MODID);

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        Optional<Holder<EntityType<?>>> skeletal_king = ForgeRegistries.ENTITY_TYPES.getHolder(setRL("skeletal_king"));
        Optional<Holder<EntityType<?>>> witherling = ForgeRegistries.ENTITY_TYPES.getHolder(setRL("witherling"));
        skeletal_king.ifPresent(king -> {
            event.put((EntityType<? extends LivingEntity>) king.get(), SkeletalKingEntity.registerAttributes().build());
        });
        witherling.ifPresent(minion -> {
            event.put((EntityType<? extends LivingEntity>) minion.get(), WitherlingEntity.registerAttributes()
                    .build());
        });
        //        event.put(ModEntities.DEMONIC_DRAGON.get(), DemonicDragonEntity.registerAttributes().build());
        //        event.put(ModEntities.FROST_WOLF.get(), FrostWolfEntity.registerAttributes().build());
        //        event.put(ModEntities.FROST_WOLF_ALPHA.get(), FrostWolfAlphaEntity.registerAttributes().build());
        //        event.put(ModEntities.BOREAS.get(), BoreasEntity.registerAttributes().build());
        //TODO Fix Mobs
        //                event.validateEntityAttributes();
    }
}
