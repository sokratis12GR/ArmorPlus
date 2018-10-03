package net.thedragonteam.armorplus.api.properties;

import net.thedragonteam.armorplus.api.properties.iface.IRemovable;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class AbilityProvider implements IRemovable {

    private final String[] abilities;
    private final int[] abilityLevels;
    private final int[] abilityDuration;
    private final boolean enabled;

    public AbilityProvider() {
        this("empty", -1, 0, false);
    }

    public AbilityProvider(String ability) {
        this(ability, 0, 12, true);
    }

    public AbilityProvider(String ability, int duration) {
        this(ability, 0, duration, true);
    }

    public AbilityProvider(String ability, int abilityLevel, int duration) {
        this(ability, abilityLevel, duration, true);
    }

    public AbilityProvider(String ability, int abilityLevel, int duration, boolean enabled) {
        this(new String[]{ability}, new int[]{abilityLevel}, new int[]{duration}, enabled);
    }

    public AbilityProvider(String[] abilities, int[] abilityLevels, int[] abilityDuration){
        this(abilities,abilityLevels, abilityDuration, true);
    }

    public AbilityProvider(String[] abilities, int[] abilityLevels, int[] abilityDuration, boolean enabled) {
        this.abilities = abilities;
        this.abilityLevels = abilityLevels;
        this.abilityDuration = abilityDuration;
        this.enabled = enabled;
    }

    public String[] getAbilities() {
        return abilities;
    }

    public int[] getLevels() {
        return abilityLevels;
    }

    public int[] getDurations() {
        return abilityDuration;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
