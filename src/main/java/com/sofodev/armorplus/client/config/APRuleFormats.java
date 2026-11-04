package com.sofodev.armorplus.client.config;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.item.armor.SetRequirement;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.armorplus.registry.rune.RuneOverrides;
import com.sofodev.thedragonlib.client.config.RuleFormat;
import com.sofodev.thedragonlib.client.config.RuleListContext;
import com.sofodev.thedragonlib.client.config.RuleType;
import com.sofodev.thedragonlib.util.RomanNumeralUtil;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * The line syntaxes of the ArmorPlus config lists. Saved lines are also run through the validator of the config value, so
 * the editors can't produce a line the config would throw away.
 */
final class APRuleFormats {
    private static final String SEPARATOR = " · ";

    /** {@code "[!]id|level|seconds@pieces"} */
    static final RuleFormat ARMOR_BUFF = new BuffFormat(true);
    /** {@code "[!]id|level|seconds"} */
    static final RuleFormat WEAPON_BUFF = new BuffFormat(false);
    /** {@code "id|socket=N|extract=N|catalyst=item*count|extractable=false|disabled=true"} */
    static final RuleFormat RUNE_OVERRIDE = new RuneOverrideFormat();
    /** {@code "kind|id|level|seconds"} */
    static final RuleFormat WORLD_RUNE = new WorldRuneFormat();
    /** {@code "id|slots"}, {@code "id|slots:count"} or {@code "id|count"} */
    static final RuleFormat EFFECT_SLOT = new EffectSlotFormat();

    private APRuleFormats() {
    }

    /** Plain pipe-delimited values, for the lists whose validator can't be expressed per field. */
    static RuleFormat checked(Predicate<Object> spec) {
        return new RuleFormat() {
            @Override
            public boolean matches(String line, RuleType type) {
                return RuleFormat.PIPE.matches(line, type);
            }

            @Override
            public String[] split(String line, RuleType type) {
                return RuleFormat.PIPE.split(line, type);
            }

            @Override
            public String join(String[] values, RuleType type) {
                return RuleFormat.PIPE.join(values, type);
            }

            @Override
            public @Nullable String validate(String[] values, RuleType type, RuleListContext ctx) {
                return spec.test(join(values, type)) ? null : ctx.uiKey("error.invalidEntry");
            }
        };
    }

    private static String part(String[] parts, int index) {
        return index < parts.length ? parts[index].trim() : "";
    }

    /** @return the id, level, seconds and pieces of a buff entry, empty for those it leaves out */
    private static String[] parseBuff(String entry) {
        String body = BuffParser.withoutDisabledPrefix(entry).trim();
        int split = body.indexOf(BuffParser.REQUIREMENT_SEPARATOR);
        String[] parts = BuffParser.withoutRequirement(body).split("\\|", -1);
        return new String[] {part(parts, 0), part(parts, 1), part(parts, 2), split < 0 ? "" : body.substring(split + 1).trim()};
    }

    private static String joinBuff(String id, String level, String seconds, String pieces) {
        StringBuilder entry = new StringBuilder(id);
        // The seconds are the third value, so they can't go without a level.
        if (!level.isEmpty() || !seconds.isEmpty()) entry.append('|').append(level.isEmpty() ? "1" : level);
        if (!seconds.isEmpty()) entry.append('|').append(seconds);
        if (!pieces.isEmpty()) entry.append(BuffParser.REQUIREMENT_SEPARATOR).append(pieces);
        return entry.toString();
    }

    /** E.g. "Haste II" */
    private static String buffName(RuleType.Field effect, String id, String level, RuleListContext ctx) {
        String name = ctx.suggestions().displayName(effect, id);
        return level.isEmpty() || level.equals("1") ? name : name + " " + RomanNumeralUtil.generate(Integer.parseInt(level));
    }

    private static String seconds(String seconds, RuleListContext ctx) {
        return Component.translatable(ctx.uiKey("describe.seconds"), seconds).getString();
    }

    private record BuffFormat(boolean hasPieces) implements RuleFormat {
        @Override
        public boolean matches(String line, RuleType type) {
            return BuffParser.isValid(line);
        }

        @Override
        public String[] split(String line, RuleType type) {
            String[] parsed = parseBuff(line);
            String[] values = new String[type.fields().size()];
            values[0] = parsed[0];
            values[1] = parsed[1];
            values[2] = parsed[2];
            if (hasPieces) values[3] = parsed[3];
            values[values.length - 1] = String.valueOf(!BuffParser.isDisabled(line));
            return values;
        }

