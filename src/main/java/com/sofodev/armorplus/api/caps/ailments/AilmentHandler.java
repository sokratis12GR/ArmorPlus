package com.sofodev.armorplus.api.caps.ailments;

import com.sofodev.armorplus.api.caps.abilities.ISpecialItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.common.config.ModConfig.Experimental.enableExperimentalMode;
import static com.sofodev.armorplus.common.util.Utils.setRL;

public class AilmentHandler {

    // The Capability field. Used for checks and references.
    // Initialized when forge registers the capability.
    @CapabilityInject(IAilment.class)
    public static final Capability<IAilment> CAPABILITY_AILMENTS = null;

    // Handles all of the required registration for the capability.
    public static void register() {
        CapabilityManager.INSTANCE.register(IAilment.class, new Storage(), new Factory());
        MinecraftForge.EVENT_BUS.register(new AilmentHandler());
    }

    // Allows the provider to be attached to a target entity.
    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {

        if (enableExperimentalMode && event.getObject().getItem() instanceof ISpecialItem) {
            event.addCapability(setRL("ailments"), new Provider());
        }
    }

    // Simple wrapper to get the handler from an entity.
    public static IAilment getHandler(ItemStack stack) {
        if (enableExperimentalMode && stack.getItem() instanceof ISpecialItem) {
            return stack.hasCapability(CAPABILITY_AILMENTS, null) ? stack.getCapability(CAPABILITY_AILMENTS, null) : null;
        } else return null;
    }

    // The basic properties for the new capability.
    public interface IAilment {

        List<Ailment> getAilments();

        void setAilments(List<Ailment> ability);

        void addAilment(Ailment ability);

        void removeAilment(Ailment ability);
    }

    // The default implementation of the capability. Holds all the logic.
    public static class DefaultAilment extends IForgeRegistryEntry.Impl<DefaultAilment> implements IAilment {

        private ArrayList<Ailment> ailments = new ArrayList<>();

        @Override
        public List<Ailment> getAilments() {
            return this.ailments;
        }

        @Override
        public void setAilments(List<Ailment> abilities) {
            this.ailments.clear();
            this.ailments.addAll(abilities);
        }

        @Override
        public void addAilment(Ailment ability) {
            if (!ailments.contains(ability)) {
                this.ailments.add(ability);
            }
        }

        @Override
        public void removeAilment(Ailment ability) {
            if (!this.ailments.isEmpty()) {
                this.ailments.remove(ability);
            }
        }
    }

    // Handles the actual read/write of the nbt.
    public static class Storage implements Capability.IStorage<IAilment> {

        @Override
        public NBTBase writeNBT(Capability<IAilment> capability, IAilment instance, EnumFacing side) {

            final NBTTagCompound tag = new NBTTagCompound();
            if (instance != null) {
                NBTTagList tagList = new NBTTagList();
                List<Ailment> ailments = instance.getAilments();
                List<String> ailment = ailments.stream().map(Ailment::getSafeName).collect(Collectors.toList());
                ailment.stream().map(NBTTagString::new).forEach(tagList::appendTag);
                tag.setTag("ailments", tagList);
            }
            return tag;
        }

        @Override
        public void readNBT(Capability<IAilment> capability, IAilment instance, EnumFacing side, NBTBase nbt) {

            final NBTTagCompound tag = (NBTTagCompound) nbt;

            NBTTagList tagList = tag.getTagList("ailments", 8);
            List<Ailment> ailments;
            int bound = tagList.tagCount();
            ailments = IntStream.range(0, bound).mapToObj(tagList::getStringTagAt).map(Ailment::getAilment).collect(Collectors.toList());
            instance.setAilments(ailments);
        }
    }

    // Delegates all of the system calls to the capability.
    @SuppressWarnings("all")
    public static class Provider implements ICapabilitySerializable<NBTTagCompound> {

        IAilment instance = CAPABILITY_AILMENTS.getDefaultInstance();

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return capability.equals(CAPABILITY_AILMENTS);
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            return hasCapability(capability, facing) ? CAPABILITY_AILMENTS.<T>cast(instance) : null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return (NBTTagCompound) CAPABILITY_AILMENTS.getStorage().writeNBT(CAPABILITY_AILMENTS, instance, null);
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            CAPABILITY_AILMENTS.getStorage().readNBT(CAPABILITY_AILMENTS, instance, null, nbt);
        }
    }

    public static class Factory implements Callable<IAilment> {

        @Override
        public IAilment call() throws Exception {
            return new DefaultAilment();
        }
    }
}
