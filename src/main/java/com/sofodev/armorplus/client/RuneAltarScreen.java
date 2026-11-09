package com.sofodev.armorplus.client;

import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffNames;
import com.sofodev.armorplus.registry.rune.RuneAltarMenu;
import com.sofodev.armorplus.registry.rune.RuneRitual;
import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * Rune Altar screen: a scrollable list of the equipment's extractable buffs and socketed runes with their catalyst and
 * experience level cost.
 * <p>
 * Layout (see textures/gui/container/rune_altar.png): equipment, Blank Rune and catalyst slots on the left, the buff list
 * in the middle, the result and its cost on the right.
 */
public class RuneAltarScreen extends AbstractContainerScreen<RuneAltarMenu> {

    private static final ResourceLocation BG_LOCATION = ResourceLocation.fromNamespaceAndPath(MODID, "textures/gui/container/rune_altar.png");
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
    private static final int TITLE_X = 8;
    private static final int LABEL_COLOR = 0xFFE6D2E2;
    private static final int COST_Y = 69;
    private static final int TEXT_COLOR = 0xFFFFFFFF;
    private static final int HINT_COLOR = 0xFFC8B0C8;
    private static final int COST_COLOR = 0xFF80FF20;
    private static final int TOO_EXPENSIVE_COLOR = 0xFFFF6060;
    private static final int MISSING_CATALYST_COLOR = 0xA02E2440;
    private static final int BLOCKED_ROW_COLOR = 0xFF2A2A2A;
    private static final int BLOCKED_TEXT_COLOR = 0xFF707070;
    private static final int PREVIEW_X = 161;
    private static final int PREVIEW_Y = 14;
    private static final int PREVIEW_TEXT_Y = 33;
    private static final int PREVIEW_TEXT_WIDTH = 28;
    private static final int ARROW_X = 150;
    private static final int ARROW_Y = 48;
    private static final int ARROW_WIDTH = 6;
    private static final int ARROW_HEIGHT = 8;
    private static final float LIT_ARROW_U = 200.0F;
    private static final int RITUAL_LINK_COLOR = 0xFF36243C;
    private static final int RITUAL_LINK_HOVERED_COLOR = 0xFF7C3A70;
    private static final int RITUAL_RIGHT = 186;
    private static final int RITUAL_Y = 3;
    private static final int RITUAL_HEIGHT = 10;

    private float scrollOffs;
    private boolean scrolling;
    private int startIndex;
    private List<RuneAltarMenu.Option> shownOptions = List.of();
    private final ItemStack gearGhost = new ItemStack(Items.DIAMOND_CHESTPLATE);
    private final ItemStack blankRuneGhost = new ItemStack(ModItems.BLANK_RUNE.get());

