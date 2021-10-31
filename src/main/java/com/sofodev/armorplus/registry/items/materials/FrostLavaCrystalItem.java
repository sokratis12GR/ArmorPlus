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
import static net.minecraft.util.text.TextFormatting.YELLOW;

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
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(translate("tooltip.armorplus.frost_lava_crystal.lore").setStyle(Style.EMPTY.withItalic(true).withColor(Color.parseColor("#670067"))));
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }
}