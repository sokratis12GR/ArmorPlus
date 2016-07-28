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
import sokratis12gr.armorplus.armors.origin.coal.*;
import sokratis12gr.armorplus.armors.origin.emerald.*;
import sokratis12gr.armorplus.armors.origin.lapis.*;
import sokratis12gr.armorplus.armors.origin.lava.*;
import sokratis12gr.armorplus.armors.origin.obsidian.*;
import sokratis12gr.armorplus.armors.origin.redstone.*;
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
    }
}
