/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.common;

import net.thedragonteam.armorplus.util.ArmorPlusUtils;

public class ThreadGetData extends Thread {
    public ThreadGetData() {
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        ArmorPlusUtils.updateDonators();
    }
}