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

import javax.annotation.Nonnull;
import java.util.List;

import static net.minecraft.init.Blocks.*;
import static net.minecraft.inventory.EntityEquipmentSlot.CHEST;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.armors.base.ItemArmorBase.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.compressedObsidian;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.*;
import static net.thedragonteam.armorplus.util.ToolTipUtils.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getTICItemStack;

public enum APArmorMaterial implements IStringSerializable {
    COAL(coalArmor,
            "coal", getItemStack(Items.COAL), getItemStack(COAL_BLOCK), getValueByName(coalArmorItemNameColor),
            enableFullCoalArmorEffect, coalArmorAddPotionEffect, coalArmorEffectLevel,
            new boolean[]{enableCoalBEffect, enableCoalLEffect, enableCoalCEffect, enableCoalHEffect}, "empty"
    ),
    LAPIS(lapisArmor,
            "lapis", getItemStack(Items.DYE, 4), getItemStack(LAPIS_BLOCK), getValueByName(lapisArmorItemNameColor),
            enableFullLapisArmorEffect, lapisArmorAddPotionEffect, lapisArmorEffectLevel,
            new boolean[]{enableLapisBEffect, enableLapisLEffect, enableLapisCEffect, enableLapisHEffect}, "empty"
    ),
    REDSTONE(redstoneArmor,
            "redstone", getItemStack(Items.REDSTONE), getItemStack(REDSTONE_BLOCK), getValueByName(redstoneArmorItemNameColor),
            enableFullRedstoneArmorEffect, redstoneArmorAddPotionEffect, redstoneArmorEffectLevel,
            new boolean[]{enableRedstoneBEffect, enableRedstoneLEffect, enableRedstoneCEffect, enableRedstoneHEffect}, "empty"
    ),
    EMERALD(emeraldArmor,
            "emerald", getItemStack(Items.EMERALD), getItemStack(EMERALD_BLOCK), getValueByName(emeraldArmorItemNameColor),
            enableFullEmeraldArmorEffect, emeraldArmorAddPotionEffect, emeraldArmorEffectLevel,
            new boolean[]{enableEmeraldBEffect, enableEmeraldLEffect, enableEmeraldCEffect, enableEmeraldHEffect}, "empty"
    ),
    OBSIDIAN(obsidianArmor,
            "obsidian", getItemStack(Blocks.OBSIDIAN), getItemStack(compressedObsidian), getValueByName(obsidianArmorItemNameColor),
            enableFullObsidianArmorEffect, obsidianArmorAddPotionEffect, obsidianArmorEffectLevel,
            new boolean[]{enableObsidianBEffect, enableObsidianLEffect, enableObsidianCEffect, enableObsidianHEffect}, "empty"
    ),
    LAVA(lavaArmor,
            "lava", getItemStack(lavaCrystal), getItemStack(lavaCrystal, 1), getValueByName(lavaArmorItemNameColor),
            enableFullLavaArmorEffect, lavaArmorAddPotionEffect, lavaArmorEffectLevel,
            new boolean[]{enableLavaBEffect, enableLavaLEffect, enableLavaCEffect, enableLavaHEffect}, "empty"
    ) {
        @Override
        public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            if (!enableFullLavaArmorEffect) {
                entity.extinguish();
                entity.setAbsorptionAmount(entity.isInLava() ? 4.0F : 0.0F);
            }
            if (entity.isInWater() && !enableFullLavaArmorEffect && entity.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
                addPotion(entity, MobEffects.SLOWNESS, 1, BAD);
                itemStack.damageItem(1, entity);
                entity.attackEntityFrom(DamageSource.DROWN, 1F);
            }
        }
    },
    GUARDIAN(guardianArmor,
            "guardian", getItemStack(materials, 1), getValueByName(guardianArmorItemNameColor),
            true, guardianArmorAddPotionEffect, guardianArmorEffectLevel,
            new boolean[]{true, true, true, true}, "empty"
    ),
    SUPER_STAR(superStarArmor,
            "super_star", getItemStack(materials, 2), getValueByName(superStarArmorItemNameColor),
            enableFullSuperStarArmorEffect, superStarArmorAddPotionEffect, superStarArmorEffectLevel,
            new boolean[]{enableSuperStarBEffect, enableSuperStarLEffect, enableSuperStarCEffect, enableSuperStarHEffect}, superStarArmorRemovePotionEffect
    ),
    ENDER_DRAGON(enderDragonArmor,
            "ender_dragon", getItemStack(materials, 3), getValueByName(enderDragonArmorItemNameColor),
            true, "empty", 0,
            new boolean[]{enableFlightAbility, enableFlightAbility, enableFlightAbility, enableFlightAbility}, enderDragonArmorRemovePotionEffect
    ) {
        @Override
        public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
            ItemStack head = entity.getItemStackFromSlot(HEAD);
            ItemStack chest = entity.getItemStackFromSlot(CHEST);
            ItemStack legs = entity.getItemStackFromSlot(LEGS);
            ItemStack feet = entity.getItemStackFromSlot(FEET);
            if (enableFlightAbility) {
                if (!head.isEmpty() && head.getItem() == enderDragon[0] && !chest.isEmpty() && chest.getItem() == enderDragon[1] && !legs.isEmpty() && legs.getItem() == enderDragon[2] && !feet.isEmpty() && feet.getItem() == enderDragon[3] || entity.capabilities.isCreativeMode || entity.isSpectator())
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
        public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
            final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
            if (isKeyDown()) {
                addToolTipFull(tooltip, "Flight");
            } else
                showInfo(tooltip, keyBindSneak, this.getFormatting());
        }
    },
    ARDITE(arditeArmor, "ardite",
            getTICItemStack("ingots", 1), getValueByName(arditeArmorItemNameColor),
            true, arditeArmorAddPotionEffect, arditeArmorEffectLevel,
            new boolean[]{enableArditeArmorEffect, enableArditeArmorEffect, enableArditeArmorEffect, enableArditeArmorEffect}, "empty"
    ),
    COBALT(cobaltArmor, "cobalt",
            getTICItemStack("ingots", 0), getValueByName(cobaltArmorItemNameColor),
            true, cobaltArmorAddPotionEffect, cobaltArmorEffectLevel,
            new boolean[]{enableCobaltArmorEffect, enableCobaltArmorEffect, enableCobaltArmorEffect, enableCobaltArmorEffect}, "empty"
    ),
    MANYULLYN(manyullynArmor, "manyullyn",
            getTICItemStack("ingots", 2), getValueByName(manyullynArmorItemNameColor),
            true, manyullynArmorAddPotionEffect, manyullynArmorEffectLevel,
            new boolean[]{enableManyullynArmorEffect, enableManyullynArmorEffect, enableManyullynArmorEffect, enableManyullynArmorEffect}, "empty"
    ),
    KNIGHT_SLIME(knightSlimeArmor, "knight_slime",
            getTICItemStack("ingots", 3), getValueByName(knightSlimeArmorItemNameColor),
            true, knightSlimeArmorAddPotionEffect, knightSlimeArmorEffectLevel,
            new boolean[]{enableKnightSlimeArmorEffect, enableKnightSlimeArmorEffect, enableKnightSlimeArmorEffect, enableKnightSlimeArmorEffect}, "empty"
    ),
    PIG_IRON(pigIronArmor, "pig_iron",
            getTICItemStack("ingots", 4), getValueByName(pigIronArmorItemNameColor),
            true, pigIronArmorAddPotionEffect, pigIronArmorEffectLevel,
            new boolean[]{enablePigIronArmorEffect, enablePigIronArmorEffect, enablePigIronArmorEffect, enablePigIronArmorEffect}, "empty"
    ),
    SLIME(slimeArmor, "slime",
            getItemStack(Items.SLIME_BALL), getItemStack(SLIME_BLOCK), getValueByName(slimeArmorItemNameColor),
            true, slimeArmorAddPotionEffect, slimeArmorEffectLevel,
            new boolean[]{enableKnightSlimeArmorEffect, enableKnightSlimeArmorEffect, enableKnightSlimeArmorEffect, enableKnightSlimeArmorEffect}, "empty"
    ),
    CHICKEN(chickenArmor, "chicken",
            getItemStack(Items.FEATHER), getValueByName(chickenArmorItemNameColor),
            true, chickenArmorAddPotionEffect, chickenArmorEffectLevel,
            new boolean[]{true, true, true, true}, "empty"
    ),;
    private final ArmorMaterial armorMaterial;
    private final String name;
    private final Item itemEasy;
    private final Item itemExpert;
    private final TextFormatting formatting;
    private final boolean enableFullArmorEffect;
    private final String addPotionEffect;
    private final int addPotionEffectAmplifier;
    private boolean[] areEffectsEnabled;
    private final String removePotionEffect;

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

    public ArmorMaterial getArmorMaterial() {
        return this.armorMaterial;
    }

    @Nonnull
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
                addToolTipPiece(tooltip, localizePotion(this.getAddPotionEffect()), this.getAddPotionEffectAmplifier());
            } else {
                addToolTipFull(tooltip, localizePotion(this.getAddPotionEffect()), this.getAddPotionEffectAmplifier());
            }
        } else
            showInfo(tooltip, keyBindSneak, this.getFormatting());
    }
}
