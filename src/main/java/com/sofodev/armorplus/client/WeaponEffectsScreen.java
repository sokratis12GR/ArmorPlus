package com.sofodev.armorplus.client;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.network.ToggleWeaponEffectPayload;
import com.sofodev.armorplus.registry.ModAttachments;
import com.sofodev.armorplus.registry.item.tool.WeaponEffects;
import com.sofodev.armorplus.registry.item.tool.WeaponEffects.Entry;
import com.sofodev.armorplus.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import java.util.List;

/**
 * Lists the on-hit effects of the held weapon (its material's effects and socketed weapon runes). Each can be switched
 * on/off (sent to the server, see {@link ToggleWeaponEffectPayload}); a switched off effect stays off for every weapon.
 * Separate from the {@link ArmorEffectsScreen} and {@link ToolEffectsScreen}.
 */
public class WeaponEffectsScreen extends EffectsScreen {

    /** Ids and states shown, to rebuild the buttons when the toggles (synced) or the held weapon change. */
    private List<String> shownState = List.of();

    public WeaponEffectsScreen() {
        super(Tab.WEAPON);
    }

    @Override
    protected void init() {
        super.init();
        List<Entry> entries = WeaponEffects.entries(this.minecraft.player);
        this.shownState = state(entries);

        boolean toggles = ArmorPlusConfig.allowEffectToggles.get();
        int left = this.width / 2 - (COLUMNS * BUTTON_WIDTH + (COLUMNS - 1) * GAP) / 2;
        // a weapon has a few material effects and a handful of runes at most, one page is plenty
        List<Entry> shown = entries.subList(0, Math.min(entries.size(), COLUMNS * rows()));
        for (int i = 0; i < shown.size(); i++) {
            Entry entry = shown.get(i);
            int x = left + (i % COLUMNS) * (BUTTON_WIDTH + GAP);
            int y = TOP + (i / COLUMNS) * (BUTTON_HEIGHT + GAP);
            Button button = Button.builder(label(entry), b -> ClientPacketDistributor.sendToServer(new ToggleWeaponEffectPayload(entry.id())))
                    .bounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT)
                    .tooltip(Tooltip.create(ToolTipUtils.buffName(entry.buff(), false)))
                    .build();
            button.active = toggles;
            this.addRenderableWidget(button);
        }

        addDoneButton();
    }

    @Override
    public void tick() {
        super.tick();
        // the server confirms toggles by syncing the player's data; the held item can change while the screen is open
        if (!state(WeaponEffects.entries(this.minecraft.player)).equals(this.shownState)) this.rebuildWidgets();
    }

    private List<String> state(List<Entry> entries) {
        List<String> disabled = this.minecraft.player.getData(ModAttachments.DISABLED_WEAPON_EFFECTS.get());
        return entries.stream().map(e -> e.id() + "|" + disabled.contains(e.id()) + "|" + e.buff().getAmplifier()).toList();
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);
        ItemStack weapon = this.minecraft.player.getMainHandItem();
        if (!weapon.isEmpty()) {
            graphics.centeredText(this.font, weapon.getHoverName().copy().withStyle(ChatFormatting.GOLD), this.width / 2, 23, 0xFFFFFFFF);
        }
        Component subtitle = !ArmorPlusConfig.allowEffectToggles.get()
                ? Component.translatable("screen.armorplus.armor_effects.disabled")
                : this.shownState.isEmpty()
                ? Component.translatable("screen.armorplus.weapon_effects.none")
                : Component.translatable("screen.armorplus.weapon_effects.hint");
        graphics.centeredText(this.font, subtitle, this.width / 2, 33, 0xFFA0A0A0);
    }

    private static Component label(Entry entry) {
        MutableComponent label = Component.empty().append(ToolTipUtils.buffName(entry.buff(), true));
        return label.append(": ").append(entry.disabled()
                ? Component.translatable("options.off").withStyle(ChatFormatting.RED)
                : Component.translatable("options.on").withStyle(ChatFormatting.GREEN));
    }
}
