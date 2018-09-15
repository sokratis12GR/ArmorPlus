package net.thedragonteam.armorplus.api.properties;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class CombinedWeapon {

    private final String color;
    private final AbilityProvider abilityProvider;
    private final WeaponSet weaponSet;

    public CombinedWeapon(String color, WeaponSet weaponSet) {
        this(color, new AbilityProvider(), weaponSet);
    }

    public CombinedWeapon(String color, String abilityName, WeaponSet weaponSet) {
        this(color, new AbilityProvider(abilityName), weaponSet);
    }

    public CombinedWeapon(String color, String abilityName, int abilityLevel, WeaponSet weaponSet) {
        this(color, new AbilityProvider(abilityName, abilityLevel, 12), weaponSet);
    }

    public CombinedWeapon(String color, AbilityProvider abilityProvider, WeaponSet weaponSet) {
        this.color = color;
        this.abilityProvider = abilityProvider;
        this.weaponSet = weaponSet;
    }

    public String getColor() {
        return color;
    }

    public AbilityProvider getAbilityProvider() {
        return abilityProvider;
    }

    public WeaponSet getWeaponSet() {
        return weaponSet;
    }
}
