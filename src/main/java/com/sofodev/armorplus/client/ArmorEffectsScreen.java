package com.sofodev.armorplus.client;

import com.sofodev.armorplus.network.ToggleEffectPayload;
import com.sofodev.armorplus.registry.ModAttachments;
import com.sofodev.armorplus.registry.item.armor.ArmorEffects;
import com.sofodev.armorplus.registry.item.armor.ArmorEffects.Entry;
import com.sofodev.armorplus.registry.item.extra.BuffNames;
import com.sofodev.armorplus.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

/**
 * Lists the effects of the worn ArmorPlus sets, one page per set. Available effects can be switched on/off for that set
 * (sent to the server, see {@link ToggleEffectPayload}); effects that need more worn pieces are shown greyed out.
 */
public class ArmorEffectsScreen extends EffectsScreen {

    public ArmorEffectsScreen() {
        super(Tab.ARMOR);
    }

    @Override
    protected void reload() {
        // the client keeps its own cache of the worn runes, which isn't invalidated by server events
        ArmorEffects.invalidate(this.minecraft.player);
    }

    @Override
    protected List<Effect> effects() {
        return ArmorEffects.entries(this.minecraft.player).stream().map(entry -> new Effect(section(entry), sourceName(entry.source()), label(entry), tooltip(entry), entry.available(),
                () -> PacketDistributor.sendToServer(new ToggleEffectPayload(ToggleEffectPayload.Scope.ARMOR, entry.toggleKey())))).toList();
    }

    private static String section(Entry entry) {
        return entry.source().equals(ArmorEffects.RUNE_SOURCE) ? RUNES : entry.source();
    }

    @Override
    protected List<String> state() {
        List<String> disabled = this.minecraft.player.getData(ModAttachments.DISABLED_EFFECTS.get());
        return ArmorEffects.entries(this.minecraft.player).stream()
                .map(e -> e.toggleKey() + "|" + e.worn() + "|" + disabled.contains(e.toggleKey()) + "|" + e.buff().getAmplifier())
                .toList();
    }

    /** @return "Coal Set", "Enhanced Iron Set", ... or "Runes" */
    private static Component sourceName(String source) {
        if (source.equals(ArmorEffects.RUNE_SOURCE)) return runesTitle();
        return Component.translatable("screen.armorplus.armor_effects.set", Component.translatable("armorplus.configuration." + source)).withStyle(ChatFormatting.GOLD);
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
        MutableComponent tooltip = ToolTipUtils.buffTooltip(entry.buff()).append("\n");
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
