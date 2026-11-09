package com.sofodev.armorplus.client;

import com.sofodev.armorplus.network.ToggleEffectPayload;
import com.sofodev.armorplus.registry.ModAttachments;
import com.sofodev.armorplus.registry.item.tool.WeaponEffects;
import com.sofodev.armorplus.registry.item.tool.WeaponEffects.Entry;
import com.sofodev.armorplus.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

/**
 * Lists the on-hit effects of the held weapon's material. Each can be switched on/off (sent to the server, see
 * {@link ToggleEffectPayload}); a switched off effect stays off for every weapon.
 */
public class WeaponEffectsScreen extends EffectsScreen {

    private static final String MATERIAL = "material";

    public WeaponEffectsScreen() {
        super(Tab.WEAPON);
    }

    @Override
    protected List<Effect> effects() {
        Component weapon = this.minecraft.player.getMainHandItem().getHoverName().copy().withStyle(ChatFormatting.GOLD);
        return WeaponEffects.entries(this.minecraft.player).stream().map(entry -> new Effect(entry.rune() ? RUNES : MATERIAL, entry.rune() ? runesTitle() : weapon,
                onOffLabel(entry.buff(), entry.disabled()), ToolTipUtils.buffTooltip(entry.buff()), true,
                () -> PacketDistributor.sendToServer(new ToggleEffectPayload(ToggleEffectPayload.Scope.WEAPON, entry.id())))).toList();
    }

    @Override
    protected List<String> state() {
        List<String> disabled = this.minecraft.player.getData(ModAttachments.DISABLED_WEAPON_EFFECTS.get());
        List<String> state = new ArrayList<>();
        state.add(this.minecraft.player.getMainHandItem().getHoverName().getString());
        for (Entry e : WeaponEffects.entries(this.minecraft.player)) state.add(e.id() + "|" + disabled.contains(e.id()) + "|" + e.buff().getAmplifier() + "|" + e.rune());
        return state;
    }
}
