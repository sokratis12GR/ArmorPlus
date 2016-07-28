package sokratis12gr.armorplus.registry;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12gr.armorplus.ArmorPlus;
import sokratis12gr.armorplus.armors.dev.DevBoots;
import sokratis12gr.armorplus.armors.dev.DevChestplate;
import sokratis12gr.armorplus.armors.dev.DevHelmet;
import sokratis12gr.armorplus.armors.dev.DevLeggings;
import sokratis12gr.armorplus.armors.origin.coal.CoalBoots;
import sokratis12gr.armorplus.armors.origin.coal.CoalChestplate;
import sokratis12gr.armorplus.armors.origin.coal.CoalHelmet;
import sokratis12gr.armorplus.armors.origin.coal.CoalLeggings;
import sokratis12gr.armorplus.armors.origin.emerald.EmeraldBoots;
import sokratis12gr.armorplus.armors.origin.emerald.EmeraldChestplate;
import sokratis12gr.armorplus.armors.origin.emerald.EmeraldHelmet;
import sokratis12gr.armorplus.armors.origin.emerald.EmeraldLeggings;
import sokratis12gr.armorplus.armors.origin.lapis.LapisBoots;
import sokratis12gr.armorplus.armors.origin.lapis.LapisChestplate;
import sokratis12gr.armorplus.armors.origin.lapis.LapisHelmet;
import sokratis12gr.armorplus.armors.origin.lapis.LapisLeggings;
import sokratis12gr.armorplus.armors.origin.lava.LavaBoots;
import sokratis12gr.armorplus.armors.origin.lava.LavaChestplate;
import sokratis12gr.armorplus.armors.origin.lava.LavaHelmet;
import sokratis12gr.armorplus.armors.origin.lava.LavaLeggings;
import sokratis12gr.armorplus.armors.origin.obsidian.ObsidianBoots;
import sokratis12gr.armorplus.armors.origin.obsidian.ObsidianChestplate;
import sokratis12gr.armorplus.armors.origin.obsidian.ObsidianHelmet;
import sokratis12gr.armorplus.armors.origin.obsidian.ObsidianLeggings;
import sokratis12gr.armorplus.armors.origin.redstone.RedstoneBoots;
import sokratis12gr.armorplus.armors.origin.redstone.RedstoneChestplate;
import sokratis12gr.armorplus.armors.origin.redstone.RedstoneHelmet;
import sokratis12gr.armorplus.armors.origin.redstone.RedstoneLeggings;
import sokratis12gr.armorplus.armors.reinforced.rcarmor.RCBoots;
import sokratis12gr.armorplus.armors.reinforced.rcarmor.RCChestplate;
import sokratis12gr.armorplus.armors.reinforced.rcarmor.RCHelmet;
import sokratis12gr.armorplus.armors.reinforced.rcarmor.RCLeggings;
import sokratis12gr.armorplus.armors.reinforced.rdarmor.RDBoots;
import sokratis12gr.armorplus.armors.reinforced.rdarmor.RDChestplate;
import sokratis12gr.armorplus.armors.reinforced.rdarmor.RDHelmet;
import sokratis12gr.armorplus.armors.reinforced.rdarmor.RDLeggings;
import sokratis12gr.armorplus.armors.reinforced.rgarmor.RGBoots;
import sokratis12gr.armorplus.armors.reinforced.rgarmor.RGChestplate;
import sokratis12gr.armorplus.armors.reinforced.rgarmor.RGHelmet;
import sokratis12gr.armorplus.armors.reinforced.rgarmor.RGLeggings;
import sokratis12gr.armorplus.armors.reinforced.riarmor.RIBoots;
import sokratis12gr.armorplus.armors.reinforced.riarmor.RIChestplate;
import sokratis12gr.armorplus.armors.reinforced.riarmor.RIHelmet;
import sokratis12gr.armorplus.armors.reinforced.riarmor.RILeggings;
import sokratis12gr.armorplus.items.books.ArmorPlusBook;
import sokratis12gr.armorplus.items.books.ArmorPlusInfoBook;
import sokratis12gr.armorplus.items.consumables.RedstoneApple;
import sokratis12gr.armorplus.items.consumables.TheGiftOfTheGods;
import sokratis12gr.armorplus.items.materials.*;

