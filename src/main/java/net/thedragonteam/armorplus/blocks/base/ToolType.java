package net.thedragonteam.armorplus.blocks.base;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

/**
 * ArmorPlus - Kotlin created by sokratis12GR
 * - TheDragonTeam
 */
public enum ToolType implements IStringSerializable {
    PICKAXE("pickaxe", "Pickaxe"),
    AXE("axe", "Axe"),
    SHOVEL("shovel", "Shovel");

    private final String tool;

    private final String name;

    ToolType(String toolIn, String nameIn) {
        tool = toolIn;
        name = nameIn;
    }

    public String getTool() {
        return tool;
    }

    @Override
    @Nonnull
    public String getName() {
        return name;
    }
}