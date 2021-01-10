package com.sofodev.armorplus.registry.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;

public class APItemBase extends APItem {

    public APItemBase() {
        super(new Item.Properties().rarity(Rarity.UNCOMMON).maxStackSize(8));
    }

    public APItemBase(Properties props) {
        super(props.rarity(Rarity.UNCOMMON).maxStackSize(8));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(translate(Color.fromHex("#252874"), "item.armorplus.base_soulless"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}