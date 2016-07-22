package sokratis12gr.armorplus.util;

import net.minecraftforge.common.MinecraftForge;
import sokratis12gr.armorplus.ArmorPlus;
import sokratis12gr.armorplus.armors.origin.*;
import sokratis12gr.armorplus.armors.reinforced.RCArmor;
import sokratis12gr.armorplus.armors.reinforced.RDArmor;
import sokratis12gr.armorplus.armors.reinforced.RGArmor;
import sokratis12gr.armorplus.armors.reinforced.RIArmor;
import sokratis12gr.armorplus.armors.special.EnderDragonArmor;
import sokratis12gr.armorplus.armors.special.GuardianArmor;
import sokratis12gr.armorplus.armors.special.SuperStarArmor;
import sokratis12gr.armorplus.armors.special.TheUltimateArmor;
import sokratis12gr.armorplus.armors.special.mob.ChickenArmor;
import sokratis12gr.armorplus.armors.special.mob.SlimeArmor;
import sokratis12gr.armorplus.armors.tconstruct.*;
import sokratis12gr.armorplus.armors.v2.ElectricalArmor;
import sokratis12gr.armorplus.armors.v2.SteelArmor;
import sokratis12gr.armorplus.registry.ModBlocks;
import sokratis12gr.armorplus.registry.ModItems;

import java.io.*;

/**
 * Created by Socrates on 4/23/2016.
 */
public class Logger {

    public static void init(File file) {
        syncConfig();
    }

