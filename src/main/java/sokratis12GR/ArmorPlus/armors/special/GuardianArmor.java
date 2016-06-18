package sokratis12GR.ArmorPlus.armors.special;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
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
            GameRegistry.addRecipe(new ItemStack(helmet, 1), "XXX", "GGG", "GXG", 'G', new ItemStack(ModItems.GUARDIAN_SCALE, 1));
            GameRegistry.addRecipe(new ItemStack(helmet, 1), "GGG", "GXG", "XXX", 'G', new ItemStack(ModItems.GUARDIAN_SCALE, 1));
            GameRegistry.addRecipe(new ItemStack(chestplate, 1), "GXG", "GGG", "GGG", 'G', new ItemStack(ModItems.GUARDIAN_SCALE, 1));
            GameRegistry.addRecipe(new ItemStack(legs, 1), "GGG", "GXG", "GXG", 'G', new ItemStack(ModItems.GUARDIAN_SCALE, 1));
            GameRegistry.addRecipe(new ItemStack(boots, 1), "XXX", "GXG", "GXG", 'G', new ItemStack(ModItems.GUARDIAN_SCALE, 1));
            GameRegistry.addRecipe(new ItemStack(boots, 1), "GXG", "GXG", "XXX", 'G', new ItemStack(ModItems.GUARDIAN_SCALE, 1));
        }
        if (ConfigHandler.expertMode && ConfigHandler.enableGuardianArmorRecipes) {
            GameRegistry.addRecipe(new ItemStack(helmet, 1), "XXX", "GLG", "GXG", 'G', new ItemStack(ModItems.GUARDIAN_SCALE, 1), 'L', new ItemStack(Blocks.SEA_LANTERN, 1));
            GameRegistry.addRecipe(new ItemStack(helmet, 1), "GLG", "GXG", "XXX", 'G', new ItemStack(ModItems.GUARDIAN_SCALE, 1), 'L', new ItemStack(Blocks.SEA_LANTERN, 1));
            GameRegistry.addRecipe(new ItemStack(chestplate, 1), "SXS", "GLG", "CGC", 'G', new ItemStack(ModItems.GUARDIAN_SCALE, 1), 'C', new ItemStack(Items.PRISMARINE_CRYSTALS, 1), 'L', new ItemStack(Blocks.SEA_LANTERN, 1), 'S', new ItemStack(Blocks.SPONGE, 1));
            GameRegistry.addRecipe(new ItemStack(legs, 1), "CGC", "GXG", "PXP", 'G', new ItemStack(ModItems.GUARDIAN_SCALE, 1), 'C', new ItemStack(Items.PRISMARINE_CRYSTALS, 1), 'P', new ItemStack(Items.PRISMARINE_SHARD, 1));
            GameRegistry.addRecipe(new ItemStack(boots, 1), "XXX", "SXS", "GXG", 'G', new ItemStack(ModItems.GUARDIAN_SCALE, 1), 'S', new ItemStack(Blocks.SPONGE, 1));
            GameRegistry.addRecipe(new ItemStack(boots, 1), "SXS", "GXG", "XXX", 'G', new ItemStack(ModItems.GUARDIAN_SCALE, 1), 'S', new ItemStack(Blocks.SPONGE, 1));
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
                tooltip.add(TextHelper.getFormattedText("&b" + "It is Thorny and gives you Water Breathing when using full set"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
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
                tooltip.add(TextHelper.getFormattedText("&b" + "It is Thorny and gives you Water Breathing when using full set"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
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
                tooltip.add(TextHelper.getFormattedText("&b" + "It is Thorny and gives you Water Breathing when using full set"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
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
                tooltip.add(TextHelper.getFormattedText("&b" + "It is Thorny and gives you Water Breathing when using full set"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
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