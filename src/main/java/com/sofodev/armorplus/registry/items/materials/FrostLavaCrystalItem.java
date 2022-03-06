package com.sofodev.armorplus.registry.items.materials;

import com.sofodev.armorplus.registry.items.APItem;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.ChatFormatting.YELLOW;

/**
 * @author Sokratis Fotkatzikis
 **/
public class FrostLavaCrystalItem extends APItem {

    public FrostLavaCrystalItem() {
        super(new Properties().fireResistant());
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.create("Frost-Lava Crystalic", YELLOW);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(translate("tooltip.armorplus.frost_lava_crystal.lore").setStyle(Style.EMPTY.withItalic(true).withColor(TextColor.parseColor("#670067"))));
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }
}