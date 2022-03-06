package com.sofodev.armorplus.registry.items.materials;

import com.sofodev.armorplus.registry.items.APItem;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.ChatFormatting.AQUA;

/**
 * @author Sokratis Fotkatzikis
 **/
public class FrostCrystalItem extends APItem {

    private final boolean isInfused;

    public FrostCrystalItem(boolean isInfused) {
        super(new Item.Properties().fireResistant());
        this.isInfused = isInfused;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.create("Frost Crystalic", AQUA);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if (!isInfused) {
            tooltip.add(translate("tooltip.armorplus.frost_crystal.how_to_infuse").setStyle(Style.EMPTY.withItalic(true).withColor(TextColor.parseColor("#670067"))));
        } else {
            tooltip.add(translate("tooltip.armorplus.frost_crystal.lore").setStyle(Style.EMPTY.withItalic(true).withColor(TextColor.parseColor("#670067"))));
        }
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    /**
     * @return True, If the crystal is infused otherwise False.
     */
    public boolean isInfused() {
        return isInfused;
    }
}