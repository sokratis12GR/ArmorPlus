package com.sofodev.armorplus.common.registry.items;

import com.sofodev.armorplus.common.registry.items.base.ItemBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;
import net.minecraftforge.fml.common.registry.EntityEntry;

import javax.annotation.Nullable;
import java.util.List;

import static com.sofodev.armorplus.common.util.TextUtils.translatedText;
import static net.minecraft.util.text.TextFormatting.BLUE;
import static net.minecraftforge.fml.common.registry.ForgeRegistries.ENTITIES;

public class ItemFragment extends ItemBase {

    private final Fragments fragment;

    public ItemFragment(Fragments fragment) {
        super(fragment.getName());
        this.fragment = fragment;
    }

    public EntityEntry getHolderEntry() {
        return ENTITIES.getValue(fragment.getHolderRL());
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public IRarity getForgeRarity(ItemStack stack) {
        return getRarity(TextFormatting.DARK_RED, "fragment");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        String name = this.getHolderEntry().getName();
        if (name != null) {
            tooltip.add(String.format("%s%s %s", BLUE, translatedText("item.armorplus.fragment.info"), name));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
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
