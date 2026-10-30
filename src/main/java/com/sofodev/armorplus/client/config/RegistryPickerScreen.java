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
import com.sofodev.thedragonlib.client.config.EntryPickerScreen;
import com.sofodev.thedragonlib.client.config.EntryPickerScreen.Candidate;
import com.sofodev.thedragonlib.client.config.EntryPickerScreen.Category;
import com.sofodev.thedragonlib.client.config.RegistryCandidates;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.ModList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * Builds the ArmorPlus-specific candidate/category lists and opens them in TheDragonLib's generic
 * {@link EntryPickerScreen}.
 */
public final class RegistryPickerScreen {

    private RegistryPickerScreen() {
    }

    private static final EntryPickerScreen.Labels LABELS = new EntryPickerScreen.Labels(
            Component.translatable("armorplus.configuration.picker.category"),
            Component.translatable("armorplus.configuration.picker.search"),
            Component.translatable("armorplus.configuration.picker.select_shown"),
            n -> Component.translatable("armorplus.configuration.picker.add", n),
            id -> Component.translatable("armorplus.configuration.picker.already_added", id));

    public enum Kind {
        /** ArmorPlus items, grouped by item type. */
        ITEMS("items"),
        /** ArmorPlus blocks, grouped by block type. */
        BLOCKS("blocks"),
        /** Blocks from every mod, grouped by mod. */
        ALL_BLOCKS("all_blocks"),
        /** Items from every mod, grouped by mod. */
        ALL_ITEMS("all_items"),
        /** Enchantments, grouped by mod. */
        ENCHANTMENTS("enchantments"),
        /** Mob effects from every mod plus the ArmorPlus abilities, grouped by mod. */
        EFFECTS("effects");

        private final String titleKey;

        Kind(String titleKey) {
            this.titleKey = "armorplus.configuration.picker.title." + titleKey;
        }

        public boolean isAvailable() {
            return true;
        }
    }

    private static Category category(String key, java.util.function.Predicate<Candidate> test) {
        return new Category(Component.translatable("armorplus.configuration.picker.category." + key), test);
    }

    private static final Category ALL = category("all", c -> true);

    private static final List<Category> ITEM_CATEGORIES = List.of(ALL,
            category("armor", c -> c.entry() instanceof APArmorItem),
            category("weapons", c -> c.entry() instanceof APSwordItem || c.entry() instanceof APBattleAxeItem
                    || c.entry() instanceof APPickaxeItem || c.entry() instanceof APShovelItem
                    || c.entry() instanceof APBowItem || c.entry() instanceof APMaceItem),
            category("arrows", c -> c.entry() instanceof APArrowItem),
            category("souls", c -> c.entry() instanceof SoulItem),
            category("bases", c -> c.id().endsWith("_base")),
            category("materials", c -> !(c.entry() instanceof APArmorItem || c.entry() instanceof APSwordItem
                    || c.entry() instanceof APBattleAxeItem || c.entry() instanceof APPickaxeItem
                    || c.entry() instanceof APShovelItem || c.entry() instanceof APBowItem || c.entry() instanceof APMaceItem
                    || c.entry() instanceof APArrowItem || c.entry() instanceof SoulItem || c.id().endsWith("_base"))));

    private static final List<Category> BLOCK_CATEGORIES = List.of(ALL,
            category("ores", c -> c.entry() instanceof CrystalOreBlock || c.entry() instanceof APLavaBlock || c.entry() instanceof APFrostBlock),
            category("stone_bricks", c -> c.id().contains("_stone_brick")),
            category("castle_blocks", c -> c.id().contains("_castle_block")),
            category("other_blocks", c -> !(c.entry() instanceof CrystalOreBlock || c.entry() instanceof APLavaBlock
                    || c.entry() instanceof APFrostBlock || c.id().contains("_stone_brick") || c.id().contains("_castle_block"))));

    /**
     * @param existing  ids that are already in the list; shown but not selectable
     * @param single    whether only one entry may be selected at a time
     * @param onConfirm receives the selected ids, called after returning to {@code parent}
     */
    public static void open(Screen parent, Kind kind, Set<String> existing, boolean single, Consumer<List<String>> onConfirm) {
        List<Candidate> candidates = switch (kind) {
            case ITEMS -> collectItems(true);
            case BLOCKS -> collectBlocks(true);
            case ALL_ITEMS -> collectItems(false);
            case ALL_BLOCKS -> collectBlocks(false);
            case ENCHANTMENTS -> collectEnchantments();
            case EFFECTS -> collectEffects();
        };
        List<Category> categories = switch (kind) {
            case ITEMS -> ITEM_CATEGORIES;
            case BLOCKS -> BLOCK_CATEGORIES;
            default -> namespaceCategories(candidates);
        };
        Component title = Component.translatable(kind.titleKey);
        parent.getMinecraft().setScreen(new EntryPickerScreen(title, parent, candidates, categories, existing, single, LABELS, onConfirm));
    }

    private static List<Candidate> collectItems(boolean armorPlusOnly) {
        if (!armorPlusOnly) return RegistryCandidates.of(RegistryCandidates.Source.ITEM);
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

    private static List<Candidate> collectBlocks(boolean armorPlusOnly) {
        if (!armorPlusOnly) return RegistryCandidates.of(RegistryCandidates.Source.BLOCK);
        List<Candidate> result = new ArrayList<>();
        for (Block block : BuiltInRegistries.BLOCK) {
            Identifier id = BuiltInRegistries.BLOCK.getKey(block);
            if (!id.getNamespace().equals(MODID)) continue;
            result.add(new Candidate(id.toString(), icon(block.asItem()), block.getName(), block));
        }
        return result;
    }

    private static List<Candidate> collectEnchantments() {
        return RegistryCandidates.of(RegistryCandidates.Source.ENCHANTMENT);
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
}
