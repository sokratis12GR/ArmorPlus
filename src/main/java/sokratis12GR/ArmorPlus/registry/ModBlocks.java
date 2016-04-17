package sokratis12GR.ArmorPlus.registry;

import net.minecraft.block.Block;
import sokratis12GR.ArmorPlus.blocks.BlockArmorWorkshop;

import static net.minecraftforge.fml.common.registry.GameRegistry.register;

/**
 * Created by Socrates on 4/17/2016.
 */
public class ModBlocks {

    public static Block BlockArmorWorkshop;

    public static void init() {
        BlockArmorWorkshop = register(new BlockArmorWorkshop());
    }
}