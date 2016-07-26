package sokratis12gr.armorplus.armors.origin;

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
import sokratis12gr.armorplus.registry.ModItems;

import java.util.List;

public class LavaArmor {

    public static Item helmet;
    public static Item chestplate;
    public static Item legs;
    public static Item boots;

    static {
        ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("LAVAARMOR", ArmorPlus.MODID + ":" + "LavaArmor", 45, new int[]
                {4, 8, 11, 6}, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

        int armorPreffix = 0;
        helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
                int lavaArmorEffectlevel = ARPConfig.lavaArmorEffectlevel + 1;
                if (ARPConfig.enableLavaHEffects) {
                    infoList.add("\2479Ability: " + "\247rResistance " + lavaArmorEffectlevel + " And Fire Resistance");
                    infoList.add("\2473Use: " + "\247rEquip A Piece");
                }
                if (ARPConfig.enableFullLavaArmorEffect) {
                    infoList.add("\2479Ability: " + "\247rResistance " + lavaArmorEffectlevel + " And Fire Resistance");
                    infoList.add("\2473Use: " + "\247rEquip The Full Set");
                }
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {

                ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
                ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
                ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);

                if (ARPConfig.enableLavaHEffects && entity instanceof EntityLivingBase && !ARPConfig.enableFullLavaArmorEffect) {
                    entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 120, ARPConfig.lavaArmorEffectlevel, true, true));
                    entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 120, 0, true, true));
                }
                if (head != null && head.getItem() == LavaArmor.helmet && chest != null && chest.getItem() == LavaArmor.chestplate && legs != null && legs.getItem() == LavaArmor.legs && feet != null && feet.getItem() == LavaArmor.boots) {
                    entity.extinguish();
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.GOLD + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }


            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == ModItems.LAVA_CRYSTAL;
            }
        }).setUnlocalizedName("LavaHelmet");
        helmet.setMaxStackSize(1);
        chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
                int lavaArmorEffectlevel = ARPConfig.lavaArmorEffectlevel + 1;
                if (ARPConfig.enableLavaCEffects) {
                    infoList.add("\2479Ability: " + "\247rResistance " + lavaArmorEffectlevel + " And Fire Resistance");
                    infoList.add("\2473Use: " + "\247rEquip A Piece");
                }
                if (ARPConfig.enableFullLavaArmorEffect) {
                    infoList.add("\2479Ability: " + "\247rResistance " + lavaArmorEffectlevel + " And Fire Resistance");
                    infoList.add("\2473Use: " + "\247rEquip The Full Set");
                }
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
                ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
                ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);

                if (ARPConfig.enableLavaCEffects && entity instanceof EntityLivingBase && !ARPConfig.enableFullLavaArmorEffect) {
                    entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 120, ARPConfig.lavaArmorEffectlevel, true, true));
                    entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 120, 0, true, true));

                }
                if (head != null && head.getItem() == LavaArmor.helmet && chest != null && chest.getItem() == LavaArmor.chestplate && legs != null && legs.getItem() == LavaArmor.legs && feet != null && feet.getItem() == LavaArmor.boots) {
                    entity.extinguish();
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.GOLD + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }


            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == ModItems.LAVA_CRYSTAL;
            }
        }).setUnlocalizedName("LavaChestplate");
        chestplate.setMaxStackSize(1);
        legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
                int lavaArmorEffectlevel = ARPConfig.lavaArmorEffectlevel + 1;
                if (ARPConfig.enableLavaLEffects) {
                    infoList.add("\2479Ability: " + "\247rResistance " + lavaArmorEffectlevel + " And Fire Resistance");
                    infoList.add("\2473Use: " + "\247rEquip A Piece");
                }
                if (ARPConfig.enableFullLavaArmorEffect) {
                    infoList.add("\2479Ability: " + "\247rResistance " + lavaArmorEffectlevel + " And Fire Resistance");
                    infoList.add("\2473Use: " + "\247rEquip The Full Set");
                }
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
                ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
                ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);

                if (ARPConfig.enableLavaLEffects && entity instanceof EntityLivingBase && !ARPConfig.enableFullLavaArmorEffect) {
                    entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 120, ARPConfig.lavaArmorEffectlevel, true, true));
                    entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 120, 0, true, true));
                }
                if (head != null && head.getItem() == LavaArmor.helmet && chest != null && chest.getItem() == LavaArmor.chestplate && legs != null && legs.getItem() == LavaArmor.legs && feet != null && feet.getItem() == LavaArmor.boots) {
                    entity.extinguish();
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.GOLD + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }


            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == ModItems.LAVA_CRYSTAL;
            }
        }).setUnlocalizedName("LavaLeggings");
        legs.setMaxStackSize(1);
        boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET) {
            @Override
            public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
                int lavaArmorEffectlevel = ARPConfig.lavaArmorEffectlevel + 1;
                if (ARPConfig.enableLavaBEffects) {
                    infoList.add("\2479Ability: " + "\247rResistance " + lavaArmorEffectlevel + " And Fire Resistance");
                    infoList.add("\2473Use: " + "\247rEquip A Piece");
                }
                if (ARPConfig.enableFullLavaArmorEffect) {
                    infoList.add("\2479Ability: " + "\247rResistance " + lavaArmorEffectlevel + " And Fire Resistance");
                    infoList.add("\2473Use: " + "\247rEquip The Full Set");
                }
            }

            public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
                ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
                ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
                ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);

                if (ARPConfig.enableLavaBEffects && entity instanceof EntityLivingBase && !ARPConfig.enableFullLavaArmorEffect) {
                    entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 120, ARPConfig.lavaArmorEffectlevel, true, true));
                    entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 120, 0, true, true));
                }
                if (head != null && head.getItem() == LavaArmor.helmet && chest != null && chest.getItem() == LavaArmor.chestplate && legs != null && legs.getItem() == LavaArmor.legs && feet != null && feet.getItem() == LavaArmor.boots) {
                    entity.extinguish();
                }
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (TextFormatting.GOLD + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
            }


            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return repair.getItem() == ModItems.LAVA_CRYSTAL;
            }
        }).setUnlocalizedName("LavaBoots");
        boots.setMaxStackSize(1);

        GameRegistry.registerItem(helmet, "lava_helmet");
        GameRegistry.registerItem(chestplate, "lava_chestplate");
        GameRegistry.registerItem(legs, "lava_leggings");
        GameRegistry.registerItem(boots, "lava_boots");

    }

    public Object instance;

    public LavaArmor() {
    }

    public void load(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:LavaHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:LavaChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:LavaLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:LavaBoots", "inventory"));
        }
        helmet.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        chestplate.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        legs.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
        boots.setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
    }

    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(helmet, 0,
                    new ModelResourceLocation("armorplus:LavaHelmet", "inventory"));
            ModelLoader.setCustomModelResourceLocation(chestplate, 0,
                    new ModelResourceLocation("armorplus:LavaChestplate", "inventory"));
            ModelLoader.setCustomModelResourceLocation(legs, 0,
                    new ModelResourceLocation("armorplus:LavaLeggings", "inventory"));
            ModelLoader.setCustomModelResourceLocation(boots, 0,
                    new ModelResourceLocation("armorplus:LavaBoots", "inventory"));
        }
    }

    public void registerRenderers() {
    }

}
