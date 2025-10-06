package com.sofodev.armorplus.registry.item.material;

import com.sofodev.armorplus.registry.item.APItem;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;

/**
 * @author Sokratis Fotkatzikis
 **/
public class FrostLavaCrystalItem extends APItem {

    public FrostLavaCrystalItem() {
        super(new Properties().fireResistant());
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(translate("tooltip.armorplus.frost_lava_crystal.lore").setStyle(Style.EMPTY.withItalic(true).withColor(TextColor.parseColor("#670067").getOrThrow())));
        super.appendHoverText(stack, ctx, tooltip, flagIn);
    }
}