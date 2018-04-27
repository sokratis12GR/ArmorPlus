package net.thedragonteam.armorplus.api.properties;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class CombinedArmorProperties {

    private final String itemColor;
    private final ArmorAbilityProperties abilityProperties;
    private final ArmorProperties armorProperties;

    public CombinedArmorProperties(String itemColor, ArmorProperties armorProperties) {
        this(itemColor, new ArmorAbilityProperties(), armorProperties);
    }

    public CombinedArmorProperties(String itemColor, String ability, boolean cancelling, ArmorProperties armorProperties) {
        this(itemColor, cancelling ? new ArmorAbilityProperties(new AbilityCancellerProperty(ability)) : new ArmorAbilityProperties(ability), armorProperties);
    }

    public CombinedArmorProperties(String itemColor, String ability, int level, ArmorProperties armorProperties) {
        this(itemColor, new ArmorAbilityProperties(ability, level), armorProperties);
    }

    public CombinedArmorProperties(String itemColor, String cancelledAbility, String appliedAbility, ArmorProperties armorProperties) {
        this(itemColor, new ArmorAbilityProperties(cancelledAbility, appliedAbility), armorProperties);
    }

    public CombinedArmorProperties(String itemColor, String cancelledAbility, String appliedAbility, int level, ArmorProperties armorProperties) {
        this(itemColor, new ArmorAbilityProperties(cancelledAbility, appliedAbility, level), armorProperties);
    }

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
