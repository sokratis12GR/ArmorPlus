package sokratis12GR.ArmorPlus.armors;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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

public class RedstoneArmor {

    public RedstoneArmor() {
    }

    public static Item helmet;
    public static Item chestplate;
    public static Item legs;
    public static Item boots;
    public Object instance;

    public void load(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:RedstoneHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:RedstoneChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:RedstoneLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:RedstoneBoots", "inventory"));
        }
        if (ConfigHandler.enableRedstoneArmorRecipes) {
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"XXX", "345", "6X8", Character.valueOf('3'), new ItemStack(Blocks.redstone_block, 1),
                            Character.valueOf('4'), new ItemStack(Blocks.redstone_block, 1), Character.valueOf('5'),
                            new ItemStack(Blocks.redstone_block, 1), Character.valueOf('6'),
                            new ItemStack(Blocks.redstone_block, 1), Character.valueOf('8'),
                            new ItemStack(Blocks.redstone_block, 1),});
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"012", "3X5", "XXX", Character.valueOf('0'), new ItemStack(Blocks.redstone_block, 1),
                            Character.valueOf('1'), new ItemStack(Blocks.redstone_block, 1), Character.valueOf('2'),
                            new ItemStack(Blocks.redstone_block, 1), Character.valueOf('3'),
                            new ItemStack(Blocks.redstone_block, 1), Character.valueOf('5'),
                            new ItemStack(Blocks.redstone_block, 1),});
            GameRegistry.addRecipe(new ItemStack(chestplate, 1), new Object[]
                    {"0X2", "345", "678", Character.valueOf('0'), new ItemStack(Blocks.redstone_block, 1),
                            Character.valueOf('2'), new ItemStack(Blocks.redstone_block, 1), Character.valueOf('3'),
                            new ItemStack(Blocks.redstone_block, 1), Character.valueOf('4'),
                            new ItemStack(Blocks.redstone_block, 1), Character.valueOf('5'),
                            new ItemStack(Blocks.redstone_block, 1), Character.valueOf('6'),
                            new ItemStack(Blocks.redstone_block, 1), Character.valueOf('7'),
                            new ItemStack(Blocks.redstone_block, 1), Character.valueOf('8'),
                            new ItemStack(Blocks.redstone_block, 1),});
            GameRegistry.addRecipe(new ItemStack(legs, 1), new Object[]
                    {"012", "3X5", "6X8", Character.valueOf('0'), new ItemStack(Blocks.redstone_block, 1),
                            Character.valueOf('1'), new ItemStack(Blocks.redstone_block, 1), Character.valueOf('2'),
                            new ItemStack(Blocks.redstone_block, 1), Character.valueOf('3'),
                            new ItemStack(Blocks.redstone_block, 1), Character.valueOf('5'),
                            new ItemStack(Blocks.redstone_block, 1), Character.valueOf('6'),
                            new ItemStack(Blocks.redstone_block, 1), Character.valueOf('8'),
                            new ItemStack(Blocks.redstone_block, 1),});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"XXX", "3X5", "6X8", Character.valueOf('3'), new ItemStack(Blocks.redstone_block, 1),
                            Character.valueOf('5'), new ItemStack(Blocks.redstone_block, 1), Character.valueOf('6'),
                            new ItemStack(Blocks.redstone_block, 1), Character.valueOf('8'),
                            new ItemStack(Blocks.redstone_block, 1),});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"0X2", "3X5", "XXX", Character.valueOf('0'), new ItemStack(Blocks.redstone_block, 1),
                            Character.valueOf('2'), new ItemStack(Blocks.redstone_block, 1), Character.valueOf('3'),
                            new ItemStack(Blocks.redstone_block, 1), Character.valueOf('5'),
                            new ItemStack(Blocks.redstone_block, 1),});
            helmet.setCreativeTab(ArmorPlus.tabArmorPlus);
            chestplate.setCreativeTab(ArmorPlus.tabArmorPlus);
            legs.setCreativeTab(ArmorPlus.tabArmorPlus);
            boots.setCreativeTab(ArmorPlus.tabArmorPlus);
        }
    }

    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:RedstoneHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:RedstoneChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:RedstoneLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:RedstoneBoots", "inventory"));
        }
    }

    public void registerRenderers() {
    }

    static {
        ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("REDSTONEARMOR", ArmorPlus.MODID + ":" + "RedstoneArmor", 11, new int[]
                {1, 3, 5, 2}, 25, SoundEvents.item_armor_equip_gold);

        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&4" + "Gives you Speed 2"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableRedstoneHSpeed) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.moveSpeed, 120, 1));
                }
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == getItemFromBlock(Blocks.redstone_block);
            }
        }).setUnlocalizedName("RedstoneHelmet");
        helmet.setMaxStackSize(1);
        chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&4" + "Gives you Speed 2"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableRedstoneCSpeed) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.moveSpeed, 120, 1));
                }
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == getItemFromBlock(Blocks.redstone_block);
            }
        }).setUnlocalizedName("RedstoneChestplate");
        chestplate.setMaxStackSize(1);
        legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&4" + "Gives you Speed 2"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableRedstoneLSpeed) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.moveSpeed, 120, 1));
                }
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == getItemFromBlock(Blocks.redstone_block);
            }
        }).setUnlocalizedName("RedstoneLeggings");
        legs.setMaxStackSize(1);
        boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&4" + "Gives you Speed 2"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableRedstoneBSpeed) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.moveSpeed, 120, 1));
                }
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == getItemFromBlock(Blocks.redstone_block);
            }
        }).setUnlocalizedName("RedstoneBoots");
        boots.setMaxStackSize(1);

        GameRegistry.registerItem(helmet, "RedstoneHelmet");
        GameRegistry.registerItem(chestplate, "RedstoneChestplate");
        GameRegistry.registerItem(legs, "RedstoneLeggings");
        GameRegistry.registerItem(boots, "RedstoneBoots");

    }

}
