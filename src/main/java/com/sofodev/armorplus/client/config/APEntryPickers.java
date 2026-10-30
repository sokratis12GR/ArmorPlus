package com.sofodev.armorplus.client.config;

import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.thedragonlib.client.config.EntryPicker;
import net.minecraft.client.gui.screens.Screen;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * ArmorPlus-specific {@link EntryPicker} factories, backed by {@link RegistryPickerScreen}.
 */
public final class APEntryPickers {

    private APEntryPickers() {
    }

    /** Picks registry ids, skipping those already in the list. */
    public static EntryPicker of(RegistryPickerScreen.Kind kind) {
        return new EntryPicker() {
            @Override
            public boolean isAvailable() {
                return kind.isAvailable();
            }

            @Override
            public void open(Screen parent, List<?> current, Consumer<List<String>> add) {
                Set<String> existing = new HashSet<>();
                current.forEach(entry -> existing.add(Objects.toString(entry)));
                RegistryPickerScreen.open(parent, kind, existing, false, add);
            }
        };
    }

    /**
     * Picks effects/abilities, producing buff entries: effects get {@code levelSuffix} appended (e.g. "minecraft:haste" + "|1"),
     * ArmorPlus abilities are added as-is. An empty suffix produces plain ids (used by "disabledBuffs").
     */
    public static EntryPicker buffs(String levelSuffix) {
        return new EntryPicker() {
            @Override
            public boolean isAvailable() {
                return true;
            }

            @Override
            public void open(Screen parent, List<?> current, Consumer<List<String>> add) {
                Set<String> existing = new HashSet<>();
                current.forEach(entry -> existing.add(BuffParser.idOf(entry)));
                RegistryPickerScreen.open(parent, RegistryPickerScreen.Kind.EFFECTS, existing, false,
                        ids -> add.accept(ids.stream().map(id -> id.startsWith(MODID + ":") ? id : id + levelSuffix).toList()));
            }
        };
    }

    /** Picks one or more blocks, then the item they smelt into, producing "block|result" entries. */
    public static EntryPicker smeltingRecipe() {
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
                RegistryPickerScreen.open(parent, RegistryPickerScreen.Kind.ALL_BLOCKS, existing, false,
                        blocks -> RegistryPickerScreen.open(parent, RegistryPickerScreen.Kind.ALL_ITEMS, Set.of(), true, results -> {
                            if (results.isEmpty()) return;
                            add.accept(blocks.stream().map(block -> block + "|" + results.getFirst()).toList());
                        }));
            }
        };
    }
}
