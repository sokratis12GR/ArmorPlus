package com.sofodev.armorplus.client;

import com.sofodev.armorplus.network.ToggleEffectPayload;
import net.minecraft.ChatFormatting;

import java.util.ArrayList;
import java.util.List;

/**
 * Lists the effects of the held tool: its material's own effects (e.g. Infused Lava's Auto-Smelt) and the tool runes
 * socketed into it. Each can be switched on/off (sent to the server, see {@link ToggleEffectPayload}); a switched off
 * effect stays off for every tool. Separate from the {@link ArmorEffectsScreen}: tool effects are not armor effects.
 */
public class ToolEffectsScreen extends EffectsScreen {

    public ToolEffectsScreen() {
        super(Tab.TOOL);
    }

    @Override
    protected List<Effect> effects() {
        return onOffEffects(this.minecraft.player.getMainHandItem().getHoverName().copy().withStyle(ChatFormatting.GOLD));
    }

    @Override
    protected List<String> state() {
        List<String> state = new ArrayList<>(super.state());
        state.add(this.minecraft.player.getMainHandItem().getHoverName().getString());
        return state;
    }
}