    public static void syncConfig() {

        Writer writer = null;

        //ArmorPlus.html
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("config" + "/" + "sokratis12GR's Mods" + "/" + ArmorPlus.MODID + "/" + "ArmorPlus.html"), "utf-8"));
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
        //Note.txt
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("config" + "/" + "sokratis12GR's Mods" + "/" + ArmorPlus.MODID + "/" + "Note.txt"), "utf-8"));
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
                    new FileOutputStream("config" + "/" + "sokratis12GR's Mods" + "/" + ArmorPlus.MODID + "/" + "ArmorPlusItems.txt"), "utf-8"));
            writer.write("1. Coal Armor: " + CoalArmor.helmet.getRegistryName() + " , " + CoalArmor.chestplate.getRegistryName() + " , " + CoalArmor.legs.getRegistryName() + " , " + CoalArmor.boots.getRegistryName());
            writer.write("\n\n2. Lapis Armor: " + LapisArmor.helmet.getRegistryName() + " , " + LapisArmor.chestplate.getRegistryName() + " , " + LapisArmor.legs.getRegistryName() + " , " + LapisArmor.boots.getRegistryName());
            writer.write("\n\n3. Redstone Armor: " + RedstoneArmor.helmet.getRegistryName() + " , " + RedstoneArmor.chestplate.getRegistryName() + " , " + RedstoneArmor.legs.getRegistryName() + " , " + RedstoneArmor.boots.getRegistryName());
            writer.write("\n\n4. Emerald Armor: " + EmeraldArmor.helmet.getRegistryName() + " , " + EmeraldArmor.chestplate.getRegistryName() + " , " + EmeraldArmor.legs.getRegistryName() + " , " + EmeraldArmor.boots.getRegistryName());
            writer.write("\n\n5. Obsidian Armor: " + ObsidianArmor.helmet.getRegistryName() + " , " + ObsidianArmor.chestplate.getRegistryName() + " , " + ObsidianArmor.legs.getRegistryName() + " , " + ObsidianArmor.boots.getRegistryName());
            writer.write("\n\n6. Lava Armor: " + LavaArmor.helmet.getRegistryName() + " , " + LavaArmor.chestplate.getRegistryName() + " , " + LavaArmor.legs.getRegistryName() + " , " + LavaArmor.boots.getRegistryName());
            writer.write("\n\n7. Guardian Armor: " + GuardianArmor.helmet.getRegistryName() + " , " + GuardianArmor.chestplate.getRegistryName() + " , " + GuardianArmor.legs.getRegistryName() + " , " + GuardianArmor.boots.getRegistryName());
            writer.write("\n\n8. Super Star Armor: " + SuperStarArmor.helmet.getRegistryName() + " , " + SuperStarArmor.chestplate.getRegistryName() + " , " + SuperStarArmor.legs.getRegistryName() + " , " + SuperStarArmor.boots.getRegistryName());
            writer.write("\n\n9. Ender Dragon Armor: " + EnderDragonArmor.helmet.getRegistryName() + " , " + EnderDragonArmor.chestplate.getRegistryName() + " , " + EnderDragonArmor.legs.getRegistryName() + " , " + EnderDragonArmor.boots.getRegistryName());
            writer.write("\n\n10. The Ultimate Armor: " + TheUltimateArmor.helmet.getRegistryName() + " , " + TheUltimateArmor.chestplate.getRegistryName() + " , " + TheUltimateArmor.legs.getRegistryName() + " , " + TheUltimateArmor.boots.getRegistryName());
            writer.write("\n\n11. Reinforced Gold Armor: " + RGArmor.helmet.getRegistryName() + " , " + RGArmor.chestplate.getRegistryName() + " , " + RGArmor.legs.getRegistryName() + " , " + RGArmor.boots.getRegistryName());
            writer.write("\n\n12. Reinforced Chain Armor: " + RCArmor.helmet.getRegistryName() + " , " + RCArmor.chestplate.getRegistryName() + " , " + RCArmor.legs.getRegistryName() + " , " + RCArmor.boots.getRegistryName());
            writer.write("\n\n13. Reinforced Iron Armor: " + RIArmor.helmet.getRegistryName() + " , " + RIArmor.chestplate.getRegistryName() + " , " + RIArmor.legs.getRegistryName() + " , " + RIArmor.boots.getRegistryName());
            writer.write("\n\n14. Reinforced Diamond Armor: " + RDArmor.helmet.getRegistryName() + " , " + RDArmor.chestplate.getRegistryName() + " , " + RDArmor.legs.getRegistryName() + " , " + RDArmor.boots.getRegistryName());
            writer.write("\n\n15. Cobalt Armor: " + CobaltArmor.helmet.getRegistryName() + " , " + CobaltArmor.chestplate.getRegistryName() + " , " + CobaltArmor.legs.getRegistryName() + " , " + CobaltArmor.boots.getRegistryName());
            writer.write("\n\n16. Ardite  Armor: " + ArditeArmor.helmet.getRegistryName() + " , " + ArditeArmor.chestplate.getRegistryName() + " , " + ArditeArmor.legs.getRegistryName() + " , " + ArditeArmor.boots.getRegistryName());
            writer.write("\n\n17. Manyullyn Armor: " + ManyullynArmor.helmet.getRegistryName() + " , " + ManyullynArmor.chestplate.getRegistryName() + " , " + ManyullynArmor.legs.getRegistryName() + " , " + ManyullynArmor.boots.getRegistryName());
            writer.write("\n\n18. Pig Iron Armor: " + PigIronArmor.helmet.getRegistryName() + " , " + PigIronArmor.chestplate.getRegistryName() + " , " + PigIronArmor.legs.getRegistryName() + " , " + PigIronArmor.boots.getRegistryName());
            writer.write("\n\n19. Knight Slime Armor: " + KnightSlimeArmor.helmet.getRegistryName() + " , " + KnightSlimeArmor.chestplate.getRegistryName() + " , " + KnightSlimeArmor.legs.getRegistryName() + " , " + KnightSlimeArmor.boots.getRegistryName());
            writer.write("\n\n20. Chicken Armor: " + ChickenArmor.helmet.getRegistryName() + " , " + ChickenArmor.chestplate.getRegistryName() + " , " + ChickenArmor.legs.getRegistryName() + " , " + ChickenArmor.boots.getRegistryName());
            writer.write("\n\n21. Slime Armor: " + SlimeArmor.helmet.getRegistryName() + " , " + SlimeArmor.chestplate.getRegistryName() + " , " + SlimeArmor.legs.getRegistryName() + " , " + SlimeArmor.boots.getRegistryName());
            writer.write("\n\n22. Steel Armor: " + SteelArmor.helmet.getRegistryName() + " , " + SteelArmor.chestplate.getRegistryName() + " , " + SteelArmor.legs.getRegistryName() + " , " + SteelArmor.boots.getRegistryName());
            writer.write("\n\n23. Electrical Armor: " + ElectricalArmor.helmet.getRegistryName() + " , " + ElectricalArmor.chestplate.getRegistryName() + " , " + ElectricalArmor.legs.getRegistryName() + " , " + ElectricalArmor.boots.getRegistryName());
            writer.write("\n\nItems: " + ModItems.CHAINMAIL.getRegistryName() + " , " + ModItems.ENDER_DRAGON_SCALE.getRegistryName() + " , " + ModItems.REINFORCING_MATERIAL.getRegistryName() + " , " + ModItems.GUARDIAN_SCALE.getRegistryName() + " , "
                    + ModItems.WITHER_BONE.getRegistryName() + " , " + ModItems.THE_ULTIMATE_MATERIAL.getRegistryName() + " , " + ModItems.LAVA_CRYSTAL.getRegistryName() + " , " + ModItems.THE_GIFT_OF_THE_GODS.getRegistryName() + " , " + ModItems.STEEL_INGOT.getRegistryName() + " , " + ModItems.ELECTRICAL_INGOT.getRegistryName());
            writer.write("\n\nBlocks: " + ModBlocks.COMPRESSED_OBSIDIAN.getRegistryName() + " , " + ModBlocks.BLOCK_LAVA_CRYSTAL.getRegistryName() + " , " + ModBlocks.ARMOR_FORGE.getRegistryName() + " , " + ModBlocks.STEEL_ORE.getRegistryName() + " , " + ModBlocks.STEEL_BLOCK.getRegistryName() + " , " + ModBlocks.ELECTRICAL_BLOCK.getRegistryName());
        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }
    }
}
