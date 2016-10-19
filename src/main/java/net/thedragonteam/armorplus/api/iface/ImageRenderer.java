/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.api.iface;

public interface ImageRenderer {

    int getWidth();

    int getHeight();

    void render(int mouseX, int mouseY);
}