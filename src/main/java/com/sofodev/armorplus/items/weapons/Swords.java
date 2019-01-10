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
import com.sofodev.armorplus.items.base.ItemSpecialSword;
import com.sofodev.armorplus.items.weapons.effects.Ignite;
import com.sofodev.armorplus.items.weapons.effects.Negative;
import com.sofodev.armorplus.items.weapons.effects.WeaponEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.*;
import static com.sofodev.armorplus.registry.ModBlocks.blockCompressedObsidian;
import static com.sofodev.armorplus.registry.ModItems.itemLavaCrystal;
import static com.sofodev.armorplus.registry.ModItems.materials;
import static com.sofodev.armorplus.util.ArmorPlusItemUtils.applyNegativeEffect;
import static com.sofodev.armorplus.util.PotionUtils.localizePotion;
import static java.util.stream.IntStream.range;
import static net.minecraft.init.Blocks.*;
import static net.minecraft.item.ItemStack.EMPTY;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 **/
public enum Swords implements IEffectHolder, IRemovable, IRepairable {
    COAL(ItemSpecialSword.swordCoalMaterial, getItemStack(COAL_BLOCK), coal, global_registry.enableCoalWeapons),
    LAPIS(ItemSpecialSword.swordLapisMaterial, getItemStack(LAPIS_BLOCK), lapis, global_registry.enableLapisWeapons),
    REDSTONE(ItemSpecialSword.swordRedstoneMaterial, getItemStack(REDSTONE_BLOCK), redstone, global_registry.enableRedstoneWeapons),
    EMERALD(ItemSpecialSword.swordEmeraldMaterial, getItemStack(EMERALD_BLOCK), emerald, global_registry.enableEmeraldWeapons),
    OBSIDIAN(ItemSpecialSword.swordObsidianMaterial, getItemStack(blockCompressedObsidian), obsidian, global_registry.enableObsidianWeapons),
    INFUSED_LAVA(ItemSpecialSword.swordLavaMaterial, getItemStack(itemLavaCrystal, 1), lava, global_registry.enableLavaWeapons),
    GUARDIAN(ItemSpecialSword.swordGuardianMaterial, getItemStack(materials, 1), guardian, global_registry.enableGuardianWeapons),
    SUPER_STAR(ItemSpecialSword.swordSuperStarMaterial, getItemStack(materials, 2), super_star, global_registry.enableSuperStarWeapons),
    ENDER_DRAGON(ItemSpecialSword.swordEnderDragonMaterial, getItemStack(materials, 3), ender_dragon, global_registry.enableEnderDragonWeapons);

    private final Item.ToolMaterial material;
    private final ItemStack repairStack;
    private final TextFormatting textFormatting;
    private final boolean isEnabled;
    private final List<String> effect;
    private final Negative negative;
    private final Ignite ignite;

    Swords(Item.ToolMaterial materialIn, ItemStack repairStackIn, OriginMaterial material, boolean[] isEnabled
    ) {
        this.material = materialIn;
        this.repairStack = repairStackIn == null ? EMPTY : repairStackIn;
        this.textFormatting = getValueByName(material.weapons.itemNameColor);
        this.isEnabled = isEnabled[0];
        WeaponEffects effects = new WeaponEffects(material);
        this.negative = effects.getNegative();
        this.ignite = effects.getIgnite();
        this.effect = setToolTip(negative.getEffects(), negative.getEffectLevels());
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
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
