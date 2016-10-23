/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus;

import com.mojang.authlib.GameProfile;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.client.gui.ARPTab;
import net.thedragonteam.armorplus.client.gui.GuiHandler;
import net.thedragonteam.armorplus.commands.CommandArmorPlus;
import net.thedragonteam.armorplus.compat.ICompatibility;
import net.thedragonteam.armorplus.proxy.CommonProxy;
import net.thedragonteam.armorplus.registry.ModCompatibility;
import net.thedragonteam.thedragonlib.config.ModConfigProcessor;
import net.thedragonteam.thedragonlib.config.ModFeatureParser;
import net.thedragonteam.thedragonlib.util.LogHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

@Mod(modid = ArmorPlus.MODID, name = ArmorPlus.MODNAME, version = ArmorPlus.VERSION, dependencies = ArmorPlus.DEPEND, guiFactory = ArmorPlus.GUIFACTORY, canBeDeactivated = false, updateJSON = "http://fdn.redstone.tech/TheDragonTeam/armorplus/update.json")
public class ArmorPlus {

    public static final String MCVERSION = "1.10.2";
    // Updates every MAJOR change, never resets
    public static final int MAJOR = 8;
    // Updates every time the API change, resets on MAJOR changes
    public static final int API = 0;
    // Updates every time a new block, item or features is added or change, resets on MAJOR changes
    public static final int MINOR = 0;
    // Updates every time a bug is fixed or issue solved or very minor code changes, resets on MINOR changes
    public static final int PATCH = 0;
    // Updates every time a build is created, mostly used for dev versions and final versions for releases after for each Minor or Major update, resets on MINOR changes
    public static final int BUILD = 2;
    // The ArmorPlus Version
    public static final String VERSION =
            ArmorPlus.MCVERSION + "-" + ArmorPlus.MAJOR + "." + ArmorPlus.API + "." + ArmorPlus.MINOR + "." + ArmorPlus.PATCH + "." + ArmorPlus.BUILD + "";
    public static final String TESLA_VERSION = "1.2.1.49";
    public static final String LIB_VERSION = "1.10.2-1.0.3.0";
    public static final String MODID = "armorplus";
    public static final String MODNAME = "ArmorPlus";
    public static final String DEPEND = "required-after:thedragonlib@[" + ArmorPlus.LIB_VERSION + ",);" + "after:tesla@[" + ArmorPlus.TESLA_VERSION + ",);" + "after:mantle@[1.10.2-1.0.0,);" + "after:tconstruct@[1.10.2-2.5.2,);";
    public static final String GUIFACTORY = "net.thedragonteam.armorplus.client.gui.ConfigGuiFactory";
    public static final String CLIENTPROXY = "net.thedragonteam.armorplus.proxy.ClientProxy";
    public static final String SERVERPROXY = "net.thedragonteam.armorplus.proxy.ServerProxy";

    @SidedProxy(clientSide = ArmorPlus.CLIENTPROXY, serverSide = ArmorPlus.SERVERPROXY)
    public static CommonProxy proxy;

    public static CreativeTabs tabArmorplus = new ARPTab(CreativeTabs.getNextID(), ArmorPlus.MODID, ArmorPlus.MODID + "." + "armors", 0);
    public static CreativeTabs tabArmorplusItems = new ARPTab(CreativeTabs.getNextID(), ArmorPlus.MODID, ArmorPlus.MODID + "." + "items", 1);
    public static CreativeTabs tabArmorplusBlocks = new ARPTab(CreativeTabs.getNextID(), ArmorPlus.MODID, ArmorPlus.MODID + "." + "blocks", 2);
    public static CreativeTabs tabArmorplusWeapons = new ARPTab(CreativeTabs.getNextID(), ArmorPlus.MODID, ArmorPlus.MODID + "." + "weapons", 3);
    public static CreativeTabs tabArmorplusTesla = new ARPTab(CreativeTabs.getNextID(), ArmorPlus.MODID, ArmorPlus.MODID + "." + "tesla", 4);
    public static CreativeTabs tabArmorplusRF = new ARPTab(CreativeTabs.getNextID(), ArmorPlus.MODID, ArmorPlus.MODID + "." + "rf", 5);
    public static CreativeTabs tabArmorplusTinkers = new ARPTab(CreativeTabs.getNextID(), ArmorPlus.MODID, ArmorPlus.MODID + "." + "tinkers", 6);
    public static ModFeatureParser featureParser = new ModFeatureParser(ArmorPlus.MODID, new CreativeTabs[]{tabArmorplus, tabArmorplusItems, tabArmorplusBlocks, tabArmorplusWeapons, tabArmorplusTesla, tabArmorplusRF, tabArmorplusTinkers});
    public static ModConfigProcessor configProcessor = new ModConfigProcessor();
    public static Configuration configuration;

    private GuiHandler GuiHandler = new GuiHandler();
    public static Logger logger = LogManager.getLogger(ArmorPlus.MODNAME);
    @Mod.Instance(ArmorPlus.MODID)
    public static ArmorPlus instance;
    /**
     * The GameProfile used by the dummy ArmorPlus player
     */
    public static GameProfile gameProfile = new GameProfile(UUID.nameUUIDFromBytes("armorplus.common".getBytes()), "[ArmorPlus]");

    public ArmorPlus() {
        LogHelper.info("Welcoming Minecraft");
    }

    public static String getVERSION() {
        return VERSION;
    }

    public static boolean hasTesla() {
        return Loader.isModLoaded("tesla");
    }

    public static String getArmorPlusLocation() {
        return ArmorPlus.MODID + ":";
    }

    @SideOnly(Side.CLIENT)
    @EventHandler
    public void initClient(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(this, GuiHandler);

        proxy.init(event);
    }

    @SideOnly(Side.SERVER)
    @EventHandler
    public void initServer(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(this, GuiHandler);

        proxy.init(event);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configuration = new Configuration(event.getSuggestedConfigurationFile());
        configProcessor.processConfig(ARPConfig.class, configuration);
        featureParser.registerFeatures();
        proxy.preInit(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        logger.info("Fake player readout: UUID = " + gameProfile.getId().toString() + ", name = " + gameProfile.getName());
        proxy.postInit(event);
    }

    @EventHandler
    public void modMapping(FMLModIdMappingEvent event) {
        ModCompatibility.loadCompat(ICompatibility.InitializationPhase.MAPPING);
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandArmorPlus());
    }
}
