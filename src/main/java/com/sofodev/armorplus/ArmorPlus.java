/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus;

import com.sofodev.armorplus.client.gui.APTab;
import com.sofodev.armorplus.client.gui.GuiHandler;
import com.sofodev.armorplus.packets.TrophyPacket;
import com.sofodev.armorplus.packets.TrophyPacketHandler;
import com.sofodev.armorplus.proxy.CommonProxy;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.thedragonteam.thedragonlib.config.ModFeatureParser;
import net.thedragonteam.thedragonlib.util.LogHelper;

import static net.minecraft.creativetab.CreativeTabs.getNextID;

/**
 * @author Sokratis Fotkatzikis
 **/
@Mod(modid = ArmorPlus.MODID,
    name = ArmorPlus.MODNAME,
    version = ArmorPlus.VERSION,
    dependencies = ArmorPlus.DEPEND,
    guiFactory = ArmorPlus.GUI_FACTORY,
    updateJSON = ArmorPlus.UPDATE_JSON,
    acceptedMinecraftVersions = "[1.12.2,1.13)"
)
public class ArmorPlus {

    /**
     * Updates every time the mod updates minecraft version,
     * Updates MAJOR with 1 after each version upgrade
     */
    public static final String MCVERSION = "1.12.2";
    /**
     * Updates every MAJOR change,
     * never resets
     */
    public static final int MAJOR = 11;
    /**
     * Updates every time a new block, item or features is added or change,
     * resets on MAJOR changes
     */
    public static final int MINOR = 16;
    /**
     * Updates every time a bug is fixed or issue solved or very minor code changes,
     * resets on MINOR changes
     */
    public static final int PATCH = 0;
    /**
     * Updates every time a build is created, mostly used for dev versions and
     * final versions for releases after for each Minor or Major update,
     * resets on MAJOR changes
     */
    public static final int BUILD = 45;
    /**
     * The ArmorPlus Version
     */
    public static final String VERSION = MCVERSION + "-" + MAJOR + "." + MINOR + "." + PATCH + "." + BUILD + "";
    public static final String LIB_VERSION = "1.12.2-5.3.0";
    public static final String FORGE_VERSION = "14.23.4.2705";
    public static final String MODID = "armorplus";
    public static final String MODNAME = "ArmorPlus";
    public static final String UPDATE_JSON = "https://cdn.tdt.pw/armorplus-updater.json";
    public static final String DEPEND = "required-after:forge@[" + FORGE_VERSION + ",);"
        + "required-after:thedragonlib@[" + LIB_VERSION + ",);"
        + "after:mantle;"
        + "after:tconstruct;";
    public static final String GUI_FACTORY = "com.sofodev.armorplus.proxy.ConfigGuiFactory";
    public static final String CLIENT_PROXY = "com.sofodev.armorplus.proxy.ClientProxy";
    public static final String SERVER_PROXY = "com.sofodev.armorplus.proxy.ServerProxy";

    public static final boolean DEV_ENVIRONMENT = false; //TODO: DON'T FORGET TO TURN OFF

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = SERVER_PROXY)
    public static CommonProxy proxy;

    public static CreativeTabs tabArmorplus = new APTab(getNextID(), MODID, Utils.setName("armors"), 0);
    public static CreativeTabs tabArmorplusItems = new APTab(getNextID(), MODID, Utils.setName("items"), 1);
    public static CreativeTabs tabArmorplusBlocks = new APTab(getNextID(), MODID, Utils.setName("blocks"), 2);
    public static CreativeTabs tabArmorplusWeapons = new APTab(getNextID(), MODID, Utils.setName("weapons"), 3);

    public static ModFeatureParser featureParser = new ModFeatureParser(MODID, new CreativeTabs[]{
        tabArmorplus, tabArmorplusItems, tabArmorplusBlocks, tabArmorplusWeapons
    });

    @Instance(MODID)
    public static ArmorPlus instance;
    public static GuiHandler guiHandler = new GuiHandler();

    public ArmorPlus() {
        LogHelper.info("Welcoming Minecraft");
    }

    public static String getVersion() {
        return VERSION;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        featureParser.registerFeatures();
        TrophyPacketHandler.INSTANCE.registerMessage(TrophyPacketHandler.class, TrophyPacket.class, 0, Side.SERVER);
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @EventHandler
    public void modMapping(FMLModIdMappingEvent event) {
        proxy.modMapping(event);
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        proxy.serverLoad(event);
    }

}