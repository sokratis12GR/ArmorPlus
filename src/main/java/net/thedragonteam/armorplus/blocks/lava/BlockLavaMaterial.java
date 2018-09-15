package net.thedragonteam.armorplus.blocks.lava;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.base.BlockBase;
import net.thedragonteam.armorplus.blocks.base.ToolType;
import net.thedragonteam.armorplus.iface.IModdedBlock;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class BlockLavaMaterial extends BlockBase implements IModdedBlock {

    public BlockLavaMaterial(LavaMaterial type) {
        super(Material.ROCK, type.getName(), type.getResistance(), type.getHardness(), ToolType.PICKAXE, 3, 0.8f);
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
        LAVA_CRYSTAL("lava_crystal"),
        INFUSED_LAVA_CRYSTAL("infused_lava_crystal"),
        COMPRESSED_LAVA_CRYSTAL("compressed_lava_crystal"),
        COMPRESSED_INFUSED_LAVA_CRYSTAL("compressed_infused_lava_crystal"),
        LAVA_INFUSED_OBSIDIAN("lava_infused_obsidian", 2000.0f, 25.0F),;

        private final String name;
        private final float resistance;
        private final float hardness;

        LavaMaterial(String name, float resistance, float hardness) {
            this.name = name;
            this.resistance = resistance;
            this.hardness = hardness;
        }

        LavaMaterial(String name) {
            this("block_" + name, 1000.0F, 5.0F);
        }

        @Override
        public String getName() {
            return this.name;
        }

        public float getResistance() {
            return this.resistance;
        }

        public float getHardness() {
            return this.hardness;
        }
    }
}
