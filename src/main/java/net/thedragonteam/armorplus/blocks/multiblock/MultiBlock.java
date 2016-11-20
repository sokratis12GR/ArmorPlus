/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.blocks.multiblock;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.*;

public class MultiBlock {

    public final List<MultiBlockComponent> components = new ArrayList<>();
    public final List<ItemStack> materials = new ArrayList<>();

    public BlockPos minPos = BlockPos.ORIGIN;
    public BlockPos maxPos = BlockPos.ORIGIN;
    public BlockPos offPos = BlockPos.ORIGIN;

    public final HashMap<BlockPos, MultiBlockComponent> locationCache = new HashMap<>();

    /**
     * Adds a Multi-Block component to this Multi-Block. The component's x y z
     * coords should be pivoted to the center of the structure.
     */
    public void addComponent(MultiBlockComponent component) {
        if (getComponentForLocation(component.getRelativePosition()) != null) {
            LogHelper.info("Overlapping blocks in multi-block structure!");
        }
        components.add(component);
        changeAxisForNewComponent(component.getRelativePosition());
        calculateCostForNewComponent(component);
        addComponentToLocationCache(component);
    }

    /**
     * Constructs and adds a Multi-Block component to this multi-block. The x y z
     * coords should be pivoted to the center of the structure.
     */
    public void addComponent(BlockPos pos, IBlockState state) {
        addComponent(new MultiBlockComponent(pos, state));
    }

    private void changeAxisForNewComponent(BlockPos pos) {
        if (pos.getX() < minPos.getX())
            minPos = new BlockPos(pos.getX(), minPos.getY(), minPos.getZ());
        else if (pos.getX() > maxPos.getX())
            maxPos = new BlockPos(pos.getX(), maxPos.getY(), maxPos.getZ());

        if (pos.getY() < minPos.getY())
            minPos = new BlockPos(minPos.getX(), pos.getY(), minPos.getZ());
        else if (pos.getY() > maxPos.getY())
            maxPos = new BlockPos(maxPos.getX(), pos.getY(), maxPos.getZ());

        if (pos.getZ() < minPos.getZ())
            minPos = new BlockPos(minPos.getX(), minPos.getY(), pos.getZ());
        else if (pos.getZ() > maxPos.getZ())
            maxPos = new BlockPos(maxPos.getX(), maxPos.getY(), pos.getZ());
    }

    private void calculateCostForNewComponent(MultiBlockComponent comp) {
        ItemStack[] materials = comp.getMaterials();
        if (materials != null)
            for (ItemStack stack : materials)
                addStack(stack);
    }

    private void addStack(ItemStack stack) {
        if (stack == null)
            return;

        for (ItemStack oStack : materials)
            if (oStack.isItemEqual(stack) && ItemStack.areItemStackTagsEqual(oStack, stack)) {
                oStack.func_190917_f(stack.func_190916_E());
                return;
            }

        materials.add(stack);
    }

    public void setRenderOffset(BlockPos pos) {
        offPos = pos;
    }

    public List<MultiBlockComponent> getComponents() {
        return components;
    }

    /**
     * Rotates this multi-block by the angle passed in. For the best results, use
     * only multiples of pi/2.
     */
    public void rotate(double angle) {
        for (MultiBlockComponent comp : getComponents())
            comp.rotate(angle);
        updateLocationCache();
    }

    public MultiBlock copy() {
        MultiBlock mb = new MultiBlock();
        for (MultiBlockComponent comp : getComponents())
            mb.addComponent(comp.copy());

        return mb;
    }

    /**
     * Creates a length 4 array of all the rotations multiple of pi/2 required
     * to render this multi-block in the world relevant to the 4 cardinal
     * orientations.
     */
    public Map<EnumFacing, MultiBlock> createRotations() {
        Map<EnumFacing, MultiBlock> ret = new EnumMap<>(EnumFacing.class);

        ret.put(EnumFacing.SOUTH, this);

        ret.put(EnumFacing.WEST, ret.get(EnumFacing.SOUTH).copy());
        ret.get(EnumFacing.WEST).rotate(Math.PI / 2);

        ret.put(EnumFacing.NORTH, ret.get(EnumFacing.WEST).copy());
        ret.get(EnumFacing.NORTH).rotate(Math.PI / 2);

        ret.put(EnumFacing.EAST, ret.get(EnumFacing.NORTH).copy());
        ret.get(EnumFacing.EAST).rotate(Math.PI / 2);

        return ret;
    }

    /**
     * Makes a Multi-BlockSet from this Multi-Block and its rotations using
     * createRotations().
     */
    public MultiBlockSet makeSet() {
        return new MultiBlockSet(this);
    }

    public int getXSize() {
        return Math.abs(minPos.getX()) + Math.abs(maxPos.getX()) + 1;
    }

    public int getYSize() {
        return Math.abs(minPos.getY()) + Math.abs(maxPos.getY()) + 1;
    }

    public int getZSize() {
        return Math.abs(minPos.getZ()) + Math.abs(maxPos.getZ()) + 1;
    }

    /**
     * Rebuilds the location cache
     */
    public void updateLocationCache() {
        locationCache.clear();
        for (MultiBlockComponent comp : components)
            addComponentToLocationCache(comp);
    }

    /**
     * Adds a single component to the location cache
     */
    private void addComponentToLocationCache(MultiBlockComponent comp) {
        BlockPos pos = comp.getRelativePosition();
        locationCache.put(pos, comp);
    }

    /**
     * Gets the component for a given location
     *
     * @param pos the position of the block component
     */
    public MultiBlockComponent getComponentForLocation(BlockPos pos) {
        return locationCache.get(pos);
    }

}
