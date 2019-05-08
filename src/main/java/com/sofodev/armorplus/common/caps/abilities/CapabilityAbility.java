/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.caps.abilities;

import com.sofodev.armorplus.common.caps.abilities.data.AbilityData;
import com.sofodev.armorplus.common.caps.abilities.data.ISpecialItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.common.util.Utils.setRL;

public class CapabilityAbility {

    // The Capability field. Used for checks and references.
    // Initialized when forge registers the capability.
    @CapabilityInject(IArmorAbilityHandler.class)
    public static Capability<IArmorAbilityHandler> CAPABILITY_ABILITIES;

    // Handles all of the required registration for the capability.
    public static void register() {
        CapabilityManager.INSTANCE.register(IArmorAbilityHandler.class, new CapabilityAbility.Storage(), new Factory());
        MinecraftForge.EVENT_BUS.register(new CapabilityAbility());
    }

    // Simple wrapper to get the handler from an entity.
    public static IArmorAbilityHandler getHandler(ItemStack stack) {
        return stack.getCapability(CAPABILITY_ABILITIES).map(handler -> handler).orElseThrow(() -> new RuntimeException("No Capability"));
    }

    // // Allows the provider to be attached to a target entity.
    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {

       // if (event.getObject().getItem() instanceof ISpecialItem) {
       //     event.addCapability(setRL("armor_abilities"), new Provider(){});
       // }
        //     //   if (event.getObject().getItem() instanceof ItemSword) {
        //     //       event.addCapability(setRL("weapon_abilities"), new Provider());
        //     //   }
    }

    // The basic properties for the new capability.
    public interface IArmorAbilityHandler {

        byte getLimit();

        IArmorAbilityHandler setLimit(byte limit);

        /**
         * Returns the list of abilities currently stored.
         */
        List<AbilityData> getAbilities();

        IArmorAbilityHandler setAbilities(List<AbilityData> ability);

        /**
         * Adds an ability from the list.
         *
         * @param ability the ability that is going to be added.
         */
        IArmorAbilityHandler addAbility(AbilityData ability);

        /**
         * Removes an ability from the list.
         *
         * @param ability the ability that is going to be removed.
         */
        IArmorAbilityHandler removeAbility(AbilityData ability);
    }

    // The default implementation of the capability. Holds all the logic.
    public static class DefaultArmorAbilityData extends ForgeRegistryEntry<DefaultArmorAbilityData> implements IArmorAbilityHandler {
        public DefaultArmorAbilityData() {
        }

        public DefaultArmorAbilityData(byte limit) {
            this.limit = limit;
        }

        public DefaultArmorAbilityData(byte limit, List<AbilityData> abilityList) {
            this.limit = limit;
            this.abilityList = abilityList;
        }

        private byte limit;
        private List<AbilityData> abilityList = new ArrayList<>();

        @Override
        public byte getLimit() {
            return this.limit;
        }

        @Override
        public IArmorAbilityHandler setLimit(byte limit) {
            this.limit = limit;
            return this;
        }

        @Override
        public List<AbilityData> getAbilities() {
            return this.abilityList;
        }

        @Override
        public IArmorAbilityHandler setAbilities(List<AbilityData> abilities) {
            this.abilityList.clear();
            this.abilityList.addAll(abilities);
            return this;
        }

        @Override
        public IArmorAbilityHandler addAbility(AbilityData ability) {
            if (abilityList.size() < limit && !abilityList.contains(ability)) {
                this.abilityList.add(ability);
            }
            return this;
        }

        @Override
        public IArmorAbilityHandler removeAbility(AbilityData ability) {
            if (!this.abilityList.isEmpty()) {
                this.abilityList.remove(ability);
            }
            return this;
        }
    }

    public static class Storage implements Capability.IStorage<IArmorAbilityHandler> {
        @Override
        public INBTBase writeNBT(Capability<IArmorAbilityHandler> capability, IArmorAbilityHandler instance, EnumFacing side) {

            final NBTTagCompound tag = new NBTTagCompound();
            if (instance != null) {
                NBTTagList tagList = new NBTTagList();
                List<AbilityData> abilityDataList = instance.getAbilities();
                List<String> ability = abilityDataList.stream().map(AbilityData::getSafeName).collect(Collectors.toList());
                ability.stream().map(NBTTagString::new).forEach(tagList::add);
                tag.put("abilityList", tagList);
                tag.putByte("limit", instance.getLimit());
            }
            return tag;
        }

        @Override
        public void readNBT(Capability<IArmorAbilityHandler> capability, IArmorAbilityHandler instance, EnumFacing side, INBTBase nbt) {

            final NBTTagCompound tag = (NBTTagCompound) nbt;

            NBTTagList tagList = tag.getList("abilityList", 8);
            List<AbilityData> abilityDataList = IntStream.range(0, tagList.size()).mapToObj(tagList::getString).map(AbilityData::getData).collect(Collectors.toList());
            instance.setAbilities(abilityDataList);
            instance.setLimit(tag.getByte("limit"));
        }
    }

    public static class Provider implements ICapabilitySerializable<NBTTagCompound> {

        LazyOptional<IArmorAbilityHandler> instance = LazyOptional.of(() -> new DefaultArmorAbilityData());

        @Nonnull
        @Override
        @SuppressWarnings("unchecked")
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable EnumFacing side) {
            if (cap == CAPABILITY_ABILITIES)
                return (LazyOptional<T>) instance;
            return LazyOptional.empty();
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return (NBTTagCompound) CAPABILITY_ABILITIES.getStorage().writeNBT(CAPABILITY_ABILITIES, instance.orElseThrow(() -> new RuntimeException("No Capability")), null);
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            CAPABILITY_ABILITIES.getStorage().readNBT(CAPABILITY_ABILITIES, instance.orElseThrow(() -> new RuntimeException("No Capability")), null, nbt);
        }
    }

    public static class Factory implements Callable<IArmorAbilityHandler> {

        @Override
        public IArmorAbilityHandler call() {
            return new DefaultArmorAbilityData();
        }
    }
}
