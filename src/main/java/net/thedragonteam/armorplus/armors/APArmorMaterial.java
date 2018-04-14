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
import net.thedragonteam.armorplus.util.PotionUtils;
import net.thedragonteam.armorplus.util.ToolTipUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;
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
    COAL(coalArmor, "coal", global_registry.enableCoalArmor, COAL_BLOCK, coal.armor.itemNameColor,
        coal.armor.enableSetEffects, coal.armor.addPotionEffects, coal.armor.effectLevels, coal.armor.enablePieceEffects,
        coal.armor.removePotionEffects, coal.armor.setUnbreakable
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, coal.armor.addPotionEffects, coal.armor.effectLevels, coal.armor.removePotionEffects, slot);
            addFullSetEffects(player, coal.armor.addPotionEffects, coal.armor.effectLevels, coal.armor.removePotionEffects, coalHelmet, coalChestplate, coalLeggings, coalBoots);
        }
    },
    LAPIS(lapisArmor, "lapis", global_registry.enableLapisArmor, LAPIS_BLOCK, lapis.armor.itemNameColor,
        lapis.armor.enableSetEffects, lapis.armor.addPotionEffects, lapis.armor.effectLevels, lapis.armor.enablePieceEffects,
        lapis.armor.removePotionEffects, lapis.armor.setUnbreakable
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, lapis.armor.addPotionEffects, lapis.armor.effectLevels, lapis.armor.removePotionEffects, slot);
            addFullSetEffects(player, lapis.armor.addPotionEffects, lapis.armor.effectLevels, lapis.armor.removePotionEffects, lapisHelmet, lapisChestplate, lapisLeggings, lapisBoots);
        }
    },
    REDSTONE(redstoneArmor, "redstone", global_registry.enableRedstoneArmor, REDSTONE_BLOCK, redstone.armor.itemNameColor,
        redstone.armor.enableSetEffects, redstone.armor.addPotionEffects, redstone.armor.effectLevels, redstone.armor.enablePieceEffects,
        redstone.armor.removePotionEffects, redstone.armor.setUnbreakable
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, redstone.armor.addPotionEffects, redstone.armor.effectLevels, redstone.armor.removePotionEffects, slot);
            addFullSetEffects(player, redstone.armor.addPotionEffects, redstone.armor.effectLevels, redstone.armor.removePotionEffects, redstoneHelmet, redstoneChestplate, redstoneLeggings, redstoneBoots);
        }
    },
    EMERALD(emeraldArmor, "emerald", global_registry.enableEmeraldArmor, EMERALD_BLOCK, emerald.armor.itemNameColor,
        emerald.armor.enableSetEffects, emerald.armor.addPotionEffects, emerald.armor.effectLevels, emerald.armor.enablePieceEffects,
        emerald.armor.removePotionEffects, emerald.armor.setUnbreakable
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, emerald.armor.addPotionEffects, emerald.armor.effectLevels, emerald.armor.removePotionEffects, slot);
            addFullSetEffects(player, emerald.armor.addPotionEffects, emerald.armor.effectLevels, emerald.armor.removePotionEffects, emeraldHelmet, emeraldChestplate, emeraldLeggings, emeraldBoots);
        }
    },
    OBSIDIAN(obsidianArmor, "obsidian", global_registry.enableObsidianArmor, compressedObsidian, obsidian.armor.itemNameColor,
        obsidian.armor.enableSetEffects, obsidian.armor.addPotionEffects, obsidian.armor.effectLevels, obsidian.armor.enablePieceEffects,
        obsidian.armor.removePotionEffects, obsidian.armor.setUnbreakable
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, obsidian.armor.addPotionEffects, obsidian.armor.effectLevels, obsidian.armor.removePotionEffects, slot);
            addFullSetEffects(player, obsidian.armor.addPotionEffects, obsidian.armor.effectLevels, obsidian.armor.removePotionEffects, obsidianHelmet, obsidianChestplate, obsidianLeggings, obsidianBoots);
        }
    },
    LAVA(lavaArmor, "infused_lava", global_registry.enableLavaArmor, getItemStack(lavaCrystal, 1), lava.armor.itemNameColor,
        lava.armor.enableSetEffects, lava.armor.addPotionEffects, lava.armor.effectLevels, lava.armor.enablePieceEffects,
        lava.armor.removePotionEffects, lava.armor.setUnbreakable
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
            addPerPieceEffects(player, lava.armor.addPotionEffects, lava.armor.effectLevels, lava.armor.removePotionEffects, slot);
            addFullSetEffects(player, lava.armor.addPotionEffects, lava.armor.effectLevels, lava.armor.removePotionEffects, lavaHelmet, lavaChestplate, lavaLeggings, lavaBoots);
        }
    },
    GUARDIAN(guardianArmor, "guardian", global_registry.enableGuardianArmor, getItemStack(materials, 1), guardian.armor.itemNameColor,
        guardian.armor.enableSetEffects, guardian.armor.addPotionEffects, guardian.armor.effectLevels, guardian.armor.enablePieceEffects,
        guardian.armor.removePotionEffects, guardian.armor.setUnbreakable
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, guardian.armor.addPotionEffects, guardian.armor.effectLevels, guardian.armor.removePotionEffects, slot);
            addFullSetEffects(player, guardian.armor.addPotionEffects, guardian.armor.effectLevels, guardian.armor.removePotionEffects, guardianHelmet, guardianChestplate, guardianLeggings, guardianBoots);
        }
    },
    SUPER_STAR(superStarArmor, "super_star", global_registry.enableSuperStarArmor, getItemStack(materials, 2), super_star.armor.itemNameColor,
        super_star.armor.enableSetEffects, super_star.armor.addPotionEffects, super_star.armor.effectLevels, super_star.armor.enablePieceEffects,
        super_star.armor.removePotionEffects, super_star.armor.setUnbreakable
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, super_star.armor.addPotionEffects, super_star.armor.effectLevels, super_star.armor.removePotionEffects, slot);
            addFullSetEffects(player, super_star.armor.addPotionEffects, super_star.armor.effectLevels, super_star.armor.removePotionEffects, superStarHelmet, superStarChestplate, superStarLeggings, superStarBoots);
        }
    },
    ENDER_DRAGON(enderDragonArmor, "ender_dragon", global_registry.enableEnderDragonArmor, getItemStack(materials, 3), ender_dragon.armor.itemNameColor,
        ender_dragon.armor.enableSetEffects, ender_dragon.armor.addPotionEffects, ender_dragon.armor.effectLevels, ender_dragon.armor.enablePieceEffects,
        ender_dragon.armor.removePotionEffects, ender_dragon.armor.setUnbreakable
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
            addPerPieceEffects(player, ender_dragon.armor.addPotionEffects, ender_dragon.armor.effectLevels, ender_dragon.armor.removePotionEffects, slot);
            addFullSetEffects(player, ender_dragon.armor.addPotionEffects, ender_dragon.armor.effectLevels, ender_dragon.armor.removePotionEffects, enderDragonHelmet, enderDragonChestplate, enderDragonLeggings, enderDragonBoots);
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
    ARDITE(arditeArmor, "ardite", global_registry.enableArditeArmor, getTICItemStack("ingots", 1), ardite.armor.itemNameColor,
        ardite.armor.enableSetEffects, ardite.armor.addPotionEffects, ardite.armor.effectLevels, ardite.armor.enablePieceEffects,
        ardite.armor.removePotionEffects, ardite.armor.setUnbreakable
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, ardite.armor.addPotionEffects, ardite.armor.effectLevels, ardite.armor.removePotionEffects, slot);
            addFullSetEffects(player, ardite.armor.addPotionEffects, ardite.armor.effectLevels, ardite.armor.removePotionEffects, arditeHelmet, arditeChestplate, arditeLeggings, arditeBoots);
        }
    },
    COBALT(cobaltArmor, "cobalt", global_registry.enableCobaltArmor, getTICItemStack("ingots", 0), cobalt.armor.itemNameColor,
        cobalt.armor.enableSetEffects, cobalt.armor.addPotionEffects, cobalt.armor.effectLevels, cobalt.armor.enablePieceEffects,
        cobalt.armor.removePotionEffects, cobalt.armor.setUnbreakable
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, cobalt.armor.addPotionEffects, cobalt.armor.effectLevels, cobalt.armor.removePotionEffects, slot);
            addFullSetEffects(player, cobalt.armor.addPotionEffects, cobalt.armor.effectLevels, cobalt.armor.removePotionEffects, cobaltHelmet, cobaltChestplate, coalLeggings, cobaltBoots);
        }
    },
    MANYULLYN(manyullynArmor, "manyullyn", global_registry.enableManyullynArmor, getTICItemStack("ingots", 2), manyullyn.armor.itemNameColor,
        manyullyn.armor.enableSetEffects, manyullyn.armor.addPotionEffects, manyullyn.armor.effectLevels, manyullyn.armor.enablePieceEffects,
        manyullyn.armor.removePotionEffects, manyullyn.armor.setUnbreakable
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, manyullyn.armor.addPotionEffects, manyullyn.armor.effectLevels, manyullyn.armor.removePotionEffects, slot);
            addFullSetEffects(player, manyullyn.armor.addPotionEffects, manyullyn.armor.effectLevels, manyullyn.armor.removePotionEffects, manyullynHelmet, manyullynChestplate, manyullynLeggings, manyullynBoots);
        }
    },
    KNIGHT_SLIME(knightSlimeArmor, "knight_slime", global_registry.enableKnightSlimeArmor, getTICItemStack("ingots", 3), knight_slime.armor.itemNameColor,
        knight_slime.armor.enableSetEffects, knight_slime.armor.addPotionEffects, knight_slime.armor.effectLevels, knight_slime.armor.enablePieceEffects,
        knight_slime.armor.removePotionEffects, knight_slime.armor.setUnbreakable
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, knight_slime.armor.addPotionEffects, knight_slime.armor.effectLevels, knight_slime.armor.removePotionEffects, slot);
            addFullSetEffects(player, knight_slime.armor.addPotionEffects, knight_slime.armor.effectLevels, knight_slime.armor.removePotionEffects, knightSlimeHelmet, knightSlimeChestplate, knightSlimeLeggings, knightSlimeBoots);
        }
    },
    PIG_IRON(pigIronArmor, "pig_iron", global_registry.enablePigIronArmor, getTICItemStack("ingots", 4), pig_iron.armor.itemNameColor,
        pig_iron.armor.enableSetEffects, pig_iron.armor.addPotionEffects, pig_iron.armor.effectLevels, pig_iron.armor.enablePieceEffects,
        pig_iron.armor.removePotionEffects, pig_iron.armor.setUnbreakable
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
            addPerPieceEffects(player, pig_iron.armor.addPotionEffects, pig_iron.armor.effectLevels, pig_iron.armor.removePotionEffects, slot);
            addFullSetEffects(player, pig_iron.armor.addPotionEffects, pig_iron.armor.effectLevels, pig_iron.armor.removePotionEffects, pigIronHelmet, pigIronChestplate, pigIronLeggings, pigIronBoots);
        }
    },
    SLIME(slimeArmor, "slime", global_registry.enableSlimeArmor, SLIME_BLOCK, slime.armor.itemNameColor,
        slime.armor.enableSetEffects, slime.armor.addPotionEffects, slime.armor.effectLevels, slime.armor.enablePieceEffects,
        slime.armor.removePotionEffects, slime.armor.setUnbreakable
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, slime.armor.addPotionEffects, slime.armor.effectLevels, slime.armor.removePotionEffects, slot);
            addFullSetEffects(player, slime.armor.addPotionEffects, slime.armor.effectLevels, slime.armor.removePotionEffects, slimeHelmet, slimeChestplate, slimeLeggings, slimeBoots);
        }
    },
    CHICKEN(chickenArmor, "chicken", global_registry.enableChickenArmor, Items.FEATHER, chicken.armor.itemNameColor,
        chicken.armor.enableSetEffects, chicken.armor.addPotionEffects, chicken.armor.effectLevels, chicken.armor.enablePieceEffects,
        chicken.armor.removePotionEffects, chicken.armor.setUnbreakable
    ) {
        @Override
        public void onArmorTick(EntityPlayer player, EntityEquipmentSlot slot) {
            addPerPieceEffects(player, chicken.armor.addPotionEffects, chicken.armor.effectLevels, chicken.armor.removePotionEffects, slot);
            addFullSetEffects(player, chicken.armor.addPotionEffects, chicken.armor.effectLevels, chicken.armor.removePotionEffects, chickenHelmet, chickenChestplate, chickenLeggings, chickenBoots);
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
    private final boolean isUnbreakable;
    private final boolean[] areEffectsEnabled;

    APArmorMaterial(ArmorMaterial armorMaterialIn, String nameIn, boolean enableArmor, Object repairStack, String textFormattingIn,
                    boolean enableFullArmorEffectIn, String[] addPotionEffectsIn, int[] addPotionEffectInAmplifiersIn, boolean[] areEffectsEnabledIn,
                    String[] removePotionEffectsIn, boolean isUnbreakable) {
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
        this.isUnbreakable = isUnbreakable;
    }

    @Override
    public List<String> getApplyEffectNames() {
        return stream(this.addPotionEffects).collect(toList());
    }

    @Override
    public List<Integer> getApplyAmplifierLevels() {
        return stream(addPotionEffectAmplifiers).boxed().collect(toList());
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
            List<String> localizedEffects = getApplyEffectNames().stream().map(PotionUtils::localizePotion).collect(toList());
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

    public void addPerPieceEffects(EntityPlayer player, String[] applyEffectNames, int[] applyEffectAmplifiers, String[] removeEffectNames, EntityEquipmentSlot slot) {
        this.addEffects(player, stream(applyEffectNames).collect(toList()), stream(applyEffectAmplifiers).boxed().collect(toList()), stream(removeEffectNames).collect(toList()), slot == FEET ? 0 : slot == LEGS ? 1 : slot == CHEST ? 2 : slot == HEAD ? 3 : -1);
    }

    public void addEffects(EntityPlayer player, List<String> applyEffectNames, List<Integer> applyEffectAmplifiers, List<String> removeEffectNames, int index) {
        if (!enableFullArmorEffect()) {
            if (this.getAreEffectsEnabled()[index]) {
                List<Potion> potions = applyEffectNames.stream().map(PotionUtils::getPotion).collect(toList());
                IntStream.range(0, potions.size()).forEach(i -> {
                    Potion potionEffect = potions.get(i);
                    if ((player.getActivePotionEffect(potionEffect) == null || potionEffect == MobEffects.NIGHT_VISION)) {
                        addPotion(player, potionEffect, applyEffectAmplifiers.get(i), GOOD);
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

    public void addFullSetEffects(EntityPlayer player, String[] applyEffectNames, int[] applyEffectAmplifiers, String[] removeEffectNames, Item helmet, Item chestplate, Item leggings, Item boots) {
        this.addFullSetEffects(player, stream(applyEffectNames).collect(toList()), stream(applyEffectAmplifiers).boxed().collect(toList()), stream(removeEffectNames).collect(toList()), helmet, chestplate, leggings, boots);
    }

    public void addFullSetEffects(EntityPlayer player, List<String> applyEffectNames, List<Integer> applyEffectAmplifiers, List<String> removeEffectNames, Item helmet, Item chestplate, Item leggings, Item boots) {
        ItemStack head = getStackFromSlot(player, HEAD);
        ItemStack chest = getStackFromSlot(player, CHEST);
        ItemStack legs = getStackFromSlot(player, LEGS);
        ItemStack feet = getStackFromSlot(player, FEET);
        if (enableFullArmorEffect()) {
            if (isArmorEmpty(head, chest, legs, feet)) {
                return;
            }
            if (this.enableFullArmorEffect() && head.getItem() == helmet && chest.getItem() == chestplate && legs.getItem() == leggings && feet.getItem() == boots) {
                List<Potion> potions = applyEffectNames.stream().map(PotionUtils::getPotion).collect(toList());
                IntStream.range(0, potions.size()).forEach(i -> {
                    Potion potionEffect = potions.get(i);
                    if ((player.getActivePotionEffect(potionEffect) == null || potionEffect == MobEffects.NIGHT_VISION)) {
                        addPotion(player, potionEffect, applyEffectAmplifiers.get(i), GOOD);
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