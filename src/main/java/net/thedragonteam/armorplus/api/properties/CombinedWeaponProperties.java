package net.thedragonteam.armorplus.api.properties;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class CombinedWeaponProperties {

    private final String itemColor;
    private final AbilityProviderProperty abilityProperties;
    private final SetWeaponProperties weaponProperties;

    public CombinedWeaponProperties(String itemColor, AbilityProviderProperty abilityProperties, SetWeaponProperties weaponProperties) {
        this.itemColor = itemColor;
        this.abilityProperties = abilityProperties;
        this.weaponProperties = weaponProperties;
    }

    public String getItemColor() {
        return itemColor;
    }

    public AbilityProviderProperty getAbilityProperties() {
        return abilityProperties;
    }

    public SetWeaponProperties getWeaponProperties() {
        return weaponProperties;
    }
}
