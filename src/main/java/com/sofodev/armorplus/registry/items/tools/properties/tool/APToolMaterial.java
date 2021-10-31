package com.sofodev.armorplus.registry.items.tools.properties.tool;

import com.sofodev.armorplus.registry.items.APRarity;
import com.sofodev.armorplus.registry.items.extras.BuffInstance;
import com.sofodev.armorplus.utils.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.*;
import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.config;
import static com.sofodev.armorplus.registry.items.APRarity.OBSIDIAN;
import static com.sofodev.armorplus.registry.items.APRarity.*;
import static com.sofodev.armorplus.registry.items.extras.DeBuff.*;
import static com.sofodev.armorplus.registry.items.tools.APPickaxeItem.SMELTING_MAP;
import static com.sofodev.armorplus.registry.items.tools.properties.tool.APToolProperties.*;
import static net.minecraft.block.Blocks.*;

public enum APToolMaterial implements IAPTool {
    COAL_MAT(COAL, COAL_PROP, () -> withBuffs(
            new BuffInstance(BLINDNESS, 0, 10)
    )),
    REDSTONE_MAT(REDSTONE, REDSTONE_PROP, () -> withBuffs(
            new BuffInstance(MINING_FATIGUE, 1, 10)
    )),
    LAPIS_MAT(LAPIS, LAPIS_PROP, () -> withBuffs(
            new BuffInstance(NAUSEA, 0, 10)
    )),
    EMERALD_MAT(EMERALD, EMERALD_PROP, () -> withBuffs(
            new BuffInstance(SLOWNESS, 0, 20)
    )),
    OBSIDIAN_MAT(OBSIDIAN, OBSIDIAN_PROP, () -> withBuffs(
            new BuffInstance(WEAKNESS, 2, 20)
    )),
    INFUSED_LAVA_MAT(INFUSED_LAVA, INFUSED_LAVA_PROP, () -> withBuffs(
            new BuffInstance(IGNITE)
    )) {
        @Override
        public void onBlockMined(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity player) {
            if (player instanceof PlayerEntity && config.infusedLavaMaterial.enableWeaponEffects.get()) {
                SMELTING_MAP.forEach((block, item) -> {
                    if (block == state.getBlock()) {
                        world.destroyBlock(pos, false, player);
                        Utils.spawnAtLocation((PlayerEntity) player, new ItemStack(item), pos);
                    }
                });
            }
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
    GUARDIAN_MAT(GUARDIAN, GUARDIAN_PROP, () -> withBuffs(
            new BuffInstance(WEAKNESS, 1, 4),
            new BuffInstance(NAUSEA, 1, 20)
    )) {
        @Override
        public void onBlockMined(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity player) {
            //TODO: WIP (new guardian pickaxe effect)
        }
    },
    SUPER_STAR_MAT(SUPER_STAR, SUPER_STAR_PROP, () -> withBuffs(
            new BuffInstance(WITHER, 1, 4),
            new BuffInstance(GLOWING, 0, 20)
    )) {
        @Override
        public void onBlockMined(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity player) {
            if (config.superStarMaterial.enableWeaponEffects.get()) {
                if (state == STONE.defaultBlockState() && random.nextInt(3) == 1) {
                    player.spawnAtLocation(new ItemStack(NETHERRACK, 1), 0);
                }
                if (state == SAND.defaultBlockState() && random.nextInt(3) == 1) {
                    player.spawnAtLocation(new ItemStack(SOUL_SAND, 1), 0);
                }
            }
        }
    },
    ENDER_DRAGON_MAT(ENDER_DRAGON, ENDER_DRAGON_PROP, () -> withBuffs(
            new BuffInstance(WITHER, 3, 4),
            new BuffInstance(SLOWNESS, 1, 20)
    )) {
        @Override
        public void onBlockMined(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity player) {
            if (config.enderDragonMaterial.enableWeaponEffects.get()) {
                if (state == END_STONE.defaultBlockState() && random.nextInt(3) == 1) {
                    player.spawnAtLocation(new ItemStack(Items.ENDER_PEARL, 1), 0);
                }
            }
        }
    },
    SLAYER_MAT(SLAYER, SLAYER_PROP, () -> withBuffs(
            new BuffInstance(WITHER, 3, 4),
            new BuffInstance(SLOWNESS, 1, 20),
            new BuffInstance(GLOWING, 0, 20),
            new BuffInstance(WEAKNESS, 1, 4),
            new BuffInstance(NAUSEA, 1, 20)
    )) {
        @Override
        public void onBlockMined(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity player) {
            if (state == END_STONE.defaultBlockState() && random.nextInt(3) == 1) {
                player.spawnAtLocation(new ItemStack(Items.ENDER_PEARL, 1), 0);
            }
            if (state == STONE.defaultBlockState() && random.nextInt(3) == 1) {
                player.spawnAtLocation(new ItemStack(NETHERRACK, 1), 0);
            }
            if (state == SAND.defaultBlockState() && random.nextInt(3) == 1) {
                player.spawnAtLocation(new ItemStack(SOUL_SAND, 1), 0);
            }
        }
    };

    public final Random random = new Random();
    private final IItemTier properties;
    private final Supplier<List<BuffInstance>> buffs;
    private final Rarity rarity;

    APToolMaterial(APRarity rarity, IItemTier properties, Supplier<List<BuffInstance>> buffs) {
        this(rarity.getRarity(), properties, buffs);
    }

    APToolMaterial(APRarity rarity, IItemTier properties) {
        this(rarity.getRarity(), properties, Collections::emptyList);
    }

    APToolMaterial(Rarity rarity, IItemTier properties, Supplier<List<BuffInstance>> buffs) {
        this.rarity = rarity;
        this.properties = properties;
        this.buffs = buffs;
    }

    private static List<BuffInstance> withBuffs(BuffInstance... buffs) {
        return Arrays.asList(buffs);
    }

    @Override
    public IItemTier get() {
        return properties;
    }

    @Override
    public Supplier<List<BuffInstance>> getBuffInstances() {
        return buffs;
    }

    @Override
    public String getName() {
        return this.name().toLowerCase(Locale.ENGLISH).replace("_mat", "");
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public String toString() {
        return "APToolMaterial{" +
                "random=" + random +
                ", properties=" + properties +
                ", buffs=" + buffs +
                ", rarity=" + rarity +
                '}';
    }
}