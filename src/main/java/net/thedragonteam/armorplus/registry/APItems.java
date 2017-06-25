/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

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

    public APItems() {
    }

    @SuppressWarnings({"Duplicates", "ConstantConditions"})
    public static void registerItemNames() {
        if (enableCoalArmor) {
            coalHelmet = coal[0];
            coalChestplate = coal[1];
            coalLeggings = coal[2];
            coalBoots = coal[3];
        }
        if (enableLapisArmor) {
            lapisHelmet = lapis[0];
            lapisChestplate = lapis[1];
            lapisLeggings = lapis[2];
            lapisBoots = lapis[3];
        }
        if (enableRedstoneArmor) {
            redstoneHelmet = redstone[0];
            redstoneChestplate = redstone[1];
            redstoneLeggings = redstone[2];
            redstoneBoots = redstone[3];
        }
        if (enableEmeraldArmor) {
            emeraldHelmet = emerald[0];
            emeraldChestplate = emerald[1];
            emeraldLeggings = emerald[2];
            emeraldBoots = emerald[3];
        }
        if (enableObsidianArmor) {
            obsidianHelmet = obsidian[0];
            obsidianChestplate = obsidian[1];
            obsidianLeggings = obsidian[2];
            obsidianBoots = obsidian[3];
        }
        if (enableLavaArmor) {
            lavaHelmet = lava[0];
            lavaChestplate = lava[1];
            lavaLeggings = lava[2];
            lavaBoots = lava[3];
        }
        if (enableChickenArmor) {
            chickenHelmet = chicken[0];
            chickenChestplate = chicken[1];
            chickenLeggings = chicken[2];
            chickenBoots = chicken[3];
        }
        if (enableSlimeArmor) {
            slimeHelmet = slime[0];
            slimeChestplate = slime[1];
            slimeLeggings = slime[2];
            slimeBoots = slime[3];
        }
        if (enableGuardianArmor) {
            guardianHelmet = guardian[0];
            guardianChestplate = guardian[1];
            guardianLeggings = guardian[2];
            guardianBoots = guardian[3];
        }
        if (enableSuperStarArmor) {
            superStarHelmet = superStar[0];
            superStarChestplate = superStar[1];
            superStarLeggings = superStar[2];
            superStarBoots = superStar[3];
        }
        if (enableEnderDragonArmor) {
            enderDragonHelmet = enderDragon[0];
            enderDragonChestplate = enderDragon[1];
            enderDragonLeggings = enderDragon[2];
            enderDragonBoots = enderDragon[3];
        }
        if (enableTheUltimateArmor) {
            theUltimateHelmet = theUltimate[0];
            theUltimateChestplate = theUltimate[1];
            theUltimateLeggings = theUltimate[2];
            theUltimateBoots = theUltimate[3];
        }
        if (enableCobaltArmor) {
            cobaltHelmet = cobalt[0];
            cobaltChestplate = cobalt[1];
            cobaltLeggings = cobalt[2];
            cobaltBoots = cobalt[3];
        }
        if (enableArditeArmor) {
            arditeHelmet = ardite[0];
            arditeChestplate = ardite[1];
            arditeLeggings = ardite[2];
            arditeBoots = ardite[3];
        }
        if (enableManyullynArmor) {
            manyullynHelmet = manyullyn[0];
            manyullynChestplate = manyullyn[1];
            manyullynLeggings = manyullyn[2];
            manyullynBoots = manyullyn[3];
        }
        if (enablePigIronArmor) {
            pigIronHelmet = pigIron[0];
            pigIronChestplate = pigIron[1];
            pigIronLeggings = pigIron[2];
            pigIronBoots = pigIron[3];
        }
        if (enableKnightSlimeArmor) {
            knightSlimeHelmet = knightSlime[0];
            knightSlimeChestplate = knightSlime[1];
            knightSlimeLeggings = knightSlime[2];
            knightSlimeBoots = knightSlime[3];
        }
        if (enableCoalWeapons[0]) coalSword = sword[0];
        if (enableCoalWeapons[1]) coalBattleAxe = battleAxe[0];
        if (enableCoalWeapons[2]) coalBow = bow[0];
        if (enableLapisWeapons[0]) lapisSword = sword[1];
        if (enableLapisWeapons[1]) lapisBattleAxe = battleAxe[1];
        if (enableLapisWeapons[2]) lapisBow = bow[1];
        if (enableRedstoneWeapons[0]) redstoneSword = sword[2];
        if (enableRedstoneWeapons[1]) redstoneBattleAxe = battleAxe[2];
        if (enableRedstoneWeapons[2]) redstoneBow = bow[2];
        if (enableEmeraldWeapons[0]) emeraldSword = sword[3];
        if (enableEmeraldWeapons[1]) emeraldBattleAxe = battleAxe[3];
        if (enableEmeraldWeapons[2]) emeraldBow = bow[3];
        if (enableObsidianWeapons[0]) obsidianSword = sword[4];
        if (enableObsidianWeapons[1]) obsidianBattleAxe = battleAxe[4];
        if (enableObsidianWeapons[2]) obsidianBow = bow[4];
        if (enableLavaWeapons[0]) lavaSword = sword[5];
        if (enableLavaWeapons[1]) lavaBattleAxe = battleAxe[5];
        if (enableLavaWeapons[2]) lavaBow = bow[5];
        if (enableGuardianWeapons[0]) guardianSword = sword[6];
        if (enableGuardianWeapons[1]) guardianBattleAxe = battleAxe[6];
        if (enableGuardianWeapons[2]) guardianBow = bow[6];
        if (enableSuperStarWeapons[0]) superStarSword = sword[7];
        if (enableSuperStarWeapons[1]) superStarBattleAxe = battleAxe[7];
        if (enableSuperStarWeapons[2]) superStarBow = bow[7];
        if (enableEnderDragonWeapons[0]) enderDragonSword = sword[8];
        if (enableEnderDragonWeapons[1]) enderDragonBattleAxe = battleAxe[8];
        if (enableEnderDragonWeapons[2]) enderDragonBow = bow[8];

        infusedLavaCrystal = getItemStack(lavaCrystal, 1);
        chainmail = getItemStack(materials, 0);
        guardianScale = getItemStack(materials, 1);
        witherBone = getItemStack(materials, 2);
        enderDragonScale = getItemStack(materials, 3);
        theUltimateMaterial = getItemStack(materials, 4);
    }
}