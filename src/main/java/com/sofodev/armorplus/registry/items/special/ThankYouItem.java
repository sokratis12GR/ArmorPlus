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
        super(new Properties().isImmuneToFire().maxStackSize(0).group(ItemGroup.SEARCH));
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.create(stack.getDisplayName().getString(), TextFormatting.RED);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(translate(GOLD, "tooltip.armorplus.thank_you_6m.1"));
        tooltip.add(translate(BLUE, "tooltip.armorplus.thank_you_6m.2"));
        tooltip.add(translate(GREEN, "tooltip.armorplus.thank_you_6m.3"));
        tooltip.add(translate(RED, "tooltip.armorplus.thank_you_6m.4"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        if (!world.isRemote) {
            InventoryHelper.spawnItemStack(world, player.getPosX(), player.getPosY(), player.getPosZ(), new ItemStack(Items.CAKE));
            player.getHeldItemMainhand().setCount(0);
        }
        return super.onItemRightClick(world, player, hand);
    }
}
