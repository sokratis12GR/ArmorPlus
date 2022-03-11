package com.sofodev.armorplus.registry.items.special;

import com.sofodev.armorplus.registry.items.APItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.util.text.TextFormatting.*;

public class ThankYouItem extends APItem {

    public ThankYouItem() {
        super(new Properties().fireResistant().stacksTo(0).tab(ItemGroup.TAB_SEARCH));
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.create(stack.getHoverName().getString(), TextFormatting.RED);
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(translate(GOLD, "tooltip.armorplus.thank_you_6m.1"));
        tooltip.add(translate(BLUE, "tooltip.armorplus.thank_you_6m.2"));
        tooltip.add(translate(GREEN, "tooltip.armorplus.thank_you_6m.3"));
        tooltip.add(translate(RED, "tooltip.armorplus.thank_you_6m.4"));
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClientSide) {
            InventoryHelper.dropItemStack(world, player.getX(), player.getY(), player.getZ(), new ItemStack(Items.CAKE));
            player.getMainHandItem().setCount(0);
        }
        return super.use(world, player, hand);
    }
}
