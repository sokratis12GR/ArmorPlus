/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.armors;

import com.sofodev.armorplus.api.properties.AbilityCanceller;
import com.sofodev.armorplus.api.properties.AbilityProvider;
import com.sofodev.armorplus.api.properties.iface.IEffectHolder;
import com.sofodev.armorplus.api.properties.iface.IRepairable;
import com.sofodev.armorplus.client.utils.ToolTipUtils;
import com.sofodev.armorplus.config.ModConfig.RegistryConfig;
import com.sofodev.armorplus.data.Abilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

import static com.sofodev.armorplus.client.utils.ToolTipUtils.*;
import static com.sofodev.armorplus.config.ModConfig.Misc.enableFlightAbility;
import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.*;
import static com.sofodev.armorplus.items.armors.base.ItemPlaceholderArmor.*;
import static com.sofodev.armorplus.registry.APItems.*;
import static com.sofodev.armorplus.registry.ModBlocks.blockCompressedObsidian;
import static com.sofodev.armorplus.util.ArmorPlusItemUtils.applyEffects;
import static com.sofodev.armorplus.util.ArmorPlusItemUtils.isFullSet;
import static com.sofodev.armorplus.util.PotionUtils.PotionValue.BAD;
import static com.sofodev.armorplus.util.PotionUtils.addPotion;
import static com.sofodev.armorplus.util.Utils.getItem;
import static com.sofodev.armorplus.util.Utils.getTCIngot;
import static net.minecraft.init.Blocks.*;
import static net.minecraft.init.Items.FEATHER;
import static net.minecraft.inventory.EntityEquipmentSlot.CHEST;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;

/**
 * @author Sokratis Fotkatzikis
 */
