package net.thedragonteam.armorplus;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import org.piwik.java.tracking.CustomVariable;
import org.piwik.java.tracking.PiwikRequest;
import org.piwik.java.tracking.PiwikTracker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.URL;
import java.util.Objects;
import java.util.UUID;

import static net.thedragonteam.armorplus.APConfig.modpackID;

/**
 * Created by Moritz30 on 17.06.2017.
 */
public class Analytics {

    /** public static PiwikTracker tracker = new PiwikTracker("https://analytics.thedragonteam.net/piwik.php");

    public static String moddomain = "armorplus.mcmod";

    public static String userID;

    public static boolean newUser = false;

    public static void registerLaunch() {

        //noinspection MethodCallSideOnly
        if (!Minecraft.getMinecraft().isSnooperEnabled() || APConfig.optOut) {
            System.out.println("asd");
            return;
        }

        try {
            (new File("thedragonteam")).mkdir();
            File userIDFile = new File("thedragonteam/user.id");

            if (userIDFile.exists()) {
                userID = (new BufferedReader(new FileReader(userIDFile))).readLine();
            } else {
                newUser = true;
                userID = UUID.randomUUID().toString();
                userIDFile.createNewFile();
                FileOutputStream oFile = new FileOutputStream(userIDFile, false);
                oFile.write(userID.getBytes());
            }

            PiwikRequest request = new PiwikRequest(1, new URL("https://armorplus.mcmod/launch"));
            request.setUserId(userID);
            setVariable(request, "forge-version", ForgeVersion.getVersion(), 1);
            setVariable(request, "armorplus-version", ArmorPlus.getVersion(), 2);
            setVariable(request, "operating-system", System.getProperty("os.name") + " " + System.getProperty("os.version") + " " + System.getProperty("os.arch"), 3);
            setVariable(request, "minecraft-version", MinecraftForge.MC_VERSION, 4);
            setVariable(request, "java-version", System.getProperty("java.version"), 5);

            if (!Objects.equals(modpackID, "none")) {
                request.setReferrerUrlWithString("https://" + modpackID + ".mcpack");
            }

            for (ModContainer container : Loader.instance().getModList()) {
                if (container.getModId().equalsIgnoreCase("thedragonlib")) {
                    setVariable(request, "thedragonlib-version", container.getVersion(), 6);
                }
            }

            request.setNewVisit(true);

            tracker.sendRequest(request);
            System.out.println("ANA");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void setVariable(PiwikRequest request, String key, String value, int index) {
        request.setUserCustomVariable(key, value);
        request.setVisitCustomVariable(new CustomVariable(key, value), index);
    }

    public static void sendAnalyticsInfo(EntityPlayer player) {
        if (!player.world.isRemote) player.sendMessage(new TextComponentTranslation("armorplus.analytics.info.text"));
    }
     **/

}
