/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.armors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ModConfig.RegistryConfig.OriginMaterial.OriginArmor;
import net.thedragonteam.armorplus.ModConfig.RegistryConfig.SpecialMaterial.Armor;
import net.thedragonteam.armorplus.api.properties.iface.IEffectHolder;
import net.thedragonteam.armorplus.api.properties.iface.IRemovable;
import net.thedragonteam.armorplus.api.properties.iface.IRepairable;
import net.thedragonteam.armorplus.util.ToolTipUtils;

import java.util.List;

import static net.minecraft.init.Blocks.*;
import static net.minecraft.init.Items.FEATHER;
import static net.minecraft.inventory.EntityEquipmentSlot.CHEST;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.ModConfig.Misc.enableFlightAbility;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.*;
import static net.thedragonteam.armorplus.armors.base.ItemArmorBase.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.blockCompressedObsidian;
import static net.thedragonteam.armorplus.registry.ModItems.itemLavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.armorplus.util.ArmorPlusItemUtils.applyEffects;
import static net.thedragonteam.armorplus.util.ArmorPlusItemUtils.isFullSet;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.addPotion;
import static net.thedragonteam.armorplus.util.ToolTipUtils.*;
import static net.thedragonteam.armorplus.util.Utils.boxList;
import static net.thedragonteam.armorplus.util.Utils.getTCIngot;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public enum APArmorMaterial implements IEffectHolder, IRepairable, IRemovable {
    COAL(coalArmor, "coal", global_registry.enableCoalArmor, coal.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, coal, slot, coalHelmet, coalChestplate, coalLeggings, coalBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getItemStack(COAL_BLOCK);
        }
    },
    LAPIS(lapisArmor, "lapis", global_registry.enableLapisArmor, lapis.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, lapis, slot, lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getItemStack(LAPIS_BLOCK);
        }
    },
    REDSTONE(redstoneArmor, "redstone", global_registry.enableRedstoneArmor, redstone.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, redstone, slot, redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getItemStack(REDSTONE_BLOCK);
        }
    },
    EMERALD(emeraldArmor, "emerald", global_registry.enableEmeraldArmor, emerald.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, emerald, slot, emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getItemStack(EMERALD_BLOCK);
        }
    },
    OBSIDIAN(obsidianArmor, "obsidian", global_registry.enableObsidianArmor, obsidian.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, obsidian, slot, obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getItemStack(blockCompressedObsidian);
        }
    },
    LAVA(lavaArmor, "infused_lava", global_registry.enableLavaArmor, lava.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            if (!lava.armor.enableSetEffects) {
                player.extinguish();
                player.setAbsorptionAmount(player.isInLava() ? 4.0F : 0.0F);
                if (lava.armor.enableOnWaterTouchDeBuff && player.isInWater() && player.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
                    addPotion(player, MobEffects.SLOWNESS, 1, BAD);
                    for (ItemStack e : player.getArmorInventoryList()) {
                        e.damageItem(1, player);
                    }
                    player.attackEntityFrom(DamageSource.DROWN, 1F);
                }
            } else if (isFullSet(player, lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots) && player.isInWater()) {
                player.extinguish();
                if (player.isInLava()) {
                    player.setAbsorptionAmount(4.0f);
                }
                if (lava.armor.enableOnWaterTouchDeBuff && player.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
                    addPotion(player, MobEffects.SLOWNESS, 120, 1, BAD);
                    for (ItemStack e : player.getArmorInventoryList()) {
                        e.damageItem(1, player);
                    }
                    player.attackEntityFrom(DamageSource.DROWN, 1f);
                }
            }
            this.addEffects(player, lava, slot, lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getItemStack(itemLavaCrystal, 1);
        }
    },
    GUARDIAN(guardianArmor, "guardian", global_registry.enableGuardianArmor, guardian.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, guardian, slot, guardianHelmet, guardianChestplate, guardianLeggings, guardianBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getItemStack(materials, 1);
        }
    },
    SUPER_STAR(superStarArmor, "super_star", global_registry.enableSuperStarArmor, super_star.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, super_star, slot, superStarHelmet, superStarChestplate, superStarLeggings, superStarBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getItemStack(materials, 2);
        }
    },
    ENDER_DRAGON(enderDragonArmor, "ender_dragon", global_registry.enableEnderDragonArmor, ender_dragon.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            if (enableFlightAbility) {
                if (isFullSet(player, enderDragonHelmet, enderDragonChestplate, enderDragonLeggings, enderDragonBoots)) {
                    player.capabilities.allowFlying = true;
                } else {
                    player.capabilities.isFlying = false;
                    player.capabilities.allowFlying = false;
                }
            }
            this.addEffects(player, ender_dragon, slot, enderDragonHelmet, enderDragonChestplate, enderDragonLeggings, enderDragonBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getItemStack(materials, 3);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
            super.addInformation(stack, world, tooltip, advanced);
            if (isKeyDown()) {
                ToolTipUtils.addToolTip(tooltip, "§eSpecial ability: Flight");
            }
        }
    },
    ARDITE(arditeArmor, "ardite", global_registry.enableArditeArmor, ardite.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, ardite, slot, arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getTCIngot(1);
        }
    },
    COBALT(cobaltArmor, "cobalt", global_registry.enableCobaltArmor, cobalt.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, cobalt, slot, cobaltHelmet, cobaltChestplate, cobaltLeggings, cobaltBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getTCIngot(0);
        }
    },
    MANYULLYN(manyullynArmor, "manyullyn", global_registry.enableManyullynArmor, manyullyn.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, manyullyn, slot, manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getTCIngot(2);
        }
    },
    KNIGHT_SLIME(knightSlimeArmor, "knight_slime", global_registry.enableKnightSlimeArmor, knight_slime.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, knight_slime, slot, knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getTCIngot(3);
        }
    },
    PIG_IRON(pigIronArmor, "pig_iron", global_registry.enablePigIronArmor, pig_iron.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            if (pig_iron.armor.enableSetEffects && isFullSet(player, pigIronHelmet, pigIronChestplate, pigIronLeggings, pigIronBoots)) {
                for (ItemStack e : player.getArmorInventoryList()) {
                    e.damageItem(1, player);
                }
            }
            this.addEffects(player, pig_iron, slot, pigIronHelmet, pigIronChestplate, pigIronLeggings, pigIronBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getTCIngot(4);
        }
    },
    SLIME(slimeArmor, "slime", global_registry.enableSlimeArmor, slime.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slime, slot, slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getItemStack(SLIME_BLOCK);
        }
    },
    CHICKEN(chickenArmor, "chicken", global_registry.enableChickenArmor, chicken.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, chicken, slot, chickenHelmet, chickenChestplate, chickenLeggings, chickenBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getItemStack(FEATHER);
        }
    },
    ;

    private final ArmorMaterial armorMaterial;
    private final String name;
    private final boolean enableArmor;
    private final String formatting;
    private final boolean enableSetEffects;
    private final String[] effects;
    private final int[] effectLevels;
    private final int[] effectDurations;
    private final String[] removeEffects;
    private final boolean isUnbreakable;
    private final boolean[] areEffectsEnabled;

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, boolean enableArmor, OriginArmor armor) {
        this(armorMaterialIn, nameIn, enableArmor,
            armor.itemNameColor, armor.enableSetEffects, armor.addPotionEffects, armor.effectLevels, armor.effectDurations, armor.enablePieceEffects, armor.removePotionEffects, armor.setUnbreakable
        );
    }

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, boolean enableArmor, Armor armor) {
        this(armorMaterialIn, nameIn, enableArmor,
            armor.itemNameColor, armor.enableSetEffects, armor.addPotionEffects, armor.effectLevels, armor.effectDurations, armor.enablePieceEffects, armor.removePotionEffects, armor.setUnbreakable
        );
    }

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, boolean enableArmor, String textFormattingIn,
                    boolean enableSetEffects, String[] effects, int[] effectLevels, int[] effectDurations, boolean[] areEffectsEnabledIn,
                    String[] removeEffects, boolean isUnbreakable) {
        this.armorMaterial = armorMaterialIn;
        this.name = nameIn;
        this.enableArmor = enableArmor;
        this.formatting = textFormattingIn;
        this.enableSetEffects = enableSetEffects;
        this.effects = effects;
        this.effectLevels = effectLevels;
        this.effectDurations = effectDurations;
        this.areEffectsEnabled = areEffectsEnabledIn;
        this.removeEffects = removeEffects;
        this.isUnbreakable = isUnbreakable;
    }

    @Override
    public List<String> getApplyEffectNames() {
        return boxList(this.effects);
    }

    @Override
    public List<Integer> getApplyEffectLevels() {
        return boxList(this.effectLevels);
    }

    @Override
    public List<Integer> getApplyEffectDurations() {
        return boxList(this.effectDurations);
    }

    @Override
    public List<String> getRemoveEffectNames() {
        return boxList(this.removeEffects);
    }

    @Override
    public ItemStack getRepairStack() {
        return getRepairItem().isEmpty() ? ItemStack.EMPTY : getRepairItem();
    }

    public abstract ItemStack getRepairItem();

    @Override
    public boolean isEnabled() {
        return this.enableArmor;
    }

    public ArmorMaterial getArmorMaterial() {
        return this.armorMaterial;
    }

    public String getName() {
        return this.name;
    }

    public TextFormatting getFormatting() {
        return getValueByName(this.formatting);
    }

    public boolean enableFullArmorEffect() {
        return this.enableSetEffects;
    }

    public boolean[] areEffectsEnabled() {
        return this.areEffectsEnabled;
    }

    public abstract void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot);

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (isKeyDown()) {
            if (!this.enableFullArmorEffect()) {
                addToolTipPiece(tooltip, getApplyEffectNames(), this.getApplyEffectLevels());
            } else {
                addToolTipFull(tooltip, getApplyEffectNames(), this.getApplyEffectLevels());
            }
        } else {
            showInfo(tooltip, keyBindSneak, this.getFormatting());
        }
    }

    public void addEffects(EntityPlayer player, OriginMaterial material, EntityEquipmentSlot slot, Item head, Item chestplate, Item legs, Item feet) {
        this.addPieceEffects(player, material, slot);
        this.addSetEffects(player, material, head, chestplate, legs, feet);
    }

    public void addEffects(EntityPlayer player, SpecialMaterial material, EntityEquipmentSlot slot, Item head, Item chestplate, Item legs, Item feet) {
        this.addPieceEffects(player, material, slot);
        this.addSetEffects(player, material, head, chestplate, legs, feet);
    }

    public void addPieceEffects(EntityPlayer player, OriginMaterial material, EntityEquipmentSlot slot) {
        OriginArmor armor = material.armor;
        this.addPieceEffects(player, armor.addPotionEffects, armor.effectLevels, armor.effectDurations, armor.removePotionEffects, slot);
    }

    public void addPieceEffects(EntityPlayer player, SpecialMaterial material, EntityEquipmentSlot slot) {
        Armor armor = material.armor;
        this.addPieceEffects(player, armor.addPotionEffects, armor.effectLevels, armor.effectDurations, armor.removePotionEffects, slot);
    }

    public void addPieceEffects(EntityPlayer player, String[] applyEffectNames, int[] applyEffectLevels, int[] applyEffectDurations, String[] removeEffectNames, EntityEquipmentSlot slot) {
        this.addEffects(player, boxList(applyEffectNames), boxList(applyEffectLevels), boxList(applyEffectDurations), boxList(removeEffectNames), slot == FEET ? 0 : slot == LEGS ? 1 : slot == CHEST ? 2 : slot == HEAD ? 3 : -1);
    }

    public void addEffects(EntityPlayer player, List<String> applyEffectNames, List<Integer> applyEffectLevels, List<Integer> applyEffectDurations, List<String> removeEffectNames, int index) {
        if (!enableFullArmorEffect()) {
            if (this.areEffectsEnabled()[index]) {
                applyEffects(player, applyEffectNames, applyEffectDurations, applyEffectLevels, removeEffectNames);
            }
        }
    }

    public void addSetEffects(EntityPlayer player, String[] applyEffectNames, int[] applyEffectLevels, int[] applyEffectDurations, String[] removeEffectNames, Item head, Item chest, Item legs, Item feet) {
        this.addSetEffects(player, boxList(applyEffectNames), boxList(applyEffectLevels), boxList(applyEffectDurations), boxList(removeEffectNames), head, chest, legs, feet);
    }

    public void addSetEffects(EntityPlayer player, OriginMaterial material, Item head, Item chest, Item legs, Item feet) {
        OriginArmor armor = material.armor;
        addSetEffects(player, armor.addPotionEffects, armor.effectLevels, armor.effectDurations, armor.removePotionEffects, head, chest, legs, feet);
    }

    public void addSetEffects(EntityPlayer player, SpecialMaterial material, Item head, Item chest, Item legs, Item feet) {
        Armor armor = material.armor;
        addSetEffects(player, armor.addPotionEffects, armor.effectLevels, armor.effectDurations, armor.removePotionEffects, head, chest, legs, feet);
    }

    public void addSetEffects(EntityPlayer player, List<String> applyEffectNames, List<Integer> applyEffectLevels, List<Integer> applyEffectDurations, List<String> removeEffectNames, Item head, Item chest, Item legs, Item feet) {
        if (enableFullArmorEffect()) {
            if (this.enableFullArmorEffect() && isFullSet(player, head, chest, legs, feet)) {
                applyEffects(player, applyEffectNames, applyEffectDurations, applyEffectLevels, removeEffectNames);
            }
        }
    }

    public boolean isUnbreakable() {
        return isUnbreakable;
    }

    // @Override
    // public Map<Enchantment, Integer> getEnchantments() {
    //     Map<Enchantment, Integer> appliedEnchantments = new HashMap<>();
    //     if (!enchantments.isEmpty()) {
    //         enchantments.forEach((key, value) -> {
    //             if (ModConfig.DebugConfig.debugMode && ModConfig.DebugConfig.debugModeEnchantments) {
    //                 LogHelper.info("Enchantment " + key + " is being applied with amplifier level of " + value);
    //             }
    //             Enchantment enchantment = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(key));
    //             if (enchantment != null) {
    //                 appliedEnchantments.put(enchantment, value);
    //             }
    //         });
    //     }
    //     return appliedEnchantments;
    // }
}