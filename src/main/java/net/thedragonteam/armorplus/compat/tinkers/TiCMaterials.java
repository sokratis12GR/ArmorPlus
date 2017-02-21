/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.tinkers;

import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.registry.ModBlocks;
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

import static slimeknights.tconstruct.library.materials.MaterialTypes.HANDLE;
import static slimeknights.tconstruct.library.materials.MaterialTypes.HEAD;
import static slimeknights.tconstruct.library.utils.HarvestLevels.COBALT;
import static slimeknights.tconstruct.tools.TinkerTraits.*;

public final class TiCMaterials {

    public static final List<Material> materials = Lists.newArrayList();

    public static final Material lavacrystal = mat("lava_crystal", 0xb32d00);
    public static final Material compressed_obsidian = mat("compressed_obsidian", 0x005580);

    private static Material mat(String name, int color) {
        Material mat = new Material(name, color);
        materials.add(mat);
        return mat;
    }

    @SideOnly(Side.CLIENT)
    public static void registerMaterialRendering() {
        lavacrystal.setRenderInfo(0xb32d00).setTextureSuffix("contrast");
        lavacrystal.setRenderInfo(new MaterialRenderInfo.Default(0xb32d00));
        lavacrystal.setRenderInfo(new MaterialRenderInfo.BlockTexture("armorplus:blocks/block_lava_crystal"));

        compressed_obsidian.setRenderInfo(0x005580).setTextureSuffix("contrast");
        compressed_obsidian.setRenderInfo(new MaterialRenderInfo.Default(0x005580));
        compressed_obsidian.setRenderInfo(new MaterialRenderInfo.BlockTexture("armorplus:blocks/compressed_obsidian"));
    }

    @Subscribe
    public void setupMaterials() {
        // Lava Crystal
        lavacrystal.setCraftable(true);
        lavacrystal.setCastable(true);
        lavacrystal.addItem("gemChargedLavaCrystal", 1, Material.VALUE_Ingot);
        lavacrystal.addItem(new ItemStack(ModItems.lavaCrystal, 1, 1), 1, Material.VALUE_Ingot);
        lavacrystal.addItem(new ItemStack(ModItems.lavaCrystal, 1), 1, 72);
        lavacrystal.addTrait(TiCTraits.lavacrystalic, HEAD);
        lavacrystal.addTrait(flammable);
        lavacrystal.addTrait(autosmelt, HANDLE);
        lavacrystal.setRepresentativeItem(new ItemStack(ModItems.lavaCrystal, 1, 1));
        // Obsidian
        compressed_obsidian.setCraftable(true);
        compressed_obsidian.addTrait(duritos);
        compressed_obsidian.addItem(new ItemStack(ModBlocks.compressedObsidian, 1), 1, Material.VALUE_Ingot);
        compressed_obsidian.setRepresentativeItem(new ItemStack(ModBlocks.compressedObsidian, 1));

        registerToolMaterialStats();
    }

    public void registerToolMaterialStats() {
        // Stats:                                                   Durability, speed, attack, handle, extra, harvestlevel
        TinkerRegistry.addMaterialStats(lavacrystal,
                new HeadMaterialStats(110, 9.00f, 4.20f, COBALT),
                new HandleMaterialStats(1.00f, 100),
                new ExtraMaterialStats(100));
        TinkerRegistry.addMaterialStats(compressed_obsidian,
                new HeadMaterialStats(139, 7.07f, 4.20f, COBALT),
                new HandleMaterialStats(0.90f, 20),
                new ExtraMaterialStats(90));
    }

    @Subscribe
    public void postInit() {
        if (TinkerTools.shard == null) return;

        // each material without a shard set gets the default one set
        for (Material material : TinkerRegistry.getAllMaterials()) {
            ItemStack shard = TinkerTools.shard.getItemstackWithMaterial(material);

            material.addRecipeMatch(new RecipeMatch.ItemCombination(Material.VALUE_Shard, shard));
            if (material.getShard() != null) material.setShard(shard);
        }
    }
}