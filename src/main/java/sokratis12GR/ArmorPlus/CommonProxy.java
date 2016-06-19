package sokratis12GR.ArmorPlus;

import net.minecraftforge.fml.common.registry.GameRegistry;
import sokratis12GR.ArmorPlus.tileentity.TileEntityArmorForge;
import sokratis12GR.ArmorPlus.worldgen.OreGen;

public class CommonProxy {
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
}
