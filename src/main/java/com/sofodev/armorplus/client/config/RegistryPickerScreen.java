package com.sofodev.armorplus.client.config;

import com.sofodev.armorplus.registry.block.APFrostBlock;
import com.sofodev.armorplus.registry.block.APLavaBlock;
import com.sofodev.armorplus.registry.block.ore.CrystalOreBlock;
import com.sofodev.armorplus.registry.item.armor.APArmorItem;
import com.sofodev.armorplus.registry.item.arrow.APArrowItem;
import com.sofodev.armorplus.registry.item.special.SoulItem;
import com.sofodev.armorplus.registry.item.tool.APBattleAxeItem;
import com.sofodev.armorplus.registry.item.tool.APBowItem;
import com.sofodev.armorplus.registry.item.tool.APMaceItem;
import com.sofodev.armorplus.registry.item.tool.APPickaxeItem;
import com.sofodev.armorplus.registry.item.tool.APShovelItem;
import com.sofodev.armorplus.registry.item.tool.APSwordItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static com.sofodev.armorplus.ArmorPlus.MODID;

public class RegistryPickerScreen extends Screen {
    private static final int ROW_HEIGHT = 22;
    private static final int COLOR_WHITE = 0xFFFFFFFF;
    private static final int COLOR_GRAY = 0xFF909090;
    private static final int COLOR_SELECTED = 0xFF55FF55;
    private static final int COLOR_SELECTED_BG = 0x4055FF55;

    public enum Kind {
        ITEMS, BLOCKS
    }

    private record Candidate(String id, ItemStack icon, Component name, Object entry) {
    }

    private record Category(String key, Predicate<Candidate> test) {
        Component label() {
            return Component.translatable("armorplus.configuration.picker.category." + key);
        }
    }

    private static final Category ALL = new Category("all", c -> true);

    private static final List<Category> ITEM_CATEGORIES = List.of(ALL,
            new Category("armor", c -> c.entry() instanceof APArmorItem),
            new Category("weapons", c -> c.entry() instanceof APSwordItem || c.entry() instanceof APBattleAxeItem
                    || c.entry() instanceof APPickaxeItem || c.entry() instanceof APShovelItem
                    || c.entry() instanceof APBowItem || c.entry() instanceof APMaceItem),
            new Category("arrows", c -> c.entry() instanceof APArrowItem),
            new Category("souls", c -> c.entry() instanceof SoulItem),
            new Category("bases", c -> c.id().endsWith("_base")),
            new Category("materials", c -> !(c.entry() instanceof APArmorItem || c.entry() instanceof APSwordItem
                    || c.entry() instanceof APBattleAxeItem || c.entry() instanceof APPickaxeItem
                    || c.entry() instanceof APShovelItem || c.entry() instanceof APBowItem || c.entry() instanceof APMaceItem
                    || c.entry() instanceof APArrowItem || c.entry() instanceof SoulItem || c.id().endsWith("_base"))));

    private static final List<Category> BLOCK_CATEGORIES = List.of(ALL,
            new Category("ores", c -> c.entry() instanceof CrystalOreBlock || c.entry() instanceof APLavaBlock || c.entry() instanceof APFrostBlock),
            new Category("stone_bricks", c -> c.id().contains("_stone_brick")),
            new Category("castle_blocks", c -> c.id().contains("_castle_block")),
            new Category("other_blocks", c -> !(c.entry() instanceof CrystalOreBlock || c.entry() instanceof APLavaBlock
                    || c.entry() instanceof APFrostBlock || c.id().contains("_stone_brick") || c.id().contains("_castle_block"))));

    private final Screen parent;
    private final Set<String> existing;
    private final Consumer<List<String>> onConfirm;
    private final List<Candidate> candidates;
    private final List<Category> categories;
    private final Set<String> selected = new LinkedHashSet<>();
    private Category category = ALL;
    private String query = "";
    private PickerList pickerList;
    private Button confirmButton;

    public RegistryPickerScreen(Screen parent, Kind kind, Set<String> existing, Consumer<List<String>> onConfirm) {
        super(Component.translatable(kind == Kind.ITEMS ? "armorplus.configuration.picker.title.items" : "armorplus.configuration.picker.title.blocks"));
        this.parent = parent;
        this.existing = existing;
        this.onConfirm = onConfirm;
        this.categories = kind == Kind.ITEMS ? ITEM_CATEGORIES : BLOCK_CATEGORIES;
        this.candidates = kind == Kind.ITEMS ? collectItems() : collectBlocks();
    }

    private static List<Candidate> collectItems() {
        List<Candidate> result = new ArrayList<>();
        for (Item item : BuiltInRegistries.ITEM) {
            Identifier id = BuiltInRegistries.ITEM.getKey(item);
            // block items are governed by hiddenBlocks
            if (!id.getNamespace().equals(MODID) || item instanceof BlockItem) continue;
            result.add(new Candidate(id.toString(), icon(item), Component.translatable(item.getDescriptionId()), item));
        }
        return result;
    }

    private static ItemStack icon(Item item) {
        return item.builtInRegistryHolder().areComponentsBound() ? new ItemStack(item) : ItemStack.EMPTY;
    }

