package com.sofodev.armorplus.client.config;

import com.sofodev.thedragonlib.client.config.RuleListScreen;
import com.sofodev.thedragonlib.client.config.RuleType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Edits a socket "weights" list, where each index is a socket count, showing what the weights add up to as chances. Changes
 * are only applied on Done.
 */
final class SocketWeightsScreen extends Screen {
    private static final String UI = APRuleTypes.UI_PREFIX + "weights.";
    /** 0 - 8 sockets, the range of "maxSockets". */
    private static final int MAX_ROWS = 9;

    private final Screen parent;
    private final List<Integer> defaults;
    private final Consumer<List<Integer>> onDone;
    private final List<String> weights = new ArrayList<>();
    private @Nullable Button doneButton;

    SocketWeightsScreen(Screen parent, Component title, List<Integer> weights, List<Integer> defaults, Consumer<List<Integer>> onDone) {
        super(title);
        this.parent = parent;
        this.defaults = defaults;
        this.onDone = onDone;
        weights.forEach(weight -> this.weights.add(String.valueOf(weight)));
    }

    @Override
    protected void init() {
        WeightRows rows = addRenderableWidget(new WeightRows(minecraft, width, height - 36 - 60, 36));
        for (int i = 0; i < weights.size(); i++) rows.add(i);

        int buttonY = height - 28;
        Button addButton = addRenderableWidget(Button.builder(Component.translatable(APRuleTypes.UI_PREFIX + "add"), b -> {
                    weights.add("0");
                    rebuildWidgets();
                })
                .bounds(width / 2 - 154, buttonY - 24, 308, 20).build());
        addButton.active = weights.size() < MAX_ROWS;
        doneButton = addRenderableWidget(Button.builder(Component.translatable("gui.done"), b -> {
                    onDone.accept(weights.stream().map(Integer::valueOf).toList());
                    minecraft.setScreen(parent);
                })
                .bounds(width / 2 - 154, buttonY, 100, 20).build());
        addRenderableWidget(Button.builder(Component.translatable(APRuleTypes.UI_PREFIX + "reset"), b -> {
                    weights.clear();
                    defaults.forEach(weight -> weights.add(String.valueOf(weight)));
                    rebuildWidgets();
                })
                .bounds(width / 2 - 50, buttonY, 100, 20).build());
        addRenderableWidget(Button.builder(Component.translatable("gui.cancel"), b -> onClose())
                .bounds(width / 2 + 54, buttonY, 100, 20).build());
        updateDone();
    }

    /** @return the weight of a row, or -1 if it's not a valid one */
    private int weight(int index) {
        try {
            return Integer.parseInt(weights.get(index));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private long total() {
        long total = 0;
        for (int i = 0; i < weights.size(); i++) total += Math.max(0, weight(i));
        return total;
    }

    /** Sockets are rolled with an int total. */
    private boolean isValid() {
        for (int i = 0; i < weights.size(); i++) {
            if (weight(i) < 0) return false;
        }
        return total() <= Integer.MAX_VALUE;
    }

    private void updateDone() {
        if (doneButton != null) doneButton.active = isValid();
    }

    @Override
    public void onClose() {
        minecraft.setScreen(parent);
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);
        graphics.centeredText(font, title, width / 2, 10, 0xFFFFFFFF);
        if (isValid()) {
            RuleListScreen.subtitle(graphics, font, Component.translatable("armorplus.configuration.weights.tooltip"), width, 22, mouseX, mouseY);
        } else {
            graphics.centeredText(font, Component.translatable(UI + "invalid"), width / 2, 22, 0xFFFF5555);
        }
        if (weights.isEmpty()) {
            graphics.centeredText(font, Component.translatable(UI + "empty"), width / 2, 50, 0xFFA0A0A0);
        }
    }

    private final class WeightRows extends ContainerObjectSelectionList<WeightRows.Row> {
        WeightRows(Minecraft minecraft, int width, int height, int y) {
            super(minecraft, width, height, y, 24);
        }

        void add(int index) {
            addEntry(new Row(index));
        }

        @Override
        public int getRowWidth() {
            return Math.min(300, width - 40);
        }

        final class Row extends ContainerObjectSelectionList.Entry<Row> {
            private final int index;
            private final Component label;
            private final EditBox box;
            private final Button deleteButton;

            Row(int index) {
                this.index = index;
                this.label = index == 0 ? Component.translatable(UI + "none")
                        : index == 1 ? Component.translatable(UI + "one")
                        : Component.translatable(UI + "many", index);
                this.box = new EditBox(font, 0, 0, 60, 20, label);
                box.setMaxLength(10);
                box.setFilter(text -> text.chars().allMatch(Character::isDigit));
                box.setValue(weights.get(index));
                box.setResponder(value -> {
                    weights.set(index, value);
                    updateDone();
                });
                this.deleteButton = Button.builder(Component.translatable(APRuleTypes.UI_PREFIX + "delete"), b -> {
                    weights.remove(index);
                    rebuildWidgets();
                }).size(50, 20).build();
            }

            @Override
            public void extractContent(GuiGraphicsExtractor graphics, int mouseX, int mouseY, boolean hovered, float a) {
                int y = getContentY() + (getContentHeight() - 20) / 2;
                int textY = y + 6;
                deleteButton.setPosition(getContentRight() - deleteButton.getWidth(), y);
                int percentX = deleteButton.getX() - 48;
                box.setPosition(percentX - 4 - box.getWidth(), y);
                box.extractRenderState(graphics, mouseX, mouseY, a);
                deleteButton.extractRenderState(graphics, mouseX, mouseY, a);
                graphics.text(font, label, getContentX(), textY, 0xFFE0E0E0);
                long total = total();
                int weight = Math.max(0, weight(index));
                double percent = total > 0 ? Math.round(weight * 1000.0 / total) / 10.0 : 0;
                graphics.text(font, RuleType.format(percent) + "%", percentX, textY, 0xFFA0A0A0);
            }

            @Override
            public List<? extends GuiEventListener> children() {
                return List.of(box, deleteButton);
            }

            @Override
            public List<? extends NarratableEntry> narratables() {
                return List.of(box, deleteButton);
            }
        }
    }
}
