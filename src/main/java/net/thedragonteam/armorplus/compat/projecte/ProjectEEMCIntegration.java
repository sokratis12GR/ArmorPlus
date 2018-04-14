package net.thedragonteam.armorplus.compat.projecte;

import com.google.common.collect.ImmutableMap;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.registry.ModItems;

import static com.google.common.collect.ImmutableMap.of;
import static net.minecraft.init.Blocks.*;
import static net.minecraft.init.Items.*;
import static net.thedragonteam.armorplus.ModConfig.RecipesDifficulty.*;
import static net.thedragonteam.armorplus.ModConfig.getRD;
import static net.thedragonteam.armorplus.compat.CompatibilityProjectE.addConversion;
import static net.thedragonteam.armorplus.registry.APBlocks.highTechBench;
import static net.thedragonteam.armorplus.registry.APBlocks.ultiTechBench;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.*;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.armorplus.registry.ModItems.theUltimateParts;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ProjectEEMCIntegration {

    public static void registerEasyEMC() {
        ItemStack guardianScale = getItemStack(materials, 1);
        ItemStack witherBone = getItemStack(materials, 2);
        ItemStack enderDragonScale = getItemStack(materials, 3);
        if (getRD() == EASY) {
            registerEasyArmorEMC(guardianScale, guardianHelmet, guardianChestplate, guardianLeggings, guardianBoots);
            registerEasyArmorEMC(witherBone, superStarHelmet, superStarChestplate, superStarLeggings, superStarBoots);
            registerEasyArmorEMC(enderDragonScale, enderDragonHelmet, enderDragonChestplate, enderDragonLeggings, enderDragonBoots);
            registerEasyMeleeEMC(guardianScale, guardianSword, guardianBattleAxe);
            registerEasyMeleeEMC(witherBone, superStarSword, superStarBattleAxe);
            registerEasyMeleeEMC(enderDragonScale, enderDragonSword, enderDragonBattleAxe);
            registerEasyRangedEMC(guardianScale, PRISMARINE_SHARD, guardianBow);
            registerEasyRangedEMC(witherBone, STRING, superStarBow);
            registerEasyRangedEMC(enderDragonScale, STRING, enderDragonBow);
        }
    }

    private static void registerEasyMeleeEMC(ItemStack material, Item sword, Item battleAxe) {
        addConversion(sword, of(material, 7, STICK, 6));
        addConversion(battleAxe, of(material, 12, STICK, 6));
    }

    private static void registerEasyRangedEMC(ItemStack materialA, Item materialB, Item bow) {
        addConversion(bow, of(materialA, 11, getItemStack(materialB), 7));
    }

    private static void registerEasyArmorEMC(ItemStack material, Item head, Item chest, Item legs, Item feet) {
        createArmorEMC(material, head, 11, chest, 34, legs, 24, feet, 6);
    }

    public static void registerExpertEMC() {
        ItemStack guardianScale = getItemStack(materials, 1);
        ItemStack witherBone = getItemStack(materials, 2);
        ItemStack enderDragonScale = getItemStack(materials, 3);
        ItemStack theUltimateMaterial = getItemStack(materials, 4);
        ItemStack infusedLavaCrystal = getItemStack(ModItems.lavaCrystal, 1);
        addConversion(getItemStack(compressedObsidian), of(OBSIDIAN, 9));
        addConversion(highTechBench, of(getItemStack(infusedLavaCrystal), 3, getItemStack(REDSTONE_BLOCK), 5, getItemStack(benches[0]), 1));
        addConversion(ultiTechBench, of(
            infusedLavaCrystal, 2, theUltimateMaterial, 6, getItemStack(benches[0]), 1, getItemStack(benches[1]), 1, compressedObsidian, 8
        ));
        if (getRD() == EXPERT || getRD() == HELLISH) {
            //
            createWorkbenchArmorEMC(COAL_BLOCK, coalHelmet, coalChestplate, coalLeggings, coalBoots);
            createWorkbenchArmorEMC(LAPIS_BLOCK, lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots);
            createWorkbenchArmorEMC(REDSTONE_BLOCK, redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots);
            createWorkbenchArmorEMC(SLIME_BLOCK, slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots);
            //
            createHighTechArmorEMC(EMERALD_BLOCK, EMERALD, emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots);
            createHighTechArmorEMC(compressedObsidian, OBSIDIAN, obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots);
            createHighTechArmorEMC(blockInfusedLavaCrystal, infusedLavaCrystal, lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots);
            //
            addConversion(chickenHelmet, of(getItemStack(FEATHER), 3, getItemStack(EGG), 2));
            addConversion(chickenChestplate, of(getItemStack(FEATHER), 5, getItemStack(EGG), 3));
            addConversion(chickenLeggings, of(getItemStack(FEATHER), 5, getItemStack(EGG), 2));
            addConversion(chickenBoots, of(getItemStack(FEATHER), 2, getItemStack(EGG), 2));
            //
            createOriginWeaponsEMC(COAL_BLOCK, coalSword, coalBattleAxe, coalBow);
            createOriginWeaponsEMC(LAPIS_BLOCK, lapisSword, lapisBattleAxe, lapisBow);
            createOriginWeaponsEMC(REDSTONE_BLOCK, redstoneSword, redstoneBattleAxe, redstoneBow);
            //
            createHighTechWeaponsEMC(EMERALD_BLOCK, emeraldSword, emeraldBattleAxe, emeraldBow);
            createHighTechWeaponsEMC(compressedObsidian, obsidianSword, obsidianBattleAxe, obsidianBow);
            createHighTechWeaponsEMC(infusedLavaCrystal, lavaSword, lavaBattleAxe, lavaBow);
            registerGuardianEMC(guardianScale);
            registerSuperStarEMC(witherBone);
            registerEnderDragonEMC(enderDragonScale);
            registerUltimateEMC();
        }
    }

    private static void createHighTechWeaponsEMC(Object material, Item sword, Item battleAxe, Item bow) {
        addConversion(sword, of(getItemStack(material), 4, getItemStack(STICK), 1));
        addConversion(battleAxe, of(getItemStack(material), 8, getItemStack(STICK), 4));
        addConversion(bow, of(getItemStack(material), 7, getItemStack(STRING), 5));
    }

    private static void createHighTechArmorEMC(Object materialA, Object materialB, Item head, Item chest, Item legs, Item feet) {
        addConversion(head,
            of(getItemStack(materialA), 4, getItemStack(materialB), 5)
        );
        addConversion(chest,
            of(getItemStack(materialA), 13, getItemStack(materialB), 7)
        );
        addConversion(legs,
            of(getItemStack(materialA), 7, getItemStack(materialB), 9)
        );
        addConversion(feet,
            of(getItemStack(materialA), 4, getItemStack(materialB), 2)
        );
    }

    private static void createOriginWeaponsEMC(Object material, Item sword, Item battleAxe, Item bow) {
        addConversion(sword, of(getItemStack(material), 2, getItemStack(STICK), 1));
        addConversion(battleAxe, of(getItemStack(material), 4, getItemStack(STICK), 2));
        addConversion(bow, of(getItemStack(material), 3, getItemStack(STRING), 3));
    }

    private static void createWorkbenchArmorEMC(Object material, Item head, Item chest, Item legs, Item feet) {
        createArmorEMC(getItemStack(material), head, 5, chest, 8, legs, 7, feet, 4);
    }

    private static void createArmorEMC(ItemStack material, Item head, int headAmount, Item chest, int chestAmount, Item legs, int legsAmount, Item feet, int feetAmount) {
        addConversion(head,
            of(material, headAmount)
        );
        addConversion(chest,
            of(material, chestAmount)
        );
        addConversion(legs,
            of(material, legsAmount)
        );
        addConversion(feet,
            of(material, feetAmount)
        );
    }

    private static void registerUltimateEMC() {
        //
        registerUltimatePartsEMC(0, 1, 2, guardianHelmet, enderDragonHelmet, superStarHelmet);
        addConversion(theUltimateHelmet, of(theUltimateMaterial, 26, infusedLavaCrystal, 12,
            getItemStack(theUltimateParts, 0), 1, getItemStack(theUltimateParts, 1), 1, getItemStack(theUltimateParts, 2), 1
        ));
        //
        registerUltimatePartsEMC(3, 4, 5, guardianChestplate, enderDragonChestplate, superStarChestplate);
        addConversion(theUltimateChestplate, of(theUltimateMaterial, 26, infusedLavaCrystal, 12,
            getItemStack(theUltimateParts, 3), 1, getItemStack(theUltimateParts, 4), 1, getItemStack(theUltimateParts, 5), 1
        ));
        //
        registerUltimatePartsEMC(6, 7, 8, guardianLeggings, enderDragonLeggings, superStarLeggings);
        addConversion(theUltimateLeggings, of(theUltimateMaterial, 26, infusedLavaCrystal, 12,
            getItemStack(theUltimateParts, 6), 1, getItemStack(theUltimateParts, 7), 1, getItemStack(theUltimateParts, 8), 1
        ));
        //
        registerUltimatePartsEMC(9, 10, 11, guardianBoots, enderDragonBoots, superStarBoots);
        addConversion(theUltimateBoots, of(theUltimateMaterial, 26, infusedLavaCrystal, 12,
            getItemStack(theUltimateParts, 9), 1, getItemStack(theUltimateParts, 10), 1, getItemStack(theUltimateParts, 11), 1
        ));
    }

    private static void registerUltimatePartsEMC(int right, int middle, int left, Item itemRight, Item itemMid, Item itemLeft) {
        addConversion(getItemStack(theUltimateParts, right), new ImmutableMap.Builder<Object, Integer>().put(theUltimateMaterial, 28).put(infusedLavaCrystal, 12)
            .put(guardianScale, 2).put(SPONGE, 2).put(PRISMARINE_SHARD, 2).put(PRISMARINE_CRYSTALS, 2).put(itemRight, 1).build()
        );
        addConversion(getItemStack(theUltimateParts, middle), new ImmutableMap.Builder<Object, Integer>().put(theUltimateMaterial, 28).put(infusedLavaCrystal, 12)
            .put(enderDragonScale, 2).put(END_CRYSTAL, 2).put(ENDER_EYE, 2).put(ENDER_PEARL, 2).put(itemMid, 1).build()
        );
        addConversion(getItemStack(theUltimateParts, left), new ImmutableMap.Builder<Object, Integer>().put(theUltimateMaterial, 28).put(infusedLavaCrystal, 12)
            .put(witherBone, 2).put(NETHER_BRICK, 2).put(SOUL_SAND, 2).put(NETHERRACK, 2).put(itemLeft, 1).build()
        );
    }

    private static void registerGuardianEMC(ItemStack guardianScale) {
        addConversion(guardianHelmet,
            of(guardianScale, 8, getItemStack(SEA_LANTERN), 3, getItemStack(PRISMARINE_CRYSTALS), 3, getItemStack(PRISMARINE), 2)
        );
        addConversion(guardianChestplate,
            of(guardianScale, 20, getItemStack(SEA_LANTERN), 4, getItemStack(PRISMARINE_CRYSTALS), 4, getItemStack(PRISMARINE), 6)
        );
        addConversion(guardianLeggings,
            of(guardianScale, 15, getItemStack(SEA_LANTERN), 1, getItemStack(PRISMARINE_CRYSTALS), 4, getItemStack(PRISMARINE), 4)
        );
        addConversion(guardianBoots,
            of(guardianScale, 6, getItemStack(SPONGE), 2)
        );
        addConversion(guardianSword, of(guardianScale, 7, getItemStack(PRISMARINE_SHARD), 6));
        addConversion(guardianBattleAxe, of(guardianScale, 12, getItemStack(PRISMARINE_SHARD), 6));
        addConversion(guardianBow, of(guardianScale, 11, getItemStack(PRISMARINE_SHARD), 7));
    }

    private static void registerSuperStarEMC(ItemStack witherBone) {
        addConversion(superStarHelmet,
            of(witherBone, 8, getItemStack(NETHER_STAR), 5, getItemStack(SOUL_SAND), 2)
        );
        addConversion(superStarChestplate,
            of(witherBone, 19, getItemStack(NETHER_STAR), 10, getItemStack(SOUL_SAND), 4, getItemStack(Items.SKULL, 1), 1)
        );
        addConversion(superStarLeggings,
            of(witherBone, 6, getItemStack(NETHER_STAR), 7, getItemStack(SOUL_SAND), 4)
        );
        addConversion(superStarBoots,
            of(witherBone, 4, getItemStack(NETHER_STAR), 2, getItemStack(SOUL_SAND), 2)
        );
        addConversion(superStarSword, of(witherBone, 6, getItemStack(NETHER_STAR), 6, getItemStack(Items.SKULL, 1), 1));
        addConversion(superStarBattleAxe, of(witherBone, 12, getItemStack(NETHER_STAR), 5, getItemStack(Items.SKULL, 1), 1));
        addConversion(superStarBow, of(witherBone, 6, getItemStack(NETHER_STAR), 5, getItemStack(STRING), 7));
    }

    private static void registerEnderDragonEMC(ItemStack enderDragonScale) {
        addConversion(enderDragonHelmet,
            of(enderDragonScale, 11, getItemStack(ENDER_EYE), 2, getItemStack(END_CRYSTAL), 2)
        );
        addConversion(enderDragonChestplate,
            of(enderDragonScale, 18, getItemStack(ENDER_EYE), 6, getItemStack(END_CRYSTAL), 6, getItemStack(END_STONE), 4)
        );
        addConversion(enderDragonLeggings,
            of(enderDragonScale, 17, getItemStack(ENDER_EYE), 3, getItemStack(END_CRYSTAL), 6)
        );
        addConversion(enderDragonBoots,
            of(enderDragonScale, 4, getItemStack(ENDER_EYE), 2, getItemStack(END_CRYSTAL), 2)
        );
        addConversion(enderDragonSword, of(enderDragonScale, 6, getItemStack(DRAGON_BREATH), 6));
        addConversion(enderDragonBattleAxe, of(enderDragonScale, 12, getItemStack(DRAGON_BREATH), 6));
        addConversion(enderDragonBow, of(enderDragonScale, 6, getItemStack(DRAGON_BREATH), 5, getItemStack(STRING), 7));
    }
}