public enum APArmorMaterial implements IEffectHolder, IRepairable {
    COAL(COAL_ARMOR, RegistryConfig.coal.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, coalHelmet, coalChestplate, coalLeggings, coalBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return new ItemStack(COAL_BLOCK);
        }
    },
    LAPIS(LAPIS_ARMOR, RegistryConfig.lapis.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return new ItemStack(LAPIS_BLOCK);
        }
    },
    REDSTONE(REDSTONE_ARMOR, RegistryConfig.redstone.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return new ItemStack(REDSTONE_BLOCK);
        }
    },
    EMERALD(EMERALD_ARMOR, RegistryConfig.emerald.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return new ItemStack(EMERALD_BLOCK);
        }
    },
    OBSIDIAN(OBSIDIAN_ARMOR, RegistryConfig.obsidian.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return new ItemStack(blockCompressedObsidian.asItem());
        }
    },
    INFUSED_LAVA(LAVA_ARMOR, RegistryConfig.lava.armor) {
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
            } else if (isFullSet(player, lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots)) {
                player.extinguish();
                if (player.isInLava()) {
                    player.setAbsorptionAmount(4.0f);
                }
                if (player.isInWater() && lava.armor.enableOnWaterTouchDeBuff && player.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
                    addPotion(player, MobEffects.SLOWNESS, 120, 1, BAD);
                    for (ItemStack e : player.getArmorInventoryList()) {
                        e.damageItem(1, player);
                    }
                    player.attackEntityFrom(DamageSource.DROWN, 1f);
                }
            }
            this.addEffects(player, slot, lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return new ItemStack(getItem("lava_crystal_infused"));
        }
    },
    GUARDIAN(GUARDIAN_ARMOR, RegistryConfig.guardian.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, guardianHelmet, guardianChestplate, guardianLeggings, guardianBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return new ItemStack(getItem("guardian_scale"));
        }
    },
    SUPER_STAR(SUPER_STAR_ARMOR, RegistryConfig.super_star.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, superStarHelmet, superStarChestplate, superStarLeggings, superStarBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return new ItemStack(getItem("wither_bone"));
        }
    },
    ENDER_DRAGON(ENDER_DRAGON_ARMOR, RegistryConfig.ender_dragon.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            if (enableFlightAbility) {
                if (isFullSet(player, enderDragonHelmet, enderDragonChestplate, enderDragonLeggings, enderDragonBoots) || player.abilities.isCreativeMode || player.isSpectator()) {
                    player.abilities.allowFlying = true;
                } else {
                    player.abilities.isFlying = false;
                    player.abilities.allowFlying = false;
                }
            }
            this.addEffects(player, slot, enderDragonHelmet, enderDragonChestplate, enderDragonLeggings, enderDragonBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return new ItemStack(getItem("ender_dragon_scale"));
        }

        @Override
        @OnlyIn(Dist.CLIENT)
        public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag advanced) {
            super.addInformation(stack, world, tooltip, advanced);
            if (isKeyDown()) {
                ToolTipUtils.addToolTip(tooltip, "\u00a7eSpecial ability: Flight");
            }
        }
    },
    ARDITE(ARDITE_ARMOR, RegistryConfig.ardite) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getTCIngot(1);
        }
    },
    COBALT(COBALT_ARMOR, RegistryConfig.cobalt) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, cobaltHelmet, cobaltChestplate, cobaltLeggings, cobaltBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getTCIngot(0);
        }
    },
    MANYULLYN(MANYULLYN_ARMOR, RegistryConfig.manyullyn) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getTCIngot(2);
        }
    },
    KNIGHT_SLIME(KNIGHT_SLIME_ARMOR, RegistryConfig.knight_slime) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getTCIngot(3);
        }
    },
    PIG_IRON(PIG_IRON_ARMOR, RegistryConfig.pig_iron) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            if (pig_iron.enableSetEffects && isFullSet(player, pigIronHelmet, pigIronChestplate, pigIronLeggings, pigIronBoots)) {
                for (ItemStack e : player.getArmorInventoryList()) {
                    e.damageItem(1, player);
                }
            }
            this.addEffects(player, slot, pigIronHelmet, pigIronChestplate, pigIronLeggings, pigIronBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getTCIngot(4);
        }
    },
    SLIME(SLIME_ARMOR, RegistryConfig.slime) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return new ItemStack(SLIME_BLOCK);
        }
    },
    CHICKEN(CHICKEN_ARMOR, RegistryConfig.chicken) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, chickenHelmet, chickenChestplate, chickenLeggings, chickenBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return new ItemStack(FEATHER);
        }
    },
    ;

    private final IArmorMaterial armorMaterial;
    private final String formatting;
    private final boolean enableSetEffects;
    private final AbilityProvider applicableEffects;
    private final AbilityCanceller removableEffects;
    private final boolean isUnbreakable;
    private final boolean[] areEffectsEnabled;

    APArmorMaterial(IArmorMaterial armorMaterialIn, ArmorProperties armor) {
        this(armorMaterialIn, armor.itemNameColor, armor.abilities, armor.removePotionEffects, armor.setUnbreakable);
    }

    APArmorMaterial(IArmorMaterial armorMaterialIn, String textFormattingIn, Abilities effects,
                    String[] removeEffects, boolean isUnbreakable) {
        this.armorMaterial = armorMaterialIn;
        this.formatting = textFormattingIn;
        this.enableSetEffects = true;
        this.applicableEffects = new AbilityProvider(effects);
        this.areEffectsEnabled = new boolean[]{true, true, true, true};
        this.removableEffects = new AbilityCanceller(removeEffects);
        this.isUnbreakable = isUnbreakable;
    }

    @Override
    public AbilityProvider getApplicableAbilities() {
        return this.applicableEffects;
    }

    @Override
    public AbilityCanceller getRemovableAbilities() {
        return this.removableEffects;
    }

    @Override
    public ItemStack getRepairStack() {
        return getRepairItem().isEmpty() ? ItemStack.EMPTY : getRepairItem();
    }

    public abstract ItemStack getRepairItem();

    public IArmorMaterial getArmorMaterial() {
        return this.armorMaterial;
    }

    public String getName() {
        return this.name().toLowerCase();
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

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag advanced) {
        final KeyBinding keyBindSneak = Minecraft.getInstance().gameSettings.keyBindSneak;
        if (isKeyDown()) {
            if (!this.enableFullArmorEffect()) {
                addToolTipPiece(tooltip, this);
            } else {
                addToolTipFull(tooltip, this);
            }
        } else {
            showInfo(tooltip, keyBindSneak, this.getFormatting());
        }
    }

    public void addEffects(EntityPlayer player, EntityEquipmentSlot slot, Item head, Item chestplate, Item legs, Item feet) {
        this.addPieceEffects(player, slot);
        this.addSetEffects(player, head, chestplate, legs, feet);
    }


    public void addPieceEffects(EntityPlayer player, EntityEquipmentSlot slot) {
        if (slot == FEET) {
            this.addEffects(player, 0);
        } else if (slot == LEGS) {
            this.addEffects(player, 1);
        } else if (slot == CHEST) {
            this.addEffects(player, 2);
        } else {
            this.addEffects(player, slot == HEAD ? 3 : -1);
        }
    }

    public void addEffects(EntityPlayer player, int index) {
        if (!enableFullArmorEffect() && this.areEffectsEnabled()[index]) {
            applyEffects(player, this);
        }
    }

    public void addSetEffects(EntityPlayer player, Item head, Item chest, Item legs, Item feet) {
        if (this.enableFullArmorEffect() && isFullSet(player, head, chest, legs, feet)) {
            applyEffects(player, this);
        }
    }

    public boolean isUnbreakable() {
        return isUnbreakable;
    }
}