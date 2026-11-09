package com.sofodev.armorplus.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = ArmorPlus.MODID, value = Dist.CLIENT)
public final class ArmorPlusKeys {

    public static final String CATEGORY = "key.category.armorplus.armorplus";
    /** Opens the Armor Effects screen, where set effects can be switched on/off. */
    public static final KeyMapping ARMOR_EFFECTS = new KeyMapping("key.armorplus.armor_effects", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, CATEGORY);
    /** Opens the Tool Effects screen, where the held tool's effects can be switched on/off. */
    public static final KeyMapping TOOL_EFFECTS = new KeyMapping("key.armorplus.tool_effects", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_H, CATEGORY);
    /** Opens the Weapon Effects screen, where the held weapon's on-hit effects can be switched on/off. */
    public static final KeyMapping WEAPON_EFFECTS = new KeyMapping("key.armorplus.weapon_effects", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_J, CATEGORY);
    /** Opens the Ranged Effects screen, where the held bow's effects can be switched on/off. */
    public static final KeyMapping RANGED_EFFECTS = new KeyMapping("key.armorplus.ranged_effects", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_K, CATEGORY);

    /** Held to show the descriptions of abilities in item tooltips. It's read from the keyboard, since a key mapping isn't updated while a screen is open. */
    public static final KeyMapping EFFECT_DETAILS = new KeyMapping("key.armorplus.effect_details", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_ALT, CATEGORY);

    private ArmorPlusKeys() {
    }

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        event.register(ARMOR_EFFECTS);
        event.register(TOOL_EFFECTS);
        event.register(WEAPON_EFFECTS);
        event.register(RANGED_EFFECTS);
        event.register(EFFECT_DETAILS);
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        for (EffectsScreen.Tab tab : EffectsScreen.Tab.values()) {
            boolean pressed = false;
            while (tab.key().consumeClick()) pressed = true;
            if (!pressed || minecraft.player == null || minecraft.screen != null) continue;
            // a menu without anything to show says why instead of opening empty
            if (tab.hasEffects(minecraft.player)) minecraft.setScreen(tab.createScreen());
            else minecraft.gui.setOverlayMessage(tab.noneMessage(), false);
        }
    }
}
