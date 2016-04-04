package sokratis12GR.ArmorPlus.armors;

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
import sokratis12GR.ArmorPlus.resources.ArmorPlusItems;
import sokratis12GR.ArmorPlus.resources.ConfigHandler;
import sokratis12GR.ArmorPlus.util.TextHelper;

import java.util.List;

public class EnderDragonArmor {

    public static Item helmet;
    public static Item chestplate;
    public static Item legs;
    public static Item boots;
    public Object instance;

    public void load(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:EnderDragonHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:EnderDragonChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:EnderDragonLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:EnderDragonBoots", "inventory"));
        }
        if (ConfigHandler.enableEnderDragonArmorRecipes) {
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"XXX", "345", "6X8", Character.valueOf('3'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1), Character.valueOf('4'),
                            new ItemStack(ArmorPlusItems.EnderDragonScale, 1), Character.valueOf('5'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1),
                            Character.valueOf('6'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1), Character.valueOf('8'),
                            new ItemStack(ArmorPlusItems.EnderDragonScale, 1),});
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"012", "3X5", "XXX", Character.valueOf('0'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1), Character.valueOf('1'),
                            new ItemStack(ArmorPlusItems.EnderDragonScale, 1), Character.valueOf('2'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1),
                            Character.valueOf('3'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1), Character.valueOf('5'),
                            new ItemStack(ArmorPlusItems.EnderDragonScale, 1),});
            GameRegistry.addRecipe(new ItemStack(chestplate, 1), new Object[]
                    {"0X2", "345", "678", Character.valueOf('0'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1), Character.valueOf('2'),
                            new ItemStack(ArmorPlusItems.EnderDragonScale, 1), Character.valueOf('3'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1),
                            Character.valueOf('4'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1), Character.valueOf('5'),
                            new ItemStack(ArmorPlusItems.EnderDragonScale, 1), Character.valueOf('6'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1),
                            Character.valueOf('7'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1), Character.valueOf('8'),
                            new ItemStack(ArmorPlusItems.EnderDragonScale, 1),});
            GameRegistry.addRecipe(new ItemStack(legs, 1), new Object[]
                    {"012", "3X5", "6X8", Character.valueOf('0'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1), Character.valueOf('1'),
                            new ItemStack(ArmorPlusItems.EnderDragonScale, 1), Character.valueOf('2'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1),
                            Character.valueOf('3'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1), Character.valueOf('5'),
                            new ItemStack(ArmorPlusItems.EnderDragonScale, 1), Character.valueOf('6'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1),
                            Character.valueOf('8'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1),});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"XXX", "3X5", "6X8", Character.valueOf('3'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1), Character.valueOf('5'),
                            new ItemStack(ArmorPlusItems.EnderDragonScale, 1), Character.valueOf('6'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1),
                            Character.valueOf('8'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1),});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"0X2", "3X5", "XXX", Character.valueOf('0'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1), Character.valueOf('2'),
                            new ItemStack(ArmorPlusItems.EnderDragonScale, 1), Character.valueOf('3'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1),
                            Character.valueOf('5'), new ItemStack(ArmorPlusItems.EnderDragonScale, 1),});
            helmet.setCreativeTab(ArmorPlus.tabArmorPlus);
            chestplate.setCreativeTab(ArmorPlus.tabArmorPlus);
            legs.setCreativeTab(ArmorPlus.tabArmorPlus);
            boots.setCreativeTab(ArmorPlus.tabArmorPlus);
        }

    }

    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:EnderDragonHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:EnderDragonChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:EnderDragonLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:EnderDragonBoots", "inventory"));
        }
    }

    public void registerRenderers() {
    }

    static {

        ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("ENDERDRAGONARMOR", "EnderDragonArmor", 60, new int[]
                {5, 9, 12, 6}, 30, SoundEvents.item_armor_equip_diamond);

        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&5" + "Gives you the power of the EnderDragon"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {

            }
        }).setUnlocalizedName("EnderDragonHelmet");
        helmet.setMaxStackSize(1);
        chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&5" + "Gives you the power of the EnderDragon"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack)
            {
            }
        }).setUnlocalizedName("EnderDragonChestplate");
        chestplate.setMaxStackSize(1);
        legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&5" + "Gives you the power of the EnderDragon"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack)
            {

            }
        }).setUnlocalizedName("EnderDragonLeggings");
        legs.setMaxStackSize(1);
        boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&5" + "Gives you the power of the EnderDragon"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {


            }
        }).setUnlocalizedName("EnderDragonBoots");
        boots.setMaxStackSize(1);
        GameRegistry.registerItem(helmet, "EnderDragonHelmet");
        GameRegistry.registerItem(chestplate, "EnderDragonChestplate");
        GameRegistry.registerItem(legs, "EnderDragonLeggings");
        GameRegistry.registerItem(boots, "EnderDragonBoots");

    }
}
