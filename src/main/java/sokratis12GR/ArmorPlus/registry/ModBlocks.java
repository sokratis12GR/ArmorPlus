package sokratis12GR.ArmorPlus.registry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12GR.ArmorPlus.blocks.BlockArmorWorkshop;

/**
 * Created by Socrates on 4/17/2016.
 */
public class ModBlocks {

    public static BlockArmorWorkshop BlockArmorWorkshop;

    public static void init() {
        BlockArmorWorkshop = new BlockArmorWorkshop();
    }
}