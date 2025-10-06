package com.sofodev.armorplus.datagen;

import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.Optional;
import java.util.function.Consumer;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.getItemByName;
import static net.minecraft.advancements.AdvancementRequirements.Strategy.AND;
import static net.minecraft.advancements.AdvancementRequirements.Strategy.OR;

public class ModAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {

        AdvancementHolder root = createAdvancement(
                "root",
                null,
                getAPItem("redstone_chestplate"),
                "textures/item/compressed_obsidian.png",
                AdvancementType.TASK,
                false,
                false,
                OR,
                "minecraft:crafting_table"
        );
        saver.accept(root);

        AdvancementHolder coalArmor = createAdvancement(
                "coal_armor",
                root,
                getAPItem("coal_chestplate"),
                null,
                AdvancementType.TASK,
                true,
                true,
                AND,
                "armorplus:coal_helmet",
                "armorplus:coal_chestplate",
                "armorplus:coal_leggings",
                "armorplus:coal_boots"
        );
        saver.accept(coalArmor);

        AdvancementHolder coalWeaponry = createAdvancement(
                "coal_weaponry",
                coalArmor,
                getAPItem("coal_sword"),
                null,
                AdvancementType.TASK,
                true,
                true,
                AND,
                "armorplus:coal_sword", "armorplus:coal_battle_axe", "armorplus:coal_mace"
        );
        saver.accept(coalWeaponry);

        AdvancementHolder lapisArmor = createAdvancement(
                "lapis_armor",
                root,
                getAPItem("lapis_chestplate"),
                null,
                AdvancementType.TASK,
                true,
                true,
                AND,
                "armorplus:lapis_helmet", "armorplus:lapis_chestplate",
                "armorplus:lapis_leggings", "armorplus:lapis_boots"
        );
        saver.accept(lapisArmor);

        AdvancementHolder lapisWeaponry = createAdvancement(
                "lapis_weaponry",
                lapisArmor,
                getAPItem("lapis_sword"),
                null,
                AdvancementType.TASK,
                true,
                true,
                AND,
                "armorplus:lapis_sword", "armorplus:lapis_battle_axe", "armorplus:lapis_mace"
        );
        saver.accept(lapisWeaponry);

        // ── Redstone Armor & Weaponry ──
        AdvancementHolder redstoneArmor = createAdvancement(
                "redstone_armor",
                root,
                getAPItem("redstone_chestplate"),
                null,
                AdvancementType.TASK,
                true,
                true,
                AND,
                "armorplus:redstone_helmet", "armorplus:redstone_chestplate",
                "armorplus:redstone_leggings", "armorplus:redstone_boots"
        );
        saver.accept(redstoneArmor);

        AdvancementHolder redstoneWeaponry = createAdvancement(
                "redstone_weaponry",
                redstoneArmor,
                getAPItem("redstone_sword"),
                null,
                AdvancementType.TASK,
                true,
                true,
                AND,
                "armorplus:redstone_sword", "armorplus:redstone_battle_axe", "armorplus:redstone_mace"
        );
        saver.accept(redstoneWeaponry);

        // ── Emerald Armor & Weaponry ──
        AdvancementHolder emeraldArmor = createAdvancement(
                "emerald_armor",
                root,
                getAPItem("emerald_chestplate"),
                null,
                AdvancementType.TASK,
                true,
                true,
                AND,
                "armorplus:emerald_helmet",
                "armorplus:emerald_chestplate",
                "armorplus:emerald_leggings",
                "armorplus:emerald_boots"
        );
        saver.accept(emeraldArmor);

        AdvancementHolder emeraldWeaponry = createAdvancement(
                "emerald_weaponry",
                emeraldArmor,
                getAPItem("emerald_sword"),
                null,
                AdvancementType.TASK,
                true,
                true,
                AND,
                "armorplus:emerald_sword",
                "armorplus:emerald_battle_axe",
                "armorplus:emerald_mace"
        );
        saver.accept(emeraldWeaponry);


        // ── Obsidian Armor & Weaponry ──
        AdvancementHolder obsidianArmor = createAdvancement(
                "obsidian_armor",
                root,
                getAPItem("obsidian_chestplate"),
                null,
                AdvancementType.TASK,
                true,
                true,
                AND,
                "armorplus:obsidian_helmet",
                "armorplus:obsidian_chestplate",
                "armorplus:obsidian_leggings",
                "armorplus:obsidian_boots"
        );
        saver.accept(obsidianArmor);

        AdvancementHolder obsidianWeaponry = createAdvancement(
                "obsidian_weaponry",
                obsidianArmor,
                getAPItem("obsidian_sword"),
                null,
                AdvancementType.TASK,
                true,
                true,
                AND,
                "armorplus:obsidian_sword",
                "armorplus:obsidian_battle_axe",
                "armorplus:obsidian_mace"
        );
        saver.accept(obsidianWeaponry);

