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
import sokratis12gr.armorplus.ArmorPlus;
import sokratis12gr.armorplus.resources.ConfigHandler;

import java.util.List;

public class EmeraldArmor {

    public static Item helmet;
    public static Item chestplate;
    public static Item legs;
    public static Item boots;

    static {

        ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("EMERALDARMOR", ArmorPlus.MODID + ":" + "EmeraldArmor", 35, new int[]
                {3, 6, 9, 4}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
                int emeraldArmorEffectLevel = ConfigHandler.emeraldArmorEffectlevel + 1;
                if (ConfigHandler.enableEmeraldHHaste) {
                    infoList.add("\2479Ability: " + "\247rHaste " + emeraldArmorEffectLevel);
                    infoList.add("\2473Use: " + "\247rEquip A Piece");
                }
                if (ConfigHandler.enableFullEmeraldArmorEffect) {
                    infoList.add("\2479Ability: " + "\247rHaste " + emeraldArmorEffectLevel);
                    infoList.add("\2473Use: " + "\247rEquip The Full Set");
                }
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableEmeraldHHaste && entity instanceof EntityLivingBase && !ConfigHandler.enableFullEmeraldArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, ConfigHandler.emeraldArmorEffectlevel, true, true));
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.DARK_GREEN + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                if (ConfigHandler.recipes == 0) {
                    return repair.getItem() == Items.EMERALD;
                }
                if (ConfigHandler.recipes == 1) {
                    return repair.getItem() == Item.getItemFromBlock(Blocks.EMERALD_BLOCK);
                }
                return true;
            }
        }).setUnlocalizedName("EmeraldHelmet");
        helmet.setMaxStackSize(1);

        chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
                int emeraldArmorEffectLevel = ConfigHandler.emeraldArmorEffectlevel + 1;
                if (ConfigHandler.enableEmeraldCHaste) {
                    infoList.add("\2479Ability: " + "\247rHaste " + emeraldArmorEffectLevel);
                    infoList.add("\2473Use: " + "\247rEquip A Piece");
                }
                if (ConfigHandler.enableFullEmeraldArmorEffect) {
                    infoList.add("\2479Ability: " + "\247rHaste " + emeraldArmorEffectLevel);
                    infoList.add("\2473Use: " + "\247rEquip The Full Set");
                }
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableEmeraldCHaste && entity instanceof EntityLivingBase && !ConfigHandler.enableFullEmeraldArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, ConfigHandler.emeraldArmorEffectlevel, true, true));
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.DARK_GREEN + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                if (ConfigHandler.recipes == 0) {
                    return repair.getItem() == Items.EMERALD;
                }
                if (ConfigHandler.recipes == 1) {
                    return repair.getItem() == Item.getItemFromBlock(Blocks.EMERALD_BLOCK);
                }
                return true;
            }
        }).setUnlocalizedName("EmeraldChestplate");
        chestplate.setMaxStackSize(1);

        legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
                int emeraldArmorEffectLevel = ConfigHandler.emeraldArmorEffectlevel + 1;
                if (ConfigHandler.enableEmeraldLHaste) {
                    infoList.add("\2479Ability: " + "\247rHaste " + emeraldArmorEffectLevel);
                    infoList.add("\2473Use: " + "\247rEquip A Piece");
                }
                if (ConfigHandler.enableFullEmeraldArmorEffect) {
                    infoList.add("\2479Ability: " + "\247rHaste " + emeraldArmorEffectLevel);
                    infoList.add("\2473Use: " + "\247rEquip The Full Set");
                }
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableEmeraldLHaste && entity instanceof EntityLivingBase && !ConfigHandler.enableFullEmeraldArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, ConfigHandler.emeraldArmorEffectlevel, true, true));
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.DARK_GREEN + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                if (ConfigHandler.recipes == 0) {
                    return repair.getItem() == Items.EMERALD;
                }
                if (ConfigHandler.recipes == 1) {
                    return repair.getItem() == Item.getItemFromBlock(Blocks.EMERALD_BLOCK);
                }
                return true;
            }
        }).setUnlocalizedName("EmeraldLeggings");
        legs.setMaxStackSize(1);

        boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
                int emeraldArmorEffectLevel = ConfigHandler.emeraldArmorEffectlevel + 1;
                if (ConfigHandler.enableEmeraldBHaste) {
                    infoList.add("\2479Ability: " + "\247rHaste " + emeraldArmorEffectLevel);
                    infoList.add("\2473Use: " + "\247rEquip A Piece");
                }
                if (ConfigHandler.enableFullEmeraldArmorEffect) {
                    infoList.add("\2479Ability: " + "\247rHaste " + emeraldArmorEffectLevel);
                    infoList.add("\2473Use: " + "\247rEquip The Full Set");
                }
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                if (ConfigHandler.enableEmeraldBHaste && entity instanceof EntityLivingBase && !ConfigHandler.enableFullEmeraldArmorEffect) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.HASTE, 120, ConfigHandler.emeraldArmorEffectlevel, true, true));
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.DARK_GREEN + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }

            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                if (ConfigHandler.recipes == 0) {
                    return repair.getItem() == Items.EMERALD;
                }
                if (ConfigHandler.recipes == 1) {
                    return repair.getItem() == Item.getItemFromBlock(Blocks.EMERALD_BLOCK);
                }
                return true;
            }
        }).setUnlocalizedName("EmeraldBoots");
        boots.setMaxStackSize(1);

        GameRegistry.registerItem(helmet, "emerald_helmet");
        GameRegistry.registerItem(chestplate, "emerald_chestplate");
        GameRegistry.registerItem(legs, "emerald_leggings");
        GameRegistry.registerItem(boots, "emerald_boots");

    }

    public Object instance;

    public EmeraldArmor() {
    }

    public void load(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {

            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:EmeraldHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:EmeraldChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:EmeraldLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:EmeraldBoots", "inventory"));
        }
        helmet.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        chestplate.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        legs.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        boots.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
    }

    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {

            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:EmeraldHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:EmeraldChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:EmeraldLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:EmeraldBoots", "inventory"));
        }
    }

    public void registerRenderers() {
    }
}
