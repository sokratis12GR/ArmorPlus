package com.sofodev.armorplus;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.config.ConfigHelper;
import com.sofodev.armorplus.datagen.DataGenerators;
import com.sofodev.armorplus.registry.ModEnchantmentEffects;
import com.sofodev.armorplus.registry.block.castle.BrickColor;
import com.sofodev.armorplus.registry.item.tool.properties.mace.APMaceMaterial;
import com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.registry.ModBlocks.BLOCKS;
import static com.sofodev.armorplus.registry.ModBlocks.TILE_ENTITIES;
import static com.sofodev.armorplus.registry.ModCreativeTabs.CREATIVE_MODE_TABS;
import static com.sofodev.armorplus.registry.ModEntities.ENTITY_TYPES;
import static com.sofodev.armorplus.registry.ModItems.ITEMS;
import static com.sofodev.armorplus.registry.ModPoI.POI_TYPES;
import static com.sofodev.armorplus.registry.ModPotions.EFFECTS;
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

    public ArmorPlus(IEventBus modBus, ModContainer container) {
        instance = this;
        modBus.addListener(
                GatherDataEvent.Client.class,
                DataGenerators::gatherClientData
        );

        modBus.addListener(
                GatherDataEvent.Server.class,
                DataGenerators::gatherServerData
        );

        ArmorPlus.config = ConfigHelper.register(
                container, ModConfig.Type.COMMON, ArmorPlusConfig::create);
        //Order of registration per https://gist.github.com/pupnewfster/ea38cf3744f23d6b65d67e6f279d5942


        BLOCKS.register(modBus);
        ENTITY_TYPES.register(modBus);
        POI_TYPES.register(modBus);
        PROFESSIONS.register(modBus);
        ITEMS.register(modBus);
        CREATIVE_MODE_TABS.register(modBus);

        TILE_ENTITIES.register(modBus);
        EFFECTS.register(modBus);

        ModEnchantmentEffects.ENTITY_ENCHANTMENT_EFFECTS.register(modBus);


    }

    public static ArmorPlus getInstance() {
        return instance;
    }

}
