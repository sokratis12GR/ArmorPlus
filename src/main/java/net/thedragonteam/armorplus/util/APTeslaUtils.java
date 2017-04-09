/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.ITeslaProducer;
import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.common.Optional.Method;

public class APTeslaUtils {

    @CapabilityInject(ITeslaConsumer.class)
    public static Capability<ITeslaConsumer> teslaConsumer;
    @CapabilityInject(ITeslaProducer.class)
    public static Capability<ITeslaProducer> teslaProducer;
    @CapabilityInject(ITeslaHolder.class)
    public static Capability<ITeslaHolder> teslaHolder;

    @Method(modid = "tesla")
    public static boolean isPoweredItem(ItemStack stack) {
        return stack.hasCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN);
    }

    @Method(modid = "tesla")
    public static long getStoredPower(ItemStack stack) {
        if (isPoweredItem(stack)) {
            BaseTeslaContainer container = getContainer(stack);
            return getContainer(stack).getStoredPower();
        }
        return 0;
    }

    @Method(modid = "tesla")
    public static long getMaxCapacity(ItemStack stack) {
        return isPoweredItem(stack) ? getContainer(stack).getCapacity() : 0;
    }

    @Method(modid = "tesla")
    public static void addPower(ItemStack stack, long amount) {
        if (isPoweredItem(stack)) getContainer(stack).givePower(amount, false);
    }

    @Method(modid = "tesla")
    public static BaseTeslaContainer getContainer(ItemStack stack) {
        return isPoweredItem(stack) ? (BaseTeslaContainer) stack.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN) : null;
    }

    @Method(modid = "tesla")
    public static void setMaxCapacity(ItemStack stack, long amount) {
        if (isPoweredItem(stack)) getContainer(stack).setCapacity(amount);
    }

    @Method(modid = "tesla")
    public static void usePower(ItemStack stack, long amount) {
        if (isPoweredItem(stack)) getContainer(stack).takePower(amount, false);
    }

    @Method(modid = "tesla")
    public static long getMaxInput(ItemStack stack) {
        return isPoweredItem(stack) ? getContainer(stack).getInputRate() : 0;
    }

    @Method(modid = "tesla")
    public static long getMaxOutput(ItemStack stack) {
        return isPoweredItem(stack) ? getContainer(stack).getOutputRate() : 0;
    }

    @Method(modid = "tesla")
    public static ItemStack createChargedStack(ItemStack stack) {
        if (isPoweredItem(stack)) {
            ItemStack chargedstack = stack.copy();
            getContainer(chargedstack).setInputRate(APTeslaUtils.getMaxCapacity(stack));
            addPower(chargedstack, getMaxCapacity(stack));
            getContainer(chargedstack).setInputRate(APTeslaUtils.getMaxInput(stack));
            return chargedstack;
        }
        return ItemStack.EMPTY;
    }

    /**
     * Blocks
     */
    @Method(modid = "tesla")
    public static boolean isTelsaBlock(TileEntity tileEntity) {
        return tileEntity.hasCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN);
    }

    @Method(modid = "tesla")
    public static BaseTeslaContainer getContainer(TileEntity tileEntity) {
        if (isTelsaBlock(tileEntity)) {
            BaseTeslaContainer container = (BaseTeslaContainer) tileEntity.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN);
            return container;
        }
        return null;
    }

    @Method(modid = "tesla")
    public static boolean isConsumer(TileEntity tileEntity) {
        return tileEntity.hasCapability(TeslaCapabilities.CAPABILITY_CONSUMER, EnumFacing.DOWN);
    }

    @Method(modid = "tesla")
    public static long getMaxCapacity(TileEntity tile) {
        return getContainer(tile).getCapacity();
    }

    @Method(modid = "tesla")
    public static long getStoredPower(TileEntity tile) {
        return getContainer(tile).getStoredPower();
    }

    @Method(modid = "tesla")
    public static long getMissingPower(TileEntity tileEntity) {
        return getMaxCapacity(tileEntity) - getStoredPower(tileEntity);
    }

    @Method(modid = "tesla")
    public static boolean canAcceptPower(TileEntity tile, long amount) {
        return getMissingPower(tile) >= amount;
    }

    @Method(modid = "tesla")
    public static void addPower(TileEntity tileEntity, long amount) {
        getContainer(tileEntity).givePower(amount, false);
    }
}