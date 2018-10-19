/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.weapons;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.api.properties.AbilityCanceller;
import net.thedragonteam.armorplus.api.properties.AbilityProvider;
import net.thedragonteam.armorplus.api.properties.iface.IEffectHolder;
import net.thedragonteam.armorplus.api.properties.iface.IRemovable;
import net.thedragonteam.armorplus.api.properties.iface.IRepairable;
import net.thedragonteam.armorplus.client.utils.ToolTipUtils;
import net.thedragonteam.armorplus.items.weapons.effects.Ignite;
import net.thedragonteam.armorplus.items.weapons.effects.Negative;
import net.thedragonteam.armorplus.items.weapons.effects.WeaponEffects;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;
import static net.minecraft.init.Blocks.*;
import static net.minecraft.item.ItemStack.EMPTY;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.*;
import static net.thedragonteam.armorplus.items.base.ItemSpecialSword.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.blockCompressedObsidian;
import static net.thedragonteam.armorplus.registry.ModItems.itemLavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.armorplus.util.ArmorPlusItemUtils.applyNegativeEffect;
import static net.thedragonteam.armorplus.util.PotionUtils.localizePotion;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public enum Swords implements IEffectHolder, IRemovable, IRepairable {
    COAL(swordCoalMaterial, "coal", getItemStack(COAL_BLOCK), coal, global_registry.enableCoalWeapons),
    LAPIS(swordLapisMaterial, "lapis", getItemStack(LAPIS_BLOCK), lapis, global_registry.enableLapisWeapons),
    REDSTONE(swordRedstoneMaterial, "redstone", getItemStack(REDSTONE_BLOCK), redstone, global_registry.enableRedstoneWeapons),
    EMERALD(swordEmeraldMaterial, "emerald", getItemStack(EMERALD_BLOCK), emerald, global_registry.enableEmeraldWeapons),
    OBSIDIAN(swordObsidianMaterial, "obsidian", getItemStack(blockCompressedObsidian), obsidian, global_registry.enableObsidianWeapons),
    LAVA(swordLavaMaterial, "infused_lava", getItemStack(itemLavaCrystal, 1), lava, global_registry.enableLavaWeapons),
    GUARDIAN(swordGuardianMaterial, "guardian", getItemStack(materials, 1), guardian, global_registry.enableGuardianWeapons),
    SUPER_STAR(swordSuperStarMaterial, "super_star", getItemStack(materials, 2), super_star, global_registry.enableSuperStarWeapons),
    ENDER_DRAGON(swordEnderDragonMaterial, "ender_dragon", getItemStack(materials, 3), ender_dragon, global_registry.enableEnderDragonWeapons);

    private final String name;
    private final Item.ToolMaterial material;
    private final ItemStack repairStack;
    private final TextFormatting textFormatting;
    private final boolean isEnabled;
    private final List<String> effect;
    private final Negative negative;
    private final Ignite ignite;

    Swords(Item.ToolMaterial materialIn, String nameIn, ItemStack repairStackIn, OriginMaterial material, boolean[] isEnabled
    ) {
        this.material = materialIn;
        this.name = nameIn;
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

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
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
