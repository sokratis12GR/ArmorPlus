package net.thedragonteam.armorplus.conditions;

import com.google.gson.JsonObject;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.thedragonteam.armorplus.APConfig;

import java.util.function.BooleanSupplier;

public class ConditionEnabled implements IConditionFactory {

    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json) {
        return () -> APConfig.useJsonRecipes;
    }

}