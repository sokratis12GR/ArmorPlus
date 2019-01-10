/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks.special;

import com.sofodev.armorplus.util.Utils;
import net.minecraft.util.ResourceLocation;

public enum Trophy {
    //Compatibility
    ANY("", 0.5f) {
        @Override
        public ResourceLocation getEntityId() {
            return new ResourceLocation("pig");
        }
    },
    //Vanilla
    ELDER_GUARDIAN("elder_guardian", 0.3f) {
        @Override
        public ResourceLocation getEntityId() {
            return new ResourceLocation("elder_guardian");
        }
    },
    WITHER_BOSS("wither_boss", 0.2f) {
        @Override
        public ResourceLocation getEntityId() {
            return new ResourceLocation("wither");
        }
    },
    ENDER_DRAGON("ender_dragon", 0.1f) {
        @Override
        public ResourceLocation getEntityId() {
            return new ResourceLocation("ender_dragon");
        }
    },
    //Armorplus
    SKELETAL_KING("skeletal_king", 0.1f) {
        @Override
        public ResourceLocation getEntityId() {
            return Utils.setRL("skeletal_king");
        }
    },
    GUARDIAN_OVERLORD("guardian_overlord", 0.1f) {
        @Override
        public ResourceLocation getEntityId() {
            return Utils.setRL("overlord_of_the_guardians");
        }
    },
    DEMONIC_DRAGON("demonic_dragon", 0.1f) {
        @Override
        public ResourceLocation getEntityId() {
            return new ResourceLocation("pig");
        }
    },
    THE_LORD_OF_EVERYTHING("the_lord_of_everything", 0.1f) {
        @Override
        public ResourceLocation getEntityId() {
            return new ResourceLocation("pig");
        }
    },
    ;

    private final String name;
    private final float scale;

    Trophy(String name, float scale) {
        this.name = name;
        this.scale = scale;
    }

    public abstract ResourceLocation getEntityId();

    public String getName() {
        return name;
    }

    public float getScale() {
        return scale;
    }
}
