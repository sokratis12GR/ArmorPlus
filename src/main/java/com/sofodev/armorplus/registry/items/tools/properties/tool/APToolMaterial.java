package com.sofodev.armorplus.registry.items.tools.properties.tool;

import com.sofodev.armorplus.registry.items.APRarity;
import com.sofodev.armorplus.registry.items.extras.BuffInstance;
import com.sofodev.armorplus.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;
import java.util.function.Supplier;

import static com.sofodev.armorplus.config.ArmorPlusConfig.*;
import static com.sofodev.armorplus.registry.items.APRarity.OBSIDIAN;
import static com.sofodev.armorplus.registry.items.APRarity.*;
import static com.sofodev.armorplus.registry.items.extras.DeBuff.*;
import static com.sofodev.armorplus.registry.items.tools.APPickaxeItem.SMELTING_MAP;
import static com.sofodev.armorplus.registry.items.tools.properties.tool.APToolProperties.*;
import static net.minecraft.world.item.Items.NETHERRACK;
import static net.minecraft.world.item.Items.SOUL_SAND;
import static net.minecraft.world.level.block.Blocks.*;

public enum APToolMaterial implements IAPTool {
    COAL_MAT(COAL, COAL_PROP, () -> withBuffs(
            new BuffInstance(BLINDNESS, 0, 10)
    )) {
        @Override
        public MaterialConfig config() {
            return coalMaterial;
        }
    },
    REDSTONE_MAT(REDSTONE, REDSTONE_PROP, () -> withBuffs(
            new BuffInstance(MINING_FATIGUE, 1, 10)
    )) {
        @Override
        public MaterialConfig config() {
            return redstoneMaterial;
        }
    },
    LAPIS_MAT(LAPIS, LAPIS_PROP, () -> withBuffs(
            new BuffInstance(NAUSEA, 0, 10)
    )) {
        @Override
        public MaterialConfig config() {
            return lapisMaterial;
        }
    },
    EMERALD_MAT(EMERALD, EMERALD_PROP, () -> withBuffs(
            new BuffInstance(SLOWNESS, 0, 20)
    )) {
        @Override
        public MaterialConfig config() {
            return emeraldMaterial;
        }
    },
    OBSIDIAN_MAT(OBSIDIAN, OBSIDIAN_PROP, () -> withBuffs(
            new BuffInstance(WEAKNESS, 2, 20)
    )) {
        @Override
        public MaterialConfig config() {
            return obsidianMaterial;
        }
    },
    INFUSED_LAVA_MAT(INFUSED_LAVA, INFUSED_LAVA_PROP, () -> withBuffs(
            new BuffInstance(IGNITE)
    )) {
        @Override
        public void onBlockMined(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity player) {
            if (player instanceof Player) {
                SMELTING_MAP.forEach((block, item) -> {
                    if (block == state.getBlock()) {
                        world.destroyBlock(pos, false, player);
                        Utils.spawnAtLocation((Player) player, new ItemStack(item), pos);
                    }
                });
            }
        }

        @Override
        public MaterialConfig config() {
            return infusedLavaMaterial;
        }
    },
    GUARDIAN_MAT(GUARDIAN, GUARDIAN_PROP, () -> withBuffs(
            new BuffInstance(WEAKNESS, 1, 4),
            new BuffInstance(NAUSEA, 1, 20)
    )) {
        @Override
        public void onBlockMined(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity player) {
            //TODO: WIP (new guardian pickaxe effect)
        }

        @Override
        public MaterialConfig config() {
            return guardianMaterial;
        }
    },
    SUPER_STAR_MAT(SUPER_STAR, SUPER_STAR_PROP, () -> withBuffs(
            new BuffInstance(WITHER, 1, 4),
            new BuffInstance(GLOWING, 0, 20)
    )) {
        @Override
        public void onBlockMined(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity player) {
            if (state == STONE.defaultBlockState() && random.nextInt(3) == 1) {
                player.spawnAtLocation(new ItemStack(NETHERRACK, 1), 0);
            }
            if (state == SAND.defaultBlockState() && random.nextInt(3) == 1) {
                player.spawnAtLocation(new ItemStack(SOUL_SAND, 1), 0);
            }
        }

        @Override
        public MaterialConfig config() {
            return superStarMaterial;
        }
    },
    ENDER_DRAGON_MAT(ENDER_DRAGON, ENDER_DRAGON_PROP, () -> withBuffs(
            new BuffInstance(WITHER, 3, 4),
            new BuffInstance(SLOWNESS, 1, 20)
    )) {
        @Override
        public void onBlockMined(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity player) {
            if (state == END_STONE.defaultBlockState() && random.nextInt(3) == 1) {
                player.spawnAtLocation(new ItemStack(Items.ENDER_PEARL, 1), 0);
            }
        }

        @Override
        public MaterialConfig config() {
            return enderDragonMaterial;
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
        public void onBlockMined(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity player) {
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

        @Override
        public MaterialConfig config() {
            return slayerMaterial;
        }
    };

    public final Random random = new Random();
    private final Tier properties;
    private final Supplier<List<BuffInstance>> buffs;
    private final Rarity rarity;

    APToolMaterial(APRarity rarity, Tier properties, Supplier<List<BuffInstance>> buffs) {
        this(rarity.getRarity(), properties, buffs);
    }

    APToolMaterial(APRarity rarity, Tier properties) {
        this(rarity.getRarity(), properties, Collections::emptyList);
    }

    APToolMaterial(Rarity rarity, Tier properties, Supplier<List<BuffInstance>> buffs) {
        this.rarity = rarity;
        this.properties = properties;
        this.buffs = buffs;
    }

    private static List<BuffInstance> withBuffs(BuffInstance... buffs) {
        return Arrays.asList(buffs);
    }

    @Override
    public Tier get() {
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