package com.sofodev.armorplus.registry.blocks.crafting;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import static com.sofodev.armorplus.registry.ModBlocks.LAVA_INFUSER_TYPE;

public class LavaInfuserTile extends AbstractInfuserTile {

    public LavaInfuserTile() {
        super(LAVA_INFUSER_TYPE.get(), IRecipeType.SMELTING);
    }

    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.armorplus.lava_infuser");
    }

    protected Container createMenu(int id, PlayerInventory player) {
        return new FurnaceContainer(id, player, this, this.infuserData);
    }
}