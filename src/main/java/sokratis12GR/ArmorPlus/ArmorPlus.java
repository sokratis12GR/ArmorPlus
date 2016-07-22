package sokratis12gr.armorplus;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sokratis12gr.armorplus.armors.origin.*;
import sokratis12gr.armorplus.armors.reinforced.RCArmor;
import sokratis12gr.armorplus.armors.reinforced.RDArmor;
import sokratis12gr.armorplus.armors.reinforced.RGArmor;
import sokratis12gr.armorplus.armors.reinforced.RIArmor;
import sokratis12gr.armorplus.armors.special.EnderDragonArmor;
import sokratis12gr.armorplus.armors.special.GuardianArmor;
import sokratis12gr.armorplus.armors.special.SuperStarArmor;
import sokratis12gr.armorplus.armors.special.TheUltimateArmor;
import sokratis12gr.armorplus.armors.special.mob.ChickenArmor;
import sokratis12gr.armorplus.armors.special.mob.SlimeArmor;
import sokratis12gr.armorplus.armors.tconstruct.*;
import sokratis12gr.armorplus.armors.v2.ElectricalArmor;
import sokratis12gr.armorplus.armors.v2.SteelArmor;
import sokratis12gr.armorplus.client.gui.*;
import sokratis12gr.armorplus.commands.CommandArmorPlus;
import sokratis12gr.armorplus.compat.ICompatibility;
import sokratis12gr.armorplus.container.ContainerArmorForge;
import sokratis12gr.armorplus.registry.*;
import sokratis12gr.armorplus.resources.ConfigHandler;
import sokratis12gr.armorplus.resources.GlobalEventsArmorPlus;
import sokratis12gr.armorplus.tileentity.TileEntityArmorForge;
import sokratis12gr.armorplus.util.ARPAchievements;
import sokratis12gr.armorplus.util.TextHelper;

import java.io.File;

import static net.minecraftforge.oredict.OreDictionary.registerOre;
import static sokratis12gr.armorplus.client.gui.GuiHandler.GUI_ARMORPLUS;
import static sokratis12gr.armorplus.client.gui.GuiHandler.GUI_ARMOR_FORGE;

/*import sokratis12gr.armorplus.resources.ClientEvents;*/

@Mod(modid = ArmorPlus.MODID, name = ArmorPlus.MODNAME, version = ArmorPlus.VERSION, dependencies = ArmorPlus.DEPEND, acceptedMinecraftVersions = "[1.9.4,1.10)", guiFactory = ArmorPlus.GUIFACTORY, updateJSON = "https://raw.githubusercontent.com/sokratis12GR/VersionUpdate/gh-pages/ArmorPlus.json")
public class ArmorPlus {

    public static final String MODID = "armorplus";
    public static final String VERSION = "1.9.4-4.1.7.3";
    public static final String MODNAME = "ArmorPlus";
    public static final String DEPEND = "";
    public static final String CLIENTPROXY = "sokratis12gr.armorplus.ClientProxy";
    public static final String COMMONPROXY = "sokratis12gr.armorplus.CommonProxy";
    public static final String GUIFACTORY = "sokratis12gr.armorplus.client.gui.ConfigGuiFactory";

    @SidedProxy(clientSide = ArmorPlus.CLIENTPROXY, serverSide = ArmorPlus.COMMONPROXY)
    public static CommonProxy proxy;

    public static CreativeTabs TAB_ARMORPLUS = new CreativeTabArmorPlus(ArmorPlus.MODID + ".creativeTab");
    public static CreativeTabs TAB_ARMORPLUS_ITEMS = new CreativeTabArmorPlusItems(ArmorPlus.MODID + ".creativeTabItems");
    public static CreativeTabs TAB_ARMORPLUS_BLOCKS = new CreativeTabArmorPlusBlocks(ArmorPlus.MODID + ".creativeTabBlocks");
    public static Logger logger = LogManager.getLogger(ArmorPlus.MODNAME);
    @Instance(MODID)
    public static ArmorPlus instance;
    public static File configDir;
    public static File textureDir;
    public GuiHandler GuiHandler = new GuiHandler();
    CoalArmor COAL_ARMOR = new CoalArmor();
    LapisArmor LAPIS_ARMOR = new LapisArmor();
    RedstoneArmor REDSTONE_ARMOR = new RedstoneArmor();
    EmeraldArmor EMERALD_ARMOR = new EmeraldArmor();
    ObsidianArmor OBSIDIAN_ARMOR = new ObsidianArmor();
    LavaArmor LAVA_ARMOR = new LavaArmor();
    SuperStarArmor SUPER_STAR_ARMOR = new SuperStarArmor();
    EnderDragonArmor ENDER_DRAGON_ARMOR = new EnderDragonArmor();
    GuardianArmor GUARDIAN_ARMOR = new GuardianArmor();
    TheUltimateArmor THE_ULTIMATE_ARMOR = new TheUltimateArmor();
    RIArmor RI_ARMOR = new RIArmor();
    RGArmor RG_ARMOR = new RGArmor();
    RDArmor RD_ARMOR = new RDArmor();
    RCArmor RC_ARMOR = new RCArmor();
    ChickenArmor CHICKEN_ARMOR = new ChickenArmor();
    SlimeArmor SLIME_ARMOR = new SlimeArmor();
    /**
     * v2
     */
    SteelArmor STEEL_ARMOR = new SteelArmor();
    ElectricalArmor ELECTRICAL_ARMOR = new ElectricalArmor();
    /**
     * Tinkers' Construct Armors
     */
    CobaltArmor COBALT_ARMOR = new CobaltArmor();
    ArditeArmor ARDITE_ARMOR = new ArditeArmor();
    ManyullynArmor MANYULLYN_ARMOR = new ManyullynArmor();
    PigIronArmor PIG_IRON_ARMOR = new PigIronArmor();
    KnightSlimeArmor KNIGHT_SLIME_ARMOR = new KnightSlimeArmor();

