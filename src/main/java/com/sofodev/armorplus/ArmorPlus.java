package com.sofodev.armorplus;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.config.ConfigHelper;
import com.sofodev.armorplus.datagen.DataGenerators;
import net.minecraftforge.data.event.GatherDataEvent;
import com.sofodev.armorplus.registry.ModEnchantmentEffects;
import com.sofodev.armorplus.registry.block.castle.BrickColor;
import com.sofodev.armorplus.registry.item.tool.properties.mace.APMaceMaterial;
import com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.registry.ModBlocks.BLOCKS;
import static com.sofodev.armorplus.registry.ModBlocks.TILE_ENTITIES;
import static com.sofodev.armorplus.registry.ModCreativeTabs.CREATIVE_MODE_TABS;
import static com.sofodev.armorplus.registry.ModEntities.*;
import static com.sofodev.armorplus.registry.ModItems.ITEMS;
import static com.sofodev.armorplus.registry.ModPotions.EFFECTS;
import static com.sofodev.armorplus.registry.ModPoI.POI_TYPES;
import static com.sofodev.armorplus.registry.ModVillagers.PROFESSIONS;

@Mod(MODID)
public class ArmorPlus {

    public static final String MODID = "armorplus";
    public static final String MODNAME = "ArmorPlus";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    /**
     * Used as an "upper ground" variable, which sets the limit for the sets which use these materials.
     */
    public static final int AP_TOOL_MATERIAL_LENGTH = APToolProperties.values().length;
    public static final int AP_STONE_BRICKS_LENGTH = BrickColor.values().length;
    public static final int AP_MACE_MAT_LENGTH = APMaceMaterial.values().length;

    public static Map<Block, ItemLike> SMELTING_MAP = new HashMap<>();
    public static ArmorPlusConfig config;
    public static ArmorPlus instance;

    public ArmorPlus(FMLJavaModLoadingContext context) {
        instance = this;
        var modBusGroup = context.getModBusGroup();
        GatherDataEvent.getBus(modBusGroup).addListener(DataGenerators::gatherData);

        ArmorPlus.config = ConfigHelper.register(
                context, ModConfig.Type.COMMON, ArmorPlusConfig::create);
        //Order of registration per https://gist.github.com/pupnewfster/ea38cf3744f23d6b65d67e6f279d5942


        BLOCKS.register(modBusGroup);
        ENTITY_TYPES.register(modBusGroup);
        POI_TYPES.register(modBusGroup);
        PROFESSIONS.register(modBusGroup);
        ITEMS.register(modBusGroup);
        CREATIVE_MODE_TABS.register(modBusGroup);

        TILE_ENTITIES.register(modBusGroup);
        EFFECTS.register(modBusGroup);

        ModEnchantmentEffects.ENTITY_ENCHANTMENT_EFFECTS.register(modBusGroup);



    }

    public static ArmorPlus getInstance() {
        return instance;
    }

}
