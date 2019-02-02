/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.caps.abilities;

import com.sofodev.armorplus.items.armors.base.ItemArmorV2;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.util.Utils.setRL;

public class AbilityDataHandler {

    // The Capability field. Used for checks and references.
    // Initialized when forge registers the capability.
    @CapabilityInject(IAbilityHandler.class)
    public static final Capability<IAbilityHandler> CAPABILITY_ABILITIES = null;

    // Handles all of the required registration for the capability.
    public static void register() {
        CapabilityManager.INSTANCE.register(IAbilityHandler.class, new Storage(), DefaultAbilityHandler::new);
        MinecraftForge.EVENT_BUS.register(new AbilityDataHandler());
    }

    // Allows the provider to be attached to a target entity.
    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {

        if (event.getObject().getItem() instanceof ItemArmorV2) {
            event.addCapability(setRL("prototype"), new Provider());
        }
    }

    // Simple wrapper to get the handler from an entity.
    public static IAbilityHandler getHandler(ItemStack stack) {
        return stack.hasCapability(CAPABILITY_ABILITIES, null) ? stack.getCapability(CAPABILITY_ABILITIES, null) : null;
    }

    // The basic properties for the new capability.
    public interface IAbilityHandler {

        int getLimit();

        void setLimit(int limit);

        List<String> getAbilities();

        void setAbilities(List<String> ability);

        void addAbility(String ability);

        void removeAbility(String ability);
    }

    // The default implementation of the capability. Holds all the logic.
    public static class DefaultAbilityHandler implements IAbilityHandler {

        private int limit = 0;
        private ArrayList<String> abilities = new ArrayList<>();

        @Override
        public int getLimit() {
            return this.limit;
        }

        @Override
        public void setLimit(int limit) {
            this.limit = limit;
        }

        public List<String> getAbilities() {
            return this.abilities;
        }

        public void setAbilities(List<String> abilities) {
            this.abilities.clear();
            this.abilities.addAll(abilities);
        }

        @Override
        public void addAbility(String ability) {
            if (abilities.size() < limit && !abilities.contains(ability)) {
                this.abilities.add(ability);
            }
        }

        @Override
        public void removeAbility(String ability) {
            if (!this.abilities.isEmpty()) {
                this.abilities.remove(ability);
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
                List<String> abilities = instance.getAbilities();
                abilities.stream().map(NBTTagString::new).forEach(tagList::appendTag);
                tag.setTag("abilities", tagList);
                tag.setInteger("limit", instance.getLimit());
            }
            return tag;
        }

        @Override
        public void readNBT(Capability<IAbilityHandler> capability, IAbilityHandler instance, EnumFacing side, NBTBase nbt) {

            final NBTTagCompound tag = (NBTTagCompound) nbt;

            NBTTagList tagList = tag.getTagList("abilities", 8);
            List<String> abilities = IntStream.range(0, tagList.tagCount()).mapToObj(tagList::getStringTagAt).collect(Collectors.toList());
            instance.setAbilities(abilities);
            instance.setLimit(tag.getInteger("limit"));
        }
    }

    // Delegates all of the system calls to the capability.
    public static class Provider implements ICapabilitySerializable<NBTTagCompound> {

        IAbilityHandler instance = CAPABILITY_ABILITIES.getDefaultInstance();

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return capability == CAPABILITY_ABILITIES;
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

}
