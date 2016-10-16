/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.util;

import net.minecraftforge.common.MinecraftForge;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.thedragonlib.TheDragonLib;

import java.io.*;

import static java.lang.String.*;

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
                    new FileOutputStream(format("config/%s/%s.html", ArmorPlus.MODID, ArmorPlus.MODNAME)), "utf-8"));
            writer.write(format("<html><head><title>%s</title></head><body><div style=\"background-color:#1A1A1A\">", ArmorPlus.MODNAME));
            writer.write(format("%s~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</h2>", "<h2 style=\"color:#DEDEDE;text-align:center\">"));
            writer.write(format("%s<u>%s</u></h2>", "<h2 style=\"color:#DEDEDE;text-align:center\">", ArmorPlus.MODNAME));
            writer.write(format("%s<strong>ArmorPlus Version: %s</strong></p>", "<p style=\"color:#DEDEDE;text-align:center\">", ArmorPlus.VERSION));
            writer.write(format("%s<strong>modid: %s</strong></p>", "<p style=\"color:#DEDEDE;text-align:center\">", ArmorPlus.MODID));
            writer.write(format("%s<strong>Minecraft Version: %s</strong></p>", "<p style=\"color:#DEDEDE;text-align:center\">", MinecraftForge.MC_VERSION));
            writer.write(format("%s<strong>dependencies: %s%s ( %s ) </strong></p>", "<p style=\"color:#DEDEDE;text-align:center\">", TheDragonLib.MODID, TheDragonLib.VERSION, TheDragonLib.MODNAME));
            writer.write(format("%s<strong>by <a href=\"https://sokratis12gr.tk\" style=\"color:#AA0000;text-decoration:none\">%s</a></strong></p>", "<p style=\"color:#DEDEDE;text-align:center\">", "sokratis12GR"));
            writer.write(format("%sMod&#39;s Page: <a href=\"https://minecraft.curseforge.com/projects/armorplus\" style=\"color:#AA0000;text-decoration:none\">%s</a></strong></p>\n", "<p style=\"color:#DEDEDE;text-align:center\"><strong>", ArmorPlus.MODNAME));
            writer.write(format("%s~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</h2>", "<h2 style=\"color:#DEDEDE;text-align:center\">"));
            writer.write(format("%s%s%s", "</div>", "</body>", "</html>"));
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
                    new FileOutputStream(format("config/%s/Note.txt", ArmorPlus.MODID)), "utf-8"));
            writer.write(format("[ (%s) | Don't modify or delete the file: %s.html | For More Information Click: %s.html ]", ArmorPlus.MODNAME, ArmorPlus.MODNAME, ArmorPlus.MODNAME));
        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }
    }
}
