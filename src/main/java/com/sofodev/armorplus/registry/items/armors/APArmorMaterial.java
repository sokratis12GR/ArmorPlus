package com.sofodev.armorplus.registry.items.armors;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.items.extras.BuffInstance;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

import static com.sofodev.armorplus.config.ArmorPlusConfig.*;
import static com.sofodev.armorplus.registry.items.armors.APArmorProperties.*;
import static com.sofodev.armorplus.registry.items.extras.Buff.*;
import static net.minecraft.ChatFormatting.*;

/**
 * Created by Sokratis Fotkatzikis (2016-2020)
 * <p>
 * This is essentially the "MASTER" class of each armor set.
 * Here we combine all of our code into a nicely organized "data" set.
 * <p>
 * Simple creation of new equipment types, which can contain many buffs, de-buffs and etc.
 */
public enum APArmorMaterial implements IAPArmor {
    /*Tier 1*/
    COAL(COAL_PROP, GRAY, () -> withBuffs(
            new BuffInstance(NIGHT_VISION, 0)
    )) {
        @Override
        public MaterialConfig config() {
            return coalMaterial;
        }
    },
    REDSTONE(REDSTONE_PROP, DARK_RED, () -> withBuffs(
            new BuffInstance(HASTE, 1)
    )) {
        @Override
        public MaterialConfig config() {
            return redstoneMaterial;
        }
    },
    LAPIS(LAPIS_PROP, DARK_BLUE, () -> withBuffs(
            new BuffInstance(WATER_BREATHING, 0)
    )) {
        @Override
        public MaterialConfig config() {
            return lapisMaterial;
        }
    },
    CHICKEN(CHICKEN_PROP, WHITE, () -> withBuffs(
            new BuffInstance(SPEED, 3)
    )) {
        @Override
        public MaterialConfig config() {
            return chickenMaterial;
        }
    },
    SLIME(SLIME_PROP, GREEN, () -> withBuffs(
            new BuffInstance(JUMP_BOOST, 3),
            new BuffInstance(SLOW_FALLING, 1)
    )) {
        @Override
        public ArmorPlusConfig.MaterialConfig config() {
            return slimeMaterial;
        }
    },
    /*Tier 2*/
    EMERALD(EMERALD_PROP, DARK_GREEN, () -> withBuffs(
            new BuffInstance(SPEED, 1)
    )) {
        @Override
        public MaterialConfig config() {
            return emeraldMaterial;
        }
    },
    OBSIDIAN(OBSIDIAN_PROP, true, DARK_GRAY, () -> withBuffs(
            new BuffInstance(RESISTANCE, 1)
    )) {
        @Override
        public ArmorPlusConfig.MaterialConfig config() {
            return obsidianMaterial;
        }
    },
    INFUSED_LAVA(INFUSED_LAVA_PROP, true, GOLD, () -> withBuffs(
            new BuffInstance(FIRE_RESISTANCE, 0),
            new BuffInstance(FIRE_EXTINGUISH),
            new BuffInstance(WATER_WEAKNESS)
    )) {
        @Override
        public MaterialConfig config() {
            return infusedLavaMaterial;
        }
    },
    /*Tier 3*/
    GUARDIAN(GUARDIAN_PROP, true, BLUE, () -> withBuffs(
            new BuffInstance(guardianMaterial.enableNIGHT_VISION.get(), NIGHT_VISION, guardianMaterial.amplifierNIGHT_VISION.get()),
            new BuffInstance(guardianMaterial.enableWATER_BREATHING.get(), WATER_BREATHING, guardianMaterial.amplifierWATER_BREATHING.get()),
            new BuffInstance(guardianMaterial.enableSTRENGTH.get(), STRENGTH, guardianMaterial.amplifierSTRENGTH.get()),
            new BuffInstance(guardianMaterial.enableSPEED.get(), SPEED, guardianMaterial.amplifierSPEED.get()),
            new BuffInstance(guardianMaterial.enableHASTE.get(), HASTE, guardianMaterial.amplifierHASTE.get()),
            new BuffInstance(guardianMaterial.enableJUMP_BOOST.get(), JUMP_BOOST, guardianMaterial.amplifierJUMP_BOOST.get()),
            new BuffInstance(guardianMaterial.enableREGENERATION.get(), REGENERATION, guardianMaterial.amplifierREGENERATION.get(), 200),
            new BuffInstance(guardianMaterial.enableRESISTANCE.get(), RESISTANCE, guardianMaterial.amplifierRESISTANCE.get()),
            new BuffInstance(guardianMaterial.enableFIRE_RESISTANCE.get(), FIRE_RESISTANCE, guardianMaterial.amplifierFIRE_RESISTANCE.get()),
            new BuffInstance(guardianMaterial.enableSATURATION.get(), SATURATION, guardianMaterial.amplifierSATURATION.get()),
            new BuffInstance(guardianMaterial.enableINVISIBILITY.get(), INVISIBILITY, guardianMaterial.amplifierINVISIBILITY.get()),
            new BuffInstance(guardianMaterial.enableHEALTH_BOOST.get(), HEALTH_BOOST, guardianMaterial.amplifierHEALTH_BOOST.get(), 200),
            new BuffInstance(guardianMaterial.enableABSORPTION.get(), ABSORPTION, guardianMaterial.amplifierABSORPTION.get(), 200),
            new BuffInstance(guardianMaterial.enableSLOW_FALLING.get(), SLOW_FALLING, guardianMaterial.amplifierSLOW_FALLING.get()),
            new BuffInstance(guardianMaterial.enableFLIGHT.get(), FLIGHT),
            new BuffInstance(guardianMaterial.enableWITHER_IMMUNITY.get(), WITHER_IMMUNITY),
            new BuffInstance(guardianMaterial.enableNATURAL_IMMUNITY.get(), NATURAL_IMMUNITY),
            new BuffInstance(guardianMaterial.enableFIRE_EXTINGUISH.get(), FIRE_EXTINGUISH))) {
        @Override
        public MaterialConfig config() {
            return guardianMaterial;
        }
    },
    SUPER_STAR(SUPER_STAR_PROP, true, WHITE, () -> withBuffs(
            new BuffInstance(superStarMaterial.enableNIGHT_VISION.get(), NIGHT_VISION, superStarMaterial.amplifierNIGHT_VISION.get()),
            new BuffInstance(superStarMaterial.enableWATER_BREATHING.get(), WATER_BREATHING, superStarMaterial.amplifierWATER_BREATHING.get()),
            new BuffInstance(superStarMaterial.enableSTRENGTH.get(), STRENGTH, superStarMaterial.amplifierSTRENGTH.get()),
            new BuffInstance(superStarMaterial.enableSPEED.get(), SPEED, superStarMaterial.amplifierSPEED.get()),
            new BuffInstance(superStarMaterial.enableHASTE.get(), HASTE, superStarMaterial.amplifierHASTE.get()),
            new BuffInstance(superStarMaterial.enableJUMP_BOOST.get(), JUMP_BOOST, superStarMaterial.amplifierJUMP_BOOST.get()),
            new BuffInstance(superStarMaterial.enableREGENERATION.get(), REGENERATION, superStarMaterial.amplifierREGENERATION.get(), 200),
            new BuffInstance(superStarMaterial.enableRESISTANCE.get(), RESISTANCE, superStarMaterial.amplifierRESISTANCE.get()),
            new BuffInstance(superStarMaterial.enableFIRE_RESISTANCE.get(), FIRE_RESISTANCE, superStarMaterial.amplifierFIRE_RESISTANCE.get()),
            new BuffInstance(superStarMaterial.enableSATURATION.get(), SATURATION, superStarMaterial.amplifierSATURATION.get()),
            new BuffInstance(superStarMaterial.enableINVISIBILITY.get(), INVISIBILITY, superStarMaterial.amplifierINVISIBILITY.get()),
            new BuffInstance(superStarMaterial.enableHEALTH_BOOST.get(), HEALTH_BOOST, superStarMaterial.amplifierHEALTH_BOOST.get(), 200),
            new BuffInstance(superStarMaterial.enableABSORPTION.get(), ABSORPTION, superStarMaterial.amplifierABSORPTION.get(), 200),
            new BuffInstance(superStarMaterial.enableSLOW_FALLING.get(), SLOW_FALLING, superStarMaterial.amplifierSLOW_FALLING.get()),
            new BuffInstance(superStarMaterial.enableFLIGHT.get(), FLIGHT),
            new BuffInstance(superStarMaterial.enableWITHER_IMMUNITY.get(), WITHER_IMMUNITY),
            new BuffInstance(superStarMaterial.enableNATURAL_IMMUNITY.get(), NATURAL_IMMUNITY),
            new BuffInstance(superStarMaterial.enableFIRE_EXTINGUISH.get(), FIRE_EXTINGUISH)
    )) {
        @Override
        public MaterialConfig config() {
            return superStarMaterial;
        }
    },
    ENDER_DRAGON(ENDER_DRAGON_PROP, true, DARK_PURPLE, () -> withBuffs(
            new BuffInstance(enderDragonMaterial.enableNIGHT_VISION.get(), NIGHT_VISION, enderDragonMaterial.amplifierNIGHT_VISION.get()),
            new BuffInstance(enderDragonMaterial.enableWATER_BREATHING.get(), WATER_BREATHING, enderDragonMaterial.amplifierWATER_BREATHING.get()),
            new BuffInstance(enderDragonMaterial.enableSTRENGTH.get(), STRENGTH, enderDragonMaterial.amplifierSTRENGTH.get()),
            new BuffInstance(enderDragonMaterial.enableSPEED.get(), SPEED, enderDragonMaterial.amplifierSPEED.get()),
            new BuffInstance(enderDragonMaterial.enableHASTE.get(), HASTE, enderDragonMaterial.amplifierHASTE.get()),
            new BuffInstance(enderDragonMaterial.enableJUMP_BOOST.get(), JUMP_BOOST, enderDragonMaterial.amplifierJUMP_BOOST.get()),
            new BuffInstance(enderDragonMaterial.enableREGENERATION.get(), REGENERATION, enderDragonMaterial.amplifierREGENERATION.get(), 200),
            new BuffInstance(enderDragonMaterial.enableRESISTANCE.get(), RESISTANCE, enderDragonMaterial.amplifierRESISTANCE.get()),
            new BuffInstance(enderDragonMaterial.enableFIRE_RESISTANCE.get(), FIRE_RESISTANCE, enderDragonMaterial.amplifierFIRE_RESISTANCE.get()),
            new BuffInstance(enderDragonMaterial.enableSATURATION.get(), SATURATION, enderDragonMaterial.amplifierSATURATION.get()),
            new BuffInstance(enderDragonMaterial.enableINVISIBILITY.get(), INVISIBILITY, enderDragonMaterial.amplifierINVISIBILITY.get()),
            new BuffInstance(enderDragonMaterial.enableHEALTH_BOOST.get(), HEALTH_BOOST, enderDragonMaterial.amplifierHEALTH_BOOST.get(), 200),
            new BuffInstance(enderDragonMaterial.enableABSORPTION.get(), ABSORPTION, enderDragonMaterial.amplifierABSORPTION.get(), 200),
            new BuffInstance(enderDragonMaterial.enableSLOW_FALLING.get(), SLOW_FALLING, enderDragonMaterial.amplifierSLOW_FALLING.get()),
            new BuffInstance(enderDragonMaterial.enableFLIGHT.get(), FLIGHT),
            new BuffInstance(enderDragonMaterial.enableWITHER_IMMUNITY.get(), WITHER_IMMUNITY),
            new BuffInstance(enderDragonMaterial.enableNATURAL_IMMUNITY.get(), NATURAL_IMMUNITY),
            new BuffInstance(enderDragonMaterial.enableFIRE_EXTINGUISH.get(), FIRE_EXTINGUISH)
    )) {
        @Override
        public MaterialConfig config() {
            return enderDragonMaterial;
        }
    },
    /*Tier TConstruct*/
    ARDITE(ARDITE_PROP, RED),
    COBALT(COBALT_PROP, BLUE),
    KNIGHT_SLIME(KNIGHT_SLIME_PROP, LIGHT_PURPLE),
    PIG_IRON(PIG_IRON_PROP, LIGHT_PURPLE),
    MANYULLYN(MANYULLYN_PROP, DARK_PURPLE),
    /*Tier Slayer*/
    SLAYER(SLAYER_PROP, true, DARK_PURPLE, () -> withBuffs(
            new BuffInstance(slayerMaterial.enableNIGHT_VISION.get(), NIGHT_VISION, slayerMaterial.amplifierNIGHT_VISION.get()),
            new BuffInstance(slayerMaterial.enableWATER_BREATHING.get(), WATER_BREATHING, slayerMaterial.amplifierWATER_BREATHING.get()),
            new BuffInstance(slayerMaterial.enableSTRENGTH.get(), STRENGTH, slayerMaterial.amplifierSTRENGTH.get()),
            new BuffInstance(slayerMaterial.enableSPEED.get(), SPEED, slayerMaterial.amplifierSPEED.get()),
            new BuffInstance(slayerMaterial.enableHASTE.get(), HASTE, slayerMaterial.amplifierHASTE.get()),
            new BuffInstance(slayerMaterial.enableJUMP_BOOST.get(), JUMP_BOOST, slayerMaterial.amplifierJUMP_BOOST.get()),
            new BuffInstance(slayerMaterial.enableREGENERATION.get(), REGENERATION, slayerMaterial.amplifierREGENERATION.get(), 200),
            new BuffInstance(slayerMaterial.enableRESISTANCE.get(), RESISTANCE, slayerMaterial.amplifierRESISTANCE.get()),
            new BuffInstance(slayerMaterial.enableFIRE_RESISTANCE.get(), FIRE_RESISTANCE, slayerMaterial.amplifierFIRE_RESISTANCE.get()),
            new BuffInstance(slayerMaterial.enableSATURATION.get(), SATURATION, slayerMaterial.amplifierSATURATION.get()),
            new BuffInstance(slayerMaterial.enableINVISIBILITY.get(), INVISIBILITY, slayerMaterial.amplifierINVISIBILITY.get()),
            new BuffInstance(slayerMaterial.enableHEALTH_BOOST.get(), HEALTH_BOOST, slayerMaterial.amplifierHEALTH_BOOST.get(), 200),
            new BuffInstance(slayerMaterial.enableABSORPTION.get(), ABSORPTION, slayerMaterial.amplifierABSORPTION.get(), 200),
            new BuffInstance(slayerMaterial.enableSLOW_FALLING.get(), SLOW_FALLING, slayerMaterial.amplifierSLOW_FALLING.get()),
            new BuffInstance(slayerMaterial.enableFLIGHT.get(), FLIGHT),
            new BuffInstance(slayerMaterial.enableWITHER_IMMUNITY.get(), WITHER_IMMUNITY),
            new BuffInstance(slayerMaterial.enableNATURAL_IMMUNITY.get(), NATURAL_IMMUNITY),
            new BuffInstance(slayerMaterial.enableFIRE_EXTINGUISH.get(), FIRE_EXTINGUISH)
    )) {
        @Override
        public MaterialConfig config() {
            return slayerMaterial;
        }
    },
    /*Enhanced Vanilla Armor*/
    CHAINMAIL(ENHANCED_CHAINMAIL_PROP, GRAY),
    GOLDEN(ENHANCED_GOLD_PROP, GRAY),
    IRON(ENHANCED_IRON_PROP, GRAY),
    DIAMOND(ENHANCED_DIAMOND_PROP, GRAY),
    NETHERITE(ENHANCED_NETHERITE_PROP, true, GRAY, Collections::emptyList),
    FROST(FROST_PROP, false, BLUE, () -> withBuffs(
            new BuffInstance(FIRE_WEAKNESS)
    )) {
        @Override
        public MaterialConfig config() {
            return frostMaterial;
        }
    },
    FROST_LAVA(FROST_LAVA_PROP, true, YELLOW, () -> withBuffs(
            new BuffInstance(NATURAL_IMMUNITY)
    )) {
        @Override
        public MaterialConfig config() {
            return frostLavaMaterial;
        }
    };

