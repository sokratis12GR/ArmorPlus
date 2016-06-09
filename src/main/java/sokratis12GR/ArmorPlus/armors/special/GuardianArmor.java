package sokratis12GR.ArmorPlus.armors.special;

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

public class GuardianArmor {

    public static Item helmet;
    public static Item chestplate;
    public static Item legs;
    public static Item boots;
    public Object instance;

    public void load(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {

            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:GuardianHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:GuardianChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:GuardianLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:GuardianBoots", "inventory"));
        }
        if (ConfigHandler.easyMode && ConfigHandler.enableGuardianArmorRecipes) {
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"XXX", "GGG", "GXG", Character.valueOf('G'), new ItemStack(ModItems.GUARDIAN_SCALE, 1)});
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"GGG", "GXG", "XXX", Character.valueOf('G'), new ItemStack(ModItems.GUARDIAN_SCALE, 1)});
            GameRegistry.addRecipe(new ItemStack(chestplate, 1), new Object[]
                    {"GXG", "GGG", "GGG", Character.valueOf('G'), new ItemStack(ModItems.GUARDIAN_SCALE, 1)});
            GameRegistry.addRecipe(new ItemStack(legs, 1), new Object[]
                    {"GGG", "GXG", "GXG", Character.valueOf('G'), new ItemStack(ModItems.GUARDIAN_SCALE, 1)});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"XXX", "GXG", "GXG", Character.valueOf('G'), new ItemStack(ModItems.GUARDIAN_SCALE, 1)});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"GXG", "GXG", "XXX", Character.valueOf('G'), new ItemStack(ModItems.GUARDIAN_SCALE, 1)});
        }
        if (ConfigHandler.expertMode && ConfigHandler.enableGuardianArmorRecipes) {
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"XXX", "GLG", "GXG", Character.valueOf('G'), new ItemStack(ModItems.GUARDIAN_SCALE, 1), Character.valueOf('L'), new ItemStack(Item.getItemFromBlock(Blocks.SEA_LANTERN), 1)});
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"GLG", "GXG", "XXX", Character.valueOf('G'), new ItemStack(ModItems.GUARDIAN_SCALE, 1), Character.valueOf('L'), new ItemStack(Item.getItemFromBlock(Blocks.SEA_LANTERN), 1)});
            GameRegistry.addRecipe(new ItemStack(chestplate, 1), new Object[]
                    {"SXS", "GLG", "CGC", Character.valueOf('G'), new ItemStack(ModItems.GUARDIAN_SCALE, 1), Character.valueOf('C'), new ItemStack(Items.PRISMARINE_CRYSTALS, 1), Character.valueOf('L'), new ItemStack(Item.getItemFromBlock(Blocks.SEA_LANTERN), 1), Character.valueOf('S'), new ItemStack(Item.getItemFromBlock(Blocks.SPONGE), 1)});
            GameRegistry.addRecipe(new ItemStack(legs, 1), new Object[]
                    {"CGC", "GXG", "PXP", Character.valueOf('G'), new ItemStack(ModItems.GUARDIAN_SCALE, 1), Character.valueOf('C'), new ItemStack(Items.PRISMARINE_CRYSTALS, 1), Character.valueOf('P'), new ItemStack(Items.PRISMARINE_SHARD, 1)});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"XXX", "SXS", "GXG", Character.valueOf('G'), new ItemStack(ModItems.GUARDIAN_SCALE, 1), Character.valueOf('S'), new ItemStack(Item.getItemFromBlock(Blocks.SPONGE), 1)});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"SXS", "GXG", "XXX", Character.valueOf('G'), new ItemStack(ModItems.GUARDIAN_SCALE, 1), Character.valueOf('S'), new ItemStack(Item.getItemFromBlock(Blocks.SPONGE), 1)});
        }
        helmet.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        chestplate.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        legs.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        boots.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
    }

    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:GuardianHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:GuardianChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:GuardianLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:GuardianBoots", "inventory"));
        }
    }

    public void registerRenderers() {
    }

    static {
        ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("GUARDIANARMOR", ArmorPlus.MODID + ":" + "GuardianArmor", 50, new int[]
                {4, 8, 11, 6}, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&b" + "It is Thorny and gives you Water Breathing"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableGuardianHEffects && entity instanceof EntityLivingBase && !ConfigHandler.enableFullGuardianArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.AQUA + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == ModItems.GUARDIAN_SCALE;
            }
        }).setUnlocalizedName("GuardianHelmet");
        helmet.setMaxStackSize(1);
        chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&b" + "It is Thorny and gives you Water Breathing"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableGuardianCEffects && entity instanceof EntityLivingBase && !ConfigHandler.enableFullGuardianArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.AQUA + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == ModItems.GUARDIAN_SCALE;
            }
        }).setUnlocalizedName("GuardianChestplate");
        chestplate.setMaxStackSize(1);
        legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&b" + "It is Thorny and gives you Water Breathing"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableGuardianLEffects && entity instanceof EntityLivingBase && !ConfigHandler.enableFullGuardianArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.AQUA + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == ModItems.GUARDIAN_SCALE;
            }
        }).setUnlocalizedName("GuardianLeggings");
        legs.setMaxStackSize(1);
        boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&b" + "It is Thorny and gives you Water Breathing"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableGuardianBEffects && entity instanceof EntityLivingBase && !ConfigHandler.enableFullGuardianArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.AQUA + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == ModItems.GUARDIAN_SCALE;
            }
        }).setUnlocalizedName("GuardianBoots");
        boots.setMaxStackSize(1);
        GameRegistry.registerItem(helmet, "GuardianHelmet");
        GameRegistry.registerItem(chestplate, "GuardianChestplate");
        GameRegistry.registerItem(legs, "GuardianLeggings");
        GameRegistry.registerItem(boots, "GuardianBoots");
    }
}