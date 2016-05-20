package sokratis12GR.ArmorPlus.armors.tconstruct;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
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

public class ManyullymArmor {

    public static Item helmet;
    public static Item chestplate;
    public static Item legs;
    public static Item boots;
    public Object instance;

    public void load(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:ManyullymHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:ManyullymChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:ManyullymLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:ManyullymBoots", "inventory"));
        }
        if (ConfigHandler.enableManyullymArmorRecipes) {
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"XXX", "CCC", "CXC", Character.valueOf('C'), new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 2)});
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"CCC", "CXC", "XXX", Character.valueOf('C'), new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 2)});
            GameRegistry.addRecipe(new ItemStack(chestplate, 1), new Object[]
                    {"CXC", "CCC", "CCC", Character.valueOf('C'), new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 2)});
            GameRegistry.addRecipe(new ItemStack(legs, 1), new Object[]
                    {"CCC", "CXC", "CXC", Character.valueOf('C'), new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 2)});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"XXX", "CXC", "CXC", Character.valueOf('C'), new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 2)});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"CXC", "CXC", "XXX", Character.valueOf('C'), new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 2)});
            helmet.setCreativeTab(ArmorPlus.tabArmorPlus);
            chestplate.setCreativeTab(ArmorPlus.tabArmorPlus);
            legs.setCreativeTab(ArmorPlus.tabArmorPlus);
            boots.setCreativeTab(ArmorPlus.tabArmorPlus);
        }

    }

    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:ManyullymHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:ManyullymChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:ManyullymLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:ManyullymBoots", "inventory"));
        }
    }

    public void registerRenderers() {
    }

    static {
        ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("MANYULLYMARMOR", ArmorPlus.MODID + ":" + "ManyullymArmor", 66, new int[]
                {5, 10, 12, 5}, 30, SoundEvents.item_armor_equip_diamond);
        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&5" + "Gives you Strength 2, " + "Cold-Blooded"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            }
        }).setUnlocalizedName("ManyullymHelmet");
        helmet.setMaxStackSize(1);
        chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&5" + "Gives you Strength 2, " + "Cold-Blooded"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            }
        }).setUnlocalizedName("ManyullymChestplate");
        chestplate.setMaxStackSize(1);
        legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&5" + "Gives you Strength 2, " + "Cold-Blooded"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            }
        }).setUnlocalizedName("ManyullymLeggings");
        legs.setMaxStackSize(1);
        boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&5" + "Gives you Strength 2, " + "Cold-Blooded"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {

            }
        }).setUnlocalizedName("ManyullymBoots");
        boots.setMaxStackSize(1);
        GameRegistry.registerItem(helmet, "ManyullymHelmet");
        GameRegistry.registerItem(chestplate, "ManyullymChestplate");
        GameRegistry.registerItem(legs, "ManyullymLeggings");
        GameRegistry.registerItem(boots, "ManyullymBoots");
    }
}
