package com.sofodev.armorplus.registry.rune;

import com.sofodev.armorplus.api.RuneData;
import com.sofodev.armorplus.api.RuneKind;
import com.sofodev.armorplus.api.SocketResult;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.config.ArmorPlusConfig.MaterialConfig;
import com.sofodev.armorplus.config.AvailabilityConfig;
import com.sofodev.armorplus.events.RangedRuneEvents;
import com.sofodev.armorplus.registry.ModDataComponents;
import com.sofodev.armorplus.registry.item.armor.APArmorItem;
import com.sofodev.armorplus.registry.item.armor.ArmorEffects;
import com.sofodev.armorplus.registry.item.armor.SetRequirement;
import com.sofodev.armorplus.registry.item.extra.Buff;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffNames;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.armorplus.registry.item.extra.IBuff;
import com.sofodev.armorplus.registry.item.tool.APBattleAxeItem;
import com.sofodev.armorplus.registry.item.tool.APBowItem;
import com.sofodev.armorplus.registry.item.tool.APMaceItem;
import com.sofodev.armorplus.registry.item.tool.APPickaxeItem;
import com.sofodev.armorplus.registry.item.tool.APShovelItem;
import com.sofodev.armorplus.registry.item.tool.APSwordItem;
import com.sofodev.armorplus.registry.item.tool.properties.mace.APMaceMaterial;
import com.sofodev.armorplus.registry.item.tool.properties.tool.IAPTool;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.equipment.Equippable;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * Rune rules shared by the Rune Altar (extraction, and socketing and upgrading with a {@link RuneRitual}), the Grindstone (removal), the buff handlers and
 * the tooltips/item models.
 * <ul>
 *     <li>Extraction only works on ArmorPlus equipment, since the buffs come from the material's config.</li>
 *     <li>Runes can be socketed into ArmorPlus equipment and, as allowed by "allowVanillaGear", "allowModdedGear" and "excludedRuneMods",
 *     into any armor ({@code #minecraft:enchantable/armor}), weapon ({@code #minecraft:enchantable/weapon}) or tool
 *     ({@code #minecraft:enchantable/mining}), bow or crossbow. Pickaxes and shovels are tools; swords, battle axes, maces and axes weapons.</li>
 *     <li>Tool runes work while the tool is held in the main hand.</li>
 *     <li>Ranged runes are stored on each arrow when it's fired, see {@link RangedRuneEvents}.</li>
 *     <li>A rune can't be socketed if its buff is one of the equipment's own buffs or already socketed in it.</li>
 *     <li>Socketed armor runes work per piece; when several worn sources give the same buff, only the highest level counts.</li>
 * </ul>
 */
public final class Runes {

    public static final EquipmentSlot[] ARMOR_SLOTS = {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

    /** Parsed rune entries; registries don't change after startup, so the parse results can be kept. */
    private static final Map<RuneData, Optional<BuffInstance>> PARSED = new ConcurrentHashMap<>();

    /** The current "excludedRuneMods" as a set, since {@link #gearKind(ItemStack)} runs every frame; the config hands out the same list until it reloads. */
    private static volatile List<?> excludedModsSource;
    private static volatile Set<String> excludedMods = Set.of();

    private Runes() {
    }

    public static boolean enabled() {
        return ArmorPlusConfig.enableRunes.get();
    }

    /* ---------------------------------------- Equipment ---------------------------------------- */

    /** @return the kind of runes the equipment accepts, or null if it can't hold runes */
    public static @Nullable RuneKind gearKind(ItemStack stack) {
        if (stack.isEmpty()) return null;
        Item item = stack.getItem();
        if (item instanceof APArmorItem) return RuneKind.ARMOR;
        if (item instanceof APPickaxeItem || item instanceof APShovelItem) return RuneKind.TOOL;
        if (toolMaterial(item) != null) return RuneKind.WEAPON;
        if (item instanceof APBowItem) return RuneKind.RANGED;
        // Other ArmorPlus items (bases, materials, ...) never hold runes
        String namespace = BuiltInRegistries.ITEM.getKey(item).getNamespace();
        if (namespace.equals(MODID) || !allowsForeignGear(namespace)) return null;
        if (item instanceof ProjectileWeaponItem) return RuneKind.RANGED;
        if (stack.is(ItemTags.ARMOR_ENCHANTABLE)) return RuneKind.ARMOR;
        // axes are both, they keep counting as weapons
        if (stack.is(ItemTags.WEAPON_ENCHANTABLE)) return RuneKind.WEAPON;
        if (stack.is(ItemTags.MINING_ENCHANTABLE)) return RuneKind.TOOL;
        return null;
    }

    /** @return true if the config lets equipment of this vanilla or other mod's namespace hold runes */
    private static boolean allowsForeignGear(String namespace) {
        if (namespace.equals(Identifier.DEFAULT_NAMESPACE)) return ArmorPlusConfig.allowVanillaGear.get();
        if (!ArmorPlusConfig.allowModdedGear.get()) return false;
        List<? extends String> source = ArmorPlusConfig.excludedRuneMods.get();
        if (source != excludedModsSource) {
            excludedMods = Set.copyOf(source);
            excludedModsSource = source;
        }
        return !excludedMods.contains(namespace);
    }

    public static int maxSockets(RuneKind kind) {
        return switch (kind) {
            case ARMOR -> ArmorPlusConfig.maxArmorSockets.get();
            case WEAPON -> ArmorPlusConfig.maxWeaponSockets.get();
            case TOOL -> ArmorPlusConfig.maxToolSockets.get();
            case RANGED -> ArmorPlusConfig.maxRangedSockets.get();
        };
    }

    public static List<RuneData> sockets(ItemStack stack) {
        Sockets sockets = stack.get(ModDataComponents.SOCKETS.get());
        return sockets == null ? List.of() : sockets.runes();
    }

    /** @return false for equipment that hasn't rolled its sockets yet (only possible with "randomSockets") */
    public static boolean hasRolledSockets(ItemStack stack) {
        return !ArmorPlusConfig.randomSockets.get() || stack.has(ModDataComponents.SOCKET_SLOTS.get());
    }

    /** @return the number of sockets of the equipment: the rolled count (capped at the max), or the max without "randomSockets" */
    public static int socketCapacity(ItemStack stack) {
        RuneKind kind = gearKind(stack);
        if (kind == null) return 0;
        int max = maxSockets(kind);
        if (!ArmorPlusConfig.randomSockets.get()) return max;
        Integer rolled = stack.get(ModDataComponents.SOCKET_SLOTS.get());
        return rolled == null ? 0 : Math.min(rolled, max);
    }

    /**
     * Rolls the socket count of equipment that doesn't have one yet, using the configured weights.
     *
     * @return true if the stack was changed
     */
    public static boolean rollSockets(ItemStack stack, RandomSource random) {
        // cheapest checks first, this runs over every inventory slot of every player once a second
        if (stack.isEmpty() || stack.has(ModDataComponents.SOCKET_SLOTS.get()) || !ArmorPlusConfig.randomSockets.get() || !enabled()) return false;
        RuneKind kind = gearKind(stack);
        if (kind == null) return false;
        stack.set(ModDataComponents.SOCKET_SLOTS.get(), Math.min(rollSocketCount(kind, random), maxSockets(kind)));
        return true;
    }

    private static int rollSocketCount(RuneKind kind, RandomSource random) {
        List<? extends Integer> weights = switch (kind) {
            case ARMOR -> ArmorPlusConfig.armorSocketWeights.get();
            case WEAPON -> ArmorPlusConfig.weaponSocketWeights.get();
            case TOOL -> ArmorPlusConfig.toolSocketWeights.get();
            case RANGED -> ArmorPlusConfig.rangedSocketWeights.get();
        };
        int total = 0;
        for (int weight : weights) total += weight;
        if (total <= 0) return 0;
        int roll = random.nextInt(total);
        for (int count = 0; count < weights.size(); count++) {
            roll -= weights.get(count);
            if (roll < 0) return count;
        }
        return 0;
    }

    /** @return true if a Socket Punch can add a socket to the equipment */
    public static boolean canAddSocket(ItemStack gear) {
        RuneKind kind = gearKind(gear);
        return enabled() && kind != null && ArmorPlusConfig.enableSocketPunch.get() && ArmorPlusConfig.randomSockets.get()
                && hasRolledSockets(gear) && socketCapacity(gear) < maxSockets(kind);
    }

    /** @return a copy of the equipment with one more socket, see {@link #canAddSocket(ItemStack)} */
    public static ItemStack withExtraSocket(ItemStack gear) {
        ItemStack result = gear.copyWithCount(1);
        result.set(ModDataComponents.SOCKET_SLOTS.get(), socketCapacity(gear) + 1);
        return result;
    }

    /**
     * The state shown by the inventory overlay: "none" (no overlay), "empty", "partial" or "full".
     */
    public static String socketState(ItemStack stack) {
        // evaluated every frame for every item shown in a GUI, so items without sockets bail out before the tag lookups
        if (stack.isEmpty() || !enabled() || !ArmorPlusConfig.showSocketOverlay.get() || !hasRolledSockets(stack)) return "none";
        if (gearKind(stack) == null) return "none";
        int capacity = socketCapacity(stack);
        int used = sockets(stack).size();
        if (capacity <= 0 && used == 0) return "none";
        if (used == 0) return "empty";
        return used >= capacity ? "full" : "partial";
    }

    public static @Nullable IAPTool toolMaterial(Item item) {
        if (item instanceof APSwordItem sword) return sword.getMat();
        if (item instanceof APBattleAxeItem battleAxe) return battleAxe.getMat();
        if (item instanceof APPickaxeItem pickaxe) return pickaxe.getMat();
        if (item instanceof APShovelItem shovel) return shovel.getMat();
        if (item instanceof APMaceItem mace && mace.mat instanceof APMaceMaterial maceMaterial) return maceMaterial.getToolMaterial();
        return null;
    }

    private static @Nullable MaterialConfig materialConfig(Item item) {
        if (item instanceof APArmorItem armor) return armor.getMat().config() instanceof MaterialConfig cfg ? cfg : null;
        IAPTool tool = item instanceof APBowItem bow ? bow.getMat() : toolMaterial(item);
        return tool != null && tool.config() instanceof MaterialConfig cfg ? cfg : null;
    }

    /**
     * @return the material's own buff entries of the rune kind the equipment accepts (e.g. "minecraft:haste|2") without the
     * switched off ("!") and extracted ones, at this item's levels, empty for other equipment. Bows use the on-hit effects and bow effects of their material, and materials have
     * no held (tool) effects of their own
     */
    public static List<String> nativeEntries(ItemStack stack) {
        RuneKind kind = gearKind(stack);
        MaterialConfig cfg = materialConfig(stack.getItem());
        if (kind == null || cfg == null) return List.of();
        return nativeEntries(cfg, kind).stream().filter(entry -> !isExtracted(stack, BuffParser.idOf(entry))).map(entry -> withNativeLevel(stack, entry)).toList();
    }

    private static List<String> nativeEntries(MaterialConfig cfg, RuneKind kind) {
        List<String> result = new ArrayList<>();
        if (kind == RuneKind.ARMOR) result.addAll(cfg.armorBuffs().get());
        if ((kind == RuneKind.WEAPON || kind == RuneKind.RANGED) && cfg.weaponBuffs() != null) result.addAll(cfg.weaponBuffs().get());
        if (kind == RuneKind.RANGED && cfg.bowBuffs() != null) result.addAll(cfg.bowBuffs().get());
        result.removeIf(entry -> !BuffParser.isValid(entry) || BuffParser.isDisabled(entry));
        return result;
    }

    /* ---------------------------------------- Runes ---------------------------------------- */

    public static @Nullable RuneData runeData(ItemStack stack) {
        return stack.get(ModDataComponents.RUNE.get());
    }

    /**
     * @return the rune's buff, or null if the rune is inactive (runes disabled, buff disabled for runes, or the buff/effect
     * doesn't exist anymore)
     */
    public static @Nullable BuffInstance resolve(RuneData rune) {
        if (!enabled() || RuneOverrides.of(rune.buffId()).disabled()) return null;
        return parse(rune);
    }

    /** Like {@link #resolve(RuneData)}, ignoring the config toggles. Used for display names. */
    public static @Nullable BuffInstance parse(RuneData rune) {
        return PARSED.computeIfAbsent(rune, r -> {
            BuffInstance instance = switch (r.kind()) {
                case ARMOR -> BuffParser.armor(r.entry());
                case WEAPON -> BuffParser.weapon(r.entry());
                case TOOL -> BuffParser.tool(r.entry());
                case RANGED -> BuffParser.ranged(r.entry());
            };
            if (instance == null || instance.getBuff() == Buff.NONE) return Optional.empty();
            IBuff buff = instance.getBuff();
            if (buff.isEffect() && buff.getEffect() == null) return Optional.empty();
            return Optional.of(instance);
        }).orElse(null);
    }

    /** @return the armor slot of the equipment (from its equippable component), or null */
    public static @Nullable EquipmentSlot armorSlot(ItemStack stack) {
        Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
        return equippable != null && equippable.slot().getType() == EquipmentSlot.Type.HUMANOID_ARMOR ? equippable.slot() : null;
    }

    /**
     * @return the armor pieces an armor rune fits into: the slots of its entry ("@head", kept from the set it was
     * extracted from), otherwise the "effectSlots" config; empty if it fits into any
     */
    public static Set<EquipmentSlot> runeSlots(RuneData rune) {
        if (rune.kind() != RuneKind.ARMOR) return Set.of();
        SetRequirement requirement = BuffParser.requirementOf(rune.entry());
        return requirement != null ? requirement.slots() : BuffNames.effectSlots(rune.buffId());
    }

    public static SocketResult canSocket(ItemStack gear, RuneData rune) {
        RuneKind kind = gearKind(gear);
        if (!enabled() || kind == null) return SocketResult.NOT_SOCKETABLE;
        if (kind != rune.kind()) return SocketResult.WRONG_KIND;
        Set<EquipmentSlot> slots = runeSlots(rune);
        if (!slots.isEmpty() && !slots.contains(armorSlot(gear))) return SocketResult.WRONG_SLOT;
        if (resolve(rune) == null) return SocketResult.INACTIVE;
        List<RuneData> socketed = sockets(gear);
        if (socketed.size() >= socketCapacity(gear)) return SocketResult.NO_FREE_SOCKET;
        String id = rune.buffId();
        if (ids(nativeEntries(gear)).contains(id)) return SocketResult.OWN_BUFF;
        for (RuneData other : socketed) {
            if (other.buffId().equals(id)) return SocketResult.ALREADY_SOCKETED;
        }
        return SocketResult.OK;
    }

    /**
     * @return the experience level cost to socket the rune ("runeOverrides" socket=..., else the datapack rune's
     * "socket_cost", else "socketXpCost", at most 39)
     */
    public static int socketCost(RuneData rune) {
        Integer override = RuneOverrides.of(rune.buffId()).socketCost();
        if (override == null) override = datapackRule(rune, def -> def.socketCost().orElse(null));
        return Math.min(39, override != null ? override : ArmorPlusConfig.socketXpCost.get());
    }

    /** @return a rule of the datapack rune with the rune's kind, buff and level ({@link RuneDefinitions#of(RuneData)}), or null */
    private static <T> @Nullable T datapackRule(RuneData rune, Function<RuneDefinition, @Nullable T> rule) {
        RuneDefinition definition = RuneDefinitions.of(rune);
        return definition == null ? null : rule.apply(definition);
    }

    /** @return a copy of the equipment with the rune socketed, see {@link #canSocket(ItemStack, RuneData)} */
    public static ItemStack withRune(ItemStack gear, RuneData rune) {
        ItemStack result = gear.copyWithCount(1);
        Sockets sockets = result.get(ModDataComponents.SOCKETS.get());
        result.set(ModDataComponents.SOCKETS.get(), sockets == null ? new Sockets(List.of(rune)) : sockets.with(rune));
        return result;
    }

    /** @return a copy of the equipment without that one rune, the other runes stay */
    public static ItemStack withoutRune(ItemStack gear, RuneData rune) {
        ItemStack result = gear.copyWithCount(1);
        setSockets(result, withoutOne(sockets(gear), rune));
        return result;
    }

    /** @return the runes with one copy of the given rune less, duplicates stay */
    static List<RuneData> withoutOne(List<RuneData> runes, RuneData rune) {
        List<RuneData> kept = new ArrayList<>(runes);
        kept.remove(rune);
        return kept;
    }

    private static void setSockets(ItemStack stack, List<RuneData> runes) {
        if (runes.isEmpty()) stack.remove(ModDataComponents.SOCKETS.get());
        else stack.set(ModDataComponents.SOCKETS.get(), new Sockets(runes));
    }

    /** @return the experience level cost to take a socketed rune out in the Rune Altar */
    public static int removalCost() {
        return ArmorPlusConfig.runeRemovalXpCost.get();
    }

    /* ---------------------------------------- Upgrading ---------------------------------------- */

    /** @return true if the ritual can raise the rune's level: it's active, its level matters and it's below {@link #maxLevel(String)} */
    public static boolean canUpgrade(RuneData rune) {
        BuffInstance buff = resolve(rune);
        return buff != null && BuffNames.hasLevels(buff.getBuff()) && parseLevel(rune.entry()) < maxLevel(rune.buffId());
    }

    /** @return the highest level runes of the buff can be upgraded to ("runeOverrides" max=..., else "maxRuneLevel") */
    public static int maxLevel(String buffId) {
        Integer override = RuneOverrides.of(buffId).maxLevel();
        return override != null ? override : ArmorPlusConfig.maxRuneLevel.get();
    }

    /** @return the rune one level higher, see {@link #canUpgrade(RuneData)} */
    public static RuneData upgraded(RuneData rune) {
        return new RuneData(rune.kind(), upgradedEntry(rune.entry()));
    }

    /** @return the entry one level higher, keeping its seconds and requirement */
    static String upgradedEntry(String entry) {
        return entryAtLevel(entry, parseLevel(entry) + 1);
    }

    /** @return the entry at the level, keeping its seconds and requirement */
    private static String entryAtLevel(String entry, int level) {
        String base = BuffParser.withoutRequirement(entry);
        String requirement = entry.substring(base.length());
        String[] parts = base.split("\\|", -1);
        if (parts.length < 2) return base + "|" + level + requirement;
        parts[1] = String.valueOf(level);
        return String.join("|", parts) + requirement;
    }

    /** @return the experience level cost to upgrade the rune, which rises with its level */
    public static int upgradeCost(RuneData rune) {
        return upgradeCost(parseLevel(rune.entry()), ArmorPlusConfig.upgradeBaseCost.get(), ArmorPlusConfig.upgradeCostPerLevel.get());
    }

    /** @return the cost for a rune of the level, at most 39 */
    static int upgradeCost(int level, int base, int perLevel) {
        return Math.min(39, base + perLevel * level);
    }

    /* ---------------------------------------- Upgrading native effects ---------------------------------------- */

    /** @return the material's entry of the buff if this item has it, at its base level; null if it doesn't, or the buff or the material's effects are off */
    private static @Nullable String nativeEntry(ItemStack gear, String buffId) {
        MaterialConfig cfg = materialConfig(gear.getItem());
        if (!enabled() || gearKind(gear) == null || cfg == null || isExtracted(gear, buffId) || RuneOverrides.of(buffId).disabled()) return null;
        RuneKind kind = extractionKind(gear);
        if (!(kind == RuneKind.ARMOR ? cfg.enableArmorEffects() : cfg.enableWeaponEffects()).get()) return null;
        EquipmentSlot slot = armorSlot(gear);
        for (String entry : nativeEntries(cfg, kind)) {
            if (!BuffParser.idOf(entry).equals(buffId)) continue;
            BuffInstance buff = parse(new RuneData(kind, entry));
            if (buff != null && (slot == null || ArmorEffects.shownOn(buff, slot))) return entry;
        }
        return null;
    }

    private static int storedLevel(ItemStack gear, String buffId) {
        Map<String, Integer> levels = gear.get(ModDataComponents.NATIVE_LEVELS.get());
        return levels == null ? 0 : levels.getOrDefault(buffId, 0);
    }

    /** @return the level of the material's buff on this item, 0 if it doesn't have it */
    public static int nativeLevel(ItemStack gear, String buffId) {
        String entry = nativeEntry(gear, buffId);
        return entry == null ? 0 : Math.max(parseLevel(entry), storedLevel(gear, buffId));
    }

    /** @return the entry at this item's level of its buff, a stored level never lowers the material's */
    private static String withNativeLevel(ItemStack gear, String entry) {
        int stored = storedLevel(gear, BuffParser.idOf(entry));
        return stored > parseLevel(entry) ? entryAtLevel(entry, stored) : entry;
    }

    /**
     * @return true if the rune can raise the item's own effect in the ritual: the rune is active, of the kind the item's effects
     * are extracted as and at least the effect's level; the effect has levels and is below {@link #maxLevel(String)}
     */
    public static boolean canUpgradeNative(ItemStack gear, RuneData rune) {
        BuffInstance buff = resolve(rune);
        if (buff == null || !BuffNames.hasLevels(buff.getBuff()) || rune.kind() != extractionKind(gear)) return false;
        int level = nativeLevel(gear, rune.buffId());
        return level > 0 && level < maxLevel(rune.buffId()) && parseLevel(rune.entry()) >= level;
    }

    /** @return a copy of the equipment with its effect one level higher, see {@link #canUpgradeNative(ItemStack, RuneData)} */
    public static ItemStack withUpgradedNative(ItemStack gear, String buffId) {
        ItemStack result = gear.copyWithCount(1);
        Map<String, Integer> levels = new HashMap<>(gear.getOrDefault(ModDataComponents.NATIVE_LEVELS.get(), Map.of()));
        levels.put(buffId, nativeLevel(gear, buffId) + 1);
        result.set(ModDataComponents.NATIVE_LEVELS.get(), Map.copyOf(levels));
        return result;
    }

    /** @return the experience level cost to raise the item's effect, which rises with its level like {@link #upgradeCost(RuneData)} */
    public static int nativeUpgradeCost(ItemStack gear, String buffId) {
        return upgradeCost(nativeLevel(gear, buffId), ArmorPlusConfig.upgradeBaseCost.get(), ArmorPlusConfig.upgradeCostPerLevel.get());
    }

    /** @return the extra experience levels it costs to extract the effect, for every level it was upgraded on this item */
    public static int extractionUpgradeCost(ItemStack gear, String buffId) {
        String entry = nativeEntry(gear, buffId);
        return entry == null ? 0 : ArmorPlusConfig.extractionUpgradeCostPerLevel.get() * (nativeLevel(gear, buffId) - parseLevel(entry));
    }

    /* ---------------------------------------- Buffs ---------------------------------------- */

    /**
     * Worn armor runes per player, rebuilt only when the player's equipment changes or the config reloads
     * ({@link #invalidate(Player)}, {@link #clearCaches()}). Weak keys, so a respawned player (a new entity) starts fresh.
     */
    private static final Map<Player, Collection<BuffInstance>> WORN_RUNES = Collections.synchronizedMap(new WeakHashMap<>());
    /** Tool runes of the tool in the main hand, cached like {@link #WORN_RUNES} (switching items is an equipment change). */
    private static final Map<Player, Collection<BuffInstance>> HELD_TOOL_RUNES = Collections.synchronizedMap(new WeakHashMap<>());

    public static void invalidate(Player player) {
        WORN_RUNES.remove(player);
        HELD_TOOL_RUNES.remove(player);
    }

    public static void clearCaches() {
        WORN_RUNES.clear();
        HELD_TOOL_RUNES.clear();
    }

    /** The active armor runes of the worn armor, one per buff (the highest level wins). Cached, see {@link #WORN_RUNES}. */
    public static Collection<BuffInstance> wornArmorRunes(Player player) {
        if (!enabled()) return List.of();
        return WORN_RUNES.computeIfAbsent(player, Runes::collectWornArmorRunes);
    }

    private static Collection<BuffInstance> collectWornArmorRunes(Player player) {
        Map<String, BuffInstance> best = new LinkedHashMap<>();
        for (EquipmentSlot slot : ARMOR_SLOTS) {
            ItemStack stack = player.getItemBySlot(slot);
            if (gearKind(stack) != RuneKind.ARMOR) continue;
            for (RuneData rune : sockets(stack)) {
                if (rune.kind() != RuneKind.ARMOR) continue;
                BuffInstance instance = resolve(rune);
                if (instance != null) best.merge(rune.buffId(), instance, (a, b) -> b.getAmplifier() > a.getAmplifier() ? b : a);
            }
        }
        return List.copyOf(best.values());
    }

    public static boolean hasArmorRune(Player player, IBuff buff) {
        for (BuffInstance instance : wornArmorRunes(player)) {
            if (instance.getBuff().equals(buff)) return true;
        }
        return false;
    }

    /** The active tool runes of the tool in the player's main hand, one per buff. Cached, see {@link #HELD_TOOL_RUNES}. */
    public static Collection<BuffInstance> heldToolRunes(Player player) {
        if (!enabled()) return List.of();
        return HELD_TOOL_RUNES.computeIfAbsent(player, p -> {
            ItemStack tool = p.getMainHandItem();
            if (gearKind(tool) != RuneKind.TOOL) return List.of();
            Map<String, BuffInstance> best = new LinkedHashMap<>();
            for (RuneData rune : sockets(tool)) {
                if (rune.kind() != RuneKind.TOOL) continue;
                BuffInstance instance = resolve(rune);
                if (instance != null) best.merge(rune.buffId(), instance, (a, b) -> b.getAmplifier() > a.getAmplifier() ? b : a);
            }
            return List.copyOf(best.values());
        });
    }

    /** The active weapon runes socketed into the weapon. */
    public static List<BuffInstance> weaponRunes(ItemStack weapon) {
        if (!enabled() || gearKind(weapon) != RuneKind.WEAPON) return List.of();
        List<BuffInstance> result = new ArrayList<>();
        for (RuneData rune : sockets(weapon)) {
            if (rune.kind() != RuneKind.WEAPON) continue;
            BuffInstance instance = resolve(rune);
            if (instance != null) result.add(instance);
        }
        return result;
    }

    /** The active ranged runes socketed into the bow or crossbow. */
    public static List<RuneData> rangedRunes(ItemStack weapon) {
        if (!enabled() || gearKind(weapon) != RuneKind.RANGED) return List.of();
        return sockets(weapon).stream().filter(rune -> rune.kind() == RuneKind.RANGED && resolve(rune) != null).toList();
    }

    /** The active effects of the bow's material as ranged runes, whether or not the material's effects are enabled. */
    public static List<RuneData> nativeRangedRunes(ItemStack weapon) {
        if (!enabled() || gearKind(weapon) != RuneKind.RANGED) return List.of();
        return nativeEntries(weapon).stream().map(entry -> new RuneData(RuneKind.RANGED, entry)).filter(rune -> resolve(rune) != null).toList();
    }

    /** Everything the bow or crossbow gives its arrows: the enabled effects of its material and its socketed runes, once per buff. */
    public static List<RuneData> rangedEffects(ItemStack weapon) {
        Map<String, RuneData> effects = new LinkedHashMap<>();
        MaterialConfig cfg = materialConfig(weapon.getItem());
        if (cfg != null && cfg.enableWeaponEffects().get()) nativeRangedRunes(weapon).forEach(rune -> effects.put(rune.buffId(), rune));
        rangedRunes(weapon).forEach(rune -> effects.putIfAbsent(rune.buffId(), rune));
        return List.copyOf(effects.values());
    }

    /* ---------------------------------------- Extraction ---------------------------------------- */

    /** @return true if the buff was already extracted from this item, which no longer has it */
    public static boolean isExtracted(ItemStack gear, String buffId) {
        return gear.getOrDefault(ModDataComponents.EXTRACTED_EFFECTS.get(), List.of()).contains(buffId);
    }

    /** Removes the buff from this item (not from others of its kind). */
    public static void markExtracted(ItemStack gear, String buffId) {
        List<String> extracted = new ArrayList<>(gear.getOrDefault(ModDataComponents.EXTRACTED_EFFECTS.get(), List.of()));
        extracted.add(buffId);
        gear.set(ModDataComponents.EXTRACTED_EFFECTS.get(), List.copyOf(extracted));
    }

    /** @return the material's buffs that this item still has, at this item's levels */
    public static List<BuffInstance> nativeBuffs(ItemStack gear, List<BuffInstance> buffs) {
        return buffs.stream().filter(buff -> !isExtracted(gear, BuffParser.idOf(buff.getBuff()))).map(buff -> {
            int stored = storedLevel(gear, BuffParser.idOf(buff.getBuff()));
            return stored > buff.getAmplifier() + 1 ? buff.atLevel(stored) : buff;
        }).toList();
    }

    /** @return the material's buffs that were extracted from this item, which it no longer has */
    public static List<BuffInstance> extractedBuffs(ItemStack gear, List<BuffInstance> buffs) {
        return buffs.stream().filter(buff -> isExtracted(gear, BuffParser.idOf(buff.getBuff()))).toList();
    }

    /** @return the effects of the material that were extracted from this bow or crossbow, which it no longer has */
    public static List<BuffInstance> extractedRangedBuffs(ItemStack weapon) {
        MaterialConfig cfg = materialConfig(weapon.getItem());
        if (!enabled() || cfg == null || gearKind(weapon) != RuneKind.RANGED) return List.of();
        return nativeEntries(cfg, RuneKind.RANGED).stream().filter(entry -> isExtracted(weapon, BuffParser.idOf(entry)))
                .map(entry -> resolve(new RuneData(RuneKind.RANGED, entry))).filter(Objects::nonNull).toList();
    }

    /**
     * @return the durability an extraction takes: a fraction of the maximum that's higher for equipment with few effects
     * (counting the ones already extracted, so the price doesn't change), 0 for unbreakable equipment
     */
    public static int extractionDurabilityCost(ItemStack gear) {
        if (!gear.isDamageableItem()) return 0;
        boolean few = extractableEntries(gear).size() <= ArmorPlusConfig.extractionFewEffects.get();
        double fraction = few ? ArmorPlusConfig.extractionDurabilityCostFewEffects.get() : ArmorPlusConfig.extractionDurabilityCost.get();
        return (int) Math.ceil(gear.getMaxDamage() * fraction);
    }

    /** @return true if the equipment survives losing {@link #extractionDurabilityCost(ItemStack)} */
    public static boolean survivesExtraction(ItemStack gear) {
        return !gear.isDamageableItem() || gear.getDamageValue() + extractionDurabilityCost(gear) < gear.getMaxDamage();
    }

    /** @return the entries that can be extracted from the equipment in the Rune Altar, at this item's levels (including extracted ones, see {@link #isExtracted}), armor only gives its own piece's effects */
    public static List<String> extractableEntries(ItemStack gear) {
        MaterialConfig cfg = materialConfig(gear.getItem());
        if (!enabled() || gearKind(gear) == null || cfg == null) return List.of();
        // ArmorPlus tools give weapon runes of their on-hit effects, bows ranged runes
        RuneKind kind = extractionKind(gear);
        EquipmentSlot slot = armorSlot(gear);
        return extractableEntries(cfg, kind).stream()
                .filter(entry -> slot == null || ArmorEffects.shownOn(parse(new RuneData(kind, entry)), slot))
                .map(entry -> withNativeLevel(gear, entry)).toList();
    }

    private static List<String> extractableEntries(MaterialConfig cfg, RuneKind kind) {
        // armor, weapon and ranged runes come from materials, see extractionKind; bows share the weapon effects and their switch
        if (!(kind == RuneKind.ARMOR ? cfg.enableArmorEffects() : cfg.enableWeaponEffects()).get()) return List.of();
        List<String> result = new ArrayList<>();
        for (String entry : nativeEntries(cfg, kind)) {
            String id = BuffParser.idOf(entry);
            RuneOverrides.Rules rules = RuneOverrides.of(id);
            boolean datapackExtractable = !Boolean.FALSE.equals(datapackRule(new RuneData(kind, extractedEntry(entry)), def -> def.extraction().map(RuneDefinition.Extraction::extractable).orElse(null)));
            if (rules.extractable() && datapackExtractable && !rules.disabled() && parse(new RuneData(kind, entry)) != null) result.add(entry);
        }
        return result;
    }

    /**
     * @return every buff that can currently be obtained as a rune (extracted from any ArmorPlus equipment that isn't hidden
     * in the config, or found in the world), once per buff and kind, at the highest level it's available at
     */
    public static List<RuneData> allObtainableRunes() {
        if (!enabled()) return List.of();
        Map<String, RuneData> runes = new LinkedHashMap<>();
        for (Item item : BuiltInRegistries.ITEM) {
            if (materialConfig(item) == null || !AvailabilityConfig.itemEnabled(item)) continue;
            ItemStack gear = new ItemStack(item);
            RuneKind kind = extractionKind(gear);
            extractableEntries(gear).forEach(entry -> addStrongest(runes, new RuneData(kind, extractedEntry(entry))));
        }
        RuneLootModifier.worldRunes().forEach(rune -> addStrongest(runes, rune));
        return List.copyOf(runes.values());
    }

    /**
     * @return a level I rune of every vanilla effect: beneficial effects as armor and tool runes, harmful ones and Glowing as
     * weapon and ranged runes (armor and tool runes would give them to their user), the other neutral ones (omens) as armor runes
     */
    public static List<RuneData> vanillaEffectRunes() {
        List<RuneData> runes = new ArrayList<>();
        BuiltInRegistries.MOB_EFFECT.listElements().forEach(effect -> {
            Identifier id = effect.key().identifier();
            if (!id.getNamespace().equals(Identifier.DEFAULT_NAMESPACE)) return;
            String entry = id + "|1";
            MobEffect value = effect.value();
            if (value.getCategory() == MobEffectCategory.HARMFUL || value == MobEffects.GLOWING.value()) {
                runes.add(new RuneData(RuneKind.WEAPON, entry));
                runes.add(new RuneData(RuneKind.RANGED, entry));
            } else {
                runes.add(new RuneData(RuneKind.ARMOR, entry));
                if (value.isBeneficial()) runes.add(new RuneData(RuneKind.TOOL, entry));
            }
        });
        runes.removeIf(rune -> resolve(rune) == null);
        return runes;
    }

    /**
     * @return the runes of the creative tab: {@link #allObtainableRunes()} and {@link #vanillaEffectRunes()}, once per buff
     * and kind at the highest level, grouped by kind and sorted by buff id within each
     */
    public static List<RuneData> creativeRunes() {
        Map<String, RuneData> runes = new LinkedHashMap<>();
        allObtainableRunes().forEach(rune -> addStrongest(runes, rune));
        vanillaEffectRunes().forEach(rune -> addStrongest(runes, rune));
        return runes.values().stream()
                .sorted(Comparator.comparing(RuneData::kind).thenComparing(RuneData::buffId))
                .toList();
    }

    private static void addStrongest(Map<String, RuneData> runes, RuneData rune) {
        runes.merge(rune.kind().getSerializedName() + "|" + rune.buffId(), rune,
                (existing, added) -> parseLevel(added.entry()) > parseLevel(existing.entry()) ? added : existing);
    }

    /**
     * @return the item (and count) the Rune Altar consumes in addition to the experience, or EMPTY ("runeOverrides"
     * catalyst=..., else the catalyst of the datapack rune matching the extracted rune)
     */
    public static ItemStack extractionCatalyst(RuneData extracted) {
        RuneOverrides.Rules rules = RuneOverrides.of(extracted.buffId());
        if (rules.catalyst() != null) return rules.catalystStack();
        RuneDefinition.Catalyst catalyst = datapackRule(extracted, def -> def.extraction().flatMap(RuneDefinition.Extraction::catalyst).orElse(null));
        if (catalyst == null) return ItemStack.EMPTY;
        return BuiltInRegistries.ITEM.getOptional(catalyst.item()).map(item -> new ItemStack(item, catalyst.count())).orElse(ItemStack.EMPTY);
    }

    /** @return the item (and count) the ritual consumes from the pedestals to raise this rune a level, or EMPTY, see {@link #upgradeCatalyst(RuneData, int)} */
    public static ItemStack upgradeCatalyst(RuneData rune) {
        return upgradeCatalyst(rune, parseLevel(rune.entry()) + 1);
    }

    /** @return the item (and count) the ritual consumes from the pedestals to upgrade to that level with this rune, or EMPTY ("runeOverrides" upgrade_catalyst@level=..., else upgrade_catalyst=..., else "ritualCatalysts") */
    public static ItemStack upgradeCatalyst(RuneData rune, int level) {
        RuneOverrides.Rules rules = RuneOverrides.of(rune.buffId());
        RitualCatalysts.Entry atLevel = RitualCatalysts.levelEntry(rules.levelCatalysts(), level);
        if (atLevel != null) return atLevel.stack();
        return rules.upgradeCatalyst() != null ? rules.upgradeCatalystStack() : RitualCatalysts.forLevel(level);
    }

    /** @return the item (and count) the ritual consumes from the pedestals to socket this rune, or EMPTY ("runeOverrides" socket_catalyst=..., else "ritualCatalysts") */
    public static ItemStack socketCatalyst(RuneData rune) {
        RuneOverrides.Rules rules = RuneOverrides.of(rune.buffId());
        return rules.socketCatalyst() != null ? rules.socketCatalystStack() : RitualCatalysts.forSocketing();
    }

    /**
     * @return the entry with its level lowered by "extractionLevelReduction" (never below I). Slot requirements ("@head")
     * are kept, so the rune fits the same pieces; piece counts ("@2", ":2") only matter for sets and are dropped
     */
    public static String extractedEntry(String entry) {
        SetRequirement requirement = BuffParser.requirementOf(entry);
        String suffix = requirement != null && requirement.slotBound() ? BuffParser.REQUIREMENT_SEPARATOR + requirement.slotNames() : "";
        String[] parts = BuffParser.withoutRequirement(entry).split("\\|", -1);
        if (parts.length >= 2) {
            parts[1] = String.valueOf(Math.max(1, parseLevel(entry) - ArmorPlusConfig.extractionLevelReduction.get()));
        }
        return String.join("|", parts) + suffix;
    }

    /** @return the experience level cost to extract the rune (its already reduced entry, see {@link #extractedEntry(String)}) */
    public static int extractionCost(RuneData extracted) {
        Integer override = RuneOverrides.of(extracted.buffId()).extractionCost();
        if (override == null) override = datapackRule(extracted, def -> def.extraction().flatMap(RuneDefinition.Extraction::cost).orElse(null));
        if (override != null) return override;
        return ArmorPlusConfig.extractionBaseCost.get() + ArmorPlusConfig.extractionCostPerLevel.get() * parseLevel(extracted.entry());
    }

    public static RuneKind extractionKind(ItemStack gear) {
        if (gear.getItem() instanceof APBowItem) return RuneKind.RANGED;
        return gear.getItem() instanceof APArmorItem ? RuneKind.ARMOR : RuneKind.WEAPON;
    }

    /* ---------------------------------------- Helpers ---------------------------------------- */

    /** @return the level of an entry, 1 if it has none (abilities) */
    public static int parseLevel(String entry) {
        String[] parts = BuffParser.withoutRequirement(entry).split("\\|", -1);
        if (parts.length < 2) return 1;
        try {
            return Math.max(1, Integer.parseInt(parts[1].trim()));
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    private static Set<String> ids(Collection<?> entries) {
        Set<String> result = new HashSet<>();
        for (Object entry : entries) result.add(BuffParser.idOf(entry));
        return result;
    }
}
