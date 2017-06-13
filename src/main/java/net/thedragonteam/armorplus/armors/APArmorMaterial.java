/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.armors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.registry.ModPotions;

import java.util.List;

import static net.minecraft.init.Blocks.*;
import static net.minecraft.inventory.EntityEquipmentSlot.CHEST;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.item.ItemStack.EMPTY;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.armors.base.ItemArmorBase.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.GOOD;
import static net.thedragonteam.armorplus.util.PotionUtils.*;
import static net.thedragonteam.armorplus.util.ToolTipUtils.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getTICItemStack;

public enum APArmorMaterial implements IStringSerializable {
    COAL(coalArmor,
            "coal", getItemStack(Items.COAL), getItemStack(COAL_BLOCK), getValueByName(coalArmorItemNameColor),
            enableFullCoalArmorEffect, coalArmorAddPotionEffect, coalArmorEffectLevel,
            enableCoalEffect, "empty"
    ),
    LAPIS(lapisArmor,
            "lapis", getItemStack(Items.DYE, 4), getItemStack(LAPIS_BLOCK), getValueByName(lapisArmorItemNameColor),
            enableFullLapisArmorEffect, lapisArmorAddPotionEffect, lapisArmorEffectLevel,
            enableLapisEffect, "empty"
    ),
    REDSTONE(redstoneArmor,
            "redstone", getItemStack(Items.REDSTONE), getItemStack(REDSTONE_BLOCK), getValueByName(redstoneArmorItemNameColor),
            enableFullRedstoneArmorEffect, redstoneArmorAddPotionEffect, redstoneArmorEffectLevel,
            enableRedstoneEffect, "empty"
    ),
    EMERALD(emeraldArmor,
            "emerald", getItemStack(Items.EMERALD), getItemStack(EMERALD_BLOCK), getValueByName(emeraldArmorItemNameColor),
            enableFullEmeraldArmorEffect, emeraldArmorAddPotionEffect, emeraldArmorEffectLevel,
            enableEmeraldEffect, "empty"
    ),
    OBSIDIAN(obsidianArmor,
            "obsidian", getItemStack(Blocks.OBSIDIAN), EMPTY /*getItemStack(ModBlocks.compressedObsidian)*/, getValueByName(obsidianArmorItemNameColor),
            enableFullObsidianArmorEffect, obsidianArmorAddPotionEffect, obsidianArmorEffectLevel,
            enableObsidianEffect, "empty"
    ),
    LAVA(lavaArmor,
            "infused_lava", EMPTY /*getItemStack(lavaCrystal)*/, EMPTY /* getItemStack(lavaCrystal, 1)*/, getValueByName(lavaArmorItemNameColor),
            enableFullLavaArmorEffect, lavaArmorAddPotionEffect, lavaArmorEffectLevel,
            enableLavaEffect, "empty"
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
            "guardian", EMPTY /* getItemStack(materials, 1)*/, getValueByName(guardianArmorItemNameColor),
            enableFullGuardianArmorEffect, guardianArmorAddPotionEffect, guardianArmorEffectLevel,
            enableGuardianEffect, "empty"
    ),
    SUPER_STAR(superStarArmor,
            "super_star", EMPTY/* getItemStack(materials, 2)*/, getValueByName(superStarArmorItemNameColor),
            enableFullSuperStarArmorEffect, superStarArmorAddPotionEffect, superStarArmorEffectLevel,
            enableSuperStarEffect, superStarArmorRemovePotionEffect
    ),
    ENDER_DRAGON(enderDragonArmor,
            "ender_dragon", EMPTY/* getItemStack(materials, 3)*/, getValueByName(enderDragonArmorItemNameColor),
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
                if (head.isEmpty() || chest.isEmpty() || legs.isEmpty() || feet.isEmpty()) return;
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
            getTICItemStack("ingots", 1), getValueByName(arditeArmorItemNameColor),
            true, arditeArmorAddPotionEffect, arditeArmorEffectLevel,
            new boolean[4], "empty"
    ),
    COBALT(cobaltArmor, "cobalt",
            getTICItemStack("ingots", 0), getValueByName(cobaltArmorItemNameColor),
            true, cobaltArmorAddPotionEffect, cobaltArmorEffectLevel,
            new boolean[4], "empty"
    ),
    MANYULLYN(manyullynArmor, "manyullyn",
            getTICItemStack("ingots", 2), getValueByName(manyullynArmorItemNameColor),
            true, manyullynArmorAddPotionEffect, manyullynArmorEffectLevel,
            new boolean[4], "empty"
    ),
    KNIGHT_SLIME(knightSlimeArmor, "knight_slime",
            getTICItemStack("ingots", 3), getValueByName(knightSlimeArmorItemNameColor),
            true, knightSlimeArmorAddPotionEffect, knightSlimeArmorEffectLevel,
            new boolean[4], "empty"
    ),
    PIG_IRON(pigIronArmor, "pig_iron",
            getTICItemStack("ingots", 4), getValueByName(pigIronArmorItemNameColor),
            true, pigIronArmorAddPotionEffect, pigIronArmorEffectLevel,
            new boolean[4], "empty"
    ) {
        @Override
        public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            ItemStack head = entity.getItemStackFromSlot(HEAD);
            ItemStack chest = entity.getItemStackFromSlot(CHEST);
            ItemStack legs = entity.getItemStackFromSlot(LEGS);
            ItemStack feet = entity.getItemStackFromSlot(FEET);

            if (enablePigIronArmorEffect) {
                if (head.isEmpty() || chest.isEmpty() || legs.isEmpty() || feet.isEmpty()) return;
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
            getItemStack(Items.SLIME_BALL), getItemStack(SLIME_BLOCK), getValueByName(slimeArmorItemNameColor),
            enableFullSlimeArmorEffect, slimeArmorAddPotionEffect, slimeArmorEffectLevel,
            enableSlimeEffect, "empty"
    ),
    CHICKEN(chickenArmor, "chicken",
            getItemStack(Items.FEATHER), getValueByName(chickenArmorItemNameColor),
            enableFullCoalArmorEffect, chickenArmorAddPotionEffect, chickenArmorEffectLevel,
            enableChickenEffect, "empty"
    ),;
    private final ArmorMaterial armorMaterial;
    private final String name;
    private final ItemStack itemEasy;
    private final ItemStack itemExpert;
    private final TextFormatting formatting;
    private final boolean enableFullArmorEffect;
    private final String addPotionEffect;
    private final int addPotionEffectAmplifier;
    private final String removePotionEffect;
    private boolean[] areEffectsEnabled;

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, ItemStack repairEasyIn, ItemStack repairExpertIn, TextFormatting textFormattingIn,
                    boolean enableFullArmorEffectIn, String addPotionEffectIn, int addPotionEffectInAmplifierIn, boolean[] areEffectsEnabledIn, String removePotionEffectIn) {
        this.armorMaterial = armorMaterialIn;
        this.name = nameIn;
        this.itemEasy = repairEasyIn;
        this.itemExpert = repairExpertIn;
        this.formatting = textFormattingIn;
        this.enableFullArmorEffect = enableFullArmorEffectIn;
        this.addPotionEffect = addPotionEffectIn;
        this.addPotionEffectAmplifier = addPotionEffectInAmplifierIn;
        this.areEffectsEnabled = areEffectsEnabledIn;
        this.removePotionEffect = removePotionEffectIn;
    }

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, ItemStack both, TextFormatting textFormattingIn,
                    boolean enableFullArmorEffectIn, String addPotionEffectIn, int addPotionEffectInAmplifierIn, boolean[] areEffectsEnabledIn, String removePotionEffectIn) {
        this(armorMaterialIn, nameIn, both, both, textFormattingIn, enableFullArmorEffectIn, addPotionEffectIn, addPotionEffectInAmplifierIn, areEffectsEnabledIn, removePotionEffectIn);
    }

    public ArmorMaterial getArmorMaterial() {
        return this.armorMaterial;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public ItemStack getItemEasy() {
        return this.itemEasy;
    }

    public ItemStack getItemExpert() {
        return this.itemExpert;
    }

    public TextFormatting getFormatting() {
        return this.formatting;
    }

    public boolean enableFullArmorEffect() {
        return this.enableFullArmorEffect;
    }

    public String getAddPotionEffect() {
        return this.addPotionEffect;
    }

    public int getAddPotionEffectAmplifier() {
        return addPotionEffectAmplifier;
    }

    public boolean[] getAreEffectsEnabled() {
        return areEffectsEnabled;
    }

    public String getRemovePotionEffect() {
        return removePotionEffect;
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
