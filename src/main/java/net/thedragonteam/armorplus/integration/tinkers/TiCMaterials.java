/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.integration.tinkers;

import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.registry.ModItems;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.client.MaterialRenderInfo;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.List;

import static net.thedragonteam.armorplus.integration.tinkers.TiCTraits.lavacrystalic;
import static slimeknights.tconstruct.library.materials.MaterialTypes.HANDLE;
import static slimeknights.tconstruct.library.materials.MaterialTypes.HEAD;
import static slimeknights.tconstruct.library.utils.HarvestLevels.COBALT;
import static slimeknights.tconstruct.tools.TinkerTraits.flammable;
import static slimeknights.tconstruct.tools.TinkerTraits.writable;

public final class TiCMaterials {

    public static final List<Material> materials = Lists.newArrayList();

    public static final Material lavacrystal = mat("lava_crystal", 0xb32d00);

    private static Material mat(String name, int color) {
        Material mat = new Material(name, color);
        materials.add(mat);
        return mat;
    }

    @SideOnly(Side.CLIENT)
    public static void registerMaterialRendering() {
        lavacrystal.setRenderInfo(new MaterialRenderInfo.Default(0xb32d00));
        lavacrystal.setRenderInfo(new MaterialRenderInfo.BlockTexture("armorplus:blocks/block_lava_crystal"));
    }

    @Subscribe
    public void setupMaterials() {

        // Lava Crystal
        lavacrystal.setCraftable(true);
        lavacrystal.setCastable(true);
        lavacrystal.setShard(ModItems.lavaCrystal);
        lavacrystal.addRecipeMatch(new RecipeMatch.ItemCombination(100, new ItemStack(ModItems.lavaCrystal, 1, 1), new ItemStack(Items.BRICK)));
        lavacrystal.addRecipeMatch(new RecipeMatch.ItemCombination(50, new ItemStack(ModItems.lavaCrystal, 1), new ItemStack(Items.BRICK)));
        lavacrystal.addTrait(lavacrystalic, HEAD);
        lavacrystal.addTrait(flammable);
        lavacrystal.addTrait(writable, HANDLE);
        registerToolMaterialStats();
    }

    public void registerToolMaterialStats() {
        // Stats:                                                   Durability, speed, attack, handle, extra, harvestlevel
        TinkerRegistry.addMaterialStats(lavacrystal,
                new HeadMaterialStats(110, 9.00f, 4.20f, COBALT),
                new HandleMaterialStats(1.00f, 100),
                new ExtraMaterialStats(100));
    }

    @Subscribe
    public void postInit(FMLPostInitializationEvent event) {
        if (TinkerTools.shard == null) {
            return;
        }

        // each material without a shard set gets the default one set
        for (Material material : TinkerRegistry.getAllMaterials()) {
            ItemStack shard = TinkerTools.shard.getItemstackWithMaterial(material);

            material.addRecipeMatch(new RecipeMatch.ItemCombination(Material.VALUE_Shard, shard));
            if (material.getShard() != null) {
                material.setShard(shard);
            }
        }
    }
}