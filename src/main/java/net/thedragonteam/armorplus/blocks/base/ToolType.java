package net.thedragonteam.armorplus.blocks.base;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;
import java.util.Locale;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public enum ToolType implements IStringSerializable {
    PICKAXE("Pickaxe"),
    AXE("Axe"),
    SHOVEL("Shovel");

    private final String name;

    ToolType(String nameIn) {
        name = nameIn;
    }

    public String getTool() {
        return name().toLowerCase(Locale.ROOT);
    }

    @Override
    @Nonnull
    public String getName() {
        return name;
    }
}