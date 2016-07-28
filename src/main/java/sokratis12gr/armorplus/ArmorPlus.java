package sokratis12gr.armorplus;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sokratis12gr.armorplus.armors.tconstruct.*;
import sokratis12gr.armorplus.client.gui.ARPTab;
import sokratis12gr.armorplus.client.gui.GuiArmorForge;
import sokratis12gr.armorplus.client.gui.GuiArmorPlus;
import sokratis12gr.armorplus.commands.CommandArmorPlus;
import sokratis12gr.armorplus.compat.ICompatibility;
import sokratis12gr.armorplus.container.ContainerArmorForge;
import sokratis12gr.armorplus.registry.*;
import sokratis12gr.armorplus.resources.GlobalEventsArmorPlus;
import sokratis12gr.armorplus.tileentity.TileEntityArmorForge;
import sokratis12gr.armorplus.util.ARPAchievements;
import sokratis12gr.armorplus.util.TextHelper;
import sokratis12gr.sokratiscore.SokratisCore;
import sokratis12gr.sokratiscore.config.ModConfigProcessor;
import sokratis12gr.sokratiscore.config.ModFeatureParser;
import sokratis12gr.sokratiscore.util.LogHelper;

import java.io.File;

import static net.minecraftforge.oredict.OreDictionary.registerOre;
import static sokratis12gr.armorplus.client.gui.GuiHandler.GUI_ARMORPLUS;
import static sokratis12gr.armorplus.client.gui.GuiHandler.GUI_ARMOR_FORGE;


@Mod(modid = ArmorPlus.MODID, name = ArmorPlus.MODNAME, version = ArmorPlus.VERSION, dependencies = ArmorPlus.DEPEND, canBeDeactivated = false, guiFactory = ArmorPlus.GUIFACTORY, updateJSON = "https://raw.githubusercontent.com/sokratis12GR/VersionUpdate/gh-pages/ArmorPlus.json")
public class ArmorPlus {

    public static final String MODID = "armorplus";
    public static final String VERSION = "1.10.2-5.0.3.0";
    public static final String MODNAME = "ArmorPlus";
    public static final String DEPEND = "required-after:sokratiscore@[" + SokratisCore.VERSION + ",);";
    public static final String CLIENTPROXY = "sokratis12gr.armorplus.ClientProxy";
    public static final String COMMONPROXY = "sokratis12gr.armorplus.CommonProxy";
    public static final String GUIFACTORY = "sokratis12gr.armorplus.client.gui.ConfigGuiFactory";

    @SidedProxy(clientSide = ArmorPlus.CLIENTPROXY, serverSide = ArmorPlus.COMMONPROXY)
    public static CommonProxy proxy;

    public static CreativeTabs TAB_ARMORPLUS = new ARPTab(CreativeTabs.getNextID(), ArmorPlus.MODID, "armors", 0);
    public static CreativeTabs TAB_ARMORPLUS_ITEMS = new ARPTab(CreativeTabs.getNextID(), ArmorPlus.MODID, "items", 1);
    public static CreativeTabs TAB_ARMORPLUS_BLOCKS = new ARPTab(CreativeTabs.getNextID(), ArmorPlus.MODID, "blocks", 2);

    public static ModFeatureParser featureParser = new ModFeatureParser(MODID, new CreativeTabs[]{TAB_ARMORPLUS, TAB_ARMORPLUS_ITEMS, TAB_ARMORPLUS_BLOCKS});
    public static ModConfigProcessor configProcessor = new ModConfigProcessor();

    public static Configuration configuration;

    public static Logger logger = LogManager.getLogger(ArmorPlus.MODNAME);

    @Mod.Instance(ArmorPlus.MODID)
    public static ArmorPlus instance;

    public static File configDir;

    public static File textureDir;

    public GuiHandler GuiHandler = new GuiHandler();

    public static File getConfigDir() {
        return configDir;
    }

    public static File getloggerDir() {
        return textureDir;
    }

    public ArmorPlus() {
        LogHelper.info("Welcoming Minecraft");
    }

    @SideOnly(Side.CLIENT)
    @EventHandler
    public void initClient(FMLInitializationEvent event) {

        ModCompatibility.loadCompat(ICompatibility.InitializationPhase.INIT);
        logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.load.init"));
        MinecraftForge.EVENT_BUS.register(new GlobalEventsArmorPlus());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, GuiHandler);

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

        ModCompatibility.loadCompat(ICompatibility.InitializationPhase.INIT);
        logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.load.init"));
        MinecraftForge.EVENT_BUS.register(new GlobalEventsArmorPlus());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, GuiHandler);

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
        ModBlocks.register();
        logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.load.blocks"));
        MinecraftForge.EVENT_BUS.register(new MobDrops());

        configuration = new Configuration(event.getSuggestedConfigurationFile());
        configProcessor.processConfig(ARPConfig.class, configuration);

        featureParser.registerFeatures();

        logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.load.preInit"));
        configDir = new File(event.getModConfigurationDirectory() + "/" + ArmorPlus.MODID);
        configDir.mkdirs();
        sokratis12gr.armorplus.util.Logger.init(new File(event.getModConfigurationDirectory().getPath()));
        proxy.registerRenderers(this);
        proxy.registerWorldGenerators();
        proxy.registerTileEntities();
        proxy.preInit(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ModCompatibility.loadCompat(ICompatibility.InitializationPhase.POST_INIT);
        logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.load.postInit"));
        proxy.postInit(event);
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
            return null;
        }

        @Override
        public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
            if (ID == GUI_ARMORPLUS)
                return new GuiArmorPlus();
            if (ID == GUI_ARMOR_FORGE) {
                return new GuiArmorForge(player.inventory, world, new BlockPos(x, y, z), (TileEntityArmorForge) world.getTileEntity(new BlockPos(x, y, z)));
            }
            return null;
        }
    }
}
