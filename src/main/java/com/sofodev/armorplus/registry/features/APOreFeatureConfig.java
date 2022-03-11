package com.sofodev.armorplus.registry.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;

public class APOreFeatureConfig implements IFeatureConfig {
    public static final Codec<APOreFeatureConfig> CODEC = RecordCodecBuilder.create((apOreFeatureConfigInstance) -> apOreFeatureConfigInstance.group(
                    RuleTest.CODEC.fieldOf("target").forGetter((config) -> config.target),
                    BlockState.CODEC.fieldOf("state").forGetter((config) -> config.state),
                    Codec.intRange(0, 64).fieldOf("size").forGetter((config) -> config.size))
            .apply(apOreFeatureConfigInstance, APOreFeatureConfig::new));
    public final RuleTest target;
    public final int size;
    public final BlockState state;

    public APOreFeatureConfig(RuleTest ruleTest, BlockState state, int size) {
        this.size = size;
        this.state = state;
        this.target = ruleTest;
    }

    public static final class FillerBlockType {
        public static final RuleTest BASE_STONE_OVERWORLD = new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD);
        public static final RuleTest NETHERRACK = new BlockMatchRuleTest(Blocks.NETHERRACK);
        public static final RuleTest OBSIDIAN = new BlockMatchRuleTest(Blocks.OBSIDIAN);
        public static final RuleTest BASE_STONE_NETHER = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
    }
}
