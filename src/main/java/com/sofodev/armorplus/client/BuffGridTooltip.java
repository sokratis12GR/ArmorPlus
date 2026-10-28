package com.sofodev.armorplus.client;

import com.mojang.datafixers.util.Either;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Lays out a long list of buff lines as a pixel-aligned grid (filled column by column), so armor/weapon tooltips with
 * many effects stay short. Buff lines are marked by {@link #BUFF_LINE} (see {@code ToolTipUtils#addBuffLines}) and
 * collapsed into a grid while the tooltip is gathered; anything else is left untouched.
 */
public final class BuffGridTooltip implements TooltipComponent, ClientTooltipComponent {

    /** Style insertion marking a tooltip line as a buff line. Insertions only do anything in chat, so it's invisible here. */
    public static final String BUFF_LINE = "armorplus:buff_line";
    /** Above this many buffs the list is split into 2 columns. */
    private static final int TWO_COLUMNS_ABOVE = 5;
    /** Above this many buffs the list is split into 3 columns. */
    private static final int THREE_COLUMNS_ABOVE = 12;
    private static final int COLUMN_GAP = 6;
    private static final int LINE_HEIGHT = 10;

    private final List<Component> cells;
    private final int rows;
    /** x offset of each column, plus the total width as the last element. */
    private final int[] columnX;

    private BuffGridTooltip(List<Component> cells, int columns, int[] cellWidths) {
        this.cells = cells;
        this.rows = (cells.size() + columns - 1) / columns;
        this.columnX = new int[columns + 1];
        for (int column = 0; column < columns; column++) {
            int width = 0;
            for (int i = column * rows; i < Math.min(cells.size(), (column + 1) * rows); i++) {
                width = Math.max(width, cellWidths[i]);
            }
            columnX[column + 1] = columnX[column] + width + (column < columns - 1 ? COLUMN_GAP : 0);
        }
    }

    /**
     * Replaces every run of buff lines that is too long with a single grid, using as many columns as fit in half the
     * screen (the tooltip is drawn beside the cursor).
     */
    public static void collapse(List<Either<FormattedText, TooltipComponent>> elements, int screenWidth) {
        int maxWidth = screenWidth / 2 - 24;
        Font font = Minecraft.getInstance().font;
        for (int start = 0; start < elements.size(); start++) {
            int end = start;
            while (end < elements.size() && isBuffLine(elements.get(end))) end++;
            int count = end - start;
            if (count <= TWO_COLUMNS_ABOVE) {
                start = end;
                continue;
            }

            List<Component> cells = new ArrayList<>(count);
            int[] widths = new int[count];
            for (int i = 0; i < count; i++) {
                Component cell = (Component) elements.get(start + i).left().orElseThrow();
                cells.add(cell);
                widths[i] = font.width(cell);
            }
            BuffGridTooltip grid = null;
            for (int columns = count > THREE_COLUMNS_ABOVE ? 3 : 2; columns > 1; columns--) {
                grid = new BuffGridTooltip(cells, columns, widths);
                if (grid.width() <= maxWidth) break;
            }
            if (grid == null || grid.width() > maxWidth) {
                start = end;
                continue;
            }
            elements.subList(start, end).clear();
            elements.add(start, Either.right(grid));
        }
    }

    private static boolean isBuffLine(Either<FormattedText, TooltipComponent> element) {
        return element.left().map(text -> text instanceof Component c && BUFF_LINE.equals(c.getStyle().getInsertion())).orElse(false);
    }

    private int width() {
        return columnX[columnX.length - 1];
    }

    @Override
    public int getWidth(Font font) {
        return width();
    }

    @Override
    public int getHeight(Font font) {
        return rows * LINE_HEIGHT;
    }

    @Override
    public void extractText(GuiGraphicsExtractor graphics, Font font, int x, int y) {
        for (int i = 0; i < cells.size(); i++) {
            graphics.text(font, cells.get(i), x + columnX[i / rows], y + (i % rows) * LINE_HEIGHT, -1, true);
        }
    }
}
