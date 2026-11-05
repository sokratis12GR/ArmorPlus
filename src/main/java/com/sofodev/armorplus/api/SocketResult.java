package com.sofodev.armorplus.api;

/**
 * Whether a rune fits into equipment, see {@link ArmorPlusAPI#canSocket}: {@link #OK} or the first problem found.
 */
public enum SocketResult {
    OK, NOT_SOCKETABLE, WRONG_KIND, WRONG_SLOT, NO_FREE_SOCKET, OWN_BUFF, ALREADY_SOCKETED, INACTIVE
}
