/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.FMLInjectionData;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.client.gui.ARPTab;
import net.thedragonteam.armorplus.compat.ICompatibility;
import net.thedragonteam.armorplus.entity.ARPEntities;
import net.thedragonteam.armorplus.integration.TiC;
import net.thedragonteam.armorplus.registry.*;
import net.thedragonteam.armorplus.resources.GlobalEventsArmorPlus;
import net.thedragonteam.armorplus.tileentity.TileEntityHighTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityUltiTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityWorkbench;
import net.thedragonteam.armorplus.util.ARPAchievements;
import net.thedragonteam.armorplus.worldgen.OreGen;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.io.File;
import java.lang.ref.WeakReference;

public class CommonProxy {

    private static WeakReference<EntityPlayer> dummyPlayer = new WeakReference<EntityPlayer>(null);

    public void preInit(FMLPreInitializationEvent event) {
        LogHelper.info("Begin PreInitialization");
        ARPEntities.init();
        ModBlocks.init();
        registerWorldGenerators();
        registerTileEntities();
        if (Loader.isModLoaded("tconstruct")) {
            TiC.preInit();
        }
        MinecraftForge.EVENT_BUS.register(new MobDrops());
        LogHelper.info("Finished PreInitialization");
    }

    public void init(FMLInitializationEvent event) {
        LogHelper.info("Begin Initialization");
        ModOreDicts.registerOreDictEnties();
        ARPTab.initialize();
        if (Loader.isModLoaded("tconstruct")) {
            TiC.init();
        }
        LogHelper.info("Finished Initialization");
    }

    public void postInit(FMLPostInitializationEvent event) {
        LogHelper.info("Begin PostInitialization");
        LogHelper.info("Finished PostInitialization");
    }

    public void registerEvents() {
        ModCompatibility.loadCompat(ICompatibility.InitializationPhase.INIT);
        MinecraftForge.EVENT_BUS.register(new GlobalEventsArmorPlus());

        //Register to receive subscribed events
        MinecraftForge.EVENT_BUS.register(this);

        ARPAchievements.init();
        ModRecipes.init();
    }

    /**
     * Whether or not the game is paused.
     */
    public boolean isPaused() {
        return false;
    }

    public void registerRenderers() {
    }

    public void registerRenderer() {
    }

    public void registerModels() {
    }

    /**
     * Gets the Minecraft base directory.
     *
     * @return base directory
     */
    public File getMinecraftDir() {
        return (File) FMLInjectionData.data()[6];
    }

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityWorkbench.class, "Workbench");
        GameRegistry.registerTileEntity(TileEntityHighTechBench.class, "HighTechBench");
        GameRegistry.registerTileEntity(TileEntityUltiTechBench.class, "UltiTechBench");
    }

    private WeakReference<EntityPlayer> createNewPlayer(WorldServer world) {
        EntityPlayer player = FakePlayerFactory.get(world, ArmorPlus.gameProfile);

        return new WeakReference<EntityPlayer>(player);
    }

    private WeakReference<EntityPlayer> createNewPlayer(WorldServer world, double x, double y, double z) {
        EntityPlayer player = FakePlayerFactory.get(world, ArmorPlus.gameProfile);

        player.posX = x;
        player.posY = y;
        player.posZ = z;

        return new WeakReference<EntityPlayer>(player);
    }

    public final WeakReference<EntityPlayer> getDummyPlayer(WorldServer world) {
        if (dummyPlayer.get() == null) {
            dummyPlayer = createNewPlayer(world);
        } else {
            dummyPlayer.get().worldObj = world;
        }

        return dummyPlayer;
    }

    public final WeakReference<EntityPlayer> getDummyPlayer(WorldServer world, double x, double y, double z) {
        if (dummyPlayer.get() == null) {
            dummyPlayer = createNewPlayer(world, x, y, z);
        } else {
            dummyPlayer.get().worldObj = world;
            dummyPlayer.get().posX = x;
            dummyPlayer.get().posY = y;
            dummyPlayer.get().posZ = z;
        }

        return dummyPlayer;
    }

    public void registerWorldGenerators() {
        GameRegistry.registerWorldGenerator(new OreGen(), 0);
    }

    public EntityPlayer getPlayer(MessageContext context) {
        return context.getServerHandler().playerEntity;
    }

    public void handlePacket(Runnable runnable, EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            ((WorldServer) player.worldObj).addScheduledTask(runnable);
        }
    }
}
