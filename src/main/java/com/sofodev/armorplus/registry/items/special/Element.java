package com.sofodev.armorplus.registry.items.special;


import java.util.Locale;

public enum Element {
    NONE(),
    EARTH("wind", "fire"),
    WIND("water", "earth"),
    WATER("fire", "wind"),
    FIRE("earth", "water"),
    //Separate
    LIGHT("dark", "light"),
    DARK("light", "dark"),
    ;

    private final String strongerAgainst;
    private final String weakerAgainst;

    //An empty element
    Element() {
        this("none", "none");
    }

    //Reference: https://tvtropes.org/pmwiki/pmwiki.php/Main/ElementsOfNature
    Element(String strongerAgainst, String weakerAgainst) {
        this.strongerAgainst = strongerAgainst;
        this.weakerAgainst = weakerAgainst;
    }

    public String getName() {
        return name().toLowerCase(Locale.ENGLISH);
    }

    public String getStrongerAgainst() {
        return strongerAgainst;
    }

    public String getWeakerAgainst() {
        return weakerAgainst;
    }

    public double getEffectiveness(Element defending) {
        if (this.getStrongerAgainst().equals(defending.getName())) {
            return 1.4;
        } else if (this.getWeakerAgainst().equals(defending.getName())) {
            return 0.6;
        } else if (this.equals(defending))
            return 0;
        return 1;
    }

    /**
     * This acts as the type effectiveness controller
     * - Table with visual values of this method: https://docs.google.com/spreadsheets/d/1a__dl4u62pJ8CBIh10gy_58_1yk9_WxIv5v5JPkInGc/edit?usp=sharing
     *
     * @param attacking The attacking element (affinity)
     * @param defending The defending element (affinity)
     * @return an effectiveness value based on if the attacking element is stronger, weaker, neutral or immune to the defending element.
     */
    public static double getEffectiveness(Element attacking, Element defending) {
        if (attacking.getStrongerAgainst().equals(defending.getName())) {
            return 1.4;
        } else if (attacking.getWeakerAgainst().equals(defending.getName())) {
            return 0.6;
        } else if (attacking.equals(defending))
            return 0;
        return 1;
    }
}
