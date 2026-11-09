package com.sofodev.armorplus.client;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.item.armor.ArmorEffects;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.armorplus.registry.item.tool.RangedEffects;
import com.sofodev.armorplus.registry.item.tool.ToolEffects;
import com.sofodev.armorplus.registry.item.tool.WeaponEffects;
import com.sofodev.armorplus.api.RuneData;
import com.sofodev.armorplus.api.RuneKind;
import com.sofodev.armorplus.registry.rune.Runes;
import com.sofodev.armorplus.utils.ToolTipUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.ArrayList;
import java.util.List;

import static com.sofodev.armorplus.utils.ToolTipUtils.DETAILS_HINT;
import static com.sofodev.armorplus.utils.ToolTipUtils.addBuffLines;
import static com.sofodev.armorplus.utils.ToolTipUtils.addDetailsHint;
import static com.sofodev.armorplus.utils.ToolTipUtils.isSneakDown;
import static com.sofodev.armorplus.utils.ToolTipUtils.runeSuffix;
import static com.sofodev.armorplus.utils.ToolTipUtils.showInfo;
import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.ChatFormatting.DARK_GRAY;
import static net.minecraft.ChatFormatting.LIGHT_PURPLE;

/**
 * Lists the socketed runes on any equipment that can hold runes (ArmorPlus, vanilla and other mods).
 * <p>
 * The socket count is always shown, the runes themselves while Shift is held. The lines go right below the ArmorPlus
 * buff lines (or the "Press Shift" hint), or below the item name for equipment without ArmorPlus buffs.
 */
@EventBusSubscriber(modid = ArmorPlus.MODID, value = Dist.CLIENT)
public final class RuneTooltipEvents {

    private static final String SHIFT_HINT = "tooltip.armorplus.shift.showinfo";

    private RuneTooltipEvents() {
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        RuneKind kind = Runes.gearKind(stack);
        // equipment rolls its sockets when crafted or picked up, until then (e.g. in the creative menu) there is nothing to show
        if (kind == null || !Runes.enabled() || !Runes.hasRolledSockets(stack)) return;

        List<Component> tooltip = event.getToolTip();
        List<Component> lines = new ArrayList<>();
        List<RuneData> runes = Runes.sockets(stack);
        int max = Math.max(Runes.socketCapacity(stack), runes.size());
        if (max <= 0) {
            if (Runes.maxSockets(kind) <= 0) return;
            lines.add(translate(DARK_GRAY, "tooltip.armorplus.rune.no_sockets"));
        } else {
            lines.add(translate(LIGHT_PURPLE, "tooltip.armorplus.rune.sockets", runes.size(), max));
            if (isSneakDown()) {
                Player player = Minecraft.getInstance().player;
                List<BuffInstance> buffs = new ArrayList<>();
                for (RuneData rune : runes) {
                    BuffInstance buff = Runes.resolve(rune);
                    if (buff != null) {
                        addBuffLines(lines, List.of(buff), rune.kind() == RuneKind.WEAPON || rune.kind() == RuneKind.RANGED ? ToolTipUtils::durationPrefix : b -> null,
                                b -> player == null || !isRuneDisabled(player, rune.kind(), BuffParser.idOf(b.getBuff())), runeSuffix(rune.kind()));
                        buffs.add(buff);
                    } else {
                        lines.add(translate(DARK_GRAY, "tooltip.armorplus.rune.socket_inactive", rune.buffId()));
                    }
                }
                for (int i = runes.size(); i < max; i++) {
                    lines.add(translate(DARK_GRAY, "tooltip.armorplus.rune.socket_empty"));
                }
                // the equipment's own effects may have added it already
                if (tooltip.stream().noneMatch(line -> hasKey(line, DETAILS_HINT))) addDetailsHint(lines, buffs, LIGHT_PURPLE);
            } else if (findLast(tooltip, true) < 0) {
                // equipment without ArmorPlus buffs has no "Press Shift" hint yet
                showInfo(lines, Minecraft.getInstance().options.keyShift, LIGHT_PURPLE);
            }
        }

        int anchor = findLast(tooltip, false);
        int index = anchor >= 0 ? anchor + 1 : Math.min(1, tooltip.size());
        tooltip.addAll(index, lines);
    }

    /** @return true if the player switched this socketed rune's effect off, in the screen matching its kind */
    private static boolean isRuneDisabled(Player player, RuneKind kind, String id) {
        return switch (kind) {
            case WEAPON -> WeaponEffects.isDisabled(player, id);
            case TOOL -> ToolEffects.isDisabled(player, id);
            case RANGED -> RangedEffects.isDisabled(player, id);
            case ARMOR -> ArmorEffects.isDisabled(player, ArmorEffects.toggleKey(ArmorEffects.RUNE_SOURCE, id));
        };
    }

    /**
     * @param hintOnly only look for the "Press Shift" hint
     * @return the index of the last ArmorPlus buff line or "Press Shift" hint, or -1
     */
    private static int findLast(List<Component> tooltip, boolean hintOnly) {
        for (int i = tooltip.size() - 1; i >= 0; i--) {
            Component line = tooltip.get(i);
            boolean hint = hasKey(line, SHIFT_HINT);
            String insertion = line.getStyle().getInsertion();
            boolean buff = BuffGridTooltip.BUFF_LINE.equals(insertion) || BuffGridTooltip.BUFF_DESCRIPTION.equals(insertion);
            if (hint || (!hintOnly && buff)) return i;
        }
        return -1;
    }

    private static boolean hasKey(Component line, String key) {
        return line.getContents() instanceof TranslatableContents contents && contents.getKey().equals(key);
    }
}
