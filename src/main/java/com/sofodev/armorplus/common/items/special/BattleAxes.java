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
import com.sofodev.armorplus.common.items.special.effects.Ignite;
import com.sofodev.armorplus.common.items.special.effects.Negative;
import com.sofodev.armorplus.common.items.special.effects.WeaponEffects;
import com.sofodev.armorplus.common.registry.ModBlocks;
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
import static com.sofodev.armorplus.common.items.base.ItemSpecialBattleAxe.*;
import static com.sofodev.armorplus.common.items.base.ItemSpecialBattleAxe.LAPIS_BATTLE_AXE;
import static com.sofodev.armorplus.common.registry.ModItems.itemLavaCrystal;
import static com.sofodev.armorplus.common.registry.ModItems.materials;
import static com.sofodev.armorplus.common.util.ArmorPlusItemUtils.applyNegativeEffect;
import static com.sofodev.armorplus.common.util.PotionUtils.localizePotion;
import static java.util.stream.IntStream.range;
import static net.minecraft.init.Blocks.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 **/
public enum BattleAxes implements IEffectHolder, IRemovable, IRepairable {
    COAL(COAL_BATTLE_AXE, getItemStack(COAL_BLOCK), coal, 8.0F),
    LAPIS(LAPIS_BATTLE_AXE, getItemStack(LAPIS_BLOCK), lapis, 9.0F),
    REDSTONE(REDSTONE_BATTLE_AXE, getItemStack(REDSTONE_BLOCK), redstone, 9.0F),
    EMERALD(EMERALD_BATTLE_AXE, getItemStack(EMERALD_BLOCK), emerald, 10.0F),
    OBSIDIAN(OBSIDIAN_BATTLE_AXE, getItemStack(ModBlocks.blockCompressedObsidian), obsidian, 10.5F),
    INFUSED_LAVA(INFUSED_LAVA_BATTLE_AXE, getItemStack(itemLavaCrystal, 1), lava, 11.5F),
    GUARDIAN(GUARDIAN_BATTLE_AXE, getItemStack(materials, 1), guardian, 14.0F),
    SUPER_STAR(SUPER_STAR_BATTLE_AXE, getItemStack(materials, 2), super_star, 15.0F),
    ENDER_DRAGON(ENDER_DRAGON_BATTLE_AXE, getItemStack(materials, 3), ender_dragon, 16.0F),
    // WOOD(, "wooden"),
    // STONE(, "stone"),
    // IRON(, "iron"),
    // GOLD(, "golden"),
    // DIAMOND(, "diamond")
    ;

    private final Item.ToolMaterial material;
    private final ItemStack repairStack;
    private final TextFormatting textFormatting;
    private final List<String> effect;
    private final float efficiency;
    private final Negative negative;
    private final Ignite ignite;

    BattleAxes(Item.ToolMaterial materialIn, ItemStack repairStackIn, OriginMaterial material, float efficiencyIn) {
        this.material = materialIn;
        this.repairStack = repairStackIn;
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

    @SideOnly(Side.CLIENT)
    public void addInformation(List<String> tooltip) {
        ToolTipUtils.addSpecialInformation(tooltip, this.negative, this.ignite, getTextFormatting());
    }
}
