//package com.sofodev.armorplus.registry;
//
//import com.sofodev.armorplus.registry.features.APOreFeature;
//import com.sofodev.armorplus.registry.features.APOreFeatureConfig;
//import net.minecraft.world.level.levelgen.feature.Feature;
//import net.minecraft.world.level.levelgen.feature.OreFeature;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
//
//import java.util.function.Supplier;
//
//import static com.sofodev.armorplus.ArmorPlus.MODID;
//
//@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
//public class ModFeatures {
//
//    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, MODID);
//
//    //Features
//    public static final RegistryObject<Feature<APOreFeatureConfig>> ORE_CONFIG = createFeature("ap_ore_feature",
//            () -> new OreFeature(APOreFeatureConfig.CODEC));
//
//    public static <F extends Feature<?>> RegistryObject<F> createFeature(String name, Supplier<F> feature) {
//        return FEATURES.register(name, feature);
//    }
//}
