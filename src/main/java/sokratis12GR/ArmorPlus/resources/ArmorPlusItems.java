package sokratis12GR.ArmorPlus.resources;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12GR.ArmorPlus.items.EnderDragonScale;

public class ArmorPlusItems {

    public static EnderDragonScale EnderDragonScale;

    public static void init() {
        EnderDragonScale = new EnderDragonScale();
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        EnderDragonScale.initModel();
    }
}
