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
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.registry.ModPotions;
import net.thedragonteam.armorplus.util.PotionUtils;

import java.util.List;

import static net.minecraft.init.Blocks.*;
import static net.minecraft.inventory.EntityEquipmentSlot.CHEST;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.armors.base.ItemArmorBase.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.compressedObsidian;
import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.GOOD;
import static net.thedragonteam.armorplus.util.PotionUtils.*;
import static net.thedragonteam.armorplus.util.ToolTipUtils.*;
import static net.thedragonteam.armorplus.util.Utils.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getTICItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public enum APArmorMaterial implements IStringSerializable {
    COAL(coalArmor, "coal", enableCoalArmor,
        getItemStack(COAL_BLOCK), coalArmorItemNameColor,
        enableFullCoalArmorEffect, coalArmorAddPotionEffect, coalArmorEffectLevel, enableCoalEffect
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, cobaltHelmet, coalChestplate, coalLeggings, coalBoots);
        }
    },
    LAPIS(lapisArmor, "lapis", enableLapisArmor,
        getItemStack(LAPIS_BLOCK), lapisArmorItemNameColor,
        enableFullLapisArmorEffect, lapisArmorAddPotionEffect, lapisArmorEffectLevel, enableLapisEffect
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots);
        }
    },
    REDSTONE(redstoneArmor, "redstone", enableRedstoneArmor,
        getItemStack(REDSTONE_BLOCK), redstoneArmorItemNameColor,
        enableFullRedstoneArmorEffect, redstoneArmorAddPotionEffect, redstoneArmorEffectLevel, enableRedstoneEffect
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots);

        }
    },
    EMERALD(emeraldArmor, "emerald", enableEmeraldArmor,
        getItemStack(EMERALD_BLOCK), emeraldArmorItemNameColor,
        enableFullEmeraldArmorEffect, emeraldArmorAddPotionEffect, emeraldArmorEffectLevel, enableEmeraldEffect
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots);
        }
    },
    OBSIDIAN(obsidianArmor, "obsidian", enableObsidianArmor,
        getItemStack(compressedObsidian), obsidianArmorItemNameColor,
        enableFullObsidianArmorEffect, obsidianArmorAddPotionEffect, obsidianArmorEffectLevel, enableObsidianEffect
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots);
        }
    },
    LAVA(lavaArmor, "infused_lava", enableLavaArmor,
        getItemStack(lavaCrystal, 1), lavaArmorItemNameColor,
        enableFullLavaArmorEffect, lavaArmorAddPotionEffect, lavaArmorEffectLevel, enableLavaEffect
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            ItemStack head = player.getItemStackFromSlot(HEAD);
            ItemStack chest = player.getItemStackFromSlot(CHEST);
            ItemStack legs = player.getItemStackFromSlot(LEGS);
            ItemStack feet = player.getItemStackFromSlot(FEET);
            if (isArmorEmpty(head, chest, legs, feet)) {
                return;
            }
            if (!enableFullLavaArmorEffect) {
                player.extinguish();
                if (player.isInLava() && !enableFullLavaArmorEffect) {
                    player.setAbsorptionAmount(player.isInLava() ? 4.0F : 0.0F);
                }
                if (enableLavaArmorOnWaterTouchDeBuff) {
                    if (player.isInWater() && player.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
                        addPotion(player, MobEffects.SLOWNESS, 1, BAD);
                        for (ItemStack e : player.getArmorInventoryList()) {
                            e.damageItem(1, player);
                        }
                        player.attackEntityFrom(DamageSource.DROWN, 1F);
                    }
                }
            } else if (head.getItem() == lavaHelmet && chest.getItem() == lavaChestplate && legs.getItem() == lavaLeggings && feet.getItem() == lavaBoots && player.isInWater()) {
                player.extinguish();
                if (player.isInLava()) {
                    player.setAbsorptionAmount(4.0f);
                }
                if (enableLavaArmorOnWaterTouchDeBuff) {
                    if (player.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
                        addPotion(player, MobEffects.SLOWNESS, 120, 1, BAD);
                        for (ItemStack e : player.getArmorInventoryList()) {
                            e.damageItem(1, player);
                        }
                        player.attackEntityFrom(DamageSource.DROWN, 1f);
                    }
                }
            }
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots);
        }
    },
    GUARDIAN(guardianArmor, "guardian", enableGuardianArmor,
        getItemStack(materials, 1), guardianArmorItemNameColor,
        enableFullGuardianArmorEffect, guardianArmorAddPotionEffect, guardianArmorEffectLevel, enableGuardianEffect
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, guardianHelmet, guardianChestplate, guardianLeggings, guardianBoots);
        }
    },
    SUPER_STAR(superStarArmor, "super_star", enableSuperStarArmor,
        getItemStack(materials, 2), superStarArmorItemNameColor,
        enableFullSuperStarArmorEffect, superStarArmorAddPotionEffect, superStarArmorEffectLevel,
        enableSuperStarEffect, superStarArmorRemovePotionEffect
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            ItemStack head = player.getItemStackFromSlot(HEAD);
            ItemStack chest = player.getItemStackFromSlot(CHEST);
            ItemStack legs = player.getItemStackFromSlot(LEGS);
            ItemStack feet = player.getItemStackFromSlot(FEET);
            if (isArmorEmpty(head, chest, legs, feet)) {
                return;
            }
            if (enableFullSuperStarArmorEffect) {
                if (head.getItem() == superStarHelmet && chest.getItem() == superStarChestplate && legs.getItem() == superStarLeggings && feet.getItem() == superStarBoots) {
                    if (player.getActivePotionEffect(getPotion(superStarArmorAddPotionEffect)) == null) {
                        addPotion(player, getPotion(superStarArmorAddPotionEffect), 120, superStarArmorEffectLevel, GOOD);
                    }
                    removePotion(player, getPotion(superStarArmorRemovePotionEffect));
                }
            }
        }
    },
    ENDER_DRAGON(enderDragonArmor, "ender_dragon", enableEnderDragonArmor,
        getItemStack(materials, 3), enderDragonArmorItemNameColor,
        enableFullEnderDragonArmorEffect, "empty", 0,
        enableEnderDragonEffect, enderDragonArmorRemovePotionEffect
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            ItemStack head = player.getItemStackFromSlot(HEAD);
            ItemStack chest = player.getItemStackFromSlot(CHEST);
            ItemStack legs = player.getItemStackFromSlot(LEGS);
            ItemStack feet = player.getItemStackFromSlot(FEET);
            if (isArmorEmpty(head, chest, legs, feet)) {
                return;
            }
            if (enableFlightAbility) {
                if (!head.isEmpty() && head.getItem() == enderDragonHelmet && !chest.isEmpty() && chest.getItem() == enderDragonChestplate &&
                    !legs.isEmpty() && legs.getItem() == enderDragonLeggings && !feet.isEmpty() && feet.getItem() == enderDragonBoots) {
                    player.capabilities.allowFlying = true;
                } else {
                    player.capabilities.isFlying = false;
                    player.capabilities.allowFlying = false;
                }
            }
            if (getPotion(this.getRemovePotionEffect()) != null && getPotion(this.getRemovePotionEffect()) != ModPotions.EMPTY) {
                removePotion(player, getPotion(this.getRemovePotionEffect()));
            }
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
    ARDITE(arditeArmor, "ardite", enableArditeArmor,
        getTICItemStack("ingots", 1), arditeArmorItemNameColor,
        true, arditeArmorAddPotionEffect, arditeArmorEffectLevel
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addFullSetEffects(player, arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots);
        }
    },
    COBALT(cobaltArmor, "cobalt", enableCobaltArmor,
        getTICItemStack("ingots", 0), cobaltArmorItemNameColor,
        true, cobaltArmorAddPotionEffect, cobaltArmorEffectLevel
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addFullSetEffects(player, cobaltHelmet, cobaltChestplate, coalLeggings, cobaltBoots);
        }
    },
    MANYULLYN(manyullynArmor, "manyullyn", enableManyullynArmor,
        getTICItemStack("ingots", 2), manyullynArmorItemNameColor,
        true, manyullynArmorAddPotionEffect, manyullynArmorEffectLevel
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addFullSetEffects(player, manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots);
        }
    },
    KNIGHT_SLIME(knightSlimeArmor, "knight_slime", enableKnightSlimeArmor,
        getTICItemStack("ingots", 3), knightSlimeArmorItemNameColor,
        true, knightSlimeArmorAddPotionEffect, knightSlimeArmorEffectLevel
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addFullSetEffects(player, knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots);
        }
    },
    PIG_IRON(pigIronArmor, "pig_iron", enablePigIronArmor,
        getTICItemStack("ingots", 4), pigIronArmorItemNameColor,
        true, pigIronArmorAddPotionEffect, pigIronArmorEffectLevel
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            ItemStack head = player.getItemStackFromSlot(HEAD);
            ItemStack chest = player.getItemStackFromSlot(CHEST);
            ItemStack legs = player.getItemStackFromSlot(LEGS);
            ItemStack feet = player.getItemStackFromSlot(FEET);
            if (isArmorEmpty(head, chest, legs, feet)) {
                return;
            }
            if (enablePigIronArmorEffect) {
                if (head.getItem() == pigIronHelmet && chest.getItem() == pigIronChestplate && legs.getItem() == pigIronLeggings && feet.getItem() == pigIronBoots) {
                    addPotion(player, this.getAddPotionEffect(), this.getAddPotionEffectAmplifier(), GOOD);
                    for (ItemStack e : player.getArmorInventoryList()) {
                        e.damageItem(1, player);
                    }
                }
            }
        }
    },
    SLIME(slimeArmor, "slime", enableSlimeArmor,
        getItemStack(SLIME_BLOCK), slimeArmorItemNameColor,
        enableFullSlimeArmorEffect, slimeArmorAddPotionEffect, slimeArmorEffectLevel, enableSlimeEffect
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots);
        }
    },
    CHICKEN(chickenArmor, "chicken", enableChickenArmor,
        getItemStack(Items.FEATHER), chickenArmorItemNameColor,
        enableFullCoalArmorEffect, chickenArmorAddPotionEffect, chickenArmorEffectLevel, enableChickenEffect
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, chickenHelmet, chickenChestplate, chickenLeggings, chickenBoots);
        }
    },;
    private final ArmorMaterial armorMaterial;
    private final String name;
    private final boolean enableArmor;
    private final ItemStack repairStack;
    private final String formatting;
    private final boolean enableFullArmorEffect;
    private final String addPotionEffect;
    private final int addPotionEffectAmplifier;
    private final String removePotionEffect;
    private boolean[] areEffectsEnabled;

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, boolean enableArmor, ItemStack repairStack, String textFormattingIn,
                    boolean enableFullArmorEffectIn, String addPotionEffectIn, int addPotionEffectInAmplifierIn, boolean[] areEffectsEnabledIn, String removePotionEffectIn) {
        this.armorMaterial = armorMaterialIn;
        this.name = nameIn;
        this.enableArmor = enableArmor;
        this.repairStack = repairStack;
        this.formatting = textFormattingIn;
        this.enableFullArmorEffect = enableFullArmorEffectIn;
        this.addPotionEffect = addPotionEffectIn;
        this.addPotionEffectAmplifier = addPotionEffectInAmplifierIn;
        this.areEffectsEnabled = areEffectsEnabledIn;
        this.removePotionEffect = removePotionEffectIn;
    }

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, boolean enableArmor, ItemStack repairStack, String textFormattingIn,
                    boolean enableFullArmorEffectIn, String addPotionEffectIn, int addPotionEffectInAmplifierIn, boolean[] areEffectsEnabledIn) {
        this(armorMaterialIn, nameIn, enableArmor, repairStack, textFormattingIn, enableFullArmorEffectIn, addPotionEffectIn, addPotionEffectInAmplifierIn, areEffectsEnabledIn, "empty");
    }

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, boolean enableArmor, ItemStack repairStack, String textFormattingIn,
                    boolean enableFullArmorEffectIn, String addPotionEffectIn, int addPotionEffectInAmplifierIn) {
        this(armorMaterialIn, nameIn, enableArmor, repairStack, textFormattingIn, enableFullArmorEffectIn, addPotionEffectIn, addPotionEffectInAmplifierIn, new boolean[4], "empty");
    }

    public ArmorMaterial getArmorMaterial() {
        return this.armorMaterial;
    }


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

    public abstract void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot);

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (isKeyDown()) {
            if (!this.enableFullArmorEffect()) {
                addToolTipPiece(tooltip, localizePotion(this.getAddPotionEffect()), this.getAddPotionEffectAmplifier());
            } else {
                addToolTipFull(tooltip, localizePotion(this.getAddPotionEffect()), this.getAddPotionEffectAmplifier());
            }
        } else {
            showInfo(tooltip, keyBindSneak, this.getFormatting());
        }
    }

    public boolean isArmorEnabled() {
        return enableArmor;
    }

    public void addPerPieceEffects(EntityPlayer player, EntityEquipmentSlot slot) {
        switch (slot) {
            case FEET:
                addEffects(player, 0);
                break;
            case LEGS:
                addEffects(player, 1);
                break;
            case CHEST:
                addEffects(player, 2);
                break;
            case HEAD:
                addEffects(player, 3);
                break;
        }
    }

    public void addEffects(EntityPlayer player, int index) {
        if (!enableFullArmorEffect() && this.getAreEffectsEnabled()[index]) {
            if (player.getActivePotionEffect(getPotion(getAddPotionEffect())) == null || getPotion(getAddPotionEffect()) == MobEffects.NIGHT_VISION) {
                addPotion(player, getPotion(getAddPotionEffect()), getAddPotionEffectAmplifier(), GOOD);
            }
            if (isNotNull(getPotion(this.getRemovePotionEffect()))) {
                PotionUtils.removePotion(player, getPotion(this.getRemovePotionEffect()));
            }
        }
    }

    public void addFullSetEffects(EntityPlayer player, Item helmet, Item chestplate, Item leggings, Item boots) {
        if (enableFullArmorEffect()) {
            ItemStack head = player.getItemStackFromSlot(HEAD);
            ItemStack chest = player.getItemStackFromSlot(CHEST);
            ItemStack legs = player.getItemStackFromSlot(LEGS);
            ItemStack feet = player.getItemStackFromSlot(FEET);
            if (isNull(helmet) || isNull(chestplate) || isNull(leggings) || isNull(boots) || isArmorEmpty(head, chest, legs, feet)) {
                return;
            }
            if (areSame(head, helmet) && areSame(chest, chestplate) && areSame(legs, leggings) && areSame(feet, boots)) {
                if (player.getActivePotionEffect(getPotion(getAddPotionEffect())) == null || getPotion(getAddPotionEffect()) == MobEffects.NIGHT_VISION) {
                    addPotion(player, getPotion(getAddPotionEffect()), getAddPotionEffectAmplifier(), GOOD);
                }
            }
        }
    }
}