/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.util;

import net.minecraftforge.common.MinecraftForge;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;
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
            writer.write("[ " + "( " + "ArmorPlus" + " )" + " | " + "Don't Modify Or Delete These Files: " + "ArmorPlus.txt" + ", " + "ArmorPlus.html" + " | " + "For More Information Click: " + "ArmorPlus.html" + " ]");
        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }
        //ArmorPlusItem.txt
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("config" + "/" + ArmorPlus.MODID + "/" + "ArmorPlusItems.txt"), "utf-8"));
            writer.write("1. Coal Armor: " + ModItems.COAL_HELMET.getRegistryName() + " , " + ModItems.COAL_CHESTPLATE.getRegistryName() + " , " + ModItems.COAL_LEGGINGS.getRegistryName() + " , " + ModItems.COAL_BOOTS.getRegistryName());
            writer.write("\n\n2. Lapis Armor: " + ModItems.LAPIS_HELMET.getRegistryName() + " , " + ModItems.LAPIS_CHESTPLATE.getRegistryName() + " , " + ModItems.LAPIS_LEGGINGS.getRegistryName() + " , " + ModItems.LAPIS_BOOTS.getRegistryName());
            writer.write("\n\n3. Redstone Armor: " + ModItems.REDSTONE_HELMET.getRegistryName() + " , " + ModItems.REDSTONE_CHESTPLATE.getRegistryName() + " , " + ModItems.REDSTONE_LEGGINGS.getRegistryName() + " , " + ModItems.REDSTONE_BOOTS.getRegistryName());
            writer.write("\n\n4. Emerald Armor: " + ModItems.EMERALD_HELMET.getRegistryName() + " , " + ModItems.EMERALD_CHESTPLATE.getRegistryName() + " , " + ModItems.EMERALD_LEGGINGS.getRegistryName() + " , " + ModItems.EMERALD_BOOTS.getRegistryName());
            writer.write("\n\n5. Obsidian Armor: " + ModItems.OBSIDIAN_HELMET.getRegistryName() + " , " + ModItems.OBSIDIAN_CHESTPLATE.getRegistryName() + " , " + ModItems.OBSIDIAN_LEGGINGS.getRegistryName() + " , " + ModItems.OBSIDIAN_BOOTS.getRegistryName());
            writer.write("\n\n6. Lava Armor: " + ModItems.LAVA_HELMET.getRegistryName() + " , " + ModItems.LAVA_CHESTPLATE.getRegistryName() + " , " + ModItems.LAVA_LEGGINGS.getRegistryName() + " , " + ModItems.LAVA_BOOTS.getRegistryName());
            writer.write("\n\n7. Guardian Armor: " + ModItems.GUARDIAN_HELMET.getRegistryName() + " , " + ModItems.GUARDIAN_CHESTPLATE.getRegistryName() + " , " + ModItems.GUARDIAN_LEGGINGS.getRegistryName() + " , " + ModItems.GUARDIAN_BOOTS.getRegistryName());
            writer.write("\n\n8. Super Star Armor: " + ModItems.SUPER_STAR_HELMET.getRegistryName() + " , " + ModItems.SUPER_STAR_CHESTPLATE.getRegistryName() + " , " + ModItems.SUPER_STAR_LEGGINGS.getRegistryName() + " , " + ModItems.SUPER_STAR_BOOTS.getRegistryName());
            writer.write("\n\n9. Ender Dragon Armor: " + ModItems.ENDER_DRAGON_HELMET.getRegistryName() + " , " + ModItems.ENDER_DRAGON_CHESTPLATE.getRegistryName() + " , " + ModItems.ENDER_DRAGON_LEGGINGS.getRegistryName() + " , " + ModItems.ENDER_DRAGON_BOOTS.getRegistryName());
            writer.write("\n\n10. The Ultimate Armor: " + ModItems.THE_ULTIMATE_HELMET.getRegistryName() + " , " + ModItems.THE_ULTIMATE_CHESTPLATE.getRegistryName() + " , " + ModItems.THE_ULTIMATE_LEGGINGS.getRegistryName() + " , " + ModItems.THE_ULTIMATE_BOOTS.getRegistryName());
            writer.write("\n\n11. Cobalt Armor: " + ModItems.COBALT_HELMET.getRegistryName() + " , " + ModItems.COBALT_CHESTPLATE.getRegistryName() + " , " + ModItems.COBALT_LEGGINGS.getRegistryName() + " , " + ModItems.COBALT_BOOTS.getRegistryName());
            writer.write("\n\n12. Ardite  Armor: " + ModItems.ARDITE_HELMET.getRegistryName() + " , " + ModItems.ARDITE_CHESTPLATE.getRegistryName() + " , " + ModItems.ARDITE_LEGGINGS.getRegistryName() + " , " + ModItems.ARDITE_BOOTS.getRegistryName());
            writer.write("\n\n13. Manyullyn Armor: " + ModItems.MANYULLYN_HELMET.getRegistryName() + " , " + ModItems.MANYULLYN_CHESTPLATE.getRegistryName() + " , " + ModItems.MANYULLYN_LEGGINGS.getRegistryName() + " , " + ModItems.MANYULLYN_BOOTS.getRegistryName());
            writer.write("\n\n14. Pig Iron Armor: " + ModItems.PIG_IRON_HELMET.getRegistryName() + " , " + ModItems.PIG_IRON_CHESTPLATE.getRegistryName() + " , " + ModItems.PIG_IRON_LEGGINGS.getRegistryName() + " , " + ModItems.PIG_IRON_BOOTS.getRegistryName());
            writer.write("\n\n15. Knight Slime Armor: " + ModItems.KNIGHT_SLIME_HELMET.getRegistryName() + " , " + ModItems.KNIGHT_SLIME_CHESTPLATE.getRegistryName() + " , " + ModItems.KNIGHT_SLIME_LEGGINGS.getRegistryName() + " , " + ModItems.KNIGHT_SLIME_BOOTS.getRegistryName());
            writer.write("\n\n16. Chicken Armor: " + ModItems.CHICKEN_HELMET.getRegistryName() + " , " + ModItems.CHICKEN_CHESTPLATE.getRegistryName() + " , " + ModItems.CHICKEN_LEGGINGS.getRegistryName() + " , " + ModItems.CHICKEN_BOOTS.getRegistryName());
            writer.write("\n\n17. Slime Armor: " + ModItems.SLIME_HELMET.getRegistryName() + " , " + ModItems.SLIME_CHESTPLATE.getRegistryName() + " , " + ModItems.SLIME_LEGGINGS.getRegistryName() + " , " + ModItems.SLIME_BOOTS.getRegistryName());
            writer.write("\n\n18. Steel Armor: " + ModItems.STEEL_HELMET.getRegistryName() + " , " + ModItems.STEEL_CHESTPLATE.getRegistryName() + " , " + ModItems.STEEL_LEGGINGS.getRegistryName() + " , " + ModItems.STEEL_BOOTS.getRegistryName());
            writer.write("\n\n19. Electrical Armor: " + ModItems.ELECTRICAL_HELMET.getRegistryName() + " , " + ModItems.ELECTRICAL_CHESTPLATE.getRegistryName() + " , " + ModItems.ELECTRICAL_LEGGINGS.getRegistryName() + " , " + ModItems.ELECTRICAL_BOOTS.getRegistryName());
            writer.write("\n\n20. Dev Armor: " + ModItems.DEV_HELMET.getRegistryName() + " , " + ModItems.DEV_CHESTPLATE.getRegistryName() + " , " + ModItems.DEV_LEGGINGS.getRegistryName() + " , " + ModItems.DEV_BOOTS.getRegistryName());
            writer.write("\n\n21. Coal Weapons: " + ModItems.COAL_SWORD.getRegistryName() + " , " + ModItems.COAL_BATTLE_AXE.getRegistryName() + " , " + ModItems.COAL_BOW.getRegistryName());
            writer.write("\n\n22. Lapis Weapons: " + ModItems.LAPIS_SWORD.getRegistryName() + " , " + ModItems.LAPIS_BATTLE_AXE.getRegistryName() + " , " + ModItems.LAPIS_BOW.getRegistryName());
            writer.write("\n\n23. Redstone Weapons: " + ModItems.REDSTONE_SWORD.getRegistryName() + " , " + ModItems.REDSTONE_BATTLE_AXE.getRegistryName() + " , " + ModItems.REDSTONE_BOW.getRegistryName());
            writer.write("\n\n24. Emerald Weapons: " + ModItems.EMERALD_SWORD.getRegistryName() + " , " + ModItems.EMERALD_BATTLE_AXE.getRegistryName() + " , " + ModItems.EMERALD_BOW.getRegistryName());
            writer.write("\n\n25. Obsidian Weapons: " + ModItems.OBSIDIAN_SWORD.getRegistryName() + " , " + ModItems.OBSIDIAN_BATTLE_AXE.getRegistryName() + " , " + ModItems.OBSIDIAN_BOW.getRegistryName());
            writer.write("\n\n26. Lava Weapons: " + ModItems.LAVA_SWORD.getRegistryName() + " , " + ModItems.LAVA_BATTLE_AXE.getRegistryName() + " , " + ModItems.LAVA_BOW.getRegistryName());
            writer.write("\n\n27. Super Star Weapons: " + ModItems.SUPER_STAR_SWORD.getRegistryName() + " , " + ModItems.SUPER_STAR_BATTLE_AXE.getRegistryName() + " , " + ModItems.SUPER_STAR_BOW.getRegistryName());
            writer.write("\n\n28. Guardian Weapons: " + ModItems.GUARDIAN_SWORD.getRegistryName() + " , " + ModItems.GUARDIAN_BATTLE_AXE.getRegistryName() + " , " + ModItems.GUARDIAN_BOW.getRegistryName());
            writer.write("\n\n29. Ender Dragon Weapons: " + ModItems.ENDER_DRAGON_SWORD.getRegistryName() + " , " + ModItems.ENDER_DRAGON_BATTLE_AXE.getRegistryName() + " , " + ModItems.ENDER_DRAGON_BOW.getRegistryName());
            writer.write("\n\nItems: " + ModItems.CHAINMAIL.getRegistryName() + " , " + ModItems.ENDER_DRAGON_SCALE.getRegistryName() + " , " + ModItems.GUARDIAN_SCALE.getRegistryName() + " , "
                    + ModItems.WITHER_BONE.getRegistryName() + " , " + ModItems.THE_ULTIMATE_MATERIAL.getRegistryName() + " , " + ModItems.LAVA_CRYSTAL.getRegistryName() + " , " + ModItems.THE_GIFT_OF_THE_GODS.getRegistryName() + " , " + ModItems.STEEL_INGOT.getRegistryName() + " , " + ModItems.ELECTRICAL_INGOT.getRegistryName() + " , " + ModItems.DEV_TOOL.getRegistryName());
            writer.write("\n\nBlocks: " + ModBlocks.COMPRESSED_OBSIDIAN.getRegistryName() + " , " + ModBlocks.BLOCK_LAVA_CRYSTAL.getRegistryName() + " , " + ModBlocks.ARMOR_FORGE.getRegistryName() + " , " + " , " + ModBlocks.STEEL_BLOCK.getRegistryName() + " , " + ModBlocks.ELECTRICAL_BLOCK.getRegistryName() + " , " + ModBlocks.ADVANCED_ARMOR_FORGE + " , " + ModBlocks.LAVA_CACTUS + " , " + ModBlocks.LAVA_NETHER_BRICK);
            writer.write("\n\nThe Ultimate Armor Parts: " + ModItems.THE_ULTIMATE_BOOTS_LEFT.getRegistryName() + " , " + ModItems.THE_ULTIMATE_BOOTS_MIDDLE.getRegistryName() + " , " + ModItems.THE_ULTIMATE_BOOTS_RIGHT.getRegistryName() + " , " + ModItems.THE_ULTIMATE_LEGGINGS_LEFT.getRegistryName() + " , " + ModItems.THE_ULTIMATE_LEGGINGS_MIDDLE.getRegistryName() + " , " + ModItems.THE_ULTIMATE_LEGGINGS_RIGHT.getRegistryName() + " , " + ModItems.THE_ULTIMATE_CHESTPLATE_LEFT.getRegistryName() + " , " + ModItems.THE_ULTIMATE_CHESTPLATE_MIDDLE.getRegistryName() + " , " + ModItems.THE_ULTIMATE_CHESTPLATE_RIGHT.getRegistryName() + " , " + ModItems.THE_ULTIMATE_HELMET_LEFT.getRegistryName() + " , " + ModItems.THE_ULTIMATE_HELMET_MIDDLE.getRegistryName() + " , " + ModItems.THE_ULTIMATE_HELMET_RIGHT.getRegistryName());

        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }
    }
}
