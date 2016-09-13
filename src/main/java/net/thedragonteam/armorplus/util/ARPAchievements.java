/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.ArrayList;
import java.util.List;

public class ARPAchievements {
    public static Achievement craftCoalArmor,
            craftLapisArmor,
            craftRedstoneArmor,
            craftEmeraldArmor,
            craftObsidianArmor,
            craftLavaArmor,
            craftSuperStarArmor,
            craftEnderDragonArmor,
            craftGuardianArmor,
            craftTheUltimatermor,
            craftSlimeArmor,
            craftChickenArmor,
            craftCobaltArmor,
            craftArditeArmor,
            craftManyullynArmor,
            craftPigIronArmor,
            craftKnightSlimeArmor,
            welcomeToArmorPlus;
    public static ArrayList<AchievementARP> normalCraftingAchievements = new ArrayList();
    private static AchievementPage arpAchievementPage;

    public static void init() {
        welcomeToArmorPlus = new AchievementARP("craft_armor_forge", -2, -2, ModItems.armorPlusBook, AchievementList.OPEN_INVENTORY).setNormalCrafting().setSpecial();
        craftCoalArmor = new AchievementARP("craft_coal_armor", 0, 0, ModItems.coalHelmet, craftCoalArmor).setNormalCrafting();
        craftChickenArmor = new AchievementARP("craft_chicken_armor", 2, 2, ModItems.chickenBoots, craftCoalArmor).setNormalCrafting();
        craftSlimeArmor = new AchievementARP("craft_slime_armor", 2, 4, ModItems.slimeBoots, craftCoalArmor).setNormalCrafting();
        craftLapisArmor = new AchievementARP("craft_lapis_armor", -2, 2, ModItems.lapisHelmet, craftCoalArmor).setNormalCrafting();
        craftGuardianArmor = new AchievementARP("craft_guardian_armor", -2, 4, ModItems.guardianHelmet, craftLapisArmor).setNormalCrafting();
        craftRedstoneArmor = new AchievementARP("craft_redstone_armor", 0, -4, ModItems.redstoneBoots, craftCoalArmor).setNormalCrafting();
        craftEmeraldArmor = new AchievementARP("craft_emerald_armor", 0, -6, ModItems.emeraldChestplate, craftRedstoneArmor).setNormalCrafting();
        craftObsidianArmor = new AchievementARP("craft_obsidian_armor", 4, 0, ModItems.obsidianChestplate, craftCoalArmor).setNormalCrafting();
        craftLavaArmor = new AchievementARP("craft_lava_armor", 6, 0, ModItems.lavaChestplate, craftObsidianArmor).setNormalCrafting();
        craftSuperStarArmor = new AchievementARP("craft_super_star_armor", 8, 0, ModItems.superStarChestplate, craftLavaArmor).setNormalCrafting();
        craftEnderDragonArmor = new AchievementARP("craft_ender_dragon_armor", 8, 2, ModItems.enderDragonChestplate, AchievementList.THE_END2).setNormalCrafting();
        craftTheUltimatermor = new AchievementARP("craft_the_ultimate_armor", 6, 4, ModItems.theUltimateChestplate, craftEnderDragonArmor).setNormalCrafting().setSpecial();
        craftCobaltArmor = new AchievementARP("craft_cobalt_armor", -4, 0, ModItems.cobaltChestplate, craftCoalArmor).setNormalCrafting();
        craftArditeArmor = new AchievementARP("craft_ardite_armor", -6, 0, ModItems.arditeChestplate, craftCobaltArmor).setNormalCrafting();
        craftManyullynArmor = new AchievementARP("craft_manyullyn_armor", -8, 0, ModItems.manyullynChestplate, craftArditeArmor).setNormalCrafting().setSpecial();
        craftPigIronArmor = new AchievementARP("craft_pig_iron_armor", -4, -2, ModItems.pigIronChestplate, craftCobaltArmor).setNormalCrafting();
        craftKnightSlimeArmor = new AchievementARP("craft_knight_slime_armor", -4, 2, ModItems.knightSlimeChestplate, craftCobaltArmor).setNormalCrafting();

        arpAchievementPage = new AchievementPage(ArmorPlus.MODNAME, AchievementARP.achievements.toArray(new Achievement[AchievementARP.achievements.size()]));
        AchievementPage.registerAchievementPage(arpAchievementPage);

    }

    public static class AchievementARP extends Achievement {
        public static List<Achievement> achievements = new ArrayList();
        public ItemStack[] triggerItems;

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

        public AchievementARP setNormalCrafting(ItemStack... triggerItems) {
            this.triggerItems = triggerItems;
            normalCraftingAchievements.add(this);
            return this;
        }

    }
}