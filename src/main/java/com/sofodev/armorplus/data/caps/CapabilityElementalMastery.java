package com.sofodev.armorplus.data.caps;

import com.sofodev.armorplus.registry.items.special.Element;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
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
import java.util.Locale;

import static com.sofodev.armorplus.utils.Utils.setRL;

public class CapabilityElementalMastery {

    @CapabilityInject(IElementalMastery.class)
    public static Capability<IElementalMastery> CAPABILITY_ELEMENTAL_MASTERY;

    public static void register() {
        CapabilityManager.INSTANCE.register(IElementalMastery.class, new Capability.IStorage<IElementalMastery>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<IElementalMastery> capability, IElementalMastery instance, Direction side) {
                throw new RuntimeException("Use com.sofodev.armorplus.data.caps.CapabilityElementalMastery.Provider instead");
            }

            @Override
            public void readNBT(Capability<IElementalMastery> capability, IElementalMastery instance, Direction side, INBT nbt) {
                throw new RuntimeException("Use com.sofodev.armorplus.data.caps.CapabilityElementalMastery.Provider instead");
            }
        }, Implementation::new);
        MinecraftForge.EVENT_BUS.register(new CapabilityElementalMastery());
    }

    public static IElementalMastery getHandler(ItemStack stack) {
        return stack.getCapability(CAPABILITY_ELEMENTAL_MASTERY).map(handler -> handler).orElseThrow(() -> new RuntimeException("No Capability"));
    }

    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof PlayerEntity)
            event.addCapability(setRL("elemental_mastery"), new Provider());
    }

    public interface IElementalMastery {
        /**
         * Returns the primary elemental affinity that the user possess.
         */
        Element getPrimary();

        /**
         * @param primary The elemental affinity that will be set as primary for the user
         * @return the instance of IElementalMastery containing the new primary affinity
         */
        IElementalMastery setPrimary(Element primary);

        /**
         * Returns the secondary elemental affinity that the user possess. (If any)
         */
        Element getSecondary();

        /**
         * @param secondary The elemental affinity that will be set as secondary for the user
         * @return the instance of IElementalMastery containing the new secondary affinity
         */
        IElementalMastery setSecondary(Element secondary);
    }

    public static class Implementation extends ForgeRegistryEntry<Implementation> implements IElementalMastery {
        public Implementation() {
        }

        public Implementation(Element primary) {
            this.primary = primary;
        }

        public Implementation(Element primary, Element secondary) {
            this.primary = primary;
            this.secondary = secondary;
        }

        private Element primary = Element.NONE;
        private Element secondary = Element.NONE;

        @Override
        public Element getPrimary() {
            return this.primary;
        }

        @Override
        public IElementalMastery setPrimary(Element primary) {
            if (this.primary != primary && primary != Element.NONE) {
                this.primary = primary;
            }
            return this;
        }

        @Override
        public Element getSecondary() {
            return this.secondary;
        }

        @Override
        public IElementalMastery setSecondary(Element secondary) {
            if (secondary != Element.NONE && this.primary != secondary) {
                this.secondary = secondary;
            }
            return this;
        }
    }

    public static class Provider implements ICapabilitySerializable<CompoundNBT> {

        LazyOptional<IElementalMastery> instance = LazyOptional.of(Implementation::new);

        @Override
        public CompoundNBT serializeNBT() {
            final CompoundNBT tag = new CompoundNBT();
            IElementalMastery mastery = instance.orElseThrow(() -> new RuntimeException("No Capability"));
            if (instance != null) {
                tag.putString("primary", mastery.getPrimary().getName());
                tag.putString("secondary", mastery.getSecondary().getName());
            }
            return tag;
        }

        @Override
        public void deserializeNBT(CompoundNBT nbt) {
            IElementalMastery mastery = instance.orElseThrow(() -> new RuntimeException("No Capability"));
            Element primary = Element.valueOf(nbt.getString("primary").toUpperCase(Locale.ENGLISH));
            Element secondary = Element.valueOf(nbt.getString("secondary").toUpperCase(Locale.ENGLISH));
            mastery.setPrimary(primary);
            mastery.setSecondary(secondary);
        }

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            if (cap == CAPABILITY_ELEMENTAL_MASTERY)
                return instance.cast();
            return LazyOptional.empty();
        }
    }

}