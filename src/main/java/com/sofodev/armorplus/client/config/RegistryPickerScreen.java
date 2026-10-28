package com.sofodev.armorplus.client.config;

import com.sofodev.armorplus.registry.block.APFrostBlock;
import com.sofodev.armorplus.registry.block.APLavaBlock;
import com.sofodev.armorplus.registry.block.ore.CrystalOreBlock;
import com.sofodev.armorplus.registry.item.armor.APArmorItem;
import com.sofodev.armorplus.registry.item.extra.Buff;
import com.sofodev.armorplus.registry.item.extra.DeBuff;
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
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.ModList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
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
        /** ArmorPlus items, grouped by item type. */
        ITEMS("items"),
        /** ArmorPlus blocks, grouped by block type. */
        BLOCKS("blocks"),
        /** Blocks from every mod, grouped by mod. */
        ALL_BLOCKS("all_blocks"),
        /** Items from every mod, grouped by mod. */
        ALL_ITEMS("all_items"),
        /** Enchantments from the current world, grouped by mod. */
        ENCHANTMENTS("enchantments"),
        /** Mob effects from every mod plus the ArmorPlus abilities, grouped by mod. */
        EFFECTS("effects");

        private final String titleKey;

        Kind(String titleKey) {
            this.titleKey = "armorplus.configuration.picker.title." + titleKey;
        }

        /** Enchantments are a datapack registry, so they can only be listed while in a world. */
        public boolean isAvailable() {
            return this != ENCHANTMENTS || Minecraft.getInstance().level != null;
        }
    }

    private record Candidate(String id, ItemStack icon, Component name, Object entry) {
    }

    private record Category(Component label, Predicate<Candidate> test) {
        static Category of(String key, Predicate<Candidate> test) {
            return new Category(Component.translatable("armorplus.configuration.picker.category." + key), test);
        }
    }

    private static final Category ALL = Category.of("all", c -> true);

    private static final List<Category> ITEM_CATEGORIES = List.of(ALL,
            Category.of("armor", c -> c.entry() instanceof APArmorItem),
            Category.of("weapons", c -> c.entry() instanceof APSwordItem || c.entry() instanceof APBattleAxeItem
                    || c.entry() instanceof APPickaxeItem || c.entry() instanceof APShovelItem
                    || c.entry() instanceof APBowItem || c.entry() instanceof APMaceItem),
            Category.of("arrows", c -> c.entry() instanceof APArrowItem),
            Category.of("souls", c -> c.entry() instanceof SoulItem),
            Category.of("bases", c -> c.id().endsWith("_base")),
            Category.of("materials", c -> !(c.entry() instanceof APArmorItem || c.entry() instanceof APSwordItem
                    || c.entry() instanceof APBattleAxeItem || c.entry() instanceof APPickaxeItem
                    || c.entry() instanceof APShovelItem || c.entry() instanceof APBowItem || c.entry() instanceof APMaceItem
                    || c.entry() instanceof APArrowItem || c.entry() instanceof SoulItem || c.id().endsWith("_base"))));

    private static final List<Category> BLOCK_CATEGORIES = List.of(ALL,
            Category.of("ores", c -> c.entry() instanceof CrystalOreBlock || c.entry() instanceof APLavaBlock || c.entry() instanceof APFrostBlock),
            Category.of("stone_bricks", c -> c.id().contains("_stone_brick")),
            Category.of("castle_blocks", c -> c.id().contains("_castle_block")),
            Category.of("other_blocks", c -> !(c.entry() instanceof CrystalOreBlock || c.entry() instanceof APLavaBlock
                    || c.entry() instanceof APFrostBlock || c.id().contains("_stone_brick") || c.id().contains("_castle_block"))));

    private final Screen parent;
    private final Set<String> existing;
    private final boolean single;
    private final Consumer<List<String>> onConfirm;
    private final List<Candidate> candidates;
    private final List<Category> categories;
    private final Set<String> selected = new LinkedHashSet<>();
    private Category category = ALL;
    private String query = "";
    private PickerList pickerList;
    private Button confirmButton;

    public RegistryPickerScreen(Screen parent, Kind kind, Set<String> existing, Consumer<List<String>> onConfirm) {
        this(parent, kind, existing, false, onConfirm);
    }

    /**
     * @param existing  ids that are already in the list; shown but not selectable
     * @param single    whether only one entry may be selected at a time
     * @param onConfirm receives the selected ids, called after returning to {@code parent}
     */
    public RegistryPickerScreen(Screen parent, Kind kind, Set<String> existing, boolean single, Consumer<List<String>> onConfirm) {
        super(Component.translatable(kind.titleKey));
        this.parent = parent;
        this.existing = existing;
        this.single = single;
        this.onConfirm = onConfirm;
        this.candidates = switch (kind) {
            case ITEMS -> collectItems(true);
            case BLOCKS -> collectBlocks(true);
            case ALL_ITEMS -> collectItems(false);
            case ALL_BLOCKS -> collectBlocks(false);
            case ENCHANTMENTS -> collectEnchantments();
            case EFFECTS -> collectEffects();
        };
        this.categories = switch (kind) {
            case ITEMS -> ITEM_CATEGORIES;
            case BLOCKS -> BLOCK_CATEGORIES;
            default -> namespaceCategories(candidates);
        };
    }

    private static List<Candidate> collectItems(boolean armorPlusOnly) {
        List<Candidate> result = new ArrayList<>();
        for (Item item : BuiltInRegistries.ITEM) {
            Identifier id = BuiltInRegistries.ITEM.getKey(item);
            if (armorPlusOnly) {
                // block items are governed by hiddenBlocks
                if (!id.getNamespace().equals(MODID) || item instanceof BlockItem) continue;
            } else if (item == Items.AIR) {
                continue;
            }
            result.add(new Candidate(id.toString(), icon(item), Component.translatable(item.getDescriptionId()), item));
        }
        return result;
    }

    private static ItemStack icon(Item item) {
        return item.builtInRegistryHolder().areComponentsBound() ? new ItemStack(item) : ItemStack.EMPTY;
    }

    private static List<Candidate> collectBlocks(boolean armorPlusOnly) {
        List<Candidate> result = new ArrayList<>();
        for (Block block : BuiltInRegistries.BLOCK) {
            Identifier id = BuiltInRegistries.BLOCK.getKey(block);
            if (armorPlusOnly ? !id.getNamespace().equals(MODID) : block.defaultBlockState().isAir()) continue;
            result.add(new Candidate(id.toString(), icon(block.asItem()), block.getName(), block));
        }
        return result;
    }

    private static List<Candidate> collectEnchantments() {
        List<Candidate> result = new ArrayList<>();
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null) return result;
        Registry<Enchantment> registry = minecraft.level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        ItemStack book = icon(Items.ENCHANTED_BOOK);
        registry.listElements().forEach(holder -> result.add(new Candidate(holder.key().identifier().toString(), book,
                holder.value().description(), holder.value())));
        result.sort(Comparator.comparing(Candidate::id));
        return result;
    }

    private static List<Candidate> collectEffects() {
        List<Candidate> result = new ArrayList<>();
        ItemStack potion = icon(Items.POTION);
        for (MobEffect effect : BuiltInRegistries.MOB_EFFECT) {
            Identifier id = BuiltInRegistries.MOB_EFFECT.getKey(effect);
            result.add(new Candidate(id.toString(), potion, Component.translatable(effect.getDescriptionId()), effect));
        }
        ItemStack star = icon(Items.NETHER_STAR);
        Set<String> abilities = new TreeSet<>();
        for (Buff buff : Buff.values()) if (buff != Buff.NONE && !buff.isEffect()) abilities.add(buff.name().toLowerCase(Locale.ENGLISH));
        for (DeBuff buff : DeBuff.values()) if (buff != DeBuff.NONE && !buff.isEffect()) abilities.add(buff.name().toLowerCase(Locale.ENGLISH));
        for (String ability : abilities) {
            result.add(new Candidate(MODID + ":" + ability, star, Component.translatable("armorplus.buff." + ability), ability));
        }
        result.sort(Comparator.comparing(Candidate::id));
        return result;
    }

    private static List<Category> namespaceCategories(List<Candidate> candidates) {
        List<Category> result = new ArrayList<>();
        result.add(ALL);
        Set<String> namespaces = new TreeSet<>();
        candidates.forEach(c -> namespaces.add(namespace(c.id())));
        for (String namespace : namespaces) {
            Component label = Component.literal(ModList.get().getModContainerById(namespace)
                    .map(container -> container.getModInfo().getDisplayName())
                    .orElse(namespace));
            result.add(new Category(label, c -> namespace(c.id()).equals(namespace)));
        }
        return result;
    }

    private static String namespace(String id) {
        int colon = id.indexOf(':');
        return colon < 0 ? Identifier.DEFAULT_NAMESPACE : id.substring(0, colon);
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
        Button toggleShown = addRenderableWidget(Button.builder(Component.translatable("armorplus.configuration.picker.select_shown"), button -> toggleShown())
                .bounds(cx - 155, height - 28, 100, 20).build());
        toggleShown.active = !single;
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
        if (!selected.remove(candidate.id())) {
            if (single) selected.clear();
            selected.add(candidate.id());
        }
        updateConfirm();
    }

    private Component confirmLabel() {
        return single ? CommonComponents.GUI_DONE : Component.translatable("armorplus.configuration.picker.add", selected.size());
    }

    private void updateConfirm() {
        confirmButton.setMessage(confirmLabel());
        confirmButton.active = !selected.isEmpty();
    }

    private void confirm() {
        List<String> result = new ArrayList<>(selected);
        // return first so the callback can open a follow-up screen
        onClose();
        onConfirm.accept(result);
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
