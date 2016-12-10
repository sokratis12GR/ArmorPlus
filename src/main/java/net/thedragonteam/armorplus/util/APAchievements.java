/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.ArrayList;
import java.util.List;

public class APAchievements {
    public static Achievement craftCoalArmor,
            craftLapisArmor,
            craftRedstoneArmor,
            craftEmeraldArmor,
            craftObsidianArmor,
            craftLavaArmor,
            craftSuperStarArmor,
            craftEnderDragonArmor,
            craftGuardianArmor,
            craftTheUltimateArmor,
            craftSlimeArmor,
            craftChickenArmor,
            craftCobaltArmor,
            craftArditeArmor,
            craftManyullynArmor,
            craftPigIronArmor,
            craftKnightSlimeArmor,
            welcomeToArmorPlus,
            craftHighTechBench,
            craftUltiTechBench,
            craftChampionBench;
    public static Achievement armorPlus;
    @SuppressWarnings("unchecked")
    public static ArrayList<AchievementAP> normalCraftingAchievements = new ArrayList();

    public static void init() {
        welcomeToArmorPlus = new AchievementAP("craft_workbench", 6, -4, ModBlocks.arpWorkbench, AchievementList.OPEN_INVENTORY).setNormalCrafting().setSpecial();
        craftHighTechBench = new AchievementAP("craft_high_bench", 8, -4, ModBlocks.arpHighTechBench, welcomeToArmorPlus).setNormalCrafting().setSpecial();
        craftUltiTechBench = new AchievementAP("craft_ulti_bench", 8, -2, ModBlocks.arpUltiTechBench, craftHighTechBench).setNormalCrafting().setSpecial();
        craftChampionBench = new AchievementAP("craft_champion_bench", 8, 0, ModBlocks.arpChampionBench, craftUltiTechBench).setNormalCrafting().setSpecial();
        armorPlus = new AchievementAP("armorplus", -4, -4, ModItems.redstoneChestplate, AchievementList.OPEN_INVENTORY).setSpecial().initIndependentStat();
        craftCoalArmor = new AchievementAP("craft_coal_armor", 0, 0, ModItems.coalHelmet, craftCoalArmor).setNormalCrafting();
        craftChickenArmor = new AchievementAP("craft_chicken_armor", 2, 2, ModItems.chickenBoots, craftCoalArmor).setNormalCrafting();
        craftSlimeArmor = new AchievementAP("craft_slime_armor", 2, 4, ModItems.slimeBoots, craftCoalArmor).setNormalCrafting();
        craftLapisArmor = new AchievementAP("craft_lapis_armor", -2, 2, ModItems.lapisHelmet, craftCoalArmor).setNormalCrafting();
        craftGuardianArmor = new AchievementAP("craft_guardian_armor", -2, 4, ModItems.guardianHelmet, craftLapisArmor).setNormalCrafting();
        craftRedstoneArmor = new AchievementAP("craft_redstone_armor", 0, -4, ModItems.redstoneBoots, craftCoalArmor).setNormalCrafting();
        craftEmeraldArmor = new AchievementAP("craft_emerald_armor", 0, -6, ModItems.emeraldChestplate, craftRedstoneArmor).setNormalCrafting();
        craftObsidianArmor = new AchievementAP("craft_obsidian_armor", 4, 0, ModItems.obsidianChestplate, craftCoalArmor).setNormalCrafting();
        craftLavaArmor = new AchievementAP("craft_lava_armor", 6, 0, ModItems.lavaChestplate, craftObsidianArmor).setNormalCrafting();
        craftSuperStarArmor = new AchievementAP("craft_super_star_armor", 8, 0, ModItems.superStarChestplate, craftLavaArmor).setNormalCrafting();
        craftEnderDragonArmor = new AchievementAP("craft_ender_dragon_armor", 8, 2, ModItems.enderDragonChestplate, AchievementList.THE_END2).setNormalCrafting();
        craftTheUltimateArmor = new AchievementAP("craft_the_ultimate_armor", 6, 4, ModItems.theUltimateChestplate, craftEnderDragonArmor).setNormalCrafting().setSpecial();
        craftCobaltArmor = new AchievementAP("craft_cobalt_armor", -4, 0, ModItems.cobaltChestplate, craftCoalArmor).setNormalCrafting();
        craftArditeArmor = new AchievementAP("craft_ardite_armor", -6, 0, ModItems.arditeChestplate, craftCobaltArmor).setNormalCrafting();
        craftManyullynArmor = new AchievementAP("craft_manyullyn_armor", -8, 0, ModItems.manyullynChestplate, craftArditeArmor).setNormalCrafting().setSpecial();
        craftPigIronArmor = new AchievementAP("craft_pig_iron_armor", -4, -2, ModItems.pigIronChestplate, craftCobaltArmor).setNormalCrafting();
        craftKnightSlimeArmor = new AchievementAP("craft_knight_slime_armor", -4, 2, ModItems.knightSlimeChestplate, craftCobaltArmor).setNormalCrafting();

        AchievementPage arpAchievementPage = new AchievementPage(ArmorPlus.MODNAME, AchievementAP.achievements.toArray(new Achievement[AchievementAP.achievements.size()]));
        AchievementPage.registerAchievementPage(arpAchievementPage);
    }

    @SuppressWarnings("unchecked")
    private static class AchievementAP extends Achievement {
        public static List<Achievement> achievements = new ArrayList();
        ItemStack[] triggerItems;

        AchievementAP(String name, int x, int y, ItemStack icon, Achievement parent) {
            super("achievement.armorplus." + name, "armorplus." + name, x, y, icon, parent);
            achievements.add(this);
            registerStat();
        }

        AchievementAP(String name, int x, int y, Item icon, Achievement parent) {
            this(name, x, y, new ItemStack(icon), parent);
        }

        AchievementAP(String name, int x, int y, Block icon, Achievement parent) {
            this(name, x, y, new ItemStack(icon), parent);
        }

        AchievementAP setNormalCrafting(ItemStack... triggerItems) {
            this.triggerItems = triggerItems;
            normalCraftingAchievements.add(this);
            return this;
        }

    }
}