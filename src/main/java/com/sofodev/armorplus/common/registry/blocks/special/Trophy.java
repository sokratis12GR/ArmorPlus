/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.blocks.special;

import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.util.ResourceLocation;

public enum Trophy {
    //Compatibility
    ANY(0.5f) {
        @Override
        public ResourceLocation getEntityId() {
            return new ResourceLocation("pig");
        }
    },
    //Vanilla Mobs
    PIG, SHEEP, COW, CHICKEN, HORSE, RABBIT, BAT, OCELOT, PARROT, POLAR_BEAR, WOLF,
    CREEPER, BLAZE, SPIDER, CAVE_SPIDER, GUARDIAN, ZOMBIE, ENDERMAN, ENDERMITE, STRAY,
    EVOKER {
        @Override
        public ResourceLocation getEntityId() {
            return new ResourceLocation("evocation_illager");
        }
    }, VEX, VINDICATOR {
        @Override
        public ResourceLocation getEntityId() {
            return new ResourceLocation("vindication_illager");
        }
    }, GHAST(0.1f), HUSK, SHULKER, MAGMA_CUBE, SILVERFISH, SKELETON,
    WITCH, ZOMBIE_VILLAGER, ZOMBIE_PIGMAN, WITHER_SKELETON, SLIME,
    //Vanilla Bosses
    ELDER_GUARDIAN(0.3f),
    WITHER_BOSS(0.2f) {
        @Override
        public ResourceLocation getEntityId() {
            return new ResourceLocation("wither");
        }
    },
    ENDER_DRAGON(0.1f),
    //Armorplus
    SKELETAL_KING(0.1f) {
        @Override
        public ResourceLocation getEntityId() {
            return Utils.setRL("skeletal_king");
        }
    },
    GUARDIAN_OVERLORD(0.1f) {
        @Override
        public ResourceLocation getEntityId() {
            return Utils.setRL("overlord_of_the_guardians");
        }
    },
    DEMONIC_DRAGON(0.1f) {
        @Override
        public ResourceLocation getEntityId() {
            return Utils.setRL("demonic_dragon");
        }
    },
    THE_LORD_OF_EVERYTHING(0.1f),
    ;

    private final float scale;

    Trophy() {
        this.scale = 0.5f;
    }

    Trophy(float scale) {
        this.scale = scale;
    }

    public ResourceLocation getEntityId() {
        return new ResourceLocation(getName());
    }

    public String getName() {
        String name = this.name().toLowerCase();
        return name.equals("any") ? "" : name;
    }

    public float getScale() {
        return scale;
    }
}