    private final ArmorMaterial armor;
    private final boolean isImmuneToFire;
    private final Supplier<List<BuffInstance>> buffs;
    private final ChatFormatting formatting;

    APArmorMaterial() {
        this(ArmorMaterials.IRON, false, RESET, Collections::emptyList);
    }

    APArmorMaterial(ArmorMaterial armor) {
        this(armor, false, RESET, Collections::emptyList);
    }

    APArmorMaterial(ArmorMaterial armor, ChatFormatting formatting) {
        this(armor, false, formatting, Collections::emptyList);
    }

    APArmorMaterial(ArmorMaterial armor, ChatFormatting formatting, Supplier<List<BuffInstance>> buffs) {
        this(armor, false, formatting, buffs);
    }

    APArmorMaterial(ArmorMaterial armor, boolean isImmuneToFire, ChatFormatting formatting, Supplier<List<BuffInstance>> buffs) {
        this.armor = armor;
        this.isImmuneToFire = isImmuneToFire;
        this.buffs = buffs;
        this.formatting = formatting;
    }

    private static List<BuffInstance> withBuffs(BuffInstance... buffs) {
        return Arrays.asList(buffs);
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ENGLISH);
    }

    @Override
    public ArmorMaterial get() {
        return this.armor;
    }

    @Override
    public Item.Properties getProperties() {
        return new Item.Properties().rarity(Rarity.create(this.getName(), this.getFormatting()));
    }

    @Override
    public boolean isImmuneToFire() {
        return this.isImmuneToFire;
    }

    @Override
    public ChatFormatting getFormatting() {
        return this.formatting;
    }

    @Override
    public Supplier<List<BuffInstance>> getBuffInstances() {
        return this.buffs;
    }

    @Override
    public MaterialConfig config() {
        return IAPArmor.super.config();
    }

    @Override
    public String toString() {
        return "APArmorMaterial{" +
                "armor=" + armor +
                ", isImmuneToFire=" + isImmuneToFire +
                ", buffs=" + buffs +
                ", formatting=" + formatting +
                '}';
    }
}