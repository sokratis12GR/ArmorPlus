/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.items.special;

import com.sofodev.armorplus.api.properties.AbilityCanceller;
import com.sofodev.armorplus.api.properties.AbilityProvider;
import com.sofodev.armorplus.api.properties.iface.IEffectHolder;
import com.sofodev.armorplus.api.properties.iface.IRemovable;
import com.sofodev.armorplus.api.properties.iface.IRepairable;
import com.sofodev.armorplus.client.utils.ToolTipUtils;
import com.sofodev.armorplus.common.items.base.ItemSpecialSword;
import com.sofodev.armorplus.common.items.special.effects.Ignite;
import com.sofodev.armorplus.common.items.special.effects.Negative;
import com.sofodev.armorplus.common.items.special.effects.WeaponEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.*;
import static com.sofodev.armorplus.common.registry.ModBlocks.blockCompressedObsidian;
import static com.sofodev.armorplus.common.registry.ModItems.itemLavaCrystal;
import static com.sofodev.armorplus.common.registry.ModItems.materials;
import static com.sofodev.armorplus.common.util.ArmorPlusItemUtils.applyNegativeEffect;
import static com.sofodev.armorplus.common.util.PotionUtils.localizePotion;
import static java.util.stream.IntStream.range;
import static net.minecraft.init.Blocks.*;
import static net.minecraft.item.ItemStack.EMPTY;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 **/
public enum Swords implements IEffectHolder, IRemovable, IRepairable {
    COAL(ItemSpecialSword.COAL_SWORD, getItemStack(COAL_BLOCK), coal),
    LAPIS(ItemSpecialSword.LAPIS_SWORD, getItemStack(LAPIS_BLOCK), lapis),
    REDSTONE(ItemSpecialSword.REDSTONE_SWORD, getItemStack(REDSTONE_BLOCK), redstone),
    EMERALD(ItemSpecialSword.EMERALD_SWORD, getItemStack(EMERALD_BLOCK), emerald),
    OBSIDIAN(ItemSpecialSword.OBSIDIAN_SWORD, getItemStack(blockCompressedObsidian), obsidian),
    INFUSED_LAVA(ItemSpecialSword.INFUSED_LAVA_SWORD, getItemStack(itemLavaCrystal, 1), lava),
    GUARDIAN(ItemSpecialSword.GUARDIAN_SWORD, getItemStack(materials, 1), guardian),
    SUPER_STAR(ItemSpecialSword.SUPER_STAR_SWORD, getItemStack(materials, 2), super_star),
    ENDER_DRAGON(ItemSpecialSword.ENDER_DRAGON_SWORD, getItemStack(materials, 3), ender_dragon);

    private final Item.ToolMaterial material;
    private final ItemStack repairStack;
    private final TextFormatting textFormatting;
    private final List<String> effect;
    private final Negative negative;
    private final Ignite ignite;

    Swords(Item.ToolMaterial materialIn, ItemStack repairStackIn, OriginMaterial material) {
        this.material = materialIn;
        this.repairStack = repairStackIn == null ? EMPTY : repairStackIn;
        this.textFormatting = getValueByName(material.weapons.itemNameColor);
        WeaponEffects effects = new WeaponEffects(material);
        this.negative = effects.getNegative();
        this.ignite = effects.getIgnite();
        this.effect = setToolTip(negative.getEffects(), negative.getEffectLevels());
    }

    @Override
    public AbilityProvider getApplicableAbilities() {
        return new AbilityProvider(this.negative.getEffects(), this.negative.getEffectLevels(), this.negative.getEffectDurations());
    }

    @Override
    public AbilityCanceller getRemovableAbilities() {
        return new AbilityCanceller();
    }

    public static List<String> setToolTip(String[] effectName, int[] effectLevel) {
        return range(0, effectLevel.length).mapToObj(i -> localizePotion(effectName[i]) + " " + (effectLevel[i] + 1)).collect(Collectors.toList());
    }

    public String getName() {
        return this.name().toLowerCase();
    }

    public Item.ToolMaterial getToolMaterial() {
        return material;
    }

    public List<String> getEffects() {
        return effect;
    }

    @Override
    public ItemStack getRepairStack() {
        return repairStack;
    }

    public TextFormatting getTextFormatting() {
        return textFormatting;
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase target, @Nonnull EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        if (this.ignite.isEnabled()) {
            target.setFire(this.ignite.getFireSeconds());
        }
        applyNegativeEffect(target, negative);
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(List<String> tooltip) {
        ToolTipUtils.addSpecialInformation(tooltip, this.negative, this.ignite, getTextFormatting());
    }
}
