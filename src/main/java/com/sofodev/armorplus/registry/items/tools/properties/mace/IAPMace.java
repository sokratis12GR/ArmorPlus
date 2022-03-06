package com.sofodev.armorplus.registry.items.tools.properties.mace;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;

public interface IAPMace {

    /**
     * @return The IITemTier properties of the mace
     */
    Tier get();


    /**
     * @return The mace type: {Light, Normal, Heavy}.
     */
    APMaceType getType();

    /**
     * @return The name of the mace
     */
    String getName();

    /**
     * @return (in seconds) the period of time required to pass until the next possible effect application is allowed.
     */
    int cooldown();

    /**
     * @return The length of the "destruction" blow of the mace, i.e how many blocks further from the player's eyesight it can destroy.
     */
    int destructionRange();

    /**
     * @return True - If the mace would do damage in an aoe area on top of from the line of sight, the shapes can be found here {@link DestructionShape}.
     * False - If the mace would just deal linear destruction.
     */
    boolean hasAOEDestruction();

    /**
     * @return The shape of the AOE destruction, functionality depends on if {@link IAPMace#hasAOEDestruction()} is set to TRUE.
     */
    DestructionShape getShape();

    /**
     * @return The rarity of the tool, (i.e the color of its name)
     */
    Rarity getRarity();
}
