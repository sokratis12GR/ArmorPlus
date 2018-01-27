/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.enums;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.api.properties.IRemovable;
import net.thedragonteam.armorplus.api.properties.IRepairable;
import net.thedragonteam.armorplus.registry.ModBlocks;

import static net.minecraft.item.ItemStack.EMPTY;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public enum Bows implements IRepairable, IRemovable {
    COAL(coal.weapons.bow.durability, "coal", coal.weapons.bow.arrowBonusDamage,
        getItemStack(Blocks.COAL_BLOCK), getValueByName(coal.weapons.itemNameColor), coalBow, global_registry.enableCoalWeapons[2]
    ),
    LAPIS(lapis.weapons.bow.durability, "lapis", lapis.weapons.bow.arrowBonusDamage,
        getItemStack(Blocks.LAPIS_BLOCK), getValueByName(lapis.weapons.itemNameColor), lapisBow, global_registry.enableEmeraldWeapons[2]
    ),
    REDSTONE(redstone.weapons.bow.durability, "redstone", redstone.weapons.bow.arrowBonusDamage
        , getItemStack(Blocks.REDSTONE_BLOCK), getValueByName(redstone.weapons.itemNameColor), redstoneBow, global_registry.enableRedstoneWeapons[2]
    ),
    EMERALD(emerald.weapons.bow.durability, "emerald", emerald.weapons.bow.arrowBonusDamage,
        getItemStack(Blocks.EMERALD_BLOCK), getValueByName(emerald.weapons.itemNameColor), emeraldBow, global_registry.enableEmeraldWeapons[2]
    ),
    OBSIDIAN(obsidian.weapons.bow.durability, "obsidian", obsidian.weapons.bow.arrowBonusDamage,
        getItemStack(ModBlocks.compressedObsidian), getValueByName(obsidian.weapons.itemNameColor), obsidianBow, global_registry.enableObsidianWeapons[2]
    ),
    LAVA(lava.weapons.bow.durability, "infused_lava", lava.weapons.bow.arrowBonusDamage,
        getItemStack(lavaCrystal, 1), getValueByName(lava.weapons.itemNameColor), lavaBow, global_registry.enableLavaWeapons[2]
    ),
    GUARDIAN(guardian.weapons.bow.durability, "guardian", guardian.weapons.bow.arrowBonusDamage,
        getItemStack(materials, 1), getValueByName(guardian.weapons.itemNameColor), guardianBow, global_registry.enableGuardianWeapons[2]
    ),
    SUPER_STAR(super_star.weapons.bow.durability, "super_star", super_star.weapons.bow.arrowBonusDamage,
        getItemStack(materials, 2), getValueByName(super_star.weapons.itemNameColor), superStarBow, global_registry.enableSuperStarWeapons[2]
    ),
    ENDER_DRAGON(ender_dragon.weapons.bow.durability, "ender_dragon", ender_dragon.weapons.bow.arrowBonusDamage,
        getItemStack(materials, 3), getValueByName(ender_dragon.weapons.itemNameColor), enderDragonBow, global_registry.enableEnderDragonWeapons[2]
    );

    private final String name;

    private final int durability;

    private final double damage;

    private final boolean isEnabled;

    private final ItemStack repairStack;

    private final TextFormatting textFormatting;

    private final Item bowItem;

    Bows(int durabilityIn, String nameIn, double damageIn, ItemStack repairStackIn, TextFormatting textFormattingIn, Item bowItemIn, boolean isEnabled) {
        this.name = nameIn;
        this.durability = durabilityIn;
        this.damage = damageIn;
        this.isEnabled = isEnabled;
        if (repairStackIn == null) repairStackIn = EMPTY;
        this.repairStack = repairStackIn;
        this.textFormatting = textFormattingIn;
        this.bowItem = bowItemIn;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public double getDamage() {
        return this.damage;
    }

    public int getDurability() {
        return this.durability;
    }

    public Item getBowItem() {
        return this.bowItem;
    }

    public ItemStack getRepairStack() {
        return this.repairStack;
    }

    public TextFormatting getTextFormatting() {
        return this.textFormatting;
    }
}
