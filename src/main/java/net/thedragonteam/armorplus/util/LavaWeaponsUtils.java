package net.thedragonteam.armorplus.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.lava;
import static net.thedragonteam.armorplus.util.PotionUtils.localizePotion;
import static net.thedragonteam.armorplus.util.ToolTipUtils.showInfo;

public class LavaWeaponsUtils {

    @SideOnly(Side.CLIENT)
    public static void addLavaInformation(List<String> tooltip, List<String> effects, List<Integer> levels, TextFormatting formatting) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            if (lava.weapons.shouldApplyFire) {
                tooltip.add("\2479Abilities:");
                tooltip.add("\u00a79" + "Sets the entities on fire for " + lava.weapons.onFireSeconds + " seconds");
            }
            if (lava.weapons.enableEffects) {
                for (int i = 0; i < effects.size(); i++) {
                    if (effects.get(i).equals("empty")) {
                        return;
                    }
                    tooltip.add("\2479Abilities:");
                    tooltip.add("\u00a79" + localizePotion(effects.get(i)) + " " + (levels.get(i) + 1));
                }
            }
            tooltip.add("\2473Use: " + "\247rHit a Target");
        } else {
            showInfo(tooltip, keyBindSneak, formatting);
        }
    }
}
