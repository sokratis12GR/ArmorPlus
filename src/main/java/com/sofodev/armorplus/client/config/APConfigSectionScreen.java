package com.sofodev.armorplus.client.config;

import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.thedragonlib.client.config.EntryPicker;
import com.sofodev.thedragonlib.client.config.EntryPickerScreen.Candidate;
import com.sofodev.thedragonlib.client.config.PickerListScreen;
import com.sofodev.thedragonlib.client.config.RuleListScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ListValueSpec;
import org.jspecify.annotations.Nullable;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class APConfigSectionScreen extends ConfigurationScreen.ConfigurationSectionScreen {
    private static final String SECTION = "neoforge.configuration.uitext.section";
    private static final String SECTION_TEXT = "neoforge.configuration.uitext.sectiontext";
    private static final String CRUMB = "neoforge.configuration.uitext.breadcrumb.order";
    private static final int MIN_SECTIONS_TO_FIND = 8;

    private final Map<String, Runnable> sections = new LinkedHashMap<>();

    public APConfigSectionScreen(Screen parent, ModConfig.Type type, ModConfig modConfig, Component title) {
        super(parent, type, modConfig, title);
    }

    public APConfigSectionScreen(Context parentContext, Screen parent, Map<String, Object> valueSpecs, String key,
                                 Set<? extends UnmodifiableConfig.Entry> entrySet, Component title) {
        super(parentContext, parent, valueSpecs, key, entrySet, title);
    }

    private Button sectionButton(String key, Button.OnPress onPress) {
        String buttonKey = getTranslationKey(key) + ".button";
        return Button.builder(Component.translatable(SECTION, Component.translatable(I18n.exists(buttonKey) ? buttonKey : SECTION_TEXT)), onPress)
                .tooltip(Tooltip.create(getTooltipComponent(key, null)))
                .width(Button.DEFAULT_WIDTH)
                .build();
    }

    private Element element(String key, Button.OnPress onPress) {
        return new Element(Component.translatable(SECTION, getTranslationComponent(key)), getTooltipComponent(key, null),
                sectionButton(key, onPress), false);
    }

    private Component breadcrumb(String key) {
        return Component.translatable(CRUMB, getTitle(), ConfigurationScreen.CRUMB_SEPARATOR, getTranslationComponent(key));
    }

    @Override
    protected void addTitle() {
        super.addTitle();
        long subsections = context.entries().stream().filter(entry -> entry.getRawValue() instanceof UnmodifiableConfig).count();
        if (subsections < MIN_SECTIONS_TO_FIND) return;
        layout.addToHeader(Button.builder(Component.translatable(APRuleTypes.UI_PREFIX + "find"), button -> openFinder())
                .tooltip(Tooltip.create(Component.translatable(APRuleTypes.UI_PREFIX + "find.tooltip")))
                .width(60)
                .build(), settings -> settings.alignHorizontallyRight().paddingRight(6));
    }

    private void openFinder() {
        List<Candidate> candidates = sections.keySet().stream()
                .map(key -> new Candidate(key, RegistryPickerScreen.sectionIcon(key), getTranslationComponent(key)))
                .sorted(Comparator.comparing(candidate -> candidate.name().getString()))
                .toList();
        RegistryPickerScreen.openSection(this, candidates, key -> sections.get(key).run());
    }

    @Override
    protected @Nullable Element createSection(String key, UnmodifiableConfig subconfig, UnmodifiableConfig subsection) {
        if (subconfig.isEmpty()) return null;
        Runnable open = () -> minecraft.setScreen(sectionCache.computeIfAbsent(key,
                k -> new APConfigSectionScreen(context, this, subconfig.valueMap(), key, subsection.entrySet(), getTranslationComponent(key))));
        sections.put(key, open);
        return element(key, button -> open.run());
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T> @Nullable Element createList(String key, ListValueSpec spec, ModConfigSpec.ConfigValue<List<T>> list) {
        APRuleTypes.Editor editor = APRuleTypes.of(key);
        if (editor != null) {
            return element(key, button -> minecraft.setScreen(new RuleListScreen(this, editor.type(), APRuleTypes.context(),
                    list.getRaw().stream().map(String::valueOf).toList(), editor.picker(),
                    updated -> apply(key, list, (List<T>) updated))
                    .withDefaults(((List<?>) spec.correct(null)).stream().map(String::valueOf).toList())));
        }
        if (key.equals("weights")) {
            return element(key, button -> minecraft.setScreen(new SocketWeightsScreen(this, breadcrumb(key),
                    numbers(list.getRaw()), numbers((List<?>) spec.correct(null)),
                    updated -> apply(key, list, (List<T>) updated))));
        }
        EntryPicker picker = switch (key) {
            case "hiddenItems" -> APEntryPickers.of(RegistryPickerScreen.Kind.ITEMS);
            case "hiddenBlocks" -> APEntryPickers.of(RegistryPickerScreen.Kind.BLOCKS);
            case "disallowWithSoulHardenList" -> APEntryPickers.of(RegistryPickerScreen.Kind.ENCHANTMENTS);
            case "singleLevelEffects" -> APEntryPickers.buffs("", null);
            default -> null;
        };
        if (picker == null) return super.createList(key, spec, list);
        return element(key, button -> minecraft.setScreen(sectionCache.computeIfAbsent(key,
                k -> new PickerListScreen<>(Context.list(context, this), key, breadcrumb(key),
                        spec, list, picker,
                        Component.translatable("armorplus.configuration.picker.open"),
                        Component.translatable("armorplus.configuration.picker.unavailable"),
                        Component.translatable("armorplus.configuration.picker.manual")))));
    }

    private static List<Integer> numbers(List<?> values) {
        return values.stream().map(value -> ((Number) value).intValue()).toList();
    }

    private <T> void apply(String key, ModConfigSpec.ConfigValue<List<T>> list, List<T> updated) {
        List<T> current = list.getRaw();
        if (current.equals(updated)) return;
        Consumer<List<T>> set = value -> {
            list.set(value);
            onChanged(key);
        };
        undoManager.add(set, updated, set, current);
    }
}
