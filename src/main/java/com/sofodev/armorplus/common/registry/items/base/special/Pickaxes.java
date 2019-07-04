package com.sofodev.armorplus.common.registry.items.base.special;

import com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.*;
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

public enum Pickaxes {
    COAL(COAL_PICKAXE, coal),
    LAPIS(LAPIS_PICKAXE, lapis),
    REDSTONE(REDSTONE_PICKAXE, redstone),
    EMERALD(EMERALD_PICKAXE, emerald),
    OBSIDIAN(OBSIDIAN_PICKAXE, obsidian),
    INFUSED_LAVA(INFUSED_LAVA_PICKAXE, lava, new String[]{"Smelts blocks to their corresponding smelted form", "Uses Furnace & Lava Infuser recipes"}),
    GUARDIAN(GUARDIAN_PICKAXE, guardian, new String[]{"While underwater:", "Increases mining speed"}) {
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
    SUPER_STAR(SUPER_STAR_PICKAXE, super_star, new String[]{"Has a chance to drop:", "Soul Stone when mining Sand", "Netherrack when mining Stone"}) {
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
    ENDER_DRAGON(ENDER_DRAGON_PICKAXE, ender_dragon, new String[]{"Has a chance to drop:", "Ender Pearl(s) when mining Endstone"}) {
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
    private final Item.ToolMaterial material;
    private final TextFormatting formatting;
    private final boolean hasAbility;
    private final String[] abilityDesc;

    Pickaxes(Item.ToolMaterial material, OriginMaterial tools) {
        this(material, tools, false, null);
    }

    Pickaxes(Item.ToolMaterial material, OriginMaterial tools, String[] abilityDesc) {
        this(material, tools, true, abilityDesc);
    }

    Pickaxes(Item.ToolMaterial material, OriginMaterial tools, boolean hasAbility, String[] abilityDesc) {
        this.material = material;
        this.formatting = getValueByName(tools.tools.itemNameColor);
        this.hasAbility = hasAbility;
        this.abilityDesc = abilityDesc;
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
