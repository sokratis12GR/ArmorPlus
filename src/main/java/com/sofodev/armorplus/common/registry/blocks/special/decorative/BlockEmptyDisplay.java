package com.sofodev.armorplus.common.registry.blocks.special.decorative;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.common.iface.IModdedBlock;
import com.sofodev.armorplus.common.registry.blocks.BlockProperties;
import com.sofodev.armorplus.common.registry.blocks.base.BlockBase;
import com.sofodev.armorplus.common.registry.constants.APItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;
import java.util.Random;

import static com.sofodev.armorplus.common.util.Utils.setRL;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class BlockEmptyDisplay extends BlockBase implements IModdedBlock {

    public static final BlockProperties PROPERTIES = new BlockProperties(1000.0f, 10.0f);
    protected static final AxisAlignedBB PEDESTAL_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);

    public BlockEmptyDisplay() {
        super(Material.IRON, "empty_sword_display", PROPERTIES);
        this.setCreativeTab(ArmorPlus.tabArmorPlusBlocks);
    }

    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        super.onEntityCollision(worldIn, pos, state, entityIn);
    }

    @Override
    public boolean requiresUpdates() {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return PEDESTAL_AABB;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(setRL("empty_sword_display"), "", "", 0, "normal");
    }
}
