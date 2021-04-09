package com.sofodev.armorplus.registry.blocks.special;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.blocks.APBlockItem;
import com.sofodev.armorplus.registry.items.APRarity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;

public class TrophyItem extends APBlockItem {

    public TrophyItem(Supplier<Block> block) {
        super(block.get(), new Properties().fireResistant().tab(ArmorPlus.AP_BLOCK_GROUP).rarity(APRarity.ENDER_DRAGON.getRarity()).stacksTo(64));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        CompoundNBT tag = stack.getTag();
        if (tag != null && tag.contains("DisplayEntity", 10) && tag.getCompound("DisplayEntity").contains("id", 8)) {
            ResourceLocation rl = new ResourceLocation(tag.getCompound("DisplayEntity").getString("id"));
            if (ForgeRegistries.ENTITIES.getValue(rl) != null) {
                Entity entity = ForgeRegistries.ENTITIES.getValue(rl).create(world);
                if (entity != null) {
                    tooltip.add(translate(TextFormatting.GRAY, "tooltip.armorplus.trophy.dropped_by", entity.getName()));
                }
            }
        }

        super.appendHoverText(stack, world, tooltip, flag);
    }

    @Override
    protected boolean placeBlock(BlockItemUseContext ctx, BlockState state) {
        return ctx.getLevel().setBlock(ctx.getClickedPos(), state, 26);
    }
}
