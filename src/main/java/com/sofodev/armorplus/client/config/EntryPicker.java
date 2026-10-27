package com.sofodev.armorplus.client.config;

import net.minecraft.client.gui.screens.Screen;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Opens a picker screen which produces new entries for a config list.
 */
public interface EntryPicker {

    /** Whether the picker can be opened right now. */
    boolean isAvailable();

    /**
     * @param parent  the screen to return to
     * @param current the entries currently in the list
     * @param add     receives the entries to append
     */
    void open(Screen parent, List<?> current, Consumer<List<String>> add);

    /** Picks registry ids, skipping those already in the list. */
    static EntryPicker of(RegistryPickerScreen.Kind kind) {
        return new EntryPicker() {
            @Override
            public boolean isAvailable() {
                return kind.isAvailable();
            }

            @Override
            public void open(Screen parent, List<?> current, Consumer<List<String>> add) {
                Set<String> existing = new HashSet<>();
                current.forEach(entry -> existing.add(Objects.toString(entry)));
                parent.getMinecraft().setScreen(new RegistryPickerScreen(parent, kind, existing, add));
            }
        };
    }

    /** Picks one or more blocks, then the item they smelt into, producing "block|result" entries. */
    static EntryPicker smeltingRecipe() {
        return new EntryPicker() {
            @Override
            public boolean isAvailable() {
                return true;
            }

            @Override
            public void open(Screen parent, List<?> current, Consumer<List<String>> add) {
                // a block can only have one recipe, so blocks that already have one aren't selectable
                Set<String> existing = new HashSet<>();
                current.forEach(entry -> existing.add(Objects.toString(entry).split("\\|", -1)[0].trim()));
                parent.getMinecraft().setScreen(new RegistryPickerScreen(parent, RegistryPickerScreen.Kind.ALL_BLOCKS, existing,
                        blocks -> parent.getMinecraft().setScreen(new RegistryPickerScreen(parent, RegistryPickerScreen.Kind.ALL_ITEMS,
                                Set.of(), true, results -> {
                            if (results.isEmpty()) return;
                            add.accept(blocks.stream().map(block -> block + "|" + results.getFirst()).toList());
                        }))));
            }
        };
    }
}
