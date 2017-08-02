package net.thedragonteam.armorplus.iface;

import net.minecraft.item.EnumRarity;
import net.minecraft.util.text.TextFormatting;

import static net.thedragonteam.armorplus.util.EnumHelperUtil.addRarity;

public interface IRarityHelper {

    default EnumRarity getRarity(String enumName, Object formatting, String displayName) {
        if (formatting instanceof TextFormatting) {
            return addRarity(enumName, (TextFormatting) formatting, displayName);
        } else if (formatting instanceof String) {
            return addRarity(enumName, (String) formatting, displayName);
        } else if (formatting instanceof Integer) {
            return addRarity(enumName, (Integer) formatting, displayName);
        }
        return EnumRarity.COMMON;
    }

}
