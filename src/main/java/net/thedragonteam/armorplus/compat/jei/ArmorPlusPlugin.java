/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional.Interface;

import java.util.Arrays;

import static net.thedragonteam.armorplus.APConfig.enableJEIIntegration;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.lavaInfuser;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.armorplus.util.TextUtils.formattedText;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

@Interface(iface = "mezz.jei.api.ingredients.IIngredientBlacklist", modid = "jei", striprefs = true)
@JEIPlugin
public class ArmorPlusPlugin implements IModPlugin {

    public static IJeiHelpers jeiHelper;

    @Override
    public void register(IModRegistry registry) {
        if (enableJEIIntegration) {
            jeiHelper = registry.getJeiHelpers();
            IIngredientBlacklist blacklist = jeiHelper.getIngredientBlacklist();

            registerDescriptions(registry);
            blackListIngredients(blacklist, moddedCityItem, jonBamsItem, getItemStack(jonBamsItem, 1), theDragonTeamItem, twitchItem, beamItem);
        }
    }

    private void blackListIngredients(IIngredientBlacklist blacklist, Object... stacks) {
        Arrays.stream(stacks).forEachOrdered(stack -> {
            if (stack instanceof ItemStack) {
                blacklist.addIngredientToBlacklist(stack);
            } else if (stack instanceof Block) {
                blacklist.addIngredientToBlacklist(getItemStack((Block) stack));
            } else if (stack instanceof Item) {
                blacklist.addIngredientToBlacklist(getItemStack((Item) stack));
            }
        });
    }

    private void registerDescriptions(IModRegistry registry) {
        this.registerDescriptions(registry, guardianScale, "armorplus.jei.guardian_scale.desc");
        this.registerDescriptions(registry, witherBone, "armorplus.jei.wither_bone.desc");
        this.registerDescriptions(registry, enderDragonScale, "armorplus.jei.ender_dragon_scale.desc");
        this.registerDescriptions(registry, getItemStack(lavaInfuser), "armorplus.jei.lava_infuser.desc");
    }

    private void registerDescriptions(IModRegistry registry, ItemStack ingredient, String translationKey) {
        registry.addIngredientInfo(ingredient, ItemStack.class, formattedText(translationKey));
    }
}
