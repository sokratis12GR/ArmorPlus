package sokratis12GR.ArmorPlus.armors.origin;

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

public class CoalArmor {

    public CoalArmor() {
    }

    public static Item helmet;
    public static Item chestplate;
    public static Item legs;
    public static Item boots;
    public Object instance;

    public void load(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:CoalHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:CoalChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:CoalLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:CoalBoots", "inventory"));

        }
        if (ConfigHandler.enableCoalArmorRecipes) {
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"XXX", "CCC", "CXC", Character.valueOf('C'), new ItemStack(Items.coal, 1),});
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"CCC", "CXC", "XXX", Character.valueOf('C'), new ItemStack(Items.coal, 1),});
            GameRegistry.addRecipe(new ItemStack(chestplate, 1), new Object[]
                    {"CXC", "CCC", "CCC", Character.valueOf('C'), new ItemStack(Items.coal, 1),});
            GameRegistry.addRecipe(new ItemStack(legs, 1), new Object[]
                    {"CCC", "CXC", "CXC", Character.valueOf('C'), new ItemStack(Items.coal, 1),});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"XXX", "CXC", "CXC", Character.valueOf('C'), new ItemStack(Items.coal, 1),});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"CXC", "CXC", "XXX", Character.valueOf('C'), new ItemStack(Items.coal, 1),});
            helmet.setCreativeTab(ArmorPlus.tabArmorPlus);
            chestplate.setCreativeTab(ArmorPlus.tabArmorPlus);
            legs.setCreativeTab(ArmorPlus.tabArmorPlus);
            boots.setCreativeTab(ArmorPlus.tabArmorPlus);
        }
    }

    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:CoalHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:CoalChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:CoalLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:CoalBoots", "inventory"));
        }
    }

    public void registerRenderers() {
    }

    static {
        ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("COALARMOR", ArmorPlus.MODID + ":" + "CoalArmor", 7, new int[]
                {1, 2, 3, 1}, 8, SoundEvents.item_armor_equip_leather);

        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&7" + "Gives you Night Vision"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableCoalHNightVision && entity instanceof EntityLivingBase && !ConfigHandler.enableFullCoalArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.nightVision, 240, 0, true, true));
                }
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == Items.coal;
            }
        }).setUnlocalizedName("CoalHelmet");
        helmet.setMaxStackSize(1);

        chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&7" + "Gives you Night Vision"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableCoalCNightVision && entity instanceof EntityLivingBase && !ConfigHandler.enableFullCoalArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.nightVision, 240, 0, true, true));
                }
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == Items.coal;
            }
        }).setUnlocalizedName("CoalChestplate");
        chestplate.setMaxStackSize(1);

        legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&7" + "Gives you Night Vision"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableCoalLNightVision && entity instanceof EntityLivingBase && !ConfigHandler.enableFullCoalArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.nightVision, 240, 0, true, true));
                }
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == Items.coal;
            }
        }).setUnlocalizedName("CoalLeggings");
        legs.setMaxStackSize(1);

        boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&7" + "Gives you Night Vision"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableCoalBNightVision && entity instanceof EntityLivingBase && !ConfigHandler.enableFullCoalArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.nightVision, 240, 0, true, true));
                }
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == Items.coal;
            }
        }).setUnlocalizedName("CoalBoots");
        boots.setMaxStackSize(1);

        GameRegistry.registerItem(helmet, "CoalHelmet");
        GameRegistry.registerItem(chestplate, "CoalChestplate");
        GameRegistry.registerItem(legs, "CoalLeggings");
        GameRegistry.registerItem(boots, "CoalBoots");
    }
}
