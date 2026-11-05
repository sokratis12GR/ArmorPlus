package com.sofodev.armorplus.registry.item.tool.properties.tool;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.config.ArmorPlusConfig.MaterialConfig;
import com.sofodev.armorplus.registry.item.APRarity;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.armorplus.utils.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.SMELTING_MAP;
import static com.sofodev.armorplus.ArmorPlus.config;
import static com.sofodev.armorplus.registry.item.APRarity.AMETHYST;
import static com.sofodev.armorplus.registry.item.APRarity.ARDITE;
import static com.sofodev.armorplus.registry.item.APRarity.CHAINMAIL;
import static com.sofodev.armorplus.registry.item.APRarity.CHICKEN;
import static com.sofodev.armorplus.registry.item.APRarity.COAL;
import static com.sofodev.armorplus.registry.item.APRarity.COBALT;
import static com.sofodev.armorplus.registry.item.APRarity.COPPER;
import static com.sofodev.armorplus.registry.item.APRarity.DIAMOND;
import static com.sofodev.armorplus.registry.item.APRarity.EMERALD;
import static com.sofodev.armorplus.registry.item.APRarity.ENDER_DRAGON;
import static com.sofodev.armorplus.registry.item.APRarity.FROST;
import static com.sofodev.armorplus.registry.item.APRarity.FROST_LAVA;
import static com.sofodev.armorplus.registry.item.APRarity.GOLDEN;
import static com.sofodev.armorplus.registry.item.APRarity.GUARDIAN;
import static com.sofodev.armorplus.registry.item.APRarity.HONEY;
import static com.sofodev.armorplus.registry.item.APRarity.INFUSED_LAVA;
import static com.sofodev.armorplus.registry.item.APRarity.IRON;
import static com.sofodev.armorplus.registry.item.APRarity.KNIGHT_SLIME;
import static com.sofodev.armorplus.registry.item.APRarity.LAPIS;
import static com.sofodev.armorplus.registry.item.APRarity.MANYULLYN;
import static com.sofodev.armorplus.registry.item.APRarity.NETHERITE;
import static com.sofodev.armorplus.registry.item.APRarity.PIG_IRON;
import static com.sofodev.armorplus.registry.item.APRarity.QUARTZ;
import static com.sofodev.armorplus.registry.item.APRarity.REDSTONE;
import static com.sofodev.armorplus.registry.item.APRarity.SLAYER;
import static com.sofodev.armorplus.registry.item.APRarity.SLIME;
import static com.sofodev.armorplus.registry.item.APRarity.SUPER_STAR;
import static com.sofodev.armorplus.registry.item.APRarity.WARDEN;
import static com.sofodev.armorplus.registry.item.APRarity.OBSIDIAN;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.AMETHYST_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.ARDITE_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.CHAINMAIL_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.CHICKEN_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.COAL_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.COBALT_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.COPPER_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.DIAMOND_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.EMERALD_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.ENDER_DRAGON_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.FROST_LAVA_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.FROST_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.GLOWSTONE_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.GOLDEN_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.GUARDIAN_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.HONEY_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.INFUSED_LAVA_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.IRON_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.KNIGHT_SLIME_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.LAPIS_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.MANYULLYN_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.NETHERITE_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.OBSIDIAN_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.PIG_IRON_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.QUARTZ_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.REDSTONE_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.SLAYER_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.SLIME_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.SUPER_STAR_PROP;
import static com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties.WARDEN_PROP;
import static net.minecraft.world.item.Items.NETHERRACK;
import static net.minecraft.world.item.Items.SOUL_SAND;
import static net.minecraft.world.level.block.Blocks.END_STONE;
import static net.minecraft.world.level.block.Blocks.SAND;
import static net.minecraft.world.level.block.Blocks.SCULK;
import static net.minecraft.world.level.block.Blocks.STONE;

