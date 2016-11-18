/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.thedragonteam.armorplus.enchantments.FuriousEnchantment;
import net.thedragonteam.armorplus.enchantments.LifeStealEnchantment;

public class ModEnchantments {

    public static final Enchantment FURIOUS = new FuriousEnchantment();
    public static final Enchantment LIFESTEAL = new LifeStealEnchantment();

    public static void registerEnchantments() {
        GameRegistry.register(new LifeStealEnchantment());
        GameRegistry.register(new FuriousEnchantment());
    }
}