    public static File getConfigDir() {
        return configDir;
    }

    public static File getloggerDir() {
        return textureDir;
    }
    // player.getHealth()

    @SideOnly(Side.CLIENT)
    @EventHandler
    public void initClient(FMLInitializationEvent event) {

        ModCompatibility.loadCompat(ICompatibility.InitializationPhase.INIT);
        logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.load.init"));
        MinecraftForge.EVENT_BUS.register(new GlobalEventsArmorPlus());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, GuiHandler);
        /*MinecraftForge.EVENT_BUS.register(new ClientEvents());*/
        COAL_ARMOR.load(event);
        LAPIS_ARMOR.load(event);
        REDSTONE_ARMOR.load(event);
        EMERALD_ARMOR.load(event);
        OBSIDIAN_ARMOR.load(event);
        LAVA_ARMOR.load(event);
        SUPER_STAR_ARMOR.load(event);
        ENDER_DRAGON_ARMOR.load(event);
        GUARDIAN_ARMOR.load(event);
        THE_ULTIMATE_ARMOR.load(event);
        RI_ARMOR.load(event);
        RG_ARMOR.load(event);
        RD_ARMOR.load(event);
        RC_ARMOR.load(event);
        CHICKEN_ARMOR.load(event);
        SLIME_ARMOR.load(event);

        /** v2 */
        STEEL_ARMOR.load(event);
        ELECTRICAL_ARMOR.load(event);

        /** Tinkers' Construct Armors */
        COBALT_ARMOR.load(event);
        ARDITE_ARMOR.load(event);
        MANYULLYN_ARMOR.load(event);
        PIG_IRON_ARMOR.load(event);
        KNIGHT_SLIME_ARMOR.load(event);

        ARPAchievements.init();
        ModRecipes.init();

