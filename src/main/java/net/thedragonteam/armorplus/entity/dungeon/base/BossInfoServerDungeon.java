package net.thedragonteam.armorplus.entity.dungeon.base;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfoServer;

import static net.minecraft.world.BossInfo.Color.*;
import static net.minecraft.world.BossInfo.Overlay.NOTCHED_20;
import static net.minecraft.world.BossInfo.Overlay.NOTCHED_6;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class BossInfoServerDungeon extends BossInfoServer {

    public BossInfoServerDungeon(ITextComponent nameIn, BossInfoDungeonType type) {
        super(nameIn, type.getColor(), type.getOverlay());
        this.setDarkenSky(type.isDarkenSky());
        this.setPlayEndBossMusic(type.canPlayEndBossMusic());
    }

    public enum BossInfoDungeonType {
        SKELETAL_KING(WHITE, NOTCHED_6, true, false),
        GUARDIAN_OVERLORD(BLUE, NOTCHED_6, false, false),
        DEMONIC_DRAGON(PURPLE, NOTCHED_6, true, false),
        THE_MEANING_OF_EVERYTHING(GREEN, NOTCHED_20, true, true),;

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