package sokratis12gr.armorplus.armors.special;

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
import sokratis12gr.armorplus.ArmorPlus;
import sokratis12gr.armorplus.registry.ModItems;
import sokratis12gr.armorplus.resources.ConfigHandler;

import java.util.List;

public class EnderDragonArmor {

    public static Item helmet;
    public static Item chestplate;
    public static Item legs;
    public static Item boots;

    static {

        ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("ENDERDRAGONARMOR", ArmorPlus.MODID + ":" + "EnderDragonArmor", 60, new int[]
                {5, 9, 12, 6}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
                infoList.add("\2479Ability: " + "\247rFlight");
                infoList.add("\2473Use: " + "\247rEquip The Full Set");
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
                ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
                ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
                if (ConfigHandler.enableFlightAbility) {
                    if (head != null && head.getItem() == EnderDragonArmor.helmet && chest != null && chest.getItem() == EnderDragonArmor.chestplate && legs != null && legs.getItem() == EnderDragonArmor.legs && feet != null && feet.getItem() == EnderDragonArmor.boots || entity.capabilities.isCreativeMode || entity.isSpectator()) {
                        entity.capabilities.allowFlying = true;
                    } else {
                        entity.capabilities.isFlying = false;
                        entity.capabilities.allowFlying = false;
                    }
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.DARK_PURPLE + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == ModItems.ENDER_DRAGON_SCALE;
            }
        }).setUnlocalizedName("EnderDragonHelmet");
        helmet.setMaxStackSize(1);
        chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
                infoList.add("\2479Ability: " + "\247rFlight");
                infoList.add("\2473Use: " + "\247rEquip The Full Set");
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
                ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
                ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
                if (ConfigHandler.enableFlightAbility) {
                    if (head != null && head.getItem() == EnderDragonArmor.helmet && chest != null && chest.getItem() == EnderDragonArmor.chestplate && legs != null && legs.getItem() == EnderDragonArmor.legs && feet != null && feet.getItem() == EnderDragonArmor.boots || entity.capabilities.isCreativeMode || entity.isSpectator()) {
                        entity.capabilities.allowFlying = true;
                    } else {
                        entity.capabilities.isFlying = false;
                        entity.capabilities.allowFlying = false;
                    }
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.DARK_PURPLE + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == ModItems.ENDER_DRAGON_SCALE;
            }
        }).setUnlocalizedName("EnderDragonChestplate");
        chestplate.setMaxStackSize(1);
        legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
                infoList.add("\2479Ability: " + "\247rFlight");
                infoList.add("\2473Use: " + "\247rEquip The Full Set");
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
                ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
                ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
                if (ConfigHandler.enableFlightAbility) {
                    if (head != null && head.getItem() == EnderDragonArmor.helmet && chest != null && chest.getItem() == EnderDragonArmor.chestplate && legs != null && legs.getItem() == EnderDragonArmor.legs && feet != null && feet.getItem() == EnderDragonArmor.boots || entity.capabilities.isCreativeMode || entity.isSpectator()) {
                        entity.capabilities.allowFlying = true;
                    } else {
                        entity.capabilities.isFlying = false;
                        entity.capabilities.allowFlying = false;
                    }
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.DARK_PURPLE + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == ModItems.ENDER_DRAGON_SCALE;
            }
        }).setUnlocalizedName("EnderDragonLeggings");
        legs.setMaxStackSize(1);
        boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
                infoList.add("\2479Ability: " + "\247rFlight");
                infoList.add("\2473Use: " + "\247rEquip The Full Set");
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
                ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
                ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
                if (ConfigHandler.enableFlightAbility) {
                    if (head != null && head.getItem() == EnderDragonArmor.helmet && chest != null && chest.getItem() == EnderDragonArmor.chestplate && legs != null && legs.getItem() == EnderDragonArmor.legs && feet != null && feet.getItem() == EnderDragonArmor.boots || entity.capabilities.isCreativeMode || entity.isSpectator()) {
                        entity.capabilities.allowFlying = true;
                    } else {
                        entity.capabilities.isFlying = false;
                        entity.capabilities.allowFlying = false;
                    }
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.DARK_PURPLE + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == ModItems.ENDER_DRAGON_SCALE;
            }
        }).setUnlocalizedName("EnderDragonBoots");
        boots.setMaxStackSize(1);
        GameRegistry.registerItem(helmet, "ender_dragon_helmet");
        GameRegistry.registerItem(chestplate, "ender_dragon_chestplate");
        GameRegistry.registerItem(legs, "ender_dragon_leggings");
        GameRegistry.registerItem(boots, "ender_dragon_boots");

    }

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
        helmet.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        chestplate.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        legs.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        boots.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
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
}
