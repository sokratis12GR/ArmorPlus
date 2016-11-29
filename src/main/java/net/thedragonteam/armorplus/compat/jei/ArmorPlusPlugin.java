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
import net.thedragonteam.thedragonlib.util.TextHelper;

@JEIPlugin
public class ArmorPlusPlugin extends BlankModPlugin {

    public static IJeiHelpers jeiHelper;

    @Override
    public void register(IModRegistry registry) {
        jeiHelper = registry.getJeiHelpers();

        addDescription(registry, ModItems.materials, 1, TextHelper.localize("armorplus.jei.guardian_scale.desc"));
        addDescription(registry, ModItems.materials, 2, TextHelper.localize("armorplus.jei.wither_bone.desc"));
        addDescription(registry, ModItems.materials, 3, TextHelper.localize("armorplus.jei.ender_dragon_scale.desc"));
        super.register(registry);
    }

    private void addDescription(IModRegistry registry, Item item, int meta, String description) {
        registry.addDescription(new ItemStack(item, 1, meta), description);
    }
}
