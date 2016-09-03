package net.thedragonteam.armorplus.misc;

import net.thedragonteam.core.util.LogHelper;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

public class ThreadSpecialFetcher extends Thread {

    public ThreadSpecialFetcher() {
        this.setName("ArmorPlus" + " Special Fetcher");
        this.setDaemon(true);
        this.start();
    }

    @Override
    public void run() {
        LogHelper.info("Fetching Special People Stuff...");
        try {
            URL url = new URL("http://fdn.redstone.tech/TheDragonTeam/armorplus/thedragonteam.properties");
            Properties specialProperties = new Properties();
            specialProperties.load(new InputStreamReader(url.openStream()));
            SpecialRenderInit.parse(specialProperties);

            LogHelper.info("Fetching Special People Stuff done!");
        } catch (Exception e) {
            LogHelper.error("Fetching Special People Stuff failed! (You can ignore this error technically.)", e);
        }
    }
}