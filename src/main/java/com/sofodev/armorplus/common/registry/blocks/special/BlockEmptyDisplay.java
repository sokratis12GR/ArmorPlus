package com.sofodev.armorplus.common.registry.blocks.special;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.common.iface.IModdedBlock;
import com.sofodev.armorplus.common.registry.blocks.BlockProperties;
import com.sofodev.armorplus.common.registry.blocks.base.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.sofodev.armorplus.common.util.Utils.setRL;

public class BlockEmptyDisplay extends BlockBase implements IModdedBlock {

    public static final BlockProperties PROPERTIES = new BlockProperties(1000.0f, 10.0f);

    public BlockEmptyDisplay() {
        super(Material.IRON, "empty_sword_display", PROPERTIES);
        this.setCreativeTab(ArmorPlus.tabArmorPlusBlocks);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(setRL("empty_sword_display"), "", "", 0, "normal");
    }
}
