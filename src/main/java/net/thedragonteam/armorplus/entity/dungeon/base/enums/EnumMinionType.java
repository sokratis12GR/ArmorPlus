package net.thedragonteam.armorplus.entity.dungeon.base.enums;

public enum EnumMinionType {
    WARRIOR,
    ARCHER,
    KNIGHT,;

    EnumMinionType() {
    }

    public String getTypeName() {
        return this.name().toLowerCase();
    }
}