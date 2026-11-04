package com.sofodev.armorplus.client;

import com.sofodev.armorplus.registry.item.armor.ArmorEffects;
import com.sofodev.armorplus.registry.item.tool.ToolEffects;
import com.sofodev.armorplus.registry.item.tool.WeaponEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * The layout shared by the {@link ArmorEffectsScreen}, {@link ToolEffectsScreen} and {@link WeaponEffectsScreen}, and the
 * tabs (and keys) to swap between them. A menu without anything to show can't be opened.
 */
public abstract class EffectsScreen extends Screen {

    protected static final int COLUMNS = 2;
    protected static final int ROWS = 9;
    protected static final int BUTTON_WIDTH = 150;
    protected static final int BUTTON_HEIGHT = 20;
    protected static final int GAP = 4;
    protected static final int TOP = 44;

    /** Three tabs as wide as the two columns of effects. */
    private static final int TAB_WIDTH = 98;
    private static final int TAB_HEIGHT = 18;
    private static final int TAB_GAP = 5;
    private static final int TAB_TOP = 2;
    private static final int TAB_INDICATOR_HEIGHT = 2;
    private static final int TAB_INDICATOR_COLOR = 0xFFFFAA00;

    public enum Tab {
        ARMOR("armor_effects", ArmorPlusKeys.ARMOR_EFFECTS, ArmorEffectsScreen::new, player -> {
            // the client keeps its own cache of the worn runes, which isn't invalidated by server events
            ArmorEffects.invalidate(player);
            return !ArmorEffects.entries(player).isEmpty();
        }),
        TOOL("tool_effects", ArmorPlusKeys.TOOL_EFFECTS, ToolEffectsScreen::new, player -> {
            // the client keeps its own cache of the held tool's runes, which isn't invalidated by server events
            ToolEffects.invalidate(player);
            return !ToolEffects.entries(player).isEmpty();
        }),
        WEAPON("weapon_effects", ArmorPlusKeys.WEAPON_EFFECTS, WeaponEffectsScreen::new, player -> !WeaponEffects.entries(player).isEmpty());

        private final String translationKey;
        private final KeyMapping key;
        private final Supplier<EffectsScreen> screen;
        private final Predicate<Player> hasEffects;

        Tab(String name, KeyMapping key, Supplier<EffectsScreen> screen, Predicate<Player> hasEffects) {
            this.translationKey = "screen.armorplus." + name;
            this.key = key;
            this.screen = screen;
            this.hasEffects = hasEffects;
        }

        /** @return true if the player has anything to show in this tab's menu */
        public boolean hasEffects(Player player) {
            return this.hasEffects.test(player);
        }

        public EffectsScreen createScreen() {
            return this.screen.get();
        }

        /** @return why this tab's menu has nothing to show, e.g. "Your held tool has no tool runes" */
        public Component noneMessage() {
            return Component.translatable(this.translationKey + ".none");
        }

        private MutableComponent label() {
            return Component.translatable(this.translationKey + ".tab");
        }
    }

    private final Tab tab;

    protected EffectsScreen(Tab tab) {
        super(Component.translatable(tab.translationKey));
        this.tab = tab;
    }

    @Override
    protected void init() {
        for (Tab other : Tab.values()) {
            boolean current = other == this.tab;
            boolean available = current || other.hasEffects(this.minecraft.player);
            Button.Builder builder = Button.builder(current ? other.label().withStyle(ChatFormatting.GOLD) : other.label(), b -> swapTo(other))
                    .bounds(tabX(other), TAB_TOP, TAB_WIDTH, TAB_HEIGHT);
            if (!available) builder.tooltip(Tooltip.create(other.noneMessage()));
            this.addRenderableWidget(builder.build()).active = available && !current;
        }
    }

    private int tabX(Tab tab) {
        int left = this.width / 2 - (Tab.values().length * TAB_WIDTH + (Tab.values().length - 1) * TAB_GAP) / 2;
        return left + tab.ordinal() * (TAB_WIDTH + TAB_GAP);
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);
        // marks the current tab, a disabled button alone looks the same as a tab without anything to show
        int x = tabX(this.tab);
        int y = TAB_TOP + TAB_HEIGHT;
        graphics.fill(x, y, x + TAB_WIDTH, y + TAB_INDICATOR_HEIGHT, TAB_INDICATOR_COLOR);
    }

    private void swapTo(Tab other) {
        this.minecraft.setScreen(other.createScreen());
    }

    @Override
    public boolean keyPressed(KeyEvent event) {
        for (Tab other : Tab.values()) {
            if (other.key.matches(event)) {
                if (other != this.tab && other.hasEffects(this.minecraft.player)) swapTo(other);
                return true;
            }
        }
        return super.keyPressed(event);
    }

    /** @return the y of the bottom row of buttons: below all {@link #ROWS} rows, or the screen's bottom if that's too small */
    protected int bottom() {
        return Math.min(TOP + ROWS * (BUTTON_HEIGHT + GAP) + 6, this.height - BUTTON_HEIGHT - 4);
    }

    /** @return the rows of effects that fit above the {@link #bottom()} row */
    protected int rows() {
        return Math.clamp((bottom() - TOP) / (BUTTON_HEIGHT + GAP), 1, ROWS);
    }

    protected void addDoneButton() {
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, b -> this.onClose())
                .bounds(this.width / 2 - 100, bottom(), 200, BUTTON_HEIGHT).build());
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
