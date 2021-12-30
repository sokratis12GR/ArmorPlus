package com.sofodev.armorplus.registry.entities.bosses.data;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static com.sofodev.armorplus.utils.Utils.*;
import static net.minecraft.world.item.ItemStack.EMPTY;
import static net.minecraft.world.item.Items.*;

/**
 * @author Sokratis Fotkatzikis
 */
public class MinionType {

    private static final String WARRIOR = "Warrior";
    //1200-200
    public static final MinionType WARRIOR_0 = new MinionType(WARRIOR, 15D, EMPTY, EMPTY, emptyArmor);
    private static final double WARRIOR_HEALTH = 23D;
    public static final MinionType WARRIOR_1 = new MinionType(WARRIOR, WARRIOR_HEALTH, STONE_SWORD, EMPTY, new ArmorSet(LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_LEGGINGS, LEATHER_BOOTS));
    public static final MinionType WARRIOR_2 = new MinionType(WARRIOR, WARRIOR_HEALTH + 3, GOLDEN_SWORD, EMPTY, new ArmorSet(GOLDEN_HELMET, GOLDEN_CHESTPLATE, GOLDEN_LEGGINGS, GOLDEN_BOOTS));
    public static final MinionType WARRIOR_3 = new MinionType(WARRIOR, WARRIOR_HEALTH + 6, IRON_SWORD, EMPTY, new ArmorSet(IRON_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS));
    public static final MinionType WARRIOR_4 = new MinionType(WARRIOR, WARRIOR_HEALTH + 9, DIAMOND_SWORD, EMPTY, new ArmorSet(DIAMOND_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS));
    public static final MinionType WARRIOR_5 = new MinionType(WARRIOR, WARRIOR_HEALTH + 12, DIAMOND_AXE, EMPTY, new ArmorSet(DIAMOND_HELMET, DIAMOND_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS));
    private static final String ARCHER = "Archer";
    private static final double ARCHER_HEALTH = 18D;
    //800-200
    public static final MinionType ARCHER_1 = new MinionType(ARCHER, ARCHER_HEALTH, BOW, EMPTY, new ArmorSet(LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_LEGGINGS, LEATHER_BOOTS));
    public static final MinionType ARCHER_2 = new MinionType(ARCHER, ARCHER_HEALTH + 3, BOW, EMPTY, new ArmorSet(CHAINMAIL_HELMET, CHAINMAIL_CHESTPLATE, CHAINMAIL_LEGGINGS, CHAINMAIL_BOOTS));
    public static final MinionType ARCHER_3 = new MinionType(ARCHER, ARCHER_HEALTH + 6, BOW, EMPTY, new ArmorSet(IRON_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS));
    public static final MinionType ARCHER_4 = new MinionType(ARCHER, ARCHER_HEALTH + 9, BOW, EMPTY, new ArmorSet(DIAMOND_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS));
    private static final String PALADIN = "Paladin";
    private static final double PALADIN_HEALTH = 50D;
    //400-200
    public static final MinionType PALADIN_1 = new MinionType(PALADIN, PALADIN_HEALTH, getAPItem("super_star_sword"), getItemStack(SHIELD), new ArmorSet(DIAMOND_HELMET, DIAMOND_CHESTPLATE, DIAMOND_LEGGINGS, DIAMOND_BOOTS));
    public static final MinionType PALADIN_2 = new MinionType(PALADIN, PALADIN_HEALTH + 5, getAPItem("super_star_battle_axe"), getItemStack(SHIELD),
            new ArmorSet("super_star_helmet", "super_star_chestplate", "super_star_leggings", "super_star_boots"));

    private final String name;
    private final double health;
    private final ItemStack weapon;
    private final ItemStack offHand;
    private final ItemStack[] armor;

    public MinionType(String name, double health, ItemStack weapon, ItemStack offHand, ItemStack[] armor) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
        this.offHand = offHand;
        this.armor = armor;
    }

    public MinionType(String name, double health, Item weapon, ItemStack offHand, ArmorSet set) {
        this(name, health, getItemStack(weapon), offHand, set.getSet());
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public ItemStack getWeapon() {
        return weapon.isEmpty() ? EMPTY : weapon;
    }

    public ItemStack getOffHand() {
        return offHand.isEmpty() ? EMPTY : offHand;
    }

    public ItemStack[] getArmor() {
        return armor;
    }
}