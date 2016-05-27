package sokratis12GR.ArmorPlus.registry;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12GR.ArmorPlus.items.*;

public class ModItems {

    public static EnderDragonScale ENDER_DRAGON_SCALE;
    public static Chainmail CHAINMAIL;
    public static ReinforcingMaterial REINFORCING_MATERIAL;

    public static void init() {
        ENDER_DRAGON_SCALE = new EnderDragonScale();
        CHAINMAIL = new Chainmail();
        REINFORCING_MATERIAL = new ReinforcingMaterial();
        Item.REGISTRY.containsKey(new ResourceLocation("tconstruct", "ingots"));
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        ENDER_DRAGON_SCALE.initModel();
        CHAINMAIL.initModel();
        REINFORCING_MATERIAL.initModel();
    }
}
