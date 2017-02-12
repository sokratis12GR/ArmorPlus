/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei;

import mezz.jei.api.*;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import net.minecraftforge.fml.common.Optional.Interface;
import net.minecraftforge.fml.common.Optional.Method;
import net.thedragonteam.armorplus.items.base.BaseItem;
import net.thedragonteam.armorplus.registry.ModItems;

import javax.annotation.Nonnull;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

@Interface(iface = "mezz.jei.api.IItemBlacklist", modid = "jei", striprefs = true)
@JEIPlugin
public class JEI implements IModPlugin {

    @Method(modid = "jei")
    public void register(@Nonnull IModRegistry registry) {
        IJeiHelpers helpers = registry.getJeiHelpers();
        IIngredientBlacklist blacklist = helpers.getIngredientBlacklist();
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.moddedCityItem));
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.jonBamsItem));
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.jonBamsItem, 1));
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.theDragonTeamItem));
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.twitchItem));
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.beamItem));
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.nbtItem));
        for (BaseItem item : ModItems.templates) blacklist.addIngredientToBlacklist(getItemStack(item));
    }

    @Method(modid = "jei")
    public void onRuntimeAvailable(@Nonnull IJeiRuntime jeiRuntime) {
    }

    @Method(modid = "jei")
    public void registerIngredients(@Nonnull IModIngredientRegistration registry) {
    }

    @Method(modid = "jei")
    @Override
    public void registerItemSubtypes(@Nonnull ISubtypeRegistry subtypeRegistry) {
    }
}