public enum APToolMaterial implements IAPTool {
    COAL_MAT(COAL, COAL_PROP) {
        @Override
        public MaterialConfig config() {
            return config.coalMaterial;
        }
    }, REDSTONE_MAT(REDSTONE, REDSTONE_PROP) {
        @Override
        public MaterialConfig config() {
            return config.redstoneMaterial;
        }
    }, LAPIS_MAT(LAPIS, LAPIS_PROP) {
        @Override
        public MaterialConfig config() {
            return config.lapisMaterial;
        }
    }, EMERALD_MAT(EMERALD, EMERALD_PROP) {
        @Override
        public MaterialConfig config() {
            return config.emeraldMaterial;
        }
    }, OBSIDIAN_MAT(OBSIDIAN, OBSIDIAN_PROP) {
        @Override
        public MaterialConfig config() {
            return config.obsidianMaterial;
        }
    }, INFUSED_LAVA_MAT(INFUSED_LAVA, INFUSED_LAVA_PROP) {
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
    }, GUARDIAN_MAT(GUARDIAN, GUARDIAN_PROP) {
        @Override
        public void onBlockMined(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity player) {
        }

        @Override
        public MaterialConfig config() {
            return config.guardianMaterial;
        }
    }, SUPER_STAR_MAT(SUPER_STAR, SUPER_STAR_PROP) {
        @Override
        public void onBlockMined(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity player) {
            if (state == STONE.defaultBlockState() && random.nextInt(3) == 1) {
                if (world instanceof ServerLevel server) player.spawnAtLocation(server, new ItemStack(NETHERRACK, 1));
            }
            if (state == SAND.defaultBlockState() && random.nextInt(3) == 1) {
                if (world instanceof ServerLevel server) player.spawnAtLocation(server, new ItemStack(SOUL_SAND, 1));
            }
        }

        @Override
        public MaterialConfig config() {
            return config.superStarMaterial;
        }
    }, ENDER_DRAGON_MAT(ENDER_DRAGON, ENDER_DRAGON_PROP) {
        @Override
        public void onBlockMined(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity player) {
            if (state == END_STONE.defaultBlockState() && random.nextInt(3) == 1) {
                if (world instanceof ServerLevel server) player.spawnAtLocation(server, new ItemStack(Items.ENDER_PEARL, 1));
            }
        }

        @Override
        public MaterialConfig config() {
            return config.enderDragonMaterial;
        }
    }, WARDEN_MAT(WARDEN, WARDEN_PROP) {
        @Override
        public void onBlockMined(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity player) {
            if (state == SCULK.defaultBlockState() && random.nextInt(3) == 1) {
                if (world instanceof ServerLevel server) player.spawnAtLocation(server, new ItemStack(Items.ECHO_SHARD, 1));
            }
        }

        @Override
        public MaterialConfig config() {
            return config.wardenMaterial;
        }
    }, SLAYER_MAT(SLAYER, SLAYER_PROP) {
        @Override
        public void onBlockMined(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity player) {
            if (state == END_STONE.defaultBlockState() && random.nextInt(3) == 1) {
                if (world instanceof ServerLevel server) player.spawnAtLocation(server, new ItemStack(Items.ENDER_PEARL, 1));
            }
            if (state == STONE.defaultBlockState() && random.nextInt(3) == 1) {
                if (world instanceof ServerLevel server) player.spawnAtLocation(server, new ItemStack(NETHERRACK, 1));
            }
            if (state == SAND.defaultBlockState() && random.nextInt(3) == 1) {
                if (world instanceof ServerLevel server) player.spawnAtLocation(server, new ItemStack(SOUL_SAND, 1));
            }
        }

        @Override
        public MaterialConfig config() {
            return config.slayerMaterial;
        }
    }, AMETHYST_MAT(AMETHYST, AMETHYST_PROP) {
        @Override
        public MaterialConfig config() {
            return config.amethystMaterial;
        }
    }, QUARTZ_MAT(QUARTZ, QUARTZ_PROP) {
        @Override
        public MaterialConfig config() {
            return config.quartzMaterial;
        }
    }, GLOWSTONE_MAT(APRarity.GLOWSTONE, GLOWSTONE_PROP) {
        @Override
        public MaterialConfig config() {
            return config.glowstoneMaterial;
        }
    }, HONEY_MAT(HONEY, HONEY_PROP) {
        @Override
        public MaterialConfig config() {
            return config.honeyMaterial;
        }
    }, CHICKEN_MAT(CHICKEN, CHICKEN_PROP) {
        @Override
        public MaterialConfig config() {
            return config.chickenMaterial;
        }
    }, SLIME_MAT(SLIME, SLIME_PROP) {
        @Override
        public MaterialConfig config() {
            return config.slimeMaterial;
        }
    }, ARDITE_MAT(ARDITE, ARDITE_PROP) {
        @Override
        public MaterialConfig config() {
            return config.arditeMaterial;
        }
    }, COBALT_MAT(COBALT, COBALT_PROP) {
        @Override
        public MaterialConfig config() {
            return config.cobaltMaterial;
        }
    }, KNIGHT_SLIME_MAT(KNIGHT_SLIME, KNIGHT_SLIME_PROP) {
        @Override
        public MaterialConfig config() {
            return config.knightSlimeMaterial;
        }
    }, PIG_IRON_MAT(PIG_IRON, PIG_IRON_PROP) {
        @Override
        public MaterialConfig config() {
            return config.pigIronMaterial;
        }
    }, MANYULLYN_MAT(MANYULLYN, MANYULLYN_PROP) {
        @Override
        public MaterialConfig config() {
            return config.manyullynMaterial;
        }
    }, FROST_MAT(FROST, FROST_PROP) {
        @Override
        public MaterialConfig config() {
            return config.frostMaterial;
        }
    }, FROST_LAVA_MAT(FROST_LAVA, FROST_LAVA_PROP) {
        @Override
        public MaterialConfig config() {
            return config.frostLavaMaterial;
        }
    }, CHAINMAIL_MAT(CHAINMAIL, CHAINMAIL_PROP) {
        @Override
        public MaterialConfig config() {
            return config.enhancedChainmailMaterial;
        }
    }, GOLDEN_MAT(GOLDEN, GOLDEN_PROP) {
        @Override
        public MaterialConfig config() {
            return config.enhancedGoldenMaterial;
        }
    }, COPPER_MAT(COPPER, COPPER_PROP) {
        @Override
        public MaterialConfig config() {
            return config.enhancedCopperMaterial;
        }
    }, IRON_MAT(IRON, IRON_PROP) {
        @Override
        public MaterialConfig config() {
            return config.enhancedIronMaterial;
        }
    }, DIAMOND_MAT(DIAMOND, DIAMOND_PROP) {
        @Override
        public MaterialConfig config() {
            return config.enhancedDiamondMaterial;
        }
    }, NETHERITE_MAT(NETHERITE, NETHERITE_PROP) {
        @Override
        public MaterialConfig config() {
            return config.enhancedNetheriteMaterial;
        }
    };

