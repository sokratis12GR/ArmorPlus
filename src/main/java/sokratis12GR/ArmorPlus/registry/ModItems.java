package sokratis12gr.armorplus.registry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12gr.armorplus.items.books.ArmorPlusBook;
import sokratis12gr.armorplus.items.books.ArmorPlusInfoBook;
import sokratis12gr.armorplus.items.consumables.RedstoneApple;
import sokratis12gr.armorplus.items.consumables.TheGiftOfTheGods;
import sokratis12gr.armorplus.items.materials.*;

public class ModItems {

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
    }
}
