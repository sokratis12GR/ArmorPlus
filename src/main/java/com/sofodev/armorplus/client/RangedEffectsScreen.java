package com.sofodev.armorplus.client;

import com.sofodev.armorplus.network.ToggleEffectPayload;
import com.sofodev.armorplus.registry.item.tool.RangedEffects;
import net.minecraft.ChatFormatting;

import java.util.ArrayList;
import java.util.List;

/**
 * Lists the ranged effects of the held bow or crossbow (its material's effects and socketed ranged runes). Each can be
 * switched on/off (sent to the server, see {@link ToggleEffectPayload}); a switched off effect stays off for every bow and
 * crossbow, and for arrows fired from then on.
 */
public class RangedEffectsScreen extends EffectsScreen {

    public RangedEffectsScreen() {
        super(Tab.RANGED);
    }

    @Override
    protected List<Effect> effects() {
        return onOffEffects(RangedEffects.heldWeapon(this.minecraft.player).getHoverName().copy().withStyle(ChatFormatting.GOLD));
    }

    @Override
    protected List<String> state() {
        List<String> state = new ArrayList<>(super.state());
        state.add(RangedEffects.heldWeapon(this.minecraft.player).getHoverName().getString());
        return state;
    }
}