    public final Random random = new Random();
    private final APToolProperties toolProperties;
    private final Supplier<List<BuffInstance>> buffs;
    private final ChatFormatting rarity;
    /** Built on first use (item registration), once the stats config has loaded. */
    private ToolMaterial properties;

    /**
     * On-hit buffs are read from the material's config ("weaponBuffs", formatted as "id|level|seconds", see {@link BuffParser}).
     */
    APToolMaterial(APRarity rarity, APToolProperties properties) {
        this.rarity = rarity.getColor();
        this.toolProperties = properties;
        this.buffs = () -> config() instanceof MaterialConfig cfg ? cfg.weaponBuffInstances() : List.of();
    }

    /**
     * Builds on-hit buffs in code using the same format as the config files, e.g. {@code withBuffs("minecraft:wither|2|4")}.
     */
    public static List<BuffInstance> withBuffs(String... entries) {
        return Arrays.stream(entries).map(BuffParser::weapon).filter(Objects::nonNull).toList();
    }

    @Override
    public ToolMaterial get() {
        if (properties == null) properties = toolProperties.material();
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
    public double getBowDamageBonus() {
        return get().attackDamageBonus();
    }

    @Override
    public String toString() {
        return "APToolMaterial{" + "random=" + random + ", properties=" + properties + ", buffs=" + buffs + ", rarity=" + rarity + '}';
    }

}