package com.sofodev.armorplus.registry.entities.bosses.data;

import net.minecraft.entity.EntityClassification;

import static net.minecraft.entity.EntityClassification.MONSTER;

public class MobType {
    public static final MobType SKELETAL_KING = new MobType("skeletal_king", MONSTER, 2f, 2f, true);
    public static final MobType WITHERLING = new MobType("witherling", MONSTER, 2f, 2f, true);

    public static final MobType GUARDIAN_OVERLORD = new MobType("guardian_overlord", MONSTER);
    public static final MobType DEMONIC_DRAGON = new MobType("demonic_dragon", MONSTER, 2f, 2f, true);

    private String name;
    private EntityClassification classification;
    private float width;
    private float height;
    private boolean isImmuneToFire;
    private int trackingRange;

    MobType(String name, EntityClassification classification) {
        this(name, classification, 10);
    }

    MobType(String name, EntityClassification classification, float width, float height) {
        this(name, classification, width, height, 10);
    }

    MobType(String name, EntityClassification classification, int trackingRange) {
        this(name, classification, 1.0f, 1.0f, trackingRange);
    }

    MobType(String name, EntityClassification classification, float width, float height, int trackingRange) {
        this(name, classification, width, height, trackingRange, false);
    }

    MobType(String name, EntityClassification classification, float width, float height, boolean isImmuneToFire) {
        this(name, classification, width, height, 10, isImmuneToFire);
    }

    MobType(String name, EntityClassification classification, float width, float height, int trackingRange, boolean isImmuneToFire) {
        this.name = name;
        this.classification = classification;
        this.width = width;
        this.height = height;
        this.trackingRange = trackingRange;
        this.isImmuneToFire = isImmuneToFire;
    }

    public String getName() {
        return name;
    }

    public EntityClassification getClassification() {
        return classification;
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

    public int getTrackingRange() {
        return trackingRange;
    }
}