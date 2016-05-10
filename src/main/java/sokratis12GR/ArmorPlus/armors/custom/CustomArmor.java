package sokratis12GR.ArmorPlus.armors.custom;

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
import sokratis12GR.ArmorPlus.resources.ConfigHandler;
import sokratis12GR.ArmorPlus.util.TextHelper;

import java.util.List;

public class CustomArmor {

    public static Item helmet;
    public static Item chestplate;
    public static Item legs;
    public static Item boots;
    public Object instance;

    public void load(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:CustomHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:CustomChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:CustomLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:CustomBoots", "inventory"));
        }
        if (ConfigHandler.enableCustomArmorRecipes) {
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"XXX", "CCC", "CXC", Character.valueOf('C'), new ItemStack(Items.paper, 1)});
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"CCC", "CXC", "XXX", Character.valueOf('C'), new ItemStack(Items.paper, 1)});
            GameRegistry.addRecipe(new ItemStack(chestplate, 1), new Object[]
                    {"CXC", "CCC", "CCC", Character.valueOf('C'), new ItemStack(Items.paper, 1)});
            GameRegistry.addRecipe(new ItemStack(legs, 1), new Object[]
                    {"CCC", "CXC", "CXC", Character.valueOf('C'), new ItemStack(Items.paper, 1)});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"XXX", "CXC", "CXC", Character.valueOf('C'), new ItemStack(Items.paper, 1)});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"CXC", "CXC", "XXX", Character.valueOf('C'), new ItemStack(Items.paper, 1)});
            helmet.setCreativeTab(ArmorPlus.tabArmorPlus);
            chestplate.setCreativeTab(ArmorPlus.tabArmorPlus);
            legs.setCreativeTab(ArmorPlus.tabArmorPlus);
            boots.setCreativeTab(ArmorPlus.tabArmorPlus);
        }

    }

    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:CustomHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:CustomChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:CustomLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:CustomBoots", "inventory"));
        }
    }

    public void registerRenderers() {
    }

    public static int customChestplateProtection;

    public static int customHelmetProtection;

    public static int customBootsProtection;

    public static int customLeggingsProtection;

    static {

        int[] armor;
        armor = new int[4];
        armor[0] = ConfigHandler.customBootsProtection;
        armor[1] = ConfigHandler.customLeggingsProtection;
        armor[2] = ConfigHandler.customChestplateProtection;
        armor[3] = ConfigHandler.customHelmetProtection;
        customChestplateProtection = ConfigHandler.customChestplateProtection;
        customHelmetProtection = ConfigHandler.customHelmetProtection;
        customBootsProtection = ConfigHandler.customBootsProtection;
        customLeggingsProtection = ConfigHandler.customLeggingsProtection;
        ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("CUSTOMARMOR",ArmorPlus.MODID + ":" + "CustomArmor", 100, armor, 30, SoundEvents.item_armor_equip_chain);

        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&b" + "Still WIP don't use or craft it"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == Items.paper;
            }
        }).setUnlocalizedName("CustomHelmet");
        helmet.setMaxStackSize(1);
        chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&b" + "Still WIP don't use or craft it"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == Items.paper;
            }
        }).setUnlocalizedName("CustomChestplate");
        chestplate.setMaxStackSize(1);
        legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&b" + "Still WIP don't use or craft it"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == Items.paper;
            }
        }).setUnlocalizedName("CustomLeggings");
        legs.setMaxStackSize(1);
        boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&b" + "Still WIP don't use or craft it"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {

            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == Items.paper;
            }
        }).setUnlocalizedName("CustomBoots");
        boots.setMaxStackSize(1);
        GameRegistry.registerItem(helmet, "CustomHelmet");
        GameRegistry.registerItem(chestplate, "CustomChestplate");
        GameRegistry.registerItem(legs, "CustomLeggings");
        GameRegistry.registerItem(boots, "CustomBoots");

    }
}
