package com.sofodev.armorplus.registry.items.armors;

import com.sofodev.armorplus.ArmorPlus;
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
    OBSIDIAN(OBSIDIAN_PROP, DARK_GRAY, () -> withBuffs(
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
            new BuffInstance(WATER_BREATHING, 0)
    )) {
        @Override
        public MaterialConfig config() {
            return guardianMaterial;
        }
    },
    SUPER_STAR(SUPER_STAR_PROP, true, WHITE, () -> withBuffs(
            new BuffInstance(WITHER_IMMUNITY),
            new BuffInstance(REGENERATION, 0),
            new BuffInstance(FIRE_RESISTANCE),
            new BuffInstance(FIRE_EXTINGUISH)
    )) {
        @Override
        public MaterialConfig config() {
            return superStarMaterial;
        }
    },
    ENDER_DRAGON(ENDER_DRAGON_PROP, true, DARK_PURPLE, () -> withBuffs(
            new BuffInstance(WITHER_IMMUNITY),
            new BuffInstance(FLIGHT),
            new BuffInstance(SLOW_FALLING, 0)
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
            new BuffInstance(FIRE_RESISTANCE, 0),
            new BuffInstance(WITHER_IMMUNITY),
            new BuffInstance(FLIGHT),
            new BuffInstance(WATER_BREATHING, 0),
            new BuffInstance(SLOW_FALLING, 0),
            new BuffInstance(FIRE_EXTINGUISH)
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
        return new Item.Properties().tab(ArmorPlus.AP_GROUP).rarity(Rarity.create(this.getName(), this.getFormatting()));
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