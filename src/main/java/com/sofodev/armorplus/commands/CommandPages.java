package com.sofodev.armorplus.commands;

import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;

import java.util.List;

/**
 * Splits long command output into chat pages: the lines of one page, then a "[◀ Prev]  Page 1/3  [Next ▶]" row whose
 * buttons run the same command for the other pages.
 */
public final class CommandPages {

    /** Lines per page. */
    public static final int PAGE_SIZE = 8;

    private CommandPages() {
    }

    /**
     * Sends one page of the lines.
     *
     * @param page    the page to show, from 1 (clamped to the pages there are)
     * @param command the command that shows a page, followed by the page number, e.g. "/armorplus effects page"
     * @return the page shown
     */
    public static int send(CommandSourceStack source, List<Component> lines, int page, String command) {
        int pages = Math.max(1, (lines.size() + PAGE_SIZE - 1) / PAGE_SIZE);
        int current = Math.clamp(page, 1, pages);
        for (Component line : lines.subList((current - 1) * PAGE_SIZE, Math.min(lines.size(), current * PAGE_SIZE))) {
            source.sendSuccess(() -> line, false);
        }
        if (pages > 1) {
            MutableComponent nav = Component.literal("  ")
                    .append(button("commands.armorplus.page.previous", command + " " + (current - 1), current > 1))
                    .append(Component.literal("  ").append(Component.translatable("commands.armorplus.page", current, pages)).append("  ").withStyle(ChatFormatting.YELLOW))
                    .append(button("commands.armorplus.page.next", command + " " + (current + 1), current < pages));
            source.sendSuccess(() -> nav, false);
        }
        return current;
    }

    /** @return a button running the command, or a greyed out label without a page to go to */
    private static Component button(String key, String command, boolean active) {
        MutableComponent label = Component.translatable(key);
        return active
                ? label.withStyle(style -> style.withColor(ChatFormatting.AQUA)
                .withClickEvent(new ClickEvent.RunCommand(command))
                .withHoverEvent(new HoverEvent.ShowText(Component.literal(command))))
                : label.withStyle(ChatFormatting.DARK_GRAY);
    }
}
