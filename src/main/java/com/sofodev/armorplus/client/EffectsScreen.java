package com.sofodev.armorplus.client;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.item.armor.ArmorEffects;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.tool.WeaponEffects;
import com.sofodev.armorplus.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import com.sofodev.armorplus.registry.item.tool.RangedEffects;
import com.sofodev.armorplus.registry.item.tool.ToolEffects;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * The layout shared by the {@link ArmorEffectsScreen}, {@link ToolEffectsScreen}, {@link WeaponEffectsScreen} and
 * {@link RangedEffectsScreen}, and the tabs (and keys) to swap between them. A menu without anything to show can't be opened.
 */
public abstract class EffectsScreen extends Screen {

    protected static final int COLUMNS = 2;
    protected static final int ROWS = 9;
    protected static final int BUTTON_WIDTH = 150;
    protected static final int BUTTON_HEIGHT = 20;
    protected static final int GAP = 4;
    protected static final int TOP = 44;

    /** Four tabs as wide as the two columns of effects. */
    private static final int TAB_WIDTH = 72;
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
        WEAPON("weapon_effects", ArmorPlusKeys.WEAPON_EFFECTS, WeaponEffectsScreen::new, player -> !WeaponEffects.entries(player).isEmpty()),
        RANGED("ranged_effects", ArmorPlusKeys.RANGED_EFFECTS, RangedEffectsScreen::new, player -> !RangedEffects.entries(player).isEmpty());

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

