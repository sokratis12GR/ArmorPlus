package com.sofodev.armorplus.client.config;

import com.electronwill.nightconfig.core.UnmodifiableConfig;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ListValueSpec;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class APConfigSectionScreen extends ConfigurationScreen.ConfigurationSectionScreen {
    private static final String SECTION = "neoforge.configuration.uitext.section";
    private static final String SECTION_TEXT = "neoforge.configuration.uitext.sectiontext";
    private static final String CRUMB = "neoforge.configuration.uitext.breadcrumb.order";

    public APConfigSectionScreen(Screen parent, ModConfig.Type type, ModConfig modConfig, Component title) {
        super(parent, type, modConfig, title);
    }

    public APConfigSectionScreen(Context parentContext, Screen parent, Map<String, Object> valueSpecs, String key,
                                 Set<? extends UnmodifiableConfig.Entry> entrySet, Component title) {
        super(parentContext, parent, valueSpecs, key, entrySet, title);
    }

    private Button sectionButton(String key, Button.OnPress onPress) {
        return Button.builder(Component.translatable(SECTION, Component.translatable(SECTION_TEXT)), onPress)
                .tooltip(Tooltip.create(getTooltipComponent(key, null)))
                .width(Button.DEFAULT_WIDTH)
                .build();
    }

    @Override
    protected @Nullable Element createSection(String key, UnmodifiableConfig subconfig, UnmodifiableConfig subsection) {
        if (subconfig.isEmpty()) return null;
        return new Element(Component.translatable(SECTION, getTranslationComponent(key)), getTooltipComponent(key, null),
                sectionButton(key, button -> minecraft.setScreen(sectionCache.computeIfAbsent(key,
                        k -> new APConfigSectionScreen(context, this, subconfig.valueMap(), key, subsection.entrySet(), getTranslationComponent(key))))),
                false);
    }

    @Override
    protected <T> @Nullable Element createList(String key, ListValueSpec spec, ModConfigSpec.ConfigValue<List<T>> list) {
        RegistryPickerScreen.Kind kind = switch (key) {
            case "hiddenItems" -> RegistryPickerScreen.Kind.ITEMS;
            case "hiddenBlocks" -> RegistryPickerScreen.Kind.BLOCKS;
            default -> null;
        };
        if (kind == null) return super.createList(key, spec, list);
        return new Element(Component.translatable(SECTION, getTranslationComponent(key)), getTooltipComponent(key, null),
                sectionButton(key, button -> minecraft.setScreen(sectionCache.computeIfAbsent(key,
                        k -> new PickerListScreen<>(Context.list(context, this), key,
                                Component.translatable(CRUMB, getTitle(), ConfigurationScreen.CRUMB_SEPARATOR, getTranslationComponent(key)),
                                spec, list, kind)))),
                false);
    }
}
