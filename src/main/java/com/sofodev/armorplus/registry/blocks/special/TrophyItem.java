//package com.sofodev.armorplus.registry.blocks.special;
//
//import com.sofodev.armorplus.ArmorPlus;
//import com.sofodev.armorplus.registry.blocks.APBlockItem;
//import com.sofodev.armorplus.registry.items.APRarity;
//import net.minecraft.ChatFormatting;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.network.chat.Component;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.TooltipFlag;
//import net.minecraft.world.item.context.BlockPlaceContext;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraftforge.registries.ForgeRegistries;
//
//import javax.annotation.Nullable;
//import java.util.List;
//import java.util.function.Function;
//import java.util.function.Supplier;
//
//import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
//
//public class TrophyItem extends APBlockItem {
//
//    public TrophyItem(Supplier<Block> block) {
//        super(block.get(), new Properties().fireResistant().tab(ArmorPlus.AP_BLOCK_GROUP).rarity(APRarity.ENDER_DRAGON.getRarity()).stacksTo(64));
//    }
//
//    @Override
//    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
//        CompoundTag tag = stack.getTag();
//        if (tag != null && tag.contains("DisplayEntity", 10) && tag.getCompound("DisplayEntity").contains("id", 8)) {
//            ResourceLocation rl = new ResourceLocation(tag.getCompound("DisplayEntity").getString("id"));
//            if (ForgeRegistries.ENTITY_TYPES.getValue(rl) != null) {
//                Entity entity = ForgeRegistries.ENTITY_TYPES.getValue(rl).create(world);
//                if (entity != null) {
//                    tooltip.add(translate(ChatFormatting.GRAY, "tooltip.armorplus.trophy.dropped_by", entity.getName()));
//                }
//            }
//        }
//
//        super.appendHoverText(stack, world, tooltip, flag);
//    }
//
//    @Override
//    protected boolean placeBlock(BlockPlaceContext ctx, BlockState state) {
//        return ctx.getLevel().setBlock(ctx.getClickedPos(), state, 26);
//    }
//
//}
