/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.dev;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.base.BaseItem;

import java.io.*;
import java.util.List;

import static java.lang.String.format;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.thedragonteam.armorplus.DevUtils.enableDevTool;
import static net.thedragonteam.armorplus.util.ToolTipUtils.showInfo;
import static net.thedragonteam.armorplus.util.Utils.isNotNull;
import static org.apache.commons.compress.utils.IOUtils.closeQuietly;

public class DevTool extends BaseItem {

    private int entityNumber = 0;

    public DevTool() {
        super("dev_tool");
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return this.getRarity("DEV", TextFormatting.BOLD, "Dev");
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (enableDevTool() || APConfig.debugMode) {
            if (!playerIn.world.isRemote) {
                if (isNotNull(target)) {
                    this.writeToFile(playerIn, target);
                    entityNumber++;
                    return true;
                }
            }
        }
        return false;
    }

    private void writeToFile(EntityPlayer player, EntityLivingBase entity) {
        new File("./armorplus/entity/" + player.getUniqueID()).mkdirs();
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(
                    new File(format("./armorplus/entity/%s/%s_%d.json", player.getUniqueID(), entity.getName(), entityNumber))
            ));
            this.addLine(writer,
                    "{", //0
                    format("\t\"Entity Class\": \"%s\",", entity.getClass().toString().replace("class ", "")),
                    "\t\"Names\": {",
                    format("\t\t\"Name\": \"%s\",", entity.getName()),
                    format("\t\t\"Custom Name Tag\": \"%s\"", entity.getCustomNameTag()),
                    "\t},",
                    "\t\"Dimension\": {",
                    format("\t\t\"ID\": %d", entity.dimension),
                    "\t},",
                    "\t\"World\": {",
                    format("\t\t\"Name\": \"%s\",", entity.world.getWorldInfo().getWorldName()),
                    format("\t\t\"Are Commands Allowed\": %s,", entity.world.getWorldInfo().areCommandsAllowed()),
                    format("\t\t\"Is Difficulty Locked\": %s,", entity.world.getWorldInfo().isDifficultyLocked()),
                    format("\t\t\"Is Hardcore Mode Enabled\": %s,", entity.world.getWorldInfo().isHardcoreModeEnabled()),
                    "\t\t\"Spawn\": {",
                    format("\t\t\t\"X\": %d,", entity.world.getWorldInfo().getSpawnX()),
                    format("\t\t\t\"Y\": %d,", entity.world.getWorldInfo().getSpawnY()),
                    format("\t\t\t\"Z\": %d", entity.world.getWorldInfo().getSpawnZ()),
                    "\t\t}",
                    "\t},",
                    "\t\"Position\": {",
                    format("\t\t\"X\": %d,", entity.getPosition().getX()),
                    format("\t\t\"Y\": %d,", entity.getPosition().getY()),
                    format("\t\t\"Z\": %d", entity.getPosition().getZ()),
                    "\t},",
                    "\t\"Inventory Armor\": {",
                    "\t\t\"Head\": {"
            );
            this.writeArmorSlotItem(writer, entity, HEAD);
            this.addLine(writer,
                    tabTwo("},"), //2
                    tabTwo("\"Chest\": {") //2
            );
            this.writeArmorSlotItem(writer, entity, CHEST);
            this.addLine(writer,
                    tabTwo("},"), //2
                    tabTwo("\"Legs\": {") //2
            );
            this.writeArmorSlotItem(writer, entity, LEGS);
            this.addLine(writer,
                    tabTwo("},"), //2
                    tabTwo("\"Feet\": {") //2
            );
            this.writeArmorSlotItem(writer, entity, FEET);
            this.addLine(writer,
                    tabTwo("}"), //2
                    tabOne("},"), //1
                    tabOne("\"Inventory Hands\": {"), //1
                    tabTwo("\"Main-Hand\": {") //2
            );
            this.writeItem(writer, entity.getHeldItemMainhand());
            this.addLine(writer,
                    "\t\t},",
                    "\t\t\"Off-Hand\": {"
            );
            this.writeItem(writer, entity.getHeldItemOffhand());
            this.addLine(writer,
                    "\t\t}",
                    "\t},",
                    "\t\"Entity Info\": {",
                    format("\t\t\"Max Health\": %s,", entity.getMaxHealth()),
                    format("\t\t\"Health\": %s,", entity.getHealth()),
                    format("\t\t\"Absorption Amount\": %s,", entity.getAbsorptionAmount()),
                    format("\t\t\"Is Invulnerable\": %s,", entity.getIsInvulnerable()),
                    format("\t\t\"Is Invisible\": %s,", entity.isInvisible()),
                    format("\t\t\"Is Glowing\": %s,", entity.isGlowing()),
                    format("\t\t\"Is Immune To Explosion\": %s,", entity.isImmuneToExplosions()),
                    format("\t\t\"Is Immune To Fire\": %s", entity.isImmuneToFire()),
                    "\t}",
                    "}" //0
            );
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(writer);
        }
    }

    private void writeArmorSlotItem(Writer writer, EntityLivingBase entity, EntityEquipmentSlot slot) {
        this.writeItem(writer, entity.getItemStackFromSlot(slot));
    }

    private void writeItem(Writer writer, ItemStack stack) {
        if (!stack.isEmpty()) {
            addLine(writer,
                    tabThree(format("\"ItemStack\": \"%s\",", stack.getItem().getRegistryName())),
                    tabThree(format("\"Display Name\": \"%s\",", stack.getDisplayName())),
                    tabThree(format("\"Unlocalized Name\": \"%s\",", stack.getUnlocalizedName())),
                    tabThree(format("\"Count\": %d,", stack.getCount())),
                    tabThree(format("\"Metadata\": %d", stack.getMetadata()))
            );
        }
    }

    private void addLine(Writer writer, String... lines) {
        try {
            for (String line : lines) {
                writer.write(addLine(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String addLine(String string) {
        return format("%s\n", string);
    }

    private String tabOne(String string) {
        return format("\t%s", string);
    }

    private String tabTwo(String string) {
        return format("\t\t%s", string);
    }

    private String tabThree(String string) {
        return format("\t\t\t%s", string);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\247cOnly works if debugMode = true in the configuration file");
            tooltip.add("§9Ability: §rGives Information about the Target");
            tooltip.add("§3Use: §rRight Click a Target");
            if (advanced.isAdvanced()) {
                tooltip.add("Information is located at: <instance>/armorplus/<player_name>/<entity>-<id>");
            }
        } else {
            showInfo(tooltip, keyBindSneak, TextFormatting.BOLD);
        }
    }
}
