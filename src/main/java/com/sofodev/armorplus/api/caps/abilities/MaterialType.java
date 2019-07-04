/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.caps.abilities;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;

import static com.sofodev.armorplus.common.registry.items.armors.ArmorMaterials.*;
import static net.minecraft.item.ItemArmor.ArmorMaterial.LEATHER;

public class MaterialType {

    public static final MaterialType NONE = new MaterialType("none", 0, LEATHER);
    public static final MaterialType COAL = new MaterialType("coal", 1, COAL_ARMOR);
    public static final MaterialType LAPIS = new MaterialType("lapis", 1, LAPIS_ARMOR);
    public static final MaterialType REDSTONE = new MaterialType("redstone", 1, REDSTONE_ARMOR);
    public static final MaterialType EMERALD = new MaterialType("emerald", 2, EMERALD_ARMOR);
    public static final MaterialType OBSIDIAN = new MaterialType("obsidian", 2, OBSIDIAN_ARMOR);
    public static final MaterialType INFUSED_LAVA = new MaterialType("infused_lava", 2, LAVA_ARMOR);
    public static final MaterialType CHICKEN = new MaterialType("chicken", 1, CHICKEN_ARMOR);
    public static final MaterialType SLIME = new MaterialType("slime", 1, SLIME_ARMOR);
    public static final MaterialType GUARDIAN = new MaterialType("guardian", 3, GUARDIAN_ARMOR);
    public static final MaterialType SUPER_STAR = new MaterialType("super_star", 3, SUPER_STAR_ARMOR);
    public static final MaterialType ENDER_DRAGON = new MaterialType("ender_dragon", 3, ENDER_DRAGON_ARMOR);
    public static final MaterialType ARDITE = new MaterialType("ardite", 2, ARDITE_ARMOR);
    public static final MaterialType COBALT = new MaterialType("cobalt", 2, COBALT_ARMOR);
    public static final MaterialType KNIGHT_SLIME = new MaterialType("knight_slime", 2, KNIGHT_SLIME_ARMOR);
    public static final MaterialType PIG_IRON = new MaterialType("pig_iron", 2, PIG_IRON_ARMOR);
    public static final MaterialType MANYULLYN = new MaterialType("manyullyn", 3, MANYULLYN_ARMOR);
    public static final MaterialType ULTIMATE = new MaterialType("ultimate", 4, THE_ULTIMATE_ARMOR);

    private String name;
    private byte limit;
    private ToolMaterial toolMaterial;
    private ArmorMaterial armorMaterial;

    public MaterialType() {
        this(null, 0, null, null);
    }

    public MaterialType(String name, int limit, ArmorMaterial armorMaterial, ToolMaterial toolMaterial) {
        this.name = name;
        this.limit = (byte) limit;
        this.armorMaterial = armorMaterial;
        this.toolMaterial = toolMaterial;
    }

    public MaterialType(String name, int limit, ArmorMaterial armorMaterial) {
        this(name, limit, armorMaterial, null);
    }

    public MaterialType(String name, int limit, ToolMaterial toolMaterial) {
        this(name, limit, null, toolMaterial);
    }

    public String getName() {
        return name;
    }

    public byte getLimit() {
        return limit;
    }

    public ArmorMaterial getArmorMaterial() {
        return armorMaterial;
    }

    public ToolMaterial getToolMaterial() {
        return toolMaterial;
    }

    public MaterialType setName(String name) {
        this.name = name;
        return this;
    }

    public MaterialType setLimit(byte limit) {
        this.limit = limit;
        return this;
    }

    public MaterialType setArmorMaterial(ArmorMaterial armorMaterial) {
        this.armorMaterial = armorMaterial;
        return this;
    }

    public MaterialType setToolMaterial(ToolMaterial toolMaterial) {
        this.toolMaterial = toolMaterial;
        return this;
    }
}