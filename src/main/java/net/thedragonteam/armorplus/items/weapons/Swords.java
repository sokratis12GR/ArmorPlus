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
import net.thedragonteam.armorplus.api.properties.IEffectHolder;
import net.thedragonteam.armorplus.api.properties.IRemovable;
import net.thedragonteam.armorplus.api.properties.IRepairable;
import net.thedragonteam.armorplus.items.weapons.effects.Ignite;
import net.thedragonteam.armorplus.items.weapons.effects.Negative;
import net.thedragonteam.armorplus.util.ToolTipUtils;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;
import static net.minecraft.init.Blocks.*;
import static net.minecraft.item.ItemStack.EMPTY;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.*;
import static net.thedragonteam.armorplus.items.base.ItemSpecialSword.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.compressedObsidian;
import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public enum Swords implements IEffectHolder, IRemovable, IRepairable {
    COAL(swordCoalMaterial, "coal", getItemStack(COAL_BLOCK), getValueByName(coal.weapons.itemNameColor),
        new Negative(coal), new Ignite(coal), global_registry.enableCoalWeapons[0]
    ),
    LAPIS(swordLapisMaterial, "lapis", getItemStack(LAPIS_BLOCK), getValueByName(lapis.weapons.itemNameColor),
        new Negative(lapis), new Ignite(lapis), global_registry.enableLapisWeapons[0]
    ),
    REDSTONE(swordRedstoneMaterial, "redstone", getItemStack(REDSTONE_BLOCK), getValueByName(redstone.weapons.itemNameColor),
        new Negative(redstone), new Ignite(redstone), global_registry.enableRedstoneWeapons[0]
    ),
    EMERALD(swordEmeraldMaterial, "emerald", getItemStack(EMERALD_BLOCK), getValueByName(emerald.weapons.itemNameColor),
        new Negative(emerald), new Ignite(emerald), global_registry.enableEmeraldWeapons[0]
    ),
    OBSIDIAN(swordObsidianMaterial, "obsidian", getItemStack(compressedObsidian), getValueByName(obsidian.weapons.itemNameColor),
        new Negative(obsidian), new Ignite(obsidian), global_registry.enableObsidianWeapons[0]
    ),
    LAVA(swordLavaMaterial, "infused_lava", getItemStack(lavaCrystal, 1), getValueByName(lava.weapons.itemNameColor),
        new Negative(lava), new Ignite(lava), global_registry.enableLavaWeapons[0]
    ),
    GUARDIAN(swordGuardianMaterial, "guardian", getItemStack(materials, 1), getValueByName(guardian.weapons.itemNameColor),
        new Negative(guardian), new Ignite(guardian), global_registry.enableGuardianWeapons[0]
    ),
    SUPER_STAR(swordSuperStarMaterial, "super_star", getItemStack(materials, 2), getValueByName(super_star.weapons.itemNameColor),
        new Negative(super_star), new Ignite(super_star), global_registry.enableSuperStarWeapons[0]
    ),
    ENDER_DRAGON(swordEnderDragonMaterial, "ender_dragon", getItemStack(materials, 3), getValueByName(ender_dragon.weapons.itemNameColor),
        new Negative(ender_dragon), new Ignite(ender_dragon), global_registry.enableEnderDragonWeapons[0]
    );

    private final String name;
    private final Item.ToolMaterial material;
    private final ItemStack repairStack;
    private final TextFormatting textFormatting;
    private final boolean isEnabled;
    private final List<String> effect;
    private final Negative negative;
    private final Ignite ignite;

    Swords(Item.ToolMaterial materialIn, String nameIn, ItemStack repairStackIn, TextFormatting textFormattingIn, Negative negative, Ignite ignite, boolean isEnabled
    ) {
        this.material = materialIn;
        this.name = nameIn;
        this.repairStack = repairStackIn == null ? EMPTY : repairStackIn;
        this.textFormatting = textFormattingIn;
        this.isEnabled = isEnabled;
        this.negative = negative;
        this.ignite = ignite;
        this.effect = setToolTip(negative.getNegativeEffects(), negative.getNegativeEffectsAmplifier());
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    @Override
    public List<String> getApplyEffectNames() {
        return Arrays.asList(this.negative.getNegativeEffects());
    }

    @Override
    public List<Integer> getApplyAmplifierLevels() {
        return Arrays.stream(this.negative.getNegativeEffectsAmplifier()).boxed().collect(Collectors.toList());
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

    public ItemStack getRepairStack() {
        return repairStack;
    }

    public TextFormatting getTextFormatting() {
        return textFormatting;
    }

    public boolean areEffectsEnabled() {
        return this.negative.isEnabled();
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase target, @Nonnull EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        if (this.ignite.isEnabled()) {
            target.setFire(this.ignite.getFireSeconds());
        }
        if (this.areEffectsEnabled()) {
            IntStream.range(0, this.negative.getNegativeEffects().length).forEach(
                potionID -> addPotion(target, getPotion(this.getApplyEffectNames().get(potionID)), this.getApplyAmplifierLevels().get(potionID), BAD)
            );
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(List<String> tooltip) {
        ToolTipUtils.addSpecialInformation(tooltip, this.negative, this.ignite, getTextFormatting());
    }
}
