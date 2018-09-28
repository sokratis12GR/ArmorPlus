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
import net.thedragonteam.armorplus.api.properties.iface.IEffectHolder;
import net.thedragonteam.armorplus.api.properties.iface.IRemovable;
import net.thedragonteam.armorplus.api.properties.iface.IRepairable;
import net.thedragonteam.armorplus.items.weapons.effects.Ignite;
import net.thedragonteam.armorplus.items.weapons.effects.Negative;
import net.thedragonteam.armorplus.items.weapons.effects.WeaponEffects;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.client.utils.ToolTipUtils;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;
import static net.minecraft.init.Blocks.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.*;
import static net.thedragonteam.armorplus.items.base.ItemSpecialBattleAxe.*;
import static net.thedragonteam.armorplus.registry.ModItems.itemLavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.armorplus.util.ArmorPlusItemUtils.applyNegativeEffect;
import static net.thedragonteam.armorplus.util.PotionUtils.localizePotion;
import static net.thedragonteam.armorplus.util.Utils.boxList;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public enum BattleAxes implements IEffectHolder, IRemovable, IRepairable {
    COAL(battleAxeCoalMaterial, "coal", getItemStack(COAL_BLOCK), coal, 8.0F, global_registry.enableCoalWeapons),
    LAPIS(battleAxeLapisMaterial, "lapis", getItemStack(LAPIS_BLOCK), lapis, 9.0F, global_registry.enableLapisWeapons),
    REDSTONE(battleAxeRedstoneMaterial, "redstone", getItemStack(REDSTONE_BLOCK), redstone, 9.0F, global_registry.enableRedstoneWeapons),
    EMERALD(battleAxeEmeraldMaterial, "emerald", getItemStack(EMERALD_BLOCK), emerald, 10.0F, global_registry.enableEmeraldWeapons),
    OBSIDIAN(battleAxeObsidianMaterial, "obsidian", getItemStack(ModBlocks.blockCompressedObsidian), obsidian, 10.5F, global_registry.enableObsidianWeapons),
    LAVA(battleAxeLavaMaterial, "infused_lava", getItemStack(itemLavaCrystal, 1), lava, 11.5F, global_registry.enableLavaWeapons),
    GUARDIAN(battleAxeGuardianMaterial, "guardian", getItemStack(materials, 1), guardian, 14.0F, global_registry.enableGuardianWeapons),
    SUPER_STAR(battleAxeSuperStarMaterial, "super_star", getItemStack(materials, 2), super_star, 15.0F, global_registry.enableSuperStarWeapons),
    ENDER_DRAGON(battleAxeEnderDragonMaterial, "ender_dragon", getItemStack(materials, 3), ender_dragon, 16.0F, global_registry.enableEnderDragonWeapons),
    // WOOD(, "wooden"),
    // STONE(, "stone"),
    // IRON(, "iron"),
    // GOLD(, "golden"),
    // DIAMOND(, "diamond")
    ;

    private final String name;
    private final Item.ToolMaterial material;
    private final ItemStack repairStack;
    private final TextFormatting textFormatting;
    private final boolean isEnabled;
    private final List<String> effect;
    private final float efficiency;
    private final Negative negative;
    private final Ignite ignite;

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, ItemStack repairStackIn, OriginMaterial material, float efficiencyIn, boolean[] isEnabled
    ) {
        this.material = materialIn;
        this.name = nameIn;
        this.repairStack = repairStackIn;
        this.textFormatting = getValueByName(material.weapons.itemNameColor);
        this.isEnabled = isEnabled[1];
        this.efficiency = efficiencyIn;
        WeaponEffects effects = new WeaponEffects(material);
        this.negative = effects.getNegative();
        this.ignite = effects.getIgnite();
        this.effect = setToolTip(negative.getEffects(), negative.getEffectLevels());
    }

    @Override
    public List<String> getApplyEffectNames() {
        return boxList(this.negative.getEffects());
    }

    @Override
    public List<Integer> getApplyEffectLevels() {
        return boxList(this.negative.getEffectLevels());
    }

    @Override
    public List<Integer> getApplyEffectDurations() {
        return boxList(this.negative.getEffectDurations());
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
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
