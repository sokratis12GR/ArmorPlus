package com.sofodev.armorplus.client;

import com.sofodev.armorplus.network.ToggleEffectPayload;
import com.sofodev.armorplus.registry.ModAttachments;
import com.sofodev.armorplus.registry.item.tool.ToolEffects;
import com.sofodev.armorplus.registry.item.tool.ToolEffects.Entry;
import com.sofodev.armorplus.utils.ToolTipUtils;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import java.util.List;

/**
 * Lists the effects of the tool runes socketed into the held tool. Each can be switched on/off (sent to the server, see
 * {@link ToggleEffectPayload}); a switched off effect stays off for every tool. Separate from the
 * {@link ArmorEffectsScreen}: tool effects are not armor effects.
 */
public class ToolEffectsScreen extends EffectsScreen {

    public ToolEffectsScreen() {
        super(Tab.TOOL);
    }

    @Override
    protected void reload() {
        // the client keeps its own cache of the held tool's runes, which isn't invalidated by server events
        ToolEffects.invalidate(this.minecraft.player);
    }

    @Override
    protected List<Effect> effects() {
        return ToolEffects.entries(this.minecraft.player).stream().map(entry -> new Effect(RUNES, runesTitle(), onOffLabel(entry.buff(), entry.disabled()),
                ToolTipUtils.buffTooltip(entry.buff()), true,
                () -> ClientPacketDistributor.sendToServer(new ToggleEffectPayload(ToggleEffectPayload.Scope.TOOL, entry.id())))).toList();
    }

    @Override
    protected List<String> state() {
        List<Entry> entries = ToolEffects.entries(this.minecraft.player);
        List<String> disabled = this.minecraft.player.getData(ModAttachments.DISABLED_TOOL_EFFECTS.get());
        return entries.stream().map(e -> e.id() + "|" + disabled.contains(e.id()) + "|" + e.buff().getAmplifier()).toList();
    }
}
