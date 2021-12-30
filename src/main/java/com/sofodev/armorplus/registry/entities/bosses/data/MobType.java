package com.sofodev.armorplus.registry.entities.bosses.data;

import net.minecraft.world.entity.MobCategory;

import static net.minecraft.world.entity.MobCategory.CREATURE;
import static net.minecraft.world.entity.MobCategory.MONSTER;

public class MobType {
    public static final MobType SKELETAL_KING = new MobType("skeletal_king", MONSTER, 2f, 2f, true);
    public static final MobType WITHERLING = new MobType("witherling", MONSTER, 2f, 2f, true);

    public static final MobType GUARDIAN_OVERLORD = new MobType("guardian_overlord", MONSTER);
    public static final MobType DEMONIC_DRAGON = new MobType("demonic_dragon", MONSTER, 2f, 2f, true);

    public static final MobType FROST_WOLF = new MobType("frost_wolf", CREATURE, 1f, 1f, false);
    public static final MobType FROST_WOLF_ALPHA = new MobType("frost_wolf_alpha", CREATURE, 1f, 1f, false);
    public static final MobType BOREAS = new MobType("boreas", MONSTER, 1f, 3f, false);

    private String name;
    private MobCategory classification;
    private float width;
    private float height;
    private boolean isImmuneToFire;
    private int trackingRange;

    MobType(String name, MobCategory classification) {
        this(name, classification, 10);
    }

    MobType(String name, MobCategory classification, float width, float height) {
        this(name, classification, width, height, 10);
    }

    MobType(String name, MobCategory classification, int trackingRange) {
        this(name, classification, 1.0f, 1.0f, trackingRange);
    }

    MobType(String name, MobCategory classification, float width, float height, int trackingRange) {
        this(name, classification, width, height, trackingRange, false);
    }

    MobType(String name, MobCategory classification, float width, float height, boolean isImmuneToFire) {
        this(name, classification, width, height, 10, isImmuneToFire);
    }

    MobType(String name, MobCategory classification, float width, float height, int trackingRange, boolean isImmuneToFire) {
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

    public MobCategory getClassification() {
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