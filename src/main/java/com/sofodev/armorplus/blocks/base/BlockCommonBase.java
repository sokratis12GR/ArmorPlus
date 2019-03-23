/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks.base;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.blocks.BlockProperties;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockCommonBase extends Block {

    public BlockCommonBase(Material material, String name, BlockProperties prop) {
        super(material);
        this.setTranslationKey(Utils.setName(name));
        this.setResistance(prop.getResistance());
        this.setHardness(prop.getHardness());
        this.setHarvestLevel(prop.getToolType().getTool(), prop.isUnbreakable() ? -1 : prop.getHarvestLevel());
        this.setLightLevel(prop.getLightLevel());
        this.setLightOpacity(prop.getLightOpacity());
        //Sets block unbreakable if true
        if (prop.isUnbreakable()) {
            this.setBlockUnbreakable();
        }
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks);
    }
}