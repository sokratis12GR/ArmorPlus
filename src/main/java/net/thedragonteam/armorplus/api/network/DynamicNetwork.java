/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.network;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.thedragonteam.armorplus.api.IClientTicker;

import java.util.*;

public abstract class DynamicNetwork<A, N extends DynamicNetwork<A, N>> implements IClientTicker {
    public LinkedHashSet<IGridTransmitter<A, N>> transmitters = Sets.newLinkedHashSet();
    public LinkedHashSet<IGridTransmitter<A, N>> transmittersToAdd = Sets.newLinkedHashSet();
    public LinkedHashSet<IGridTransmitter<A, N>> transmittersAdded = Sets.newLinkedHashSet();

    public HashMap<IGridTransmitter<A, N>, EnumSet<EnumFacing>> changedAcceptors = Maps.newHashMap();
    protected int capacity = 0;
    protected double meanCapacity = 0;
    protected boolean needsUpdate = false;
    protected int updateDelay = 0;
    protected boolean firstUpdate = true;
    protected World worldObj = null;
    private Set<DelayQueue> updateQueue = new LinkedHashSet<DelayQueue>();

    public void addNewTransmitters(Collection<IGridTransmitter<A, N>> newTransmitters) {
        transmittersToAdd.addAll(newTransmitters);
    }

    public abstract void absorbBuffer(IGridTransmitter<A, N> transmitter);

    public abstract void clampBuffer();

    public void invalidate() {
        //Remove invalid transmitters first for share calculations
        for (Iterator<IGridTransmitter<A, N>> iter = transmitters.iterator(); iter.hasNext(); ) {
            IGridTransmitter<A, N> transmitter = iter.next();

            if (!transmitter.isValid()) {
                iter.remove();
                continue;
            }
        }

        //Clamp the new buffer
        clampBuffer();

        //Update all shares
        for (IGridTransmitter<A, N> transmitter : transmitters) {
            transmitter.updateShare();
        }

        //Now invalidate the transmitters
        for (IGridTransmitter<A, N> transmitter : transmitters) {
            invalidateTransmitter(transmitter);
        }

        transmitters.clear();
        deregister();
    }

    public void invalidateTransmitter(IGridTransmitter<A, N> transmitter) {
        if (!worldObj.isRemote && transmitter.isValid()) {
            transmitter.takeShare();
            transmitter.setTransmitterNetwork(null);
        }
    }

    public void acceptorChanged(IGridTransmitter<A, N> transmitter, EnumFacing side) {
        EnumSet<EnumFacing> directions = changedAcceptors.get(transmitter);

        if (directions != null) {
            directions.add(side);
        } else {
            changedAcceptors.put(transmitter, EnumSet.of(side));
        }

        TransmitterNetworkRegistry.registerChangedNetwork(this);
    }

    public void register() {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            TransmitterNetworkRegistry.getInstance().registerNetwork(this);
        } else {
            MinecraftForge.EVENT_BUS.post(new ClientTickUpdate(this, (byte) 1));
        }
    }

    public void deregister() {
        transmitters.clear();

        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            TransmitterNetworkRegistry.getInstance().removeNetwork(this);
        } else {
            MinecraftForge.EVENT_BUS.post(new ClientTickUpdate(this, (byte) 0));
        }
    }

    public int getSize() {
        return transmitters.size();
    }

    public synchronized void updateCapacity() {
        updateMeanCapacity();
        capacity = (int) meanCapacity * transmitters.size();
    }

    /**
     * Override this if things can have variable capacity along the network.
     *
     * @return An 'average' value of capacity. Calculate it how you will.
     */
    protected synchronized void updateMeanCapacity() {
        if (transmitters.size() > 0) {
            meanCapacity = transmitters.iterator().next().getCapacity();
        } else {
            meanCapacity = 0;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public World getWorld() {
        return worldObj;
    }

    public void tick() {
        onUpdate();
    }

    public void onUpdate() {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            Iterator<DelayQueue> i = updateQueue.iterator();

            try {
                while (i.hasNext()) {
                    DelayQueue q = i.next();

                    if (q.delay > 0) {
                        q.delay--;
                    } else {
                        transmittersAdded.addAll(transmitters);
                        updateDelay = 1;
                        i.remove();
                    }
                }
            } catch (Exception e) {
            }

            if (updateDelay > 0) {
                updateDelay--;

                if (updateDelay == 0) {
                    MinecraftForge.EVENT_BUS.post(new TransmittersAddedEvent(this, firstUpdate, (Collection) transmittersAdded));
                    firstUpdate = false;
                    transmittersAdded.clear();
                    needsUpdate = true;
                }
            }
        }
    }

    @Override
    public boolean needsTicks() {
        return getSize() > 0;
    }

    @Override
    public void clientTick() {
    }

    public void queueClientUpdate(Collection<IGridTransmitter<A, N>> newTransmitters) {
        transmittersAdded.addAll(newTransmitters);
        updateDelay = 3;
    }

    public void addUpdate(EntityPlayer player) {
        updateQueue.add(new DelayQueue(player));
    }

    public static class TransmittersAddedEvent extends Event {
        public DynamicNetwork<?, ?> network;
        public boolean newNetwork;
        public Collection<IGridTransmitter> newTransmitters;

        public TransmittersAddedEvent(DynamicNetwork net, boolean newNet, Collection<IGridTransmitter> added) {
            network = net;
            newNetwork = newNet;
            newTransmitters = added;
        }
    }

    public static class ClientTickUpdate extends Event {
        public DynamicNetwork network;
        public byte operation; /*0 remove, 1 add*/

        public ClientTickUpdate(DynamicNetwork net, byte b) {
            network = net;
            operation = b;
        }
    }

    public static class NetworkClientRequest extends Event {
        public TileEntity tileEntity;

        public NetworkClientRequest(TileEntity tile) {
            tileEntity = tile;
        }
    }

    public static class DelayQueue {
        public EntityPlayer player;
        public int delay;

        public DelayQueue(EntityPlayer p) {
            player = p;
            delay = 5;
        }

        @Override
        public int hashCode() {
            return player.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof DelayQueue && ((DelayQueue) o).player.equals(this.player);
        }
    }
}