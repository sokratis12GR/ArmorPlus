/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.items.weapons;

import com.sofodev.armorplus.api.properties.AbilityCanceller;
import com.sofodev.armorplus.api.properties.AbilityProvider;
import com.sofodev.armorplus.api.properties.iface.IEffectHolder;
import com.sofodev.armorplus.api.properties.iface.IRemovable;
import com.sofodev.armorplus.api.properties.iface.IRepairable;
import com.sofodev.armorplus.client.utils.ToolTipUtils;
import com.sofodev.armorplus.common.config.ModConfig.RegistryConfig;
import com.sofodev.armorplus.common.registry.items.weapons.effects.Ignite;
import com.sofodev.armorplus.common.registry.items.weapons.effects.Negative;
import com.sofodev.armorplus.common.registry.items.weapons.effects.WeaponEffects;
import com.sofodev.armorplus.common.util.PotionUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.OriginMaterial;
import static com.sofodev.armorplus.common.registry.items.base.ItemSpecialSword.*;
import static com.sofodev.armorplus.common.util.ArmorPlusItemUtils.applyNegativeEffect;
import static com.sofodev.armorplus.common.util.Utils.getBlock;
import static com.sofodev.armorplus.common.util.Utils.getItem;
import static java.util.stream.IntStream.range;
import static net.minecraft.init.Blocks.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;

/**
 * @author Sokratis Fotkatzikis
 **/
public enum Swords implements IEffectHolder, IRemovable, IRepairable {
    COAL(SWORD_COAL_MATERIAL, COAL_BLOCK, RegistryConfig.coal),
    LAPIS(SWORD_LAPIS_MATERIAL, LAPIS_BLOCK, RegistryConfig.lapis),
    REDSTONE(SWORD_REDSTONE_MATERIAL, REDSTONE_BLOCK, RegistryConfig.redstone),
    EMERALD(SWORD_EMERALD_MATERIAL, EMERALD_BLOCK, RegistryConfig.emerald),
    OBSIDIAN(SWORD_OBSIDIAN_MATERIAL, getBlock("compressed_obsidian"), RegistryConfig.obsidian),
    INFUSED_LAVA(SWORD_LAVA_MATERIAL, getItem("infused_lava_crystal"), RegistryConfig.lava),
    GUARDIAN(SWORD_GUARDIAN_MATERIAL, getItem("guardian_scale"), RegistryConfig.guardian),
    SUPER_STAR(SWORD_SUPER_STAR_MATERIAL, getItem("wither_bone"), RegistryConfig.super_star),
    ENDER_DRAGON(SWORD_ENDER_DRAGON_MATERIAL, getItem("ender_dragon_scale"), RegistryConfig.ender_dragon);

    private final IItemTier material;
    private final ItemStack repairStack;
    private final TextFormatting textFormatting;
    private final List<String> effect;
    private final Negative negative;
    private final Ignite ignite;

    Swords(IItemTier materialIn, IItemProvider repairItem, OriginMaterial material) {
        this.material = materialIn;
        this.repairStack = new ItemStack(repairItem);
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

    @OnlyIn(Dist.CLIENT)
    public static List<String> setToolTip(String[] effectName, int[] effectLevel) {
        return range(0, effectLevel.length).mapToObj(i -> PotionUtils.localizePotion(effectName[i]) + " " + (effectLevel[i] + 1)).collect(Collectors.toList());
    }

    public String getName() {
        return this.name().toLowerCase();
    }

    public IItemTier getToolMaterial() {
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

    @OnlyIn(Dist.CLIENT)
    public void addInformation(List<ITextComponent> tooltip) {
        ToolTipUtils.addSpecialInformation(tooltip, this.negative, this.ignite, getTextFormatting());
    }
}
