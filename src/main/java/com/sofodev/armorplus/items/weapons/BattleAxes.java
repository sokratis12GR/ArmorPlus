/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.weapons;

import com.sofodev.armorplus.api.properties.AbilityCanceller;
import com.sofodev.armorplus.api.properties.AbilityProvider;
import com.sofodev.armorplus.api.properties.iface.IEffectHolder;
import com.sofodev.armorplus.api.properties.iface.IRemovable;
import com.sofodev.armorplus.api.properties.iface.IRepairable;
import com.sofodev.armorplus.client.utils.ToolTipUtils;
import com.sofodev.armorplus.items.weapons.effects.Ignite;
import com.sofodev.armorplus.items.weapons.effects.Negative;
import com.sofodev.armorplus.items.weapons.effects.WeaponEffects;
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

import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.*;
import static com.sofodev.armorplus.items.base.ItemSpecialBattleAxe.*;
import static com.sofodev.armorplus.util.ArmorPlusItemUtils.applyNegativeEffect;
import static com.sofodev.armorplus.util.PotionUtils.localizePotion;
import static com.sofodev.armorplus.util.Utils.getBlock;
import static com.sofodev.armorplus.util.Utils.getItem;
import static java.util.stream.IntStream.range;
import static net.minecraft.init.Blocks.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;

/**
 * @author Sokratis Fotkatzikis
 **/
public enum BattleAxes implements IEffectHolder, IRemovable, IRepairable {
    COAL(BATTLE_AXE_COAL_MATERIAL, COAL_BLOCK, coal, 8.0F),
    LAPIS(BATTLE_AXE_LAPIS_MATERIAL, LAPIS_BLOCK, lapis, 9.0F),
    REDSTONE(BATTLE_AXE_REDSTONE_MATERIAL, REDSTONE_BLOCK, redstone, 9.0F),
    EMERALD(BATTLE_AXE_EMERALD_MATERIAL, EMERALD_BLOCK, emerald, 10.0F),
    OBSIDIAN(BATTLE_AXE_OBSIDIAN_MATERIAL, getBlock("compressed_obsidian"), obsidian, 10.5F),
    INFUSED_LAVA(BATTLE_AXE_LAVA_MATERIAL, getItem("infused_lava_crystal"), lava, 11.5F),
    GUARDIAN(BATTLE_AXE_GUARDIAN_MATERIAL, getItem("guardian_scale"), guardian, 14.0F),
    SUPER_STAR(BATTLE_AXE_SUPER_STAR_MATERIAL, getItem("wither_bone"), super_star, 15.0F),
    ENDER_DRAGON(BATTLE_AXE_ENDER_DRAGON_MATERIAL, getItem("ender_dragon_scale"), ender_dragon, 16.0F),
    // WOOD(, "wooden"),
    // STONE(, "stone"),
    // IRON(, "iron"),
    // GOLD(, "golden"),
    // DIAMOND(, "diamond")
    ;

    private final IItemTier material;
    private final ItemStack repairStack;
    private final TextFormatting textFormatting;
    private final List<String> effect;
    private final float efficiency;
    private final Negative negative;
    private final Ignite ignite;

    BattleAxes(IItemTier materialIn, IItemProvider repairItem, OriginMaterial material, float efficiencyIn) {
        this.material = materialIn;
        this.repairStack = new ItemStack(repairItem);
        this.textFormatting = getValueByName(material.weapons.itemNameColor);
        this.efficiency = efficiencyIn;
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
        return range(0, effectLevel.length).mapToObj(i -> localizePotion(effectName[i]) + " " + (effectLevel[i] + 1)).collect(Collectors.toList());
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

    public float getEfficiency() {
        return efficiency;
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase target, @Nonnull EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        if (this.ignite.isEnabled()) {
            target.setFire(ignite.getFireSeconds());
        }
        applyNegativeEffect(target, negative);
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(List<ITextComponent> tooltip) {
        ToolTipUtils.addSpecialInformation(tooltip, this.negative, this.ignite, getTextFormatting());
    }
}
