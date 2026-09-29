package com.sofodev.armorplus.registry.item.material;

import com.sofodev.armorplus.registry.item.APItem;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
import java.util.function.Consumer;
import net.minecraft.world.item.component.TooltipDisplay;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;

/**
 * @author Sokratis Fotkatzikis
 **/
public class FrostLavaCrystalItem extends APItem {

    public FrostLavaCrystalItem() {
        super(com.sofodev.armorplus.registry.RegistryContext.itemProperties().fireResistant());
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flagIn) {
        tooltip.accept(translate("tooltip.armorplus.frost_lava_crystal.lore").setStyle(Style.EMPTY.withItalic(true).withColor(TextColor.parseColor("#670067").getOrThrow())));
        super.appendHoverText(stack, ctx, display, tooltip, flagIn);
    }
}