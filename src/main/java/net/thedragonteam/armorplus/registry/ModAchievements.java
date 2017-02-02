/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;
import net.thedragonteam.armorplus.ArmorPlus;

import java.util.ArrayList;
import java.util.List;

import static net.thedragonteam.armorplus.registry.APBlocks.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModAchievements {
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
    public static ArrayList<AchievementAP> normalCraftingAchievements = new ArrayList<>();

    public static void init() {
        welcomeToArmorPlus = new AchievementAP("craft_workbench", 4, -4, workbench, AchievementList.OPEN_INVENTORY)
                .setNormalCrafting(getItemStack(workbench)).setSpecial();
        craftHighTechBench = new AchievementAP("craft_high_tech_bench", 6, -2, highTechBench, welcomeToArmorPlus)
                .setNormalCrafting(getItemStack(highTechBench)).setSpecial();
        craftUltiTechBench = new AchievementAP("craft_ulti_tech_bench", 8, -2, ultiTechBench, craftHighTechBench)
                .setNormalCrafting(getItemStack(ultiTechBench)).setSpecial();
        craftChampionBench = new AchievementAP("craft_champion_bench", 10, -4, championBench, craftUltiTechBench)
                .setNormalCrafting(getItemStack(championBench)).setSpecial();
        armorPlus = new AchievementAP("armorplus", -4, -4, redstoneHelmet, AchievementList.OPEN_INVENTORY).setSpecial().initIndependentStat();
        craftCoalArmor = new AchievementAP("craft_coal_armor", 0, 0, coalHelmet, craftCoalArmor)
                .setNormalCrafting(getItemStack(coalHelmet), getItemStack(coalChestplate), getItemStack(coalLeggings), getItemStack(coalBoots));
        craftChickenArmor = new AchievementAP("craft_chicken_armor", 2, 2, chickenBoots, craftCoalArmor)
                .setNormalCrafting(getItemStack(chickenHelmet), getItemStack(chickenChestplate), getItemStack(chickenLeggings), getItemStack(chickenBoots));
        craftSlimeArmor = new AchievementAP("craft_slime_armor", 2, 4, slimeBoots, craftCoalArmor)
                .setNormalCrafting(getItemStack(slimeHelmet), getItemStack(slimeChestplate), getItemStack(slimeLeggings), getItemStack(slimeBoots));
        craftLapisArmor = new AchievementAP("craft_lapis_armor", -2, 2, ModItems.lapis[0], craftCoalArmor)
                .setNormalCrafting(getItemStack(lapisHelmet), getItemStack(lapisChestplate), getItemStack(lapisLeggings), getItemStack(lapisBoots));
        craftGuardianArmor = new AchievementAP("craft_guardian_armor", -2, 4, ModItems.guardian[0], craftLapisArmor)
                .setNormalCrafting(getItemStack(guardianHelmet), getItemStack(guardianChestplate), getItemStack(guardianLeggings), getItemStack(guardianBoots));
        craftRedstoneArmor = new AchievementAP("craft_redstone_armor", 0, -4, ModItems.redstone[3], craftCoalArmor)
                .setNormalCrafting(getItemStack(redstoneHelmet), getItemStack(redstoneChestplate), getItemStack(redstoneLeggings), getItemStack(redstoneBoots));
        craftEmeraldArmor = new AchievementAP("craft_emerald_armor", 0, -6, ModItems.emerald[1], craftRedstoneArmor)
                .setNormalCrafting(getItemStack(emeraldHelmet), getItemStack(emeraldChestplate), getItemStack(emeraldLeggings), getItemStack(emeraldBoots));
        craftObsidianArmor = new AchievementAP("craft_obsidian_armor", 4, 0, ModItems.obsidian[1], craftCoalArmor)
                .setNormalCrafting(getItemStack(obsidianHelmet), getItemStack(obsidianChestplate), getItemStack(obsidianLeggings), getItemStack(obsidianBoots));
        craftLavaArmor = new AchievementAP("craft_lava_armor", 6, 0, ModItems.lava[1], craftObsidianArmor)
                .setNormalCrafting(getItemStack(lavaHelmet), getItemStack(lavaChestplate), getItemStack(lavaLeggings), getItemStack(lavaBoots)).setSpecial();
        craftSuperStarArmor = new AchievementAP("craft_super_star_armor", 8, 0, ModItems.superStar[1], craftLavaArmor)
                .setNormalCrafting(getItemStack(superStarHelmet), getItemStack(superStarChestplate), getItemStack(superStarLeggings), getItemStack(superStarBoots)).setSpecial();
        craftEnderDragonArmor = new AchievementAP("craft_ender_dragon_armor", 8, 2, ModItems.enderDragon[1], AchievementList.THE_END2)
                .setNormalCrafting(getItemStack(enderDragonHelmet), getItemStack(enderDragonChestplate), getItemStack(enderDragonLeggings), getItemStack(enderDragonBoots)).setSpecial();
        craftTheUltimateArmor = new AchievementAP("craft_the_ultimate_armor", 6, 4, ModItems.theUltimate[1], craftEnderDragonArmor)
                .setNormalCrafting(getItemStack(theUltimateHelmet), getItemStack(theUltimateChestplate), getItemStack(theUltimateLeggings), getItemStack(theUltimateBoots)).setSpecial().setSpecial();
        craftCobaltArmor = new AchievementAP("craft_cobalt_armor", -4, 0, ModItems.cobalt[1], craftCoalArmor)
                .setNormalCrafting(getItemStack(cobaltHelmet), getItemStack(cobaltChestplate), getItemStack(cobaltLeggings), getItemStack(cobaltBoots)).setSpecial();
        craftArditeArmor = new AchievementAP("craft_ardite_armor", -6, 0, ModItems.ardite[1], craftCobaltArmor)
                .setNormalCrafting(getItemStack(arditeHelmet), getItemStack(arditeChestplate), getItemStack(arditeLeggings), getItemStack(arditeBoots)).setSpecial();
        craftManyullynArmor = new AchievementAP("craft_manyullyn_armor", -8, 0, ModItems.manyullyn[1], craftArditeArmor)
                .setNormalCrafting(getItemStack(manyullynHelmet), getItemStack(manyullynChestplate), getItemStack(manyullynLeggings), getItemStack(manyullynBoots)).setSpecial();
        craftPigIronArmor = new AchievementAP("craft_pig_iron_armor", -4, -2, ModItems.pigIron[1], craftCobaltArmor)
                .setNormalCrafting(getItemStack(pigIronHelmet), getItemStack(pigIronChestplate), getItemStack(pigIronLeggings), getItemStack(pigIronBoots));
        craftKnightSlimeArmor = new AchievementAP("craft_knight_knightSlime_armor", -4, 2, ModItems.knightSlime[1], craftCobaltArmor)
                .setNormalCrafting(getItemStack(knightSlimeHelmet), getItemStack(knightSlimeChestplate), getItemStack(knightSlimeLeggings), getItemStack(knightSlimeBoots));

        //Minecraft Achievements KAPPA
        //        TDLAchievements.thedragonlibAchievements.add(new TDLAchievements.AchievementTDL("minecraft_achievement", 5, 0, Items.STONE_AXE, AchievementList.OPEN_INVENTORY).setSpecial());

        AchievementPage arpAchievementPage = new AchievementPage(ArmorPlus.MODNAME, AchievementAP.achievements.toArray(new Achievement[AchievementAP.achievements.size()]));
        AchievementPage.registerAchievementPage(arpAchievementPage);
    }

    public static class AchievementAP extends Achievement {
        public static List<Achievement> achievements = new ArrayList<>();
        ItemStack[] triggerItems;

        public AchievementAP(String name, int x, int y, ItemStack icon, Achievement parent) {
            super("achievement.armorplus." + name, "armorplus." + name, x, y, icon, parent);
            achievements.add(this);
            registerStat();
        }

        public AchievementAP(String name, int x, int y, Item icon, Achievement parent) {
            this(name, x, y, getItemStack(icon), parent);
        }

        public AchievementAP(String name, int x, int y, Block icon, Achievement parent) {
            this(name, x, y, getItemStack(icon), parent);
        }

        public AchievementAP setNormalCrafting(ItemStack... triggerItems) {
            this.triggerItems = triggerItems;
            normalCraftingAchievements.add(this);
            return this;
        }
    }
}