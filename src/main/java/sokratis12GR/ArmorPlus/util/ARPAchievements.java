package sokratis12GR.ArmorPlus.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;
import sokratis12GR.ArmorPlus.ArmorPlus;
import sokratis12GR.ArmorPlus.armors.*;

import java.util.ArrayList;
import java.util.List;

public class ARPAchievements {
    public static AchievementPage arpAchievementPage;

    public static Achievement craftCoalArmor;//0,0
    public static Achievement craftLapisArmor;//3,1
    public static Achievement craftRedstoneArmor;//0,-2
    public static Achievement craftEmeraldArmor;//-2,0
    public static Achievement craftObsidianArmor;//2,-2
    public static Achievement craftLavaArmor;//2,-4
    public static Achievement craftSuperStarArmor;//4,-4

    public static void init() {
        craftCoalArmor = new AchievementARP("craftCoalArmor", 0, 0, CoalArmor.helmet, AchievementList.openInventory).setNormalCrafting();
        craftLapisArmor = new AchievementARP("craftLapisArmor", 3, 1, LapisArmor.helmet, craftCoalArmor).setNormalCrafting();
        craftRedstoneArmor = new AchievementARP("craftRedstoneArmor", 0, -2, RedstoneArmor.boots, craftCoalArmor).setNormalCrafting();
        craftEmeraldArmor = new AchievementARP("craftEmeraldArmor", -2, 0, EmeraldArmor.chestplate, craftCoalArmor).setNormalCrafting();
        craftObsidianArmor = new AchievementARP("craftObsidianArmor", 2, -2, ObsidianArmor.chestplate, craftCoalArmor).setNormalCrafting();
        craftLavaArmor = new AchievementARP("craftLavaArmor", 2, -4, LavaArmor.chestplate, craftObsidianArmor).setNormalCrafting();
        craftSuperStarArmor = new AchievementARP("craftSuperStarArmor", 4, -4, SuperStarArmor.chestplate, craftLavaArmor).setNormalCrafting();

        arpAchievementPage = new AchievementPage(ArmorPlus.MODNAME, AchievementARP.achievements.toArray(new Achievement[AchievementARP.achievements.size()]));
        AchievementPage.registerAchievementPage(arpAchievementPage);

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