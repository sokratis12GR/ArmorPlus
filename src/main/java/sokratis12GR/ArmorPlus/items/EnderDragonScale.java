package sokratis12GR.ArmorPlus.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sokratis12GR.ArmorPlus.ArmorPlus;


public class EnderDragonScale {

    public static Item enderdragon_scale;

    public static void init()
    {
        enderdragon_scale = new Item().setUnlocalizedName("enderdragon_scale");
    }

    public static void register()
    {
        GameRegistry.registerItem(enderdragon_scale, enderdragon_scale.getUnlocalizedName().substring(5)); //"tile.enderdragon_scale"
    }

    public static void registerRenders()
    {
        registerRender(enderdragon_scale);
    }

    public static void registerRender(Item item)
    {

                Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(ArmorPlus.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

}
