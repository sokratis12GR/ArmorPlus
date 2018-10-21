package net.thedragonteam.armorplus.entity.dungeon.skeletalking.projectile;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.properties.ArmorSet;

import static net.minecraft.init.Items.*;
import static net.minecraft.item.ItemStack.EMPTY;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.util.Utils.emptyArmor;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class Skeleton {

    private static final String WARRIOR = "Warrior";
    private static final double WARRIOR_HEALTH = 23D;
    private static final String ARCHER = "Archer";
    private static final double ARCHER_HEALTH = 18D;
    private static final String PALADIN = "Paladin";
    private static final double PALADIN_HEALTH = 50D;

    //1200-200
    public static final Skeleton WARRIOR_0 = new Skeleton(WARRIOR, 15D, EMPTY, EMPTY, emptyArmor);
    public static final Skeleton WARRIOR_1 = new Skeleton(WARRIOR, WARRIOR_HEALTH, STONE_SWORD, EMPTY, new ArmorSet(LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_LEGGINGS, LEATHER_BOOTS));
    public static final Skeleton WARRIOR_2 = new Skeleton(WARRIOR, WARRIOR_HEALTH + 3, GOLDEN_SWORD, EMPTY, new ArmorSet(GOLDEN_HELMET, GOLDEN_CHESTPLATE, GOLDEN_LEGGINGS, GOLDEN_BOOTS));
    public static final Skeleton WARRIOR_3 = new Skeleton(WARRIOR, WARRIOR_HEALTH + 6, IRON_SWORD, EMPTY, new ArmorSet(IRON_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS));
    public static final Skeleton WARRIOR_4 = new Skeleton(WARRIOR, WARRIOR_HEALTH + 9, DIAMOND_SWORD, EMPTY, new ArmorSet(DIAMOND_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS));
    public static final Skeleton WARRIOR_5 = new Skeleton(WARRIOR, WARRIOR_HEALTH + 12, DIAMOND_AXE, EMPTY, new ArmorSet(DIAMOND_HELMET, DIAMOND_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS));
    //800-200
    public static final Skeleton ARCHER_1 = new Skeleton(ARCHER, ARCHER_HEALTH, BOW, EMPTY, new ArmorSet(LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_LEGGINGS, LEATHER_BOOTS));
    public static final Skeleton ARCHER_2 = new Skeleton(ARCHER, ARCHER_HEALTH + 3, BOW, EMPTY, new ArmorSet(CHAINMAIL_HELMET, CHAINMAIL_CHESTPLATE, CHAINMAIL_LEGGINGS, CHAINMAIL_BOOTS));
    public static final Skeleton ARCHER_3 = new Skeleton(ARCHER, ARCHER_HEALTH + 6, BOW, EMPTY, new ArmorSet(IRON_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS));
    public static final Skeleton ARCHER_4 = new Skeleton(ARCHER, ARCHER_HEALTH + 9, BOW, EMPTY, new ArmorSet(DIAMOND_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS));
    //400-200
    public static final Skeleton PALADIN_1 = new Skeleton(PALADIN, PALADIN_HEALTH, superStarSword, getItemStack(SHIELD), new ArmorSet(DIAMOND_HELMET, DIAMOND_CHESTPLATE, DIAMOND_LEGGINGS, DIAMOND_BOOTS));
    public static final Skeleton PALADIN_2 = new Skeleton(PALADIN, PALADIN_HEALTH + 5, superStarBattleAxe, getItemStack(SHIELD), new ArmorSet(superStarHelmet, superStarChestplate, superStarLeggings, superStarBoots));

    private final String name;
    private final double health;
    private final ItemStack weapon;
    private final ItemStack offHand;
    private final ItemStack[] armor;

    public Skeleton(String name, double health, ItemStack weapon, ItemStack offHand, ItemStack[] armor) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
        this.offHand = offHand;
        this.armor = armor;
    }

    public Skeleton(String name, double health, Item weapon, ItemStack offHand, ArmorSet set) {
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
