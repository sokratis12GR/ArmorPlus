package sokratis12GR.ArmorPlus.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;
import sokratis12GR.ArmorPlus.ArmorPlus;
import sokratis12GR.ArmorPlus.armors.origin.*;
import sokratis12GR.ArmorPlus.armors.reinforced.RDArmor;
import sokratis12GR.ArmorPlus.armors.special.EnderDragonArmor;
import sokratis12GR.ArmorPlus.armors.special.GuardianArmor;
import sokratis12GR.ArmorPlus.armors.special.SuperStarArmor;
import sokratis12GR.ArmorPlus.armors.special.TheUltimateArmor;
import sokratis12GR.ArmorPlus.armors.special.mob.ChickenArmor;
import sokratis12GR.ArmorPlus.armors.special.mob.SlimeArmor;
import sokratis12GR.ArmorPlus.armors.tconstruct.*;
import sokratis12GR.ArmorPlus.registry.ModItems;

import java.util.ArrayList;
import java.util.List;

public class ARPAchievements {
    public static AchievementPage ARP_ACHIEVEMENT_PAGE;

    public static Achievement CRAFT_COAL_ARMOR;//0,0
    public static Achievement CRAFT_LAPIS_ARMOR;//0,2
    public static Achievement CRAFT_REDSTONE_ARMOR;//0,-2
    public static Achievement CRAFT_EMERALD_ARMOR;//0,-4
    public static Achievement CRAFT_OBSIDIAN_ARMOR;//2,0
    public static Achievement CRAFT_LAVA_ARMOR;//4,0
    public static Achievement CRAFT_SUPER_STAR_ARMOR;//6,0
    public static Achievement CRAFT_ENDER_DRAGON_ARMOR;//8,0
    public static Achievement CRAFT_GUARDIAN_ARMOR;//0,4
    public static Achievement CRAFT_THE_ULTIMATE_ARMOR;//10,0
    public static Achievement CRAFT_REINFORCED_ARMOR;//2,-2
    public static Achievement CRAFT_SLIME_ARMOR;//2,4
    public static Achievement CRAFT_CHICKEN_ARMOR;//2,2
    /**
     * Tinkers' Construct
     */
    public static Achievement CRAFT_COBALT_ARMOR;//-2,0
    public static Achievement CRAFT_ARDITE_ARMOR;//-4,0
    public static Achievement CRAFT_MANYULLYN_ARMOR;//-6,0
    public static Achievement CRAFT_PIG_IRON_ARMOR;//-2,-2
    public static Achievement CRAFT_KNIGHT_SLIME_ARMOR;//-2,2

    /**
     * ArmorPlus Book
     */
    public static Achievement CRAFT_ANY_ARMOR;

