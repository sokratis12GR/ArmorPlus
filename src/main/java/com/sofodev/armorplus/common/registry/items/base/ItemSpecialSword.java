/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.items.base;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.common.iface.IModdedItem;
import com.sofodev.armorplus.common.registry.ModBlocks;
import com.sofodev.armorplus.common.registry.blocks.special.decorative.BlockSwordDisplay;
import com.sofodev.armorplus.common.registry.items.base.special.Swords;
import com.sofodev.armorplus.common.util.ArmorPlusItemUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.thedragonlib.util.LogHelper;

import javax.annotation.Nonnull;
import java.util.List;

import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.*;
import static com.sofodev.armorplus.common.util.Utils.setName;
import static com.sofodev.armorplus.common.util.Utils.setRL;
import static net.minecraftforge.common.util.EnumHelper.addToolMaterial;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ItemSpecialSword extends ItemSword implements IModdedItem {

    public static final ToolMaterial COAL_SWORD = addToolMaterial("COAL_SWORD", 1, coal.weapons.sword.durability, 1.0F, (float) coal.weapons.sword.damage, 15);
    public static final ToolMaterial LAPIS_SWORD = addToolMaterial("LAPIS_SWORD", 1, lapis.weapons.sword.durability, 1.0F, (float) lapis.weapons.sword.damage, 30);
    public static final ToolMaterial REDSTONE_SWORD = addToolMaterial("REDSTONE_SWORD", 1, redstone.weapons.sword.durability, 1.0F, (float) redstone.weapons.sword.damage, 20);
    public static final ToolMaterial EMERALD_SWORD = addToolMaterial("EMERALD_SWORD", 1, emerald.weapons.sword.durability, 1.0F, (float) emerald.weapons.sword.damage, 20);
    public static final ToolMaterial OBSIDIAN_SWORD = addToolMaterial("OBSIDIAN_SWORD", 1, obsidian.weapons.sword.durability, 1.0F, (float) obsidian.weapons.sword.damage, 20);
    public static final ToolMaterial INFUSED_LAVA_SWORD = addToolMaterial("INFUSED_LAVA_SWORD", 1, lava.weapons.sword.durability, 1.0F, (float) lava.weapons.sword.damage, 20);
    public static final ToolMaterial GUARDIAN_SWORD = addToolMaterial("GUARDIAN_SWORD", 1, guardian.weapons.sword.durability, 1.0F, (float) guardian.weapons.sword.damage, 30);
    public static final ToolMaterial SUPER_STAR_SWORD = addToolMaterial("SUPER_STAR_SWORD", 1, super_star.weapons.sword.durability, 1.0F, (float) super_star.weapons.sword.damage, 20);
    public static final ToolMaterial ENDER_DRAGON_SWORD = addToolMaterial("ENDER_DRAGON_SWORD", 1, ender_dragon.weapons.sword.durability, 1.0F, (float) ender_dragon.weapons.sword.damage, 20);
    public ItemStack itemExpert;
    public TextFormatting formatting;
    public List<String> effect;
    public String itemName;
    public Swords swords;

    public ItemSpecialSword(Swords swords) {
        super(swords.getToolMaterial());
        this.swords = swords;
        this.itemName = swords.getName();
        this.itemExpert = swords.getRepairStack();
        this.formatting = swords.getTextFormatting();
        this.effect = swords.getEffects();
        this.setRegistryName(setRL(swords.getName() + "_sword"));
        this.setTranslationKey(setName(swords.getName() + "_sword"));
        this.setCreativeTab(ArmorPlus.tabArmorPlusWeapons);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        swords.addInformation(tooltip);
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        return swords.hitEntity(stack, target, attacker);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (player == null) {
            return EnumActionResult.FAIL;
        }
        if (world.isRemote || player.isSneaking()) {
            return EnumActionResult.PASS;
        }
        ItemStack stack = player.getHeldItem(hand);
        Block block = world.getBlockState(pos).getBlock();
        if (block == ModBlocks.blockEmptyDisplay && hand == EnumHand.MAIN_HAND) {
            Block display = ForgeRegistries.BLOCKS.getValue(setRL(swords.getName() + "_sword_display"));
            if (display instanceof BlockSwordDisplay) {
                ((BlockSwordDisplay) display).setSword(new ItemStack(stack.getItem(), stack.getCount(), stack.getItemDamage()));
                IBlockState state = display.getDefaultState();
                world.setBlockState(pos, state, 3);
                LogHelper.debug("Object [BlockSwordDisplay] - " + display.getTranslationKey() + " has been placed");
                if (world.getBlockState(pos) == display.getDefaultState()) {
                    stack.shrink(1);
                    return EnumActionResult.SUCCESS;
                }
            }
        }
        return EnumActionResult.SUCCESS;
    }

    @Override
    @Nonnull
    public IRarity getForgeRarity(ItemStack stack) {
        return this.getRarity(formatting, "Special Sword");
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return ArmorPlusItemUtils.isItemRepairable(repair, itemExpert);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(swords.getName(), 0);
    }
}