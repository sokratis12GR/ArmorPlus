package com.sofodev.armorplus.client;

import com.mojang.blaze3d.platform.cursor.CursorTypes;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.rune.RuneAltarMenu;
import com.sofodev.armorplus.registry.rune.RuneData;
import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import com.sofodev.armorplus.registry.item.extra.BuffNames;

/**
 * Rune Altar screen: a scrollable list of the equipment's extractable buffs with their catalyst and experience level cost.
 * <p>
 * Layout (see textures/gui/container/rune_altar.png): equipment, Blank Rune and catalyst slots on the left, the buff list
 * in the middle, the result and its cost on the right.
 */
public class RuneAltarScreen extends AbstractContainerScreen<RuneAltarMenu> {

    private static final Identifier BG_LOCATION = Identifier.fromNamespaceAndPath(MODID, "textures/gui/container/rune_altar.png");
    private static final int IMAGE_WIDTH = 192;
    private static final int IMAGE_HEIGHT = 186;
    private static final int LIST_X = 32;
    private static final int LIST_Y = 17;
    private static final int LIST_WIDTH = 106;
    private static final int ROW_HEIGHT = 18;
    private static final int ROWS = 4;
    private static final int LIST_HEIGHT = ROWS * ROW_HEIGHT;
    private static final int SCROLLER_X = 141;
    private static final int SCROLLER_WIDTH = 6;
    private static final int SCROLLER_HEIGHT = 15;
    private static final int RESULT_CENTER_X = 169;
    private static final int INVENTORY_LABEL_X = 16;
    private static final int COST_Y = 69;
    private static final int TEXT_COLOR = 0xFFFFFFFF;
    private static final int HINT_COLOR = 0xFF9A8AB0;
    private static final int COST_COLOR = 0xFF80FF20;
    private static final int TOO_EXPENSIVE_COLOR = 0xFFFF6060;
    private static final int MISSING_CATALYST_COLOR = 0xA02E2440;
    private static final String ELLIPSIS = "...";

    private float scrollOffs;
    private boolean scrolling;
    private int startIndex;
    private List<String> shownOptions = List.of();

    public RuneAltarScreen(RuneAltarMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
        this.inventoryLabelX = INVENTORY_LABEL_X;
        menu.registerUpdateListener(this::containerChanged);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        graphics.blit(RenderPipelines.GUI_TEXTURED, BG_LOCATION, this.leftPos, this.topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);

        int scrollerX = this.leftPos + SCROLLER_X;
        int scrollerY = this.topPos + LIST_Y + (int) ((LIST_HEIGHT - SCROLLER_HEIGHT) * this.scrollOffs);
        graphics.fill(scrollerX, scrollerY, scrollerX + SCROLLER_WIDTH, scrollerY + SCROLLER_HEIGHT, isScrollBarActive() ? 0xFFC9A6F0 : 0xFF505050);

        int x = this.leftPos + LIST_X;
        if (!this.menu.hasGear()) {
            Component hint = Component.translatable("container.armorplus.rune_altar.place_gear");
            graphics.textWithWordWrap(this.font, hint, x + 4, this.topPos + LIST_Y + 4, LIST_WIDTH - 8, HINT_COLOR, false);
            return;
        }

        for (int index = this.startIndex; index < this.startIndex + ROWS && index < optionCount(); index++) {
            int y = this.topPos + LIST_Y + (index - this.startIndex) * ROW_HEIGHT;
            boolean hovered = isOver(mouseX, mouseY, x, y);
            int background = index == this.menu.getSelectedIndex() ? 0xFF6A3D9A : hovered ? 0xFF4A3A63 : 0xFF2E2440;
            graphics.fill(x, y, x + LIST_WIDTH, y + ROW_HEIGHT - 1, background);

            int cost = this.menu.getCost(index);
            String costText = String.valueOf(cost);
            int costWidth = this.font.width(costText);
            int costX = x + LIST_WIDTH - costWidth - 3;
            int nameWidth = LIST_WIDTH - costWidth - 10;
            ItemStack catalyst = this.menu.getCatalyst(index);
            if (!catalyst.isEmpty()) {
                nameWidth -= 18;
                extractCatalyst(graphics, catalyst, this.menu.hasCatalyst(index), costX - 18, y);
            }
            graphics.text(this.font, Language.getInstance().getVisualOrder(fit(optionName(index), nameWidth)), x + 3, y + 5, TEXT_COLOR);
            graphics.text(this.font, costText, costX, y + 5, canAfford(cost) ? COST_COLOR : TOO_EXPENSIVE_COLOR);
            if (hovered) graphics.requestCursor(CursorTypes.POINTING_HAND);
        }
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        super.extractLabels(graphics, mouseX, mouseY);
        if (!this.menu.getSlot(RuneAltarMenu.RESULT_SLOT).hasItem()) return;

        int cost = this.menu.getCost();
        String text = String.valueOf(cost);
        graphics.text(this.font, text, RESULT_CENTER_X - this.font.width(text) / 2, COST_Y, canAfford(cost) ? COST_COLOR : TOO_EXPENSIVE_COLOR);
    }

