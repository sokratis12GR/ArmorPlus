/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.items.weapons.BattleAxes;
import com.sofodev.armorplus.items.weapons.Bows;
import com.sofodev.armorplus.items.weapons.Swords;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.global_registry;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ModItems {

    public static boolean[] isArmorEnabled = new boolean[]{
        global_registry.enableCoalArmor, global_registry.enableEmeraldArmor, global_registry.enableLapisArmor, global_registry.enableLavaArmor, global_registry.enableObsidianArmor, global_registry.enableRedstoneArmor,
        global_registry.enableChickenArmor, global_registry.enableSlimeArmor, global_registry.enableGuardianArmor, global_registry.enableSuperStarArmor, global_registry.enableEnderDragonArmor,
        global_registry.enableArditeArmor, global_registry.enableCobaltArmor, global_registry.enableManyullynArmor, global_registry.enablePigIronArmor, global_registry.enableKnightSlimeArmor
    };
    public static Swords[] swordType = new Swords[]{
        Swords.COAL, Swords.LAPIS, Swords.REDSTONE, Swords.EMERALD, Swords.OBSIDIAN, Swords.INFUSED_LAVA, Swords.GUARDIAN, Swords.SUPER_STAR, Swords.ENDER_DRAGON
    };
    public static BattleAxes[] battleAxeType = new BattleAxes[]{
        BattleAxes.COAL, BattleAxes.LAPIS, BattleAxes.REDSTONE, BattleAxes.EMERALD, BattleAxes.OBSIDIAN, BattleAxes.INFUSED_LAVA, BattleAxes.GUARDIAN, BattleAxes.SUPER_STAR, BattleAxes.ENDER_DRAGON
    };
    public static Bows[] bowType = new Bows[]{
        Bows.COAL, Bows.LAPIS, Bows.REDSTONE, Bows.EMERALD, Bows.OBSIDIAN, Bows.INFUSED_LAVA, Bows.GUARDIAN, Bows.SUPER_STAR, Bows.ENDER_DRAGON
    };
    public static boolean[] isSwordEnabled = new boolean[]{
        global_registry.enableCoalWeapons[0], global_registry.enableLapisWeapons[0], global_registry.enableRedstoneWeapons[0], global_registry.enableEmeraldWeapons[0], global_registry.enableObsidianWeapons[0], global_registry.enableLavaWeapons[0], global_registry.enableGuardianWeapons[0], global_registry.enableSuperStarWeapons[0], global_registry.enableEnderDragonWeapons[0]
    };
    public static boolean[] isBattleAxeEnabled = new boolean[]{
        global_registry.enableCoalWeapons[1], global_registry.enableLapisWeapons[1], global_registry.enableRedstoneWeapons[1], global_registry.enableEmeraldWeapons[1], global_registry.enableObsidianWeapons[1], global_registry.enableLavaWeapons[1], global_registry.enableGuardianWeapons[1], global_registry.enableSuperStarWeapons[1], global_registry.enableEnderDragonWeapons[1]
    };
    public static boolean[] isBowEnabled = new boolean[]{
        global_registry.enableCoalWeapons[2], global_registry.enableLapisWeapons[2], global_registry.enableRedstoneWeapons[2], global_registry.enableEmeraldWeapons[2], global_registry.enableObsidianWeapons[2], global_registry.enableLavaWeapons[2], global_registry.enableGuardianWeapons[2], global_registry.enableSuperStarWeapons[2], global_registry.enableEnderDragonWeapons[2]
    };

    //  public static ItemAPBook bookInfo;
    //  public static ItemBase steelIngot;
    //  public static ItemBase electricalIngot;
    //  public static ItemRedstoneApple itemRedstoneApple;
    //  public static ItemLavaCrystal itemLavaCrystal;
    //  public static ItemLavaCrystal itemInfusedLavaCrystal;
    //  public static ItemTGOTG itemTGOTG;
    //  public static ItemMaterial chainmail;
    //  public static ItemMaterial guardianScale;
    //  public static ItemMaterial witherBone;
    //  public static ItemMaterial enderDragonScale;
    //  public static ItemMaterial theUltimateMaterial;
    //  public static ItemDevTool itemDevTool;
    //  public static ItemCosmetic twitchItem;
    //  public static ItemCosmetic beamItem;
    //  public static ItemCosmetic theDragonTeamItem;
    //  public static ItemCosmetic moddedCityItem;
    //  public static ItemCosmetic jonBamsItem;
    //  public static ItemCosmetic btmMoon;
    //  public static ItemCosmetic m1Jordan;
    //  public static ItemCosmetic teamRapture;
    //  public static ItemSpecialArrow itemCoalArrow;
    //  public static ItemSpecialArrow itemLapisArrow;
    //  public static ItemSpecialArrow itemRedstoneArrow;
    //  public static ItemSpecialArrow itemLavaArrow;
    //  public static ItemSpecialArrow itemEnderDragonArrow;
    //  //public static ItemBaseHorseArmor[] horseArmors;
    //  public static APArmorMaterial[] horseMaterial =new APArmorMaterial[]{APArmorMaterial.COAL, APArmorMaterial.LAPIS, APArmorMaterial.REDSTONE, APArmorMaterial.EMERALD, APArmorMaterial.OBSIDIAN, APArmorMaterial.INFUSED_LAVA, APArmorMaterial.GUARDIAN, APArmorMaterial.SUPER_STAR, APArmorMaterial.ENDER_DRAGON},;
    //  public static ItemSpawnStructure towerSpawnItem;
    //  public static ItemSpawnStructure enderDungeonFloor1SpawnItem;
    //  public static ItemArmorBase[] coal;
    //  public static ItemArmorBase[] emerald;
    //  public static ItemArmorBase[] lapis;
    //  public static ItemArmorBase[] redstone;
    //  public static ItemArmorBase[] obsidian;
    //  public static ItemArmorBase[] lava;
    //  public static ItemArmorBase[] chicken;
    //  public static ItemArmorBase[] slime;
    //  public static ItemArmorBase[] guardian;
    //  public static ItemArmorBase[] superStar;
    //  public static ItemArmorBase[] enderDragon;
    //  public static ItemArmorBase[] ardite;
    //  public static ItemArmorBase[] cobalt;
    //  public static ItemArmorBase[] manyullyn;
    //  public static ItemArmorBase[] pigIron;
    //  public static ItemArmorBase[] knightSlime;
    //  public static ItemUltimateArmor[] theUltimate;
    //  public static ItemSpecialSword[] sword;
    //  public static ItemSpecialBattleAxe[] battleAxe;
    //  public static ItemSpecialBow[] bow;
    //  public static ItemUltimatePart[] theUltimateParts;
    //  public static ItemArmorV2[] coalExp;
    //  public static ItemArmorV2[] chickenExp;
    //  public static ItemArmorV2[] slimeExp;
    //  public static ItemArmorV2[] emeraldExp;
    //  public static ItemArmorV2[] lapisExp;
    //  public static ItemArmorV2[] redstoneExp;
    //  public static ItemArmorV2[] obsidianExp;
    //  public static ItemArmorV2[] lavaExp;
    //  public static ItemArmorV2[] guardianExp;
    //  public static ItemArmorV2[] superStarExp;
    //  public static ItemArmorV2[] enderDragonExp;
    //  public static ItemArmorV2[] arditeExp;
    //  public static ItemArmorV2[] cobaltExp;
    //  public static ItemArmorV2[] manyullynExp;
    //  public static ItemArmorV2[] pigIronExp;
    //  public static ItemArmorV2[] knightSlimeExp;
    //  public static ItemArmorV2[] ultimateExp;
    //


    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Registration {
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            //       IForgeRegistry<Block> blockRegistry = event.getRegistry();
//
            //       blockRegistry.registerAll(
            //       );
        }

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {

        }
    }
}
