package sokratis12GR.ArmorPlus;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sokratis12GR.ArmorPlus.armors.origin.*;
import sokratis12GR.ArmorPlus.armors.reinforced.RCArmor;
import sokratis12GR.ArmorPlus.armors.reinforced.RDArmor;
import sokratis12GR.ArmorPlus.armors.reinforced.RGArmor;
import sokratis12GR.ArmorPlus.armors.reinforced.RIArmor;
import sokratis12GR.ArmorPlus.armors.special.EnderDragonArmor;
import sokratis12GR.ArmorPlus.armors.special.GuardianArmor;
import sokratis12GR.ArmorPlus.armors.special.SuperStarArmor;
import sokratis12GR.ArmorPlus.armors.special.TheUltimateArmor;
import sokratis12GR.ArmorPlus.armors.special.mob.ChickenArmor;
import sokratis12GR.ArmorPlus.armors.special.mob.SlimeArmor;
import sokratis12GR.ArmorPlus.armors.tconstruct.*;
import sokratis12GR.ArmorPlus.client.gui.CreativeTabArmorPlus;
import sokratis12GR.ArmorPlus.client.gui.CreativeTabArmorPlusBlocks;
import sokratis12GR.ArmorPlus.client.gui.CreativeTabArmorPlusItems;
import sokratis12GR.ArmorPlus.client.gui.GuiArmorPlus;
import sokratis12GR.ArmorPlus.commands.CommandArmorPlus;
import sokratis12GR.ArmorPlus.commands.CommandBook;
import sokratis12GR.ArmorPlus.registry.MobDrops;
import sokratis12GR.ArmorPlus.registry.ModBlocks;
import sokratis12GR.ArmorPlus.registry.ModItems;
import sokratis12GR.ArmorPlus.resources.ConfigHandler;
import sokratis12GR.ArmorPlus.resources.GlobalEventsArmorPlus;
import sokratis12GR.ArmorPlus.util.ARPAchievements;
import sokratis12GR.ArmorPlus.util.TextHelper;

import java.io.File;

import static sokratis12GR.ArmorPlus.client.gui.GuiHandler.GUI_ARMORPLUS;

@Mod(modid = ArmorPlus.MODID, name = ArmorPlus.MODNAME, version = ArmorPlus.VERSION, dependencies = ArmorPlus.DEPEND, guiFactory = ArmorPlus.GUIFACTORY, updateJSON = "https://raw.githubusercontent.com/sokratis12GR/VersionUpdate/gh-pages/ArmorPlus.json")
public class ArmorPlus {

    public static final String MODNAME = "ArmorPlus";
    public static final String MODID = "armorplus";
    public static final String CHANNEL = "ArmorPlus";
    public static final String DEPEND = "";
    public static final String VERSION = "1.9.4-2.0.7.1";
    public static final String CLIENTPROXY = "sokratis12GR.ArmorPlus.ClientProxy";
    public static final String COMMONPROXY = "sokratis12GR.ArmorPlus.CommonProxy";
    public static final String GUIFACTORY = "sokratis12GR.ArmorPlus.client.gui.ConfigGuiFactory";

    @SidedProxy(clientSide = ArmorPlus.CLIENTPROXY, serverSide = ArmorPlus.COMMONPROXY)
    public static CommonProxy proxy;

    public static CreativeTabs TAB_ARMORPLUS = new CreativeTabArmorPlus(ArmorPlus.MODID + ".creativeTab");
    public static CreativeTabs TAB_ARMORPLUS_ITEMS = new CreativeTabArmorPlusItems(ArmorPlus.MODID + ".creativeTabItems");
    public static CreativeTabs TAB_ARMORPLUS_BLOCKS = new CreativeTabArmorPlusBlocks(ArmorPlus.MODID + ".creativeTabBlocks");
    public static Logger logger = LogManager.getLogger(ArmorPlus.MODNAME);
    public GuiHandler GuiHandler = new GuiHandler();

    @Instance(MODID)
    public static ArmorPlus instance;
    public static File configDir;
    public static File textureDir;

    public static File getConfigDir() {
        return configDir;
    }

    public static File getloggerDir() {
        return textureDir;
    }

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
     * Tinkers' Construct Armors
     */
    CobaltArmor COBALT_ARMOR = new CobaltArmor();
    ArditeArmor ARDITE_ARMOR = new ArditeArmor();
    ManyullynArmor MANYULLYN_ARMOR = new ManyullynArmor();
    PigIronArmor PIG_IRON_ARMOR = new PigIronArmor();
    KnightSlimeArmor KNIGHT_SLIME_ARMOR = new KnightSlimeArmor();
    // player.getHealth()

