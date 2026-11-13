package com.sofodev.armorplus.client;

import com.sofodev.armorplus.api.EffectEntry;
import com.sofodev.armorplus.api.EffectScope;
import com.sofodev.armorplus.api.EffectScopes;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.network.ToggleEffectPayload;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.utils.ToolTipUtils;
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
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    /** The tabs share the width of the two columns of effects. */
    private static final int TABS_WIDTH = COLUMNS * BUTTON_WIDTH + (COLUMNS - 1) * GAP;
    private static final int TAB_HEIGHT = 18;
    private static final int TAB_GAP = 5;
    private static final int TAB_TOP = 2;
    private static final int TAB_INDICATOR_HEIGHT = 2;
    private static final int TAB_INDICATOR_COLOR = 0xFFFFAA00;

    /**
     * A tab of the effects menus: the scope it lists, the key that opens it and its screen. Its texts use the translation
     * keys "screen.armorplus.&lt;scope&gt;_effects" (title), ".tab", ".none" and ".hint".
     * <p>
     * A new scope only needs an {@link EffectsScreen} subclass and a {@link #register registered} tab, its key is
     * registered and handled by {@link ArmorPlusKeys}. Register it before the key mappings are (during mod construction).
     */
    public record Tab(EffectScope scope, KeyMapping key, Supplier<EffectsScreen> screen) {

        private static final List<Tab> TABS = new ArrayList<>();

        public static final Tab ARMOR = register(new Tab(EffectScopes.ARMOR, ArmorPlusKeys.ARMOR_EFFECTS, ArmorEffectsScreen::new));
        public static final Tab TOOL = register(new Tab(EffectScopes.TOOL, ArmorPlusKeys.TOOL_EFFECTS, ToolEffectsScreen::new));
        public static final Tab WEAPON = register(new Tab(EffectScopes.WEAPON, ArmorPlusKeys.WEAPON_EFFECTS, WeaponEffectsScreen::new));
        public static final Tab RANGED = register(new Tab(EffectScopes.RANGED, ArmorPlusKeys.RANGED_EFFECTS, RangedEffectsScreen::new));

        /** Adds a tab after the others. */
        public static synchronized Tab register(Tab tab) {
            if (TABS.stream().anyMatch(other -> other.scope() == tab.scope())) {
                throw new IllegalArgumentException("A tab for the effect scope '" + tab.scope().name() + "' is already registered");
            }
            TABS.add(tab);
            return tab;
        }

        /** @return the registered tabs, in order */
        public static synchronized List<Tab> all() {
            return List.copyOf(TABS);
        }

        private String translationKey() {
            return "screen.armorplus." + this.scope.name() + "_effects";
        }

        /** @return true if the player has anything to show in this tab's menu */
        public boolean hasEffects(Player player) {
            // the client keeps its own cache of the worn and held runes, which isn't invalidated by server events
            this.scope.invalidate(player);
            return !this.scope.entries(player).isEmpty();
        }

        public EffectsScreen createScreen() {
            return this.screen().get();
        }

        /** @return why this tab's menu has nothing to show, e.g. "Your held tool has no tool runes" */
        public Component noneMessage() {
            return Component.translatable(translationKey() + ".none");
        }

        private MutableComponent label() {
            return Component.translatable(translationKey() + ".tab");
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

    /** The section of socketed runes, every menu gives them their own pages. */
    protected static final String RUNES = "rune";

    private record Page(Component title, List<Effect> effects) {
    }

    private final Tab tab;
    private List<Page> pages = List.of();
    /** Keys and states shown, to rebuild the buttons when the toggles (synced) or the equipment change. */
    private List<String> shownState = List.of();
    private int page;

    protected EffectsScreen(Tab tab) {
        super(Component.translatable(tab.translationKey()));
        this.tab = tab;
    }

    /** @return the scope of the effects this screen lists */
    protected final EffectScope scope() {
        return this.tab.scope();
    }

    /** @return the player's entries of this screen's scope */
    protected final List<EffectEntry> entries() {
        return scope().entries(this.minecraft.player);
    }

    /** @return the action of a button: switches the entry's effect on/off on the server */
    protected final Runnable toggle(EffectEntry entry) {
        return () -> ClientPacketDistributor.sendToServer(new ToggleEffectPayload(scope(), entry.toggleKey()));
    }

    /** @return the effects to list, by section */
    protected abstract List<Effect> effects();

    /**
     * @return what the list depends on, the buttons are rebuilt when it changes; by default the toggle key, level, source,
     * worn pieces and on/off of each entry
     */
    protected List<String> state() {
        List<String> disabled = this.minecraft.player.getData(scope().disabledEffects());
        return entries().stream().map(e -> e.toggleKey() + "|" + e.source() + "|" + e.worn() + "|" + disabled.contains(e.toggleKey()) + "|" + e.buff().getAmplifier()).toList();
    }

    /** Clears what the client caches of the effects. */
    protected void reload() {
        scope().invalidate(this.minecraft.player);
    }

    protected static Component runesTitle() {
        return Component.translatable("screen.armorplus.armor_effects.runes").withStyle(ChatFormatting.GOLD);
    }

    /**
     * @param materialTitle the title of the page of the held item's own material effects
     * @return one on/off button per entry: the runes on their own pages, the material effects on the other
     */
    protected final List<Effect> onOffEffects(Component materialTitle) {
        return entries().stream().map(entry -> new Effect(entry.fromRune() ? RUNES : entry.source(), entry.fromRune() ? runesTitle() : materialTitle,
                onOffLabel(entry.buff(), entry.disabled()), ToolTipUtils.buffTooltip(entry.buff()), true, toggle(entry))).toList();
    }

    /** @return e.g. "Haste II: On" */
    protected static Component onOffLabel(BuffInstance buff, boolean disabled) {
        return Component.empty().append(ToolTipUtils.buffName(buff, true)).append(": ").append(disabled
                ? Component.translatable("options.off").withStyle(ChatFormatting.RED)
                : Component.translatable("options.on").withStyle(ChatFormatting.GREEN));
    }

    @Override
    protected void init() {
        for (Tab other : Tab.all()) {
            boolean current = other == this.tab;
            boolean available = current || other.hasEffects(this.minecraft.player);
            Button.Builder builder = Button.builder(current ? other.label().withStyle(ChatFormatting.GOLD) : other.label(), b -> swapTo(other))
                    .bounds(tabX(other), TAB_TOP, tabWidth(), TAB_HEIGHT);
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

    /** @return the width of each tab: 72 for four tabs */
    private static int tabWidth() {
        int count = Tab.all().size();
        return (TABS_WIDTH - (count - 1) * TAB_GAP) / count;
    }

    private int tabX(Tab tab) {
        List<Tab> tabs = Tab.all();
        int left = this.width / 2 - (tabs.size() * tabWidth() + (tabs.size() - 1) * TAB_GAP) / 2;
        return left + tabs.indexOf(tab) * (tabWidth() + TAB_GAP);
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);
        // marks the current tab, a disabled button alone looks the same as a tab without anything to show
        int x = tabX(this.tab);
        int y = TAB_TOP + TAB_HEIGHT;
        graphics.fill(x, y, x + tabWidth(), y + TAB_INDICATOR_HEIGHT, TAB_INDICATOR_COLOR);

        if (!this.pages.isEmpty()) {
            MutableComponent pageTitle = this.pages.get(this.page).title().copy();
            if (this.pages.size() > 1) pageTitle.append(Component.literal("  " + (this.page + 1) + "/" + this.pages.size()).withStyle(ChatFormatting.GRAY));
            graphics.centeredText(this.font, pageTitle, this.width / 2, 23, 0xFFFFFFFF);
        }
        Component subtitle = !ArmorPlusConfig.allowEffectToggles.get()
                ? Component.translatable("screen.armorplus.armor_effects.disabled")
                : this.pages.isEmpty()
                ? this.tab.noneMessage()
                : Component.translatable(this.tab.translationKey() + ".hint");
        graphics.centeredText(this.font, subtitle, this.width / 2, 33, 0xFFA0A0A0);
    }

    private void swapTo(Tab other) {
        this.minecraft.setScreen(other.createScreen());
    }

    @Override
    public boolean keyPressed(KeyEvent event) {
        for (Tab other : Tab.all()) {
            if (other.key().matches(event)) {
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

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