        registerOre("oreLavaCrystal", new ItemStack(ModBlocks.BLOCK_LAVA_CRYSTAL, 1));
        registerOre("ingotSteel", new ItemStack(ModItems.STEEL_INGOT, 1));
        registerOre("oreSteel", new ItemStack(ModBlocks.STEEL_ORE, 1));
        registerOre("blockSteel", new ItemStack(ModBlocks.STEEL_BLOCK, 1));
        registerOre("ingotElectrical", new ItemStack(ModItems.ELECTRICAL_INGOT, 1));
        registerOre("blockElectrical", new ItemStack(ModBlocks.ELECTRICAL_BLOCK, 1));
        registerOre("gemLavaCrystal", new ItemStack(ModItems.LAVA_CRYSTAL, 1));
    }

    @SideOnly(Side.SERVER)
    @EventHandler
    public void initServer(FMLInitializationEvent event) {

        ModCompatibility.loadCompat(ICompatibility.InitializationPhase.INIT);
        logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.load.init"));
        MinecraftForge.EVENT_BUS.register(new GlobalEventsArmorPlus());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, GuiHandler);
        COAL_ARMOR.load(event);
        LAPIS_ARMOR.load(event);
        REDSTONE_ARMOR.load(event);
        EMERALD_ARMOR.load(event);
        OBSIDIAN_ARMOR.load(event);
        LAVA_ARMOR.load(event);
        SUPER_STAR_ARMOR.load(event);
        ENDER_DRAGON_ARMOR.load(event);
        GUARDIAN_ARMOR.load(event);
        THE_ULTIMATE_ARMOR.load(event);
        RI_ARMOR.load(event);
        RG_ARMOR.load(event);
        RD_ARMOR.load(event);
        RC_ARMOR.load(event);
        CHICKEN_ARMOR.load(event);
        SLIME_ARMOR.load(event);

        /** v2 */
        STEEL_ARMOR.load(event);
        ELECTRICAL_ARMOR.load(event);

        /** Tinkers' Construct Armors */
        COBALT_ARMOR.load(event);
        ARDITE_ARMOR.load(event);
        MANYULLYN_ARMOR.load(event);
        PIG_IRON_ARMOR.load(event);
        KNIGHT_SLIME_ARMOR.load(event);

        ARPAchievements.init();
        ModRecipes.init();

        registerOre("oreLavaCrystal", new ItemStack(ModBlocks.BLOCK_LAVA_CRYSTAL, 1));
        registerOre("ingotMetal", new ItemStack(ModItems.STEEL_INGOT, 1));
        registerOre("ingotElectrical", new ItemStack(ModItems.ELECTRICAL_INGOT, 1));
        registerOre("gemLavaCrystal", new ItemStack(ModItems.LAVA_CRYSTAL, 1));
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
        COAL_ARMOR.instance = ArmorPlus.instance;
        LAPIS_ARMOR.instance = ArmorPlus.instance;
        REDSTONE_ARMOR.instance = ArmorPlus.instance;
        EMERALD_ARMOR.instance = ArmorPlus.instance;
        OBSIDIAN_ARMOR.instance = ArmorPlus.instance;
        LAVA_ARMOR.instance = ArmorPlus.instance;
        SUPER_STAR_ARMOR.instance = ArmorPlus.instance;
        ENDER_DRAGON_ARMOR.instance = ArmorPlus.instance;
        GUARDIAN_ARMOR.instance = ArmorPlus.instance;
        THE_ULTIMATE_ARMOR.instance = ArmorPlus.instance;
        RI_ARMOR.instance = ArmorPlus.instance;
        RG_ARMOR.instance = ArmorPlus.instance;
        RD_ARMOR.instance = ArmorPlus.instance;
        RC_ARMOR.instance = ArmorPlus.instance;
        CHICKEN_ARMOR.instance = ArmorPlus.instance;
        SLIME_ARMOR.instance = ArmorPlus.instance;

        /** v2 */
        STEEL_ARMOR.instance = ArmorPlus.instance;
        ELECTRICAL_ARMOR.instance = ArmorPlus.instance;


        /** Tinkers' Construct Armors */
        COBALT_ARMOR.instance = ArmorPlus.instance;
        ARDITE_ARMOR.instance = ArmorPlus.instance;
        MANYULLYN_ARMOR.instance = ArmorPlus.instance;
        PIG_IRON_ARMOR.instance = ArmorPlus.instance;
        KNIGHT_SLIME_ARMOR.instance = ArmorPlus.instance;

        COAL_ARMOR.preInit(event);
        LAPIS_ARMOR.preInit(event);
        REDSTONE_ARMOR.preInit(event);
        EMERALD_ARMOR.preInit(event);
        OBSIDIAN_ARMOR.preInit(event);
        LAVA_ARMOR.preInit(event);
        SUPER_STAR_ARMOR.preInit(event);
        ENDER_DRAGON_ARMOR.preInit(event);
        GUARDIAN_ARMOR.preInit(event);
        THE_ULTIMATE_ARMOR.preInit(event);
        RI_ARMOR.preInit(event);
        RG_ARMOR.preInit(event);
        RD_ARMOR.preInit(event);
        RC_ARMOR.preInit(event);
        CHICKEN_ARMOR.preInit(event);
        SLIME_ARMOR.preInit(event);

        /** v2 */
        STEEL_ARMOR.preInit(event);
        ELECTRICAL_ARMOR.preInit(event);

        /** Tinkers' Construct Armors */
        COBALT_ARMOR.preInit(event);
        ARDITE_ARMOR.preInit(event);
        MANYULLYN_ARMOR.preInit(event);
        PIG_IRON_ARMOR.preInit(event);
        KNIGHT_SLIME_ARMOR.preInit(event);

        logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.load.preInit"));
        configDir = new File(event.getModConfigurationDirectory() + "/" + "sokratis12GR's Mods" + "/" + ArmorPlus.MODID);
        configDir.mkdirs();
        sokratis12gr.armorplus.util.Logger.init(new File(configDir.getPath()));
        ConfigHandler.init(new File(configDir.getPath(), ArmorPlus.MODID + ".cfg"));
        proxy.registerRenderers(this);
        proxy.registerWorldGenerators();
        proxy.registerTileEntities();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ModCompatibility.loadCompat(ICompatibility.InitializationPhase.POST_INIT);
        logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.load.postInit"));
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
