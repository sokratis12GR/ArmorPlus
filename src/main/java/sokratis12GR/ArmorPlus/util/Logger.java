package sokratis12GR.ArmorPlus.util;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import sokratis12GR.ArmorPlus.ArmorPlus;

import java.io.*;

/**
 * Created by Socrates on 4/23/2016.
 */
public class Logger {

    public static Configuration Logger;

    public static void init(File file) {
        syncConfig();
    }

    public static void syncConfig() {

        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("config" + "/" + ArmorPlus.MODID + "/" + "ArmorPlus.html"), "utf-8"));
            writer.write("<html>" + "<head><title>ArmorPlus</title></head>" + "<body><div style=\"background-color:#1A1A1A\">");
            writer.write("<h2 style=\"color:#DEDEDE;text-align:center\">" + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "</h2>");
            writer.write("<h2 style=\"color:#DEDEDE;text-align:center\"><u>" + ArmorPlus.MODNAME + "</u></h2>");
            writer.write("<p style=\"color:#DEDEDE;text-align:center\"><strong>" + "ArmorPlus Version: " + ArmorPlus.VERSION + "</strong></p>");
            writer.write("<p style=\"color:#DEDEDE;text-align:center\"><strong>" + "modid: " + ArmorPlus.MODID + "</strong></p>");
            writer.write("<p style=\"color:#DEDEDE;text-align:center\"><strong>" + "Minecraft Version: " + MinecraftForge.MC_VERSION + "</strong></p>");
            writer.write("<p style=\"color:#DEDEDE;text-align:center\"><strong>" + "by " + "<a href=\"http://minecraft.curseforge.com/members/sokratis12GR\" style=\"color:#AA0000;text-decoration:none\">sokratis12GR</a></strong></p>");
            writer.write("<p style=\"color:#DEDEDE;text-align:center\"><strong>" + "Mod&#39;s Page: " + "<a href=\"http://minecraft.curseforge.com/projects/armorplus\" style=\"color:#AA0000;text-decoration:none\">ArmorPlus</a></strong></p>\n");
            writer.write("<h2 style=\"color:#DEDEDE;text-align:center\">" + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "</h2>");
            writer.write("</div></body>" + "</html>");
        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("config" + "/" + ArmorPlus.MODID + "/" + "ArmorPlus.txt"), "utf-8"));
            writer.write("[ " + "( " + "ArmorPlus" + " )" + " | " + "Don't Modify Or Delete These Files: " + "ArmorPlus.txt" + ", " + "ArmorPlus.html" + " | " + "For More Information Click: " + "ArmorPlus.html" + " ]");
        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }
    }
}
