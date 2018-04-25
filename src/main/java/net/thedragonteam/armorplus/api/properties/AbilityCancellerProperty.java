package net.thedragonteam.armorplus.api.properties;

public class AbilityCancellerProperty {

    private final String[] cancelledAbilities;

    public AbilityCancellerProperty() {
        this("empty");
    }

    public AbilityCancellerProperty(String cancelledAbility) {
        this(new String[]{cancelledAbility});
    }

    public AbilityCancellerProperty(String[] cancelledAbilities) {
        this.cancelledAbilities = cancelledAbilities;
    }

    public String[] getCancelledAbilities() {
        return cancelledAbilities;
    }
}
