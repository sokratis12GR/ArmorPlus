package sokratis12gr.armorplus.armors.tconstruct;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import sokratis12gr.armorplus.ArmorPlus;

import java.util.List;

public class ArditeArmor {

    public static Item helmet;
    public static Item chestplate;
    public static Item legs;
    public static Item boots;

    static {
        ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("ARDITEARMOR", ArmorPlus.MODID + ":" + "ArditeArmor", 55, new int[]
                {4, 8, 10, 4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.DARK_RED + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            @Override
            public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
                infoList.add("\2479Ability: " + "\247rFire Resistance");
                infoList.add("\2473Use: " + "\247rEquip The Full Set");
            }
        }).setUnlocalizedName("ArditeHelmet");
        helmet.setMaxStackSize(1);
        chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST) {
            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.DARK_RED + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            @Override
            public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
                infoList.add("\2479Ability: " + "\247rFire Resistance");
                infoList.add("\2473Use: " + "\247rEquip The Full Set");
            }
        }).setUnlocalizedName("ArditeChestplate");
        chestplate.setMaxStackSize(1);
        legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS) {
            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.DARK_RED + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            @Override
            public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
                infoList.add("\2479Ability: " + "\247rFire Resistance");
                infoList.add("\2473Use: " + "\247rEquip The Full Set");
            }
        }).setUnlocalizedName("ArditeLeggings");
        legs.setMaxStackSize(1);
        boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET) {
            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.DARK_RED + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            @Override
            public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
                infoList.add("\2479Ability: " + "\247rFire Resistance");
                infoList.add("\2473Use: " + "\247rEquip The Full Set");
            }
        }).setUnlocalizedName("ArditeBoots");
        boots.setMaxStackSize(1);
        GameRegistry.registerItem(helmet, "ardite_helmet");
        GameRegistry.registerItem(chestplate, "ardite_chestplate");
        GameRegistry.registerItem(legs, "ardite_leggings");
        GameRegistry.registerItem(boots, "ardite_boots");
    }

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
        helmet.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        chestplate.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        legs.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        boots.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
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
}
