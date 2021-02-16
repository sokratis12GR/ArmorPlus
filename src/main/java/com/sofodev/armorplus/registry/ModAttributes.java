package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.entities.bosses.DemonicDragonEntity;
import com.sofodev.armorplus.registry.entities.bosses.SkeletalKingEntity;
import com.sofodev.armorplus.registry.entities.bosses.WitherlingEntity;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModAttributes {

    public static void registerAttributes() {
        GlobalEntityTypeAttributes.put(ModEntities.SKELETAL_KING.get(), SkeletalKingEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ModEntities.WITHERLING.get(), WitherlingEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ModEntities.DEMONIC_DRAGON.get(), DemonicDragonEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.validateEntityAttributes();
    }
}
