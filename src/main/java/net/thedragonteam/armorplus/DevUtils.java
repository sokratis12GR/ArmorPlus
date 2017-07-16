package net.thedragonteam.armorplus;

import static net.thedragonteam.armorplus.ArmorPlus.DEV_ENVIRONMENT;

@SuppressWarnings("ALL")
public class DevUtils {

    private static boolean enableTowerDev = false;
    private static boolean enableDevTool = false;

    public static boolean enableTowerDevEnv() {
        return isDev(enableTowerDev);
    }

    public static boolean enableDevTool() {
        return isDev(enableDevTool);
    }

    private static boolean isDev(boolean type){
        return DEV_ENVIRONMENT && type;
    }
}
