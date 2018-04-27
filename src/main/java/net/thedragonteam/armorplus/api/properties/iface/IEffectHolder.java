package net.thedragonteam.armorplus.api.properties.iface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 * <p>
 * The IEffectHolder implementor will have the ability to create specific objects to contain effects and/or remove them.
 */
public interface IEffectHolder {

    /**
     * @return A list of the effects that will be applied to the entity
     */
    default List<String> getApplyEffectNames() {
        return new ArrayList<>();
    }

    /**
     * @return A list of the amplifier levels of the given effects provided by {@link #getApplyEffectNames()}
     */
    default List<Integer> getApplyAmplifierLevels() {
        return new ArrayList<>();
    }

    /**
     * @return A list of the effects that will be removed from the entity
     */
    default List<String> getRemoveEffectNames() {
        return new ArrayList<>();
    }
}
