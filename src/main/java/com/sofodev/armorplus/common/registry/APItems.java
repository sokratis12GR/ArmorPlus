/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 **/
public class APItems {

    public static Item coalHelmet;
    public static Item coalChestplate;
    public static Item coalLeggings;
    public static Item coalBoots;
    public static Item lapisHelmet;
    public static Item lapisChestplate;
    public static Item lapisLeggings;
    public static Item lapisBoots;
    public static Item redstoneHelmet;
    public static Item redstoneChestplate;
    public static Item redstoneLeggings;
    public static Item redstoneBoots;
    public static Item emeraldHelmet;
    public static Item emeraldChestplate;
    public static Item emeraldLeggings;
    public static Item emeraldBoots;
    public static Item obsidianHelmet;
    public static Item obsidianChestplate;
    public static Item obsidianLeggings;
    public static Item obsidianBoots;
    public static Item lavaHelmet;
    public static Item lavaChestplate;
    public static Item lavaLeggings;
    public static Item lavaBoots;
    public static Item chickenHelmet;
    public static Item chickenChestplate;
    public static Item chickenLeggings;
    public static Item chickenBoots;
    public static Item slimeHelmet;
    public static Item slimeChestplate;
    public static Item slimeLeggings;
    public static Item slimeBoots;
    public static Item guardianHelmet;
    public static Item guardianChestplate;
    public static Item guardianLeggings;
    public static Item guardianBoots;
    public static Item superStarHelmet;
    public static Item superStarChestplate;
    public static Item superStarLeggings;
    public static Item superStarBoots;
    public static Item enderDragonHelmet;
    public static Item enderDragonChestplate;
    public static Item enderDragonLeggings;
    public static Item enderDragonBoots;
    public static Item theUltimateHelmet;
    public static Item theUltimateChestplate;
    public static Item theUltimateLeggings;
    public static Item theUltimateBoots;
    public static Item cobaltHelmet;
    public static Item cobaltChestplate;
    public static Item cobaltLeggings;
    public static Item cobaltBoots;
    public static Item arditeHelmet;
    public static Item arditeChestplate;
    public static Item arditeLeggings;
    public static Item arditeBoots;
    public static Item manyullynHelmet;
    public static Item manyullynChestplate;
    public static Item manyullynLeggings;
    public static Item manyullynBoots;
    public static Item pigIronHelmet;
    public static Item pigIronChestplate;
    public static Item pigIronLeggings;
    public static Item pigIronBoots;
    public static Item knightSlimeHelmet;
    public static Item knightSlimeChestplate;
    public static Item knightSlimeLeggings;
    public static Item knightSlimeBoots;
    //Weapons
    public static Item coalSword;
    public static Item coalBattleAxe;
    public static Item coalBow;
    public static Item lapisSword;
    public static Item lapisBattleAxe;
    public static Item lapisBow;
    public static Item redstoneSword;
    public static Item redstoneBattleAxe;
    public static Item redstoneBow;
    public static Item emeraldSword;
    public static Item emeraldBattleAxe;
    public static Item emeraldBow;
    public static Item obsidianSword;
    public static Item obsidianBattleAxe;
    public static Item obsidianBow;
    public static Item lavaSword;
    public static Item lavaBattleAxe;
    public static Item lavaBow;
    public static Item guardianSword;
    public static Item guardianBattleAxe;
    public static Item guardianBow;
    public static Item superStarSword;
    public static Item superStarBattleAxe;
    public static Item superStarBow;
    public static Item enderDragonSword;
    public static Item enderDragonBattleAxe;
    public static Item enderDragonBow;

    public static ItemStack material;
    public static ItemStack chainmail;
    public static ItemStack guardianScale;
    public static ItemStack witherBone;
    public static ItemStack enderDragonScale;
    public static ItemStack theUltimateMaterial;
    public static ItemStack infusedLavaCrystal;


