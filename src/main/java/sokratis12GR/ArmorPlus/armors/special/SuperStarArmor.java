package sokratis12GR.ArmorPlus.armors.special;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
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
import sokratis12GR.ArmorPlus.registry.ModItems;
import sokratis12GR.ArmorPlus.resources.ConfigHandler;
import sokratis12GR.ArmorPlus.util.TextHelper;

import java.util.List;

public class SuperStarArmor {

    public SuperStarArmor() {
    }

    public static Item helmet;
    public static Item chestplate;
    public static Item legs;
    public static Item boots;
    public Object instance;

    public void load(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:SuperStarHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:SuperStarChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:SuperStarLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:SuperStarBoots", "inventory"));
        }
        if (ConfigHandler.easyMode && ConfigHandler.enableSuperStarArmorRecipes) {
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"XXX", "WWW", "WNW", Character.valueOf('W'), new ItemStack(ModItems.WITHER_BONE, 1), Character.valueOf('N'), new ItemStack(Items.NETHER_STAR, 1)});
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"WWW", "WNW", "XXX", Character.valueOf('W'), new ItemStack(ModItems.WITHER_BONE, 1), Character.valueOf('N'), new ItemStack(Items.NETHER_STAR, 1)});
            GameRegistry.addRecipe(new ItemStack(chestplate, 1), new Object[]
                    {"WNW", "WWW", "WWW", Character.valueOf('W'), new ItemStack(ModItems.WITHER_BONE, 1), Character.valueOf('N'), new ItemStack(Items.NETHER_STAR, 1)});
            GameRegistry.addRecipe(new ItemStack(legs, 1), new Object[]
                    {"WWW", "WNW", "WXW", Character.valueOf('W'), new ItemStack(ModItems.WITHER_BONE, 1), Character.valueOf('N'), new ItemStack(Items.NETHER_STAR, 1)});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"XXX", "WNW", "WXW", Character.valueOf('W'), new ItemStack(ModItems.WITHER_BONE, 1), Character.valueOf('N'), new ItemStack(Items.NETHER_STAR, 1)});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"WNW", "WXW", "XXX", Character.valueOf('W'), new ItemStack(ModItems.WITHER_BONE, 1), Character.valueOf('N'), new ItemStack(Items.NETHER_STAR, 1)});
        }
        if (ConfigHandler.expertMode && ConfigHandler.enableSuperStarArmorRecipes) {
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"WWW", "WSW", "NXN", Character.valueOf('W'), new ItemStack(ModItems.WITHER_BONE, 1), Character.valueOf('S'), new ItemStack(Items.SKULL, 1, 1), Character.valueOf('N'), new ItemStack(Items.NETHER_STAR, 1),});
            GameRegistry.addRecipe(new ItemStack(chestplate, 1), new Object[]
                    {"SXS", "NNN", "WWW", Character.valueOf('W'), new ItemStack(ModItems.WITHER_BONE, 1), Character.valueOf('S'), new ItemStack(Items.SKULL, 1, 1), Character.valueOf('N'), new ItemStack(Items.NETHER_STAR, 1)});
            GameRegistry.addRecipe(new ItemStack(legs, 1), new Object[]
                    {"SWS", "WXW", "NXN", Character.valueOf('W'), new ItemStack(ModItems.WITHER_BONE, 1), Character.valueOf('S'), new ItemStack(Items.SKULL, 1, 1), Character.valueOf('N'), new ItemStack(Items.NETHER_STAR, 1)});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"XXX", "WXW", "NXN", Character.valueOf('W'), new ItemStack(ModItems.WITHER_BONE, 1), Character.valueOf('N'), new ItemStack(Items.NETHER_STAR, 1)});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"WXW", "NXN", "XXX", Character.valueOf('W'), new ItemStack(ModItems.WITHER_BONE, 1), Character.valueOf('N'), new ItemStack(Items.NETHER_STAR, 1)});
        }
        helmet.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        chestplate.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        legs.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        boots.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
    }

    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:SuperStarHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:SuperStarChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:SuperStarLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:SuperStarBoots", "inventory"));
        }
    }

    public void registerRenderers() {
    }

    static {
        ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("SUPERSTARARMOR", ArmorPlus.MODID + ":" + "SuperStarArmor", 50, new int[]
                {5, 9, 12, 6}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                int superstarArmorEffectlevel = ConfigHandler.superstarArmorEffectlevel + 1;
                tooltip.add(TextHelper.getFormattedText("&f" + "Gives you Regeneration " + superstarArmorEffectlevel));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableSuperStarHRegen && entity instanceof EntityLivingBase && !ConfigHandler.enableFullSuperStarArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 120, ConfigHandler.superstarArmorEffectlevel, true, true));
                    ((EntityLivingBase) entity).removePotionEffect(MobEffects.WITHER);
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.WHITE + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == ModItems.WITHER_BONE;
            }
        }).setUnlocalizedName("SuperStarHelmet");
        helmet.setMaxStackSize(1);
        chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                int superstarArmorEffectlevel = ConfigHandler.superstarArmorEffectlevel + 1;
                tooltip.add(TextHelper.getFormattedText("&f" + "Gives you Regeneration " + superstarArmorEffectlevel));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableSuperStarCRegen && entity instanceof EntityLivingBase && !ConfigHandler.enableFullSuperStarArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 120, ConfigHandler.superstarArmorEffectlevel, true, true));
                    ((EntityLivingBase) entity).removePotionEffect(MobEffects.WITHER);
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.WHITE + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == ModItems.WITHER_BONE;
            }
        }).setUnlocalizedName("SuperStarChestplate");
        chestplate.setMaxStackSize(1);
        legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                int superstarArmorEffectlevel = ConfigHandler.superstarArmorEffectlevel + 1;
                tooltip.add(TextHelper.getFormattedText("&f" + "Gives you Regeneration " + superstarArmorEffectlevel));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableSuperStarCRegen && entity instanceof EntityLivingBase && !ConfigHandler.enableFullSuperStarArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 120, ConfigHandler.superstarArmorEffectlevel, true, true));
                    ((EntityLivingBase) entity).removePotionEffect(MobEffects.WITHER);
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.WHITE + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == ModItems.WITHER_BONE;
            }
        }).setUnlocalizedName("SuperStarLeggings");
        legs.setMaxStackSize(1);
        boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                int superstarArmorEffectlevel = ConfigHandler.superstarArmorEffectlevel + 1;
                tooltip.add(TextHelper.getFormattedText("&f" + "Gives you Regeneration " + superstarArmorEffectlevel));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableSuperStarCRegen && entity instanceof EntityLivingBase && !ConfigHandler.enableFullSuperStarArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 120, ConfigHandler.superstarArmorEffectlevel, true, true));
                    ((EntityLivingBase) entity).removePotionEffect(MobEffects.WITHER);
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.WHITE + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == ModItems.WITHER_BONE;
            }
        }).setUnlocalizedName("SuperStarBoots");
        boots.setMaxStackSize(1);

        GameRegistry.registerItem(helmet, "SuperStarHelmet");
        GameRegistry.registerItem(chestplate, "SuperStarChestplate");
        GameRegistry.registerItem(legs, "SuperStarLeggings");
        GameRegistry.registerItem(boots, "SuperStarBoots");

    }

}
