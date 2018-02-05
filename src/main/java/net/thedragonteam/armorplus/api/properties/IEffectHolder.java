package net.thedragonteam.armorplus.api.properties;

import java.util.ArrayList;
import java.util.List;

public interface IEffectHolder {

    default List<String> getApplyEffectNames() {
        return new ArrayList<>();
    }

    default List<Integer> getApplyAmplifierLevels() {
        return new ArrayList<>();
    }

    default List<String> getRemoveEffectNames() {
        return new ArrayList<>();
    }
}
