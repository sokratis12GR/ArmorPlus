package net.thedragonteam.armorplus.api.properties;

import java.util.Collections;
import java.util.List;

public interface IEffectHolder {

    default List<String> getApplyEffectNames() {
        return Collections.emptyList();
    }

    default List<Integer> getApplyAmplifierLevels() {
        return Collections.emptyList();
    }

    default List<String> getRemoveEffectNames() {
        return Collections.emptyList();
    }
}
