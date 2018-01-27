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
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.api.properties.IEffectHolder;
import net.thedragonteam.armorplus.api.properties.IRemovable;
import net.thedragonteam.armorplus.api.properties.IRepairable;
import net.thedragonteam.armorplus.registry.ModPotions;
import net.thedragonteam.armorplus.util.PotionUtils;
import net.thedragonteam.armorplus.util.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static net.minecraft.init.Blocks.*;
import static net.minecraft.inventory.EntityEquipmentSlot.CHEST;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.ModConfig.Misc.enableFlightAbility;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.*;
import static net.thedragonteam.armorplus.armors.base.ItemArmorBase.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.compressedObsidian;
import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.GOOD;
import static net.thedragonteam.armorplus.util.PotionUtils.addPotion;
import static net.thedragonteam.armorplus.util.PotionUtils.removePotion;
import static net.thedragonteam.armorplus.util.ToolTipUtils.*;
import static net.thedragonteam.armorplus.util.Utils.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getTICItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public enum APArmorMaterial implements IEffectHolder, IRepairable, IRemovable {
    COAL(coalArmor, "coal", global_registry.enableCoalArmor, COAL_BLOCK, coal.armor.itemNameColor,
        coal.armor.enableSetEffects, coal.armor.addPotionEffects, coal.armor.effectLevels, coal.armor.enablePieceEffects,
        coal.armor.removePotionEffects
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, cobaltHelmet, coalChestplate, coalLeggings, coalBoots);
        }
    },
    LAPIS(lapisArmor, "lapis", global_registry.enableLapisArmor, LAPIS_BLOCK, lapis.armor.itemNameColor,
        lapis.armor.enableSetEffects, lapis.armor.addPotionEffects, lapis.armor.effectLevels, lapis.armor.enablePieceEffects,
        lapis.armor.removePotionEffects
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots);
        }
    },
    REDSTONE(redstoneArmor, "redstone", global_registry.enableRedstoneArmor, REDSTONE_BLOCK, redstone.armor.itemNameColor,
        redstone.armor.enableSetEffects, redstone.armor.addPotionEffects, redstone.armor.effectLevels, redstone.armor.enablePieceEffects,
        redstone.armor.removePotionEffects
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots);
        }
    },
    EMERALD(emeraldArmor, "emerald", global_registry.enableEmeraldArmor, EMERALD_BLOCK, emerald.armor.itemNameColor,
        emerald.armor.enableSetEffects, emerald.armor.addPotionEffects, emerald.armor.effectLevels, emerald.armor.enablePieceEffects,
        emerald.armor.removePotionEffects
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots);
        }
    },
    OBSIDIAN(obsidianArmor, "obsidian", global_registry.enableObsidianArmor, compressedObsidian, obsidian.armor.itemNameColor,
        obsidian.armor.enableSetEffects, obsidian.armor.addPotionEffects, obsidian.armor.effectLevels, obsidian.armor.enablePieceEffects,
        obsidian.armor.removePotionEffects
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots);
        }
    },
    LAVA(lavaArmor, "infused_lava", global_registry.enableLavaArmor, getItemStack(lavaCrystal, 1), lava.armor.itemNameColor,
        lava.armor.enableSetEffects, lava.armor.addPotionEffects, lava.armor.effectLevels, lava.armor.enablePieceEffects,
        lava.armor.removePotionEffects
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
            if (!lava.armor.enableSetEffects) {
                player.extinguish();
                if (player.isInLava() && !lava.armor.enableSetEffects) {
                    player.setAbsorptionAmount(player.isInLava() ? 4.0F : 0.0F);
                }
                if (lava.armor.enableOnWaterTouchDeBuff && player.isInWater() && player.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
                    addPotion(player, MobEffects.SLOWNESS, 1, BAD);
                    for (ItemStack e : player.getArmorInventoryList()) {
                        e.damageItem(1, player);
                    }
                    player.attackEntityFrom(DamageSource.DROWN, 1F);
                }
            } else if (head.getItem() == lavaHelmet && chest.getItem() == lavaChestplate && legs.getItem() == lavaLeggings && feet.getItem() == lavaBoots && player.isInWater()) {
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
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots);
        }
    },
    GUARDIAN(guardianArmor, "guardian", global_registry.enableGuardianArmor, getItemStack(materials, 1), guardian.armor.itemNameColor,
        guardian.armor.enableSetEffects, guardian.armor.addPotionEffects, guardian.armor.effectLevels, guardian.armor.enablePieceEffects,
        guardian.armor.removePotionEffects
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, guardianHelmet, guardianChestplate, guardianLeggings, guardianBoots);
        }
    },
    SUPER_STAR(superStarArmor, "super_star", global_registry.enableSuperStarArmor, getItemStack(materials, 2), super_star.armor.itemNameColor,
        super_star.armor.enableSetEffects, super_star.armor.addPotionEffects, super_star.armor.effectLevels, super_star.armor.enablePieceEffects,
        super_star.armor.removePotionEffects
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
            if (super_star.armor.enableSetEffects && head.getItem() == superStarHelmet && chest.getItem() == superStarChestplate && legs.getItem() == superStarLeggings && feet.getItem() == superStarBoots) {
                List<Potion> potions = Arrays.stream(super_star.armor.addPotionEffects).map(PotionUtils::getPotion).collect(Collectors.toList());
                IntStream.range(0, potions.size()).forEach(i -> {
                    Potion potionEffect = potions.get(i);
                    if (player.getActivePotionEffect(potionEffect) == null) {
                        addPotion(player, potionEffect, 120, super_star.armor.effectLevels[i], GOOD);
                    }
                });
            }
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, superStarHelmet, superStarChestplate, superStarLeggings, superStarBoots);
        }
    },
    ENDER_DRAGON(enderDragonArmor, "ender_dragon", global_registry.enableEnderDragonArmor, getItemStack(materials, 3), ender_dragon.armor.itemNameColor,
        ender_dragon.armor.enableSetEffects, ender_dragon.armor.addPotionEffects, ender_dragon.armor.effectLevels, ender_dragon.armor.enablePieceEffects,
        ender_dragon.armor.removePotionEffects
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
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, enderDragonHelmet, enderDragonChestplate, enderDragonLeggings, enderDragonBoots);
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
    ARDITE(arditeArmor, "ardite", global_registry.enableArditeArmor, getTICItemStack("ingots", 1), ardite.armor.itemNameColor,
        ardite.armor.enableSetEffects, ardite.armor.addPotionEffects, ardite.armor.effectLevels, ardite.armor.enablePieceEffects,
        ardite.armor.removePotionEffects
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots);
        }
    },
    COBALT(cobaltArmor, "cobalt", global_registry.enableCobaltArmor, getTICItemStack("ingots", 0), cobalt.armor.itemNameColor,
        cobalt.armor.enableSetEffects, cobalt.armor.addPotionEffects, cobalt.armor.effectLevels, cobalt.armor.enablePieceEffects,
        cobalt.armor.removePotionEffects
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, cobaltHelmet, cobaltChestplate, coalLeggings, cobaltBoots);
        }
    },
    MANYULLYN(manyullynArmor, "manyullyn", global_registry.enableManyullynArmor, getTICItemStack("ingots", 2), manyullyn.armor.itemNameColor,
        manyullyn.armor.enableSetEffects, manyullyn.armor.addPotionEffects, manyullyn.armor.effectLevels, manyullyn.armor.enablePieceEffects,
        manyullyn.armor.removePotionEffects
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots);
        }
    },
    KNIGHT_SLIME(knightSlimeArmor, "knight_slime", global_registry.enableKnightSlimeArmor, getTICItemStack("ingots", 3), knight_slime.armor.itemNameColor,
        knight_slime.armor.enableSetEffects, knight_slime.armor.addPotionEffects, knight_slime.armor.effectLevels, knight_slime.armor.enablePieceEffects,
        knight_slime.armor.removePotionEffects
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots);
        }
    },
    PIG_IRON(pigIronArmor, "pig_iron", global_registry.enablePigIronArmor, getTICItemStack("ingots", 4), pig_iron.armor.itemNameColor,
        pig_iron.armor.enableSetEffects, pig_iron.armor.addPotionEffects, pig_iron.armor.effectLevels, pig_iron.armor.enablePieceEffects,
        pig_iron.armor.removePotionEffects
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
            if (pig_iron.armor.enableSetEffects && head.getItem() == pigIronHelmet && chest.getItem() == pigIronChestplate && legs.getItem() == pigIronLeggings && feet.getItem() == pigIronBoots) {
                for (ItemStack e : player.getArmorInventoryList()) {
                    e.damageItem(1, player);
                }
            }
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, pigIronHelmet, pigIronChestplate, pigIronLeggings, pigIronBoots);
        }
    },
    SLIME(slimeArmor, "slime", global_registry.enableSlimeArmor, SLIME_BLOCK, slime.armor.itemNameColor,
        slime.armor.enableSetEffects, slime.armor.addPotionEffects, slime.armor.effectLevels, slime.armor.enablePieceEffects,
        slime.armor.removePotionEffects
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, slot);
            addFullSetEffects(player, slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots);
        }
    },
    CHICKEN(chickenArmor, "chicken", global_registry.enableChickenArmor, Items.FEATHER, chicken.armor.itemNameColor,
        chicken.armor.enableSetEffects, chicken.armor.addPotionEffects, chicken.armor.effectLevels, chicken.armor.enablePieceEffects,
        chicken.armor.removePotionEffects
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
    private final Object repairMaterial;
    private final String formatting;
    private final boolean enableFullArmorEffect;
    private final String[] addPotionEffects;
    private final int[] addPotionEffectAmplifiers;
    private final String[] removePotionEffects;
    private final boolean[] areEffectsEnabled;

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, boolean enableArmor, Object repairStack, String textFormattingIn,
                    boolean enableFullArmorEffectIn, String[] addPotionEffectsIn, int[] addPotionEffectInAmplifiersIn, boolean[] areEffectsEnabledIn,
                    String[] removePotionEffectsIn) {
        this.armorMaterial = armorMaterialIn;
        this.name = nameIn;
        this.enableArmor = enableArmor;
        this.repairMaterial = repairStack;
        this.formatting = textFormattingIn;
        this.enableFullArmorEffect = enableFullArmorEffectIn;
        this.addPotionEffects = addPotionEffectsIn;
        this.addPotionEffectAmplifiers = addPotionEffectInAmplifiersIn;
        this.areEffectsEnabled = areEffectsEnabledIn;
        this.removePotionEffects = removePotionEffectsIn;
    }

    @Override
    public List<String> getApplyEffectNames() {
        return Arrays.asList(this.addPotionEffects);
    }

    @Override
    public List<Integer> getApplyAmplifierLevels() {
        return Arrays.stream(addPotionEffectAmplifiers).boxed().collect(Collectors.toList());
    }

    @Override
    public List<String> getRemoveEffectNames() {
        return Arrays.asList(this.removePotionEffects);
    }

    @Override
    public ItemStack getRepairStack() {
        return isNotEmpty(getItemStack(this.repairMaterial)) ? getItemStack(this.repairMaterial) : ItemStack.EMPTY;
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
        return this.enableFullArmorEffect;
    }

    public boolean[] getAreEffectsEnabled() {
        return this.areEffectsEnabled;
    }

    public abstract void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot);

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (isKeyDown()) {
            List<String> localizedEffects = getApplyEffectNames().stream().map(PotionUtils::localizePotion).collect(Collectors.toList());
            if (!this.enableFullArmorEffect()) {
                addToolTipPiece(tooltip, localizedEffects, this.getApplyAmplifierLevels());
            } else {
                addToolTipFull(tooltip, localizedEffects, this.getApplyAmplifierLevels());
            }
        } else {
            showInfo(tooltip, keyBindSneak, this.getFormatting());
        }
    }

    @Override
    public boolean isEnabled() {
        return this.enableArmor;
    }

    public void addPerPieceEffects(EntityPlayer player, EntityEquipmentSlot slot) {
        this.addEffects(player, slot == FEET ? 0 : slot == LEGS ? 1 : slot == CHEST ? 2 : slot == HEAD ? 3 : -1);
    }

    public void addEffects(EntityPlayer player, int index) {
        if (!enableFullArmorEffect() && this.getAreEffectsEnabled()[index]) {
            List<Potion> potions = getApplyEffectNames().stream().map(PotionUtils::getPotion).collect(Collectors.toList());
            IntStream.range(0, potions.size()).forEach(i -> {
                Potion potionEffect = potions.get(i);
                if ((player.getActivePotionEffect(potionEffect) == null || potionEffect == MobEffects.NIGHT_VISION) && potionEffect != ModPotions.EMPTY) {
                    addPotion(player, potionEffect, getApplyAmplifierLevels().get(i), GOOD);
                }
            });
            List<Potion> removablePotions = getRemoveEffectNames().stream().map(PotionUtils::getPotion).collect(Collectors.toList());
            removablePotions.stream().filter(
                potionEffect -> Utils.isNotNull(potionEffect) && potionEffect != ModPotions.EMPTY
            ).forEach(
                potionEffect -> removePotion(player, potionEffect)
            );
        }
    }

    public void addFullSetEffects(EntityPlayer player, Item helmet, Item chestplate, Item leggings, Item boots) {
        if (enableFullArmorEffect()) {
            ItemStack head = getStackFromSlot(player, HEAD);
            ItemStack chest = getStackFromSlot(player, CHEST);
            ItemStack legs = getStackFromSlot(player, LEGS);
            ItemStack feet = getStackFromSlot(player, FEET);
            if (isNull(helmet) || isNull(chestplate) || isNull(leggings) || isNull(boots) || isArmorEmpty(head, chest, legs, feet)) {
                return;
            }
            if (areSame(head, helmet) && areSame(chest, chestplate) && areSame(legs, leggings) && areSame(feet, boots)) {
                List<Potion> potions = getApplyEffectNames().stream().map(PotionUtils::getPotion).collect(Collectors.toList());
                IntStream.range(0, potions.size()).forEach(i -> {
                    Potion potionEffect = potions.get(i);
                    if ((player.getActivePotionEffect(potionEffect) == null || potionEffect == MobEffects.NIGHT_VISION) && potionEffect != ModPotions.EMPTY) {
                        addPotion(player, potionEffect, getApplyAmplifierLevels().get(i), GOOD);
                    }
                });

                List<Potion> removablePotions = getRemoveEffectNames().stream().map(PotionUtils::getPotion).collect(Collectors.toList());
                removablePotions.stream().filter(
                    potionEffect -> Utils.isNotNull(potionEffect) && potionEffect != ModPotions.EMPTY
                ).forEach(
                    potionEffect -> removePotion(player, potionEffect)
                );
            }
        }
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