    public static void init() {
        CRAFT_ANY_ARMOR = new AchievementARP("craftAnyArmor", -2, -2, ModItems.ARMORPLUS_BOOK, AchievementList.OPEN_INVENTORY).setNormalCrafting().setSpecial();
        /** Center */
        CRAFT_COAL_ARMOR = new AchievementARP("craftCoalArmor", 0, 0, CoalArmor.helmet, AchievementList.OPEN_INVENTORY).setNormalCrafting();
        /** Bottom-Right */
        CRAFT_CHICKEN_ARMOR = new AchievementARP("craftChickenArmor", 2, 2, ChickenArmor.boots, CRAFT_COAL_ARMOR).setNormalCrafting();
        CRAFT_SLIME_ARMOR = new AchievementARP("craftSlimeArmor", 2, 4, SlimeArmor.boots, CRAFT_COAL_ARMOR).setNormalCrafting();
        /** Bottom */
        CRAFT_LAPIS_ARMOR = new AchievementARP("craftLapisArmor", -2, 2, LapisArmor.helmet, CRAFT_COAL_ARMOR).setNormalCrafting();
        CRAFT_GUARDIAN_ARMOR = new AchievementARP("craftGuardianArmor", -2, 4, GuardianArmor.helmet, CRAFT_LAPIS_ARMOR).setNormalCrafting();
        /** Top */
        CRAFT_REDSTONE_ARMOR = new AchievementARP("craftRedstoneArmor", 0, -4, RedstoneArmor.boots, CRAFT_COAL_ARMOR).setNormalCrafting();
        CRAFT_EMERALD_ARMOR = new AchievementARP("craftEmeraldArmor", 0, -6, EmeraldArmor.chestplate, CRAFT_REDSTONE_ARMOR).setNormalCrafting();
        /** Top-Right */
        CRAFT_REINFORCED_ARMOR = new AchievementARP("craftReinforcedArmor", 2, -2, RDArmor.chestplate, CRAFT_COAL_ARMOR).setNormalCrafting();
        /** Right */
        CRAFT_OBSIDIAN_ARMOR = new AchievementARP("craftObsidianArmor", 4, 0, ObsidianArmor.chestplate, CRAFT_COAL_ARMOR).setNormalCrafting();
        CRAFT_LAVA_ARMOR = new AchievementARP("craftLavaArmor", 6, 0, LavaArmor.chestplate, CRAFT_OBSIDIAN_ARMOR).setNormalCrafting();
        CRAFT_SUPER_STAR_ARMOR = new AchievementARP("craftSuperStarArmor", 8, 0, SuperStarArmor.chestplate, CRAFT_LAVA_ARMOR).setNormalCrafting();
        CRAFT_ENDER_DRAGON_ARMOR = new AchievementARP("craftEnderDragonArmor", 8, 2, EnderDragonArmor.chestplate, AchievementList.THE_END2).setNormalCrafting();
        CRAFT_THE_ULTIMATE_ARMOR = new AchievementARP("craftTheUltimateArmor", 6, 4, TheUltimateArmor.chestplate, CRAFT_ENDER_DRAGON_ARMOR).setNormalCrafting().setSpecial();
        /** Top-Left */
        /** Left */
        /** Tinkers' Construct */
        CRAFT_COBALT_ARMOR = new AchievementARP("craftCobaltArmor", -4, 0, CobaltArmor.chestplate, CRAFT_COAL_ARMOR).setNormalCrafting();
        CRAFT_ARDITE_ARMOR = new AchievementARP("craftArditeArmor", -6, 0, ArditeArmor.chestplate, CRAFT_COBALT_ARMOR).setNormalCrafting();
        CRAFT_MANYULLYN_ARMOR = new AchievementARP("craftManyullynArmor", -8, 0, ManyullynArmor.chestplate, CRAFT_ARDITE_ARMOR).setNormalCrafting().setSpecial();
        CRAFT_PIG_IRON_ARMOR = new AchievementARP("craftPigIronArmor", -4, -2, PigIronArmor.chestplate, CRAFT_COBALT_ARMOR).setNormalCrafting();
        CRAFT_KNIGHT_SLIME_ARMOR = new AchievementARP("craftKnightSlimeArmor", -4, 2, KnightSlimeArmor.chestplate, CRAFT_COBALT_ARMOR).setNormalCrafting();

        ARP_ACHIEVEMENT_PAGE = new AchievementPage(ArmorPlus.MODNAME, AchievementARP.achievements.toArray(new Achievement[AchievementARP.achievements.size()]));
        AchievementPage.registerAchievementPage(ARP_ACHIEVEMENT_PAGE);

    }

    public static class AchievementARP extends Achievement {
        public static List<Achievement> achievements = new ArrayList();

        public AchievementARP(String name, int x, int y, ItemStack icon, Achievement parent) {
            super("achievement.armorplus." + name, "armorplus." + name, x, y, icon, parent);
            achievements.add(this);
            registerStat();
        }

        public AchievementARP(String name, int x, int y, Item icon, Achievement parent) {
            this(name, x, y, new ItemStack(icon), parent);
        }

        public AchievementARP(String name, int x, int y, Block icon, Achievement parent) {
            this(name, x, y, new ItemStack(icon), parent);
        }

        public ItemStack[] triggerItems;

        public AchievementARP setNormalCrafting(ItemStack... triggerItems) {
            this.triggerItems = triggerItems;
            normalCraftingAchievements.add(this);
            return this;
        }

    }

    public static ArrayList<AchievementARP> normalCraftingAchievements = new ArrayList();
}