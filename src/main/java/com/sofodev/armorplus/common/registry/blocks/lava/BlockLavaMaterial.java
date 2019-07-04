/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.blocks.lava;

import com.sofodev.armorplus.common.iface.IModdedBlock;
import com.sofodev.armorplus.common.registry.blocks.BlockProperties;
import com.sofodev.armorplus.common.registry.blocks.base.BlockBase;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.blocks;

/**
 * @author Sokratis Fotkatzikis
 */
public class BlockLavaMaterial extends BlockBase implements IModdedBlock {

    public BlockLavaMaterial(LavaMaterial type) {
        super(Material.ROCK, type.getName(), new BlockProperties(type.getResistance(), type.getHardness(), blocks.lava_material.props, 0.8f));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(0, "normal");
    }

    @Override
    @SuppressWarnings("deprecation")
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return MapColor.RED;
    }

    public enum LavaMaterial implements IStringSerializable {
        LAVA_CRYSTAL,
        INFUSED_LAVA_CRYSTAL,
        COMPRESSED_LAVA_CRYSTAL,
        COMPRESSED_INFUSED_LAVA_CRYSTAL,
        LAVA_INFUSED_OBSIDIAN(2000.0f, 25.0F),
        ;

        private final float resistance;
        private final float hardness;

        LavaMaterial(float resistance, float hardness) {
            this.resistance = resistance;
            this.hardness = hardness;
        }

        LavaMaterial() {
            this(1000.0F, 5.0F);
        }

        @Override
        public String getName() {
            return this == LavaMaterial.LAVA_INFUSED_OBSIDIAN ? this.name().toLowerCase() : "block_" + this.name().toLowerCase();
        }

        public float getResistance() {
            return this.resistance;
        }

        public float getHardness() {
            return this.hardness;
        }
    }
}
