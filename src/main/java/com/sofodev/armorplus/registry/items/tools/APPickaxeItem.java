package com.sofodev.armorplus.registry.items.tools;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.tools.properties.tool.IAPTool;
import com.sofodev.armorplus.registry.items.tools.properties.tool.Tool;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Rarity;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

import static com.sofodev.armorplus.registry.items.tools.properties.tool.APToolType.PICKAXE;

public class APPickaxeItem extends PickaxeItem implements Tool {

    public static Map<Block, IItemProvider> SMELTING_LIST = new HashMap<>();

    private final IAPTool mat;

    public APPickaxeItem(IAPTool mat) {
        super(mat.get(), (int) (mat.get().getAttackDamageBonus() + PICKAXE.getDmg()), PICKAXE.getAttackSpeed(), new Item.Properties().tab(ArmorPlus.AP_WEAPON_GROUP));
        this.mat = mat;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return mat.getRarity();
    }

    @Override
    public boolean mineBlock(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity player) {
        if (!player.level.isClientSide) {
            mat.onBlockMined(stack, world, state, pos, player);
        }
        return super.mineBlock(stack, world, state, pos, player);
    }

    @Override
    public IAPTool getMat() {
        return this.mat;
    }
}