package net.thedragonteam.armorplus.api.properties;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class ArmorAbilityProperties {

    private final AbilityCancellerProperty abilityCanceller;
    private final AbilityProviderProperty abilityProvider;

    public ArmorAbilityProperties() {
        this(new AbilityProviderProperty());
    }

    public ArmorAbilityProperties(String ability) {
        this(new AbilityProviderProperty(ability));
    }

    public ArmorAbilityProperties(String ability, int abilityLevel) {
        this(new AbilityProviderProperty(ability, abilityLevel));
    }

    public ArmorAbilityProperties(String cancelledAbility, String appliedAbility) {
        this(new AbilityCancellerProperty(cancelledAbility), new AbilityProviderProperty(appliedAbility));
    }

    public ArmorAbilityProperties(String cancelledAbility, String appliedAbility, int abilityLevel) {
        this(new AbilityCancellerProperty(cancelledAbility), new AbilityProviderProperty(appliedAbility, abilityLevel));
    }

    public ArmorAbilityProperties(AbilityProviderProperty abilityProvider) {
        this(new AbilityCancellerProperty(), abilityProvider);
    }

    public ArmorAbilityProperties(AbilityCancellerProperty abilityCanceller) {
        this(abilityCanceller, new AbilityProviderProperty());
    }

    public ArmorAbilityProperties(AbilityCancellerProperty abilityCanceller, AbilityProviderProperty abilityProvider) {
        this.abilityCanceller = abilityCanceller;
        this.abilityProvider = abilityProvider;
    }

    public AbilityCancellerProperty getAbilityCanceller() {
        return abilityCanceller;
    }

    public AbilityProviderProperty getAbilityProvider() {
        return abilityProvider;
    }
}
