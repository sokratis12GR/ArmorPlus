package com.sofodev.armorplus.client;

import com.mojang.blaze3d.platform.cursor.CursorTypes;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.rune.RitualMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.Optional;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * Ritual screen of a Rune Altar with a complete circle: tabs to socket, upgrade a rune, upgrade an effect or go to extraction.
 * <p>
 * Layout (see textures/gui/container/ritual_altar*.png, one per tab with the same layout): the two inputs on the left, what the
 * four pedestals hold around the altar in the middle, the result and its cost on the right, and what's missing below them.
 */
public class RitualScreen extends AbstractContainerScreen<RitualMenu> {

    /** Per {@link RitualMenu.Tab}. */
    private static final Identifier[] BACKGROUNDS = {background("ritual_altar"), background("ritual_altar_upgrade_rune"), background("ritual_altar_upgrade_effect")};
    private static final int[] ACCENT_COLORS = {0xFFC070FF, 0xFFFF9A38, 0xFF6EC8FF};
    private static final int[] SELECTED_TAB_COLORS = {0xFF6A3D9A, 0xFF96501A, 0xFF23609C};
    private static final int[] HOVERED_TAB_COLORS = {0xFF4A3A63, 0xFF5E3C24, 0xFF2C4663};
    private static final int[] BASE_TAB_COLORS = {0xFF2E2440, 0xFF33211A, 0xFF1C2A3E};
    private static final int[] NAME_COLORS = {0xFFF0D8FF, 0xFFFFE0B0, 0xFFD8F4FF};
    private static final int IMAGE_WIDTH = 192;
    private static final int IMAGE_HEIGHT = 206;
    private static final int TITLE_X = 8;
    private static final int TITLE_WIDTH = 176;
    private static final int INVENTORY_LABEL_X = 16;
    private static final int INVENTORY_LABEL_Y = 114;
    private static final int TAB_X = 8;
    private static final int TAB_Y = 15;
    private static final int TAB_STEP = 22;
    private static final int TAB_WIDTH = 20;
    private static final int TAB_HEIGHT = 18;
    private static final int TAB_NAME_X = 100;
    private static final int TAB_NAME_Y = 20;
    private static final int TAB_NAME_WIDTH = 86;
    private static final int LABEL_COLOR = 0xFFD8CCE8;
    private static final int RESULT_CENTER_X = 169;
    private static final int COST_Y = 76;
    private static final int STATUS_X = 11;
    private static final int STATUS_Y = 94;
    private static final int STATUS_WIDTH = 170;
    private static final int STATUS_HEIGHT = 17;
    private static final int HINT_COLOR = 0xFFB9A8D0;
    private static final int READY_COLOR = 0xFFFFFFFF;
    private static final int COST_COLOR = 0xFF80FF20;
    private static final int TOO_EXPENSIVE_COLOR = 0xFFFF6060;
    /** The top left of each pedestal's square, in the order of {@code RuneRitual.pedestalPositions}: north, east, south, west. */
    private static final int[][] PEDESTAL_SQUARES = {{102, 37}, {120, 55}, {102, 73}, {84, 55}};
    private static final int ALTAR_X = 102;
    private static final int ALTAR_Y = 55;
    private static final int SQUARE_SIZE = 18;
    private static final int ARROW_X = 144;
    private static final int ARROW_Y = 58;
    private static final int ARROW_WIDTH = 12;
    private static final int ARROW_HEIGHT = 8;
    private static final float LIT_ARROW_U = 200.0F;

    private final ItemStack[] tabIcons;
    private final ItemStack altarIcon;
    private final ItemStack gearGhost;
    private final ItemStack runeGhost;
    private boolean statusCut;

    public RitualScreen(RitualMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
        this.inventoryLabelX = INVENTORY_LABEL_X;
        this.inventoryLabelY = INVENTORY_LABEL_Y;
        this.altarIcon = new ItemStack(ModBlocks.RUNE_ALTAR.get());
        this.gearGhost = new ItemStack(Items.DIAMOND_CHESTPLATE);
        this.runeGhost = new ItemStack(ModItems.ARMOR_RUNE.get());
        this.tabIcons = new ItemStack[] {new ItemStack(ModItems.SOCKET_PUNCH.get()), this.runeGhost, this.gearGhost, this.altarIcon};
    }

