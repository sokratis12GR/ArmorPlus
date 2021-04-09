package com.sofodev.armorplus.registry.items.tools.properties.tool;

import com.sofodev.armorplus.registry.items.APRarity;
import com.sofodev.armorplus.registry.items.extras.BuffInstance;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.Random;

import static com.sofodev.armorplus.registry.items.APRarity.OBSIDIAN;
import static com.sofodev.armorplus.registry.items.APRarity.*;
import static com.sofodev.armorplus.registry.items.extras.DeBuff.*;
import static com.sofodev.armorplus.registry.items.tools.properties.tool.APToolProperties.*;
import static net.minecraft.block.Blocks.*;

public enum APToolMaterial implements IAPTool {
    COAL_MAT(COAL, COAL_PROP, new BuffInstance(BLINDNESS, 0, 10)),
    REDSTONE_MAT(REDSTONE, REDSTONE_PROP, new BuffInstance(MINING_FATIGUE, 1, 10)),
    LAPIS_MAT(LAPIS, LAPIS_PROP, new BuffInstance(NAUSEA, 0, 10)),
    EMERALD_MAT(EMERALD, EMERALD_PROP, new BuffInstance(SLOWNESS, 0, 20)),
    OBSIDIAN_MAT(OBSIDIAN, OBSIDIAN_PROP, new BuffInstance(WEAKNESS, 2, 20)),
    INFUSED_LAVA_MAT(INFUSED_LAVA, INFUSED_LAVA_PROP, new BuffInstance(IGNITE)) {
        @Override
        public void onBlockMined(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity player) {
            //    ListIterator<ItemStack> iter = state.getBlock();
            //    while (iter.hasNext()) {
            //        ItemStack drop = iter.next();
            //        ItemStack furnace = FurnaceRecipes.checked()instance().getSmeltingResult(drop);
            //        ItemStack result;
            //        if (!furnace.isEmpty() && isNotForbidden(furnace)) {
            //            result = furnace;
            //        } else {
            //            result = ItemStack.EMPTY;
            //        }
            //        if (!result.isEmpty()) {
            //            convertDrop(tool, result, drop, iter);
            //        }
            //    }
            //TODO: Implement a list of all vanilla block furnace recipes.
        }
    },
    GUARDIAN_MAT(GUARDIAN, GUARDIAN_PROP, new BuffInstance(WEAKNESS, 1, 4), new BuffInstance(NAUSEA, 1, 20)) {
        @Override
        public void onBlockMined(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity player) {
            //TODO: WIP (new guardian pickaxe effect)
        }
    },
    SUPER_STAR_MAT(SUPER_STAR, SUPER_STAR_PROP, new BuffInstance(WITHER, 1, 4), new BuffInstance(GLOWING, 0, 20)) {
        @Override
        public void onBlockMined(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity player) {
            if (state == STONE.defaultBlockState() && random.nextInt(3) == 1) {
                player.spawnAtLocation(new ItemStack(NETHERRACK, 1), 0);
            }
            if (state == SAND.defaultBlockState() && random.nextInt(3) == 1) {
                player.spawnAtLocation(new ItemStack(SOUL_SAND, 1), 0);
            }
        }
    },
    ENDER_DRAGON_MAT(ENDER_DRAGON, ENDER_DRAGON_PROP, new BuffInstance(WITHER, 3, 4), new BuffInstance(SLOWNESS, 1, 20)) {
        @Override
        public void onBlockMined(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity player) {
            if (state == END_STONE.defaultBlockState() && random.nextInt(3) == 1) {
                player.spawnAtLocation(new ItemStack(Items.ENDER_PEARL, 1), 0);
            }
        }
    },
    SLAYER_MAT(SLAYER, SLAYER_PROP),
    ;
    //TODO: Implement Enhanced Vanilla Tools
    //TODO: Implement Soul Gathering System
    public final Random random = new Random();
    private final IItemTier properties;
    private final BuffInstance[] buffs;
    private final Rarity rarity;

    APToolMaterial(APRarity rarity, IItemTier properties, BuffInstance... buffs) {
        this(rarity.getRarity(), properties, buffs);
    }

    APToolMaterial(Rarity rarity, IItemTier properties, BuffInstance... buffs) {
        this.rarity = rarity;
        this.properties = properties;
        this.buffs = buffs;
    }

    @Override
    public IItemTier get() {
        return properties;
    }

    @Override
    public BuffInstance[] getBuffInstances() {
        return buffs;
    }

    @Override
    public String getName() {
        return this.name().toLowerCase().replace("_mat", "");
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public String toString() {
        return "APToolMaterial{" +
                "properties=" + properties +
                ", buffs=" + Arrays.toString(buffs) +
                ", rarity=" + rarity +
                '}';
    }
}