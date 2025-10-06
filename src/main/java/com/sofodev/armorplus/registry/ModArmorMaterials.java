package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.registry.item.armor.APArmorProperties;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static net.minecraft.core.registries.Registries.ARMOR_MATERIAL;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModArmorMaterials {

    public static final DeferredRegister<ArmorMaterial> MATERIALS =
            DeferredRegister.create(ARMOR_MATERIAL, MODID);

    private static final Map<APArmorProperties, RegistryObject<ArmorMaterial>> REGISTRY = new EnumMap<>(APArmorProperties.class);

    private static final Map<APArmorProperties, Supplier<Holder<ArmorMaterial>>> HOLDERS = new EnumMap<>(APArmorProperties.class);

    static {
        for (APArmorProperties prop : APArmorProperties.values()) {
            RegistryObject<ArmorMaterial> reg = MATERIALS.register(
                    prop.name().toLowerCase().replace("_prop", ""),
                    prop::toMaterial
            );
            REGISTRY.put(prop, reg);

            HOLDERS.put(prop, () -> {
                if (!reg.isPresent()) {
                    throw new IllegalStateException("RegistryObject not yet available for " + prop);
                }
                return reg.getHolder().orElseThrow();
            });
        }
    }

    /**
     * Get the RegistryObject for a given enum property
     */
    public static RegistryObject<ArmorMaterial> getRegistry(APArmorProperties prop) {
        return REGISTRY.get(prop);
    }

    /**
     * Get the Holder<ArmorMaterial> for a given enum property
     */
    public static Supplier<Holder<ArmorMaterial>> getHolder(APArmorProperties prop) {
        return () -> getRegistry(prop).getHolder().orElseThrow();
    }
}
