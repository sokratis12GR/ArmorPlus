package net.thedragonteam.armorplus.compat.jei.misc;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class OutputSlot {

    private final int posX;
    private final int posY;

    public OutputSlot(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }
}
