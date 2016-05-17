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
        if (ConfigHandler.enableGuardianArmorRecipes) {
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"XXX", "345", "6X8", Character.valueOf('3'), new ItemStack(Items.prismarine_shard, 1), Character.valueOf('4'),
                            new ItemStack(Items.prismarine_shard, 1), Character.valueOf('5'), new ItemStack(Items.prismarine_shard, 1),
                            Character.valueOf('6'), new ItemStack(Items.prismarine_shard, 1), Character.valueOf('8'),
                            new ItemStack(Items.prismarine_shard, 1),});
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"012", "3X5", "XXX", Character.valueOf('0'), new ItemStack(Items.prismarine_shard, 1), Character.valueOf('1'),
                            new ItemStack(Items.prismarine_shard, 1), Character.valueOf('2'), new ItemStack(Items.prismarine_shard, 1),
                            Character.valueOf('3'), new ItemStack(Items.prismarine_shard, 1), Character.valueOf('5'),
                            new ItemStack(Items.prismarine_shard, 1),});
            GameRegistry.addRecipe(new ItemStack(chestplate, 1), new Object[]
                    {"0X2", "345", "678", Character.valueOf('0'), new ItemStack(Items.prismarine_shard, 1), Character.valueOf('2'),
                            new ItemStack(Items.prismarine_shard, 1), Character.valueOf('3'), new ItemStack(Items.prismarine_shard, 1),
                            Character.valueOf('4'), new ItemStack(Items.prismarine_shard, 1), Character.valueOf('5'),
                            new ItemStack(Items.prismarine_shard, 1), Character.valueOf('6'), new ItemStack(Items.prismarine_shard, 1),
                            Character.valueOf('7'), new ItemStack(Items.prismarine_shard, 1), Character.valueOf('8'),
                            new ItemStack(Items.prismarine_shard, 1),});
            GameRegistry.addRecipe(new ItemStack(legs, 1), new Object[]
                    {"012", "3X5", "6X8", Character.valueOf('0'), new ItemStack(Items.prismarine_shard, 1), Character.valueOf('1'),
                            new ItemStack(Items.prismarine_shard, 1), Character.valueOf('2'), new ItemStack(Items.prismarine_shard, 1),
                            Character.valueOf('3'), new ItemStack(Items.prismarine_shard, 1), Character.valueOf('5'),
                            new ItemStack(Items.prismarine_shard, 1), Character.valueOf('6'), new ItemStack(Items.prismarine_shard, 1),
                            Character.valueOf('8'), new ItemStack(Items.prismarine_shard, 1),});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"XXX", "3X5", "6X8", Character.valueOf('3'), new ItemStack(Items.prismarine_shard, 1), Character.valueOf('5'),
                            new ItemStack(Items.prismarine_shard, 1), Character.valueOf('6'), new ItemStack(Items.prismarine_shard, 1),
                            Character.valueOf('8'), new ItemStack(Items.prismarine_shard, 1),});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"0X2", "3X5", "XXX", Character.valueOf('0'), new ItemStack(Items.prismarine_shard, 1), Character.valueOf('2'),
                            new ItemStack(Items.prismarine_shard, 1), Character.valueOf('3'), new ItemStack(Items.prismarine_shard, 1),
                            Character.valueOf('5'), new ItemStack(Items.prismarine_shard, 1),});
            helmet.setCreativeTab(ArmorPlus.tabArmorPlus);
            chestplate.setCreativeTab(ArmorPlus.tabArmorPlus);
            legs.setCreativeTab(ArmorPlus.tabArmorPlus);
            boots.setCreativeTab(ArmorPlus.tabArmorPlus);
        }
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
                {4, 8, 11, 6}, 28, SoundEvents.item_armor_equip_diamond);

        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&b" + "It is Thorny and gives you Water Breathing"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableGuardianHEffects && entity instanceof EntityLivingBase && !ConfigHandler.enableFullGuardianArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.waterBreathing, 120, 0, true, true));
                }
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == Items.prismarine_shard;
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
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.waterBreathing, 120, 0, true, true));
                }
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == Items.prismarine_shard;
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
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.waterBreathing, 120, 0, true, true));
                }
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == Items.prismarine_shard;
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
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.waterBreathing, 120, 0, true, true));
                }
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == Items.prismarine_shard;
            }
        }).setUnlocalizedName("GuardianBoots");
        boots.setMaxStackSize(1);
        GameRegistry.registerItem(helmet, "GuardianHelmet");
        GameRegistry.registerItem(chestplate, "GuardianChestplate");
        GameRegistry.registerItem(legs, "GuardianLeggings");
        GameRegistry.registerItem(boots, "GuardianBoots");
    }
}