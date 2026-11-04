package com.sofodev.armorplus.client;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.network.ToggleToolEffectPayload;
import com.sofodev.armorplus.registry.ModAttachments;
import com.sofodev.armorplus.registry.item.tool.ToolEffects;
import com.sofodev.armorplus.registry.item.tool.ToolEffects.Entry;
import com.sofodev.armorplus.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import java.util.List;

/**
 * Lists the effects of the tool runes socketed into the held tool. Each can be switched on/off (sent to the server, see
 * {@link ToggleToolEffectPayload}); a switched off effect stays off for every tool. Separate from the
 * {@link ArmorEffectsScreen}: tool effects are not armor effects.
 */
public class ToolEffectsScreen extends Screen {

    private static final int COLUMNS = 2;
    private static final int ROWS = 9;
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 20;
    private static final int GAP = 4;
    private static final int TOP = 44;

    /** Ids and states shown, to rebuild the buttons when the toggles (synced) or the held tool change. */
    private List<String> shownState = List.of();

    public ToolEffectsScreen() {
        super(Component.translatable("screen.armorplus.tool_effects"));
    }

    @Override
    protected void init() {
        // the client keeps its own cache of the held tool's runes, which isn't invalidated by server events
        ToolEffects.invalidate(this.minecraft.player);
        List<Entry> entries = ToolEffects.entries(this.minecraft.player);
        this.shownState = state(entries);

        boolean toggles = ArmorPlusConfig.allowEffectToggles.get();
        int left = this.width / 2 - (COLUMNS * BUTTON_WIDTH + (COLUMNS - 1) * GAP) / 2;
        // a tool holds a handful of runes at most ("maxSockets"), one page is plenty
        List<Entry> shown = entries.subList(0, Math.min(entries.size(), COLUMNS * ROWS));
        for (int i = 0; i < shown.size(); i++) {
            Entry entry = shown.get(i);
            int x = left + (i % COLUMNS) * (BUTTON_WIDTH + GAP);
            int y = TOP + (i / COLUMNS) * (BUTTON_HEIGHT + GAP);
            Button button = Button.builder(label(entry), b -> ClientPacketDistributor.sendToServer(new ToggleToolEffectPayload(entry.id())))
                    .bounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT)
                    .tooltip(Tooltip.create(ToolTipUtils.buffName(entry.buff(), false)))
                    .build();
            button.active = toggles;
            this.addRenderableWidget(button);
        }

        int bottom = TOP + ROWS * (BUTTON_HEIGHT + GAP) + 6;
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, b -> this.onClose())
                .bounds(this.width / 2 - 100, bottom, 200, BUTTON_HEIGHT).build());
    }

    @Override
    public void tick() {
        super.tick();
        // the server confirms toggles by syncing the player's data; the held item can change while the screen is open
        if (this.minecraft.player.tickCount % 10 == 0) ToolEffects.invalidate(this.minecraft.player);
        if (!state(ToolEffects.entries(this.minecraft.player)).equals(this.shownState)) this.rebuildWidgets();
    }

    private List<String> state(List<Entry> entries) {
        List<String> disabled = this.minecraft.player.getData(ModAttachments.DISABLED_TOOL_EFFECTS.get());
        return entries.stream().map(e -> e.id() + "|" + disabled.contains(e.id()) + "|" + e.buff().getAmplifier()).toList();
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);
        graphics.centeredText(this.font, this.title, this.width / 2, 12, 0xFFFFFFFF);
        ItemStack tool = this.minecraft.player.getMainHandItem();
        if (!tool.isEmpty()) {
            graphics.centeredText(this.font, tool.getHoverName().copy().withStyle(ChatFormatting.GOLD), this.width / 2, 23, 0xFFFFFFFF);
        }
        Component subtitle = !ArmorPlusConfig.allowEffectToggles.get()
                ? Component.translatable("screen.armorplus.armor_effects.disabled")
                : this.shownState.isEmpty()
                ? Component.translatable("screen.armorplus.tool_effects.none")
                : Component.translatable("screen.armorplus.tool_effects.hint");
        graphics.centeredText(this.font, subtitle, this.width / 2, 33, 0xFFA0A0A0);
    }

    private static Component label(Entry entry) {
        MutableComponent label = Component.empty().append(ToolTipUtils.buffName(entry.buff(), true));
        return label.append(": ").append(entry.disabled()
                ? Component.translatable("options.off").withStyle(ChatFormatting.RED)
                : Component.translatable("options.on").withStyle(ChatFormatting.GREEN));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
