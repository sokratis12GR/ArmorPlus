/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.compat;

import mezz.jei.api.*;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;
import net.thedragonteam.armorplus.registry.ModItems;

import javax.annotation.Nonnull;

@Optional.Interface(iface = "mezz.jei.api.IItemBlacklist", modid = "JEI", striprefs = true)
@JEIPlugin
public class JEI implements IModPlugin {
    @Optional.Method(modid = "JEI")
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
        blacklist.addItemToBlacklist(new ItemStack(ModItems.devHelmet, 1));
        blacklist.addItemToBlacklist(new ItemStack(ModItems.devChestplate, 1));
        blacklist.addItemToBlacklist(new ItemStack(ModItems.devLeggings, 1));
        blacklist.addItemToBlacklist(new ItemStack(ModItems.devBoots, 1));
        blacklist.addItemToBlacklist(new ItemStack(ModItems.itemEnergyStorage, 1)); //TODO: Balance the damn energy storage item before release
    }

    @Optional.Method(modid = "JEI")
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
    }

    @Optional.Method(modid = "JEI")
    public void registerIngredients(IModIngredientRegistration registry) {
    }

    @Optional.Method(modid = "JEI")
    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
    }
}