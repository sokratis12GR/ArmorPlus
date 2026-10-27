package com.sofodev.armorplus.client.config;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ListValueSpec;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class PickerListScreen<T> extends ConfigurationScreen.ConfigurationListScreen<T> {
    private final RegistryPickerScreen.Kind kind;

    public PickerListScreen(Context context, String key, Component title, ListValueSpec spec,
                            ModConfigSpec.ConfigValue<List<T>> valueList, RegistryPickerScreen.Kind kind) {
        super(context, key, title, spec, valueList);
        this.kind = kind;
    }

    @Override
    protected void createAddElementButton() {
        if (!spec.getSizeRange().test(cfgList.size() + 1)) return;
        list.addSmall(new StringWidget(Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, Component.empty(), font),
                Button.builder(Component.translatable("armorplus.configuration.picker.open"), button -> {
                    Set<String> existing = new HashSet<>();
                    cfgList.forEach(entry -> existing.add(Objects.toString(entry)));
                    minecraft.setScreen(new RegistryPickerScreen(this, kind, existing, this::addEntries));
                }).build());
    }

    @SuppressWarnings("unchecked")
    private void addEntries(List<String> ids) {
        List<T> newValue = new ArrayList<>(cfgList);
        for (String id : ids) {
            if (!newValue.contains(id)) newValue.add((T) id);
        }
        if (newValue.size() == cfgList.size() || !spec.test(newValue)) return;
        undoManager.add(v -> {
            cfgList = v;
            onChanged(key);
        }, newValue, v -> {
            cfgList = v;
            onChanged(key);
        }, cfgList);
    }
}
