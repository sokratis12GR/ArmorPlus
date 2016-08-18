/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.iface;

public interface ContentProvider {

    Iterable<String> getContent(String path);
}