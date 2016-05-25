package sokratis12GR.ArmorPlus.armors.tconstruct;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
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
import sokratis12GR.ArmorPlus.ArmorPlus;
import sokratis12GR.ArmorPlus.resources.ConfigHandler;

public class ArditeArmor {

    public static Item helmet;
    public static Item chestplate;
    public static Item legs;
    public static Item boots;
    public Object instance;

    public void load(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:ArditeHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:ArditeChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:ArditeLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:ArditeBoots", "inventory"));
        }
        if (ConfigHandler.enableArditeArmorRecipes) {
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"XXX", "CCC", "CXC", Character.valueOf('C'), new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 1)});
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"CCC", "CXC", "XXX", Character.valueOf('C'), new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 1)});
            GameRegistry.addRecipe(new ItemStack(chestplate, 1), new Object[]
                    {"CXC", "CCC", "CCC", Character.valueOf('C'), new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 1)});
            GameRegistry.addRecipe(new ItemStack(legs, 1), new Object[]
                    {"CCC", "CXC", "CXC", Character.valueOf('C'), new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 1)});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"XXX", "CXC", "CXC", Character.valueOf('C'), new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 1)});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"CXC", "CXC", "XXX", Character.valueOf('C'), new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 1)});
            helmet.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
            chestplate.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
            legs.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
            boots.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        }

    }

    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:ArditeHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:ArditeChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:ArditeLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:ArditeBoots", "inventory"));
        }
    }

    public void registerRenderers() {
    }

    static {
        ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("ARDITEARMOR", ArmorPlus.MODID + ":" + "ArditeArmor", 55, new int[]
                {4, 8, 10, 4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            }
        }).setUnlocalizedName("ArditeHelmet");
        helmet.setMaxStackSize(1);
        chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST) {
            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            }
        }).setUnlocalizedName("ArditeChestplate");
        chestplate.setMaxStackSize(1);
        legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS) {
            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            }
        }).setUnlocalizedName("ArditeLeggings");
        legs.setMaxStackSize(1);
        boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET) {
            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {

            }
        }).setUnlocalizedName("ArditeBoots");
        boots.setMaxStackSize(1);
        GameRegistry.registerItem(helmet, "ArditeHelmet");
        GameRegistry.registerItem(chestplate, "ArditeChestplate");
        GameRegistry.registerItem(legs, "ArditeLeggings");
        GameRegistry.registerItem(boots, "ArditeBoots");
    }
}
