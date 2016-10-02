/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.util;

import net.minecraftforge.common.MinecraftForge;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.core.TheDragonCore;

import java.io.*;

/**
 * net.thedragonteam.armorplus.util
 * Created by sokratis12GR on 4/23/2016.
 * - TheDragonTeam
 */
public class Logger {

    public static void init(File file) {
        syncConfig();
    }

    private static void syncConfig() {

        Writer writer = null;

        //ArmorPlus.html
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("config" + "/" + ArmorPlus.MODID + "/" + "ArmorPlus.html"), "utf-8"));
            writer.write("<html>" + "<head><title>ArmorPlus</title></head>" + "<body><div style=\"background-color:#1A1A1A\">");
            writer.write("<h2 style=\"color:#DEDEDE;text-align:center\">" + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "</h2>");
            writer.write("<h2 style=\"color:#DEDEDE;text-align:center\"><u>" + ArmorPlus.MODNAME + "</u></h2>");
            writer.write("<p style=\"color:#DEDEDE;text-align:center\"><strong>" + "ArmorPlus Version: " + ArmorPlus.VERSION + "</strong></p>");
            writer.write("<p style=\"color:#DEDEDE;text-align:center\"><strong>" + "modid: " + ArmorPlus.MODID + "</strong></p>");
            writer.write("<p style=\"color:#DEDEDE;text-align:center\"><strong>" + "Minecraft Version: " + MinecraftForge.MC_VERSION + "</strong></p>");
            writer.write("<p style=\"color:#DEDEDE;text-align:center\"><strong>" + "dependencies: " + TheDragonCore.MODID + TheDragonCore.VERSION + " ( " + TheDragonCore.MODNAME + " ) " + "</strong></p>");
            writer.write("<p style=\"color:#DEDEDE;text-align:center\"><strong>" + "by " + "<a href=\"https://minecraft.curseforge.com/members/TheDragonTeam\" style=\"color:#AA0000;text-decoration:none\">TheDragonTeam</a></strong></p>");
            writer.write("<p style=\"color:#DEDEDE;text-align:center\"><strong>" + "Mod&#39;s Page: " + "<a href=\"https://minecraft.curseforge.com/projects/armorplus\" style=\"color:#AA0000;text-decoration:none\">ArmorPlus</a></strong></p>\n");
            writer.write("<h2 style=\"color:#DEDEDE;text-align:center\">" + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "</h2>");
            writer.write("</div></body>" + "</html>");
        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }
        //Note.txt
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("config" + "/" + ArmorPlus.MODID + "/" + "Note.txt"), "utf-8"));
            writer.write("[ " + "( " + "ArmorPlus" + " )" + " | " + "Don't modify or delete the file: " + "ArmorPlus.html" + " | " + "For More Information Click: " + "ArmorPlus.html" + " ]");
        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }
    }
}
