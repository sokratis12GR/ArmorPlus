package com.sofodev.armorplus.utils;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.client.BuffGridTooltip;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.item.extra.Buff;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.tool.properties.tool.IAPTool;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.glfw.GLFW;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sofodev.thedragonlib.util.RomanNumeralUtil.generate;
import static net.minecraft.ChatFormatting.*;

public class ToolTipUtils {

    /**
     * This provides the "Press [Key] to show more" tooltip
     *
     * @param tooltip    the tooltip of the item
     * @param keyBinding the keybind that the users will need to press to display the more information (replaces [Key])
     * @param formatting the formatting of the tooltip, its color and style.
     */
    public static void showInfo(Consumer<Component> tooltip, KeyMapping keyBinding, ChatFormatting formatting) {
        tooltip.accept(translate(GRAY, "tooltip.armorplus.shift.showinfo", translate(formatting, keyBinding.getName())));
    }

    /**
     * Adds a basic damage information about arrows
     */
    public static void appendArrowHoverText(Consumer<Component> tooltip, Component effect, double damage, ChatFormatting formatting) {
        KeyMapping keyBindSneak = Minecraft.getInstance().options.keyShift;
        if ((InputConstants.isKeyDown(Minecraft.getInstance().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT) || InputConstants.isKeyDown(Minecraft.getInstance().getWindow(), GLFW.GLFW_KEY_RIGHT_SHIFT))) {
            tooltip.accept(translate("tooltip.armorplus.arrow.ability_desc", effect));
            tooltip.accept(translate("tooltip.armorplus.arrow.ability", damage));
        } else {
            showInfo(tooltip, keyBindSneak, formatting);
        }
    }

    /** Returns true while the player is holding Shift in a GUI tooltip. */
    public static boolean isSneakDown() {
        return (InputConstants.isKeyDown(Minecraft.getInstance().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT) || InputConstants.isKeyDown(Minecraft.getInstance().getWindow(), GLFW.GLFW_KEY_RIGHT_SHIFT));
    }

    /** Adds the standard ArmorPlus "Press Shift for more info" line. */
    public static void showSneakInfo(Consumer<Component> tooltip, ChatFormatting formatting) {
        showInfo(tooltip, Minecraft.getInstance().options.keyShift, formatting);
    }

    public static void addBuffInformation(IAPTool tool, Consumer<Component> tooltip, String condition, boolean applyToSelf, boolean enabled) {
        if (!tool.getBuffInstances().get().isEmpty()) {
            tooltip.accept(translate(YELLOW, "tooltip.armorplus.condition", enabled ? "" : "(DISABLED)"));
            tooltip.accept(translate(GOLD, "tooltip.armorplus.condition." + condition));
            tooltip.accept(translate(GREEN, "tooltip.armorplus." + (applyToSelf ? "provides" : "applies")));
            addBuffLines(tooltip, tool.getBuffInstances().get());
        }
    }

    private static final String INDENT = "   ";

    /**
     * Adds one line per buff ("Name Level"). The lines are marked so that long lists get laid out as a grid when the
     * tooltip is rendered (see {@link BuffGridTooltip}).
     */
    public static void addBuffLines(Consumer<Component> tooltip, List<BuffInstance> buffs) {
        for (BuffInstance buff : buffs) {
            if (buff.getBuff() == Buff.NONE) continue;
            int lvl = buff.getAmplifier() + 1;
            String roman = lvl > 0 ? " " + generate(lvl) : "";
            tooltip.accept(Component.literal(INDENT).append(buff.getTranslatedName()).append(roman)
                    .withStyle(style -> style.withColor(DARK_AQUA).withInsertion(BuffGridTooltip.BUFF_LINE)));
        }
    }

    private static final Style LORE = Style.EMPTY.withItalic(true).withColor(TextColor.fromRgb(0x670067));

    /**
     * Adds an item's lang-driven description: an always-visible flavor line ({@code tooltip.armorplus.<id>.flavor}),
     * followed by detail lines ({@code tooltip.armorplus.<id>.info.1}, {@code .info.2}, ...) shown while Shift is held.
     * Every key is optional, so adding a description is a lang-only change.
     */
    public static void addItemLore(Consumer<Component> tooltip, Item item) {
        String base = "tooltip" + item.getDescriptionId().substring(item.getDescriptionId().indexOf('.'));
        if (ArmorPlusConfig.enableLoreFlavor.get() && I18n.exists(base + ".flavor")) tooltip.accept(translate(LORE, base + ".flavor"));
        if (!ArmorPlusConfig.enableLoreInfo.get() || !I18n.exists(base + ".info.1")) return;
        if (!isSneakDown()) {
            showSneakInfo(tooltip, DARK_PURPLE);
            return;
        }
        Object[] args = loreArgs(item);
        for (int i = 1; I18n.exists(base + ".info." + i); i++) tooltip.accept(loreLine(I18n.get(base + ".info." + i, args)));
    }

    private static final Pattern LORE_LABEL = Pattern.compile("^([A-Z][A-Za-z ]{1,9}):\\s*");
    private static final Pattern LORE_KEYWORD = Pattern.compile("\\{([^}]*)}");
    private static final Style LORE_LABEL_STYLE = Style.EMPTY.withBold(true).withColor(ChatFormatting.GOLD);
    private static final Style LORE_KEYWORD_STYLE = Style.EMPTY.withColor(ChatFormatting.YELLOW);

