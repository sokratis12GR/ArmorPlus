/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.materials;

import com.sofodev.armorplus.client.utils.ToolTipUtils;
import com.sofodev.armorplus.items.base.ItemBase;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static net.minecraft.util.text.TextFormatting.DARK_PURPLE;
import static net.minecraft.util.text.TextFormatting.ITALIC;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ItemLavaCrystal extends ItemBase {

    private LavaType type;

    public enum LavaType {
        NORMAL("", 20000),
        INFUSED("infused_", 25000);

        private final String name;
        private final int burnTime;

        LavaType(String name, int burnTime) {
            this.name = name;
            this.burnTime = burnTime;
        }

        public String getName() {
            return this.name + "lava_crystal";
        }
    }

    public ItemLavaCrystal(LavaType type, Properties properties) {
        super(properties);
        this.type = type;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        final KeyBinding keyBindSneak = Minecraft.getInstance().gameSettings.keyBindSneak;
        if (ToolTipUtils.isKeyDown()) {
            if (type == LavaType.NORMAL) {
                tooltip.add(new TextComponentString(DARK_PURPLE + ITALIC.toString() + new TextComponentTranslation("item.armorplus.lava_crystal.desc").getFormattedText()));
            } else if (type == LavaType.INFUSED) {
                tooltip.add(new TextComponentString(DARK_PURPLE + ITALIC.toString() + new TextComponentTranslation("item.armorplus.infused_lava_crystal.desc").getFormattedText()));
            }
        } else {
            ToolTipUtils.showInfo(tooltip, keyBindSneak, TextFormatting.GOLD);
        }
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        if (itemStack.getItem() == this) {
            return type.burnTime;
        }
        return 0;
    }
}