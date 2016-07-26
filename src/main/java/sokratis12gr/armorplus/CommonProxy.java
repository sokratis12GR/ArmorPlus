package sokratis12gr.armorplus;

import net.minecraft.world.DimensionType;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sokratis12gr.armorplus.client.gui.ARPTab;
import sokratis12gr.armorplus.tileentity.TileEntityArmorForge;
import sokratis12gr.armorplus.worldgen.OreGen;
import sokratis12gr.sokratiscore.util.LogHelper;

public class CommonProxy {

    private DimensionType ArmorPlusDimension;

    public void preInit(FMLPreInitializationEvent event) {
        LogHelper.info("Finished PreInitialization");
    }

    public void init(FMLInitializationEvent event) {
        ARPTab.initialize();

        LogHelper.info("Finished Initialization");
    }

    public void postInit(FMLPostInitializationEvent event) {
        LogHelper.info("Finished PostInitialization");
    }

    public void loadCommands() {

    }

    public void loadRenders() {

    }

    public void addChatMessage(String string) {
    }

    /**
     * Sends a message to the client that gets replaced when the same messages are sent again. This reduces the amount of chat spam players end up with when checking things repeatedly.
     *
     * @param string - String message to send to player
     * @param id     - An ID for the message. Gets replaced by any other message that uses the same ID.
     */
    public void addChatMessage(String string, int id) {
    }

    public void registerRenderers(ArmorPlus armorPlus) {
    }

    public void registerWorldGenerators() {
        GameRegistry.registerWorldGenerator(new OreGen(), 0);
    }

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityArmorForge.class, "ArmorForge");
    }

    public DimensionType getArmorPlusDimension() {
        return ArmorPlusDimension;
    }
}
