package net.thedragonteam.armorplus;

import static net.thedragonteam.armorplus.ArmorPlus.DEV_ENVIRONMENT;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
@SuppressWarnings("ConstantConditions")
public class DevUtils {

    private static final boolean ENABLE_TOWER_DEV = false;
    private static final boolean ENABLE_DEV_TOOL = false;

    public static boolean enableTowerDevEnv() {
        return isDev(ENABLE_TOWER_DEV);
    }

    public static boolean enableDevTool() {
        return isDev(ENABLE_DEV_TOOL);
    }

    private static boolean isDev(boolean type){
        return DEV_ENVIRONMENT && type;
    }
}
