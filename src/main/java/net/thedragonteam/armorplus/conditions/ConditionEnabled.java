package net.thedragonteam.armorplus.conditions;

import com.google.gson.JsonObject;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;

import java.util.function.BooleanSupplier;

import static net.thedragonteam.armorplus.ModConfig.MainConfig.global;

public class ConditionEnabled implements IConditionFactory {

    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json) {
        return () -> global.useJsonRecipes;
    }

}