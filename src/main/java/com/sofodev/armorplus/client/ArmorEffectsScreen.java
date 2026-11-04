package com.sofodev.armorplus.client;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.network.ToggleEffectPayload;
import com.sofodev.armorplus.registry.ModAttachments;
import com.sofodev.armorplus.registry.item.armor.ArmorEffects;
import com.sofodev.armorplus.registry.item.armor.ArmorEffects.Entry;
import com.sofodev.armorplus.registry.item.extra.BuffNames;
import com.sofodev.armorplus.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Lists the effects of the worn ArmorPlus sets and socketed runes, one page per set (and one for the runes). Available
 * effects can be switched on/off for that set (sent to the server, see {@link ToggleEffectPayload}); effects that need
 * more worn pieces are shown greyed out.
 */
public class ArmorEffectsScreen extends EffectsScreen {

    /** One page per source and as many effects as fit (a source with more effects gets several pages). */
    private record Page(String source, List<Entry> entries) {
    }

    private List<Page> pages = List.of();
    /** Keys and states shown, to rebuild the buttons when the toggles (synced) or the equipment change. */
    private List<String> shownState = List.of();
    private int page;

    public ArmorEffectsScreen() {
        super(Tab.ARMOR);
    }

    @Override
    protected void init() {
        super.init();
        // the client keeps its own cache of the worn runes, which isn't invalidated by server events
        ArmorEffects.invalidate(this.minecraft.player);
        List<Entry> entries = ArmorEffects.entries(this.minecraft.player);
        this.shownState = state(entries);
        this.pages = paginate(entries, COLUMNS * rows());
        this.page = Math.clamp(this.page, 0, Math.max(0, this.pages.size() - 1));

        boolean toggles = ArmorPlusConfig.allowEffectToggles.get();
        int left = this.width / 2 - (COLUMNS * BUTTON_WIDTH + (COLUMNS - 1) * GAP) / 2;
        List<Entry> shown = this.pages.isEmpty() ? List.of() : this.pages.get(this.page).entries();
        for (int i = 0; i < shown.size(); i++) {
            Entry entry = shown.get(i);
            int x = left + (i % COLUMNS) * (BUTTON_WIDTH + GAP);
            int y = TOP + (i / COLUMNS) * (BUTTON_HEIGHT + GAP);
            Button button = Button.builder(label(entry), b -> ClientPacketDistributor.sendToServer(new ToggleEffectPayload(entry.toggleKey())))
                    .bounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT)
                    .tooltip(Tooltip.create(tooltip(entry)))
                    .build();
            button.active = toggles && entry.available();
            this.addRenderableWidget(button);
        }