    public RuneAltarScreen(RuneAltarMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.imageWidth = IMAGE_WIDTH;
        this.imageHeight = IMAGE_HEIGHT;
        this.inventoryLabelY = IMAGE_HEIGHT - 94;
        this.inventoryLabelX = INVENTORY_LABEL_X;
        menu.registerUpdateListener(this::containerChanged);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        this.renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.blit(BG_LOCATION, this.leftPos, this.topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
        if (this.menu.getSlot(RuneAltarMenu.RESULT_SLOT).hasItem()) {
            graphics.blit(BG_LOCATION, this.leftPos + ARROW_X, this.topPos + ARROW_Y, LIT_ARROW_U, 0.0F, ARROW_WIDTH, ARROW_HEIGHT, 256, 256);
        }
        extractInputGhost(graphics, RuneAltarMenu.GEAR_SLOT, this.gearGhost);
        extractInputGhost(graphics, RuneAltarMenu.BLANK_RUNE_SLOT, this.blankRuneGhost);
        extractInputGhost(graphics, RuneAltarMenu.CATALYST_SLOT, this.menu.getCatalyst(previewOption(mouseX, mouseY)));

        int scrollerX = this.leftPos + SCROLLER_X;
        int scrollerY = this.topPos + LIST_Y + (int) ((LIST_HEIGHT - SCROLLER_HEIGHT) * this.scrollOffs);
        graphics.fill(scrollerX, scrollerY, scrollerX + SCROLLER_WIDTH, scrollerY + SCROLLER_HEIGHT, isScrollBarActive() ? 0xFFC9A6F0 : 0xFF505050);

        int x = this.leftPos + LIST_X;
        if (!this.menu.hasGear()) {
            Component hint = Component.translatable("container.armorplus.rune_altar.place_gear");
            AltarGui.paragraph(graphics, this.font, hint, x + 4, this.topPos + LIST_Y + 4, LIST_WIDTH - 8, LIST_HEIGHT - 8, HINT_COLOR);
            return;
        }

        for (int index = this.startIndex; index < this.startIndex + ROWS && index < optionCount(); index++) {
            int y = this.topPos + LIST_Y + (index - this.startIndex) * ROW_HEIGHT;
            boolean blocked = this.menu.getBlockReason(index) != null;
            boolean hovered = isOver(mouseX, mouseY, x, y);
            int background = blocked ? BLOCKED_ROW_COLOR : index == this.menu.getSelectedIndex() ? 0xFF6A3D9A : hovered ? 0xFF4A3A63 : 0xFF2E2440;
            graphics.fill(x, y, x + LIST_WIDTH, y + ROW_HEIGHT - 1, background);

            int cost = this.menu.getCost(index);
            Component costText = Component.literal(String.valueOf(cost));
            int costWidth = AltarGui.width(this.font, costText);
            int costX = x + LIST_WIDTH - costWidth - 3;
            int nameWidth = LIST_WIDTH - costWidth - 10;
            ItemStack catalyst = this.menu.getCatalyst(index);
            if (!catalyst.isEmpty()) {
                nameWidth -= 18;
                extractCatalyst(graphics, catalyst, this.menu.hasCatalyst(index), costX - 18, y);
            }
            Component name = this.menu.isExtracted(index) ? optionName(index).copy().withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.STRIKETHROUGH)
                    : blocked ? optionName(index).copy().withStyle(ChatFormatting.DARK_GRAY) : optionName(index);
            AltarGui.line(graphics, this.font, name, x + 3, y + 5, nameWidth, TEXT_COLOR, true);
            AltarGui.text(graphics, this.font, costText, costX, y + 5, blocked ? BLOCKED_TEXT_COLOR : canAfford(cost) ? COST_COLOR : TOO_EXPENSIVE_COLOR, true);
        }
    }

    private void extractInputGhost(GuiGraphics graphics, int slotIndex, ItemStack ghost) {
        Slot slot = this.menu.getSlot(slotIndex);
        if (!slot.hasItem() && !ghost.isEmpty()) AltarGui.ghostItem(graphics, ghost, this.leftPos + slot.x, this.topPos + slot.y);
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        AltarGui.line(graphics, this.font, this.title, TITLE_X, this.titleLabelY, RITUAL_RIGHT - AltarGui.width(this.font, ritualLinkText()) - 4 - TITLE_X - 2, LABEL_COLOR, false);
        AltarGui.text(graphics, this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, LABEL_COLOR, false);
        extractRitualLink(graphics, mouseX, mouseY);
        extractPreview(graphics, previewOption(mouseX, mouseY));
        if (!this.menu.getSlot(RuneAltarMenu.RESULT_SLOT).hasItem()) return;

        int cost = this.menu.getCost();
        AltarGui.centered(graphics, this.font, Component.literal(String.valueOf(cost)), RESULT_CENTER_X, COST_Y, canAfford(cost) ? COST_COLOR : TOO_EXPENSIVE_COLOR, true);
    }

