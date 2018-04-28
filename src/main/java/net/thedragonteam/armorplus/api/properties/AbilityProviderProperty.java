package net.thedragonteam.armorplus.api.properties;

import net.thedragonteam.armorplus.api.properties.iface.IRemovable;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class AbilityProviderProperty implements IRemovable {

    private final String[] abilities;
    private final int[] abilityLevels;
    private final int[] abilityDuration;
    private final boolean enabled;

    public AbilityProviderProperty() {
        this("empty", 0, 0, false);
    }

    public AbilityProviderProperty(String ability) {
        this(ability, 0, 12, true);
    }

    public AbilityProviderProperty(String ability, int duration) {
        this(ability, 0, duration, true);
    }

    public AbilityProviderProperty(String ability, int abilityLevel, int duration) {
        this(ability, abilityLevel, duration, true);
    }

    public AbilityProviderProperty(String ability, int abilityLevel, int duration, boolean enabled) {
        this(new String[]{ability}, new int[]{abilityLevel}, new int[]{duration}, enabled);
    }

    public AbilityProviderProperty(String[] abilities, int[] abilityLevels, int[] abilityDuration, boolean enabled) {
        this.abilities = abilities;
        this.abilityLevels = abilityLevels;
        this.abilityDuration = abilityDuration;
        this.enabled = enabled;
    }

    public String[] getAbilities() {
        return abilities;
    }

    public int[] getAbilityLevels() {
        return abilityLevels;
    }

    public int[] getAbilityDuration() {
        return abilityDuration;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
