/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.misc;

import net.thedragonteam.thedragonlib.util.LogHelper;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

public class ThreadCosmeticsFetcher extends Thread {

    public ThreadCosmeticsFetcher() {
        this.setName("ArmorPlus" + " cosmetics fetcher");
        this.setDaemon(true);
        this.start();
    }

    @Override
    public void run() {
        LogHelper.info("Fetching cosmetics for people...");
        try {
            URL url = new URL("http://fdn.redstone.tech/TheDragonTeam/armorplus/thedragonteam.properties");
            Properties specialProperties = new Properties();
            specialProperties.load(new InputStreamReader(url.openStream()));
            CosmeticsRenderInit.parse(specialProperties);

            LogHelper.info("Fetching cosmetics for people done!");
        } catch (Exception e) {
            LogHelper.error(ThreadCosmeticsFetcher.class + " Failed", e);
        }
    }
}