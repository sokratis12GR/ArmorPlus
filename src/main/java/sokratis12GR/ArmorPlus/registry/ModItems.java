package sokratis12GR.ArmorPlus.registry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12GR.ArmorPlus.items.*;

public class ModItems {

    public static Chainmail CHAINMAIL;
    public static ReinforcingMaterial REINFORCING_MATERIAL;
    /** Special Items */
    public static GuardianScale GUARDIAN_SCALE;
    public static WitherBone WITHER_BONE;
    public static EnderDragonScale ENDER_DRAGON_SCALE;
    public static TheUltimateMaterial THE_ULTIMATE_MATERIAL;
    /** Expert Items */
    public static CompressedObsidian COMPRESSED_OBSIDIAN;


    public static void init() {
        CHAINMAIL = new Chainmail();
        REINFORCING_MATERIAL = new ReinforcingMaterial();
        /** Special Items */
        GUARDIAN_SCALE = new GuardianScale();
        WITHER_BONE = new WitherBone();
        ENDER_DRAGON_SCALE = new EnderDragonScale();
        THE_ULTIMATE_MATERIAL = new TheUltimateMaterial();
        /** Expert Items */
        COMPRESSED_OBSIDIAN = new CompressedObsidian();
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
        /** Expert Items */
        COMPRESSED_OBSIDIAN.initModel();
    }
}
