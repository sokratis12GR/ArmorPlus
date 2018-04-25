package net.thedragonteam.armorplus.api.properties;

import net.thedragonteam.armorplus.api.properties.iface.IRemovable;

public class AbilityProviderProperty implements IRemovable {

    private final String[] abilities;
    private final int[] abilityLevels;
    private final boolean enabled;

    public AbilityProviderProperty() {
        this("empty", 0, false);
    }

    public AbilityProviderProperty(String ability) {
        this(ability, 0, true);
    }

    public AbilityProviderProperty(String ability, int abilityLevel) {
        this(ability, abilityLevel, true);
    }

    public AbilityProviderProperty(String ability, int abilityLevel, boolean enabled) {
        this(new String[]{ability}, new int[]{abilityLevel}, enabled);
    }

    public AbilityProviderProperty(String[] abilities, int[] abilityLevels, boolean enabled) {
        this.abilities = abilities;
        this.abilityLevels = abilityLevels;
        this.enabled = enabled;
    }

    public String[] getAbilities() {
        return abilities;
    }

    public int[] getAbilityLevels() {
        return abilityLevels;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
