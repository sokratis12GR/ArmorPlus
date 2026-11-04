package com.sofodev.armorplus.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = ArmorPlus.MODID, value = Dist.CLIENT)
public final class ArmorPlusKeys {

    public static final KeyMapping.Category CATEGORY = new KeyMapping.Category(Identifier.fromNamespaceAndPath(ArmorPlus.MODID, "armorplus"));
    /** Opens the Armor Effects screen, where set and rune effects can be switched on/off. */
    public static final KeyMapping ARMOR_EFFECTS = new KeyMapping("key.armorplus.armor_effects", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, CATEGORY);
    /** Opens the Tool Effects screen, where the held tool's rune effects can be switched on/off. */
    public static final KeyMapping TOOL_EFFECTS = new KeyMapping("key.armorplus.tool_effects", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_H, CATEGORY);
    /** Opens the Weapon Effects screen, where the held weapon's on-hit effects can be switched on/off. */
    public static final KeyMapping WEAPON_EFFECTS = new KeyMapping("key.armorplus.weapon_effects", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_J, CATEGORY);

    private ArmorPlusKeys() {
    }

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        event.registerCategory(CATEGORY);
        event.register(ARMOR_EFFECTS);
        event.register(TOOL_EFFECTS);
        event.register(WEAPON_EFFECTS);
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        while (ARMOR_EFFECTS.consumeClick()) {
            if (minecraft.player != null && minecraft.screen == null) minecraft.setScreen(new ArmorEffectsScreen());
        }
        while (TOOL_EFFECTS.consumeClick()) {
            if (minecraft.player != null && minecraft.screen == null) minecraft.setScreen(new ToolEffectsScreen());
        }
        while (WEAPON_EFFECTS.consumeClick()) {
            if (minecraft.player != null && minecraft.screen == null) minecraft.setScreen(new WeaponEffectsScreen());
        }
    }
}
