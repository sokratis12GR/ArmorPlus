package net.thedragonteam.armorplus.util;

import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.ArmorPlus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Utilities used by ArmorPlus. All miscellaneous methods are located here.
 *
 * @author AidanBrady
 */
public final class ArmorPlusUtils {

    /**
     * Updates the donator list by retrieving the most recent information from a foreign document.
     */
    public static void updateDonators() {
        ArmorPlus.donators.clear();

        for (String s : getHTML("http://capes.sokratis12gr.tk/donators.txt")) {
            ArmorPlus.donators.add(s);
        }
    }

    /**
     * Returns one line of HTML from the url.
     *
     * @param urlToRead - URL to read from.
     * @return HTML text from the url.
     */
    public static List<String> getHTML(String urlToRead) {
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        List<String> result = new ArrayList<String>();

        try {
            url = new URL(urlToRead);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line = rd.readLine()) != null) {
                result.add(line.trim());
            }

            rd.close();
        } catch (Exception e) {
            result.clear();
            result.add("null");
            ArmorPlus.logger.error("An error occured while connecting to URL '" + urlToRead + ".'");
        }

        return result;
    }

    /**
     * Checks if Minecraft is running in offline mode.
     *
     * @return if mod is running in offline mode.
     */
    public static boolean isOffline() {
        try {
            new URL("http://www.apple.com").openConnection().connect();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Copies an ItemStack and returns it with a defined stackSize.
     *
     * @param itemstack - stack to change size
     * @param size      - size to change to
     * @return resized ItemStack
     */
    public static ItemStack size(ItemStack itemstack, int size) {
        ItemStack newStack = itemstack.copy();
        newStack.stackSize = size;
        return newStack;
    }
}