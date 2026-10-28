package com.sofodev.armorplus.registry.item.tool.properties.tool;

import com.sofodev.armorplus.registry.item.armor.APRepair;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import static net.minecraft.world.item.Items.*;

public enum APToolProperties {
    COAL_PROP(BlockTags.INCORRECT_FOR_GOLD_TOOL, 60, 2.0f, 0f, 10, COAL),
    REDSTONE_PROP(BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 6.0f, 1f, 60, REDSTONE),
    LAPIS_PROP(BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 6.0f, 1f, 60, LAPIS_LAZULI),
    EMERALD_PROP(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1561, 8.0f, 3f, 60, EMERALD),
    OBSIDIAN_PROP(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 5200, 6.0f, 2f, 30, OBSIDIAN),
    INFUSED_LAVA_PROP(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 3000, 10.0f, 3f, 60, "infused_lava_crystal"),
    GUARDIAN_PROP(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 6000, 14.0f, 5f, 70, "guardian_scale"),
    SUPER_STAR_PROP(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 6000, 14.0f, 5f, 70, "wither_bone"),
    ENDER_DRAGON_PROP(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 6000, 14.0f, 5f, 70, "ender_dragon_scale"),
    WARDEN_PROP(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 6000, 14.0f, 5f, 70, "warden_heart"),
    SLAYER_PROP(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 9001, 20.0f, 7.0f, 100, "the_ultimate_material"),
    AMETHYST_PROP(BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 6.0f, 1f, 60, AMETHYST_SHARD),
    QUARTZ_PROP(BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 6.0f, 1f, 60, QUARTZ),
    GLOWSTONE_PROP(BlockTags.INCORRECT_FOR_GOLD_TOOL, 60, 2.0f, 0f, 10, GLOWSTONE_DUST),
    HONEY_PROP(BlockTags.INCORRECT_FOR_GOLD_TOOL, 60, 2.0f, 0f, 10, HONEYCOMB),
    CHICKEN_PROP(BlockTags.INCORRECT_FOR_GOLD_TOOL, 60, 2.0f, 0f, 10, FEATHER),
    SLIME_PROP(BlockTags.INCORRECT_FOR_GOLD_TOOL, 100, 3.0f, 0.5f, 15, SLIME_BALL),
    ARDITE_PROP(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1800, 9.0f, 3.5f, 40, "ardite_ingot"),
    COBALT_PROP(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1500, 8.0f, 3f, 40, "cobalt_ingot"),
    KNIGHT_SLIME_PROP(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1000, 7.0f, 2.5f, 30, "knight_slime_crystal"),
    PIG_IRON_PROP(BlockTags.INCORRECT_FOR_IRON_TOOL, 800, 6.0f, 2f, 20, "pig_iron_ingot"),
    MANYULLYN_PROP(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 4000, 12.0f, 4.5f, 60, "manyullyn_ingot"),
    FROST_PROP(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2500, 9.0f, 3f, 50, "infused_frost_crystal"),
    FROST_LAVA_PROP(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 4500, 12.0f, 5f, 60, "infused_frost_lava_crystal"),
    CHAINMAIL_PROP(BlockTags.INCORRECT_FOR_IRON_TOOL, 400, 6.0f, 1.5f, 15, "enhanced_chainmail"),
    GOLDEN_PROP(BlockTags.INCORRECT_FOR_GOLD_TOOL, 200, 10.0f, 1f, 40, "enhanced_gold"),
    COPPER_PROP(BlockTags.INCORRECT_FOR_IRON_TOOL, 350, 6.0f, 1.5f, 20, "enhanced_copper"),
    IRON_PROP(BlockTags.INCORRECT_FOR_IRON_TOOL, 500, 7.0f, 2f, 20, "enhanced_iron"),
    DIAMOND_PROP(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2000, 9.0f, 3.5f, 15, "enhanced_diamond"),
    NETHERITE_PROP(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 3500, 10.0f, 4.5f, 25, "enhanced_netherite"),
    ;

    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final APRepair repair;
    private final TagKey<Block> incorrectBlocksForDrops;

    APToolProperties(TagKey<Block> incorrectBlocksForDrops, int maxUses, float efficiency, float attackDamage, int enchantability, ItemLike... repair) {
        this(incorrectBlocksForDrops, maxUses, efficiency, attackDamage, enchantability, new APRepair(repair));
    }

    APToolProperties(TagKey<Block> incorrectBlocksForDrops, int maxUses, float efficiency, float attackDamage, int enchantability, String... repair) {
        this(incorrectBlocksForDrops, maxUses, efficiency, attackDamage, enchantability, new APRepair(repair));
    }

    APToolProperties(TagKey<Block> incorrectBlocksForDrops, int maxUses, float efficiency, float attackDamage, int enchantability, APRepair repair) {
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repair = repair;
    }
//
//    @Override
//    public int getLevel() {
//        return this.harvestLevel;
//    }

    public ToolMaterial material() {
        TagKey<Item> repairTag = TagKey.create(BuiltInRegistries.ITEM.key(),
                Identifier.fromNamespaceAndPath("armorplus", "repairs_" + name().toLowerCase()));
        return new ToolMaterial(incorrectBlocksForDrops, maxUses, efficiency, attackDamage, enchantability, repairTag);
    }

    @Override
    public String toString() {
        return "APToolProperties{" +
                "incorrectBlocksForDrops=" + incorrectBlocksForDrops +
                ", maxUses=" + maxUses +
                ", efficiency=" + efficiency +
                ", attackDamage=" + attackDamage +
                ", enchantability=" + enchantability +
                ", repairMaterial=" + repair +
                '}';
    }
}