package net.thedragonteam.armorplus.iface;

import net.minecraft.item.EnumRarity;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.EnumHelper;

import static net.minecraft.util.text.TextFormatting.fromColorIndex;
import static net.minecraft.util.text.TextFormatting.getValueByName;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public interface IRarityHelper {

    default EnumRarity getRarity(String enumName, Object formatting, String displayName) {
        if (formatting instanceof TextFormatting) {
            return EnumHelper.addRarity(enumName, (TextFormatting) formatting, displayName);
        } else if (formatting instanceof String) {
            return EnumHelper.addRarity(enumName, getValueByName((String) formatting), displayName);
        } else if (formatting instanceof Integer) {
            return EnumHelper.addRarity(enumName, fromColorIndex((Integer) formatting), displayName);
        }
        return EnumRarity.COMMON;
    }

}
