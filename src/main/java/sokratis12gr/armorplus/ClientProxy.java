package sokratis12gr.armorplus;

import sokratis12gr.armorplus.registry.ModBlocks;
import sokratis12gr.armorplus.registry.ModItems;


public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers(ArmorPlus ins) {
        ModItems.initModels();
        ModBlocks.registerRenders();
    }
}


