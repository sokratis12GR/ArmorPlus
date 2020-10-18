package com.sofodev.armorplus.registry.entities.bosses.data;

import net.minecraft.entity.EntityClassification;

import static net.minecraft.entity.EntityClassification.MISC;
import static net.minecraft.entity.EntityClassification.MONSTER;

public class MobType {
    public static final MobType SKELETAL_KING = new MobType("skeletal_king", MONSTER).setImmuneToFire(true);
    public static final MobType WITHERLING = new MobType("witherling", MONSTER).setImmuneToFire(true);
    public static final MobType WITHER_MINION = new MobType("wither_minion", MISC, 0.3125F, 0.3125F).setImmuneToFire(true);

    private String name;
    private EntityClassification classification;
    private boolean hasSize;
    private float width;
    private float height;
    private boolean isImmuneToFire;

    MobType(String name, EntityClassification classification) {
        this(name, classification, false, 0, 0);
    }

    MobType(String name, EntityClassification classification, float width, float height) {
        this(name, classification, true, width, height);
    }

    MobType(String name, EntityClassification classification, boolean hasSize, float width, float height) {
        this.name = name;
        this.classification = classification;
        this.hasSize = hasSize;
        this.width = width;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public EntityClassification getClassification() {
        return classification;
    }

    public boolean hasSize() {
        return hasSize;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public boolean isImmuneToFire() {
        return isImmuneToFire;
    }

    public MobType setImmuneToFire(boolean immuneToFire) {
        isImmuneToFire = immuneToFire;
        return this;
    }
}