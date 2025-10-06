package com.sofodev.armorplus.registry.item.tool.properties.tool;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.config.ArmorPlusConfig.AdvancedMaterialConfig;
import com.sofodev.armorplus.config.ArmorPlusConfig.MaterialConfig;
import com.sofodev.armorplus.registry.item.APRarity;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.utils.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;
import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.SMELTING_MAP;
import static com.sofodev.armorplus.ArmorPlus.config;
import static com.sofodev.armorplus.registry.item.APRarity.*;
import static com.sofodev.armorplus.registry.item.APRarity.OBSIDIAN;
import static com.sofodev.armorplus.registry.item.extra.DeBuff.*;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.*;
import static net.minecraft.world.item.Items.NETHERRACK;
import static net.minecraft.world.item.Items.SOUL_SAND;
import static net.minecraft.world.level.block.Blocks.*;

public enum APToolMaterial implements IAPTool {
    COAL_MAT(COAL, COAL_PROP, () -> withBuffs(
            new BuffInstance(BLINDNESS, 0, 10)
    )) {
        @Override
        public MaterialConfig config() {
            return config.coalMaterial;
        }
    },
    REDSTONE_MAT(REDSTONE, REDSTONE_PROP, () -> withBuffs(
            new BuffInstance(MINING_FATIGUE, 1, 10)
    )) {
        @Override
        public MaterialConfig config() {
            return config.redstoneMaterial;
        }
    },
    LAPIS_MAT(LAPIS, LAPIS_PROP, () -> withBuffs(
            new BuffInstance(NAUSEA, 0, 10)
    )) {
        @Override
        public MaterialConfig config() {
            return config.lapisMaterial;
        }
    },
    EMERALD_MAT(EMERALD, EMERALD_PROP, () -> withBuffs(
            new BuffInstance(SLOWNESS, 0, 20)
    )) {
        @Override
        public MaterialConfig config() {
            return config.emeraldMaterial;
        }
    },
    OBSIDIAN_MAT(OBSIDIAN, OBSIDIAN_PROP, () -> withBuffs(
            new BuffInstance(WEAKNESS, 2, 20)
    )) {
        @Override
        public MaterialConfig config() {
            return config.obsidianMaterial;
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
            return config.infusedLavaMaterial;
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
        public AdvancedMaterialConfig config() {
            return config.guardianMaterial;
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
        public AdvancedMaterialConfig config() {
            return config.superStarMaterial;
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
        public AdvancedMaterialConfig config() {
            return config.enderDragonMaterial;
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
        public ArmorPlusConfig.IMaterialConfig config() {
            return config.slayerMaterial;
        }
    };

    public final Random random = new Random();
    private final Tier properties;
    private final Supplier<List<BuffInstance>> buffs;
    private final ChatFormatting rarity;

    APToolMaterial(APRarity rarity, Tier properties, Supplier<List<BuffInstance>> buffs) {
        this(rarity.getColor(), properties, buffs);
    }

    APToolMaterial(APRarity rarity, Tier properties) {
        this(rarity.getColor(), properties, Collections::emptyList);
    }

    APToolMaterial(ChatFormatting rarity, Tier properties, Supplier<List<BuffInstance>> buffs) {
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
    public ChatFormatting getColor() {
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