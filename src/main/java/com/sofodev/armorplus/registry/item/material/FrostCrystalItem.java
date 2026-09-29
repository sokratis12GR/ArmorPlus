package com.sofodev.armorplus.registry.item.material;

import com.sofodev.armorplus.registry.item.APItem;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
import java.util.function.Consumer;
import net.minecraft.world.item.component.TooltipDisplay;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;

/**
 * @author Sokratis Fotkatzikis
 **/
public class FrostCrystalItem extends APItem {

    private final boolean isInfused;

    public FrostCrystalItem(boolean isInfused) {
        super(com.sofodev.armorplus.registry.RegistryContext.itemProperties().fireResistant());
        this.isInfused = isInfused;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flagIn) {
        if (!isInfused) {
            tooltip.accept(translate("tooltip.armorplus.frost_crystal.how_to_infuse").setStyle(Style.EMPTY.withItalic(true).withColor(TextColor.parseColor("#670067").getOrThrow())));
        } else {
            tooltip.accept(translate("tooltip.armorplus.frost_crystal.lore").setStyle(Style.EMPTY.withItalic(true).withColor(TextColor.parseColor("#670067").getOrThrow())));
        }
        super.appendHoverText(stack, ctx, display, tooltip, flagIn);
    }

    /**
     * @return True, If the crystal is infused otherwise False.
     */
    public boolean isInfused() {
        return isInfused;
    }
}