package com.sofodev.armorplus.registry.items.tools;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.tools.properties.tool.IAPTool;
import com.sofodev.armorplus.registry.items.tools.properties.tool.Tool;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Rarity;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sofodev.armorplus.config.ArmorPlusConfig.autoSmeltingInput;
import static com.sofodev.armorplus.config.ArmorPlusConfig.autoSmeltingOutput;
import static com.sofodev.armorplus.registry.items.tools.properties.tool.APToolType.PICKAXE;
import static com.sofodev.armorplus.utils.ToolTipUtils.addBuffInformation;

public class APPickaxeItem extends PickaxeItem implements Tool {

    public static Map<Block, IItemProvider> SMELTING_MAP = registerSmeltingMap();
    private final IAPTool mat;

    public APPickaxeItem(IAPTool mat) {
        super(mat.get(), (int) (mat.get().getAttackDamageBonus() + PICKAXE.getDmg()), PICKAXE.getAttackSpeed(), new Item.Properties().tab(ArmorPlus.AP_WEAPON_GROUP));
        this.mat = mat;
    }

    private static Map<Block, IItemProvider> registerSmeltingMap() {
        HashMap<Block, IItemProvider> map = new HashMap<>();
//        List<? extends String> input = asList("minecraft:iron_ore", "minecraft:gold_ore",
//                "minecraft:sand", "minecraft:sandstone", "minecraft:wet_sponge", "minecraft:clay", "minecraft:stone_bricks", "minecraft:cobblestone", "minecraft:stone",
//                "minecraft:acacia_log", "minecraft:birch_log", "minecraft:dark_oak_log", "minecraft:jungle_log", "minecraft:oak_log", "minecraft:spruce_log",
//                "minecraft:netherrack", "minecraft:ancient_debris", "minecraft:stone_bricks");
//        List<? extends String> output = asList("minecraft:iron_ingot", "minecraft:gold_ingot", "minecraft:glass", "minecraft:smooth_sandstone", "minecraft:sponge",
//                "minecraft:terracotta", "minecraft:cracked_stone_bricks", "minecraft:stone", "minecraft:stone",
//                "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal",
//                "minecraft:nether_brick", "minecraft:netherite_scrap", "minecraft:cracked_stone_bricks");
        List<? extends String> input = autoSmeltingInput.get();
        List<? extends String> output = autoSmeltingOutput.get();
        if (input.size() != output.size()) {
            throw new IllegalArgumentException("autoSmeltingInput and autoSmeltingOutput in config/ap_config.toml must have the same size!");
        }
        for (int i = 0; i < input.size(); i++) {
            String entryInput = input.get(i);
            String entryOutput = output.get(i);
            Block fromBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(entryInput));
            Item toItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(entryOutput));
            if (fromBlock != null && toItem != null) {
                map.put(fromBlock, toItem);
            } else {
                ArmorPlus.LOGGER.error("Block with the registry name: " + entryInput + " or Item with the registry name: " + entryOutput + " don't exist. Failed to add auto smelt recipe");
            }
        }
        return map;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return mat.getRarity();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        addBuffInformation(mat, tooltip, "on_hit", false, mat.config().enableWeaponEffects.get());
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public boolean mineBlock(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity player) {
        if (!player.level.isClientSide && mat.config().enableWeaponEffects.get()) {
            mat.onBlockMined(stack, world, state, pos, player);
        }
        return super.mineBlock(stack, world, state, pos, player);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level.isClientSide && mat.config().enableWeaponEffects.get()) {
            mat.getBuffInstances().get().forEach(instance -> instance.hitEntity(stack, target, attacker));
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public IAPTool getMat() {
        return this.mat;
    }
}