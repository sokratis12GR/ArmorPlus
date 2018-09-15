package net.thedragonteam.armorplus.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.thedragonteam.armorplus.ArmorPlus;

import static net.thedragonteam.armorplus.util.Utils.setName;
import static net.thedragonteam.armorplus.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class BlockBase extends Block {

    public BlockBase(String name) {
        this(Material.GROUND, name);
    }

    public BlockBase(Material material, String name) {
        this(material, name, false);
    }

    public BlockBase(Material material, String name, boolean unbreakable) {
        this(material, name, 0.0f, unbreakable);
    }

    public BlockBase(Material material, String name, float resistance) {
        this(material, name, resistance, false);
    }

    public BlockBase(Material material, String name, float resistance, boolean unbreakable) {
        this(material, name, resistance, 0.0f, unbreakable);
    }

    public BlockBase(Material material, String name, float resistance, float hardness) {
        this(material, name, resistance, hardness, false);
    }

    public BlockBase(Material material, String name, float resistance, float hardness, boolean unbreakable) {
        this(material, name, resistance, hardness, ToolType.PICKAXE, unbreakable);
    }

    public BlockBase(Material material, String name, float resistance, float hardness, ToolType tool) {
        this(material, name, resistance, hardness, tool, false);
    }

    public BlockBase(Material material, String name, float resistance, float hardness, ToolType tool, boolean unbreakable) {
        this(material, name, resistance, hardness, tool, 0, unbreakable);
    }

    public BlockBase(Material material, String name, float resistance, float hardness, ToolType tool, int harvestLevel) {
        this(material, name, resistance, hardness, tool, harvestLevel, false);
    }

    public BlockBase(Material material, String name, float resistance, float hardness, ToolType tool, int harvestLevel, boolean unbreakable) {
        this(material, name, resistance, hardness, tool, harvestLevel, 0.0f, unbreakable);
    }

    public BlockBase(Material material, String name, float resistance, float hardness, ToolType tool, int harvestLevel, float lightLevel) {
        this(material, name, resistance, hardness, tool, harvestLevel, lightLevel, false);
    }

    public BlockBase(Material material, String name, float resistance, float hardness, ToolType tool, int harvestLevel, float lightLevel, boolean unbreakable) {
        this(material, name, resistance, hardness, tool, harvestLevel, lightLevel, 0, unbreakable);
    }

    public BlockBase(Material material, String name, float resistance, float hardness, ToolType tool, int harvestLevel, float lightLevel, int lightOpacity) {
        this(material, name, resistance, hardness, tool, harvestLevel, lightLevel, lightOpacity, false);
    }

    public BlockBase(Material material, String name, float resistance, float hardness, ToolType tool, int harvestLevel, float lightLevel, int lightOpacity, boolean unbreakable) {
        super(material);
        this.setRegistryName(setRL(name));
        this.setTranslationKey(setName(name));
        this.setResistance(resistance);
        this.setHardness(hardness);
        this.setHarvestLevel(tool.getTool(), harvestLevel);
        this.setLightLevel(lightLevel);
        this.setLightOpacity(lightOpacity);
        if (unbreakable) this.setBlockUnbreakable();
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks);
    }
}