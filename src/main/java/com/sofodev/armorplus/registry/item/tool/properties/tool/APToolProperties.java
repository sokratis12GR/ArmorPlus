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
    SLAYER_PROP(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 9001, 20.0f, 7.0f, 100, "the_ultimate_material"),
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