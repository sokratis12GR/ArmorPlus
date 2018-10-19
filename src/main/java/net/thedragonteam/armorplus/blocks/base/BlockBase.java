package net.thedragonteam.armorplus.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.blocks.BlockProperties;

import static net.thedragonteam.armorplus.util.Utils.setName;
import static net.thedragonteam.armorplus.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class BlockBase extends Block {

    public BlockBase(Material material, String name, BlockProperties prop) {
        super(material);
        this.setRegistryName(setRL(name));
        this.setTranslationKey(setName(name));
        this.setResistance(prop.getResistance());
        this.setHardness(prop.getHardness());
        this.setHarvestLevel(prop.getToolType().getTool(), prop.getHarvestLevel());
        this.setLightLevel(prop.getLightLevel());
        this.setLightOpacity(prop.getLightOpacity());
        if (prop.isUnbreakable()) this.setBlockUnbreakable();
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks);
    }
}