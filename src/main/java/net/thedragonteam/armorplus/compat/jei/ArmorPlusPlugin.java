/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional.Interface;
import net.thedragonteam.armorplus.registry.APBlocks;
import net.thedragonteam.armorplus.registry.APItems;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.util.TextUtils.formattedText;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

@Interface(iface = "mezz.jei.api.ingredients.IIngredientBlacklist", modid = "jei", striprefs = true)
@JEIPlugin
public class ArmorPlusPlugin implements IModPlugin {

    public static IJeiHelpers jeiHelper;

    @Override
    public void register(IModRegistry registry) {
        jeiHelper = registry.getJeiHelpers();
        IIngredientBlacklist blacklist = jeiHelper.getIngredientBlacklist();

        registerDescriptions(registry);
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.moddedCityItem));
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.jonBamsItem));
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.jonBamsItem, 1));
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.theDragonTeamItem));
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.twitchItem));
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.beamItem));
        blacklist.addIngredientToBlacklist(getItemStack(ModItems.nbtItem));

        //    for (Item item : ModItems.templates)
        //        blacklist.addIngredientToBlacklist(getItemStack(item))

        for (Block block : ModBlocks.enderBlocks)
            blacklist.addIngredientToBlacklist(getItemStack(block));
    }

    private void registerDescriptions(IModRegistry registry) {
        registry.addIngredientInfo(APItems.guardianScale, ItemStack.class, formattedText("armorplus.jei.guardian_scale.desc"));
        registry.addIngredientInfo(APItems.witherBone, ItemStack.class, formattedText("armorplus.jei.guardian_scale.desc"));
        registry.addIngredientInfo(APItems.enderDragonScale, ItemStack.class, formattedText("armorplus.jei.ender_dragon_scale.desc"));
        registry.addIngredientInfo(getItemStack(APBlocks.lavaInfuser), ItemStack.class, formattedText("armorplus.jei.lava_infuser.desc"));
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
    }
}
