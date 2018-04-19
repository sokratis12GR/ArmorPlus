/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.dev;

import com.google.gson.*;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.base.BaseItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.thedragonteam.armorplus.DevUtils.enableDevTool;
import static net.thedragonteam.armorplus.ModConfig.DebugConfig.debugMode;
import static net.thedragonteam.armorplus.util.JsonUtils.*;
import static net.thedragonteam.armorplus.util.ToolTipUtils.showInfo;
import static net.thedragonteam.armorplus.util.Utils.isNotNull;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class DevTool extends BaseItem {

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
        if ((enableDevTool() || debugMode) && !playerIn.world.isRemote && isNotNull(target)) {
            this.writeFile(playerIn, target);
            return true;
        }
        return false;
    }

    private void writeFile(EntityPlayer player, EntityLivingBase entity) {
        new File("./armorplus/entity/" + player.getUniqueID()).mkdirs();
        //Write JSON String to file
        LocalDateTime dateTime = LocalDateTime.now();
        String timeStamp = dateTime.getHour() + "-" + dateTime.getMinute() + "-" + dateTime.getSecond() + "-" + dateTime.getYear() + "-" + dateTime.getMonth().getValue() + "-" + dateTime.getDayOfMonth();
        try (FileWriter fileWriter = new FileWriter(new File(
            format("./armorplus/entity/%s/%s_%s.json", player.getUniqueID(), entity.getName(), timeStamp)
        ))) {
            WorldInfo worldInfo = entity.world.getWorldInfo();
            BlockPos entityPos = entity.getPosition();
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("entity_class", getPrimitive(entity.getClass().getName()));
            Map<String, JsonElement> nameMap = new HashMap<>();
            nameMap.put("name", getPrimitive(entity.getName()));
            nameMap.put("custom_name_tag", getPrimitive(entity.getCustomNameTag()));
            jsonObject.add("names", addArray(nameMap));
            jsonObject.add("dimension", addArray("id", getPrimitive(entity.dimension)));
            Map<String, JsonElement> worldMap = new HashMap<>();
            worldMap.put("name", getPrimitive(worldInfo.getWorldName()));
            worldMap.put("are_commands_allowed", getPrimitive(worldInfo.areCommandsAllowed()));
            worldMap.put("is_difficulty_locked", getPrimitive(worldInfo.isDifficultyLocked()));
            worldMap.put("is_hardcore_mode_enabled", getPrimitive(worldInfo.isHardcoreModeEnabled()));
            Map<String, JsonElement> worldSpawnMap = new HashMap<>();
            worldSpawnMap.put("x", getPrimitive(worldInfo.getSpawnX()));
            worldSpawnMap.put("y", getPrimitive(worldInfo.getSpawnY()));
            worldSpawnMap.put("z", getPrimitive(worldInfo.getSpawnZ()));
            worldMap.put("spawn", addArray(worldSpawnMap));
            jsonObject.add("world", addArray(addProperty(worldMap)));
            Map<String, JsonElement> entityPosMap = new HashMap<>();
            entityPosMap.put("x", getPrimitive(entityPos.getX()));
            entityPosMap.put("y", getPrimitive(entityPos.getY()));
            entityPosMap.put("z", getPrimitive(entityPos.getZ()));
            jsonObject.add("position", addArray(entityPosMap));
            Map<String, JsonElement> inventoryArmorMap = new HashMap<>();
            inventoryArmorMap.put("head", addArmorItemData(entity, HEAD));
            inventoryArmorMap.put("chest", addArmorItemData(entity, CHEST));
            inventoryArmorMap.put("legs", addArmorItemData(entity, LEGS));
            inventoryArmorMap.put("feet", addArmorItemData(entity, FEET));
            jsonObject.add("inventory_armor", addArray(inventoryArmorMap));
            Map<String, JsonElement> inventoryHandsMap = new HashMap<>();
            inventoryHandsMap.put("main_hand", addItemData(entity.getHeldItemMainhand()));
            inventoryHandsMap.put("off_hand", addItemData(entity.getHeldItemOffhand()));
            jsonObject.add("inventory_hands", addArray(inventoryHandsMap));
            Map<String, JsonElement> entityInfoMap = new HashMap<>();
            entityInfoMap.put("max_health", getPrimitive(entity.getMaxHealth()));
            entityInfoMap.put("health", getPrimitive(entity.getHealth()));
            entityInfoMap.put("absorption_amount", getPrimitive(entity.getAbsorptionAmount()));
            entityInfoMap.put("is_invulnerable", getPrimitive(entity.getIsInvulnerable()));
            entityInfoMap.put("is_invisible", getPrimitive(entity.isInvisible()));
            entityInfoMap.put("is_glowing", getPrimitive(entity.isGlowing()));
            entityInfoMap.put("is_immune_to_explosions", getPrimitive(entity.isImmuneToExplosions()));
            entityInfoMap.put("is_immune_to_fire", getPrimitive(entity.isImmuneToFire()));
            jsonObject.add("entity_info", addArray(entityInfoMap));
            // Create a new Gson object
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            //convert the Java object to json
            String jsonString = gson.toJson(jsonObject);
            fileWriter.write(jsonString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JsonArray addArmorItemData(EntityLivingBase entity, EntityEquipmentSlot slot) {
        return this.addItemData(entity.getItemStackFromSlot(slot));
    }

    private JsonArray addItemData(ItemStack stack) {
        Map<String, JsonElement> itemDataMap = new HashMap<>();
        if (!stack.isEmpty()) {
            itemDataMap.put("itemstack", getPrimitive(stack.getItem().getRegistryName()));
            itemDataMap.put("display_name", getPrimitive(stack.getDisplayName()));
            itemDataMap.put("unlocalized_name", getPrimitive(stack.getUnlocalizedName()));
            itemDataMap.put("count", getPrimitive(stack.getCount()));
            itemDataMap.put("metadata", getPrimitive(stack.getMetadata()));
        }
        return addArray(itemDataMap);
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
                tooltip.add("Information is located at: <instance>/armorplus/<player_name>/<entity>-<id>-<date>");
            }
        } else {
            showInfo(tooltip, keyBindSneak, TextFormatting.BOLD);
        }
    }
}
