package net.thedragonteam.armorplus.api.properties;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class AbilityCanceller {

    private final String[] cancelledAbilities;

    public AbilityCanceller() {
        this("empty");
    }

    public AbilityCanceller(String cancelledAbility) {
        this(new String[]{cancelledAbility});
    }

    public AbilityCanceller(String[] cancelledAbilities) {
        this.cancelledAbilities = cancelledAbilities;
    }

    public String[] getAbilities() {
        return cancelledAbilities;
    }
}
