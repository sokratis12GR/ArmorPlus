package net.thedragonteam.armorplus.blocks.ritual;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.blocks.base.BlockBase;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.ArrayList;
import java.util.Random;

import static net.thedragonteam.armorplus.registry.ModItems.templates;

/**
 * ArmorPlus - Kotlin created by sokratis12GR
 * - TheDragonTeam
 */
public class BlockRitualAltar extends BlockBase {

    private ArrayList<Item> itemList = new ArrayList<>();

    public boolean isValid;

    private static final AxisAlignedBB NOT_CONNECTED_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);

    public BlockRitualAltar() {
        super(Material.ROCK, "ritual_altar", 10F, 10F);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        for (Item item : templates)
            if (entityIn instanceof EntityItem && ((EntityItem) entityIn).getEntityItem().getItem() == item) {
                isItemValid(item);
                LogHelper.INSTANCE.info("Is Item Valid: " + isItemValid(item));
            }

        super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        for (int i = 1; i < templates.length; i++) {
            itemList.add(i, templates[i]);
        }
        super.updateTick(worldIn, pos, state, rand);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return NOT_CONNECTED_AABB;
    }

    private boolean isItemValid(Item item) {
        for (Item valid : itemList)
            isValid = valid == item;
        for (Item valid : templates)
            isValid = valid == item;
        return isValid;
    }
}
