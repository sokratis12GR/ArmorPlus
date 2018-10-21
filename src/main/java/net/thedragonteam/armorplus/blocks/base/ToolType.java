package net.thedragonteam.armorplus.blocks.base;

import java.util.Locale;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public enum ToolType {
    PICKAXE,
    AXE,
    SHOVEL;

    ToolType() {
    }

    public String getTool() {
        return name().toLowerCase(Locale.ROOT);
    }
}