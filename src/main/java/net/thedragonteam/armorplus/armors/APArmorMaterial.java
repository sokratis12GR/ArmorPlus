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
import net.thedragonteam.armorplus.ModConfig.RegistryConfig.OriginMaterial.OriginArmor;
import net.thedragonteam.armorplus.ModConfig.RegistryConfig.SpecialMaterial.Armor;
import net.thedragonteam.armorplus.api.properties.iface.IEffectHolder;
import net.thedragonteam.armorplus.api.properties.iface.IRemovable;
import net.thedragonteam.armorplus.api.properties.iface.IRepairable;
import net.thedragonteam.armorplus.util.PotionUtils;
import net.thedragonteam.armorplus.util.ToolTipUtils;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
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
import static net.thedragonteam.armorplus.util.ToolTipUtils.*;
import static net.thedragonteam.armorplus.util.Utils.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getTICItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public enum APArmorMaterial implements IEffectHolder, IRepairable, IRemovable {
    COAL(coalArmor, "coal", global_registry.enableCoalArmor, COAL_BLOCK, coal.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, coal, slot, coalHelmet, coalChestplate, coalLeggings, coalBoots);
        }
    },
    LAPIS(lapisArmor, "lapis", global_registry.enableLapisArmor, LAPIS_BLOCK, lapis.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, lapis, slot, lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots);
        }
    },
    REDSTONE(redstoneArmor, "redstone", global_registry.enableRedstoneArmor, REDSTONE_BLOCK, redstone.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, redstone, slot, redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots);
        }
    },
    EMERALD(emeraldArmor, "emerald", global_registry.enableEmeraldArmor, EMERALD_BLOCK, emerald.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, emerald, slot, emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots);
        }
    },
    OBSIDIAN(obsidianArmor, "obsidian", global_registry.enableObsidianArmor, compressedObsidian, obsidian.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, obsidian, slot, obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots);
        }
    },
    LAVA(lavaArmor, "infused_lava", global_registry.enableLavaArmor, getItemStack(lavaCrystal, 1), lava.armor) {
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
            this.addEffects(player, lava, slot, lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots);
        }
    },
    GUARDIAN(guardianArmor, "guardian", global_registry.enableGuardianArmor, getItemStack(materials, 1), guardian.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, guardian, slot, guardianHelmet, guardianChestplate, guardianLeggings, guardianBoots);
        }
    },
    SUPER_STAR(superStarArmor, "super_star", global_registry.enableSuperStarArmor, getItemStack(materials, 2), super_star.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, super_star, slot, superStarHelmet, superStarChestplate, superStarLeggings, superStarBoots);
        }
    },
    ENDER_DRAGON(enderDragonArmor, "ender_dragon", global_registry.enableEnderDragonArmor, getItemStack(materials, 3), ender_dragon.armor) {
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
            this.addEffects(player, ender_dragon, slot, enderDragonHelmet, enderDragonChestplate, enderDragonLeggings, enderDragonBoots);
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
    ARDITE(arditeArmor, "ardite", global_registry.enableArditeArmor, getTICItemStack("ingots", 1), ardite.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, ardite, slot, arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots);
        }
    },
    COBALT(cobaltArmor, "cobalt", global_registry.enableCobaltArmor, getTICItemStack("ingots", 0), cobalt.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, cobalt, slot, cobaltHelmet, cobaltChestplate, coalLeggings, cobaltBoots);
        }
    },
    MANYULLYN(manyullynArmor, "manyullyn", global_registry.enableManyullynArmor, getTICItemStack("ingots", 2), manyullyn.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, manyullyn, slot, manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots);
        }
    },
    KNIGHT_SLIME(knightSlimeArmor, "knight_slime", global_registry.enableKnightSlimeArmor, getTICItemStack("ingots", 3), knight_slime.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, knight_slime, slot, knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots);
        }
    },
    PIG_IRON(pigIronArmor, "pig_iron", global_registry.enablePigIronArmor, getTICItemStack("ingots", 4), pig_iron.armor) {
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
            this.addEffects(player, pig_iron, slot, pigIronHelmet, pigIronChestplate, pigIronLeggings, pigIronBoots);
        }
    },
    SLIME(slimeArmor, "slime", global_registry.enableSlimeArmor, SLIME_BLOCK, slime.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, slime, slot, slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots);
        }
    },
    CHICKEN(chickenArmor, "chicken", global_registry.enableChickenArmor, Items.FEATHER, chicken.armor) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            this.addEffects(player, chicken, slot, chickenHelmet, chickenChestplate, chickenLeggings, chickenBoots);
        }
    },;

    private final ArmorMaterial armorMaterial;
    private final String name;
    private final boolean enableArmor;
    private final Object repairMaterial;
    private final String formatting;
    private final boolean enableSetEffects;
    private final String[] effects;
    private final int[] effectLevels;
    private final int[] effectDurations;
    private final String[] removeEffects;
    private final boolean isUnbreakable;
    private final boolean[] areEffectsEnabled;

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, boolean enableArmor, Object repairStack, OriginArmor armor) {
        this(armorMaterialIn, nameIn, enableArmor, repairStack,
            armor.itemNameColor, armor.enableSetEffects, armor.addPotionEffects, armor.effectLevels, armor.effectDurations, armor.enablePieceEffects, armor.removePotionEffects, armor.setUnbreakable
        );
    }

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, boolean enableArmor, Object repairStack, Armor armor) {
        this(armorMaterialIn, nameIn, enableArmor, repairStack,
            armor.itemNameColor, armor.enableSetEffects, armor.addPotionEffects, armor.effectLevels, armor.effectDurations, armor.enablePieceEffects, armor.removePotionEffects, armor.setUnbreakable
        );
    }

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, boolean enableArmor, Object repairStack, String textFormattingIn,
                    boolean enableSetEffects, String[] effects, int[] effectLevels, int[] effectDurations, boolean[] areEffectsEnabledIn,
                    String[] removeEffects, boolean isUnbreakable) {
        this.armorMaterial = armorMaterialIn;
        this.name = nameIn;
        this.enableArmor = enableArmor;
        this.repairMaterial = repairStack;
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
        return isNotEmpty(getItemStack(this.repairMaterial)) ? getItemStack(this.repairMaterial) : ItemStack.EMPTY;
    }

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

    public boolean[] getAreEffectsEnabled() {
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
            if (this.getAreEffectsEnabled()[index]) {
                List<Potion> potions = applyEffectNames.stream().map(PotionUtils::getPotion).collect(toList());
                IntStream.range(0, potions.size()).forEach(potionID -> {
                    Potion potionEffect = potions.get(potionID);
                    if ((player.getActivePotionEffect(potionEffect) == null || potionEffect == MobEffects.NIGHT_VISION)) {
                        addPotion(player, potionEffect, convertToSeconds(applyEffectDurations.get(potionID)), applyEffectLevels.get(potionID), GOOD);
                    }
                });
                List<Potion> removablePotions = removeEffectNames.stream().map(PotionUtils::getPotion).collect(toList());
                removablePotions.stream().filter(
                    potionEffect -> player.getActivePotionEffect(potionEffect) != null
                ).forEach(
                    player::removeActivePotionEffect
                );
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
        ItemStack headI = getStackFromSlot(player, HEAD);
        ItemStack chestI = getStackFromSlot(player, CHEST);
        ItemStack legsI = getStackFromSlot(player, LEGS);
        ItemStack feetI = getStackFromSlot(player, FEET);
        if (enableFullArmorEffect()) {
            if (isArmorEmpty(headI, chestI, legsI, feetI)) {
                return;
            }
            if (this.enableFullArmorEffect() && headI.getItem() == head && chestI.getItem() == chest && legsI.getItem() == legs && feetI.getItem() == feet) {
                List<Potion> potions = applyEffectNames.stream().map(PotionUtils::getPotion).collect(toList());
                IntStream.range(0, potions.size()).forEach(potionID -> {
                        Potion potionEffect = potions.get(potionID);
                        if ((player.getActivePotionEffect(potionEffect) == null || potionEffect == MobEffects.NIGHT_VISION)) {
                            addPotion(player, potionEffect, convertToSeconds(applyEffectDurations.get(potionID)), applyEffectLevels.get(potionID), GOOD);
                        }
                    }
                );

                List<Potion> removablePotions = removeEffectNames.stream().map(PotionUtils::getPotion).collect(toList());
                removablePotions.stream().filter(
                    potionEffect -> player.getActivePotionEffect(potionEffect) != null
                ).forEach(
                    player::removeActivePotionEffect
                );
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