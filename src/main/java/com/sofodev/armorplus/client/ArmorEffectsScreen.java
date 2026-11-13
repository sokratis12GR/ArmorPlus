package com.sofodev.armorplus.client;

import com.sofodev.armorplus.api.EffectEntry;
import com.sofodev.armorplus.network.ToggleEffectPayload;
import com.sofodev.armorplus.registry.item.extra.BuffNames;
import com.sofodev.armorplus.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.List;

/**
 * Lists the effects of the worn ArmorPlus sets and socketed runes, one page per set (and one for the runes). Available
 * effects can be switched on/off for that set (sent to the server, see {@link ToggleEffectPayload}); effects that need
 * more worn pieces are shown greyed out.
 */
public class ArmorEffectsScreen extends EffectsScreen {

    public ArmorEffectsScreen() {
        super(Tab.ARMOR);
    }

    @Override
    protected List<Effect> effects() {
        return entries().stream().map(entry -> new Effect(entry.fromRune() ? RUNES : entry.source(), sourceName(entry), label(entry), tooltip(entry), entry.available(),
                toggle(entry))).toList();
    }

    /** @return "Coal Set", "Enhanced Iron Set", ... or "Runes" */
    private static Component sourceName(EffectEntry entry) {
        if (entry.fromRune()) return runesTitle();
        return Component.translatable("screen.armorplus.armor_effects.set", Component.translatable("armorplus.configuration." + entry.source())).withStyle(ChatFormatting.GOLD);
    }

    private static Component label(EffectEntry entry) {
        MutableComponent label = Component.empty();
        if (entry.fromRune()) {
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

    private static Component tooltip(EffectEntry entry) {
        MutableComponent tooltip = ToolTipUtils.buffTooltip(entry.buff()).append("\n");
        if (entry.fromRune()) {
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
    private static boolean needsSomeSlots(EffectEntry entry) {
        return entry.required() < entry.slots().size();
    }
}
