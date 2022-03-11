package com.sofodev.armorplus.registry.items.materials;

import com.sofodev.armorplus.registry.items.APItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static net.minecraft.util.text.TextFormatting.GOLD;

/**
 * @author Sokratis Fotkatzikis
 **/
public class LavaCrystalItem extends APItem {

    private final boolean isInfused;

    private int[] burnTime = new int[]{20000, 22000};

    public LavaCrystalItem(boolean isInfused) {
        super(new Properties().fireResistant());
        this.isInfused = isInfused;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (!isInfused) {
            tooltip.add(translate("tooltip.armorplus.lava_crystal.how_to_infuse").setStyle(Style.EMPTY.withItalic(true).withColor(Color.parseColor("#670067"))));
        } else
            tooltip.add(translate("tooltip.armorplus.lava_crystal.lore").setStyle(Style.EMPTY.withItalic(true).withColor(Color.parseColor("#670067"))));
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    /**
     * We use this method to `infuse` lava crystals to infused lava crystals.
     * <p>
     * We just check if the entity item of the lava crystal is inside lava, and if its not the infused variant
     * and then we drop a new entity item that contains the infused lava crystal with the same stack size as the last
     * entity item's item size, then we set the old's entity size to 0 so it "despawns" safely.
     */
    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        boolean isInLava = entity.getCommandSenderWorld().getFluidState(entity.blockPosition()).getFluidState().is(FluidTags.LAVA);
        if (!isInfused && isInLava) {
            entity.spawnAtLocation(new ItemStack(getAPItem("infused_lava_crystal"), entity.getItem().getCount()));
            entity.getItem().setCount(0);
            return true;
        }
        return false;
    }

    @Override
    public boolean canBeHurtBy(DamageSource damageSource) {
        return super.canBeHurtBy(damageSource);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.create("Lava Crystalic", GOLD);
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return isInfused ? burnTime[1] : burnTime[0];
    }

    /**
     * @return True, If the crystal is infused otherwise False.
     */
    public boolean isInfused() {
        return isInfused;
    }

}