    /**
     * Styles one detail line: a leading "Label:" (e.g. "Source:", "Craft:", "Use:") is shown in bold gold, and every
     * {@code {keyword}} is highlighted in yellow while the rest stays gray.
     */
    private static Component loreLine(String text) {
        MutableComponent line = Component.empty().withStyle(GRAY);
        Matcher label = LORE_LABEL.matcher(text);
        if (label.find()) {
            line.append(Component.literal(label.group(1) + ": ").withStyle(LORE_LABEL_STYLE));
            text = text.substring(label.end());
        }
        int pos = 0;
        for (Matcher keyword = Pattern.compile("\\{([^}]*)}").matcher(text); keyword.find(); pos = keyword.end()) {
            line.append(text.substring(pos, keyword.start()));
            line.append(Component.literal(keyword.group(1)).withStyle(LORE_KEYWORD_STYLE));
        }
        return line.append(text.substring(pos));
    }

    /**
     * Values for the {@code %1$s}-style placeholders of an item's lore: the configured drop amounts and chances, so the
     * tooltips always match the config (on the client this is the local config).
     */
    private static Object[] loreArgs(Item item) {
        ArmorPlusConfig cfg = ArmorPlus.config;
        return switch (BuiltInRegistries.ITEM.getKey(item).getPath()) {
            case "guardian_scale" -> new Object[]{amount(cfg.elderGuardianDrops.regularDropMin().get(), cfg.elderGuardianDrops.regularDropMax().get(), cfg.elderGuardianDrops.regularDropChance().get()),
                    amount(cfg.guardianDrops.regularDropMin().get(), cfg.guardianDrops.regularDropMax().get(), cfg.guardianDrops.regularDropChance().get())};
            case "wither_bone" -> new Object[]{amount(cfg.witherBossDrops.regularDropMin().get(), cfg.witherBossDrops.regularDropMax().get(), cfg.witherBossDrops.regularDropChance().get()),
                    amount(cfg.witherSkeletonDrops.regularDropMin().get(), cfg.witherSkeletonDrops.regularDropMax().get(), cfg.witherSkeletonDrops.regularDropChance().get())};
            case "ender_dragon_scale" -> new Object[]{amount(cfg.enderDragonDrops.regularDropMin().get(), cfg.enderDragonDrops.regularDropMax().get(), cfg.enderDragonDrops.regularDropChance().get())};
            case "warden_heart" -> new Object[]{amount(cfg.wardenDrops.regularDropMin().get(), cfg.wardenDrops.regularDropMax().get(), cfg.wardenDrops.regularDropChance().get())};
            case "soul_wither_skeleton" -> new Object[]{percent(cfg.witherSkeletonDrops.soulDropChance().get())};
            case "soul_guardian" -> new Object[]{percent(cfg.guardianDrops.soulDropChance().get())};
            case "soul_enderman" -> new Object[]{percent(cfg.endermanDrops.soulDropChance().get())};
            case "soul_blaze" -> new Object[]{percent(cfg.blazeDrops.soulDropChance().get())};
            case "soul_wither_boss" -> new Object[]{percent(cfg.witherBossDrops.soulDropChance().get())};
            case "soul_elder_guardian" -> new Object[]{percent(cfg.elderGuardianDrops.soulDropChance().get())};
            case "soul_ender_dragon" -> new Object[]{percent(cfg.enderDragonDrops.soulDropChance().get())};
            default -> new Object[0];
        };
    }

    /** "4-6" (or "4"), with the chance appended when the drop isn't guaranteed: "0-3, 50% chance". */
    private static String amount(int min, int max, double chance) {
        String range = min >= max ? String.valueOf(min) : min + "-" + max;
        return chance >= 1.0 ? range : range + ", " + percent(chance) + " chance";
    }

    private static String percent(double chance) {
        double pct = chance * 100;
        return (pct == Math.rint(pct) ? String.valueOf((int) pct) : String.format(java.util.Locale.ROOT, "%.1f", pct)) + "%";
    }

    /**
     * Adds bow bonus damage information.
     */
    public static void addBowDamageInformation(Consumer<Component> tooltip, double damage) {
        tooltip.accept(translate(DARK_GREEN, "tooltip.armorplus.bow.damage", damage));
    }

    public static void addExperimentalItemInformation(Consumer<Component> tooltip) {
        tooltip.accept(translate(RED, "tooltip.armorplus.not_accessible"));
        tooltip.accept(translate(RED, "tooltip.armorplus.not_accessible.2"));
        tooltip.accept(translate(RED, "tooltip.armorplus.not_accessible.3"));
    }

    public static MutableComponent translate(TextColor color, String key, Object... args) {
        return Component.translatable(key, args).setStyle(Style.EMPTY.withColor(color));
    }


    public static MutableComponent translate(Style style, String key, Object... args) {
        return Component.translatable(key, args).setStyle(style);
    }

    public static MutableComponent translate(ChatFormatting formatting, String key, Object... args) {
        return Component.translatable(key, args).withStyle(formatting);
    }

    public static MutableComponent translate(String key, Object... args) {
        return Component.translatable(key, args);
    }

}