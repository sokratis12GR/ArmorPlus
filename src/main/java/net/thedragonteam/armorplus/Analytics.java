package net.thedragonteam.armorplus;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModClassLoader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
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

    public static boolean newUser = false;

    public static void registerLaunch() {

        if (!Minecraft.getMinecraft().isSnooperEnabled()) {
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

            for (ModContainer container : Loader.instance().getModList()) {
                if (container.getModId().equalsIgnoreCase("thedragonlib")) {
                    setVariable(request,"thedragonlib-version", container.getVersion(), 6);
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
    
    public static void sendAnalyticsInfo (PlayerEvent.PlayerLoggedInEvent e) {
        e.player.sendMessage(new TextComponentString("ArmorPlus sends anonymous analytics data to it's creator TheDragonTeam. To opt out please disable the Minecraft Snooper setting."));
    }
}
