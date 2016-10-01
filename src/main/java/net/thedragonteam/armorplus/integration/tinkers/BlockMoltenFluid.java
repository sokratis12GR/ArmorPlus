/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.integration.tinkers;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import javax.annotation.Nonnull;

public class BlockMoltenFluid extends BlockFluidClassic {

    /**
     * @param fluid
     */
    public BlockMoltenFluid(Fluid fluid) {
        super(fluid, Material.LAVA);

        // setCreativeTab(TinkerRegistry.tabSmeltery);
    }

    @Nonnull
    @Override
    public String getUnlocalizedName() {
        final Fluid fluid = FluidRegistry.getFluid(this.fluidName);
        if (fluid != null)
            return fluid.getUnlocalizedName();
        return super.getUnlocalizedName();
    }
}