        // ── Infused Lava Armor & Weaponry ──
        AdvancementHolder lavaCrystal = createAdvancement(
                "obtained_lava_crystal",
                root,
                getAPItem("infused_lava_crystal"),
                null,
                AdvancementType.GOAL,
                true,
                true,
                OR,
                "armorplus:infused_lava_crystal"
        );
        saver.accept(lavaCrystal);

        AdvancementHolder soulBox = createAdvancement(
                "craft_soul_box",
                root,
                getAPItem("soul_box"),
                null,
                AdvancementType.TASK,
                true,
                true,
                OR,
                "armorplus:soul_box"
        );
        saver.accept(soulBox);

        AdvancementHolder lavaArmor = createAdvancement(
                "infused_lava_armor",
                lavaCrystal,
                getAPItem("infused_lava_chestplate"),
                null,
                AdvancementType.TASK,
                true,
                true,
                AND,
                "armorplus:infused_lava_helmet", "armorplus:infused_lava_chestplate",
                "armorplus:infused_lava_leggings", "armorplus:infused_lava_boots"
        );
        saver.accept(lavaArmor);

        AdvancementHolder lavaWeaponry = createAdvancement(
                "infused_lava_weaponry",
                lavaArmor,
                getAPItem("infused_lava_sword"),
                null,
                AdvancementType.TASK,
                true,
                true,
                AND,
                "armorplus:infused_lava_sword", "armorplus:infused_lava_battle_axe", "armorplus:infused_lava_mace"
        );
        saver.accept(lavaWeaponry);

        AdvancementHolder guardianSoulObtained = createAdvancement(
                "obtained_guardian_soul",
                lavaCrystal,
                getAPItem("soul_guardian"),
                null,
                AdvancementType.GOAL,
                true,
                true,
                OR,
                "armorplus:soul_guardian", "armorplus:soul_elder_guardian"
        );
        saver.accept(guardianSoulObtained);

        AdvancementHolder witherSoulObtained = createAdvancement(
                "obtained_wither_soul",
                guardianSoulObtained,
                getAPItem("soul_wither_boss"),
                null,
                AdvancementType.GOAL,
                true,
                true,
                OR,
                "armorplus:soul_wither_boss", "armorplus:soul_wither_skeleton"
        );
        saver.accept(witherSoulObtained);


        AdvancementHolder dragonSoulObtained = createAdvancement(
                "obtained_ender_dragon_soul",
                witherSoulObtained,
                getAPItem("soul_ender_dragon"),
                null,
                AdvancementType.GOAL,
                true,
                true,
                OR,
                "armorplus:soul_ender_dragon", "armorplus:soul_enderman"
        );
        saver.accept(dragonSoulObtained);

        // ── Guardian Armor & Weaponry ──
        AdvancementHolder guardianArmor = createAdvancement(
                "guardian_armor",
                guardianSoulObtained,
                getAPItem("guardian_chestplate"),
                null,
                AdvancementType.CHALLENGE,
                true,
                true,
                AND,
                "armorplus:guardian_helmet", "armorplus:guardian_chestplate",
                "armorplus:guardian_leggings", "armorplus:guardian_boots"
        );
        saver.accept(guardianArmor);

        AdvancementHolder guardianWeaponry = createAdvancement(
                "guardian_weaponry",
                guardianArmor,
                getAPItem("guardian_sword"),
                null,
                AdvancementType.CHALLENGE,
                true,
                true,
                AND,
                "armorplus:guardian_sword", "armorplus:guardian_battle_axe", "armorplus:guardian_mace"
        );
        saver.accept(guardianWeaponry);


        // ── Super Star Armor & Weaponry ──
        AdvancementHolder superArmor = createAdvancement(
                "super_star_armor",
                witherSoulObtained,
                getAPItem("super_star_chestplate"),
                null,
                AdvancementType.CHALLENGE,
                true,
                true,
                AND,
                "armorplus:super_star_helmet", "armorplus:super_star_chestplate",
                "armorplus:super_star_leggings", "armorplus:super_star_boots"
        );
        saver.accept(superArmor);

        AdvancementHolder superWeaponry = createAdvancement(
                "super_star_weaponry",
                superArmor,
                getAPItem("super_star_sword"),
                null,
                AdvancementType.CHALLENGE,
                true,
                true,
                AND,
                "armorplus:super_star_sword", "armorplus:super_star_battle_axe", "armorplus:super_star_mace"
        );
        saver.accept(superWeaponry);


        // ── Ender Dragon Armor & Weaponry ──
        AdvancementHolder enderArmor = createAdvancement(
                "ender_dragon_armor",
                dragonSoulObtained,
                getAPItem("ender_dragon_chestplate"),
                null,
                AdvancementType.CHALLENGE,
                true,
                true,
                AND,
                "armorplus:ender_dragon_helmet", "armorplus:ender_dragon_chestplate",
                "armorplus:ender_dragon_leggings", "armorplus:ender_dragon_boots"
        );
        saver.accept(enderArmor);

