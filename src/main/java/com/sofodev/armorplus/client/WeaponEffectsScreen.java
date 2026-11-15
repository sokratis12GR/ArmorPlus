package com.sofodev.armorplus.client;

import com.sofodev.armorplus.network.ToggleEffectPayload;
import net.minecraft.ChatFormatting;

import java.util.ArrayList;
import java.util.List;

/**
 * Lists the on-hit effects of the held weapon (its material's effects and socketed weapon runes). Each can be switched
 * on/off (sent to the server, see {@link ToggleEffectPayload}); a switched off effect stays off for every weapon.
 * Separate from the {@link ArmorEffectsScreen} and {@link ToolEffectsScreen}.
 */
public class WeaponEffectsScreen extends EffectsScreen {

    public WeaponEffectsScreen() {
        super(Tab.WEAPON);
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