        public KeyMapping key() {
            return this.key;
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

    /**
     * One button of the list.
     *
     * @param section effects of a section share their pages, in the order the sections first appear; see {@link #RUNES}
     * @param title   the title of the section's pages
     * @param active  false shows it greyed out, even with toggles allowed
     */
    protected record Effect(String section, Component title, Component label, Component tooltip, boolean active, Runnable toggle) {
    }

    private record Page(Component title, List<Effect> effects) {
    }

    private final Tab tab;
    private List<Page> pages = List.of();
    /** Keys and states shown, to rebuild the buttons when the toggles (synced) or the equipment change. */
    private List<String> shownState = List.of();
    private int page;

    protected EffectsScreen(Tab tab) {
        super(Component.translatable(tab.translationKey));
        this.tab = tab;
    }

    /** @return the effects to list, by section */
    protected abstract List<Effect> effects();

    /** @return what the list depends on, the buttons are rebuilt when it changes */
    protected abstract List<String> state();

    /** The section of socketed runes, every menu gives them their own pages. */
    protected static final String RUNES = "rune";

    /** Clears what the client caches of the effects. */
    protected void reload() {
    }

    protected static Component runesTitle() {
        return Component.translatable("screen.armorplus.armor_effects.runes").withStyle(ChatFormatting.GOLD);
    }

    /** @return e.g. "Haste II: On" */
    protected static Component onOffLabel(BuffInstance buff, boolean disabled) {
        return Component.empty().append(ToolTipUtils.buffName(buff, true)).append(": ").append(disabled
                ? Component.translatable("options.off").withStyle(ChatFormatting.RED)
                : Component.translatable("options.on").withStyle(ChatFormatting.GREEN));
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

        reload();
        this.shownState = state();
        this.pages = paginate(effects(), COLUMNS * rows());
        this.page = Math.clamp(this.page, 0, Math.max(0, this.pages.size() - 1));

        boolean toggles = ArmorPlusConfig.allowEffectToggles.get();
        int left = this.width / 2 - (COLUMNS * BUTTON_WIDTH + (COLUMNS - 1) * GAP) / 2;
        List<Effect> shown = this.pages.isEmpty() ? List.of() : this.pages.get(this.page).effects();
        for (int i = 0; i < shown.size(); i++) {
            Effect effect = shown.get(i);
            int x = left + (i % COLUMNS) * (BUTTON_WIDTH + GAP);
            int y = TOP + (i / COLUMNS) * (BUTTON_HEIGHT + GAP);
            Button button = Button.builder(effect.label(), b -> effect.toggle().run())
                    .bounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT)
                    .tooltip(Tooltip.create(effect.tooltip()))
                    .build();
            button.active = toggles && effect.active();
            this.addRenderableWidget(button);
        }

        int bottom = bottom();
        if (this.pages.size() > 1) {
            this.addRenderableWidget(Button.builder(Component.literal("<"), b -> changePage(-1))
                    .bounds(this.width / 2 - 100 - 24, bottom, 20, BUTTON_HEIGHT).build()).active = this.page > 0;
            this.addRenderableWidget(Button.builder(Component.literal(">"), b -> changePage(1))
                    .bounds(this.width / 2 + 104, bottom, 20, BUTTON_HEIGHT).build()).active = this.page < this.pages.size() - 1;
        }
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, b -> this.onClose())
                .bounds(this.width / 2 - 100, bottom, 200, BUTTON_HEIGHT).build());
    }

    /** @return one page per section and as many effects as fit (a section with more effects gets several pages) */
    private static List<Page> paginate(List<Effect> effects, int perPage) {
        Map<String, List<Effect>> bySection = new LinkedHashMap<>();
        for (Effect effect : effects) bySection.computeIfAbsent(effect.section(), s -> new ArrayList<>()).add(effect);
        List<Page> pages = new ArrayList<>();
        bySection.values().forEach(list -> {
            for (int start = 0; start < list.size(); start += perPage) {
                pages.add(new Page(list.get(0).title(), List.copyOf(list.subList(start, Math.min(list.size(), start + perPage)))));
            }
        });
        return pages;
    }

    private void changePage(int delta) {
        this.page += delta;
        this.rebuildWidgets();
    }

    @Override
    public void tick() {
        super.tick();
        // the server confirms toggles by syncing the player's data; equipment can change while the screen is open
        if (this.minecraft.player.tickCount % 10 == 0) reload();
        if (!state().equals(this.shownState)) this.rebuildWidgets();
    }

    private int tabX(Tab tab) {
        int left = this.width / 2 - (Tab.values().length * TAB_WIDTH + (Tab.values().length - 1) * TAB_GAP) / 2;
        return left + tab.ordinal() * (TAB_WIDTH + TAB_GAP);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        // marks the current tab, a disabled button alone looks the same as a tab without anything to show
        int x = tabX(this.tab);
        int y = TAB_TOP + TAB_HEIGHT;
        graphics.fill(x, y, x + TAB_WIDTH, y + TAB_INDICATOR_HEIGHT, TAB_INDICATOR_COLOR);

        if (!this.pages.isEmpty()) {
            MutableComponent pageTitle = this.pages.get(this.page).title().copy();
            if (this.pages.size() > 1) pageTitle.append(Component.literal("  " + (this.page + 1) + "/" + this.pages.size()).withStyle(ChatFormatting.GRAY));
            graphics.drawCenteredString(this.font, pageTitle, this.width / 2, 23, 0xFFFFFFFF);
        }
        Component subtitle = !ArmorPlusConfig.allowEffectToggles.get()
                ? Component.translatable("screen.armorplus.armor_effects.disabled")
                : this.pages.isEmpty()
                ? this.tab.noneMessage()
                : Component.translatable(this.tab.translationKey + ".hint");
        graphics.drawCenteredString(this.font, subtitle, this.width / 2, 33, 0xFFA0A0A0);
    }

    private void swapTo(Tab other) {
        this.minecraft.setScreen(other.createScreen());
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        for (Tab other : Tab.values()) {
            if (other.key.matches(keyCode, scanCode)) {
                if (other == this.tab) this.onClose();
                else if (other.hasEffects(this.minecraft.player)) swapTo(other);
                return true;
            }
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    /** @return the y of the bottom row of buttons: below all {@link #ROWS} rows, or the screen's bottom if that's too small */
    protected int bottom() {
        return Math.min(TOP + ROWS * (BUTTON_HEIGHT + GAP) + 6, this.height - BUTTON_HEIGHT - 4);
    }

    /** @return the rows of effects that fit above the {@link #bottom()} row */
    protected int rows() {
        return Math.clamp((bottom() - TOP) / (BUTTON_HEIGHT + GAP), 1, ROWS);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