        int bottom = bottom();
        if (this.pages.size() > 1) {
            this.addRenderableWidget(Button.builder(Component.literal("<"), b -> changePage(-1))
                    .bounds(this.width / 2 - 100 - 24, bottom, 20, BUTTON_HEIGHT).build()).active = this.page > 0;
            this.addRenderableWidget(Button.builder(Component.literal(">"), b -> changePage(1))
                    .bounds(this.width / 2 + 104, bottom, 20, BUTTON_HEIGHT).build()).active = this.page < this.pages.size() - 1;
        }
        addDoneButton();
    }

    private static List<Page> paginate(List<Entry> entries, int perPage) {
        Map<String, List<Entry>> bySource = new LinkedHashMap<>();
        for (Entry entry : entries) bySource.computeIfAbsent(entry.source(), s -> new ArrayList<>()).add(entry);
        List<Page> pages = new ArrayList<>();
        bySource.forEach((source, list) -> {
            for (int start = 0; start < list.size(); start += perPage) {
                pages.add(new Page(source, List.copyOf(list.subList(start, Math.min(list.size(), start + perPage)))));
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
        if (this.minecraft.player.tickCount % 10 == 0) ArmorEffects.invalidate(this.minecraft.player);
        if (!state(ArmorEffects.entries(this.minecraft.player)).equals(this.shownState)) this.rebuildWidgets();
    }

    private List<String> state(List<Entry> entries) {
        List<String> disabled = this.minecraft.player.getData(ModAttachments.DISABLED_EFFECTS.get());
        return entries.stream()
                .map(e -> e.toggleKey() + "|" + e.worn() + "|" + disabled.contains(e.toggleKey()) + "|" + e.buff().getAmplifier())
                .toList();
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);
        if (!this.pages.isEmpty()) {
            Component pageTitle = sourceName(this.pages.get(this.page).source()).copy().withStyle(ChatFormatting.GOLD);
            if (this.pages.size() > 1) pageTitle = pageTitle.copy().append(Component.literal("  " + (this.page + 1) + "/" + this.pages.size()).withStyle(ChatFormatting.GRAY));
            graphics.centeredText(this.font, pageTitle, this.width / 2, 23, 0xFFFFFFFF);
        }
        Component subtitle = !ArmorPlusConfig.allowEffectToggles.get()
                ? Component.translatable("screen.armorplus.armor_effects.disabled")
                : this.pages.isEmpty()
                ? Component.translatable("screen.armorplus.armor_effects.none")
                : Component.translatable("screen.armorplus.armor_effects.hint");
        graphics.centeredText(this.font, subtitle, this.width / 2, 33, 0xFFA0A0A0);
    }

    /** @return "Coal Set", "Enhanced Iron Set", ... or "Runes" */
    private static Component sourceName(String source) {
        if (source.equals(ArmorEffects.RUNE_SOURCE)) return Component.translatable("screen.armorplus.armor_effects.runes");
        return Component.translatable("screen.armorplus.armor_effects.set", Component.translatable("armorplus.configuration." + source));
    }

    private static Component label(Entry entry) {
        MutableComponent label = Component.empty();
        if (entry.source().equals(ArmorEffects.RUNE_SOURCE)) {
            label.append(ToolTipUtils.buffName(entry.buff(), true));
        } else {
            label.append(Component.literal("[" + Math.min(entry.worn(), entry.required()) + "/" + entry.required() + "] ")
                    .withStyle(entry.available() ? ChatFormatting.GRAY : ChatFormatting.DARK_GRAY));
            label.append(ToolTipUtils.buffName(entry.buff(), true));
        }
        if (!entry.available()) return label.withStyle(ChatFormatting.GRAY);
        return label.append(": ").append(entry.disabled()
                ? Component.translatable("options.off").withStyle(ChatFormatting.RED)
                : Component.translatable("options.on").withStyle(ChatFormatting.GREEN));
    }

    private static Component tooltip(Entry entry) {
        MutableComponent tooltip = Component.empty().append(ToolTipUtils.buffName(entry.buff(), false)).append("\n");
        if (entry.source().equals(ArmorEffects.RUNE_SOURCE)) {
            tooltip.append(Component.translatable("screen.armorplus.armor_effects.from_rune"));
        } else if (entry.slots().isEmpty()) {
            tooltip.append(Component.translatable("screen.armorplus.armor_effects.from_set", entry.worn(), entry.required()));
        } else if (needsSomeSlots(entry)) {
            tooltip.append(Component.translatable("screen.armorplus.armor_effects.from_some_pieces", entry.required(), BuffNames.slotNames(entry.slots())));
        } else {
            tooltip.append(Component.translatable("screen.armorplus.armor_effects.from_piece", BuffNames.slotNames(entry.slots())));
        }
        if (!entry.available()) {
            int missing = entry.required() - entry.worn();
            MutableComponent needs;
            if (entry.slots().isEmpty()) needs = Component.translatable("screen.armorplus.armor_effects.needs_pieces", missing);
            else if (needsSomeSlots(entry)) needs = Component.translatable("screen.armorplus.armor_effects.needs_some_pieces", missing, BuffNames.slotNames(entry.slots()));
            else needs = Component.translatable("screen.armorplus.armor_effects.needs_piece", BuffNames.slotNames(entry.slots()));
            tooltip.append("\n").append(needs.withStyle(ChatFormatting.RED));
        }
        return tooltip;
    }

    /** @return true if the effect only needs some of its pieces, e.g. "@chest,legs,feet:2" */
    private static boolean needsSomeSlots(Entry entry) {
        return entry.required() < entry.slots().size();
    }
}
