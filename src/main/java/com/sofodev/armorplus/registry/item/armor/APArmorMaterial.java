package com.sofodev.armorplus.registry.item.armor;

import com.sofodev.armorplus.registry.ModArmorMaterials;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.config;
import static com.sofodev.armorplus.config.ArmorPlusConfig.*;
import static com.sofodev.armorplus.registry.item.armor.APArmorProperties.*;
import static com.sofodev.armorplus.registry.item.extra.Buff.*;
import static net.minecraft.ChatFormatting.*;

public enum APArmorMaterial implements IAPArmor {
    /*Tier 1*/
    COAL(COAL_PROP, GRAY, () -> withBuffs(new BuffInstance(NIGHT_VISION, 0))) {
        @Override
        public MaterialConfig config() {
            return config.coalMaterial();
        }
    },
    REDSTONE(REDSTONE_PROP, DARK_RED, () -> withBuffs(new BuffInstance(HASTE, 1))) {
        @Override
        public MaterialConfig config() {
            return config.redstoneMaterial();
        }
    },
    LAPIS(LAPIS_PROP, DARK_BLUE, () -> withBuffs(new BuffInstance(WATER_BREATHING, 0))) {
        @Override
        public MaterialConfig config() {
            return config.lapisMaterial();
        }
    },
    CHICKEN(CHICKEN_PROP, WHITE, () -> withBuffs(new BuffInstance(SPEED, 3))) {
        @Override
        public MaterialConfig config() {
            return config.chickenMaterial();
        }
    },
    SLIME(SLIME_PROP, GREEN, () -> withBuffs(
            new BuffInstance(JUMP_BOOST, 3),
            new BuffInstance(SLOW_FALLING, 1)
    )) {
        @Override
        public MaterialConfig config() {
            return config.slimeMaterial();
        }
    },

    /*Tier 2*/
    EMERALD(EMERALD_PROP, DARK_GREEN, () -> withBuffs(new BuffInstance(SPEED, 1))) {
        @Override
        public MaterialConfig config() {
            return config.emeraldMaterial();
        }
    },
    OBSIDIAN(OBSIDIAN_PROP, true, DARK_GRAY, () -> withBuffs(new BuffInstance(RESISTANCE, 1))) {
        @Override
        public MaterialConfig config() {
            return config.obsidianMaterial();
        }
    },
    INFUSED_LAVA(INFUSED_LAVA_PROP, true, GOLD, () -> withBuffs(
            new BuffInstance(FIRE_RESISTANCE, 0),
            new BuffInstance(FIRE_EXTINGUISH),
            new BuffInstance(WATER_WEAKNESS)
    )) {
        @Override
        public MaterialConfig config() {
            return config.infusedLavaMaterial();
        }
    },

    /*Tier 3*/
    GUARDIAN(GUARDIAN_PROP, true, BLUE, fromConfig(() -> config.guardianMaterial())) {
        @Override
        public AdvancedMaterialConfig config() {
            return config.guardianMaterial();
        }
    },
    SUPER_STAR(SUPER_STAR_PROP, true, WHITE, fromConfig(() -> config.superStarMaterial())) {
        @Override
        public AdvancedMaterialConfig config() {
            return config.superStarMaterial();
        }
    },
    ENDER_DRAGON(ENDER_DRAGON_PROP, true, DARK_PURPLE, fromConfig(() -> config.enderDragonMaterial())) {
        @Override
        public AdvancedMaterialConfig config() {
            return config.enderDragonMaterial();
        }
    },

    /*Tier TConstruct*/
    ARDITE(ARDITE_PROP, RED),
    COBALT(COBALT_PROP, BLUE),
    KNIGHT_SLIME(KNIGHT_SLIME_PROP, LIGHT_PURPLE),
    PIG_IRON(PIG_IRON_PROP, LIGHT_PURPLE),
    MANYULLYN(MANYULLYN_PROP, DARK_PURPLE),

    /*Tier Slayer*/
    SLAYER(SLAYER_PROP, true, DARK_PURPLE, fromConfig(() -> config.slayerMaterial())) {
        @Override
        public AdvancedMaterialConfig config() {
            return config.slayerMaterial();
        }
    },

    /*Enhanced Vanilla Armor*/
    CHAINMAIL(ENHANCED_CHAINMAIL_PROP, GRAY),
    GOLDEN(ENHANCED_GOLD_PROP, GRAY),
    IRON(ENHANCED_IRON_PROP, GRAY),
    DIAMOND(ENHANCED_DIAMOND_PROP, GRAY),
    NETHERITE(ENHANCED_NETHERITE_PROP, true, GRAY, Collections::emptyList),
    FROST(FROST_PROP, false, BLUE, () -> withBuffs(new BuffInstance(FIRE_WEAKNESS))) {
        @Override
        public MaterialConfig config() {
            return config.frostMaterial();
        }
    },
    FROST_LAVA(FROST_LAVA_PROP, true, YELLOW, () -> withBuffs(new BuffInstance(NATURAL_IMMUNITY))) {
        @Override
        public MaterialConfig config() {
            return config.frostLavaMaterial();
        }
    };

