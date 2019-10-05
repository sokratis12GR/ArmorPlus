package com.sofodev.armorplus.common.registry.items;

import com.sofodev.armorplus.common.registry.items.base.ItemBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemFragment extends ItemBase {

    private final Fragments fragment;

    public ItemFragment(Fragments fragment) {
        super(fragment.getName());
        this.fragment = fragment;
    }

    public EntityEntry getHolderEntry() {
        return ForgeRegistries.ENTITIES.getValue(fragment.getHolderRL());
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    public enum Fragments {
        DUSK(0, "villager_golem"),
        NOON(1, "wither"),
        MIDNIGHT(2, "ender_dragon"),
        DAWN(3, "elder_guardian");

        private final int index;
        private final ResourceLocation entityRL;

        Fragments(int index, String dropEntityRegName) {
            this.index = index;
            entityRL = new ResourceLocation(dropEntityRegName);
        }

        public int getIndex() {
            return index;
        }

        public ResourceLocation getHolderRL() {
            return entityRL;
        }

        public String getName() {
            return name().toLowerCase();
        }
    }
}