        AdvancementHolder enderWeaponry = createAdvancement(
                "ender_dragon_weaponry",
                enderArmor,
                getAPItem("ender_dragon_sword"),
                null,
                AdvancementType.CHALLENGE,
                true,
                true,
                AND,
                "armorplus:ender_dragon_sword", "armorplus:ender_dragon_battle_axe", "armorplus:ender_dragon_mace"
        );
        saver.accept(enderWeaponry);

        // ── Slayer Armor ──
        AdvancementHolder slayerSoulCrafted = createAdvancement(
                "obtained_slayer_soul",
                dragonSoulObtained,
                getAPItem("soul_slayer"),
                null,
                AdvancementType.GOAL,
                true,
                true,
                OR,
                "armorplus:soul_slayer"
        );
        saver.accept(slayerSoulCrafted);

        AdvancementHolder slayerArmor = createAdvancement(
                "slayer_armor",
                slayerSoulCrafted,
                getAPItem("slayer_chestplate"),
                null,
                AdvancementType.CHALLENGE,
                true,
                true,
                AND,
                "armorplus:slayer_helmet",
                "armorplus:slayer_chestplate",
                "armorplus:slayer_leggings",
                "armorplus:slayer_boots"
        );
        saver.accept(slayerArmor);

        AdvancementHolder slayerWeaponry = createAdvancement(
                "slayer_weaponry",
                slayerArmor,
                getAPItem("slayer_sword"),
                null,
                AdvancementType.CHALLENGE,
                true,
                true,
                AND,
                "armorplus:slayer_sword", "armorplus:slayer_battle_axe", "armorplus:slayer_mace"
        );
        saver.accept(slayerWeaponry);


        // ── Thank You Advancement ──
        AdvancementHolder thankYou = createAdvancement(
                "thank_you_6m",
                root,
                getAPItem("redstone_chestplate"),
                null,
                AdvancementType.TASK,
                true,
                false,
                OR,
                "armorplus:redstone_chestplate"
        );
        saver.accept(thankYou);
    }

    private AdvancementHolder createAdvancement(
            String id,
            AdvancementHolder parent,
            ItemLike iconItem,
            String backgroundPath,
            AdvancementType frame,
            boolean showToast,
            boolean announceToChat,
            AdvancementRequirements.Strategy strategy,
            String... criterionItems
    ) {

        String titleKey = "advancements.armorplus.story." + id + ".title";
        String descKey = "advancements.armorplus.story." + id + ".description";
        DisplayInfo display = new DisplayInfo(
                new ItemStack(iconItem),
                Component.translatable(titleKey),
                Component.translatable(descKey),
                backgroundPath != null ? Optional.of(ResourceLocation.fromNamespaceAndPath(MODID, backgroundPath)) : Optional.empty(),
                frame,
                showToast,
                announceToChat,
                false
        );

        int experience = 10;
        Advancement.Builder builder = Advancement.Builder.advancement()
                .display(display)
                .rewards(AdvancementRewards.Builder.experience(experience).build());
        builder.requirements(strategy);

        for (String item : criterionItems) {
            builder.addCriterion(item, InventoryChangeTrigger.TriggerInstance.hasItems(getItemByName(item)));
        }

        if (parent != null) {
            builder.parent(parent);
        }

        return builder.build(ResourceLocation.fromNamespaceAndPath(MODID, "story/" + id));
    }
//
//    private AdvancementHolder createAdvancement(
//            String id,
//            AdvancementHolder parent,
//            String titleKey,
//            String descKey,
//            ItemLike iconItem,
//            String backgroundPath,
//            AdvancementType frame,
//            boolean showToast,
//            boolean announceToChat,
//            boolean hidden,
//            String criterionName,
//            List<String> criterionItems,
//            int experienceReward
//    ) {
//        DisplayInfo display = new DisplayInfo(
//                new ItemStack(iconItem),
//                Component.translatable(titleKey),
//                Component.translatable(descKey),
//                backgroundPath != null ? Optional.of(ResourceLocation.fromNamespaceAndPath(MODID, backgroundPath)) : Optional.empty(),
//                frame,
//                showToast,
//                announceToChat,
//                hidden
//        );
//
//        Advancement.Builder builder = Advancement.Builder.advancement()
//                .display(display)
//                .rewards(experienceReward > 0 ? AdvancementRewards.Builder.experience(experienceReward).build() : AdvancementRewards.EMPTY)
//                .addCriterion(criterionName,
//                        CriteriaTriggers.INVENTORY_CHANGED.createCriterion(
//                                new InventoryChangeTrigger.TriggerInstance(
//                                        Optional.empty(),
//                                        InventoryChangeTrigger.TriggerInstance.Slots.ANY,
//                                        criterionItems.stream().map(item -> ItemPredicate.Builder.item().of(iconItem).build()).toList()
//                                )
//                        )
//                )
//                .requirements(OR);
//
//        if (parent != null) {
//            builder.parent(parent);
//        }
//
//        return builder.build(ResourceLocation.fromNamespaceAndPath(MODID, "story/" + id));
//    }
}