    public static void registerItemNames() {
        coalHelmet = ModItems.coal[0];
        coalChestplate = ModItems.coal[1];
        coalLeggings = ModItems.coal[2];
        coalBoots = ModItems.coal[3];

        lapisHelmet = ModItems.lapis[0];
        lapisChestplate = ModItems.lapis[1];
        lapisLeggings = ModItems.lapis[2];
        lapisBoots = ModItems.lapis[3];

        redstoneHelmet = ModItems.redstone[0];
        redstoneChestplate = ModItems.redstone[1];
        redstoneLeggings = ModItems.redstone[2];
        redstoneBoots = ModItems.redstone[3];

        emeraldHelmet = ModItems.emerald[0];
        emeraldChestplate = ModItems.emerald[1];
        emeraldLeggings = ModItems.emerald[2];
        emeraldBoots = ModItems.emerald[3];

        obsidianHelmet = ModItems.obsidian[0];
        obsidianChestplate = ModItems.obsidian[1];
        obsidianLeggings = ModItems.obsidian[2];
        obsidianBoots = ModItems.obsidian[3];

        lavaHelmet = ModItems.lava[0];
        lavaChestplate = ModItems.lava[1];
        lavaLeggings = ModItems.lava[2];
        lavaBoots = ModItems.lava[3];

        chickenHelmet = ModItems.chicken[0];
        chickenChestplate = ModItems.chicken[1];
        chickenLeggings = ModItems.chicken[2];
        chickenBoots = ModItems.chicken[3];

        slimeHelmet = ModItems.slime[0];
        slimeChestplate = ModItems.slime[1];
        slimeLeggings = ModItems.slime[2];
        slimeBoots = ModItems.slime[3];

        guardianHelmet = ModItems.guardian[0];
        guardianChestplate = ModItems.guardian[1];
        guardianLeggings = ModItems.guardian[2];
        guardianBoots = ModItems.guardian[3];

        superStarHelmet = ModItems.superStar[0];
        superStarChestplate = ModItems.superStar[1];
        superStarLeggings = ModItems.superStar[2];
        superStarBoots = ModItems.superStar[3];

        enderDragonHelmet = ModItems.enderDragon[0];
        enderDragonChestplate = ModItems.enderDragon[1];
        enderDragonLeggings = ModItems.enderDragon[2];
        enderDragonBoots = ModItems.enderDragon[3];

        theUltimateHelmet = ModItems.theUltimate[0];
        theUltimateChestplate = ModItems.theUltimate[1];
        theUltimateLeggings = ModItems.theUltimate[2];
        theUltimateBoots = ModItems.theUltimate[3];

        infusedLavaCrystal = getItemStack(ModItems.itemLavaCrystal, 1);
        chainmail = getItemStack(ModItems.materials, 0);
        guardianScale = getItemStack(ModItems.materials, 1);
        witherBone = getItemStack(ModItems.materials, 2);
        enderDragonScale = getItemStack(ModItems.materials, 3);
        theUltimateMaterial = getItemStack(ModItems.materials, 4);

    }

    public static void registerWeaponsA() {
        coalSword = ModItems.sword[0];
        coalBattleAxe = ModItems.battleAxe[0];
        coalBow = ModItems.bow[0];
        lapisSword = ModItems.sword[1];
        lapisBattleAxe = ModItems.battleAxe[1];
        lapisBow = ModItems.bow[1];
        redstoneSword = ModItems.sword[2];
        redstoneBattleAxe = ModItems.battleAxe[2];
        redstoneBow = ModItems.bow[2];
        emeraldSword = ModItems.sword[3];
        emeraldBattleAxe = ModItems.battleAxe[3];
        emeraldBow = ModItems.bow[3];
        obsidianSword = ModItems.sword[4];
        obsidianBattleAxe = ModItems.battleAxe[4];
        obsidianBow = ModItems.bow[4];
    }

    public static void registerWeaponsB() {
        lavaSword = ModItems.sword[5];
        lavaBattleAxe = ModItems.battleAxe[5];
        lavaBow = ModItems.bow[5];
        guardianSword = ModItems.sword[6];
        guardianBattleAxe = ModItems.battleAxe[6];
        guardianBow = ModItems.bow[6];
        superStarSword = ModItems.sword[7];
        superStarBattleAxe = ModItems.battleAxe[7];
        superStarBow = ModItems.bow[7];
        enderDragonSword = ModItems.sword[8];
        enderDragonBattleAxe = ModItems.battleAxe[8];
        enderDragonBow = ModItems.bow[8];
    }

    public static void registerTCItemNames() {
        cobaltHelmet = ModItems.cobalt[0];
        cobaltChestplate = ModItems.cobalt[1];
        cobaltLeggings = ModItems.cobalt[2];
        cobaltBoots = ModItems.cobalt[3];

        arditeHelmet = ModItems.ardite[0];
        arditeChestplate = ModItems.ardite[1];
        arditeLeggings = ModItems.ardite[2];
        arditeBoots = ModItems.ardite[3];

        manyullynHelmet = ModItems.manyullyn[0];
        manyullynChestplate = ModItems.manyullyn[1];
        manyullynLeggings = ModItems.manyullyn[2];
        manyullynBoots = ModItems.manyullyn[3];

        pigIronHelmet = ModItems.pigIron[0];
        pigIronChestplate = ModItems.pigIron[1];
        pigIronLeggings = ModItems.pigIron[2];
        pigIronBoots = ModItems.pigIron[3];

        knightSlimeHelmet = ModItems.knightSlime[0];
        knightSlimeChestplate = ModItems.knightSlime[1];
        knightSlimeLeggings = ModItems.knightSlime[2];
        knightSlimeBoots = ModItems.knightSlime[3];
    }
}