package com.sofodev.armorplus.common.registry.items.base;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.client.utils.ToolTipUtils;
import com.sofodev.armorplus.common.iface.IModdedItem;
import com.sofodev.armorplus.common.registry.items.base.special.Pickaxes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import static com.sofodev.armorplus.client.utils.ToolTipUtils.showInfo;
import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.*;
import static com.sofodev.armorplus.common.util.Utils.setName;
import static net.minecraftforge.common.util.EnumHelper.addToolMaterial;

public class ItemSpecialPickaxe extends ItemPickaxe implements IModdedItem {

    public static final ToolMaterial COAL_PICKAXE = addToolMaterial("coalPickaxe", 1, coal.tools.pickaxe.durability, (float) coal.tools.pickaxe.efficiency, 1.0f, 10);
    public static final ToolMaterial LAPIS_PICKAXE = addToolMaterial("lapisPickaxe", 2, lapis.tools.pickaxe.durability, (float) lapis.tools.pickaxe.efficiency, 2.0f, 10);
    public static final ToolMaterial REDSTONE_PICKAXE = addToolMaterial("redstonePickaxe", 2, redstone.tools.pickaxe.durability, (float) redstone.tools.pickaxe.efficiency, 2.0f, 10);
    public static final ToolMaterial EMERALD_PICKAXE = addToolMaterial("emeraldPickaxe", 3, emerald.tools.pickaxe.durability, (float) emerald.tools.pickaxe.efficiency, 4.0f, 10);
    public static final ToolMaterial OBSIDIAN_PICKAXE = addToolMaterial("obsidianPickaxe", 2, obsidian.tools.pickaxe.durability, (float) obsidian.tools.pickaxe.efficiency, 3.0f, 22);
    public static final ToolMaterial INFUSED_LAVA_PICKAXE = addToolMaterial("infusedLavaPickaxe", 3, lava.tools.pickaxe.durability, (float) lava.tools.pickaxe.efficiency, 5.5f, 22);
    public static final ToolMaterial GUARDIAN_PICKAXE = addToolMaterial("guardianPickaxe", 4, guardian.tools.pickaxe.durability, (float) guardian.tools.pickaxe.efficiency, 6.5f, 30);
    public static final ToolMaterial SUPER_STAR_PICKAXE = addToolMaterial("superStarPickaxe", 4, super_star.tools.pickaxe.durability, (float) super_star.tools.pickaxe.efficiency, 7.5f, 30);
    public static final ToolMaterial ENDER_DRAGON_PICKAXE = addToolMaterial("enderDragonPickaxe", 4, ender_dragon.tools.pickaxe.durability, (float) ender_dragon.tools.pickaxe.efficiency, 8.5f, 30);

    public final Pickaxes pickaxes;
    private Random random = new Random();

    public ItemSpecialPickaxe(Pickaxes pickaxes) {
        super(pickaxes.getMaterial());
        this.pickaxes = pickaxes;
        this.setRegistryName(pickaxes.getName() + "_pickaxe");
        this.setTranslationKey(setName(pickaxes.getName() + "_pickaxe"));
        this.setCreativeTab(ArmorPlus.tabArmorPlusTools);
    }

    @Override
    public IRarity getForgeRarity(ItemStack stack) {
        return this.getRarity(pickaxes.getFormatting(), "Special Pickaxe");
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
        if (!player.world.isRemote) {
            pickaxes.onBlockStartBreak(itemstack, pos, player);
        }
        return super.onBlockStartBreak(itemstack, pos, player);
    }

    public void setEfficiency(float value) {
        this.efficiency = value;
    }

    public float getEfficiency() {
        return this.efficiency;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase player) {
        if (!world.isRemote) {
            pickaxes.onBlockDestroyed(stack, world, state, pos, player);
        }
        return super.onBlockDestroyed(stack, world, state, pos, player);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (pickaxes.hasAbilities()) {
            if (GameSettings.isKeyDown(keyBindSneak)) {
                ToolTipUtils.addToolTip(tooltip, pickaxes.getAbilityDesc());
                ;
            } else {
                showInfo(tooltip, keyBindSneak, pickaxes.getFormatting());
            }
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
        return super.canHarvestBlock(state, stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(pickaxes.getName(), 0);
    }
}
