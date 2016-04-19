package sokratis12GR.ArmorPlus.armors.reinforced;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
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
import sokratis12GR.ArmorPlus.registry.ModItems;
import sokratis12GR.ArmorPlus.resources.ConfigHandler;

public class RIArmor {

    public static Item helmet;
    public static Item chestplate;
    public static Item legs;
    public static Item boots;
    public Object instance;

    public void load(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:RIHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:RIChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:RILeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:RIBoots", "inventory"));
        }
        if (ConfigHandler.enableReinforcedArmorsRecipes) {
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"XXX", "345", "6L8", Character.valueOf('3'), new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('4'),
                            new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('5'), new ItemStack(ModItems.ReinforcingMaterial, 1),
                            Character.valueOf('6'), new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('8'),
                            new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('L'),
                            new ItemStack(Items.iron_helmet, 1),});
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"012", "3L5", "XXX", Character.valueOf('0'), new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('1'),
                            new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('2'), new ItemStack(ModItems.ReinforcingMaterial, 1),
                            Character.valueOf('3'), new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('5'),
                            new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('L'),
                            new ItemStack(Items.iron_helmet, 1),});
            GameRegistry.addRecipe(new ItemStack(chestplate, 1), new Object[]
                    {"0L2", "345", "678", Character.valueOf('0'), new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('2'),
                            new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('3'), new ItemStack(ModItems.ReinforcingMaterial, 1),
                            Character.valueOf('4'), new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('5'),
                            new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('6'), new ItemStack(ModItems.ReinforcingMaterial, 1),
                            Character.valueOf('7'), new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('8'),
                            new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('L'),
                            new ItemStack(Items.iron_chestplate, 1),});
            GameRegistry.addRecipe(new ItemStack(legs, 1), new Object[]
                    {"012", "3L5", "6X8", Character.valueOf('0'), new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('1'),
                            new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('2'), new ItemStack(ModItems.ReinforcingMaterial, 1),
                            Character.valueOf('3'), new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('5'),
                            new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('6'), new ItemStack(ModItems.ReinforcingMaterial, 1),
                            Character.valueOf('8'), new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('L'),
                            new ItemStack(Items.iron_leggings, 1),});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"XXX", "3L5", "6X8", Character.valueOf('3'), new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('5'),
                            new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('6'), new ItemStack(ModItems.ReinforcingMaterial, 1),
                            Character.valueOf('8'), new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('L'),
                            new ItemStack(Items.iron_boots, 1),});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"0L2", "3X5", "XXX", Character.valueOf('0'), new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('2'),
                            new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('3'), new ItemStack(ModItems.ReinforcingMaterial, 1),
                            Character.valueOf('5'), new ItemStack(ModItems.ReinforcingMaterial, 1), Character.valueOf('L'),
                            new ItemStack(Items.iron_boots, 1),});
            helmet.setCreativeTab(ArmorPlus.tabArmorPlus);
            chestplate.setCreativeTab(ArmorPlus.tabArmorPlus);
            legs.setCreativeTab(ArmorPlus.tabArmorPlus);
            boots.setCreativeTab(ArmorPlus.tabArmorPlus);
        }

    }

    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:RIHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:RIChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:RILeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:RIBoots", "inventory"));
        }
    }

    public void registerRenderers() {
    }

    static {

        ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("RIARMOR", ArmorPlus.MODID + ":" + "RIArmor", 18, new int[]
        {3, 6, 7, 3}, 30, SoundEvents.item_armor_equip_iron);

        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {

            }
            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == Items.iron_ingot;
            }
        }).setUnlocalizedName("RIHelmet");
        helmet.setMaxStackSize(1);
        chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST) {
            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack)
            {
            }
            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == Items.iron_ingot;
            }
        }).setUnlocalizedName("RIChestplate");
        chestplate.setMaxStackSize(1);
        legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS) {
            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack)
            {

            }
            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == Items.iron_ingot;
            }
        }).setUnlocalizedName("RILeggings");
        legs.setMaxStackSize(1);
        boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET) {
            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {


            }
            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == Items.iron_ingot;
            }
        }).setUnlocalizedName("RIBoots");
        boots.setMaxStackSize(1);
        GameRegistry.registerItem(helmet, "RIHelmet");
        GameRegistry.registerItem(chestplate, "RIChestplate");
        GameRegistry.registerItem(legs, "RILeggings");
        GameRegistry.registerItem(boots, "RIBoots");

    }
}
