package sokratis12gr.armorplus.armors.v2;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import sokratis12gr.armorplus.ARPConfig;
import sokratis12gr.armorplus.ArmorPlus;
import sokratis12gr.armorplus.registry.ModBlocks;
import sokratis12gr.armorplus.registry.ModItems;
import sokratis12gr.armorplus.resources.ConfigHandler;

public class SteelArmor {
    public static EntityLivingBase entityLivingBase;

    public static Item helmet;
    public static Item chestplate;
    public static Item legs;
    public static Item boots;

    static {

        ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("STEELARMOR", ArmorPlus.MODID + ":" + "SteelArmor", 15,
                new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);

        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                if (ARPConfig.recipes == 0) {
                    return repair.getItem() == ModItems.STEEL_INGOT;
                }
                if (ARPConfig.recipes == 1) {
                    return repair.getItem() == Item.getItemFromBlock(ModBlocks.STEEL_BLOCK);
                }
                return true;
            }
        }).setUnlocalizedName("SteelHelmet");
        helmet.setMaxStackSize(1);
        chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST) {
            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                if (ARPConfig.recipes == 0) {
                    return repair.getItem() == ModItems.STEEL_INGOT;
                }
                if (ARPConfig.recipes == 1) {
                    return repair.getItem() == Item.getItemFromBlock(ModBlocks.STEEL_BLOCK);
                }
                return true;
            }
        }).setUnlocalizedName("SteelChestplate");
        chestplate.setMaxStackSize(1);
        legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS) {
            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                if (ARPConfig.recipes == 0) {
                    return repair.getItem() == ModItems.STEEL_INGOT;
                }
                if (ARPConfig.recipes == 1) {
                    return repair.getItem() == Item.getItemFromBlock(ModBlocks.STEEL_BLOCK);
                }
                return true;
            }
        }).setUnlocalizedName("SteelLeggings");
        legs.setMaxStackSize(1);
        boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET) {
            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                if (ARPConfig.recipes == 0) {
                    return repair.getItem() == ModItems.STEEL_INGOT;
                }
                if (ARPConfig.recipes == 1) {
                    return repair.getItem() == Item.getItemFromBlock(ModBlocks.STEEL_BLOCK);
                }
                return true;
            }
        }).setUnlocalizedName("SteelBoots");
        boots.setMaxStackSize(1);
        GameRegistry.registerItem(helmet, "steel_helmet");
        GameRegistry.registerItem(chestplate, "steel_chestplate");
        GameRegistry.registerItem(legs, "steel_leggings");
        GameRegistry.registerItem(boots, "steel_boots");

    }

    public Object instance;

    public void load(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:SteelHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:SteelChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:SteelLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:SteelBoots", "inventory"));
        }
        helmet.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        chestplate.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        legs.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        boots.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
    }

    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:SteelHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:SteelChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:SteelLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:SteelBoots", "inventory"));
        }
    }

    public void registerRenderers() {
    }
}