    @Override
    protected void extractTooltip(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        super.extractTooltip(graphics, mouseX, mouseY);
        int index = hoveredOption(mouseX, mouseY);
        if (index < 0) return;

        List<Component> lines = new ArrayList<>();
        lines.add(optionName(index));
        int cost = this.menu.getCost(index);
        lines.add(Component.translatable("container.armorplus.rune_altar.cost_levels", cost)
                .withStyle(canAfford(cost) ? ChatFormatting.GREEN : ChatFormatting.RED));
        ItemStack catalyst = this.menu.getCatalyst(index);
        if (!catalyst.isEmpty()) {
            lines.add(Component.translatable("container.armorplus.rune_altar.needs_catalyst", catalyst.getCount(), catalyst.getHoverName())
                    .withStyle(this.menu.hasCatalyst(index) ? ChatFormatting.GREEN : ChatFormatting.RED));
        }
        if (!this.menu.hasBlankRune()) {
            lines.add(Component.translatable("container.armorplus.rune_altar.needs_blank_rune").withStyle(ChatFormatting.GRAY));
        }
        graphics.setTooltipForNextFrame(this.font, lines, Optional.empty(), mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        int index = hoveredOption(event.x(), event.y());
        if (index >= 0 && this.menu.clickMenuButton(this.minecraft.player, index)) {
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, index);
            return true;
        }

        int scrollerX = this.leftPos + SCROLLER_X;
        int scrollerY = this.topPos + LIST_Y;
        if (event.x() >= scrollerX && event.x() < scrollerX + SCROLLER_WIDTH && event.y() >= scrollerY && event.y() < scrollerY + LIST_HEIGHT) {
            this.scrolling = true;
        }
        return super.mouseClicked(event, doubleClick);
    }

    @Override
    public boolean mouseDragged(MouseButtonEvent event, double dx, double dy) {
        if (this.scrolling && isScrollBarActive()) {
            int top = this.topPos + LIST_Y;
            this.scrollOffs = Mth.clamp(((float) event.y() - top - SCROLLER_HEIGHT / 2.0F) / (LIST_HEIGHT - SCROLLER_HEIGHT), 0.0F, 1.0F);
            this.startIndex = Math.round(this.scrollOffs * getOffscreenRows());
            return true;
        }
        return super.mouseDragged(event, dx, dy);
    }

    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        this.scrolling = false;
        return super.mouseReleased(event);
    }

    @Override
    public boolean mouseScrolled(double x, double y, double scrollX, double scrollY) {
        if (super.mouseScrolled(x, y, scrollX, scrollY)) return true;
        if (isScrollBarActive()) {
            int offscreenRows = getOffscreenRows();
            this.scrollOffs = Mth.clamp(this.scrollOffs - (float) scrollY / offscreenRows, 0.0F, 1.0F);
            this.startIndex = Math.round(this.scrollOffs * offscreenRows);
        }
        return true;
    }

    /** Draws the catalyst beside the cost with its count, greyed out if the catalyst slot doesn't hold enough of it. */
    private void extractCatalyst(GuiGraphicsExtractor graphics, ItemStack catalyst, boolean present, int x, int y) {
        graphics.item(catalyst, x, y);
        graphics.itemDecorations(this.font, catalyst, x, y);
        if (!present) {
            graphics.nextStratum();
            graphics.fill(x, y, x + 16, y + 16, MISSING_CATALYST_COLOR);
        }
    }

    /** @return the text cut to the width, with "..." if it doesn't fit (the full name is in the tooltip) */
    private FormattedText fit(Component text, int width) {
        if (this.font.width(text) <= width) return text;
        FormattedText cut = this.font.substrByWidth(text, width - this.font.width(ELLIPSIS));
        return FormattedText.composite(cut, FormattedText.of(ELLIPSIS));
    }

    private Component optionName(int index) {
        String entry = Runes.extractedEntry(this.menu.getOptions().get(index));
        BuffInstance buff = Runes.parse(new RuneData(Runes.extractionKind(this.menu.getSlot(RuneAltarMenu.GEAR_SLOT).getItem()), entry));
        if (buff == null) return Component.literal(entry);
        MutableComponent name = buff.getTranslatedName().withStyle(ChatFormatting.WHITE);
        return name.append(BuffNames.levelSuffix(buff));
    }

    private int hoveredOption(double mouseX, double mouseY) {
        if (!this.menu.hasGear()) return -1;
        int x = this.leftPos + LIST_X;
        for (int index = this.startIndex; index < this.startIndex + ROWS && index < optionCount(); index++) {
            if (isOver(mouseX, mouseY, x, this.topPos + LIST_Y + (index - this.startIndex) * ROW_HEIGHT)) return index;
        }
        return -1;
    }

    private static boolean isOver(double mouseX, double mouseY, int x, int y) {
        return mouseX >= x && mouseX < x + LIST_WIDTH && mouseY >= y && mouseY < y + ROW_HEIGHT - 1;
    }

    private boolean canAfford(int cost) {
        return this.minecraft.player.hasInfiniteMaterials() || this.minecraft.player.experienceLevel >= cost;
    }

    private int optionCount() {
        return this.menu.getOptions().size();
    }

    private boolean isScrollBarActive() {
        return this.menu.hasGear() && optionCount() > ROWS;
    }

    private int getOffscreenRows() {
        return Math.max(1, optionCount() - ROWS);
    }

    private void containerChanged() {
        if (this.menu.getOptions() != this.shownOptions) {
            this.shownOptions = this.menu.getOptions();
            this.scrollOffs = 0.0F;
            this.startIndex = 0;
        }
    }
}
