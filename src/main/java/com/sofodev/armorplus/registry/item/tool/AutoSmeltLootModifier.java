package com.sofodev.armorplus.registry.item.tool;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.item.extra.ToolBuff;
import com.sofodev.armorplus.registry.item.tool.properties.tool.APToolMaterial;
import com.sofodev.armorplus.registry.item.tool.properties.tool.Tool;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Smelts the drops of blocks mined with Infused Lava tools and Auto-Smelt tool runes. As a loot modifier the block breaks
 * normally, so Fortune, block experience, stats, hunger and other mods' protections all keep working.
 * <p>
 * A block in the COMMON config's "block|result" smelting recipes always drops the result instead, whatever its own loot
 * is (they can turn the block into something a furnace wouldn't). Any other block's drops go through the furnace recipes.
 */
public class AutoSmeltLootModifier extends LootModifier {

    public static final MapCodec<AutoSmeltLootModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            codecStart(instance).apply(instance, AutoSmeltLootModifier::new));

    public AutoSmeltLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (!ArmorPlusConfig.enableAutoSmelting.get()) return generatedLoot;

        BlockState state = context.getParamOrNull(LootContextParams.BLOCK_STATE);
        if (state == null || !(context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Player player)
                || !(context.getParamOrNull(LootContextParams.TOOL) instanceof ItemStack tool)) return generatedLoot;
        if (!hasAutoSmelt(player, tool) || !tool.isCorrectToolForDrops(state)) return generatedLoot;

        // Silk Touch should preserve the original block instead of smelting it.
        if (enchantmentLevel(tool, Enchantments.SILK_TOUCH) > 0) return generatedLoot;

        // Do not replace the drops of blocks with block entities (inventories, machines, etc.).
        if (context.hasParam(LootContextParams.BLOCK_ENTITY)) return generatedLoot;

        Item configured = configuredResult(BuiltInRegistries.BLOCK.getKey(state.getBlock()));
        if (configured != null) {
            int largestDrop = generatedLoot.stream().mapToInt(ItemStack::getCount).max().orElse(0);
            generatedLoot.clear();
            generatedLoot.add(new ItemStack(configured, configuredCount(enchantmentLevel(tool, Enchantments.FORTUNE), largestDrop)));
            return generatedLoot;
        }

        generatedLoot.replaceAll(stack -> smelted(stack, context.getLevel()));
        return generatedLoot;
    }

    /** @return how many results a configured recipe drops: one, or as many as the block's largest drop with Fortune */
    static int configuredCount(int fortune, int largestDrop) {
        return fortune > 0 ? Math.max(1, largestDrop) : 1;
    }

    private static int enchantmentLevel(ItemStack tool, ResourceKey<Enchantment> enchantment) {
        for (var entry : tool.getEnchantments().entrySet()) {
            if (entry.getKey().is(enchantment)) return entry.getIntValue();
        }
        return 0;
    }

    /** @return the stack's furnace result with the same amount of items, or the stack itself if it can't be smelted */
    private static ItemStack smelted(ItemStack stack, ServerLevel level) {
        SingleRecipeInput input = new SingleRecipeInput(stack);
        return level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, input, level)
                .map(recipe -> recipe.value().assemble(input, level.registryAccess()))
                .filter(result -> !result.isEmpty())
                .map(result -> result.copyWithCount(result.getCount() * stack.getCount()))
                .orElse(stack);
    }

    /**
     * @return true for Infused Lava tools (with their effects enabled), and for any tool with an Auto-Smelt tool rune
     * (armorplus:auto_smelt) that the player hasn't switched off
     */
    private static boolean hasAutoSmelt(Player player, ItemStack tool) {
        if (tool.getItem() instanceof Tool apTool && apTool.getMat() == APToolMaterial.INFUSED_LAVA_MAT
                && apTool.getMat().config().enableWeaponEffects().get()) return true;
        return ToolEffects.has(player, ToolBuff.AUTO_SMELT);
    }

    private static @Nullable Item configuredResult(ResourceLocation blockId) {
        ResourceLocation outputId = findResult(blockId, ArmorPlusConfig.autoSmeltingRecipes.get());
        return outputId != null && BuiltInRegistries.ITEM.containsKey(outputId) ? BuiltInRegistries.ITEM.get(outputId) : null;
    }

    /** Returns the result of the first "block|result" recipe matching the block, or null. */
    static @Nullable ResourceLocation findResult(ResourceLocation blockId, List<? extends String> recipes) {
        for (String recipe : recipes) {
            String[] parts = recipe.split("\\|", -1);
            if (parts.length != 2) continue;
            ResourceLocation input = ResourceLocation.tryParse(parts[0].trim());
            if (blockId.equals(input)) return ResourceLocation.tryParse(parts[1].trim());
        }
        return null;
    }

    @Override
    public MapCodec<? extends LootModifier> codec() {
        return CODEC;
    }
}