    private final Supplier<Holder<ArmorMaterial>> armor;
    private final boolean isImmuneToFire;
    private final Supplier<List<BuffInstance>> buffs;
    private final net.minecraft.ChatFormatting formatting;

//    APArmorMaterial() {
//        this(ENHANCED_IRON_PROP, false, RESET, Collections::emptyList);
//    }

//    APArmorMaterial(APArmorProperties armor) {
//        this(armor, false, RESET, Collections::emptyList);
//    }

    APArmorMaterial(APArmorProperties armor, net.minecraft.ChatFormatting formatting) {
        this(armor, false, formatting, Collections::emptyList);
    }

    APArmorMaterial(APArmorProperties armor, net.minecraft.ChatFormatting formatting, Supplier<List<BuffInstance>> buffs) {
        this(armor, false, formatting, buffs);
    }

    APArmorMaterial(APArmorProperties armor, boolean isImmuneToFire, net.minecraft.ChatFormatting formatting, Supplier<List<BuffInstance>> buffs) {
        this.armor = ModArmorMaterials.getHolder(armor);
        this.isImmuneToFire = isImmuneToFire;
        this.buffs = buffs;
        this.formatting = formatting;
    }

    private static Supplier<List<BuffInstance>> fromConfig(Supplier<AdvancedMaterialConfig> configSupplier) {
        return () -> {
            AdvancedMaterialConfig config = configSupplier.get();
            if (config == null) return List.of();

            return Arrays.asList(
                    new BuffInstance(config.enableNIGHT_VISION().get(), NIGHT_VISION, config.amplifierNIGHT_VISION().get()),
                    new BuffInstance(config.enableWATER_BREATHING().get(), WATER_BREATHING, config.amplifierWATER_BREATHING().get()),
                    new BuffInstance(config.enableSTRENGTH().get(), STRENGTH, config.amplifierSTRENGTH().get()),
                    new BuffInstance(config.enableSPEED().get(), SPEED, config.amplifierSPEED().get()),
                    new BuffInstance(config.enableHASTE().get(), HASTE, config.amplifierHASTE().get()),
                    new BuffInstance(config.enableJUMP_BOOST().get(), JUMP_BOOST, config.amplifierJUMP_BOOST().get()),
                    new BuffInstance(config.enableREGENERATION().get(), REGENERATION, config.amplifierREGENERATION().get(), 200),
                    new BuffInstance(config.enableRESISTANCE().get(), RESISTANCE, config.amplifierRESISTANCE().get()),
                    new BuffInstance(config.enableFIRE_RESISTANCE().get(), FIRE_RESISTANCE, config.amplifierFIRE_RESISTANCE().get()),
                    new BuffInstance(config.enableSATURATION().get(), SATURATION, config.amplifierSATURATION().get()),
                    new BuffInstance(config.enableINVISIBILITY().get(), INVISIBILITY, config.amplifierINVISIBILITY().get()),
                    new BuffInstance(config.enableHEALTH_BOOST().get(), HEALTH_BOOST, config.amplifierHEALTH_BOOST().get(), 200),
                    new BuffInstance(config.enableABSORPTION().get(), ABSORPTION, config.amplifierABSORPTION().get(), 200),
                    new BuffInstance(config.enableSLOW_FALLING().get(), SLOW_FALLING, config.amplifierSLOW_FALLING().get()),
                    new BuffInstance(config.enableFLIGHT().get(), FLIGHT),
                    new BuffInstance(config.enableWITHER_IMMUNITY().get(), WITHER_IMMUNITY),
                    new BuffInstance(config.enableNATURAL_IMMUNITY().get(), NATURAL_IMMUNITY),
                    new BuffInstance(config.enableFIRE_EXTINGUISH().get(), FIRE_EXTINGUISH)
            );
        };
    }

    private static List<BuffInstance> withBuffs(BuffInstance... buffs) {
        return Arrays.asList(buffs);
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ENGLISH);
    }

    @Override
    public Supplier<Holder<ArmorMaterial>> get() {
        return this.armor;
    }

    @Override
    public Item.Properties getProperties() {
        return new Item.Properties().rarity(Rarity.EPIC);
    }

    @Override
    public boolean isImmuneToFire() {
        return this.isImmuneToFire;
    }

    @Override
    public net.minecraft.ChatFormatting getFormatting() {
        return this.formatting;
    }

    @Override
    public Supplier<List<BuffInstance>> getBuffInstances() {
        return this.buffs;
    }

    @Override
    public IMaterialConfig config() {
        return IAPArmor.super.config();
    }
}
