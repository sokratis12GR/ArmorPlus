/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.armors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModPotions;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.minecraft.init.Blocks.*;
import static net.minecraft.inventory.EntityEquipmentSlot.CHEST;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.armors.base.ItemArmorBase.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.GOOD;
import static net.thedragonteam.armorplus.util.PotionUtils.*;
import static net.thedragonteam.armorplus.util.ToolTipUtils.*;
import static net.thedragonteam.armorplus.util.Utils.isNotEmpty;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getTICItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public enum APArmorMaterial implements IStringSerializable {
    COAL(coalArmor,
        "coal", getItemStack(COAL_BLOCK), coalArmorItemNameColor,
        enableFullCoalArmorEffect, coalArmorAddPotionEffect, coalArmorEffectLevel,
        enableCoalEffect
    ),
    LAPIS(lapisArmor,
        "lapis", getItemStack(LAPIS_BLOCK), lapisArmorItemNameColor,
        enableFullLapisArmorEffect, lapisArmorAddPotionEffect, lapisArmorEffectLevel,
        enableLapisEffect
    ),
    REDSTONE(redstoneArmor,
        "redstone", getItemStack(REDSTONE_BLOCK), redstoneArmorItemNameColor,
        enableFullRedstoneArmorEffect, redstoneArmorAddPotionEffect, redstoneArmorEffectLevel,
        enableRedstoneEffect
    ),
    EMERALD(emeraldArmor,
        "emerald", getItemStack(EMERALD_BLOCK), emeraldArmorItemNameColor,
        enableFullEmeraldArmorEffect, emeraldArmorAddPotionEffect, emeraldArmorEffectLevel,
        enableEmeraldEffect
    ),
    OBSIDIAN(obsidianArmor,
        "obsidian", getItemStack(ModBlocks.compressedObsidian), obsidianArmorItemNameColor,
        enableFullObsidianArmorEffect, obsidianArmorAddPotionEffect, obsidianArmorEffectLevel,
        enableObsidianEffect
    ),
    LAVA(lavaArmor,
        "infused_lava", getItemStack(lavaCrystal, 1), lavaArmorItemNameColor,
        enableFullLavaArmorEffect, lavaArmorAddPotionEffect, lavaArmorEffectLevel,
        enableLavaEffect
    ) {
        @Override
        public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            entity.extinguish();
            if (entity.isInLava() && !enableFullLavaArmorEffect) {
                entity.setAbsorptionAmount(entity.isInLava() ? 4.0F : 0.0F);
            }
            if (enableLavaArmorOnWaterTouchDeBuff) {
                if (entity.isInWater() && !enableFullLavaArmorEffect && entity.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
                    addPotion(entity, MobEffects.SLOWNESS, 1, BAD);
                    itemStack.damageItem(1, entity);
                    entity.attackEntityFrom(DamageSource.DROWN, 1F);
                }
            }
        }
    },
    GUARDIAN(guardianArmor,
        "guardian", getItemStack(materials, 1), guardianArmorItemNameColor,
        enableFullGuardianArmorEffect, guardianArmorAddPotionEffect, guardianArmorEffectLevel,
        enableGuardianEffect
    ),
    SUPER_STAR(superStarArmor,
        "super_star", getItemStack(materials, 2), superStarArmorItemNameColor,
        enableFullSuperStarArmorEffect, superStarArmorAddPotionEffect, superStarArmorEffectLevel,
        enableSuperStarEffect, superStarArmorRemovePotionEffect
    ),
    ENDER_DRAGON(enderDragonArmor,
        "ender_dragon", getItemStack(materials, 3), enderDragonArmorItemNameColor,
        enableFullEnderDragonArmorEffect, "empty", 0,
        enableEnderDragonEffect, enderDragonArmorRemovePotionEffect
    ) {
        @Override
        public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            ItemStack head = entity.getItemStackFromSlot(HEAD);
            ItemStack chest = entity.getItemStackFromSlot(CHEST);
            ItemStack legs = entity.getItemStackFromSlot(LEGS);
            ItemStack feet = entity.getItemStackFromSlot(FEET);
            if (enableFlightAbility) {
                if (head.getItem() == enderDragonHelmet && chest.getItem() == enderDragonChestplate && legs.getItem() == enderDragonLeggings && feet.getItem() == enderDragonBoots || entity.capabilities.isCreativeMode || entity.isSpectator())
                    entity.capabilities.allowFlying = true;
                else {
                    entity.capabilities.isFlying = false;
                    entity.capabilities.allowFlying = false;
                }
            }
            if (getPotion(this.getRemovePotionEffect()) != null && getPotion(this.getRemovePotionEffect()) != ModPotions.EMPTY)
                removePotion(entity, getPotion(this.getRemovePotionEffect()));
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack stack, World playerIn, List<String> tooltip, ITooltipFlag advanced) {
            final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
            if (isKeyDown()) {
                addToolTipFull(tooltip, "Flight");
            } else {
                showInfo(tooltip, keyBindSneak, this.getFormatting());
            }
        }
    },
    ARDITE(arditeArmor, "ardite",
        getTICItemStack("ingots", 1), arditeArmorItemNameColor,
        true, arditeArmorAddPotionEffect, arditeArmorEffectLevel

    ),
    COBALT(cobaltArmor, "cobalt",
        getTICItemStack("ingots", 0), cobaltArmorItemNameColor,
        true, cobaltArmorAddPotionEffect, cobaltArmorEffectLevel

    ),
    MANYULLYN(manyullynArmor, "manyullyn",
        getTICItemStack("ingots", 2), manyullynArmorItemNameColor,
        true, manyullynArmorAddPotionEffect, manyullynArmorEffectLevel

    ),
    KNIGHT_SLIME(knightSlimeArmor, "knight_slime",
        getTICItemStack("ingots", 3), knightSlimeArmorItemNameColor,
        true, knightSlimeArmorAddPotionEffect, knightSlimeArmorEffectLevel

    ),
    PIG_IRON(pigIronArmor, "pig_iron",
        getTICItemStack("ingots", 4), pigIronArmorItemNameColor,
        true, pigIronArmorAddPotionEffect, pigIronArmorEffectLevel

    ) {
        @Override
        public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            ItemStack head = entity.getItemStackFromSlot(HEAD);
            ItemStack chest = entity.getItemStackFromSlot(CHEST);
            ItemStack legs = entity.getItemStackFromSlot(LEGS);
            ItemStack feet = entity.getItemStackFromSlot(FEET);

            if (enablePigIronArmorEffect) {
                if (head.getItem() == pigIronHelmet && chest.getItem() == pigIronChestplate && legs.getItem() == pigIronLeggings && feet.getItem() == pigIronBoots && entity.getFoodStats().needFood()) {
                    addPotion(entity, this.getAddPotionEffect(), this.getAddPotionEffectAmplifier(), GOOD);
                    head.damageItem(1, entity);
                    chest.damageItem(1, entity);
                    legs.damageItem(1, entity);
                    feet.damageItem(1, entity);
                }
            }
        }
    },
    SLIME(slimeArmor, "slime",
        getItemStack(SLIME_BLOCK), slimeArmorItemNameColor,
        enableFullSlimeArmorEffect, slimeArmorAddPotionEffect, slimeArmorEffectLevel,
        enableSlimeEffect
    ),
    CHICKEN(chickenArmor, "chicken",
        getItemStack(Items.FEATHER), chickenArmorItemNameColor,
        enableFullCoalArmorEffect, chickenArmorAddPotionEffect, chickenArmorEffectLevel,
        enableChickenEffect
    ),;
    private final ArmorMaterial armorMaterial;
    private final String name;
    private final ItemStack repairStack;
    private final String formatting;
    private final boolean enableFullArmorEffect;
    private final String addPotionEffect;
    private final int addPotionEffectAmplifier;
    private final String removePotionEffect;
    private boolean[] areEffectsEnabled;

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, ItemStack repairStack, String textFormattingIn,
                    boolean enableFullArmorEffectIn, String addPotionEffectIn, int addPotionEffectInAmplifierIn, boolean[] areEffectsEnabledIn, String removePotionEffectIn) {
        this.armorMaterial = armorMaterialIn;
        this.name = nameIn;
        this.repairStack = repairStack;
        this.formatting = textFormattingIn;
        this.enableFullArmorEffect = enableFullArmorEffectIn;
        this.addPotionEffect = addPotionEffectIn;
        this.addPotionEffectAmplifier = addPotionEffectInAmplifierIn;
        this.areEffectsEnabled = areEffectsEnabledIn;
        this.removePotionEffect = removePotionEffectIn;
    }

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, ItemStack repairStack, String textFormattingIn,
                    boolean enableFullArmorEffectIn, String addPotionEffectIn, int addPotionEffectInAmplifierIn, boolean[] areEffectsEnabledIn) {
        this(armorMaterialIn, nameIn, repairStack, textFormattingIn, enableFullArmorEffectIn, addPotionEffectIn, addPotionEffectInAmplifierIn, areEffectsEnabledIn, "empty");
    }

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, ItemStack repairStack, String textFormattingIn,
                    boolean enableFullArmorEffectIn, String addPotionEffectIn, int addPotionEffectInAmplifierIn) {
        this(armorMaterialIn, nameIn, repairStack, textFormattingIn, enableFullArmorEffectIn, addPotionEffectIn, addPotionEffectInAmplifierIn, new boolean[4], "empty");
    }

    public ArmorMaterial getArmorMaterial() {
        return this.armorMaterial;
    }

    @NotNull
    @Override
    public String getName() {
        return this.name;
    }

    public ItemStack getRepairStack() {
        return isNotEmpty(repairStack) ? this.repairStack : ItemStack.EMPTY;
    }

    public TextFormatting getFormatting() {
        return getValueByName(this.formatting);
    }

    public boolean enableFullArmorEffect() {
        return this.enableFullArmorEffect;
    }

    public String getAddPotionEffect() {
        return this.addPotionEffect;
    }

    public int getAddPotionEffectAmplifier() {
        return this.addPotionEffectAmplifier;
    }

    public boolean[] getAreEffectsEnabled() {
        return this.areEffectsEnabled;
    }

    public String getRemovePotionEffect() {
        return this.removePotionEffect;
    }

    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (isKeyDown()) {
            if (!this.enableFullArmorEffect()) {
                addToolTipPiece(tooltip, localizePotion(this.getAddPotionEffect()), this.getAddPotionEffectAmplifier());
            }
            addToolTipFull(tooltip, localizePotion(this.getAddPotionEffect()), this.getAddPotionEffectAmplifier());
        } else {
            showInfo(tooltip, keyBindSneak, this.getFormatting());
        }
    }
}
