package sokratis12GR.ArmorPlus.armors.special;

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
import sokratis12GR.ArmorPlus.util.TextHelper;

import java.util.List;

public class TheUltimateArmor {

    public TheUltimateArmor() {
    }

    public static Item helmet;
    public static Item chestplate;
    public static Item legs;
    public static Item boots;
    public Object instance;

    public void load(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation(ArmorPlus.MODID + ":" + "TheUltimateHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation(ArmorPlus.MODID + ":" + "TheUltimateChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation(ArmorPlus.MODID + ":" + "TheUltimateLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation(ArmorPlus.MODID + ":" + "TheUltimateBoots", "inventory"));
            helmet.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
            chestplate.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
            legs.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
            boots.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        }
    }

    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation(ArmorPlus.MODID + ":" + "TheUltimateHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation(ArmorPlus.MODID + ":" + "TheUltimateChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation(ArmorPlus.MODID + ":" + "TheUltimateLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation(ArmorPlus.MODID + ":" + "TheUltimateBoots", "inventory"));
        }
    }

    public void registerRenderers() {
    }

    static {
        ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("THEULTIMATEARMOR", ArmorPlus.MODID + ":" + "TheUltimateArmor", 160, new int[]
                {10, 20, 30, 15}, 88, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 6.0F);

        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&a" + "The Most OverPowered Armor"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
                ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
                ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
                if (ConfigHandler.enableFlightAbility) {

                    if (head != null && head.getItem() == TheUltimateArmor.helmet && chest != null && chest.getItem() == TheUltimateArmor.chestplate && legs != null && legs.getItem() == TheUltimateArmor.legs && feet != null && feet.getItem() == TheUltimateArmor.boots || entity.capabilities.isCreativeMode || entity.isSpectator()) {
                        entity.capabilities.allowFlying = true;
                    } else {
                        entity.capabilities.isFlying = false;
                        entity.capabilities.allowFlying = false;
                    }
                }
            }
        }).setUnlocalizedName("TheUltimateHelmet");
        helmet.setMaxStackSize(1);
        chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&a" + "The Most OverPowered Armor"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
                ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
                ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
                if (ConfigHandler.enableFlightAbility) {

                    if (head != null && head.getItem() == TheUltimateArmor.helmet && chest != null && chest.getItem() == TheUltimateArmor.chestplate && legs != null && legs.getItem() == TheUltimateArmor.legs && feet != null && feet.getItem() == TheUltimateArmor.boots || entity.capabilities.isCreativeMode || entity.isSpectator()) {
                        entity.capabilities.allowFlying = true;
                    } else {
                        entity.capabilities.isFlying = false;
                        entity.capabilities.allowFlying = false;
                    }
                }
            }
        }).setUnlocalizedName("TheUltimateChestplate");
        chestplate.setMaxStackSize(1);
        legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&a" + "The Most OverPowered Armor"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
                ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
                ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
                if (ConfigHandler.enableFlightAbility) {

                    if (head != null && head.getItem() == TheUltimateArmor.helmet && chest != null && chest.getItem() == TheUltimateArmor.chestplate && legs != null && legs.getItem() == TheUltimateArmor.legs && feet != null && feet.getItem() == TheUltimateArmor.boots || entity.capabilities.isCreativeMode || entity.isSpectator()) {
                        entity.capabilities.allowFlying = true;
                    } else {
                        entity.capabilities.isFlying = false;
                        entity.capabilities.allowFlying = false;
                    }
                }
            }
        }).setUnlocalizedName("TheUltimateLeggings");
        legs.setMaxStackSize(1);
        boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&a" + "The Most OverPowered Armor"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
                ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
                ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
                if (ConfigHandler.enableFlightAbility) {

                    if (head != null && head.getItem() == TheUltimateArmor.helmet && chest != null && chest.getItem() == TheUltimateArmor.chestplate && legs != null && legs.getItem() == TheUltimateArmor.legs && feet != null && feet.getItem() == TheUltimateArmor.boots || entity.capabilities.isCreativeMode || entity.isSpectator()) {
                        entity.capabilities.allowFlying = true;
                    } else {
                        entity.capabilities.isFlying = false;
                        entity.capabilities.allowFlying = false;
                    }
                }
            }
        }).setUnlocalizedName("TheUltimateBoots");
        boots.setMaxStackSize(1);

        GameRegistry.registerItem(helmet, "TheUltimateHelmet");
        GameRegistry.registerItem(chestplate, "TheUltimateChestplate");
        GameRegistry.registerItem(legs, "TheUltimateLeggings");
        GameRegistry.registerItem(boots, "TheUltimateBoots");

    }

}