    private static Identifier background(String name) {
        return Identifier.fromNamespaceAndPath(MODID, "textures/gui/container/" + name + ".png");
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int page = this.menu.getTab().ordinal();
        graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUNDS[page], this.leftPos, this.topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
        RitualMenu.Plan plan = this.menu.getPlan();
        boolean ready = !plan.result().isEmpty() && canAfford(plan.cost());
        if (ready) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUNDS[page], this.leftPos + ARROW_X, this.topPos + ARROW_Y, LIT_ARROW_U, 0.0F, ARROW_WIDTH, ARROW_HEIGHT, 256, 256);
        }

        extractTabs(graphics, mouseX, mouseY, page);
        extractAltar(graphics, page, ready);
        extractPedestals(graphics, page, plan);
        extractInputGhost(graphics, RitualMenu.PRIMARY_SLOT, this.menu.getTab() == RitualMenu.Tab.UPGRADE_RUNE ? this.runeGhost : this.gearGhost);
        extractInputGhost(graphics, RitualMenu.SECONDARY_SLOT, this.runeGhost);
    }

    private void extractTabs(GuiGraphicsExtractor graphics, int mouseX, int mouseY, int page) {
        int hovered = hoveredTab(mouseX, mouseY);
        for (int tab = 0; tab < this.tabIcons.length; tab++) {
            int x = this.leftPos + TAB_X + tab * TAB_STEP;
            int y = this.topPos + TAB_Y;
            int background = tab == page ? SELECTED_TAB_COLORS[page] : tab == hovered ? HOVERED_TAB_COLORS[page] : BASE_TAB_COLORS[page];
            graphics.fill(x, y, x + TAB_WIDTH, y + TAB_HEIGHT, background);
            AltarGui.outline(graphics, x, y, TAB_WIDTH, TAB_HEIGHT, tab == page ? ACCENT_COLORS[page] : 0xFF0C0814);
            graphics.item(this.tabIcons[tab], x + 2, y + 1);
        }
        if (hovered >= 0) graphics.requestCursor(CursorTypes.POINTING_HAND);
    }

    /** The altar itself in the middle of its circle, glowing brighter when the ritual can start. */
    private void extractAltar(GuiGraphicsExtractor graphics, int page, boolean ready) {
        int x = this.leftPos + ALTAR_X;
        int y = this.topPos + ALTAR_Y;
        int alpha = (ready ? 90 : 25) + (int) (AltarGui.pulse() * (ready ? 90 : 35));
        graphics.fill(x + 1, y + 1, x + SQUARE_SIZE - 1, y + SQUARE_SIZE - 1, AltarGui.withAlpha(ACCENT_COLORS[page], alpha));
        graphics.item(this.altarIcon, x + 1, y + 1);
    }

    /** What each pedestal holds with a glow around it. Empty ones show the catalyst that's still missing. */
    private void extractPedestals(GuiGraphicsExtractor graphics, int page, RitualMenu.Plan plan) {
        List<ItemStack> pedestals = this.menu.getPedestalItems();
        int missing = missing(plan.catalyst(), pedestals);
        int missingType = missing(plan.typeCatalyst(), pedestals);
        int glow = AltarGui.withAlpha(ACCENT_COLORS[page], 120 + (int) (AltarGui.pulse() * 110));
        for (int index = 0; index < pedestals.size() && index < PEDESTAL_SQUARES.length; index++) {
            ItemStack stack = pedestals.get(index);
            int left = this.leftPos + PEDESTAL_SQUARES[index][0];
            int top = this.topPos + PEDESTAL_SQUARES[index][1];
            if (!stack.isEmpty()) {
                AltarGui.outline(graphics, left, top, SQUARE_SIZE, SQUARE_SIZE, glow);
                graphics.item(stack, left + 1, top + 1);
                graphics.itemDecorations(this.font, stack, left + 1, top + 1);
            } else if (missing > 0) {
                missing--;
                AltarGui.ghostItem(graphics, plan.catalyst(), left + 1, top + 1);
            } else if (missingType > 0) {
                missingType--;
                AltarGui.ghostItem(graphics, plan.typeCatalyst(), left + 1, top + 1);
            }
        }
    }

    private static int missing(ItemStack catalyst, List<ItemStack> pedestals) {
        if (catalyst.isEmpty()) return 0;
        int missing = catalyst.getCount();
        for (ItemStack stack : pedestals) {
            if (stack.is(catalyst.getItem())) missing -= stack.getCount();
        }
        return missing;
    }

    private void extractInputGhost(GuiGraphicsExtractor graphics, int slotIndex, ItemStack ghost) {
        Slot slot = this.menu.getSlot(slotIndex);
        if (!slot.hasItem()) AltarGui.ghostItem(graphics, ghost, this.leftPos + slot.x, this.topPos + slot.y);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        int page = this.menu.getTab().ordinal();
        AltarGui.line(graphics, this.font, this.title, TITLE_X, this.titleLabelY, TITLE_WIDTH, LABEL_COLOR, false);
        AltarGui.text(graphics, this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, LABEL_COLOR, false);
        AltarGui.line(graphics, this.font, tabName(page), TAB_NAME_X, TAB_NAME_Y, TAB_NAME_WIDTH, NAME_COLORS[page], false);

        RitualMenu.Plan plan = this.menu.getPlan();
        boolean affordable = canAfford(plan.cost());
        if (!plan.result().isEmpty()) {
            AltarGui.centered(graphics, this.font, Component.literal(String.valueOf(plan.cost())), RESULT_CENTER_X, COST_Y, affordable ? COST_COLOR : TOO_EXPENSIVE_COLOR, true);
        }
        this.statusCut = AltarGui.paragraph(graphics, this.font, status(plan, affordable), STATUS_X, STATUS_Y, STATUS_WIDTH, STATUS_HEIGHT, statusColor(plan, affordable));
    }

    private Component status(RitualMenu.Plan plan, boolean affordable) {
        if (plan.problem() != null) return plan.problem();
        if (!affordable) return Component.translatable("container.armorplus.ritual_altar.too_expensive", plan.cost());
        if (!plan.catalyst().isEmpty() && !plan.typeCatalyst().isEmpty()) {
            return Component.translatable("container.armorplus.ritual_altar.uses_catalysts", plan.catalyst().getCount(), plan.catalyst().getHoverName(), plan.typeCatalyst().getCount(), plan.typeCatalyst().getHoverName());
        }
        ItemStack catalyst = plan.catalyst().isEmpty() ? plan.typeCatalyst() : plan.catalyst();
        if (!catalyst.isEmpty()) return Component.translatable("container.armorplus.ritual_altar.uses_catalyst", catalyst.getCount(), catalyst.getHoverName());
        return Component.translatable("container.armorplus.ritual_altar.ready");
    }

    private static int statusColor(RitualMenu.Plan plan, boolean affordable) {
        if (plan.problem() != null) return HINT_COLOR;
        return affordable ? READY_COLOR : TOO_EXPENSIVE_COLOR;
    }

    @Override
    protected void extractTooltip(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        super.extractTooltip(graphics, mouseX, mouseY);
        int tab = hoveredTab(mouseX, mouseY);
        if (tab >= 0) {
            List<Component> lines = List.of(tabName(tab), Component.translatable(tabKey(tab) + ".tooltip").withStyle(ChatFormatting.GRAY));
            graphics.setTooltipForNextFrame(this.font, lines, Optional.empty(), mouseX, mouseY);
            return;
        }
        if (this.statusCut && isOverStatus(mouseX, mouseY)) {
            RitualMenu.Plan plan = this.menu.getPlan();
            graphics.setTooltipForNextFrame(this.font, List.of(status(plan, canAfford(plan.cost()))), Optional.empty(), mouseX, mouseY);
            return;
        }
        int pedestal = hoveredPedestal(mouseX, mouseY);
        if (pedestal < 0 || pedestal >= this.menu.getPedestalItems().size()) return;
        ItemStack stack = this.menu.getPedestalItems().get(pedestal);
        Component hint = Component.translatable("container.armorplus.ritual_altar.pedestal").withStyle(ChatFormatting.GRAY);
        graphics.setTooltipForNextFrame(this.font, stack.isEmpty() ? List.of(hint) : List.of(stack.getHoverName(), hint), Optional.empty(), mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        int tab = hoveredTab(event.x(), event.y());
        if (tab >= 0) {
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
            this.menu.clickMenuButton(this.minecraft.player, tab);
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, tab);
            return true;
        }
        return super.mouseClicked(event, doubleClick);
    }

    private boolean isOverStatus(double mouseX, double mouseY) {
        int x = this.leftPos + STATUS_X;
        int y = this.topPos + STATUS_Y;
        return mouseX >= x && mouseX < x + STATUS_WIDTH && mouseY >= y && mouseY < y + STATUS_HEIGHT;
    }

    private int hoveredTab(double mouseX, double mouseY) {
        int y = this.topPos + TAB_Y;
        if (mouseY < y || mouseY >= y + TAB_HEIGHT) return -1;
        for (int tab = 0; tab < this.tabIcons.length; tab++) {
            int x = this.leftPos + TAB_X + tab * TAB_STEP;
            if (mouseX >= x && mouseX < x + TAB_WIDTH) return tab;
        }
        return -1;
    }

    private int hoveredPedestal(double mouseX, double mouseY) {
        for (int index = 0; index < PEDESTAL_SQUARES.length; index++) {
            int x = this.leftPos + PEDESTAL_SQUARES[index][0];
            int y = this.topPos + PEDESTAL_SQUARES[index][1];
            if (mouseX >= x && mouseX < x + SQUARE_SIZE && mouseY >= y && mouseY < y + SQUARE_SIZE) return index;
        }
        return -1;
    }

    /** @return the key of the tab, the last one past the menu's tabs is the way to extraction */
    private static String tabKey(int tab) {
        RitualMenu.Tab[] tabs = RitualMenu.Tab.values();
        return "container.armorplus.ritual_altar.tab." + (tab < tabs.length ? tabs[tab].key() : "extract");
    }

    private static Component tabName(int tab) {
        return Component.translatable(tabKey(tab));
    }

    private boolean canAfford(int cost) {
        return this.minecraft.player.hasInfiniteMaterials() || this.minecraft.player.experienceLevel >= cost;
    }
}
