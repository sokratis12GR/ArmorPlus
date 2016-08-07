package net.thedragonteam.armorplus.armors.dev;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.registry.ModItems;

/**
 * sokratis12gr.armorplus.armors.dev
 * ArmorPlus created by sokratis12GR on 7/25/2016 10:08 AM.
 */
public class DevBoots extends ItemArmor {

    public static int armorPreffix = 0;

    public DevBoots() {
        super(ModItems.DEV_ARMOR_MATERIAL, armorPreffix, EntityEquipmentSlot.FEET);
        setMaxStackSize(1);
        setRegistryName("dev_boots");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName("DevBoots");     // Used for localization (en_US.lang)
        GameRegistry.register(this);
        setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("armorplus:DevBoots", "inventory"));
    }
}