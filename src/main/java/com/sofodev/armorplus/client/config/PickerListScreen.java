package com.sofodev.armorplus.client.config;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ListValueSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * A config list screen whose add row offers both a picker ("Add from list...") and the regular manual add button.
 */
public class PickerListScreen<T> extends ConfigurationScreen.ConfigurationListScreen<T> {
    private final EntryPicker picker;

    public PickerListScreen(Context context, String key, Component title, ListValueSpec spec,
                            ModConfigSpec.ConfigValue<List<T>> valueList, EntryPicker picker) {
        super(context, key, title, spec, valueList);
        this.picker = picker;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void createAddElementButton() {
        if (!spec.getSizeRange().test(cfgList.size() + 1)) return;

        Button pickButton = Button.builder(Component.translatable("armorplus.configuration.picker.open"),
                button -> picker.open(this, cfgList, this::addEntries)).width(Button.DEFAULT_WIDTH).build();
        if (!picker.isAvailable()) {
            pickButton.active = false;
            pickButton.setTooltip(Tooltip.create(Component.translatable("armorplus.configuration.picker.unavailable")));
        }

        final Supplier<?> newElement = spec.getNewElementSupplier();
        if (newElement == null) {
            list.addSmall(new StringWidget(Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, Component.empty(), font), pickButton);
            return;
        }
        list.addSmall(pickButton, Button.builder(ConfigurationScreen.NEW_LIST_ELEMENT, button -> {
            List<T> newValue = new ArrayList<>(cfgList);
            newValue.add((T) newElement.get());
            setList(newValue);
        }).tooltip(Tooltip.create(Component.translatable("armorplus.configuration.picker.manual"))).build());
    }

    @SuppressWarnings("unchecked")
    private void addEntries(List<String> ids) {
        List<T> newValue = new ArrayList<>(cfgList);
        for (String id : ids) {
            if (!newValue.contains(id)) newValue.add((T) id);
        }
        if (newValue.size() == cfgList.size() || !spec.test(newValue)) return;
        setList(newValue);
    }

    private void setList(List<T> newValue) {
        undoManager.add(v -> {
            cfgList = v;
            onChanged(key);
        }, newValue, v -> {
            cfgList = v;
            onChanged(key);
        }, cfgList);
        rebuild();
    }
}
