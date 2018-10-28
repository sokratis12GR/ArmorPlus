/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.tinkers;

import com.google.common.eventbus.Subscribe;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.compat.tinkers.modifiers.TiCModifiers;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.thedragonlib.util.LogHelper;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.client.MaterialRenderInfo;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.tools.AbstractToolPulse;
import slimeknights.tconstruct.tools.TinkerTools;

import static net.thedragonteam.armorplus.util.Utils.isNull;
import static net.thedragonteam.armorplus.util.Utils.setRL;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;
import static slimeknights.tconstruct.library.materials.MaterialTypes.HANDLE;
import static slimeknights.tconstruct.library.materials.MaterialTypes.HEAD;
import static slimeknights.tconstruct.library.utils.HarvestLevels.COBALT;
import static slimeknights.tconstruct.tools.TinkerMaterials.materials;
import static slimeknights.tconstruct.tools.TinkerTraits.*;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public final class TiCMaterials extends AbstractToolPulse {

    public static final Material INFUSED_LAVA_CRYSTAL = mat("infused_lava_crystal", 0xb32d00);
    public static final Material COMPRESSED_OBSIDIAN = mat("compressed_obsidian", 0x005580);
    public static final Material LAVA_INFUSED_OBSIDIAN = mat("lava_infused_obsidian", 0x631706);

    private static Material mat(String name, int color) {
        Material mat = new Material(name, color);
        materials.add(mat);
        return mat;
    }

    @SideOnly(Side.CLIENT)
    public static void registerMaterialRendering() {
        INFUSED_LAVA_CRYSTAL.setRenderInfo(0xb32d00).setTextureSuffix("contrast");
        INFUSED_LAVA_CRYSTAL.setRenderInfo(new MaterialRenderInfo.Default(0xb32d00));
        //Funny Ghost-like inversion
        //INFUSED_LAVA_CRYSTAL.setRenderInfo(new MaterialRenderInfo.InverseMultiColor(0xb32d00, 0xDD7A24, 0xF7A62A));
        INFUSED_LAVA_CRYSTAL.setRenderInfo(new MaterialRenderInfo.MultiColor(0xb32d00, 0xDD7A24, 0xF7A62A));

        COMPRESSED_OBSIDIAN.setRenderInfo(0x005580).setTextureSuffix("contrast");
        COMPRESSED_OBSIDIAN.setRenderInfo(new MaterialRenderInfo.Default(0x005580));
        COMPRESSED_OBSIDIAN.setRenderInfo(new MaterialRenderInfo.BlockTexture(setRL("blocks/compressed_obsidian")));

        LAVA_INFUSED_OBSIDIAN.setRenderInfo(0x631706).setTextureSuffix("contrast");
        LAVA_INFUSED_OBSIDIAN.setRenderInfo(new MaterialRenderInfo.Default(0x631706));
        LAVA_INFUSED_OBSIDIAN.setRenderInfo(new MaterialRenderInfo.BlockTexture(setRL("blocks/compressed_obsidian")));
    }

    @Subscribe
    public void setupMaterialStats(FMLPreInitializationEvent event) {
        this.registerToolMaterialStats();
    }

    @Subscribe
    public void setupMaterials(FMLInitializationEvent event) {
        INFUSED_LAVA_CRYSTAL.addItem(getItemStack(ModItems.itemLavaCrystal, 1), 2, Material.VALUE_Ingot);
        INFUSED_LAVA_CRYSTAL.addItem("gemInfusedLavaCrystal", 2, Material.VALUE_Ingot);
        INFUSED_LAVA_CRYSTAL.addItem(ModBlocks.blockInfusedLavaCrystal, Material.VALUE_Block);
        INFUSED_LAVA_CRYSTAL.addItem("blockInfusedLavaCrystal", 2, Material.VALUE_Block);
        INFUSED_LAVA_CRYSTAL.addTrait((ITrait) TiCModifiers.infusedLavaCrystalModifier, HEAD).addTrait(flammable, HEAD).addTrait(autosmelt);

        COMPRESSED_OBSIDIAN.addItem(ModBlocks.blockCompressedObsidian, Material.VALUE_Ingot);
        COMPRESSED_OBSIDIAN.addTrait(duritos);

        LAVA_INFUSED_OBSIDIAN.addItem(ModBlocks.blockLavaInfusedObsidian, Material.VALUE_Ingot);
        LAVA_INFUSED_OBSIDIAN.addTrait(duritos).addTrait(autosmelt, HANDLE);


        this.setupMaterialBasics(INFUSED_LAVA_CRYSTAL);
        this.setupMaterialBasics(COMPRESSED_OBSIDIAN);
        this.setupMaterialBasics(LAVA_INFUSED_OBSIDIAN);

        this.setRepresentativeItems(INFUSED_LAVA_CRYSTAL, ModBlocks.blockInfusedLavaCrystal);
        this.setRepresentativeItems(COMPRESSED_OBSIDIAN, ModBlocks.blockCompressedObsidian);
        this.setRepresentativeItems(LAVA_INFUSED_OBSIDIAN, ModBlocks.blockLavaInfusedObsidian);
    }

    private void setupMaterialBasics(Material material) {
        material.setCraftable(true);
    }

    private void setRepresentativeItems(Material material, Object representative) {
        if (representative instanceof ItemStack || representative instanceof Item || representative instanceof Block) {
            material.setRepresentativeItem(getItemStack(representative));
        } else {
            LogHelper.info("couldn't determine the type of " + representative);
        }
    }

    public void registerToolMaterialStats() {
        TinkerRegistry.addMaterialStats(INFUSED_LAVA_CRYSTAL,
                new HeadMaterialStats(110, 9.00f, 4.20f, COBALT),
                new HandleMaterialStats(1.00f, 100),
                new ExtraMaterialStats(100));
        TinkerRegistry.addMaterialStats(COMPRESSED_OBSIDIAN,
                new HeadMaterialStats(139, 7.07f, 2.20f, COBALT),
                new HandleMaterialStats(0.90f, 20),
                new ExtraMaterialStats(90));
        TinkerRegistry.addMaterialStats(LAVA_INFUSED_OBSIDIAN,
                new HeadMaterialStats(153, 8.00f, 2.35f, COBALT),
                new HandleMaterialStats(1.00f, 80),
                new ExtraMaterialStats(100));
    }

    @Override
    @Subscribe
    public void postInit(FMLPostInitializationEvent event) {
        if (isNull(TinkerTools.shard)) return;

        TinkerRegistry.getAllMaterials().forEach(material -> {
            ItemStack shard = TinkerTools.shard.getItemstackWithMaterial(material);
            material.addRecipeMatch(new RecipeMatch.ItemCombination(Material.VALUE_Shard, shard));
            if (!material.getShard().isEmpty()) material.setShard(shard);
        });
    }
}