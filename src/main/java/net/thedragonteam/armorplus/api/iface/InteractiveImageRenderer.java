/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.iface;

public interface InteractiveImageRenderer extends ImageRenderer {

    String getTooltip(String tooltip);

    boolean onMouseClick(int mouseX, int mouseY);
}