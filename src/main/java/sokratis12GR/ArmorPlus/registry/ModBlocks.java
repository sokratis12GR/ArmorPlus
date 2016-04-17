package sokratis12GR.ArmorPlus.registry;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12GR.ArmorPlus.blocks.BlockArmorWorkshop;

import static net.minecraftforge.fml.common.registry.GameRegistry.register;

/**
 * Created by Socrates on 4/17/2016.
 */
public class ModBlocks {

    public static Block blockArmorWorkshop;

    public static void init() {
        blockArmorWorkshop = register(new BlockArmorWorkshop());
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        BlockArmorWorkshop.initModel();
    }
}