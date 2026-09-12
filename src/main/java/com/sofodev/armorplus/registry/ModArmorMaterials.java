package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.registry.item.armor.APArmorProperties;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorMaterial;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static net.minecraft.core.registries.Registries.ARMOR_MATERIAL;

public class ModArmorMaterials {

    public static final DeferredRegister<ArmorMaterial> MATERIALS =
            DeferredRegister.create(ARMOR_MATERIAL, MODID);

    private static final Map<APArmorProperties, DeferredHolder<ArmorMaterial, ArmorMaterial>> REGISTRY = new EnumMap<>(APArmorProperties.class);

    private static final Map<APArmorProperties, Supplier<Holder<ArmorMaterial>>> HOLDERS = new EnumMap<>(APArmorProperties.class);

    static {
        for (APArmorProperties prop : APArmorProperties.values()) {
            DeferredHolder<ArmorMaterial, ArmorMaterial> reg = MATERIALS.register(
                    prop.name().toLowerCase().replace("_prop", ""),
                    prop::toMaterial
            );
            REGISTRY.put(prop, reg);

            HOLDERS.put(prop, () -> {
                if (!reg.isBound()) {
                    throw new IllegalStateException("RegistryObject not yet available for " + prop);
                }
                return reg.getDelegate();
            });
        }
    }

    /**
     * Get the RegistryObject for a given enum property
     */
    public static DeferredHolder<ArmorMaterial, ArmorMaterial> getRegistry(APArmorProperties prop) {
        return REGISTRY.get(prop);
    }

    /**
     * Get the Holder<ArmorMaterial> for a given enum property
     */
    public static Supplier<Holder<ArmorMaterial>> getHolder(APArmorProperties prop) {
        return () -> getRegistry(prop).getDelegate();
    }

    /**
     * Get the Holder<ArmorMaterial> for a given enum property (from the HOLDERS map)
     */
    public static Supplier<Holder<ArmorMaterial>> getFromHolders(APArmorProperties prop) {
        Supplier<Holder<ArmorMaterial>> holder = HOLDERS.get(prop);
        if (holder == null) {
            throw new IllegalArgumentException("No holder found for " + prop);
        }
        return holder;
    }
}