    @Override
    protected void renderTooltip(GuiGraphics graphics, int mouseX, int mouseY) {
        super.renderTooltip(graphics, mouseX, mouseY);
        extractPreviewTooltip(graphics, mouseX, mouseY);
        if (isOverRitualLink(mouseX, mouseY)) {
            graphics.renderTooltip(this.font, ritualTooltip(), Optional.empty(), mouseX, mouseY);
            return;
        }
        int index = hoveredOption(mouseX, mouseY);
        if (index < 0) return;

        List<Component> lines = new ArrayList<>();
        lines.add(optionName(index));
        Component blockReason = this.menu.getBlockReason(index);
        if (blockReason != null) {
            lines.add(blockReason.copy().withStyle(ChatFormatting.RED));
            graphics.renderTooltip(this.font, lines, Optional.empty(), mouseX, mouseY);
            return;
        }
        int cost = this.menu.getCost(index);
        lines.add(Component.translatable("container.armorplus.rune_altar.cost_levels", cost)
                .withStyle(canAfford(cost) ? ChatFormatting.GREEN : ChatFormatting.RED));
        ItemStack catalyst = this.menu.getCatalyst(index);
        if (!catalyst.isEmpty()) {
            lines.add(Component.translatable("container.armorplus.rune_altar.needs_catalyst", catalyst.getCount(), catalyst.getHoverName())
                    .withStyle(this.menu.hasCatalyst(index) ? ChatFormatting.GREEN : ChatFormatting.RED));
        }
        int durability = this.menu.getDurabilityCost(index);
        if (this.menu.destroysGear(index)) {
            lines.add(Component.translatable("container.armorplus.rune_altar.destroys_gear").withStyle(ChatFormatting.RED));
        } else if (durability > 0) {
            lines.add(Component.translatable("container.armorplus.rune_altar.durability_cost", durability, durabilityPercent(durability)).withStyle(ChatFormatting.GOLD));
        }
        if (this.menu.getOptions().get(index).socketed()) {
            lines.add(Component.translatable("container.armorplus.rune_altar.durability_free").withStyle(ChatFormatting.GOLD));
        } else if (!this.menu.hasBlankRune()) {
            lines.add(Component.translatable("container.armorplus.rune_altar.needs_blank_rune").withStyle(ChatFormatting.GRAY));
        }
        graphics.renderTooltip(this.font, lines, Optional.empty(), mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isOverRitualLink(mouseX, mouseY) && (ritualReady() || this.menu.getAltarPos() != null)) {
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
            if (ritualReady()) {
                this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, RuneAltarMenu.RITUAL_BUTTON);
            } else {
                toggleGhostCircle(this.menu.getAltarPos());
            }
            return true;
        }
        int index = hoveredOption(mouseX, mouseY);
        if (index >= 0 && this.menu.clickMenuButton(this.minecraft.player, index)) {
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, index);
            return true;
        }

        int scrollerX = this.leftPos + SCROLLER_X;
        int scrollerY = this.topPos + LIST_Y;
        if (mouseX >= scrollerX && mouseX < scrollerX + SCROLLER_WIDTH && mouseY >= scrollerY && mouseY < scrollerY + LIST_HEIGHT) {
            this.scrolling = true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dx, double dy) {
        if (this.scrolling && isScrollBarActive()) {
            int top = this.topPos + LIST_Y;
            this.scrollOffs = Mth.clamp(((float) mouseY - top - SCROLLER_HEIGHT / 2.0F) / (LIST_HEIGHT - SCROLLER_HEIGHT), 0.0F, 1.0F);
            this.startIndex = Math.round(this.scrollOffs * getOffscreenRows());
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dx, dy);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        this.scrolling = false;
        return super.mouseReleased(mouseX, mouseY, button);
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

    private boolean ritualReady() {
        return this.menu.getPedestalCount() == RuneRitual.PEDESTAL_COUNT;
    }

    /** Showing it closes the screen, it's the world behind it that has something to see now. */
    private void toggleGhostCircle(BlockPos altar) {
        if (RitualCircleGhost.toggle(this.minecraft.level, altar)) {
            this.minecraft.player.displayClientMessage(Component.translatable("container.armorplus.rune_altar.circle.shown"), true);
            this.onClose();
        }
    }

    private boolean ghostCircleShown() {
        return this.menu.getAltarPos() != null && RitualCircleGhost.isShowing(this.menu.getAltarPos());
    }

    private Component ritualLinkText() {
        if (ritualReady()) return Component.translatable("container.armorplus.rune_altar.ritual");
        return Component.translatable("container.armorplus.rune_altar.circle." + (ghostCircleShown() ? "hide" : "show"), this.menu.getPedestalCount(), RuneRitual.PEDESTAL_COUNT);
    }

    private List<Component> ritualTooltip() {
        if (ritualReady()) return List.of(Component.translatable("container.armorplus.rune_altar.ritual.tooltip"));
        return List.of(Component.translatable("container.armorplus.rune_altar.circle.tooltip", RuneRitual.PEDESTAL_COUNT, RuneRitual.PEDESTAL_DISTANCE),
                Component.translatable("container.armorplus.rune_altar.circle.ghost_tooltip").withStyle(ChatFormatting.GRAY));
    }

    private boolean isOverRitualLink(double mouseX, double mouseY) {
        int right = this.leftPos + RITUAL_RIGHT;
        int top = this.topPos + RITUAL_Y;
        return mouseX >= right - AltarGui.width(this.font, ritualLinkText()) - 4 && mouseX < right && mouseY >= top && mouseY < top + RITUAL_HEIGHT;
    }

    /** The way to the other form of the altar, or how far the circle is. */
    private void extractRitualLink(GuiGraphics graphics, int mouseX, int mouseY) {
        Component text = ritualLinkText();
        int left = RITUAL_RIGHT - AltarGui.width(this.font, text) - 4;
        boolean hovered = isOverRitualLink(mouseX, mouseY);
        graphics.fill(left, RITUAL_Y, RITUAL_RIGHT, RITUAL_Y + RITUAL_HEIGHT, hovered ? RITUAL_LINK_HOVERED_COLOR : RITUAL_LINK_COLOR);
        AltarGui.text(graphics, this.font, text, left + 2, RITUAL_Y + 1, ritualReady() ? TEXT_COLOR : HINT_COLOR, false);
    }

    /** @return the row being hovered, otherwise the selected one */
    private int previewOption(int mouseX, int mouseY) {
        int hovered = hoveredOption(mouseX, mouseY);
        return hovered >= 0 ? hovered : this.menu.getSelectedIndex();
    }

    /** Draws what the option leaves of the equipment, dimmed; it's only a picture, not a slot. */
    private void extractPreview(GuiGraphics graphics, int index) {
        if (this.menu.destroysGear(index)) {
            AltarGui.small(graphics, this.font, Component.translatable("container.armorplus.rune_altar.destroyed"), RESULT_CENTER_X, PREVIEW_TEXT_Y, PREVIEW_TEXT_WIDTH, TOO_EXPENSIVE_COLOR);
            return;
        }
        ItemStack preview = this.menu.getPreview(index);
        if (preview.isEmpty()) return;
        AltarGui.previewItem(graphics, this.font, preview, PREVIEW_X, PREVIEW_Y);
        int durability = this.menu.getDurabilityCost(index);
        if (durability > 0) {
            AltarGui.small(graphics, this.font, Component.literal("-" + durabilityPercent(durability) + "%"), RESULT_CENTER_X, PREVIEW_TEXT_Y, PREVIEW_TEXT_WIDTH, TOO_EXPENSIVE_COLOR);
        }
    }

    private void extractPreviewTooltip(GuiGraphics graphics, int mouseX, int mouseY) {
        int x = this.leftPos + PREVIEW_X;
        int y = this.topPos + PREVIEW_Y;
        if (mouseX < x || mouseX >= x + 16 || mouseY < y || mouseY >= y + 16) return;
        ItemStack preview = this.menu.getPreview(this.menu.getSelectedIndex());
        if (preview.isEmpty()) return;
        int index = this.menu.getSelectedIndex();
        List<Component> lines = new ArrayList<>();
        lines.add(Component.translatable("container.armorplus.rune_altar.preview").withStyle(ChatFormatting.GOLD));
        lines.add(Component.translatable("container.armorplus.rune_altar.preview_removes", optionName(index).copy().withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.STRIKETHROUGH)).withStyle(ChatFormatting.GRAY));
        if (preview.isDamageableItem()) {
            lines.add(Component.translatable("container.armorplus.rune_altar.preview_durability", preview.getMaxDamage() - preview.getDamageValue(), preview.getMaxDamage()).withStyle(ChatFormatting.GRAY));
        }
        lines.addAll(Screen.getTooltipFromItem(this.minecraft, preview));
        graphics.renderTooltip(this.font, lines, preview.getTooltipImage(), preview, mouseX, mouseY);
    }

    private int durabilityPercent(int durability) {
        int max = this.menu.getSlot(RuneAltarMenu.GEAR_SLOT).getItem().getMaxDamage();
        return max == 0 ? 0 : Math.round(100.0F * durability / max);
    }

    /** Draws the catalyst beside the cost with its count, greyed out if the catalyst slot doesn't hold enough of it. */
    private void extractCatalyst(GuiGraphics graphics, ItemStack catalyst, boolean present, int x, int y) {
        graphics.renderItem(catalyst, x, y);
        graphics.renderItemDecorations(this.font, catalyst, x, y);
        if (!present) {
            AltarGui.overlay(graphics, x, y, MISSING_CATALYST_COLOR);
        }
    }

    private Component optionName(int index) {
        RuneAltarMenu.Option option = this.menu.getOptions().get(index);
        ChatFormatting color = option.socketed() ? ChatFormatting.LIGHT_PURPLE : ChatFormatting.WHITE;
        BuffInstance buff = Runes.parse(option.rune());
        if (buff == null) return Component.literal(option.rune().entry()).withStyle(color);
        MutableComponent name = buff.getTranslatedName().withStyle(color);
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
