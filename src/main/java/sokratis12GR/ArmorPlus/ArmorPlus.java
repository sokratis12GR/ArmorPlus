package sokratis12GR.ArmorPlus;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sokratis12GR.ArmorPlus.armors.*;
import sokratis12GR.ArmorPlus.client.gui.CreativeTabArmorPlus;
import sokratis12GR.ArmorPlus.resources.*;
import sokratis12GR.ArmorPlus.util.ARPAchievements;
import sokratis12GR.ArmorPlus.util.TextHelper;

import java.io.File;

@Mod(modid = ArmorPlus.MODID, name = ArmorPlus.MODNAME, version = ArmorPlus.VERSION, dependencies = ArmorPlus.DEPEND, guiFactory = ArmorPlus.GUIFACTORY, updateJSON = "https://raw.githubusercontent.com/sokratis12GR/VersionUpdate/gh-pages/ArmorPlus.json")
public class ArmorPlus {

    public static final String MODNAME = "ArmorPlus";
    public static final String MODID = "armorplus";
    public static final String CHANNEL = "ArmorPlus";
    public static final String DEPEND = "";
    public static final String VERSION = "1.7.15";
    public static final String CLIENTPROXY = "sokratis12GR.ArmorPlus.ClientProxy";
    public static final String COMMONPROXY = "sokratis12GR.ArmorPlus.CommonProxy";
    public static final String GUIFACTORY = "sokratis12GR.ArmorPlus.client.gui.ConfigGuiFactory";

    @SidedProxy(clientSide = ArmorPlus.CLIENTPROXY, serverSide = ArmorPlus.COMMONPROXY)
    public static CommonProxy proxy;

    public static CreativeTabs tabArmorPlus = new CreativeTabArmorPlus(ArmorPlus.MODID + ".creativeTab");
    public static Logger logger = LogManager.getLogger(ArmorPlus.MODNAME);

    @Instance(MODID)
    public static ArmorPlus instance;
    private static File configDir;

    public static File getConfigDir() {
        return configDir;
    }

    CoalArmor CoalArmor = new CoalArmor();
    LapisArmor LapisArmor = new LapisArmor();
    RedstoneArmor RedstoneArmor = new RedstoneArmor();
    EmeraldArmor EmeraldArmor = new EmeraldArmor();
    ObsidianArmor ObsidianArmor = new ObsidianArmor();
    LavaArmor LavaArmor = new LavaArmor();
    SuperStarArmor SuperStarArmor = new SuperStarArmor();
    EnderDragonArmor EnderDragonArmor = new EnderDragonArmor();
    GuardianArmor GuardianArmor = new GuardianArmor();

    @EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.load.init"));
        MinecraftForge.EVENT_BUS.register(new GlobalEventsArmorPlus());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        CoalArmor.load(event);
        LapisArmor.load(event);
        RedstoneArmor.load(event);
        EmeraldArmor.load(event);
        ObsidianArmor.load(event);
        LavaArmor.load(event);
        SuperStarArmor.load(event);
        EnderDragonArmor.load(event);
        GuardianArmor.load(event);
        ARPAchievements.init();
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ArmorPlusBlocks.init();
        ArmorPlusItems.init();
        ArmorPlusBlocks.register();
        MinecraftForge.EVENT_BUS.register(new MobDrops());
        CoalArmor.instance = ArmorPlus.instance;
        LapisArmor.instance = ArmorPlus.instance;
        RedstoneArmor.instance = ArmorPlus.instance;
        EmeraldArmor.instance = ArmorPlus.instance;
        ObsidianArmor.instance = ArmorPlus.instance;
        LavaArmor.instance = ArmorPlus.instance;
        SuperStarArmor.instance = ArmorPlus.instance;
        EnderDragonArmor.instance = ArmorPlus.instance;
        GuardianArmor.instance = ArmorPlus.instance;
        CoalArmor.preInit(event);
        LapisArmor.preInit(event);
        RedstoneArmor.preInit(event);
        EmeraldArmor.preInit(event);
        ObsidianArmor.preInit(event);
        LavaArmor.preInit(event);
        SuperStarArmor.preInit(event);
        EnderDragonArmor.preInit(event);
        GuardianArmor.preInit(event);
        logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.load.preInit"));
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        configDir = new File(event.getModConfigurationDirectory() + "/" + ArmorPlus.MODID);
        configDir.mkdirs();
        ConfigHandler.init(new File(configDir.getPath(), ArmorPlus.MODID + ".cfg"));
        proxy.registerRenderers(this);

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.load.postInit"));
    }

    public static class GuiHandler implements IGuiHandler {
        @Override
        public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
            return null;
        }

        @Override
        public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
            return null;
        }
    }

}
