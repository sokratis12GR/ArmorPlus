package net.thedragonteam.armorplus.compat.jei.misc;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class UVData {

    private final int u, v;
    private final int widthU, heightV;

    public UVData(int u, int v, int widthU, int heightV) {
        this.u = u;
        this.v = v;
        this.widthU = widthU;
        this.heightV = heightV;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    public int getWidthU() {
        return widthU;
    }

    public int getHeightV() {
        return heightV;
    }
}
