package sokratis12GR.ArmorPlus.armors.special;

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
            if (ConfigHandler.enableTheUltimateArmorRecipes) {
                GameRegistry.addShapelessRecipe(new ItemStack(helmet, 1), new Object[]
                        {new ItemStack(SuperStarArmor.helmet, 1), new ItemStack(EnderDragonArmor.helmet, 1),
                                new ItemStack(GuardianArmor.helmet, 2),});
                GameRegistry.addShapelessRecipe(new ItemStack(chestplate, 1), new Object[]
                        {new ItemStack(SuperStarArmor.chestplate, 1), new ItemStack(EnderDragonArmor.chestplate, 1),
                                new ItemStack(GuardianArmor.chestplate, 1),});
                GameRegistry.addShapelessRecipe(new ItemStack(legs, 1), new Object[]
                        {new ItemStack(SuperStarArmor.legs, 1), new ItemStack(EnderDragonArmor.legs, 1),
                                new ItemStack(GuardianArmor.legs, 1),});
                GameRegistry.addShapelessRecipe(new ItemStack(boots, 1), new Object[]
                        {new ItemStack(SuperStarArmor.boots, 1), new ItemStack(EnderDragonArmor.boots, 1),
                                new ItemStack(GuardianArmor.boots, 1),});
                helmet.setCreativeTab(ArmorPlus.tabArmorPlus);
                chestplate.setCreativeTab(ArmorPlus.tabArmorPlus);
                legs.setCreativeTab(ArmorPlus.tabArmorPlus);
                boots.setCreativeTab(ArmorPlus.tabArmorPlus);
            }
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
                {14, 26, 35, 18}, 88, SoundEvents.item_armor_equip_diamond);

        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&a" + "The Most OverPowered Armor"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.waterBreathing, 120, 0));
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.regeneration, 120, 1));
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.nightVision, 240, 0));
            }
        }).setUnlocalizedName("TheUltimateHelmet");
        helmet.setMaxStackSize(1);
        chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&a" + "The Most OverPowered Armor"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.waterBreathing, 120, 0));
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.regeneration, 120, 1));
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.nightVision, 240, 0));
            }
        }).setUnlocalizedName("TheUltimateChestplate");
        chestplate.setMaxStackSize(1);
        legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&a" + "The Most OverPowered Armor"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.waterBreathing, 120, 0));
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.regeneration, 120, 1));
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.nightVision, 240, 0));
            }
        }).setUnlocalizedName("TheUltimateLeggings");
        legs.setMaxStackSize(1);
        boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&a" + "The Most OverPowered Armor"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.waterBreathing, 120, 0));
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.regeneration, 120, 1));
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.nightVision, 240, 0));
            }
        }).setUnlocalizedName("TheUltimateBoots");
        boots.setMaxStackSize(1);

        GameRegistry.registerItem(helmet, "TheUltimateHelmet");
        GameRegistry.registerItem(chestplate, "TheUltimateChestplate");
        GameRegistry.registerItem(legs, "TheUltimateLeggings");
        GameRegistry.registerItem(boots, "TheUltimateBoots");

    }

}

