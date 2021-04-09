package com.sofodev.armorplus.registry.items.materials;

import com.sofodev.armorplus.registry.items.APItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.util.text.TextFormatting.AQUA;

/**
 * @author Sokratis Fotkatzikis
 **/
public class FrostCrystalItem extends APItem {

    private final boolean isInfused;

    public FrostCrystalItem(boolean isInfused) {
        super(new Properties().fireResistant());
        this.isInfused = isInfused;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.create("Frost Crystalic", AQUA);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (!isInfused) {
            tooltip.add(translate("tooltip.armorplus.frost_crystal.how_to_infuse").setStyle(Style.EMPTY.withItalic(true).withColor(Color.parseColor("#670067"))));
        } else {
            tooltip.add(translate("tooltip.armorplus.frost_crystal.lore").setStyle(Style.EMPTY.withItalic(true).withColor(Color.parseColor("#670067"))));
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