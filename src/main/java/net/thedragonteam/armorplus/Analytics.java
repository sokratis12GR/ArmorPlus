package net.thedragonteam.armorplus;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModClassLoader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.piwik.java.tracking.CustomVariable;
import org.piwik.java.tracking.PiwikRequest;
import org.piwik.java.tracking.PiwikTracker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.URL;
import java.util.UUID;

/**
 * Created by Moritz30 on 17.06.2017.
 */
public class Analytics {

    public static PiwikTracker tracker = new PiwikTracker("https://analytics.thedragonteam.net/piwik.php");

    public static String moddomain = "armorplus.mcmod";

    public static String userID;

    public static void registerLaunch() {

        try {
            (new File("thedragonteam")).mkdir();
            File userIDFile = new File("thedragonteam/user.id");

            if (userIDFile.exists()) {
                userID = (new BufferedReader(new FileReader(userIDFile))).readLine();
            } else {
                userID = UUID.randomUUID().toString();
                userIDFile.createNewFile();
                FileOutputStream oFile = new FileOutputStream(userIDFile, false);
                oFile.write(userID.getBytes());
            }

            PiwikRequest request = new PiwikRequest(1, new URL("https://armorplus.mcmod/launch"));
            request.setUserId(userID);
            request.setUserCustomVariable("forge-version", ForgeVersion.getVersion());
            request.setUserCustomVariable("armorplus-version", ArmorPlus.getVersion());
            request.setUserCustomVariable("operating-system", System.getProperty("os.name") + " " + System.getProperty("os.version") + " " + System.getProperty("os.arch"));
            request.setUserCustomVariable("minecraft-version", MinecraftForge.MC_VERSION);
            request.setUserCustomVariable("java-version", System.getProperty("java.version"));
            request.setVisitCustomVariable(new CustomVariable("forge-version", ForgeVersion.getVersion()), 1);
            request.setVisitCustomVariable(new CustomVariable("armorplus-version", ArmorPlus.getVersion()), 2);
            request.setVisitCustomVariable(new CustomVariable("operating-system", System.getProperty("os.name") + " " + System.getProperty("os.version") + " " + System.getProperty("os.arch")), 3);
            request.setVisitCustomVariable(new CustomVariable("minecraft-version", MinecraftForge.MC_VERSION), 4);
            request.setVisitCustomVariable(new CustomVariable("java-version", System.getProperty("java.version")), 5);

            for (ModContainer container : Loader.instance().getModList()) {
                if(container.getModId().equalsIgnoreCase("thedragonlib")) {
                    request.setUserCustomVariable("thedragonlib-version", container.getVersion());
                    request.setVisitCustomVariable(new CustomVariable("thedragonlib-version", container.getVersion()), 6);
                }
            }

            request.setNewVisit(true);

            tracker.sendRequest(request);
            System.out.println("ANA");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
