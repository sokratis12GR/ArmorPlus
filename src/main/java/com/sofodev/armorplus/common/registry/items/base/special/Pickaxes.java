package com.sofodev.armorplus.common.registry.items.base.special;

import com.sofodev.armorplus.api.properties.iface.IRepairable;
import com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.*;
import com.sofodev.armorplus.common.registry.ModBlocks;
import com.sofodev.armorplus.common.registry.ModItems;
import com.sofodev.armorplus.common.registry.items.base.ItemSpecialPickaxe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.Random;

import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.*;
import static com.sofodev.armorplus.common.registry.items.base.ItemSpecialPickaxe.*;
import static net.minecraft.init.Blocks.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public enum Pickaxes implements IRepairable {
    COAL(getItemStack(COAL_BLOCK), COAL_PICKAXE, coal),
    LAPIS(getItemStack(LAPIS_BLOCK), LAPIS_PICKAXE, lapis),
    REDSTONE(getItemStack(REDSTONE_BLOCK), REDSTONE_PICKAXE, redstone),
    EMERALD(getItemStack(EMERALD_BLOCK), EMERALD_PICKAXE, emerald),
    OBSIDIAN(getItemStack(ModBlocks.blockCompressedObsidian), OBSIDIAN_PICKAXE, obsidian),
    INFUSED_LAVA(getItemStack(ModItems.itemLavaCrystal, 1), INFUSED_LAVA_PICKAXE, lava, new String[]{"Smelts blocks to their corresponding smelted form", "Uses Furnace recipes"}),
    GUARDIAN(getItemStack(ModItems.materials, 1), GUARDIAN_PICKAXE, guardian, new String[]{"While underwater:", "Increases mining speed"}) {
        @Override
        public void onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
            ItemSpecialPickaxe pickaxe = (ItemSpecialPickaxe) itemstack.getItem();
            final float initialDMG = (float) guardian.tools.pickaxe.efficiency;
            if (player.isInWater() && pickaxe.getDestroySpeed(itemstack, player.world.getBlockState(pos)) < initialDMG + 10.0f) {
                pickaxe.setEfficiency(initialDMG + 10.0f);
            } else {
                pickaxe.setEfficiency(initialDMG);
            }
        }
    },
    SUPER_STAR(getItemStack(ModItems.materials, 2), SUPER_STAR_PICKAXE, super_star, new String[]{"Has a chance to drop:", "Soul Stone when mining Sand", "Netherrack when mining Stone"}) {
        @Override
        public void onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase player) {
            if (state == STONE.getDefaultState() && checkMatch()) {
                player.entityDropItem(new ItemStack(NETHERRACK, 1), 0);
            }
            if (state == SAND.getDefaultState() && checkMatch()) {
                player.entityDropItem(new ItemStack(SOUL_SAND, 1), 0);
            }
        }
    },
    ENDER_DRAGON(getItemStack(ModItems.materials, 3), ENDER_DRAGON_PICKAXE, ender_dragon, new String[]{"Has a chance to drop:", "Ender Pearl(s) when mining Endstone"}) {
        @Override
        public void onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase player) {
            if (state == END_STONE.getDefaultState() && checkMatch()) {
                player.entityDropItem(new ItemStack(Items.ENDER_PEARL, 1), 0);
            }
        }
    },
    //ULTIMATE(),
    ;

    private static Random rand = new Random();
    private final ItemStack repairStack;
    private final Item.ToolMaterial material;
    private final TextFormatting formatting;
    private final boolean hasAbility;
    private final String[] abilityDesc;

    Pickaxes(ItemStack repairStackIn, Item.ToolMaterial material, OriginMaterial tools) {
        this(repairStackIn, material, tools, false, null);
    }

    Pickaxes(ItemStack repairStackIn, Item.ToolMaterial material, OriginMaterial tools, String[] abilityDesc) {
        this(repairStackIn, material, tools, true, abilityDesc);
    }

    Pickaxes(ItemStack repairStackIn, Item.ToolMaterial material, OriginMaterial tools, boolean hasAbility, String[] abilityDesc) {
        this.repairStack = repairStackIn;
        this.material = material;
        this.formatting = getValueByName(tools.tools.itemNameColor);
        this.hasAbility = hasAbility;
        this.abilityDesc = abilityDesc;
    }

    @Override
    public ItemStack getRepairStack() {
        return this.repairStack;
    }

    public Item.ToolMaterial getMaterial() {
        return material;
    }

    public boolean hasAbilities() {
        return hasAbility;
    }

    public String[] getAbilityDesc() {
        return abilityDesc;
    }

    public TextFormatting getFormatting() {
        return formatting;
    }

    public void onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase player) {
    }

    public void onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
    }

    private static boolean checkMatch() {
        return rand.nextInt(3) == 1;
    }

    public String getName() {
        return name().toLowerCase();
    }
}
