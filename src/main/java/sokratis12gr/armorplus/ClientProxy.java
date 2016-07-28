package sokratis12gr.armorplus;

import net.minecraftforge.fml.common.registry.GameRegistry;
import sokratis12gr.armorplus.registry.ModBlocks;
import sokratis12gr.armorplus.registry.ModItems;


public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers(ArmorPlus ins) {
        ins.SUPER_STAR_ARMOR.registerRenderers();
        ins.ENDER_DRAGON_ARMOR.registerRenderers();
        ins.GUARDIAN_ARMOR.registerRenderers();
        ins.THE_ULTIMATE_ARMOR.registerRenderers();
        ins.RI_ARMOR.registerRenderers();
        ins.RG_ARMOR.registerRenderers();
        ins.RD_ARMOR.registerRenderers();
        ins.RC_ARMOR.registerRenderers();
        ins.CHICKEN_ARMOR.registerRenderers();
        ins.SLIME_ARMOR.registerRenderers();
        /**  v2 */
        ins.STEEL_ARMOR.registerRenderers();
        ins.ELECTRICAL_ARMOR.registerRenderers();
        /** Tinkers' Construct */
        ins.COBALT_ARMOR.registerRenderers();
        ins.ARDITE_ARMOR.registerRenderers();
        ins.MANYULLYN_ARMOR.registerRenderers();
        ins.PIG_IRON_ARMOR.registerRenderers();
        ins.KNIGHT_SLIME_ARMOR.registerRenderers();
        ModItems.initModels();
        ModBlocks.registerRenders();
    }
}


