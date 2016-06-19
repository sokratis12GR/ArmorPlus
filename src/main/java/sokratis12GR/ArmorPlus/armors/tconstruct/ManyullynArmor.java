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
import sokratis12GR.ArmorPlus.util.TextHelper;

import java.util.List;

public class ManyullynArmor {

    public static Item helmet;
    public static Item chestplate;
    public static Item legs;
    public static Item boots;
    public Object instance;

    public void load(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:ManyullynHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:ManyullynChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:ManyullynLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:ManyullynBoots", "inventory"));
        }
        helmet.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        chestplate.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        legs.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        boots.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
    }

    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:ManyullynHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:ManyullynChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:ManyullynLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:ManyullynBoots", "inventory"));
        }
    }

    public void registerRenderers() {
    }

    static {
        ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("MANYULLYNARMOR", ArmorPlus.MODID + ":" + "ManyullynArmor", 66, new int[]
                {5, 10, 12, 5}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F);
        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&5" + "Gives you Strength 2 when using full set, " + "Cold-Blooded"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.DARK_PURPLE + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }
        }).setUnlocalizedName("ManyullynHelmet");
        helmet.setMaxStackSize(1);
        chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&5" + "Gives you Strength 2 when using full set, " + "Cold-Blooded"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.DARK_PURPLE + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }
        }).setUnlocalizedName("ManyullynChestplate");
        chestplate.setMaxStackSize(1);
        legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&5" + "Gives you Strength 2 when using full set, " + "Cold-Blooded"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.DARK_PURPLE + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }
        }).setUnlocalizedName("ManyullynLeggings");
        legs.setMaxStackSize(1);
        boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&5" + "Gives you Strength 2 when using full set, " + "Cold-Blooded"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {

            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.DARK_PURPLE + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }
        }).setUnlocalizedName("ManyullynBoots");
        boots.setMaxStackSize(1);
        GameRegistry.registerItem(helmet, "ManyullynHelmet");
        GameRegistry.registerItem(chestplate, "ManyullynChestplate");
        GameRegistry.registerItem(legs, "ManyullynLeggings");
        GameRegistry.registerItem(boots, "ManyullynBoots");
    }
}
