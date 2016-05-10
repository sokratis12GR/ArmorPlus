package sokratis12GR.ArmorPlus.registry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12GR.ArmorPlus.items.*;

public class ModItems {

    public static EnderDragonScale EnderDragonScale;
    public static Chainmail Chainmail;
    public static ReinforcingMaterial ReinforcingMaterial;

    public static void init() {
        EnderDragonScale = new EnderDragonScale();
        Chainmail = new Chainmail();
        ReinforcingMaterial = new ReinforcingMaterial();
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        EnderDragonScale.initModel();
        Chainmail.initModel();
        ReinforcingMaterial.initModel();
    }
}
