/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus;

import com.mojang.authlib.GameProfile;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.api.network.DynamicNetwork;
import net.thedragonteam.armorplus.client.ClientTickHandler;
import net.thedragonteam.armorplus.client.gui.ARPTab;
import net.thedragonteam.armorplus.client.gui.GuiAdvancedArmorForge;
import net.thedragonteam.armorplus.client.gui.GuiArmorForge;
import net.thedragonteam.armorplus.client.gui.GuiArmorPlus;
import net.thedragonteam.armorplus.commands.CommandArmorPlus;
import net.thedragonteam.armorplus.common.ThreadGetData;
import net.thedragonteam.armorplus.compat.ICompatibility;
import net.thedragonteam.armorplus.container.ContainerAdvancedArmorForge;
import net.thedragonteam.armorplus.container.ContainerArmorForge;
import net.thedragonteam.armorplus.entity.ArmorPlusEntity;
import net.thedragonteam.armorplus.proxy.CommonProxy;
import net.thedragonteam.armorplus.registry.*;
import net.thedragonteam.armorplus.resources.GlobalEventsArmorPlus;
import net.thedragonteam.armorplus.tileentity.TileEntityAdvancedArmorForge;
import net.thedragonteam.armorplus.tileentity.TileEntityArmorForge;
import net.thedragonteam.armorplus.util.ARPAchievements;
import net.thedragonteam.core.TheDragonCore;
import net.thedragonteam.core.config.ModConfigProcessor;
import net.thedragonteam.core.config.ModFeatureParser;
import net.thedragonteam.core.util.LogHelper;
import net.thedragonteam.core.util.TextHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static net.minecraftforge.oredict.OreDictionary.registerOre;
import static net.thedragonteam.armorplus.client.gui.GuiHandler.*;

@Mod(modid = ArmorPlus.MODID, name = ArmorPlus.MODNAME, version = ArmorPlus.VERSION, dependencies = ArmorPlus.DEPEND, guiFactory = ArmorPlus.GUIFACTORY, canBeDeactivated = false, acceptedMinecraftVersions = "[1.10.2,1.11)", updateJSON = "https://sokratis12gr.tk/armorplus/armorplus.json")
public class ArmorPlus {

    public static final String MCVERSION = "1.10.2";
    // Updates every MAJOR change, never resets
    public static final int MAJOR = 6;
    // Updates every time the API change, resets on MAJOR changes
    public static final int API = 0;
    // Updates every time a new block, item or features is added or change, resets on MAJOR changes
    public static final int MINOR = 2;
    // Updates every time a new block, item or features is added or change, resets on MINOR changes
    public static final int PATCH = 0;
    // The ArmorPlus Version
    public static final String VERSION =
            ArmorPlus.MCVERSION + "-" + ArmorPlus.MAJOR + "." + ArmorPlus.API + "." + ArmorPlus.MINOR + "." + ArmorPlus.PATCH;
    public static final String MODID = "armorplus";
    public static final String MODNAME = "ArmorPlus";
    public static final String DEPEND = "required-after:thedragoncore@[" + TheDragonCore.VERSION + ",);";

    public static final String CLIENTPROXY = "net.thedragonteam.armorplus.proxy.ClientProxy";
    public static final String COMMONPROXY = "net.thedragonteam.armorplus.proxy.CommonProxy";
    public static final String GUIFACTORY = "net.thedragonteam.armorplus.client.gui.ConfigGuiFactory";

    @SidedProxy(clientSide = ArmorPlus.CLIENTPROXY, serverSide = ArmorPlus.COMMONPROXY)
    public static CommonProxy proxy;
    public static CreativeTabs TAB_ARMORPLUS = new ARPTab(CreativeTabs.getNextID(), ArmorPlus.MODID, "armors", 0);
    public static CreativeTabs TAB_ARMORPLUS_ITEMS = new ARPTab(CreativeTabs.getNextID(), ArmorPlus.MODID, "items", 1);
    public static CreativeTabs TAB_ARMORPLUS_BLOCKS = new ARPTab(CreativeTabs.getNextID(), ArmorPlus.MODID, "blocks", 2);
    public static CreativeTabs TAB_ARMORPLUS_WEAPONS = new ARPTab(CreativeTabs.getNextID(), ArmorPlus.MODID, "weapons", 3);
    public static ModFeatureParser featureParser = new ModFeatureParser(MODID, new CreativeTabs[]{TAB_ARMORPLUS, TAB_ARMORPLUS_ITEMS, TAB_ARMORPLUS_BLOCKS});
    public static ModConfigProcessor configProcessor = new ModConfigProcessor();
    public static Configuration configuration;
    public static Logger logger = LogManager.getLogger(ArmorPlus.MODNAME);
    @Mod.Instance(ArmorPlus.MODID)
    public static ArmorPlus instance;
    public static File configDir;
    public static File textureDir;
    /**
     * The GameProfile used by the dummy ArmorPlus player
     */
    public static GameProfile gameProfile = new GameProfile(UUID.nameUUIDFromBytes("armorplus.common".getBytes()), "[ArmorPlus]");
    /**
     * A list of the usernames of players who have donated to ArmorPlus.
     */
    public static List<String> donators = new ArrayList<String>();
    public ResourceLocation resourceLocation;
    public GuiHandler GuiHandler = new GuiHandler();
    @SuppressWarnings("unused")
    private ModItems items;
    @SuppressWarnings("unused")
    private ArmorPlusEntity entity;

