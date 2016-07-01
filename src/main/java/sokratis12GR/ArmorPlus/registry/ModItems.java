package sokratis12GR.ArmorPlus.registry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12GR.ArmorPlus.items.*;

public class ModItems {

    public static Chainmail CHAINMAIL;
    public static ReinforcingMaterial REINFORCING_MATERIAL;
    /**
     * Special Items
     */
    public static GuardianScale GUARDIAN_SCALE;
    public static WitherBone WITHER_BONE;
    public static EnderDragonScale ENDER_DRAGON_SCALE;
    public static TheUltimateMaterial THE_ULTIMATE_MATERIAL;
    public static LavaCrystal LAVA_CRYSTAL;
    /**
     * The Fift Of The Gods
     */
    public static TheGiftOfTheGods THE_GIFT_OF_THE_GODS;

    /**
     * GUI Items
     */
    public static ArmorPlusBook ARMORPLUS_BOOK;

    public static void init() {
        //EntityItemArmorPlus e = new EntityItemArmorPlus();
        //e.setNoDespawn();

        CHAINMAIL = new Chainmail();
        REINFORCING_MATERIAL = new ReinforcingMaterial();
        /** Special Items */
        GUARDIAN_SCALE = new GuardianScale();
        WITHER_BONE = new WitherBone();
        ENDER_DRAGON_SCALE = new EnderDragonScale();
        THE_ULTIMATE_MATERIAL = new TheUltimateMaterial();
        LAVA_CRYSTAL = new LavaCrystal();
        /** The Fift Of The Gods */
        THE_GIFT_OF_THE_GODS = new TheGiftOfTheGods();
        /** GUI Items */
        ARMORPLUS_BOOK = new ArmorPlusBook();
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        CHAINMAIL.initModel();
        REINFORCING_MATERIAL.initModel();
        /** Special Items */
        GUARDIAN_SCALE.initModel();
        WITHER_BONE.initModel();
        ENDER_DRAGON_SCALE.initModel();
        THE_ULTIMATE_MATERIAL.initModel();
        LAVA_CRYSTAL.initModel();
        /** The Fift Of The Gods */
        THE_GIFT_OF_THE_GODS.initModel();
        /** GUI Items */
        ARMORPLUS_BOOK.initModel();
    }
}
