package com.sofodev.armorplus.utils;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.client.ArmorPlusKeys;
import com.sofodev.armorplus.client.BuffGridTooltip;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.item.extra.Buff;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffNames;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.armorplus.registry.item.extra.DeBuff;
import com.sofodev.armorplus.registry.item.extra.RangedBuff;
import com.sofodev.armorplus.registry.item.tool.WeaponEffects;
import com.sofodev.armorplus.registry.item.tool.properties.tool.IAPTool;
import com.sofodev.armorplus.registry.rune.RuneEffectTypes;
import com.sofodev.armorplus.registry.rune.Runes;
import com.sofodev.armorplus.api.RuneEffectType;
import com.sofodev.armorplus.api.RuneKind;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;
import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.glfw.GLFW;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sofodev.thedragonlib.util.RomanNumeralUtil.generate;
import static net.minecraft.ChatFormatting.DARK_AQUA;
import static net.minecraft.ChatFormatting.DARK_GRAY;
import static net.minecraft.ChatFormatting.DARK_GREEN;
import static net.minecraft.ChatFormatting.DARK_PURPLE;
import static net.minecraft.ChatFormatting.GRAY;
import static net.minecraft.ChatFormatting.RED;
import static net.minecraft.ChatFormatting.STRIKETHROUGH;
import static net.minecraft.ChatFormatting.YELLOW;

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

    /** @return the local player whose equipment tooltips compare against, or null (e.g. on the title screen) */
    public static @Nullable Player clientPlayer() {
        return Minecraft.getInstance().player;
    }

    /** Adds the standard ArmorPlus "Press Shift for more info" line. */
    public static void showSneakInfo(Consumer<Component> tooltip, ChatFormatting formatting) {
        showInfo(tooltip, Minecraft.getInstance().options.keyShift, formatting);
    }

    public static void addBuffInformation(ItemStack stack, IAPTool tool, Consumer<Component> tooltip, String condition, boolean applyToSelf, boolean enabled) {
        List<BuffInstance> buffs = Runes.nativeBuffs(stack, tool.getBuffInstances().get());
        List<BuffInstance> extracted = Runes.extractedBuffs(stack, tool.getBuffInstances().get());
        if (!buffs.isEmpty() || !extracted.isEmpty()) {
            tooltip.accept(effectsHeader(condition, enabled));
            Player player = clientPlayer();
            addBuffLines(tooltip, buffs, ToolTipUtils::durationPrefix,
                    buff -> enabled && (player == null || !WeaponEffects.isDisabled(player, BuffParser.idOf(buff.getBuff()))));
            addExtractedLines(tooltip, extracted);
            addDetailsHint(tooltip, buffs, tool.getColor());
        }
    }

    /** @return the single header line above a buff list, e.g. "Set Effects:" or "On Hit: (Disabled)" */
    public static MutableComponent effectsHeader(String condition, boolean enabled) {
        MutableComponent header = translate(YELLOW, "tooltip.armorplus.header." + condition);
        return enabled ? header : header.append(translate(RED, "tooltip.armorplus.header.disabled"));
    }

    private static final String INDENT = "   ";

    /** @return "[4s]" for on-hit effects, null for abilities */
    public static Component durationPrefix(BuffInstance buff) {
        if (!buff.getBuff().isEffect()) return null;
        return Component.literal("[" + Math.max(1, buff.getDurationTicks() / 20) + "s]").withStyle(GRAY);
    }

    /**
     * @param held whether the effect comes from a held tool instead of worn armor
     * @return "(5s cd)" for instant effects, "(5s cd, weaker)" if they're also weaker when held, null for anything else
     */
    public static @Nullable Component instantCooldown(BuffInstance buff, boolean held) {
        if (!buff.getBuff().isEffect() || buff.getBuff() instanceof DeBuff) return null;
        var effect = buff.getBuff().getEffect();
        if (effect == null || !effect.value().isInstantenous()) return null;
        int seconds = Math.max(1, ArmorPlusConfig.instantEffectCooldown.get());
        boolean weaker = held && effect.value() == MobEffects.INSTANT_HEALTH.value() && ArmorPlusConfig.heldInstantEffectStrength.get() < 1.0;
        return translate(GRAY, weaker ? "tooltip.armorplus.instant_cooldown.weaker" : "tooltip.armorplus.instant_cooldown", seconds);
    }

    /** @return the suffix of a rune's buff line: weapon and ranged runes fire on hit, so only armor and tool runes have a cooldown */
    public static Function<BuffInstance, Component> runeSuffix(RuneKind kind) {
        return buff -> switch (kind) {
            case WEAPON -> null;
            case RANGED -> rangedCost(buff);
            case ARMOR, TOOL -> instantCooldown(buff, kind == RuneKind.TOOL);
        };
    }

    /** @return what firing the ability costs from the config, e.g. "(30s cd, -10 durability)" for Ender Shot, null for anything else */
    public static @Nullable Component rangedCost(BuffInstance buff) {
        if (buff.getBuff() != RangedBuff.ENDER_SHOT) return null;
        int cooldown = ArmorPlusConfig.enderShotCooldown.get();
        int durability = ArmorPlusConfig.enderShotDurability.get();
        if (cooldown > 0 && durability > 0) return translate(GRAY, "tooltip.armorplus.ender_shot_cost", cooldown, durability);
        if (cooldown > 0) return translate(GRAY, "tooltip.armorplus.instant_cooldown", cooldown);
        return durability > 0 ? translate(GRAY, "tooltip.armorplus.ender_shot_cost.durability", durability) : null;
    }

    /**
     * @param shortName use the short name ({@code armorplus.buff.short.<namespace>.<path>}) if there is one
     * @return the buff's name with its level, if it's shown (see {@link BuffNames#showsLevel(BuffInstance)})
     */
    public static MutableComponent buffName(BuffInstance buff, boolean shortName) {
        MutableComponent name = null;
        if (shortName) {
            String key = "armorplus.buff.short." + BuffParser.idOf(buff.getBuff()).replace(':', '.');
            if (I18n.exists(key)) name = Component.translatable(key).withStyle(DARK_AQUA);
        }
        if (name == null) name = buff.getTranslatedName().copy();
        return name.append(BuffNames.levelSuffix(buff));
    }

    /**
     * Adds one line per buff ("Short Name Level"). The lines are marked so that long lists get laid out as a grid when the
     * tooltip is rendered (see {@link BuffGridTooltip}).
     */
    public static void addBuffLines(Consumer<Component> tooltip, List<BuffInstance> buffs) {
        addBuffLines(tooltip, buffs, buff -> null);
    }

    /** Like {@link #addBuffLines(Consumer, List)}, with an optional prefix per buff (null for none), e.g. "[1/4]". */
    public static void addBuffLines(Consumer<Component> tooltip, List<BuffInstance> buffs, Function<BuffInstance, Component> prefix) {
        addBuffLines(tooltip, buffs, prefix, true);
    }

    /** Like {@link #addBuffLines(Consumer, List, Function)}, graying out every line when {@code enabled} is false. */
    public static void addBuffLines(Consumer<Component> tooltip, List<BuffInstance> buffs, Function<BuffInstance, Component> prefix, boolean enabled) {
        addBuffLines(tooltip, buffs, prefix, buff -> enabled);
    }

    /** Like {@link #addBuffLines(Consumer, List, Function)}, graying out each line whose {@code isEnabled} is false. */
    public static void addBuffLines(Consumer<Component> tooltip, List<BuffInstance> buffs, Function<BuffInstance, Component> prefix, Function<BuffInstance, Boolean> isEnabled) {
        addBuffLines(tooltip, buffs, prefix, isEnabled, buff -> null);
    }

    /** Like {@link #addBuffLines(Consumer, List, Function, Function)}, with an optional suffix per buff (null for none), e.g. "(5s cd)". */
    public static void addBuffLines(Consumer<Component> tooltip, List<BuffInstance> buffs, Function<BuffInstance, Component> prefix, Function<BuffInstance, Boolean> isEnabled, Function<BuffInstance, Component> suffix) {
        for (BuffInstance buff : buffs) {
            if (buff.getBuff() == Buff.NONE) continue;
            boolean enabled = isEnabled.apply(buff);
            MutableComponent line = Component.literal(INDENT);
            Component start = prefix.apply(buff);
            if (start != null) line.append(enabled ? start : start.copy().withStyle(GRAY)).append(" ");
            MutableComponent name = buffName(buff, true);
            line.append(enabled ? name : name.withStyle(GRAY));
            Component end = suffix.apply(buff);
            if (end != null) line.append(" ").append(end);
            tooltip.accept(line.withStyle(style -> style.withColor(enabled ? DARK_AQUA : GRAY).withInsertion(BuffGridTooltip.BUFF_LINE)));
            Component description = isDetailsDown() ? buffDescription(buff) : null;
            if (description != null) {
                tooltip.accept(Component.literal(INDENT + "  - ").append(description).withStyle(DESCRIPTION.withInsertion(BuffGridTooltip.BUFF_DESCRIPTION)));
            }
        }
    }

    /** Adds a dark gray, struck through line per effect that was extracted from the item, which it no longer has. */
    public static void addExtractedLines(Consumer<Component> tooltip, List<BuffInstance> extracted) {
        for (BuffInstance buff : extracted) {
            if (buff.getBuff() == Buff.NONE) continue;
            tooltip.accept(Component.literal(INDENT).append(extractedName(buff)).withStyle(style -> style.withColor(DARK_GRAY).withInsertion(BuffGridTooltip.BUFF_LINE)));
        }
    }

    /** @return the short name of the effect, dark gray and struck through */
    public static MutableComponent extractedName(BuffInstance buff) {
        return buffName(buff, true).withStyle(DARK_GRAY, STRIKETHROUGH);
    }

    /** Soft blue, to stand apart from the gray hints and the effect names. */
    private static final Style DESCRIPTION = Style.EMPTY.withItalic(true).withColor(TextColor.fromRgb(0x8FB8DE));

    /** The translation key of the {@link #addDetailsHint} line. */
    public static final String DETAILS_HINT = "tooltip.armorplus.details_hint";

    /** @return true while the Effect Details key is held in a GUI tooltip; only keyboard keys can be read there */
    public static boolean isDetailsDown() {
        InputConstants.Key key = ArmorPlusKeys.EFFECT_DETAILS.getKey();
        return key.getType() == InputConstants.Type.KEYSYM && key.getValue() >= 0 && InputConstants.isKeyDown(Minecraft.getInstance().getWindow(), key.getValue());
    }

    /** @return what the ability does ({@link RuneEffectType#getDescription()}), null for mob effects and abilities without a description */
    public static @Nullable Component buffDescription(BuffInstance buff) {
        if (buff.getBuff().isEffect()) return null;
        Identifier id = Identifier.tryParse(BuffParser.idOf(buff.getBuff()));
        RuneEffectType type = id == null ? null : RuneEffectTypes.get(id);
        return type == null ? null : type.getDescription();
    }

    /**
     * Adds "Hold Left Alt for details" if any of the buffs has a description that isn't showing yet.
     *
     * @param formatting the color of the key's name, like in {@link #showInfo}
     */
    public static void addDetailsHint(Consumer<Component> tooltip, List<BuffInstance> buffs, ChatFormatting formatting) {
        if (isDetailsDown() || buffs.stream().allMatch(buff -> buffDescription(buff) == null)) return;
        tooltip.accept(translate(GRAY, DETAILS_HINT, ArmorPlusKeys.EFFECT_DETAILS.getTranslatedKeyMessage().copy().withStyle(formatting)));
    }

    /** @return the tooltip of a buff's button in the effects screens: its full name, and its description if it has one */
    public static MutableComponent buffTooltip(BuffInstance buff) {
        MutableComponent tooltip = Component.empty().append(buffName(buff, false));
        Component description = buffDescription(buff);
        return description == null ? tooltip : tooltip.append("\n").append(description.copy().withStyle(DESCRIPTION));
    }

    private static final Style LORE =Style.EMPTY.withItalic(true).withColor(TextColor.fromRgb(0x670067));

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

    /** Adds one lang line styled like the detail lines of {@link #addItemLore}. */
    public static void addLoreLine(Consumer<Component> tooltip, String key) {
        tooltip.accept(loreLine(I18n.get(key)));
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
        return (pct == Math.rint(pct) ? String.valueOf((int) pct) : String.format(Locale.ROOT, "%.1f", pct)) + "%";
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