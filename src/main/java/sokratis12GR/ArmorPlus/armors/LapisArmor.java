package sokratis12GR.ArmorPlus.armors;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"XXX", "345", "6X8", Character.valueOf('3'), new ItemStack(Blocks.lapis_block, 1), Character.valueOf('4'),
                            new ItemStack(Blocks.lapis_block, 1), Character.valueOf('5'), new ItemStack(Blocks.lapis_block, 1),
                            Character.valueOf('6'), new ItemStack(Blocks.lapis_block, 1), Character.valueOf('8'),
                            new ItemStack(Blocks.lapis_block, 1),});
            GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
                    {"012", "3X5", "XXX", Character.valueOf('0'), new ItemStack(Blocks.lapis_block, 1), Character.valueOf('1'),
                            new ItemStack(Blocks.lapis_block, 1), Character.valueOf('2'), new ItemStack(Blocks.lapis_block, 1),
                            Character.valueOf('3'), new ItemStack(Blocks.lapis_block, 1), Character.valueOf('5'),
                            new ItemStack(Blocks.lapis_block, 1),});
            GameRegistry.addRecipe(new ItemStack(chestplate, 1), new Object[]
                    {"0X2", "345", "678", Character.valueOf('0'), new ItemStack(Blocks.lapis_block, 1), Character.valueOf('2'),
                            new ItemStack(Blocks.lapis_block, 1), Character.valueOf('3'), new ItemStack(Blocks.lapis_block, 1),
                            Character.valueOf('4'), new ItemStack(Blocks.lapis_block, 1), Character.valueOf('5'),
                            new ItemStack(Blocks.lapis_block, 1), Character.valueOf('6'), new ItemStack(Blocks.lapis_block, 1),
                            Character.valueOf('7'), new ItemStack(Blocks.lapis_block, 1), Character.valueOf('8'),
                            new ItemStack(Blocks.lapis_block, 1),});
            GameRegistry.addRecipe(new ItemStack(legs, 1), new Object[]
                    {"012", "3X5", "6X8", Character.valueOf('0'), new ItemStack(Blocks.lapis_block, 1), Character.valueOf('1'),
                            new ItemStack(Blocks.lapis_block, 1), Character.valueOf('2'), new ItemStack(Blocks.lapis_block, 1),
                            Character.valueOf('3'), new ItemStack(Blocks.lapis_block, 1), Character.valueOf('5'),
                            new ItemStack(Blocks.lapis_block, 1), Character.valueOf('6'), new ItemStack(Blocks.lapis_block, 1),
                            Character.valueOf('8'), new ItemStack(Blocks.lapis_block, 1),});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"XXX", "3X5", "6X8", Character.valueOf('3'), new ItemStack(Blocks.lapis_block, 1), Character.valueOf('5'),
                            new ItemStack(Blocks.lapis_block, 1), Character.valueOf('6'), new ItemStack(Blocks.lapis_block, 1),
                            Character.valueOf('8'), new ItemStack(Blocks.lapis_block, 1),});
            GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
                    {"0X2", "3X5", "XXX", Character.valueOf('0'), new ItemStack(Blocks.lapis_block, 1), Character.valueOf('2'),
                            new ItemStack(Blocks.lapis_block, 1), Character.valueOf('3'), new ItemStack(Blocks.lapis_block, 1),
                            Character.valueOf('5'), new ItemStack(Blocks.lapis_block, 1),});
            helmet.setCreativeTab(ArmorPlus.tabArmorPlus);
            chestplate.setCreativeTab(ArmorPlus.tabArmorPlus);
            legs.setCreativeTab(ArmorPlus.tabArmorPlus);
            boots.setCreativeTab(ArmorPlus.tabArmorPlus);
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
                {2, 5, 3, 1}, 25);

        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, 0) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&1" + "Gives you Water Breathing"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableLapisHBreathing) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(13, 120, 0));
                }
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == getItemFromBlock(Blocks.lapis_block);
            }
        }).setUnlocalizedName("LapisHelmet");
        helmet.setMaxStackSize(1);
        chestplate = (new ItemArmor(enuma, armorPreffix, 1) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&1" + "Gives you Water Breathing"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableLapisCBreathing) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(13, 120, 0));
                }
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == getItemFromBlock(Blocks.lapis_block);
            }
        }).setUnlocalizedName("LapisChestplate");
        chestplate.setMaxStackSize(1);
        legs = (new ItemArmor(enuma, armorPreffix, 2) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&1" + "Gives you Water Breathing"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableLapisLBreathing) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(13, 120, 0));
                }
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == getItemFromBlock(Blocks.lapis_block);
            }
        }).setUnlocalizedName("LapisLeggings");
        legs.setMaxStackSize(1);
        boots = (new ItemArmor(enuma, armorPreffix, 3) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
                tooltip.add(TextHelper.getFormattedText("&1" + "Gives you Water Breathing"));
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableLapisBBreathing) {
                    if (entity instanceof EntityLivingBase)
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(13, 120, 0));
                }
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == getItemFromBlock(Blocks.lapis_block);
            }
        }).setUnlocalizedName("LapisBoots");
        boots.setMaxStackSize(1);

        GameRegistry.registerItem(helmet, "LapisHelmet");
        GameRegistry.registerItem(chestplate, "LapisChestplate");
        GameRegistry.registerItem(legs, "LapisLeggings");
        GameRegistry.registerItem(boots, "LapisBoots");

    }
}
