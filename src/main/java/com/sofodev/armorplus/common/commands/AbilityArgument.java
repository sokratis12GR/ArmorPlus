package com.sofodev.armorplus.common.commands;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.sofodev.armorplus.common.caps.abilities.ImplementedAbilities;
import com.sofodev.armorplus.common.caps.abilities.data.AbilityData;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class AbilityArgument implements ArgumentType<ResourceLocation> {
    private static final Collection<String> EXAMPLES = Arrays.asList("minecraft:fire_resistance", "minecraft:haste");
    public static final DynamicCommandExceptionType ABILITY_UNKNOWN_TYPE = new DynamicCommandExceptionType(ctx
        -> new TextComponentTranslation("error.armorplus.ability.not_found", ctx));

    public static ResourceLocation getAbilityID(CommandContext<CommandSource> context, String name) throws CommandSyntaxException {
        return checkIfAbilityExists(context.getArgument(name, ResourceLocation.class));
    }

    private static ResourceLocation checkIfAbilityExists(ResourceLocation id) throws CommandSyntaxException {
        boolean exists = ImplementedAbilities.ARMOR_ABILITY_REGISTRY.containsKey(id);
        if (exists) {
            return id;
        } else {
            throw ABILITY_UNKNOWN_TYPE.create(id);
        }
    }

    public ResourceLocation parse(StringReader reader) throws CommandSyntaxException {
        return checkIfAbilityExists(ResourceLocation.read(reader));
    }

    public static AbilityArgument ability() {
        return new AbilityArgument();
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> ctx, SuggestionsBuilder builder) {
        return ISuggestionProvider.suggestIterable(ImplementedAbilities.ARMOR_ABILITY_REGISTRY.getKeys(), builder);
    }

    @Override
    public Collection<String> getExamples() {
        return EXAMPLES;
    }
}