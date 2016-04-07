package sokratis12GR.ArmorPlus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12GR.ArmorPlus.ArmorPlus;
import sokratis12GR.ArmorPlus.resources.ArmorPlusBlocks;

public class ArmorWorkshop extends Block {

    public ArmorWorkshop() {
        super(Material.iron);
        GameRegistry.registerBlock(this, "ArmorWorkshop");
        setRegistryName("ArmorWorkshop");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName("ArmorWorkshop");     // Used for localization (en_US.lang)
        this.setCreativeTab(ArmorPlus.tabArmorPlus);
        this.setHardness(4.0F);
        this.setHarvestLevel("pickaxe", 2);
        this.setStepSound(SoundType.METAL);
        this.setResistance(100.0F);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ArmorPlusBlocks.ArmorWorkshop), 0, new ModelResourceLocation("armorplus:ArmorWorkshop", "inventory"));
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }

}