        @Override
        public String join(String[] values, RuleType type) {
            String entry = joinBuff(values[0], values[1], values[2], hasPieces ? values[3] : "");
            return values[values.length - 1].equals("false") ? BuffParser.DISABLED_PREFIX + entry : entry;
        }

        @Override
        public @Nullable String validate(String[] values, RuleType type, RuleListContext ctx) {
            return BuffParser.isValid(join(values, type)) ? null : ctx.uiKey("error.invalidEntry");
        }

        @Override
        public Component describe(String[] values, RuleType type, RuleListContext ctx) {
            List<String> parts = new ArrayList<>();
            parts.add(buffName(type.fields().get(0), values[0], values[1], ctx));
            if (hasPieces && !values[3].isEmpty()) parts.add(ctx.suggestions().displayName(type.fields().get(3), values[3]));
            if (!values[2].isEmpty()) parts.add(seconds(values[2], ctx));
            if (values[values.length - 1].equals("false")) parts.add(Component.translatable(ctx.uiKey("describe.off")).getString());
            return Component.literal(String.join(SEPARATOR, parts));
        }
    }

    /** Fields: buff, socket, extract, catalyst, count, extractable, disabled. */
    private static final class RuneOverrideFormat implements RuleFormat {
        @Override
        public boolean matches(String line, RuleType type) {
            // A line with only the buff is reported as having no rules instead.
            return !line.contains("|") || RuneOverrides.isValid(line);
        }

        @Override
        public String[] split(String line, RuleType type) {
            String[] values = {"", "", "", "", "", "true", "false"};
            String[] parts = line.split("\\|", -1);
            values[0] = part(parts, 0);
            for (int i = 1; i < parts.length; i++) {
                String[] pair = parts[i].split("=", 2);
                if (pair.length != 2) continue;
                String value = pair[1].trim();
                switch (pair[0].trim()) {
                    case "socket" -> values[1] = value;
                    case "extract" -> values[2] = value;
                    case "catalyst" -> {
                        String[] item = value.split("\\*", -1);
                        values[3] = part(item, 0);
                        values[4] = part(item, 1);
                    }
                    case "extractable" -> values[5] = value;
                    case "disabled" -> values[6] = value;
                    default -> {
                    }
                }
            }
            return values;
        }

        @Override
        public String join(String[] values, RuleType type) {
            StringBuilder entry = new StringBuilder(values[0]);
            if (!values[1].isEmpty()) entry.append("|socket=").append(values[1]);
            if (!values[2].isEmpty()) entry.append("|extract=").append(values[2]);
            if (!values[3].isEmpty()) {
                entry.append("|catalyst=").append(values[3]);
                if (!values[4].isEmpty() && !values[4].equals("1")) entry.append('*').append(values[4]);
            }
            if (values[5].equals("false")) entry.append("|extractable=false");
            if (values[6].equals("true")) entry.append("|disabled=true");
            return entry.toString();
        }

        @Override
        public @Nullable String validate(String[] values, RuleType type, RuleListContext ctx) {
            if (values[3].isEmpty() && !values[4].isEmpty()) return ctx.uiKey("error.countWithoutCatalyst");
            return RuneOverrides.isValid(join(values, type)) ? null : ctx.uiKey("error.noRules");
        }

        @Override
        public Component describe(String[] values, RuleType type, RuleListContext ctx) {
            List<String> parts = new ArrayList<>();
            parts.add(ctx.suggestions().displayName(type.fields().get(0), values[0]));
            if (!values[1].isEmpty()) parts.add(Component.translatable(ctx.uiKey("describe.socket"), values[1]).getString());
            if (!values[2].isEmpty()) parts.add(Component.translatable(ctx.uiKey("describe.extract"), values[2]).getString());
            if (!values[3].isEmpty()) {
                String catalyst = ctx.suggestions().displayName(type.fields().get(3), values[3]);
                parts.add(values[4].isEmpty() || values[4].equals("1") ? catalyst : catalyst + " x" + values[4]);
            }
            if (values[5].equals("false")) parts.add(Component.translatable(ctx.uiKey("describe.notExtractable")).getString());
            if (values[6].equals("true")) parts.add(Component.translatable(ctx.uiKey("describe.disabled")).getString());
            return Component.literal(String.join(SEPARATOR, parts));
        }
    }

    /** Fields: effect, piece count, then one toggle per slot, named after the slot. */
    private static final class EffectSlotFormat implements RuleFormat {
        private static final int COUNT = 1;
        private static final int FIRST_SLOT = 2;

