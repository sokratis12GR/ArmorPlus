/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.tinkers;

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

import static net.thedragonteam.armorplus.compat.tinkers.modifiers.TiCTraits.lavacrystalic;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;
import static slimeknights.tconstruct.library.materials.MaterialTypes.HANDLE;
import static slimeknights.tconstruct.library.materials.MaterialTypes.HEAD;
import static slimeknights.tconstruct.library.utils.HarvestLevels.COBALT;
import static slimeknights.tconstruct.tools.TinkerMaterials.materials;
import static slimeknights.tconstruct.tools.TinkerTraits.*;

public final class TiCMaterials {

    public static final Material lavacrystal = mat("infused_lava_crystal", 0xb32d00);
    public static final Material compressed_obsidian = mat("compressed_obsidian", 0x005580);
    public static final Material lava_infused_obsidian = mat("lava_infused_obsidian", 0x631706);

    private static Material mat(String name, int color) {
        Material mat = new Material(name, color);
        materials.add(mat);
        return mat;
    }

    @SideOnly(Side.CLIENT)
    public static void registerMaterialRendering() {
        lavacrystal.setRenderInfo(0xb32d00).setTextureSuffix("contrast");
        lavacrystal.setRenderInfo(new MaterialRenderInfo.Default(0xb32d00));
        //Funny Ghost-like inversion
        //lavacrystal.setRenderInfo(new MaterialRenderInfo.InverseMultiColor(0xb32d00, 0xDD7A24, 0xF7A62A));
        lavacrystal.setRenderInfo(new MaterialRenderInfo.MultiColor(0xb32d00, 0xDD7A24, 0xF7A62A));

        compressed_obsidian.setRenderInfo(0x005580).setTextureSuffix("contrast");
        compressed_obsidian.setRenderInfo(new MaterialRenderInfo.Default(0x005580));
        compressed_obsidian.setRenderInfo(new MaterialRenderInfo.BlockTexture("armorplus:blocks/compressed_obsidian"));

        lava_infused_obsidian.setRenderInfo(0x631706).setTextureSuffix("contrast");
        lava_infused_obsidian.setRenderInfo(new MaterialRenderInfo.Default(0x631706));
        lava_infused_obsidian.setRenderInfo(new MaterialRenderInfo.BlockTexture("armorplus:blocks/lava_infused_obsidian"));
    }

    @Subscribe
    public void setupMaterials() {
        lavacrystal.setCraftable(true).setCastable(true);
        lavacrystal.addItem(getItemStack(ModItems.lavaCrystal, 1), 2, Material.VALUE_Ingot);
        lavacrystal.addItem("gemInfusedLavaCrystal", 2, Material.VALUE_Ingot);
        lavacrystal.addItem(ModBlocks.blockInfusedLavaCrystal, Material.VALUE_Block);
        lavacrystal.addItem("blockInfusedLavaCrystal", 2, Material.VALUE_Block);
        //   lavacrystal.setRepresentativeItem(getItemStack(ModItems.lavaCrystal, 1));
        lavacrystal.addTrait(lavacrystalic, HEAD).addTrait(flammable, HEAD).addTrait(autosmelt);

        compressed_obsidian.setCraftable(true).setCastable(true);
        compressed_obsidian.addItem(ModBlocks.compressedObsidian, Material.VALUE_Ingot);
        compressed_obsidian.addTrait(duritos);
        compressed_obsidian.setRepresentativeItem(ModBlocks.compressedObsidian);

        lava_infused_obsidian.setCraftable(true).setCastable(true);
        lava_infused_obsidian.addItem(ModBlocks.lavaInfusedObsidian, Material.VALUE_Ingot);
        lava_infused_obsidian.addTrait(duritos).addTrait(autosmelt, HANDLE);
        lava_infused_obsidian.setRepresentativeItem(ModBlocks.lavaInfusedObsidian);

        this.registerToolMaterialStats();
    }

    public void registerToolMaterialStats() {
        TinkerRegistry.addMaterialStats(lavacrystal,
                new HeadMaterialStats(110, 9.00f, 4.20f, COBALT),
                new HandleMaterialStats(1.00f, 100),
                new ExtraMaterialStats(100));
        TinkerRegistry.addMaterialStats(compressed_obsidian,
                new HeadMaterialStats(139, 7.07f, 2.20f, COBALT),
                new HandleMaterialStats(0.90f, 20),
                new ExtraMaterialStats(90));
        TinkerRegistry.addMaterialStats(lava_infused_obsidian,
                new HeadMaterialStats(153, 8.00f, 2.35f, COBALT),
                new HandleMaterialStats(1.00f, 80),
                new ExtraMaterialStats(100));
    }

    @Subscribe
    public void postInit() {
        if (TinkerTools.shard == null) return;

        for (Material material : TinkerRegistry.getAllMaterials()) {
            ItemStack shard = TinkerTools.shard.getItemstackWithMaterial(material);

            material.addRecipeMatch(new RecipeMatch.ItemCombination(Material.VALUE_Shard, shard));
            if (material.getShard() != null || !material.getShard().isEmpty()) material.setShard(shard);
        }
    }
}