package com.sofodev.armorplus.client;

import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.IItemDecorator;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * Draws the socket symbol on vanilla and other mods' equipment in GUIs, the same symbol ArmorPlus equipment has built into
 * its item models on newer versions (on 1.21.1 it is drawn on ArmorPlus equipment too). Registered for every non-ArmorPlus item, so the check per stack is kept cheap:
 * equipment that hasn't rolled its sockets is skipped before anything else is looked up.
 */
public final class SocketOverlayDecorator implements IItemDecorator {

    public static final SocketOverlayDecorator INSTANCE = new SocketOverlayDecorator();
    private static final int SIZE = 6;
    private static final ResourceLocation EMPTY = texture("empty");
    private static final ResourceLocation PARTIAL = texture("partial");
    private static final ResourceLocation FULL = texture("full");

    private static ResourceLocation texture(String state) {
        return ResourceLocation.fromNamespaceAndPath(MODID, "textures/item/socket_" + state + ".png");
    }

    private SocketOverlayDecorator() {
    }

    @Override
    public boolean render(GuiGraphics graphics, Font font, ItemStack stack, int x, int y) {
        graphics.pose().pushPose();
        graphics.pose().translate(0.0F, 0.0F, 200.0F);
        boolean drawn = draw(graphics, stack, x, y);
        graphics.pose().popPose();
        return drawn;
    }

    private static boolean draw(GuiGraphics graphics, ItemStack stack, int x, int y) {
        ResourceLocation texture = switch (Runes.socketState(stack)) {
            case "empty" -> EMPTY;
            case "partial" -> PARTIAL;
            case "full" -> FULL;
            default -> null;
        };
        if (texture == null) return false;
        graphics.blit(texture, x, y, 0.0F, 0.0F, SIZE, SIZE, 16, 16);
        return true;
    }
}
