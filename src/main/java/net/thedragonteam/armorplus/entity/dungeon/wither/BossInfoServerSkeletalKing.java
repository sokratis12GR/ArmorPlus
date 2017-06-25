package net.thedragonteam.armorplus.entity.dungeon.wither;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfoServer;

/**
 * ArmorPlus - Kotlin created by sokratis12GR
 * - TheDragonTeam
 */
public class BossInfoServerSkeletalKing extends BossInfoServer {

    //Yes a class just for this.
    public BossInfoServerSkeletalKing(ITextComponent nameIn) {
        super(nameIn, Color.YELLOW, Overlay.NOTCHED_6);
        this.setDarkenSky(true);
        this.setPlayEndBossMusic(true);
    }
}