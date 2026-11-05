package com.sofodev.armorplus.client;

import com.sofodev.armorplus.network.ToggleEffectPayload;
import com.sofodev.armorplus.registry.ModAttachments;
import com.sofodev.armorplus.registry.item.tool.RangedEffects;
import com.sofodev.armorplus.registry.item.tool.RangedEffects.Entry;
import com.sofodev.armorplus.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import java.util.ArrayList;
import java.util.List;

/**
 * Lists the ranged effects of the held bow or crossbow (its material's effects and socketed ranged runes). Each can be
 * switched on/off (sent to the server, see {@link ToggleEffectPayload}); a switched off effect stays off for every bow and
 * crossbow, and for arrows fired from then on.
 */
public class RangedEffectsScreen extends EffectsScreen {

    private static final String MATERIAL = "material";

    public RangedEffectsScreen() {
        super(Tab.RANGED);
    }

    @Override
    protected List<Effect> effects() {
        Component weapon = RangedEffects.heldWeapon(this.minecraft.player).getHoverName().copy().withStyle(ChatFormatting.GOLD);
        return RangedEffects.entries(this.minecraft.player).stream().map(entry -> new Effect(entry.rune() ? RUNES : MATERIAL, entry.rune() ? runesTitle() : weapon,
                onOffLabel(entry.buff(), entry.disabled()), ToolTipUtils.buffTooltip(entry.buff()), true,
                () -> ClientPacketDistributor.sendToServer(new ToggleEffectPayload(ToggleEffectPayload.Scope.RANGED, entry.id())))).toList();
    }

    @Override
    protected List<String> state() {
        List<String> disabled = this.minecraft.player.getData(ModAttachments.DISABLED_RANGED_EFFECTS.get());
        List<String> state = new ArrayList<>();
        state.add(RangedEffects.heldWeapon(this.minecraft.player).getHoverName().getString());
        for (Entry e : RangedEffects.entries(this.minecraft.player)) state.add(e.id() + "|" + disabled.contains(e.id()) + "|" + e.buff().getAmplifier() + "|" + e.rune());
        return state;
    }
}
