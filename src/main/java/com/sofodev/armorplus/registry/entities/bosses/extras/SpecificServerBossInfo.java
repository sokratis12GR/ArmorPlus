package com.sofodev.armorplus.registry.entities.bosses.extras;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;

import static net.minecraft.world.BossEvent.BossBarColor.*;
import static net.minecraft.world.BossEvent.BossBarOverlay.NOTCHED_12;
import static net.minecraft.world.BossEvent.BossBarOverlay.NOTCHED_20;

/**
 * @author Sokratis Fotkatzikis
 */
public class SpecificServerBossInfo extends ServerBossEvent {

    public SpecificServerBossInfo(Component nameIn, BossInfoDungeonType type) {
        super(nameIn, type.getColor(), type.getOverlay());
        this.setDarkenScreen(type.isDarkenSky());
        this.setPlayBossMusic(type.canPlayEndBossMusic());
    }

    public enum BossInfoDungeonType {
        SKELETAL_KING(WHITE, NOTCHED_12, true, false),
        GUARDIAN_OVERLORD(BLUE, NOTCHED_12, false, false),
        DEMONIC_DRAGON(PURPLE, NOTCHED_12, true, false),
        THE_MEANING_OF_EVERYTHING(GREEN, NOTCHED_20, true, true),
        ;

        private final BossBarColor color;
        private final BossBarOverlay overlay;
        private final boolean darkenSky;
        private final boolean playEndBossMusic;

        BossInfoDungeonType(BossBarColor color, BossBarOverlay overlay, boolean darkenSky, boolean playEndBossMusic) {
            this.color = color;
            this.overlay = overlay;
            this.darkenSky = darkenSky;
            this.playEndBossMusic = playEndBossMusic;
        }

        public BossBarColor getColor() {
            return this.color;
        }

        public BossBarOverlay getOverlay() {
            return this.overlay;
        }

        public boolean isDarkenSky() {
            return this.darkenSky;
        }

        public boolean canPlayEndBossMusic() {
            return this.playEndBossMusic;
        }
    }
}