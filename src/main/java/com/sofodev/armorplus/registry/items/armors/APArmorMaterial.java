package com.sofodev.armorplus.registry.items.armors;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.extras.BuffInstance;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

import static com.sofodev.armorplus.registry.items.armors.APArmorProperties.*;
import static com.sofodev.armorplus.registry.items.extras.Buff.*;
import static net.minecraft.util.text.TextFormatting.*;

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
    )),
    REDSTONE(REDSTONE_PROP, DARK_RED, () -> withBuffs(
            new BuffInstance(HASTE, 1)
    )),
    LAPIS(LAPIS_PROP, DARK_BLUE, () -> withBuffs(
            new BuffInstance(WATER_BREATHING, 0)
    )),
    CHICKEN(CHICKEN_PROP, WHITE, () -> withBuffs(
            new BuffInstance(SPEED, 3)
    )),
    SLIME(SLIME_PROP, GREEN, () -> withBuffs(
            new BuffInstance(JUMP_BOOST, 3),
            new BuffInstance(SLOW_FALLING, 1)
    )),
    /*Tier 2*/
    EMERALD(EMERALD_PROP, DARK_GREEN, () -> withBuffs(
            new BuffInstance(SPEED, 1)
    )),
    OBSIDIAN(OBSIDIAN_PROP, DARK_GRAY, () -> withBuffs(
            new BuffInstance(RESISTANCE, 1)
    )),
    INFUSED_LAVA(INFUSED_LAVA_PROP, true, GOLD, () -> withBuffs(
            new BuffInstance(FIRE_RESISTANCE, 0),
            new BuffInstance(FIRE_EXTINGUISH),
            new BuffInstance(WATER_WEAKNESS)
    )),
    /*Tier 3*/
    GUARDIAN(GUARDIAN_PROP, true, BLUE, () -> withBuffs(
            new BuffInstance(WATER_BREATHING, 0)
    )),
    SUPER_STAR(SUPER_STAR_PROP, true, WHITE, () -> withBuffs(
            new BuffInstance(WITHER_IMMUNITY),
            new BuffInstance(REGENERATION, 0),
            new BuffInstance(FIRE_RESISTANCE),
            new BuffInstance(FIRE_EXTINGUISH)
    )),
    ENDER_DRAGON(ENDER_DRAGON_PROP, true, DARK_PURPLE, () -> withBuffs(
            new BuffInstance(WITHER_IMMUNITY),
            new BuffInstance(FLIGHT),
            new BuffInstance(SLOW_FALLING, 0)
    )),
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
    )),
    /*Enhanced Vanilla Armor*/
    CHAINMAIL(ENHANCED_CHAINMAIL_PROP, GRAY),
    GOLDEN(ENHANCED_GOLD_PROP, GRAY),
    IRON(ENHANCED_IRON_PROP, GRAY),
    DIAMOND(ENHANCED_DIAMOND_PROP, GRAY),
    NETHERITE(ENHANCED_NETHERITE_PROP, true, GRAY, Collections::emptyList),
    FROST(FROST_PROP, false, BLUE, () -> withBuffs(
            new BuffInstance(FIRE_WEAKNESS)
    )),
    FROST_LAVA(FROST_LAVA_PROP, true, YELLOW, () -> withBuffs(
            new BuffInstance(NATURAL_IMMUNITY)
    )) {
    };

    private final IArmorMaterial armor;
    private final boolean isImmuneToFire;
    private final Supplier<List<BuffInstance>> buffs;
    private final TextFormatting formatting;

    APArmorMaterial() {
        this(ArmorMaterial.IRON, false, RESET, Collections::emptyList);
    }

    APArmorMaterial(IArmorMaterial armor) {
        this(armor, false, RESET, Collections::emptyList);
    }

    APArmorMaterial(IArmorMaterial armor, TextFormatting formatting) {
        this(armor, false, formatting, Collections::emptyList);
    }

    APArmorMaterial(IArmorMaterial armor, TextFormatting formatting, Supplier<List<BuffInstance>> buffs) {
        this(armor, false, formatting, buffs);
    }

    APArmorMaterial(IArmorMaterial armor, boolean isImmuneToFire, TextFormatting formatting, Supplier<List<BuffInstance>> buffs) {
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
    public IArmorMaterial get() {
        return this.armor;
    }

    @Override
    public Properties getProperties() {
        return new Properties().tab(ArmorPlus.AP_GROUP).rarity(Rarity.create(this.getName(), this.getFormatting()));
    }

    @Override
    public boolean isImmuneToFire() {
        return this.isImmuneToFire;
    }

    @Override
    public TextFormatting getFormatting() {
        return this.formatting;
    }

    @Override
    public Supplier<List<BuffInstance>> getBuffInstances() {
        return this.buffs;
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