package sokratis12GR.ArmorPlus.armors.origin;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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

public class LapisArmor {

    public LapisArmor() {
    }

    public static Item helmet;
    public static Item chestplate;
    public static Item legs;
    public static Item boots;
    public Object instance;

    public void load(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {

            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:LapisHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:LapisChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:LapisLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:LapisBoots", "inventory"));
        }
        if (ConfigHandler.enableLapisArmorRecipes) {
            if (ConfigHandler.expensiveLapisRecipe) {
                GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                        {"XXX", "LLL", "LXL", Character.valueOf('L'), new ItemStack(Blocks.LAPIS_BLOCK, 1),});
                GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                        {"LLL", "LXL", "XXX", Character.valueOf('L'), new ItemStack(Blocks.LAPIS_BLOCK, 1),});
                GameRegistry.addRecipe(new ItemStack(chestplate, 1), new Object[]
                        {"LXL", "LLL", "LLL", Character.valueOf('L'), new ItemStack(Blocks.LAPIS_BLOCK, 1),});
                GameRegistry.addRecipe(new ItemStack(legs, 1), new Object[]
                        {"LLL", "LXL", "LXL", Character.valueOf('L'), new ItemStack(Blocks.LAPIS_BLOCK, 1),});
                GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                        {"XXX", "LXL", "LXL", Character.valueOf('L'), new ItemStack(Blocks.LAPIS_BLOCK, 1),});
                GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                        {"LXL", "LXL", "XXX", Character.valueOf('L'), new ItemStack(Blocks.LAPIS_BLOCK, 1),});
            } else if (ConfigHandler.cheapLapisRecipe) {
                GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                        {"XXX", "LLL", "LXL", Character.valueOf('L'), new ItemStack(Items.DYE, 1, 4),});
                GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                        {"LLL", "LXL", "XXX", Character.valueOf('L'), new ItemStack(Items.DYE, 1, 4),});
                GameRegistry.addRecipe(new ItemStack(chestplate, 1), new Object[]
                        {"LXL", "LLL", "LLL", Character.valueOf('L'), new ItemStack(Items.DYE, 1, 4),});
                GameRegistry.addRecipe(new ItemStack(legs, 1), new Object[]
                        {"LLL", "LXL", "LXL", Character.valueOf('L'), new ItemStack(Items.DYE, 1, 4),});
                GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                        {"XXX", "LXL", "LXL", Character.valueOf('L'), new ItemStack(Items.DYE, 1, 4),});
                GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                        {"LXL", "LXL", "XXX", Character.valueOf('L'), new ItemStack(Items.DYE, 1, 4),});
            }
            helmet.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
            chestplate.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
            legs.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
            boots.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        }
    }

    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:LapisHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:LapisChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:LapisLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:LapisBoots", "inventory"));
        }
    }

    public void registerRenderers() {
    }

    static {
        ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("LAPISARMOR", ArmorPlus.MODID + ":" + "LapisArmor", 11, new int[]
                {1, 3, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);

        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&1" + "Gives you Water Breathing"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableLapisHBreathing && entity instanceof EntityLivingBase && !ConfigHandler.enableFullLapisArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
                }
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == getItemFromBlock(Blocks.LAPIS_BLOCK);
            }
        }).setUnlocalizedName("LapisHelmet");
        helmet.setMaxStackSize(1);
        chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&1" + "Gives you Water Breathing"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableLapisCBreathing && entity instanceof EntityLivingBase && !ConfigHandler.enableFullLapisArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
                }
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == getItemFromBlock(Blocks.LAPIS_BLOCK);
            }
        }).setUnlocalizedName("LapisChestplate");
        chestplate.setMaxStackSize(1);
        legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&1" + "Gives you Water Breathing"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableLapisLBreathing && entity instanceof EntityLivingBase && !ConfigHandler.enableFullLapisArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
                }
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == getItemFromBlock(Blocks.LAPIS_BLOCK);
            }
        }).setUnlocalizedName("LapisLeggings");
        legs.setMaxStackSize(1);
        boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&1" + "Gives you Water Breathing"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableLapisBBreathing && entity instanceof EntityLivingBase && !ConfigHandler.enableFullLapisArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
                }
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == getItemFromBlock(Blocks.LAPIS_BLOCK);
            }
        }).setUnlocalizedName("LapisBoots");
        boots.setMaxStackSize(1);

        GameRegistry.registerItem(helmet, "LapisHelmet");
        GameRegistry.registerItem(chestplate, "LapisChestplate");
        GameRegistry.registerItem(legs, "LapisLeggings");
        GameRegistry.registerItem(boots, "LapisBoots");

    }
}
