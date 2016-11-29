/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.compat.jei;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.registry.ModItems;

@JEIPlugin
public class ArmorPlusPlugin extends BlankModPlugin {

    public static IJeiHelpers jeiHelper;

    @Override
    public void register(IModRegistry registry) {
        jeiHelper = registry.getJeiHelpers();

        addDescription(registry, ModItems.materials, 1, "Used to craft the Guardian weapons, armor and the Ultimate Material. Dropped by the Elder Guardian and by Guardians");
        addDescription(registry, ModItems.materials, 2, "Used to craft the Super Star weapons, armor and the Ultimate Material. Dropped by The Wither Boss and by Wither Skeletons");
        addDescription(registry, ModItems.materials, 3, "Used to craft the Ender Dragon weapons, armor and the Ultimate Material. Dropped by the Ender Dragon and Ender Dragon Zombies");
        super.register(registry);
    }

    private void addDescription(IModRegistry registry, Item item, int meta, String description) {
        registry.addDescription(new ItemStack(item, 1, meta), description);
    }
}
