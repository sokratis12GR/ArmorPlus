package com.sofodev.armorplus.client;

import com.sofodev.armorplus.api.RuneData;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffNames;
import com.sofodev.armorplus.registry.item.extra.IBuff;
import com.sofodev.armorplus.registry.rune.Runes;
import com.sofodev.thedragonlib.util.RomanNumeralUtil;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.IItemDecorator;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * Draws a symbol on runes in GUIs that tells what they hold (a lasting effect, an instant effect or an ArmorPlus ability),
 * and the level of the ones whose level matters.
 */
public final class RuneMarkerDecorator implements IItemDecorator {

    public static final RuneMarkerDecorator INSTANCE = new RuneMarkerDecorator();
    private static final int SIZE = 6;
    private static final int SLOT_SIZE = 16;
    /** The space left in the slot beside the marker */
    private static final int MAX_LEVEL_WIDTH = SLOT_SIZE - SIZE;
    /** The glyphs of the level sheet, in order. Each has a 5x7 cell, outline included */
    private static final String GLYPHS = "IVXLC0123456789";
    private static final int GLYPH_CELL_WIDTH = 5;
    private static final int NARROW_GLYPH_WIDTH = 3;
    private static final int GLYPH_HEIGHT = 7;
    /** Tints for levels 1 to 4 of each marker, going from dark to light. The last one is also for anything above */
    private static final int[] EFFECT_COLORS = {0xFF2FA82F, 0xFF4FCB4F, 0xFF7FE57F, 0xFFB8F7B8};
    private static final int[] INSTANT_COLORS = {0xFFC9981A, 0xFFE6BC2E, 0xFFFFD94F, 0xFFFFF2A0};
    private static final int[] ABILITY_COLORS = {0xFFC93A8E, 0xFFE85CAE, 0xFFFF8FCF, 0xFFFFC4E6};
    private static final Identifier LEVEL_GLYPHS = Identifier.fromNamespaceAndPath(MODID, "textures/item/rune_level_glyphs.png");
    private static final Identifier EFFECT = texture("effect");
    private static final Identifier INSTANT = texture("instant");
    private static final Identifier ABILITY = texture("ability");

    private static Identifier texture(String type) {
        return Identifier.fromNamespaceAndPath(MODID, "textures/item/rune_marker_" + type + ".png");
    }

    private RuneMarkerDecorator() {
    }

    @Override
    public boolean render(GuiGraphicsExtractor graphics, Font font, ItemStack stack, int x, int y) {
        RuneData rune = Runes.runeData(stack);
        BuffInstance buff = rune == null ? null : Runes.parse(rune);
        if (buff == null) return false;
        graphics.blit(RenderPipelines.GUI_TEXTURED, marker(buff.getBuff()), x, y, 0.0F, 0.0F, SIZE, SIZE, 16, 16);
        if (BuffNames.hasLevels(buff.getBuff())) drawLevel(graphics, levelColors(buff.getBuff()), Math.max(1, buff.getAmplifier() + 1), x, y);
        return true;
    }

    /**
     * Draws the level, tinted by its marker and value, right aligned in the top right corner of the slot.
     * Uses a Roman numeral, or digits when the numeral wouldn't fit next to the marker.
     */
    private static void drawLevel(GuiGraphicsExtractor graphics, int[] colors, int level, int x, int y) {
        String numeral = RomanNumeralUtil.generate(level);
        String text = width(numeral) <= MAX_LEVEL_WIDTH ? numeral : String.valueOf(level);
        int color = colors[Math.min(level, colors.length) - 1];
        int glyphX = x + SLOT_SIZE - width(text);
        for (char c : text.toCharArray()) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, LEVEL_GLYPHS, glyphX, y, GLYPHS.indexOf(c) * GLYPH_CELL_WIDTH, 0.0F, glyphWidth(c), GLYPH_HEIGHT, GLYPHS.length() * GLYPH_CELL_WIDTH, GLYPH_HEIGHT, color);
            glyphX += glyphWidth(c) - 1;
        }
    }

    /** @return the width of the text with neighboring glyphs sharing their outline, or {@link Integer#MAX_VALUE} if a glyph is missing */
    private static int width(String text) {
        int width = 1;
        for (char c : text.toCharArray()) {
            if (GLYPHS.indexOf(c) < 0) return Integer.MAX_VALUE;
            width += glyphWidth(c) - 1;
        }
        return width;
    }

    private static int glyphWidth(char glyph) {
        return glyph == 'I' ? NARROW_GLYPH_WIDTH : GLYPH_CELL_WIDTH;
    }

    private static Identifier marker(IBuff buff) {
        if (!buff.isEffect()) return ABILITY;
        return buff.getEffect().value().isInstantenous() ? INSTANT : EFFECT;
    }

    private static int[] levelColors(IBuff buff) {
        if (!buff.isEffect()) return ABILITY_COLORS;
        return buff.getEffect().value().isInstantenous() ? INSTANT_COLORS : EFFECT_COLORS;
    }
}