public class ModItems {

    public static ItemArmor.ArmorMaterial DEV_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("DEVARMOR", ArmorPlus.MODID + ":" + "DevArmor", 100000, new int[]
            {100, 100, 100, 100}, 100, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 100.0F);

    public static ItemArmor.ArmorMaterial COAL_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("COALARMOR", ArmorPlus.MODID + ":" + "CoalArmor", 7, new int[]
            {1, 2, 3, 1}, 8, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);

    public static ItemArmor.ArmorMaterial EMERALD_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("EMERALDARMOR", ArmorPlus.MODID + ":" + "EmeraldArmor", 35, new int[]
            {3, 6, 9, 4}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public static ItemArmor.ArmorMaterial LAPIS_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("LAPISARMOR", ArmorPlus.MODID + ":" + "LapisArmor", 11, new int[]
            {1, 3, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);

    public static ItemArmor.ArmorMaterial LAVA_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("LAVAARMOR", ArmorPlus.MODID + ":" + "LavaArmor", 45, new int[]
            {4, 8, 11, 6}, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public static ItemArmor.ArmorMaterial OBSIDIAN_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("OBSIDIANARMOR", ArmorPlus.MODID + ":" + "ObsidianArmor", 40, new int[]
            {3, 7, 10, 5}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F);

    public static ItemArmor.ArmorMaterial REDSTONE_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("REDSTONEARMOR", ArmorPlus.MODID + ":" + "RedstoneArmor", 11, new int[]
            {1, 3, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);

    public static ItemArmor.ArmorMaterial RC_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("RCARMOR", ArmorPlus.MODID + ":" + "RCArmor", 18, new int[]
            {2, 5, 6, 3}, 30, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F);

    public static ItemArmor.ArmorMaterial RD_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("RDARMOR", ArmorPlus.MODID + ":" + "RDArmor", 35, new int[]
            {4, 7, 9, 4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public static ItemArmor.ArmorMaterial RG_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("RGARMOR", ArmorPlus.MODID + ":" + "RGArmor", 10, new int[]
            {2, 4, 6, 3}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);

    public static ItemArmor.ArmorMaterial RI_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("RIARMOR", ArmorPlus.MODID + ":" + "RIArmor", 18, new int[]
            {3, 6, 7, 3}, 30, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);

    public static Chainmail CHAINMAIL;
    public static ReinforcingMaterial REINFORCING_MATERIAL;
    public static GuardianScale GUARDIAN_SCALE;
    public static WitherBone WITHER_BONE;
    public static EnderDragonScale ENDER_DRAGON_SCALE;
    public static TheUltimateMaterial THE_ULTIMATE_MATERIAL;
    public static LavaCrystal LAVA_CRYSTAL;
    public static TheGiftOfTheGods THE_GIFT_OF_THE_GODS;
    public static ArmorPlusBook ARMORPLUS_BOOK;
    public static SteelIngot STEEL_INGOT;
    public static ElectricalIngot ELECTRICAL_INGOT;
    public static RedstoneApple REDSTONE_APPLE;
    public static ArmorPlusInfoBook ARMORPLUS_INFO_BOOK;

    public static DevHelmet DEV_HELMET;
    public static DevChestplate DEV_CHESTPLATE;
    public static DevLeggings DEV_LEGGINGS;
    public static DevBoots DEV_BOOTS;

    public static CoalHelmet COAL_HELMET;
    public static CoalChestplate COAL_CHESTPLATE;
    public static CoalLeggings COAL_LEGGINGS;
    public static CoalBoots COAL_BOOTS;

    public static EmeraldHelmet EMERALD_HELMET;
    public static EmeraldChestplate EMERALD_CHESTPLATE;
    public static EmeraldLeggings EMERALD_LEGGINGS;
    public static EmeraldBoots EMERALD_BOOTS;

    public static LapisHelmet LAPIS_HELMET;
    public static LapisChestplate LAPIS_CHESTPLATE;
    public static LapisLeggings LAPIS_LEGGINGS;
    public static LapisBoots LAPIS_BOOTS;

    public static LavaHelmet LAVA_HELMET;
    public static LavaChestplate LAVA_CHESTPLATE;
    public static LavaLeggings LAVA_LEGGINGS;
    public static LavaBoots LAVA_BOOTS;

    public static ObsidianHelmet OBSIDIAN_HELMET;
    public static ObsidianChestplate OBSIDIAN_CHESTPLATE;
    public static ObsidianLeggings OBSIDIAN_LEGGINGS;
    public static ObsidianBoots OBSIDIAN_BOOTS;

    public static RedstoneHelmet REDSTONE_HELMET;
    public static RedstoneChestplate REDSTONE_CHESTPLATE;
    public static RedstoneLeggings REDSTONE_LEGGINGS;
    public static RedstoneBoots REDSTONE_BOOTS;

    public static RCHelmet RC_HELMET;
    public static RCChestplate RC_CHESTPLATE;
    public static RCLeggings RC_LEGGINGS;
    public static RCBoots RC_BOOTS;


    public static RDHelmet RD_HELMET;
    public static RDChestplate RD_CHESTPLATE;
    public static RDLeggings RD_LEGGINGS;
    public static RDBoots RD_BOOTS;


    public static RGHelmet RG_HELMET;
    public static RGChestplate RG_CHESTPLATE;
    public static RGLeggings RG_LEGGINGS;
    public static RGBoots RG_BOOTS;

    public static RIHelmet RI_HELMET;
    public static RIChestplate RI_CHESTPLATE;
    public static RILeggings RI_LEGGINGS;
    public static RIBoots RI_BOOTS;

    public static void init() {
        CHAINMAIL = new Chainmail();
        REINFORCING_MATERIAL = new ReinforcingMaterial();
        GUARDIAN_SCALE = new GuardianScale();
        WITHER_BONE = new WitherBone();
        ENDER_DRAGON_SCALE = new EnderDragonScale();
        THE_ULTIMATE_MATERIAL = new TheUltimateMaterial();
        LAVA_CRYSTAL = new LavaCrystal();
        THE_GIFT_OF_THE_GODS = new TheGiftOfTheGods();
        ARMORPLUS_BOOK = new ArmorPlusBook();
        STEEL_INGOT = new SteelIngot();
        ELECTRICAL_INGOT = new ElectricalIngot();
        REDSTONE_APPLE = new RedstoneApple(4, 2.0f, false);
        ARMORPLUS_INFO_BOOK = new ArmorPlusInfoBook();

        DEV_HELMET = new DevHelmet();
        DEV_CHESTPLATE = new DevChestplate();
        DEV_LEGGINGS = new DevLeggings();
        DEV_BOOTS = new DevBoots();

        COAL_HELMET = new CoalHelmet();
        COAL_CHESTPLATE = new CoalChestplate();
        COAL_LEGGINGS = new CoalLeggings();
        COAL_BOOTS = new CoalBoots();

        EMERALD_HELMET = new EmeraldHelmet();
        EMERALD_CHESTPLATE = new EmeraldChestplate();
        EMERALD_LEGGINGS = new EmeraldLeggings();
        EMERALD_BOOTS = new EmeraldBoots();

        LAPIS_HELMET = new LapisHelmet();
        LAPIS_CHESTPLATE = new LapisChestplate();
        LAPIS_LEGGINGS = new LapisLeggings();
        LAPIS_BOOTS = new LapisBoots();

        LAVA_HELMET = new LavaHelmet();
        LAVA_CHESTPLATE = new LavaChestplate();
        LAVA_LEGGINGS = new LavaLeggings();
        LAVA_BOOTS = new LavaBoots();

        OBSIDIAN_HELMET = new ObsidianHelmet();
        OBSIDIAN_CHESTPLATE = new ObsidianChestplate();
        OBSIDIAN_LEGGINGS = new ObsidianLeggings();
        OBSIDIAN_BOOTS = new ObsidianBoots();

        REDSTONE_HELMET = new RedstoneHelmet();
        REDSTONE_CHESTPLATE = new RedstoneChestplate();
        REDSTONE_LEGGINGS = new RedstoneLeggings();
        REDSTONE_BOOTS = new RedstoneBoots();

        RC_HELMET = new RCHelmet();
        RC_CHESTPLATE = new RCChestplate();
        RC_LEGGINGS = new RCLeggings();
        RC_BOOTS = new RCBoots();

        RD_HELMET = new RDHelmet();
        RD_CHESTPLATE = new RDChestplate();
        RD_LEGGINGS = new RDLeggings();
        RD_BOOTS = new RDBoots();

        RG_HELMET = new RGHelmet();
        RG_CHESTPLATE = new RGChestplate();
        RG_LEGGINGS = new RGLeggings();
        RG_BOOTS = new RGBoots();

        RI_HELMET = new RIHelmet();
        RI_CHESTPLATE = new RIChestplate();
        RI_LEGGINGS = new RILeggings();
        RI_BOOTS = new RIBoots();
    }


    @SideOnly(Side.CLIENT)
    public static void initModels() {
        CHAINMAIL.initModel();
        REINFORCING_MATERIAL.initModel();
        GUARDIAN_SCALE.initModel();
        WITHER_BONE.initModel();
        ENDER_DRAGON_SCALE.initModel();
        THE_ULTIMATE_MATERIAL.initModel();
        LAVA_CRYSTAL.initModel();
        THE_GIFT_OF_THE_GODS.initModel();
        ARMORPLUS_BOOK.initModel();
        STEEL_INGOT.initModel();
        ELECTRICAL_INGOT.initModel();
        REDSTONE_APPLE.initModel();
        ARMORPLUS_INFO_BOOK.initModel();

        DEV_HELMET.initModel();
        DEV_CHESTPLATE.initModel();
        DEV_LEGGINGS.initModel();
        DEV_BOOTS.initModel();

        COAL_HELMET.initModel();
        COAL_CHESTPLATE.initModel();
        COAL_LEGGINGS.initModel();
        COAL_BOOTS.initModel();

        EMERALD_HELMET.initModel();
        EMERALD_CHESTPLATE.initModel();
        EMERALD_LEGGINGS.initModel();
        EMERALD_BOOTS.initModel();

        LAPIS_HELMET.initModel();
        LAPIS_CHESTPLATE.initModel();
        LAPIS_LEGGINGS.initModel();
        LAPIS_BOOTS.initModel();

        LAVA_HELMET.initModel();
        LAVA_CHESTPLATE.initModel();
        LAVA_LEGGINGS.initModel();
        LAVA_BOOTS.initModel();

        OBSIDIAN_HELMET.initModel();
        OBSIDIAN_CHESTPLATE.initModel();
        OBSIDIAN_LEGGINGS.initModel();
        OBSIDIAN_BOOTS.initModel();

        REDSTONE_HELMET.initModel();
        REDSTONE_CHESTPLATE.initModel();
        REDSTONE_LEGGINGS.initModel();
        REDSTONE_BOOTS.initModel();

        RC_HELMET.initModel();
        RC_CHESTPLATE.initModel();
        RC_LEGGINGS.initModel();
        RC_BOOTS.initModel();

        RD_HELMET.initModel();
        RD_CHESTPLATE.initModel();
        RD_LEGGINGS.initModel();
        RD_BOOTS.initModel();

        RG_HELMET.initModel();
        RG_CHESTPLATE.initModel();
        RG_LEGGINGS.initModel();
        RG_BOOTS.initModel();

        RI_HELMET.initModel();
        RI_CHESTPLATE.initModel();
        RI_LEGGINGS.initModel();
        RI_BOOTS.initModel();
    }
}
