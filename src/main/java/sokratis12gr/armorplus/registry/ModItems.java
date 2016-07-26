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
import sokratis12gr.armorplus.items.books.ArmorPlusBook;
import sokratis12gr.armorplus.items.books.ArmorPlusInfoBook;
import sokratis12gr.armorplus.items.consumables.RedstoneApple;
import sokratis12gr.armorplus.items.consumables.TheGiftOfTheGods;
import sokratis12gr.armorplus.items.materials.*;

public class ModItems {

    public static ItemArmor.ArmorMaterial DEV_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("DEVARMOR", ArmorPlus.MODID + ":" + "DevArmor", 100000,
            new int[]{100, 100, 100, 100}, 100, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 100.0F);

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
    }
}
