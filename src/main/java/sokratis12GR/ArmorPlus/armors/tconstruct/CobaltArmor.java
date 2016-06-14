package sokratis12GR.ArmorPlus.armors.tconstruct;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
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

public class CobaltArmor {

    public static Item helmet;
    public static Item chestplate;
    public static Item legs;
    public static Item boots;
    public Object instance;

    public void load(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:CobaltHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:CobaltChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:CobaltLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:CobaltBoots", "inventory"));
        }
        if (ConfigHandler.easyMode && ConfigHandler.enableCobaltArmorRecipes) {
            GameRegistry.addRecipe(new ItemStack(helmet, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 0));
            GameRegistry.addRecipe(new ItemStack(helmet, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 0));
            GameRegistry.addRecipe(new ItemStack(chestplate, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 0));
            GameRegistry.addRecipe(new ItemStack(legs, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 0));
            GameRegistry.addRecipe(new ItemStack(boots, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 0));
            GameRegistry.addRecipe(new ItemStack(boots, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 0));
        }
        if (ConfigHandler.expertMode && ConfigHandler.enableCobaltArmorRecipes) {
            GameRegistry.addRecipe(new ItemStack(helmet, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 0));
            GameRegistry.addRecipe(new ItemStack(helmet, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 0));
            GameRegistry.addRecipe(new ItemStack(chestplate, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 0));
            GameRegistry.addRecipe(new ItemStack(legs, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 0));
            GameRegistry.addRecipe(new ItemStack(boots, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 0));
            GameRegistry.addRecipe(new ItemStack(boots, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 0));
        }
        helmet.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        chestplate.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        legs.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        boots.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
    }

    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:CobaltHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:CobaltChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:CobaltLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:CobaltBoots", "inventory"));
        }
    }

    public void registerRenderers() {
    }

    static {
        ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("COBALTARMOR", ArmorPlus.MODID + ":" + "CobaltArmor", 44, new int[]
                {3, 7, 9, 3}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            }

            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText(TextFormatting.BLUE + "Gives you Haste 3"));
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.BLUE + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

        }).setUnlocalizedName("CobaltHelmet");
        helmet.setMaxStackSize(1);
        chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST) {
            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            }

            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText(TextFormatting.BLUE + "Gives you Haste 3"));
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.BLUE + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

        }).setUnlocalizedName("CobaltChestplate");
        chestplate.setMaxStackSize(1);
        legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS) {
            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            }

            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText(TextFormatting.BLUE + "Gives you Haste 3"));
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.BLUE + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

        }).setUnlocalizedName("CobaltLeggings");
        legs.setMaxStackSize(1);
        boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET) {
            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {

            }

            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText(TextFormatting.BLUE + "Gives you Haste 3"));
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.BLUE + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

        }).setUnlocalizedName("CobaltBoots");
        boots.setMaxStackSize(1);
        GameRegistry.registerItem(helmet, "CobaltHelmet");
        GameRegistry.registerItem(chestplate, "CobaltChestplate");
        GameRegistry.registerItem(legs, "CobaltLeggings");
        GameRegistry.registerItem(boots, "CobaltBoots");
    }
}
