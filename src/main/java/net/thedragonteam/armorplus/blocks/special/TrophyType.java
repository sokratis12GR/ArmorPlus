package net.thedragonteam.armorplus.blocks.special;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.thedragonteam.armorplus.util.Utils.setRL;

public enum TrophyType {
    //Compatibility
    ANY("", 0.5f) {
        @Override
        @SideOnly(Side.CLIENT)
        public ResourceLocation getEntityId() {
            return null;
        }
    },
    //Vanilla
    ELDER_GUARDIAN("elder_guardian", .3f) {
        @Override
        @SideOnly(Side.CLIENT)
        public ResourceLocation getEntityId() {
            return new ResourceLocation("elder_guardian");
        }
    },
    WITHER_BOSS("wither_boss", .2f) {
        @Override
        @SideOnly(Side.CLIENT)
        public ResourceLocation getEntityId() {
            return new ResourceLocation("wither");
        }
    },
    ENDER_DRAGON("ender_dragon", .1f) {
        @Override
        @SideOnly(Side.CLIENT)
        public ResourceLocation getEntityId() {
            return new ResourceLocation("ender_dragon");
        }
    },
    //Armorplus
    SKELETAL_KING("skeletal_king", .1f) {
        @Override
        @SideOnly(Side.CLIENT)
        public ResourceLocation getEntityId() {
            return setRL("skeletal_king");
        }
    },
    GUARDIAN_OVERLORD("guardian_overlord", .5f) {
        @Override
        @SideOnly(Side.CLIENT)
        public ResourceLocation getEntityId() {
            return setRL("guardian_overlord");
        }
    },
    DEMONIC_DRAGON("demonic_dragon", 0.1f) {
        @Override
        public ResourceLocation getEntityId() {
            return null;
        }
    },
    THE_LORD_OF_EVERYTHING("the_lord_of_everything", 0.1f) {
        @Override
        public ResourceLocation getEntityId() {
            return null;
        }
    },;

    private final String name;
    private final float scale;
    private final EnumParticleTypes particle;

    TrophyType(String name, float scale, EnumParticleTypes particle) {
        this.name = name;
        this.scale = scale;
        this.particle = particle;
    }

    TrophyType(String name, float scale) {
        this(name, scale, EnumParticleTypes.FLAME);
    }

    public abstract ResourceLocation getEntityId();

    public String getName() {
        return name;
    }

    public float getScale() {
        return scale;
    }

    public EnumParticleTypes getParticle() {
        return particle;
    }
}
