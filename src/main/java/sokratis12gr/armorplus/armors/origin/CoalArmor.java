package sokratis12gr.armorplus.armors.origin;

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
import sokratis12gr.armorplus.ARPConfig;
import sokratis12gr.armorplus.ArmorPlus;

import java.util.List;

public class CoalArmor {

    public static Item helmet;
    public static Item chestplate;
    public static Item legs;
    public static Item boots;

    static {
        ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("COALARMOR", ArmorPlus.MODID + ":" + "CoalArmor", 7, new int[]
                {1, 2, 3, 1}, 8, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);

        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
                if (ARPConfig.enableCoalHNightVision) {
                    infoList.add("\2479Ability: " + "\247rNight Vision");
                    infoList.add("\2473Use: " + "\247rEquip A Piece");
                }
                if (ARPConfig.enableFullCoalArmorEffect) {
                    infoList.add("\2479Ability: " + "\247rNight Vision");
                    infoList.add("\2473Use: " + "\247rEquip The Full Set");
                }
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ARPConfig.enableCoalHNightVision && entity instanceof EntityLivingBase && !ARPConfig.enableFullCoalArmorEffect) {
                    entity.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 240, 0, true, true));
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.GRAY + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                if (ARPConfig.recipes == 0) {
                    return repair.getItem() == Items.COAL;
                }
                if (ARPConfig.recipes == 1) {
                    return repair.getItem() == Item.getItemFromBlock(Blocks.COAL_BLOCK);
                }
                return true;
            }
        }).setUnlocalizedName("CoalHelmet");
        helmet.setMaxStackSize(1);

        chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
                if (ARPConfig.enableCoalCNightVision) {
                    infoList.add("\2479Ability: " + "\247rNight Vision");
                    infoList.add("\2473Use: " + "\247rEquip A Piece");
                }
                if (ARPConfig.enableFullCoalArmorEffect) {
                    infoList.add("\2479Ability: " + "\247rNight Vision");
                    infoList.add("\2473Use: " + "\247rEquip The Full Set");
                }
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ARPConfig.enableCoalCNightVision && entity instanceof EntityLivingBase && !ARPConfig.enableFullCoalArmorEffect) {
                    entity.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 240, 0, true, true));
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.GRAY + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                if (ARPConfig.recipes == 0) {
                    return repair.getItem() == Items.COAL;
                }
                if (ARPConfig.recipes == 1) {
                    return repair.getItem() == Item.getItemFromBlock(Blocks.COAL_BLOCK);
                }
                return true;
            }
        }).setUnlocalizedName("CoalChestplate");
        chestplate.setMaxStackSize(1);

        legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
                if (ARPConfig.enableCoalLNightVision) {
                    infoList.add("\2479Ability: " + "\247rNight Vision");
                    infoList.add("\2473Use: " + "\247rEquip A Piece");
                }
                if (ARPConfig.enableFullCoalArmorEffect) {
                    infoList.add("\2479Ability: " + "\247rNight Vision");
                    infoList.add("\2473Use: " + "\247rEquip The Full Set");
                }
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ARPConfig.enableCoalLNightVision && entity instanceof EntityLivingBase && !ARPConfig.enableFullCoalArmorEffect) {
                    entity.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 240, 0, true, true));
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.GRAY + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                if (ARPConfig.recipes == 0) {
                    return repair.getItem() == Items.COAL;
                }
                if (ARPConfig.recipes == 1) {
                    return repair.getItem() == Item.getItemFromBlock(Blocks.COAL_BLOCK);
                }
                return true;
            }
        }).setUnlocalizedName("CoalLeggings");
        legs.setMaxStackSize(1);

        boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
                if (ARPConfig.enableCoalBNightVision) {
                    infoList.add("\2479Ability: " + "\247rNight Vision");
                    infoList.add("\2473Use: " + "\247rEquip A Piece");
                }
                if (ARPConfig.enableFullCoalArmorEffect) {
                    infoList.add("\2479Ability: " + "\247rNight Vision");
                    infoList.add("\2473Use: " + "\247rEquip The Full Set");
                }
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ARPConfig.enableCoalBNightVision && entity instanceof EntityLivingBase && !ARPConfig.enableFullCoalArmorEffect) {
                    entity.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 240, 0, true, true));
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.GRAY + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                if (ARPConfig.recipes == 0) {
                    return repair.getItem() == Items.COAL;
                }
                if (ARPConfig.recipes == 1) {
                    return repair.getItem() == Item.getItemFromBlock(Blocks.COAL_BLOCK);
                }
                return true;
            }
        }).setUnlocalizedName("CoalBoots");
        boots.setMaxStackSize(1);

        GameRegistry.registerItem(helmet, "coal_helmet");
        GameRegistry.registerItem(chestplate, "coal_chestplate");
        GameRegistry.registerItem(legs, "coal_leggings");
        GameRegistry.registerItem(boots, "coal_boots");
    }

    public Object instance;

    public CoalArmor() {
    }

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
        helmet.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        chestplate.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        legs.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        boots.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
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
}
