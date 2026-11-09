package com.sofodev.armorplus.client;

import net.minecraft.Util;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * What the screens of the Rune Altar share: text a little smaller than normal that measures itself so it stays inside its area
 * (shrinking further first, cutting with "..." only as a last resort, for the tooltip to show in full) and dimmed items that are
 * only pictures.
 */
final class AltarGui {

    private static final String ELLIPSIS = "...";
    private static final float TEXT_SCALE = 0.85F;
    private static final float SMALL_SCALE = 0.75F;
    private static final int LINE_HEIGHT = 8;
    private static final int SMALL_LINE_HEIGHT = 7;
    private static final int GHOST_OVERLAY = 0xB01A1426;
    private static final int PREVIEW_OVERLAY = 0x701A1426;

    private AltarGui() {
    }

    /** @return how wide {@link #text} draws it */
    static int width(Font font, Component text) {
        return Mth.ceil(font.width(text) * TEXT_SCALE);
    }

    /** Draws text at the size of these screens. */
    static void text(GuiGraphics graphics, Font font, Component text, int x, int y, int color, boolean shadow) {
        scaled(graphics, font, text.getVisualOrderText(), x, y + centering(TEXT_SCALE), TEXT_SCALE, color, shadow);
    }

    /** Draws text at the size of these screens, centered on the x. */
    static void centered(GuiGraphics graphics, Font font, Component text, int centerX, int y, int color, boolean shadow) {
        float x = centerX - font.width(text) * TEXT_SCALE / 2.0F;
        scaled(graphics, font, text.getVisualOrderText(), x, y + centering(TEXT_SCALE), TEXT_SCALE, color, shadow);
    }

    /**
     * Draws one line, smaller if it's too wide.
     *
     * @return true if it still had to be cut
     */
    static boolean line(GuiGraphics graphics, Font font, Component text, int x, int y, int width, int color, boolean shadow) {
        int textWidth = font.width(text);
        float scale = Math.min(TEXT_SCALE, Math.max(SMALL_SCALE, (float) width / textWidth));
        boolean cut = textWidth * scale > width;
        FormattedText shown = cut ? ellipsize(font, text, (int) (width / scale)) : text;
        scaled(graphics, font, Language.getInstance().getVisualOrder(shown), x, y + centering(scale), scale, color, shadow);
        return cut;
    }

    /** Draws small text centered on the x, even smaller if it would be wider than the width. */
    static void small(GuiGraphics graphics, Font font, Component text, int centerX, int y, int width, int color) {
        int textWidth = font.width(text);
        float scale = Math.min(SMALL_SCALE, (float) width / textWidth);
        float x = centerX - textWidth * scale / 2.0F;
        scaled(graphics, font, text.getVisualOrderText(), x, y, scale, color, false);
    }

    /**
     * Draws wrapped text inside the area, smaller if it needs too many lines.
     *
     * @return true if it still had to be cut
     */
    static boolean paragraph(GuiGraphics graphics, Font font, Component text, int x, int y, int width, int height, int color) {
        List<FormattedText> lines = font.getSplitter().splitLines(text, (int) (width / TEXT_SCALE), Style.EMPTY);
        if (lines.size() * LINE_HEIGHT <= height + 1) {
            for (int i = 0; i < lines.size(); i++) {
                scaled(graphics, font, Language.getInstance().getVisualOrder(lines.get(i)), x, y + i * LINE_HEIGHT, TEXT_SCALE, color, false);
            }
            return false;
        }
        int smallWidth = (int) (width / SMALL_SCALE);
        List<FormattedText> small = font.getSplitter().splitLines(text, smallWidth, Style.EMPTY);
        int max = Math.max(1, height / SMALL_LINE_HEIGHT);
        boolean cut = small.size() > max;
        for (int i = 0; i < small.size() && i < max; i++) {
            FormattedText shown = cut && i == max - 1 ? ellipsize(font, small.get(i), smallWidth - font.width(ELLIPSIS)) : small.get(i);
            scaled(graphics, font, Language.getInstance().getVisualOrder(shown), x, y + i * SMALL_LINE_HEIGHT, SMALL_SCALE, color, false);
        }
        return cut;
    }

    /** @return how far down text of that scale goes to stay in the middle of the 8 pixels full size text takes up */
    private static float centering(float scale) {
        return (1.0F - scale) * 4.0F;
    }

    private static void scaled(GuiGraphics graphics, Font font, FormattedCharSequence text, float x, float y, float scale, int color, boolean shadow) {
        graphics.pose().pushPose();
        graphics.pose().translate(x, y, 0.0F);
        graphics.pose().scale(scale, scale, 1.0F);
        graphics.drawString(font, text, 0, 0, color, shadow);
        graphics.pose().popPose();
    }

    /** @return the text cut to the width with "..." */
    static FormattedText ellipsize(Font font, FormattedText text, int width) {
        if (font.width(text) <= width) return FormattedText.composite(text, FormattedText.of(ELLIPSIS));
        return FormattedText.composite(font.substrByWidth(text, width - font.width(ELLIPSIS)), FormattedText.of(ELLIPSIS));
    }

    /** Draws an item that isn't there, like what belongs in an empty slot. The overlay is drawn in front of the item. */
    static void ghostItem(GuiGraphics graphics, ItemStack stack, int x, int y) {
        graphics.renderItem(stack, x, y);
        overlay(graphics, x, y, GHOST_OVERLAY);
    }

    /** Draws a preview of an item, a little dimmer so it can't be taken for the real one. */
    static void previewItem(GuiGraphics graphics, Font font, ItemStack stack, int x, int y) {
        graphics.renderItem(stack, x, y);
        graphics.renderItemDecorations(font, stack, x, y);
        overlay(graphics, x, y, PREVIEW_OVERLAY);
    }

    /** Fills the slot in front of what was drawn in it (items and their decorations are drawn up to z 200). */
    static void overlay(GuiGraphics graphics, int x, int y, int color) {
        graphics.pose().pushPose();
        graphics.pose().translate(0.0F, 0.0F, 300.0F);
        graphics.fill(x, y, x + 16, y + 16, color);
        graphics.pose().popPose();
    }

    static void outline(GuiGraphics graphics, int x, int y, int width, int height, int color) {
        graphics.fill(x, y, x + width, y + 1, color);
        graphics.fill(x, y + height - 1, x + width, y + height, color);
        graphics.fill(x, y + 1, x + 1, y + height - 1, color);
        graphics.fill(x + width - 1, y + 1, x + width, y + height - 1, color);
    }

    /** @return a slow wave between 0 and 1 for things that glow */
    static float pulse() {
        return 0.5F + 0.5F * Mth.sin(Util.getMillis() / 500.0F);
    }

    /** @return the color with the alpha replaced */
    static int withAlpha(int color, int alpha) {
        return alpha << 24 | color & 0xFFFFFF;
    }
}
