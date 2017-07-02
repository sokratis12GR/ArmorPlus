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
import net.thedragonteam.armorplus.util.EnumHelperUtil;

import java.io.*;
import java.util.List;

import static java.lang.String.format;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.thedragonteam.armorplus.util.ToolTipUtils.showInfo;
import static org.apache.commons.compress.utils.IOUtils.closeQuietly;

public class DevTool extends BaseItem {

    private EnumRarity dev;
    private int entityNumber = 0;

    public DevTool() {
        super("dev_tool");
        dev = EnumHelperUtil.addRarity("DEV", TextFormatting.BOLD, "Dev");
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return dev;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (ArmorPlus.DEV_ENVIRONMENT || APConfig.debugMode) {
            if (!playerIn.world.isRemote) {
                if (target != null) {
                    this.writeToFile(playerIn, target);
                    entityNumber++;
                    return true;
                }
            }
        }
        return false;
    }

    private void writeToFile(EntityPlayer player, EntityLivingBase entity) {
        new File("./armorplus/entity/" + player.getName()).mkdirs();
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(
                    new File(format("./armorplus/entity/%s/%s_%d.json", player.getName(), entity.getName(), entityNumber))));
            writer.write(write("{"));
            writer.write(write(format("\t\"Entity Class\": \"%s\",", entity.getClass().toString().replace("class ", ""))));
            writer.write(write("\t\"Names\": {"));
            writer.write(write(format("\t\t\"Name\": \"%s\",", entity.getName())));
            writer.write(write(format("\t\t\"Custom Name Tag\": \"%s\"", entity.getCustomNameTag())));
            writer.write(write("\t},"));
            writer.write(write("\t\"Dimension\": {"));
            writer.write(write(format("\t\t\"ID\": %d", entity.dimension)));
            writer.write(write("\t},"));
            writer.write(write("\t\"World\": {"));
            writer.write(write(format("\t\t\"Name\": \"%s\",", entity.world.getWorldInfo().getWorldName())));
            writer.write(write(format("\t\t\"Are Commands Allowed\": %s,", entity.world.getWorldInfo().areCommandsAllowed())));
            writer.write(write(format("\t\t\"Is Difficulty Locked\": %s,", entity.world.getWorldInfo().isDifficultyLocked())));
            writer.write(write(format("\t\t\"Is Hardcore Mode Enabled\": %s,", entity.world.getWorldInfo().isHardcoreModeEnabled())));
            writer.write(write("\t\t\"Spawn\": {"));
            writer.write(write(format("\t\t\t\"X\": %d,", entity.world.getWorldInfo().getSpawnX())));
            writer.write(write(format("\t\t\t\"Y\": %d,", entity.world.getWorldInfo().getSpawnY())));
            writer.write(write(format("\t\t\t\"Z\": %d", entity.world.getWorldInfo().getSpawnZ())));
            writer.write(write("\t\t}"));
            writer.write(write("\t},"));
            writer.write(write("\t\"Position\": {"));
            writer.write(write(format("\t\t\"X\": %d,", entity.getPosition().getX())));
            writer.write(write(format("\t\t\"Y\": %d,", entity.getPosition().getY())));
            writer.write(write(format("\t\t\"Z\": %d", entity.getPosition().getZ())));
            writer.write(write("\t},"));
            writer.write(write("\t\"Inventory Armor\": {"));
            writer.write(write("\t\t\"Head\": {"));
            this.writeArmorSlotItem(writer, entity, HEAD);
            writer.write(write("\t\t},"));
            writer.write(write("\t\t\"Chest\": {"));
            this.writeArmorSlotItem(writer, entity, CHEST);
            writer.write(write("\t\t},"));
            writer.write(write("\t\t\"Legs\": {"));
            this.writeArmorSlotItem(writer, entity, LEGS);
            writer.write(write("\t\t},"));
            writer.write(write("\t\t\"Feet\": {"));
            this.writeArmorSlotItem(writer, entity, FEET);
            writer.write(write("\t\t}"));
            writer.write(write("\t},"));
            writer.write(write("\t\"Inventory Hands\": {"));
            writer.write(write("\t\t\"Main-Hand\": {"));
            writeItem(writer, entity.getHeldItemMainhand());
            writer.write(write("\t\t},"));
            writer.write(write("\t\t\"Off-Hand\": {"));
            writeItem(writer, entity.getHeldItemOffhand());
            writer.write(write("\t\t}"));
            writer.write(write("\t},"));
            writer.write(write("\t\"Entity Info\": {"));
            writer.write(write(format("\t\t\"Max Health\": %s,", entity.getMaxHealth())));
            writer.write(write(format("\t\t\"Health\": %s,", entity.getHealth())));
            writer.write(write(format("\t\t\"Absorption Amount\": %s,", entity.getAbsorptionAmount())));
            writer.write(write(format("\t\t\"Is Invulnerable\": %s,", entity.getIsInvulnerable())));
            writer.write(write(format("\t\t\"Is Invisible\": %s,", entity.isInvisible())));
            writer.write(write(format("\t\t\"Is Glowing\": %s,", entity.isGlowing())));
            writer.write(write(format("\t\t\"Is Immune To Explosion\": %s,", entity.isImmuneToExplosions())));
            writer.write(write(format("\t\t\"Is Immune To Fire\": %s", entity.isImmuneToFire())));
            writer.write(write("\t}"));
            writer.write(write("}"));
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
        try {
            if (!stack.isEmpty()) {
                writer.write(write(format("\t\t\t\"ItemStack\": \"%s\",", stack.getItem().getRegistryName())));
                writer.write(write(format("\t\t\t\"Display Name\": \"%s\",", stack.getDisplayName())));
                writer.write(write(format("\t\t\t\"Unlocalized Name\": \"%s\",", stack.getUnlocalizedName())));
                writer.write(write(format("\t\t\t\"Count\": %d,", stack.getCount())));
                writer.write(write(format("\t\t\t\"Metadata\": %d", stack.getMetadata())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String write(String string) {
        return format("%s\n", string);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\2479Ability: " + "\247rGives Information about the Target");
            tooltip.add("\2473Use: " + "\247rRight Click a Target");
            if (advanced.isAdvanced()) {
                tooltip.add("Information is located at: <instance>/armorplus/<player_name>/<entity>-<id>");
            }
        } else {
            showInfo(tooltip, keyBindSneak, TextFormatting.BOLD);
        }
    }
}