        private static boolean isSlot(String name, RuleType type) {
            for (int i = FIRST_SLOT; i < type.fields().size(); i++) {
                if (type.fields().get(i).name().equals(name)) return true;
            }
            return false;
        }

        /** @return the slots and the count as separate comma separated values, e.g. "legs,feet,1" for "legs,feet:1" */
        private static String[] pieceValues(String pieces) {
            return pieces.replace(SetRequirement.COUNT_SEPARATOR, ',').split(",", -1);
        }

        /** E.g. "legs,feet", "legs,feet:1" or "2". While editing it can be empty or need too many slots, which {@link #validate} explains. */
        private static String pieces(String[] values, RuleType type) {
            List<String> slots = new ArrayList<>();
            for (int i = FIRST_SLOT; i < values.length; i++) {
                if (values[i].equals("true")) slots.add(type.fields().get(i).name());
            }
            if (slots.isEmpty()) return values[COUNT];
            String names = String.join(",", slots);
            return values[COUNT].isEmpty() ? names : names + SetRequirement.COUNT_SEPARATOR + values[COUNT];
        }

        @Override
        public boolean matches(String line, RuleType type) {
            String[] parts = line.split("\\|", -1);
            if (parts.length != 2) return false;
            if (parts[1].isBlank()) return true;
            int counts = 0;
            for (String piece : pieceValues(parts[1])) {
                if (type.fields().get(COUNT).choices().contains(piece.trim())) counts++;
                else if (!isSlot(piece.trim(), type)) return false;
            }
            return counts <= 1;
        }

        @Override
        public String[] split(String line, RuleType type) {
            String[] values = new String[type.fields().size()];
            String[] parts = line.split("\\|", -1);
            values[0] = part(parts, 0);
            values[COUNT] = "";
            for (int i = FIRST_SLOT; i < values.length; i++) values[i] = "false";
            for (String piece : pieceValues(part(parts, 1))) {
                String name = piece.trim();
                if (type.fields().get(COUNT).choices().contains(name)) values[COUNT] = name;
                for (int i = FIRST_SLOT; i < values.length; i++) {
                    if (type.fields().get(i).name().equals(name)) values[i] = "true";
                }
            }
            return values;
        }

        @Override
        public String join(String[] values, RuleType type) {
            return values[0] + "|" + pieces(values, type);
        }

        @Override
        public @Nullable String validate(String[] values, RuleType type, RuleListContext ctx) {
            String pieces = pieces(values, type);
            if (pieces.isEmpty()) return ctx.uiKey("error.noPieces");
            if (!SetRequirement.isValid(pieces)) return ctx.uiKey("error.countTooHigh");
            return ArmorPlusConfig.isEffectSlot(join(values, type)) ? null : ctx.uiKey("error.invalidEntry");
        }

        @Override
        public Component describe(String[] values, RuleType type, RuleListContext ctx) {
            return Component.translatable(ctx.uiKey("describe." + type.key()), ctx.suggestions().displayName(type.fields().get(0), values[0]),
                    APRuleSuggestions.pieceName(pieces(values, type)));
        }
    }

    /** Fields: kind, effect, level, seconds. */
    private static final class WorldRuneFormat implements RuleFormat {
        @Override
        public boolean matches(String line, RuleType type) {
            return ArmorPlusConfig.isWorldRune(line);
        }

        @Override
        public String[] split(String line, RuleType type) {
            int split = line.indexOf('|');
            if (split < 0) return new String[] {line.trim(), "", "", ""};
            String[] buff = parseBuff(line.substring(split + 1));
            return new String[] {line.substring(0, split).trim(), buff[0], buff[1], buff[2]};
        }

        @Override
        public String join(String[] values, RuleType type) {
            return values[0] + "|" + joinBuff(values[1], values[2], values[3], "");
        }

        @Override
        public @Nullable String validate(String[] values, RuleType type, RuleListContext ctx) {
            return ArmorPlusConfig.isWorldRune(join(values, type)) ? null : ctx.uiKey("error.invalidEntry");
        }

        @Override
        public Component describe(String[] values, RuleType type, RuleListContext ctx) {
            List<String> parts = new ArrayList<>();
            parts.add(RuleType.buttonLabel(type.fields().get(0), values[0], ctx).getString());
            parts.add(buffName(type.fields().get(1), values[1], values[2], ctx));
            if (!values[3].isEmpty()) parts.add(seconds(values[3], ctx));
            return Component.literal(String.join(SEPARATOR, parts));
        }
    }
}
