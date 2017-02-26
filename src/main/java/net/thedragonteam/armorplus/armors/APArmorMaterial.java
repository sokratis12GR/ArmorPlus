/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.armors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.registry.ModPotions;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.minecraft.init.Blocks.*;
import static net.minecraft.inventory.EntityEquipmentSlot.CHEST;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.armors.base.ItemArmorBase.ArmorMaterial;
import static net.thedragonteam.armorplus.armors.base.ItemArmorBase.Companion;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.compressedObsidian;
import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.armorplus.util.PotionUtils.INSTANCE;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.GOOD;
import static net.thedragonteam.armorplus.util.ToolTipUtils.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getTICItemStack;

public enum APArmorMaterial implements IStringSerializable {
    COAL(Companion.getCoalArmor(),
            "coal", getItemStack(Items.COAL).getItem(), getItemStack(COAL_BLOCK).getItem(), getValueByName(coalArmorItemNameColor),
            enableFullCoalArmorEffect, coalArmorAddPotionEffect, coalArmorEffectLevel,
            enableCoalEffect, "empty"
    ),
    LAPIS(Companion.getLapisArmor(),
            "lapis", getItemStack(Items.DYE, 4), getItemStack(LAPIS_BLOCK), getValueByName(lapisArmorItemNameColor),
            enableFullLapisArmorEffect, lapisArmorAddPotionEffect, lapisArmorEffectLevel,
            enableLapisEffect, "empty"
    ),
    REDSTONE(Companion.getRedstoneArmor(),
            "redstone", getItemStack(Items.REDSTONE).getItem(), getItemStack(REDSTONE_BLOCK).getItem(), getValueByName(redstoneArmorItemNameColor),
            enableFullRedstoneArmorEffect, redstoneArmorAddPotionEffect, redstoneArmorEffectLevel,
            enableRedstoneEffect, "empty"
    ),
    EMERALD(Companion.getEmeraldArmor(),
            "emerald", getItemStack(Items.EMERALD).getItem(), getItemStack(EMERALD_BLOCK).getItem(), getValueByName(emeraldArmorItemNameColor),
            enableFullEmeraldArmorEffect, emeraldArmorAddPotionEffect, emeraldArmorEffectLevel,
            enableEmeraldEffect, "empty"
    ),
    OBSIDIAN(Companion.getObsidianArmor(),
            "obsidian", getItemStack(Blocks.OBSIDIAN).getItem(), getItemStack(compressedObsidian).getItem(), getValueByName(obsidianArmorItemNameColor),
            enableFullObsidianArmorEffect, obsidianArmorAddPotionEffect, obsidianArmorEffectLevel,
            enableObsidianEffect, "empty"
    ),
    LAVA(Companion.getLavaArmor(),
            "lava", getItemStack(lavaCrystal), getItemStack(lavaCrystal, 1), getValueByName(lavaArmorItemNameColor),
            enableFullLavaArmorEffect, lavaArmorAddPotionEffect, lavaArmorEffectLevel,
            enableLavaEffect, "empty"
    ) {
        @Override
        public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            if (!enableFullLavaArmorEffect) {
                entity.extinguish();
                entity.setAbsorptionAmount(entity.isInLava() ? 4.0F : 0.0F);
            }
            if (entity.isInWater() && !enableFullLavaArmorEffect && entity.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
                INSTANCE.addPotion(entity, MobEffects.SLOWNESS, 1, BAD);
                itemStack.damageItem(1, entity);
                entity.attackEntityFrom(DamageSource.DROWN, 1F);
            }
        }
    },
    GUARDIAN(Companion.getGuardianArmor(),
            "guardian", getItemStack(materials, 1), getValueByName(guardianArmorItemNameColor),
            enableFullGuardianArmorEffect, guardianArmorAddPotionEffect, guardianArmorEffectLevel,
            enableGuardianEffect, "empty"
    ),
    SUPER_STAR(Companion.getSuperStarArmor(),
            "super_star", getItemStack(materials, 2), getValueByName(superStarArmorItemNameColor),
            enableFullSuperStarArmorEffect, superStarArmorAddPotionEffect, superStarArmorEffectLevel,
            enableSuperStarEffect, superStarArmorRemovePotionEffect
    ),
    ENDER_DRAGON(Companion.getEnderDragonArmor(),
            "ender_dragon", getItemStack(materials, 3), getValueByName(enderDragonArmorItemNameColor),
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
                if (!head.isEmpty() && head.getItem() == enderDragonHelmet && !chest.isEmpty() && chest.getItem() == enderDragonChestplate && !legs.isEmpty() && legs.getItem() == enderDragonLeggings && !feet.isEmpty() && feet.getItem() == enderDragonBoots || entity.capabilities.isCreativeMode || entity.isSpectator())
                    entity.capabilities.allowFlying = true;
                else {
                    entity.capabilities.isFlying = false;
                    entity.capabilities.allowFlying = false;
                }
            }
            if (INSTANCE.getPotion(this.getRemovePotionEffect()) != null && INSTANCE.getPotion(this.getRemovePotionEffect()) != ModPotions.INSTANCE.getEMPTY())
                INSTANCE.removePotion(entity, INSTANCE.getPotion(this.getRemovePotionEffect()));
        }

        @Override
        public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
            final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
            if (isKeyDown()) {
                addToolTipFull(tooltip, "Flight");
            } else
                showInfo(tooltip, keyBindSneak, this.getFormatting());
        }
    },
    ARDITE(Companion.getArditeArmor(), "ardite",
            getTICItemStack("ingots", 1), getValueByName(arditeArmorItemNameColor),
            true, arditeArmorAddPotionEffect, arditeArmorEffectLevel,
            new boolean[4], "empty"
    ),
    COBALT(Companion.getCobaltArmor(), "cobalt",
            getTICItemStack("ingots", 0), getValueByName(cobaltArmorItemNameColor),
            true, cobaltArmorAddPotionEffect, cobaltArmorEffectLevel,
            new boolean[4], "empty"
    ),
    MANYULLYN(Companion.getManyullynArmor(), "manyullyn",
            getTICItemStack("ingots", 2), getValueByName(manyullynArmorItemNameColor),
            true, manyullynArmorAddPotionEffect, manyullynArmorEffectLevel,
            new boolean[4], "empty"
    ),
    KNIGHT_SLIME(Companion.getKnightSlimeArmor(), "knight_slime",
            getTICItemStack("ingots", 3), getValueByName(knightSlimeArmorItemNameColor),
            true, knightSlimeArmorAddPotionEffect, knightSlimeArmorEffectLevel,
            new boolean[4], "empty"
    ),
    PIG_IRON(Companion.getPigIronArmor(), "pig_iron",
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

            if (enablePigIronArmorEffect && head.getItem() == pigIronHelmet && chest.getItem() == pigIronChestplate && legs.getItem() == pigIronLeggings && feet.getItem() == pigIronBoots && entity.getFoodStats().needFood()) {
                INSTANCE.addPotion(entity, this.getAddPotionEffect(), this.getAddPotionEffectAmplifier(), GOOD);
                head.damageItem(1, entity);
                chest.damageItem(1, entity);
                legs.damageItem(1, entity);
                feet.damageItem(1, entity);
            }
        }
    },
    SLIME(Companion.getSlimeArmor(), "slime",
            getItemStack(Items.SLIME_BALL), getItemStack(SLIME_BLOCK), getValueByName(slimeArmorItemNameColor),
            enableFullSlimeArmorEffect, slimeArmorAddPotionEffect, slimeArmorEffectLevel,
            enableSlimeEffect, "empty"
    ),
    CHICKEN(Companion.getChickenArmor(), "chicken",
            Items.FEATHER, getValueByName(chickenArmorItemNameColor),
            enableFullCoalArmorEffect, chickenArmorAddPotionEffect, chickenArmorEffectLevel,
            enableChickenEffect, "empty"
    ),;
    private final ArmorMaterial armorMaterial;
    private final String name;
    private final Item itemEasy;
    private final Item itemExpert;
    private final TextFormatting formatting;
    private final boolean enableFullArmorEffect;
    private final String addPotionEffect;
    private final int addPotionEffectAmplifier;
    private final String removePotionEffect;
    private boolean[] areEffectsEnabled;

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, Item repairEasyIn, Item repairExpertIn, TextFormatting textFormattingIn,
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

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, ItemStack stackEasy, ItemStack stackExpert, TextFormatting textFormattingIn,
                    boolean enableFullArmorEffectIn, String addPotionEffectIn, int addPotionEffectInAmplifierIn, boolean[] areEffectsEnabledIn, String removePotionEffectIn) {
        this(armorMaterialIn, nameIn, stackEasy.getItem(), stackExpert.getItem(), textFormattingIn, enableFullArmorEffectIn, addPotionEffectIn, addPotionEffectInAmplifierIn, areEffectsEnabledIn, removePotionEffectIn);
    }

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, ItemStack both, TextFormatting textFormattingIn,
                    boolean enableFullArmorEffectIn, String addPotionEffectIn, int addPotionEffectInAmplifierIn, boolean[] areEffectsEnabledIn, String removePotionEffectIn) {
        this(armorMaterialIn, nameIn, both.getItem(), both.getItem(), textFormattingIn, enableFullArmorEffectIn, addPotionEffectIn, addPotionEffectInAmplifierIn, areEffectsEnabledIn, removePotionEffectIn);
    }

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, Item both, TextFormatting textFormattingIn,
                    boolean enableFullArmorEffectIn, String addPotionEffectIn, int addPotionEffectInAmplifierIn, boolean[] areEffectsEnabledIn, String removePotionEffectIn) {
        this(armorMaterialIn, nameIn, both, both, textFormattingIn, enableFullArmorEffectIn, addPotionEffectIn, addPotionEffectInAmplifierIn, areEffectsEnabledIn, removePotionEffectIn);
    }

    public ArmorMaterial getArmorMaterial() {
        return this.armorMaterial;
    }

    @NotNull
    @Override
    public String getName() {
        return this.name;
    }

    public Item getItemEasy() {
        return this.itemEasy;
    }

    public Item getItemExpert() {
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
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (isKeyDown()) {
            if (!this.enableFullArmorEffect()) {
                addToolTipPiece(tooltip, INSTANCE.localizePotion(this.getAddPotionEffect()), this.getAddPotionEffectAmplifier());
            } else {
                addToolTipFull(tooltip, INSTANCE.localizePotion(this.getAddPotionEffect()), this.getAddPotionEffectAmplifier());
            }
        } else
            showInfo(tooltip, keyBindSneak, this.getFormatting());
    }
}
