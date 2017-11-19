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
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.registry.ModPotions;
import net.thedragonteam.armorplus.util.PotionUtils;
import net.thedragonteam.thedragonlib.util.LogHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.minecraft.init.Blocks.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.armors.base.ItemArmorBase.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.compressedObsidian;
import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.armorplus.util.ArmorUtils.getArmorItems;
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
        enableFullCoalArmorEffect, coalArmorAddPotionEffect, coalArmorEffectLevel,
        enableCoalEffect
    ),
    LAPIS(lapisArmor, "lapis", enableLapisArmor,
        getItemStack(LAPIS_BLOCK), lapisArmorItemNameColor,
        enableFullLapisArmorEffect, lapisArmorAddPotionEffect, lapisArmorEffectLevel,
        enableLapisEffect
    ),
    REDSTONE(redstoneArmor, "redstone", enableRedstoneArmor,
        getItemStack(REDSTONE_BLOCK), redstoneArmorItemNameColor,
        enableFullRedstoneArmorEffect, redstoneArmorAddPotionEffect, redstoneArmorEffectLevel,
        enableRedstoneEffect
    ),
    EMERALD(emeraldArmor, "emerald", enableEmeraldArmor,
        getItemStack(EMERALD_BLOCK), emeraldArmorItemNameColor,
        enableFullEmeraldArmorEffect, emeraldArmorAddPotionEffect, emeraldArmorEffectLevel,
        enableEmeraldEffect
    ),
    OBSIDIAN(obsidianArmor, "obsidian", enableObsidianArmor,
        getItemStack(compressedObsidian), obsidianArmorItemNameColor,
        enableFullObsidianArmorEffect, obsidianArmorAddPotionEffect, obsidianArmorEffectLevel,
        enableObsidianEffect
    ),
    LAVA(lavaArmor, "infused_lava", enableLavaArmor,
        getItemStack(lavaCrystal, 1), lavaArmorItemNameColor,
        enableFullLavaArmorEffect, lavaArmorAddPotionEffect, lavaArmorEffectLevel,
        enableLavaEffect
    ) {
        @Override
        public void onArmorTick(EntityPlayer entity, EntityEquipmentSlot slot) {
            ItemStack head = getArmorItems(entity)[0];
            ItemStack chest = getArmorItems(entity)[1];
            ItemStack legs = getArmorItems(entity)[2];
            ItemStack feet = getArmorItems(entity)[3];
            if (!this.enableFullArmorEffect()) {
                if (this.isArmorEnabled()) {
                    Potion addPotion = getPotion(this.getAddPotionEffect());
                    Item helmet = getItem(this.getName() + "_helmet");
                    Item chestplate = getItem(this.getName() + "_chestplate");
                    Item leggings = getItem(this.getName() + "_leggings");
                    Item boots = getItem(this.getName() + "_boots");
                    if (isNull(helmet) || isNull(chestplate) || isNull(leggings) || isNull(boots) || isArmorEmpty(head, chest, legs, feet)) {
                        return;
                    }
                    LogHelper.info("Helmet Item: " + helmet.getUnlocalizedName());
                    LogHelper.info("Chestplate Item: " + chestplate.getUnlocalizedName());
                    LogHelper.info("Leggings Item: " + leggings.getUnlocalizedName());
                    LogHelper.info("Boots Item: " + boots.getUnlocalizedName());
                    if (areSame(head, helmet) && areSame(chest, chestplate) && areSame(legs, leggings) && areSame(feet, boots)) {
                        if (entity.getActivePotionEffect(addPotion) == null || addPotion == MobEffects.NIGHT_VISION) {
                            addPotion(entity, addPotion, this.getAddPotionEffectAmplifier(), GOOD);
                        }
                    }
                }                entity.extinguish();
                if (entity.isInLava()) {
                    entity.setAbsorptionAmount(entity.isInLava() ? 4.0F : 0.0F);
                }
                if (enableLavaArmorOnWaterTouchDeBuff) {
                    if (entity.isInWater() && entity.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
                        addPotion(entity, MobEffects.SLOWNESS, 1, BAD);
                        for (ItemStack e : entity.getArmorInventoryList()) {
                            e.damageItem(1, entity);
                        }
                        entity.attackEntityFrom(DamageSource.DROWN, 1F);
                    }

                }
            } else {
                addArmorEffects(entity);
                if (head.getItem() == lavaHelmet && chest.getItem() == lavaChestplate && legs.getItem() == lavaLeggings && feet.getItem() == lavaBoots) {
                    if (entity.isInWater()) {
                        entity.extinguish();
                        if (entity.isInLava()) {
                            entity.setAbsorptionAmount(entity.isInLava() ? 4.0F : 0.0F);
                        }
                        if (enableLavaArmorOnWaterTouchDeBuff) {
                            if (entity.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
                                addPotion(entity, MobEffects.SLOWNESS, 120, 1, BAD);
                                head.damageItem(1, entity);
                                chest.damageItem(1, entity);
                                legs.damageItem(1, entity);
                                feet.damageItem(1, entity);
                                entity.attackEntityFrom(DamageSource.DROWN, 1f);
                            }
                        }
                    }
                }
            }
        }
    },
    GUARDIAN(guardianArmor, "guardian", enableGuardianArmor,
        getItemStack(materials, 1), guardianArmorItemNameColor,
        enableFullGuardianArmorEffect, guardianArmorAddPotionEffect, guardianArmorEffectLevel,
        enableGuardianEffect
    ),
    SUPER_STAR(superStarArmor, "super_star", enableSuperStarArmor,
        getItemStack(materials, 2), superStarArmorItemNameColor,
        enableFullSuperStarArmorEffect, superStarArmorAddPotionEffect, superStarArmorEffectLevel,
        enableSuperStarEffect, superStarArmorRemovePotionEffect
    ) {
        @Override
        public void onArmorTick(EntityPlayer entity, EntityEquipmentSlot slot) {
            ItemStack head = getArmorItems(entity)[0];
            ItemStack chest = getArmorItems(entity)[1];
            ItemStack legs = getArmorItems(entity)[2];
            ItemStack feet = getArmorItems(entity)[3];
            if (enableFullSuperStarArmorEffect) {
                if (head.getItem() == superStarHelmet && chest.getItem() == superStarChestplate && legs.getItem() == superStarLeggings && feet.getItem() == superStarBoots) {
                    if (entity.getActivePotionEffect(getPotion(superStarArmorAddPotionEffect)) == null) {
                        addPotion(entity, getPotion(superStarArmorAddPotionEffect), 120, superStarArmorEffectLevel, GOOD);
                    }
                    removePotion(entity, getPotion(superStarArmorRemovePotionEffect));
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
        public void onArmorTick(EntityPlayer entity, EntityEquipmentSlot slot) {
            ItemStack head = getArmorItems(entity)[0];
            ItemStack chest = getArmorItems(entity)[1];
            ItemStack legs = getArmorItems(entity)[2];
            ItemStack feet = getArmorItems(entity)[3];
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
    ARDITE(arditeArmor, "ardite", enableArditeArmor,
        getTICItemStack("ingots", 1), arditeArmorItemNameColor,
        true, arditeArmorAddPotionEffect, arditeArmorEffectLevel

    ),
    COBALT(cobaltArmor, "cobalt", enableCobaltArmor,
        getTICItemStack("ingots", 0), cobaltArmorItemNameColor,
        true, cobaltArmorAddPotionEffect, cobaltArmorEffectLevel

    ),
    MANYULLYN(manyullynArmor, "manyullyn", enableManyullynArmor,
        getTICItemStack("ingots", 2), manyullynArmorItemNameColor,
        true, manyullynArmorAddPotionEffect, manyullynArmorEffectLevel

    ),
    KNIGHT_SLIME(knightSlimeArmor, "knight_slime", enableKnightSlimeArmor,
        getTICItemStack("ingots", 3), knightSlimeArmorItemNameColor,
        true, knightSlimeArmorAddPotionEffect, knightSlimeArmorEffectLevel

    ),
    PIG_IRON(pigIronArmor, "pig_iron", enablePigIronArmor,
        getTICItemStack("ingots", 4), pigIronArmorItemNameColor,
        true, pigIronArmorAddPotionEffect, pigIronArmorEffectLevel

    ) {
        @Override
        public void onArmorTick(EntityPlayer entity, EntityEquipmentSlot slot) {
            ItemStack head = getArmorItems(entity)[0];
            ItemStack chest = getArmorItems(entity)[1];
            ItemStack legs = getArmorItems(entity)[2];
            ItemStack feet = getArmorItems(entity)[3];
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
    SLIME(slimeArmor, "slime", enableSlimeArmor,
        getItemStack(SLIME_BLOCK), slimeArmorItemNameColor,
        enableFullSlimeArmorEffect, slimeArmorAddPotionEffect, slimeArmorEffectLevel,
        enableSlimeEffect
    ),
    CHICKEN(chickenArmor, "chicken", enableChickenArmor,
        getItemStack(Items.FEATHER), chickenArmorItemNameColor,
        enableFullCoalArmorEffect, chickenArmorAddPotionEffect, chickenArmorEffectLevel,
        enableChickenEffect
    ),;
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

    public void onArmorTick(EntityPlayer entity, EntityEquipmentSlot slot) {
        if (!this.enableFullArmorEffect()) {
            addArmorEffects(entity, slot);
        } else {
            addArmorEffects(entity);
        }
    }

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

    private static Item getItem(String name) {
        return Item.getByNameOrId("armorplus:" + name);
    }

    public void addArmorEffects(EntityPlayer player) {
        if (this.isArmorEnabled()) {
            Potion addPotion = getPotion(this.getAddPotionEffect());
            ItemStack head = getArmorItems(player)[0];
            ItemStack chest = getArmorItems(player)[1];
            ItemStack legs = getArmorItems(player)[2];
            ItemStack feet = getArmorItems(player)[3];
            Item helmet = getItem(this.getName() + "_helmet");
            Item chestplate = getItem(this.getName() + "_chestplate");
            Item leggings = getItem(this.getName() + "_leggings");
            Item boots = getItem(this.getName() + "_boots");
            if (isNull(helmet) || isNull(chestplate) || isNull(leggings) || isNull(boots) || isArmorEmpty(head, chest, legs, feet)) {
                return;
            }
            if (areSame(head, helmet) && areSame(chest, chestplate) && areSame(legs, leggings) && areSame(feet, boots)) {
                if (player.getActivePotionEffect(addPotion) == null || addPotion == MobEffects.NIGHT_VISION) {
                    addPotion(player, addPotion, this.getAddPotionEffectAmplifier(), GOOD);
                }
            }
        }
    }

    public void addArmorEffects(EntityPlayer entity, EntityEquipmentSlot slot) {
        if (isArmorEnabled()) {
            if (slot == EntityEquipmentSlot.HEAD) {
                this.addEffects(entity, 3);
            } else if (slot == EntityEquipmentSlot.CHEST) {
                this.addEffects(entity, 2);
            } else if (slot == EntityEquipmentSlot.LEGS) {
                this.addEffects(entity, 1);
            } else if (slot == EntityEquipmentSlot.FEET) {
                this.addEffects(entity, 0);
            }
        }
    }

    private void addEffects(EntityPlayer entity, int slot) {
        Potion addPotion = getPotion(this.getAddPotionEffect());
        Potion removePotion = getPotion(this.getRemovePotionEffect());
        if (removePotion == ModPotions.EMPTY || addPotion == ModPotions.EMPTY) return;
        if (this.getAreEffectsEnabled()[slot]) {
            if (entity.getActivePotionEffect(addPotion) == null || addPotion == MobEffects.NIGHT_VISION) {
                PotionUtils.addPotion(entity, addPotion, this.getAddPotionEffectAmplifier(), GOOD);
            }
            if (isNotNull(removePotion)) {
                PotionUtils.removePotion(entity, removePotion);
            }
        }
    }
}