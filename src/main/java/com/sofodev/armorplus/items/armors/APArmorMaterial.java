/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.armors;

import com.sofodev.armorplus.api.properties.AbilityCanceller;
import com.sofodev.armorplus.api.properties.AbilityProvider;
import com.sofodev.armorplus.api.properties.iface.IEffectHolder;
import com.sofodev.armorplus.api.properties.iface.IRemovable;
import com.sofodev.armorplus.api.properties.iface.IRepairable;
import com.sofodev.armorplus.items.armors.base.ItemArmorBase;
import com.sofodev.armorplus.client.utils.ToolTipUtils;
import com.sofodev.armorplus.config.Abilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static com.sofodev.armorplus.client.utils.ToolTipUtils.*;
import static com.sofodev.armorplus.config.ModConfig.Misc.enableFlightAbility;
import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.*;
import static com.sofodev.armorplus.registry.APItems.*;
import static com.sofodev.armorplus.registry.ModBlocks.blockCompressedObsidian;
import static com.sofodev.armorplus.registry.ModItems.itemLavaCrystal;
import static com.sofodev.armorplus.registry.ModItems.materials;
import static com.sofodev.armorplus.util.ArmorPlusItemUtils.applyEffects;
import static com.sofodev.armorplus.util.ArmorPlusItemUtils.isFullSet;
import static com.sofodev.armorplus.util.PotionUtils.PotionType.BAD;
import static com.sofodev.armorplus.util.PotionUtils.addPotion;
import static com.sofodev.armorplus.util.Utils.getTCIngot;
import static net.minecraft.init.Blocks.*;
import static net.minecraft.init.Items.FEATHER;
import static net.minecraft.inventory.EntityEquipmentSlot.CHEST;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
public enum APArmorMaterial implements IEffectHolder, IRepairable, IRemovable {
    COAL(ItemArmorBase.coalArmor, global_registry.enableCoalArmor, coal.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, coalHelmet, coalChestplate, coalLeggings, coalBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getItemStack(COAL_BLOCK);
        }
    },
    LAPIS(ItemArmorBase.lapisArmor, global_registry.enableLapisArmor, lapis.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getItemStack(LAPIS_BLOCK);
        }
    },
    REDSTONE(ItemArmorBase.redstoneArmor, global_registry.enableRedstoneArmor, redstone.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getItemStack(REDSTONE_BLOCK);
        }
    },
    EMERALD(ItemArmorBase.emeraldArmor, global_registry.enableEmeraldArmor, emerald.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getItemStack(EMERALD_BLOCK);
        }
    },
    OBSIDIAN(ItemArmorBase.obsidianArmor, global_registry.enableObsidianArmor, obsidian.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getItemStack(blockCompressedObsidian);
        }
    },
    INFUSED_LAVA(ItemArmorBase.lavaArmor, global_registry.enableLavaArmor, lava.armor) {
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
            return getItemStack(itemLavaCrystal, 1);
        }
    },
    GUARDIAN(ItemArmorBase.guardianArmor, global_registry.enableGuardianArmor, guardian.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, guardianHelmet, guardianChestplate, guardianLeggings, guardianBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getItemStack(materials, 1);
        }
    },
    SUPER_STAR(ItemArmorBase.superStarArmor, global_registry.enableSuperStarArmor, super_star.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, superStarHelmet, superStarChestplate, superStarLeggings, superStarBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getItemStack(materials, 2);
        }
    },
    ENDER_DRAGON(ItemArmorBase.enderDragonArmor, global_registry.enableEnderDragonArmor, ender_dragon.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            if (enableFlightAbility) {
                if (isFullSet(player, enderDragonHelmet, enderDragonChestplate, enderDragonLeggings, enderDragonBoots) || player.capabilities.isCreativeMode || player.isSpectator()) {
                    player.capabilities.allowFlying = true;
                } else {
                    player.capabilities.isFlying = false;
                    player.capabilities.allowFlying = false;
                }
            }
            this.addEffects(player, slot, enderDragonHelmet, enderDragonChestplate, enderDragonLeggings, enderDragonBoots);
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
                ToolTipUtils.addToolTip(tooltip, "\u00a7eSpecial ability: Flight");
            }
        }
    },
    ARDITE(ItemArmorBase.arditeArmor, global_registry.enableArditeArmor, ardite) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getTCIngot(1);
        }
    },
    COBALT(ItemArmorBase.cobaltArmor, global_registry.enableCobaltArmor, cobalt) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, cobaltHelmet, cobaltChestplate, cobaltLeggings, cobaltBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getTCIngot(0);
        }
    },
    MANYULLYN(ItemArmorBase.manyullynArmor, global_registry.enableManyullynArmor, manyullyn) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getTCIngot(2);
        }
    },
    KNIGHT_SLIME(ItemArmorBase.knightSlimeArmor, global_registry.enableKnightSlimeArmor, knight_slime) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getTCIngot(3);
        }
    },
    PIG_IRON(ItemArmorBase.pigIronArmor, global_registry.enablePigIronArmor, pig_iron) {
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
    SLIME(ItemArmorBase.slimeArmor, global_registry.enableSlimeArmor, slime) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getItemStack(SLIME_BLOCK);
        }
    },
    CHICKEN(ItemArmorBase.chickenArmor, global_registry.enableChickenArmor, chicken) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slot, chickenHelmet, chickenChestplate, chickenLeggings, chickenBoots);
        }

        @Override
        public ItemStack getRepairItem() {
            return getItemStack(FEATHER);
        }
    },
    ;

    private final ItemArmor.ArmorMaterial armorMaterial;
    private final boolean enableArmor;
    private final String formatting;
    private final boolean enableSetEffects;
    private final AbilityProvider applicableEffects;
    private final AbilityCanceller removableEffects;
    private final boolean isUnbreakable;
    private final boolean[] areEffectsEnabled;

    APArmorMaterial(ItemArmor.ArmorMaterial armorMaterialIn, boolean enableArmor, ArmorProperties armor) {
        this(armorMaterialIn, enableArmor,
            armor.itemNameColor, armor.enableSetEffects, armor.abilities, armor.enablePieceEffects, armor.removePotionEffects, armor.setUnbreakable
        );
    }

    APArmorMaterial(ItemArmor.ArmorMaterial armorMaterialIn, boolean enableArmor, String textFormattingIn,
                    boolean enableSetEffects, Abilities effects, boolean[] areEffectsEnabledIn,
                    String[] removeEffects, boolean isUnbreakable) {
        this.armorMaterial = armorMaterialIn;
        this.enableArmor = enableArmor;
        this.formatting = textFormattingIn;
        this.enableSetEffects = enableSetEffects;
        this.applicableEffects = new AbilityProvider(effects);
        this.areEffectsEnabled = areEffectsEnabledIn;
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

    @Override
    public boolean isEnabled() {
        return this.enableArmor;
    }

    public ItemArmor.ArmorMaterial getArmorMaterial() {
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

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
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