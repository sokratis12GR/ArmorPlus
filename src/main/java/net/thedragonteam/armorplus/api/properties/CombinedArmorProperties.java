package net.thedragonteam.armorplus.api.properties;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class CombinedArmorProperties {

    private final String itemColor;
    private final ArmorAbilityProperties abilityProperties;
    private final ArmorProperties armorProperties;

    public CombinedArmorProperties(String itemColor, ArmorAbilityProperties abilityProperties, ArmorProperties armorProperties) {
        this.itemColor = itemColor;
        this.abilityProperties = abilityProperties;
        this.armorProperties = armorProperties;
    }

    public String getItemColor() {
        return itemColor;
    }

    public ArmorAbilityProperties getAbilityProperties() {
        return abilityProperties;
    }

    public ArmorProperties getArmorProperties() {
        return armorProperties;
    }
}
