package com.sofodev.armorplus.compat.jei;

import com.sofodev.armorplus.api.SocketResult;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.config.AvailabilityConfig;
import com.sofodev.armorplus.registry.ModDataComponents;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.api.RuneData;
import com.sofodev.armorplus.registry.rune.RuneItem;
import com.sofodev.armorplus.api.RuneKind;
import com.sofodev.armorplus.registry.rune.RitualMenu;
import com.sofodev.armorplus.registry.rune.RuneLootModifier;
import com.sofodev.armorplus.registry.rune.Runes;
import mezz.jei.api.recipe.vanilla.IJeiAnvilRecipe;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * Builds the JEI recipes from the same rules the Rune Altar and its ritual use ({@link Runes}), so they follow the config.
 */
final class RuneRecipeMaker {

    private RuneRecipeMaker() {
    }

    /** One recipe per rune, listing every equipment item it can be extracted from. */
    static List<RuneExtractionRecipe> extractionRecipes(List<ItemStack> gear) {
        Map<RuneData, List<ItemStack>> sources = new LinkedHashMap<>();
        for (ItemStack stack : gear) {
            RuneKind kind = Runes.extractionKind(stack);
            for (String entry : Runes.extractableEntries(stack)) {
                sources.computeIfAbsent(new RuneData(kind, Runes.extractedEntry(entry)), rune -> new ArrayList<>()).add(stack);
            }
        }
        List<RuneExtractionRecipe> recipes = new ArrayList<>();
        sources.forEach((rune, items) -> recipes.add(new RuneExtractionRecipe(id("rune_extraction", rune), List.copyOf(items),
                Runes.extractionCatalyst(rune), RuneItem.create(rune.kind(), rune.entry()), Runes.extractionCost(rune))));
        return recipes;
    }

    /**
     * One ritual recipe per rune (every extractable or world rune) listing the equipment it can be socketed into, one for
     * each upgrade up to the maximum level and one for each upgrade of the equipment's own effects.
     */
    static List<RitualRecipe> ritualRecipes(List<ItemStack> gear, List<RuneExtractionRecipe> extractions) {
        Set<RuneData> runes = new LinkedHashSet<>();
        extractions.forEach(recipe -> runes.add(Runes.runeData(recipe.rune())));
        runes.addAll(RuneLootModifier.worldRunes());

        List<ItemStack> socketable = gear.stream().map(RuneRecipeMaker::withAllSockets).toList();
        List<RitualRecipe> recipes = new ArrayList<>();
        Set<RuneData> upgrades = new HashSet<>();
        Set<RuneData> nativeSteps = new HashSet<>();
        for (RuneData rune : runes) {
            List<ItemStack> inputs = new ArrayList<>();
            List<ItemStack> outputs = new ArrayList<>();
            for (ItemStack stack : socketable) {
                if (Runes.canSocket(stack, rune) != SocketResult.OK) continue;
                inputs.add(stack);
                outputs.add(Runes.withRune(stack, rune));
            }
            if (!inputs.isEmpty()) {
                recipes.add(new RitualRecipe(id("socketing", rune), RitualMenu.Tab.SOCKET, inputs, RuneItem.create(rune.kind(), rune.entry()), Runes.socketCatalyst(rune), outputs, Runes.socketCost(rune)));
            }
            // runes of the same buff share the higher steps, which only get one recipe
            for (RuneData step = rune; Runes.canUpgrade(step) && upgrades.add(step); step = Runes.upgraded(step)) {
                ItemStack stack = RuneItem.create(step.kind(), step.entry());
                RuneData upgraded = Runes.upgraded(step);
                recipes.add(new RitualRecipe(id("upgrading", step), RitualMenu.Tab.UPGRADE_RUNE, List.of(stack), stack, Runes.upgradeCatalyst(step),
                        List.of(RuneItem.create(upgraded.kind(), upgraded.entry())), Runes.upgradeCost(step)));
            }
            for (RuneData step = rune; ; step = Runes.upgraded(step)) {
                if (nativeSteps.add(step)) addNativeUpgrade(recipes, gear, step);
                if (!Runes.canUpgrade(step)) break;
            }
        }
        return recipes;
    }

    /** The Socket Punch stays in the Anvil. JEI asks a real Anvil for the cost, so it comes from {@code RuneEvents}. */
    static List<IJeiAnvilRecipe> socketPunchRecipes(IVanillaRecipeFactory factory, List<ItemStack> gear) {
        List<IJeiAnvilRecipe> recipes = new ArrayList<>();
        List<ItemStack> inputs = new ArrayList<>();
        List<ItemStack> outputs = new ArrayList<>();
        for (ItemStack stack : gear) {
            ItemStack unpunched = stack.copy();
            unpunched.set(ModDataComponents.SOCKET_SLOTS.get(), 0);
            if (!Runes.canAddSocket(unpunched)) continue;
            inputs.add(unpunched);
            outputs.add(Runes.withExtraSocket(unpunched));
        }
        if (!inputs.isEmpty()) {
            recipes.add(factory.createAnvilRecipe(inputs, List.of(new ItemStack(ModItems.SOCKET_PUNCH.get())), outputs,
                    Identifier.fromNamespaceAndPath(MODID, "socket_punch")));
        }
        return recipes;
    }

    /** One ritual recipe listing the equipment whose own effect is at the rune's level, which the rune raises. */
    private static void addNativeUpgrade(List<RitualRecipe> recipes, List<ItemStack> gear, RuneData rune) {
        List<ItemStack> inputs = new ArrayList<>();
        List<ItemStack> outputs = new ArrayList<>();
        for (ItemStack stack : gear) {
            if (!Runes.canUpgradeNative(stack, rune) || Runes.nativeLevel(stack, rune.buffId()) != Runes.parseLevel(rune.entry())) continue;
            inputs.add(stack);
            outputs.add(Runes.withUpgradedNative(stack, rune.buffId()));
        }
        if (!inputs.isEmpty()) {
            recipes.add(new RitualRecipe(id("native_upgrading", rune), RitualMenu.Tab.UPGRADE_EFFECT, inputs, RuneItem.create(rune.kind(), rune.entry()), Runes.upgradeCatalyst(rune),
                    outputs, Runes.nativeUpgradeCost(inputs.getFirst(), rune.buffId())));
        }
    }

    /** @return every enabled item that can hold runes, see {@link Runes#gearKind(ItemStack)} */
    static List<ItemStack> gearItems() {
        List<ItemStack> result = new ArrayList<>();
        for (Item item : BuiltInRegistries.ITEM) {
            ItemStack stack = new ItemStack(item);
            if (AvailabilityConfig.itemEnabled(item) && Runes.gearKind(stack) != null) result.add(stack);
        }
        return result;
    }

    /** With "randomSockets" fresh stacks have no sockets yet, so the shown equipment gets the most it can roll. */
    private static ItemStack withAllSockets(ItemStack gear) {
        if (!ArmorPlusConfig.randomSockets.get()) return gear;
        ItemStack result = gear.copy();
        result.set(ModDataComponents.SOCKET_SLOTS.get(), Runes.maxSockets(Runes.gearKind(gear)));
        return result;
    }

    private static Identifier id(String prefix, RuneData rune) {
        String entry = rune.entry().toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9/._-]", "_");
        return Identifier.fromNamespaceAndPath(MODID, prefix + "/" + rune.kind().getSerializedName() + "/" + entry);
    }
}
