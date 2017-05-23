/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.base;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;

import javax.annotation.Nonnull;
import java.util.Random;

public class BaseEnergyBlock extends BlockContainer {

    private TileEntity tileEntity;

    public BaseEnergyBlock(Material material, String name, float resistance, float hardness, TileEntity tileEntityIn) {
        this(material, name, resistance, hardness, null, 0, tileEntityIn);
    }

    public BaseEnergyBlock(Material material, String name, float resistance, float hardness, String tool, int harvestLevel, TileEntity tileEntityIn) {
        this(material, name, resistance, hardness, tool, harvestLevel, 0F, tileEntityIn);
    }

    public BaseEnergyBlock(Material material, String name, float resistance, float hardness, String tool, int harvestLevel, float lightLevel, TileEntity tileEntityIn) {
        super(material);
        this.setUnlocalizedName(ArmorPlus.MODID + "." + name);
        this.setRegistryName(name);
        this.tileEntity = tileEntityIn;
        this.setResistance(resistance);
        this.setHardness(hardness);
        this.setHarvestLevel(tool, harvestLevel);
        this.setLightLevel(lightLevel);
        this.setCreativeTab(ArmorPlus.tabArmorplusBlocks);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullCube(IBlockState state) {
        return true;
    }

    @Override
    @Nonnull
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @Nonnull
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta) {
        return this.tileEntity;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Random random = new Random();
        int min = 0;
        int max = 1;
        //noinspection MethodCallSideOnly
        Entity entity = worldIn.getLoadedEntityList().get(random.nextInt());
        if (entity instanceof EntityCow || entity instanceof EntityPig || entity instanceof EntityChicken || entity instanceof EntityVillager) {
            EntityLightningBolt lightningBolt = new EntityLightningBolt(worldIn, entity.posX, entity.posY, entity.posZ, false);
            worldIn.spawnEntity(lightningBolt);//.setPositionAndUpdate(entity.posX, entity.posY, entity.posZ)
            Entity[] hostiles = new Entity[]{new EntityZombie(worldIn), new EntitySkeleton(worldIn)};
            worldIn.spawnEntity(hostiles[random.nextInt(max - min + 1) + min]);
            entity.setDead();
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}
