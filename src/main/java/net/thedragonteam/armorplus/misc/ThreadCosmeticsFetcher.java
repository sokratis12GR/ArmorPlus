package net.thedragonteam.armorplus.misc;

import net.thedragonteam.thedragonlib.util.LogHelper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class ThreadCosmeticsFetcher extends Thread {

    public ThreadCosmeticsFetcher() {
        this.setName("ArmorPlus cosmetics fetcher");
        this.setDaemon(true);
        this.start();
    }

    @Override
    public void run() {
        LogHelper.info("Fetching cosmetics for people...");
        try {
            URL url = new URL("https://download.nodecdn.net/containers/thedragonteam/armorplus-cosmetics.properties");
            Properties specialProperties = new Properties();
            specialProperties.load(new InputStreamReader(url.openStream()));
            CosmeticsRenderInit.parse(specialProperties);

            LogHelper.info("Fetching cosmetics for people done!");
        } catch (IOException e) {
            LogHelper.error("${ThreadCosmeticsFetcher::class.java} Failed", e);
        }
    }
}