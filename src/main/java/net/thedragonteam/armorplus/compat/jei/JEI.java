/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei;

import mezz.jei.api.*;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional.Interface;
import net.minecraftforge.fml.common.Optional.Method;
import net.thedragonteam.armorplus.registry.ModItems;

import javax.annotation.Nonnull;

@Interface(iface = "mezz.jei.api.IItemBlacklist", modid = "jei", striprefs = true)
@JEIPlugin
public class JEI implements IModPlugin {

    @Method(modid = "jei")
    public void register(@Nonnull IModRegistry registry) {
        IJeiHelpers helpers = registry.getJeiHelpers();
        IItemBlacklist blacklist = helpers.getItemBlacklist();
        blacklist.addItemToBlacklist(new ItemStack(ModItems.moddedCityItem, 1));
        blacklist.addItemToBlacklist(new ItemStack(ModItems.jonBamsItem, 1));
        blacklist.addItemToBlacklist(new ItemStack(ModItems.jonBamsItem, 1, 1));
        blacklist.addItemToBlacklist(new ItemStack(ModItems.theDragonTeamItem, 1));
        blacklist.addItemToBlacklist(new ItemStack(ModItems.twitchItem, 1));
        blacklist.addItemToBlacklist(new ItemStack(ModItems.beamItem, 1));
        blacklist.addItemToBlacklist(new ItemStack(ModItems.nbtItem, 1));
    }

    @Method(modid = "jei")
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
    }

    @Method(modid = "jei")
    public void registerIngredients(IModIngredientRegistration registry) {
    }

    @Method(modid = "jei")
    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
    }
}