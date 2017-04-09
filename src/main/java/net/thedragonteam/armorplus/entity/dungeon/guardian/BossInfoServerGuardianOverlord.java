package net.thedragonteam.armorplus.entity.dungeon.guardian;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfoServer;

/**
 * ArmorPlus - Kotlin created by sokratis12GR
 * - TheDragonTeam
 */
public class BossInfoServerGuardianOverlord extends BossInfoServer {

    //Yes a class just for this.

    public BossInfoServerGuardianOverlord(ITextComponent nameIn) {
        super(nameIn, Color.BLUE, Overlay.NOTCHED_6);
        this.setDarkenSky(true);
        this.setPlayEndBossMusic(true);
    }
}