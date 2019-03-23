/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.caps.abilities;

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

import static com.sofodev.armorplus.config.ModConfig.Experimental.enableExperimentalMode;
import static com.sofodev.armorplus.util.Utils.setRL;

public class AbilityDataHandler {

    // The Capability field. Used for checks and references.
    // Initialized when forge registers the capability.
    @CapabilityInject(IAbilityHandler.class)
    public static final Capability<IAbilityHandler> CAPABILITY_ABILITIES = null;

    // Handles all of the required registration for the capability.
    public static void register() {
        CapabilityManager.INSTANCE.register(IAbilityHandler.class, new Storage(), new Factory());
        MinecraftForge.EVENT_BUS.register(new AbilityDataHandler());
    }

    // Allows the provider to be attached to a target entity.
    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {

        if (enableExperimentalMode && event.getObject().getItem() instanceof ISpecialItem) {
            event.addCapability(setRL("abilities"), new Provider());
        }
    }

    // Simple wrapper to get the handler from an entity.
    public static IAbilityHandler getHandler(ItemStack stack) {
        if (enableExperimentalMode && stack.getItem() instanceof ISpecialItem) {
            return stack.hasCapability(CAPABILITY_ABILITIES, null) ? stack.getCapability(CAPABILITY_ABILITIES, null) : null;
        } else return null;
    }

    // The basic properties for the new capability.
    public interface IAbilityHandler {

        byte getLimit();

        void setLimit(byte limit);

        List<AbilityData> getAbilities();

        void setAbilities(List<AbilityData> ability);

        void addAbility(AbilityData ability);

        void removeAbility(AbilityData ability);
    }

    // The default implementation of the capability. Holds all the logic.
    public static class DefaultAbilityData extends IForgeRegistryEntry.Impl<DefaultAbilityData> implements IAbilityHandler {

        private byte limit = 0;
        private ArrayList<AbilityData> abilityList = new ArrayList<>();

        @Override
        public byte getLimit() {
            return this.limit;
        }

        @Override
        public void setLimit(byte limit) {
            this.limit = limit;
        }

        @Override
        public List<AbilityData> getAbilities() {
            return this.abilityList;
        }

        @Override
        public void setAbilities(List<AbilityData> abilities) {
            this.abilityList.clear();
            this.abilityList.addAll(abilities);
        }

        @Override
        public void addAbility(AbilityData ability) {
            if (abilityList.size() < limit && !abilityList.contains(ability)) {
                this.abilityList.add(ability);
            }
        }

        @Override
        public void removeAbility(AbilityData ability) {
            if (!this.abilityList.isEmpty()) {
                this.abilityList.remove(ability);
            }
        }
    }

    // Handles the actual read/write of the nbt.
    public static class Storage implements Capability.IStorage<IAbilityHandler> {

        @Override
        public NBTBase writeNBT(Capability<IAbilityHandler> capability, IAbilityHandler instance, EnumFacing side) {

            final NBTTagCompound tag = new NBTTagCompound();
            if (instance != null) {
                NBTTagList tagList = new NBTTagList();
                List<AbilityData> abilityDataList = instance.getAbilities();
                List<String> ability = abilityDataList.stream().map(AbilityData::getSafeName).collect(Collectors.toList());
                ability.stream().map(NBTTagString::new).forEach(tagList::appendTag);
                tag.setTag("abilityList", tagList);
                tag.setByte("limit", instance.getLimit());
            }
            return tag;
        }

        @Override
        public void readNBT(Capability<IAbilityHandler> capability, IAbilityHandler instance, EnumFacing side, NBTBase nbt) {

            final NBTTagCompound tag = (NBTTagCompound) nbt;

            NBTTagList tagList = tag.getTagList("abilityList", 8);
            List<AbilityData> abilityDataList = IntStream.range(0, tagList.tagCount()).mapToObj(tagList::getStringTagAt).map(AbilityData::getData).collect(Collectors.toList());
            instance.setAbilities(abilityDataList);
            instance.setLimit(tag.getByte("limit"));
        }
    }

    // Delegates all of the system calls to the capability.
    @SuppressWarnings("all")
    public static class Provider implements ICapabilitySerializable<NBTTagCompound> {

        IAbilityHandler instance = CAPABILITY_ABILITIES.getDefaultInstance();

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return capability.equals(CAPABILITY_ABILITIES);
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            return hasCapability(capability, facing) ? CAPABILITY_ABILITIES.<T>cast(instance) : null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return (NBTTagCompound) CAPABILITY_ABILITIES.getStorage().writeNBT(CAPABILITY_ABILITIES, instance, null);
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            CAPABILITY_ABILITIES.getStorage().readNBT(CAPABILITY_ABILITIES, instance, null, nbt);
        }
    }

    public static class Factory implements Callable<IAbilityHandler> {

        @Override
        public IAbilityHandler call() throws Exception {
            return new DefaultAbilityData();
        }
    }
}