    public ArmorPlus() {
        LogHelper.info("Welcoming Minecraft");
    }

    public static File getConfigDir() {
        return configDir;
    }

    public static File getloggerDir() {
        return textureDir;
    }

    @SideOnly(Side.CLIENT)
    @EventHandler
    public void initClient(FMLInitializationEvent event) {
//        try {
//            File capeFile = new File(resourceLocation.getResourcePath() + ".png");
//
//            if (capeFile.exists()) {
//                capeFile.delete();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        entity = new ArmorPlusEntity();

        ModCompatibility.loadCompat(ICompatibility.InitializationPhase.INIT);
        logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.load.init"));
        logger.info("Version " + ArmorPlus.VERSION + " initializing...");
        MinecraftForge.EVENT_BUS.register(new GlobalEventsArmorPlus());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, GuiHandler);

        //Register to receive subscribed events
        MinecraftForge.EVENT_BUS.register(this);

        //Get data from server
        new ThreadGetData();

        ARPAchievements.init();
        ModRecipes.init();

        //Ores
        registerOre("oreLavaCrystal", new ItemStack(ModBlocks.BLOCK_LAVA_CRYSTAL, 1));

        //Ingots
        registerOre("ingotSteel", new ItemStack(ModItems.STEEL_INGOT, 1));
        registerOre("ingotElectrical", new ItemStack(ModItems.ELECTRICAL_INGOT, 1));
        registerOre("blockSteel", new ItemStack(ModBlocks.STEEL_BLOCK, 1));
        registerOre("blockElectrical", new ItemStack(ModBlocks.ELECTRICAL_BLOCK, 1));
        registerOre("blockCompressedObsidian", new ItemStack(ModBlocks.COMPRESSED_OBSIDIAN, 1));
        registerOre("armorforge", new ItemStack(ModBlocks.ARMOR_FORGE, 1));
        registerOre("advarmorforge", new ItemStack(ModBlocks.ADVANCED_ARMOR_FORGE, 1));

        //Gems
        registerOre("gemLavaCrystal", new ItemStack(ModItems.LAVA_CRYSTAL, 1));

