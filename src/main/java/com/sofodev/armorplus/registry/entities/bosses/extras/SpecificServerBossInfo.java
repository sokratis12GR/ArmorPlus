package com.sofodev.armorplus.registry.entities.bosses.extras;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.server.ServerBossInfo;

import static net.minecraft.world.BossInfo.Color.*;
import static net.minecraft.world.BossInfo.Overlay.NOTCHED_12;
import static net.minecraft.world.BossInfo.Overlay.NOTCHED_20;

/**
 * @author Sokratis Fotkatzikis
 */
public class SpecificServerBossInfo extends ServerBossInfo {

    public SpecificServerBossInfo(ITextComponent nameIn, BossInfoDungeonType type) {
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

        private final Color color;
        private final Overlay overlay;
        private final boolean darkenSky;
        private final boolean playEndBossMusic;

        BossInfoDungeonType(Color color, Overlay overlay, boolean darkenSky, boolean playEndBossMusic) {
            this.color = color;
            this.overlay = overlay;
            this.darkenSky = darkenSky;
            this.playEndBossMusic = playEndBossMusic;
        }

        public Color getColor() {
            return this.color;
        }

        public Overlay getOverlay() {
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