package com.sofodev.armorplus.client;

import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.IItemDecorator;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * Draws the socket symbol on vanilla and other mods' equipment in GUIs, the same symbol ArmorPlus equipment has built into
 * its item models (see SocketStateProperty). Registered for every non-ArmorPlus item, so the check per stack is kept cheap:
 * equipment that hasn't rolled its sockets is skipped before anything else is looked up.
 */
public final class SocketOverlayDecorator implements IItemDecorator {

    public static final SocketOverlayDecorator INSTANCE = new SocketOverlayDecorator();
    private static final int SIZE = 6;
    private static final Identifier EMPTY = texture("empty");
    private static final Identifier PARTIAL = texture("partial");
    private static final Identifier FULL = texture("full");

    private static Identifier texture(String state) {
        return Identifier.fromNamespaceAndPath(MODID, "textures/item/socket_" + state + ".png");
    }

    private SocketOverlayDecorator() {
    }

    @Override
    public boolean render(GuiGraphicsExtractor graphics, Font font, ItemStack stack, int x, int y) {
        Identifier texture = switch (Runes.socketState(stack)) {
            case "empty" -> EMPTY;
            case "partial" -> PARTIAL;
            case "full" -> FULL;
            default -> null;
        };
        if (texture == null) return false;
        graphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, 0.0F, 0.0F, SIZE, SIZE, 16, 16);
        return true;
    }
}