        //Materials
        registerOre("chainmail", new ItemStack(ModItems.CHAINMAIL, 1));
        registerOre("witherbone", new ItemStack(ModItems.WITHER_BONE, 1));
        registerOre("materialTheUltimate", new ItemStack(ModItems.THE_ULTIMATE_MATERIAL, 1));
        registerOre("materialReinforcing", new ItemStack(ModItems.REINFORCING_MATERIAL, 1));
        registerOre("scaleGuardian", new ItemStack(ModItems.GUARDIAN_SCALE, 1));
        registerOre("scaleEnderDragon", new ItemStack(ModItems.ENDER_DRAGON_SCALE, 1));
    }

    @SideOnly(Side.SERVER)
    @EventHandler
    public void initServer(FMLInitializationEvent event) {

        entity = new ArmorPlusEntity();

        //Initialization notification
        logger.info("Version " + ArmorPlus.VERSION + " initializing...");

        ModCompatibility.loadCompat(ICompatibility.InitializationPhase.INIT);
        logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.load.init"));
        MinecraftForge.EVENT_BUS.register(new GlobalEventsArmorPlus());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, GuiHandler);

        //Register to receive subscribed events
        MinecraftForge.EVENT_BUS.register(this);

        //Get data from server
        new ThreadGetData();

        ARPAchievements.init();
        ModRecipes.init();

        //Ores
        registerOre("oreLavaCrystal", new ItemStack(ModBlocks.BLOCK_LAVA_CRYSTAL, 1));

        //Ingots
        registerOre("ingotSteel", new ItemStack(ModItems.STEEL_INGOT, 1));
        registerOre("ingotElectrical", new ItemStack(ModItems.ELECTRICAL_INGOT, 1));
        registerOre("blockSteel", new ItemStack(ModBlocks.STEEL_BLOCK, 1));
        registerOre("blockElectrical", new ItemStack(ModBlocks.ELECTRICAL_BLOCK, 1));
        registerOre("blockCompressedObsidian", new ItemStack(ModBlocks.COMPRESSED_OBSIDIAN, 1));
        registerOre("armorforge", new ItemStack(ModBlocks.ARMOR_FORGE, 1));
        registerOre("advarmorforge", new ItemStack(ModBlocks.ADVANCED_ARMOR_FORGE, 1));

        //Gems
        registerOre("gemLavaCrystal", new ItemStack(ModItems.LAVA_CRYSTAL, 1));

        //Materials
        registerOre("chainmail", new ItemStack(ModItems.CHAINMAIL, 1));
        registerOre("witherbone", new ItemStack(ModItems.WITHER_BONE, 1));
        registerOre("materialTheUltimate", new ItemStack(ModItems.THE_ULTIMATE_MATERIAL, 1));
        registerOre("materialReinforcing", new ItemStack(ModItems.REINFORCING_MATERIAL, 1));
        registerOre("scaleGuardian", new ItemStack(ModItems.GUARDIAN_SCALE, 1));
        registerOre("scaleEnderDragon", new ItemStack(ModItems.ENDER_DRAGON_SCALE, 1));
        proxy.init(event);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModCompatibility.registerModCompat();
        ModCompatibility.loadCompat(ICompatibility.InitializationPhase.PRE_INIT);
        ModItems.init();
        ModBlocks.init();
        items = new ModItems();

        ModBlocks.register();
        logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.load.blocks"));
        MinecraftForge.EVENT_BUS.register(new MobDrops());

        configuration = new Configuration(event.getSuggestedConfigurationFile());
        configProcessor.processConfig(ARPConfig.class, configuration);

        featureParser.registerFeatures();

        logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.load.preInit"));
        configDir = new File(event.getModConfigurationDirectory() + "/" + ArmorPlus.MODID);
        configDir.mkdirs();
        net.thedragonteam.armorplus.util.Logger.init(new File(event.getModConfigurationDirectory().getPath()));
        proxy.registerRenderer();
        proxy.registerRenderers(this);
        proxy.registerWorldGenerators();
        proxy.registerTileEntities();
        proxy.preInit(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ModCompatibility.loadCompat(ICompatibility.InitializationPhase.POST_INIT);
        logger.info("Fake player readout: UUID = " + gameProfile.getId().toString() + ", name = " + gameProfile.getName());

        logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.load.postInit"));
        proxy.registerModels();
        proxy.postInit(event);

    }

    @SubscribeEvent
    public void onClientTickUpdate(DynamicNetwork.ClientTickUpdate event) {
        try {
            if (event.operation == 0) {
                ClientTickHandler.tickingSet.remove(event.network);
            } else {
                ClientTickHandler.tickingSet.add(event.network);
            }
        } catch (Exception e) {
            //Ignore
        }
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandArmorPlus());
    }

    public static class GuiHandler implements IGuiHandler {
        @Override
        public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
            if (ID == GUI_ARMORPLUS)
                return new GuiArmorPlus();
            if (ID == GUI_ARMOR_FORGE) {
                return new ContainerArmorForge(player.inventory, world, new BlockPos(x, y, z), (TileEntityArmorForge) world.getTileEntity(new BlockPos(x, y, z)));
            }
            if (ID == GUI_ADVANCED_ARMOR_FORGE) {
                return new ContainerAdvancedArmorForge(player.inventory, world, new BlockPos(x, y, z), (TileEntityAdvancedArmorForge) world.getTileEntity(new BlockPos(x, y, z)));
            }
            return null;
        }

        @Override
        public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
            if (ID == GUI_ARMORPLUS)
                return new GuiArmorPlus();
            if (ID == GUI_ARMOR_FORGE) {
                return new GuiArmorForge(player.inventory, world, new BlockPos(x, y, z), (TileEntityArmorForge) world.getTileEntity(new BlockPos(x, y, z)));
            }
            if (ID == GUI_ADVANCED_ARMOR_FORGE) {
                return new GuiAdvancedArmorForge(player.inventory, world, new BlockPos(x, y, z), (TileEntityAdvancedArmorForge) world.getTileEntity(new BlockPos(x, y, z)));
            }
            return null;
        }
    }
}
