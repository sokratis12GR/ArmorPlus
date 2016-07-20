package sokratis12GR.ArmorPlus;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;
import sokratis12GR.ArmorPlus.registry.ModBlocks;
import sokratis12GR.ArmorPlus.registry.ModItems;


public class ClientProxy extends CommonProxy {

    public static final KeyBinding KEY_NIGHT_VION = new KeyBinding("key.nightvision", Keyboard.KEY_N, "key.categories.armorplus");

    @Override
    public void registerClientKeyBindings() {
        ClientRegistry.registerKeyBinding(KEY_NIGHT_VION);
    }

    @Override
    public void registerRenderers(ArmorPlus ins) {
        ins.COAL_ARMOR.registerRenderers();
        ins.LAPIS_ARMOR.registerRenderers();
        ins.REDSTONE_ARMOR.registerRenderers();
        ins.EMERALD_ARMOR.registerRenderers();
        ins.OBSIDIAN_ARMOR.registerRenderers();
        ins.LAVA_ARMOR.registerRenderers();
        ins.SUPER_STAR_ARMOR.registerRenderers();
        ins.ENDER_DRAGON_ARMOR.registerRenderers();
        ins.GUARDIAN_ARMOR.registerRenderers();
        ins.THE_ULTIMATE_ARMOR.registerRenderers();
        ins.RI_ARMOR.registerRenderers();
        ins.RG_ARMOR.registerRenderers();
        ins.RD_ARMOR.registerRenderers();
        ins.RC_ARMOR.registerRenderers();
        ins.CHICKEN_ARMOR.registerRenderers();
        ins.SLIME_ARMOR.registerRenderers();
        /**  v2 */
        ins.METAL_ARMOR.registerRenderers();
        ins.ELECTRICAL_ARMOR.registerRenderers();
        /** Tinkers' Construct */
        ins.COBALT_ARMOR.registerRenderers();
        ins.ARDITE_ARMOR.registerRenderers();
        ins.MANYULLYN_ARMOR.registerRenderers();
        ins.PIG_IRON_ARMOR.registerRenderers();
        ins.KNIGHT_SLIME_ARMOR.registerRenderers();
        ModItems.initModels();
        ModBlocks.registerRenders();
    }
}