    @EventHandler
    public void init(FMLInitializationEvent event) {
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

        /** Tinkers' Construct Armors */
        COBALT_ARMOR.load(event);
        ARDITE_ARMOR.load(event);
        MANYULLYN_ARMOR.load(event);
        PIG_IRON_ARMOR.load(event);
        KNIGHT_SLIME_ARMOR.load(event);

        ARPAchievements.init();
        /**Crafting Recipes*/
        if (ConfigHandler.enableTheUltimateArmorRecipes) {
            GameRegistry.addShapelessRecipe(new ItemStack(TheUltimateArmor.helmet, 1), new ItemStack(SuperStarArmor.helmet, 1), new ItemStack(EnderDragonArmor.helmet, 1), new ItemStack(GuardianArmor.helmet, 1), new ItemStack(ModItems.THE_ULTIMATE_MATERIAL, 1));
            GameRegistry.addShapelessRecipe(new ItemStack(TheUltimateArmor.chestplate, 1), new ItemStack(SuperStarArmor.chestplate, 1), new ItemStack(EnderDragonArmor.chestplate, 1), new ItemStack(GuardianArmor.chestplate, 1), new ItemStack(ModItems.THE_ULTIMATE_MATERIAL, 1));
            GameRegistry.addShapelessRecipe(new ItemStack(TheUltimateArmor.legs, 1), new ItemStack(SuperStarArmor.legs, 1), new ItemStack(EnderDragonArmor.legs, 1), new ItemStack(GuardianArmor.legs, 1), new ItemStack(ModItems.THE_ULTIMATE_MATERIAL, 1));
            GameRegistry.addShapelessRecipe(new ItemStack(TheUltimateArmor.boots, 1), new ItemStack(SuperStarArmor.boots, 1), new ItemStack(EnderDragonArmor.boots, 1), new ItemStack(GuardianArmor.boots, 1), new ItemStack(ModItems.THE_ULTIMATE_MATERIAL, 1));
        }
        /** Chainmail Armor Recipes*/
        GameRegistry.addRecipe(new ItemStack(Items.CHAINMAIL_HELMET, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(ModItems.CHAINMAIL, 1));
        GameRegistry.addRecipe(new ItemStack(Items.CHAINMAIL_HELMET, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(ModItems.CHAINMAIL, 1));
        GameRegistry.addRecipe(new ItemStack(Items.CHAINMAIL_CHESTPLATE, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(ModItems.CHAINMAIL, 1));
        GameRegistry.addRecipe(new ItemStack(Items.CHAINMAIL_LEGGINGS, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(ModItems.CHAINMAIL, 1));
        GameRegistry.addRecipe(new ItemStack(Items.CHAINMAIL_BOOTS, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(ModItems.CHAINMAIL, 1));
        GameRegistry.addRecipe(new ItemStack(Items.CHAINMAIL_BOOTS, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(ModItems.CHAINMAIL, 1));
        /** Chainmail (Item) Recipe*/
        GameRegistry.addRecipe(new ItemStack(ModItems.CHAINMAIL, 12), "SSX", "SXS", "XSS", 'S', Items.IRON_INGOT);
        /** Reinforcing Material (Item) Recipe*/
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.REINFORCING_MATERIAL, 4), "XSX", "SBS", "XSX", 'S', "string", 'B', "slimeball"));
        /** The Ultimate Material */
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.THE_ULTIMATE_MATERIAL, 1), new ItemStack(ModItems.ENDER_DRAGON_SCALE, 1), new ItemStack(ModItems.GUARDIAN_SCALE, 1), new ItemStack(ModItems.WITHER_BONE, 1));
        /** Compressed Obsidian */
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.COMPRESSED_OBSIDIAN, 1), "OOO", "OOO", "OOO", 'O', "obsidian"));
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.OBSIDIAN, 9), new ItemStack(ModBlocks.COMPRESSED_OBSIDIAN));
        //GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.COMPRESSED_OBSIDIAN, 1, 0), "AGA", "GCG", "AAA", 'A', alloy1, 'G', "blockGlass", 'C', active_core1)
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModItems.init();
        ModBlocks.init();
        ModBlocks.register();
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

        /** Tinkers' Construct Armors */
        COBALT_ARMOR.preInit(event);
        ARDITE_ARMOR.preInit(event);
        MANYULLYN_ARMOR.preInit(event);
        PIG_IRON_ARMOR.preInit(event);
        KNIGHT_SLIME_ARMOR.preInit(event);

        logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.load.preInit"));
        configDir = new File(event.getModConfigurationDirectory() + "/" + "sokratis12GR's Mods" + "/" + ArmorPlus.MODID);
        configDir.mkdirs();
        sokratis12GR.ArmorPlus.util.Logger.init(new File(configDir.getPath()));
        ConfigHandler.init(new File(configDir.getPath(), ArmorPlus.MODID + ".cfg"));
        proxy.registerRenderers(this);
        proxy.registerWorldGenerators();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.load.postInit"));
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandArmorPlus());
        event.registerServerCommand(new CommandBook());
    }

    public static class GuiHandler implements IGuiHandler {
        @Override
        public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
            if (id == GUI_ARMORPLUS)
                return new GuiArmorPlus();
            return null;        }

        @Override
        public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
            if (id == GUI_ARMORPLUS)
                return new GuiArmorPlus();
            return null;
        }
    }

}
