package com.sofodev.armorplus.common.registry.items.spawneggs;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import java.util.function.Function;

public class EntityContainer {

    public Class<? extends Entity> entityClazz;
    public Function<? super World, ? extends Entity> entityFunction;
    public int eggColorSolid;
    public int eggColorSpot;

    public EntityContainer(Class<? extends Entity> EntityClass, Function<? super World, ? extends Entity> func, int solidColorIn, int spotColorIn) {
        this.entityClazz = EntityClass;
        this.entityFunction = func;
        this.eggColorSolid = solidColorIn;
        this.eggColorSpot = spotColorIn;
    }

}