    private static List<Candidate> collectBlocks() {
        List<Candidate> result = new ArrayList<>();
        for (Block block : BuiltInRegistries.BLOCK) {
            Identifier id = BuiltInRegistries.BLOCK.getKey(block);
            if (!id.getNamespace().equals(MODID)) continue;
            result.add(new Candidate(id.toString(), icon(block.asItem()), block.getName(), block));
        }
        return result;
    }

    @Override
    protected void init() {
        int cx = width / 2;
        addRenderableWidget(CycleButton.builder(Category::label, category)
                .withValues(categories)
                .create(cx - 155, 22, 150, 20, Component.translatable("armorplus.configuration.picker.category"),
                        (button, value) -> {
                            category = value;
                            refresh();
                        }));
        EditBox search = addRenderableWidget(new EditBox(font, cx + 5, 22, 150, 20, Component.translatable("armorplus.configuration.picker.search")));
        search.setHint(Component.translatable("armorplus.configuration.picker.search"));
        search.setValue(query);
        search.setResponder(value -> {
            query = value;
            refresh();
        });
        pickerList = addRenderableWidget(new PickerList(minecraft, width, height - 50 - 36, 50));
        addRenderableWidget(Button.builder(Component.translatable("armorplus.configuration.picker.select_shown"), button -> toggleShown())
                .bounds(cx - 155, height - 28, 100, 20).build());
        confirmButton = addRenderableWidget(Button.builder(confirmLabel(), button -> confirm())
                .bounds(cx - 50, height - 28, 100, 20).build());
        addRenderableWidget(Button.builder(CommonComponents.GUI_CANCEL, button -> onClose())
                .bounds(cx + 55, height - 28, 100, 20).build());
        refresh();
    }

    private List<Candidate> shown() {
        String q = query.toLowerCase(Locale.ROOT).trim();
        return candidates.stream()
                .filter(category.test())
                .filter(c -> q.isEmpty() || c.id().contains(q) || c.name().getString().toLowerCase(Locale.ROOT).contains(q))
                .toList();
    }

    private void refresh() {
        if (pickerList == null) return;
        pickerList.setEntries(shown());
        pickerList.setScrollAmount(0);
    }

    private void toggleShown() {
        List<String> addable = shown().stream().map(Candidate::id).filter(id -> !existing.contains(id)).toList();
        if (selected.containsAll(addable)) {
            addable.forEach(selected::remove);
        } else {
            selected.addAll(addable);
        }
        updateConfirm();
    }

    private void toggle(Candidate candidate) {
        if (existing.contains(candidate.id())) return;
        if (!selected.remove(candidate.id())) selected.add(candidate.id());
        updateConfirm();
    }

    private Component confirmLabel() {
        return Component.translatable("armorplus.configuration.picker.add", selected.size());
    }

    private void updateConfirm() {
        confirmButton.setMessage(confirmLabel());
        confirmButton.active = !selected.isEmpty();
    }

    private void confirm() {
        onConfirm.accept(new ArrayList<>(selected));
        onClose();
    }

    @Override
    public void onClose() {
        minecraft.setScreen(parent);
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);
        graphics.centeredText(font, title, width / 2, 8, COLOR_WHITE);
        if (confirmButton != null) confirmButton.active = !selected.isEmpty();
    }

    private class PickerList extends ObjectSelectionList<PickerEntry> {
        PickerList(Minecraft minecraft, int width, int height, int y) {
            super(minecraft, width, height, y, ROW_HEIGHT);
        }

        void setEntries(List<Candidate> shown) {
            clearEntries();
            shown.forEach(candidate -> addEntry(new PickerEntry(candidate)));
        }

        @Override
        public int getRowWidth() {
            return 300;
        }
    }

    private class PickerEntry extends ObjectSelectionList.Entry<PickerEntry> {
        private final Candidate candidate;

        PickerEntry(Candidate candidate) {
            this.candidate = candidate;
        }

        @Override
        public Component getNarration() {
            return candidate.name();
        }

        @Override
        public void extractContent(GuiGraphicsExtractor graphics, int mouseX, int mouseY, boolean hovered, float a) {
            boolean added = existing.contains(candidate.id());
            boolean picked = selected.contains(candidate.id());
            int x = getContentX();
            int y = getContentY();
            if (picked) {
                graphics.fill(x, y, getContentRight(), getContentBottom(), COLOR_SELECTED_BG);
            }
            if (!candidate.icon().isEmpty()) {
                graphics.item(candidate.icon(), x + 2, y + (getContentHeight() - 16) / 2);
            }
            int textX = x + 22;
            graphics.text(font, candidate.name(), textX, y + 1, added ? COLOR_GRAY : picked ? COLOR_SELECTED : COLOR_WHITE);
            Component sub = added
                    ? Component.translatable("armorplus.configuration.picker.already_added", candidate.id())
                    : Component.literal(candidate.id());
            graphics.text(font, sub, textX, y + 11, COLOR_GRAY);
        }

        @Override
        public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
            toggle(candidate);
            return true;
        }
    }
}
