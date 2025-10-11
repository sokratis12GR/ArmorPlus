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

import java.util.function.Consumer;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.getItemByName;
import static net.minecraft.advancements.RequirementsStrategy.AND;
import static net.minecraft.advancements.RequirementsStrategy.OR;

public class ModAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {

        Advancement root = createAdvancement(
                "root",
                null,
                getAPItem("redstone_chestplate"),
                "textures/item/compressed_obsidian.png",
                FrameType.TASK,
                false,
                false,
                OR,
                "minecraft:crafting_table"
        );
        saver.accept(root);

        Advancement coalArmor = createAdvancement(
                "coal_armor",
                root,
                getAPItem("coal_chestplate"),
                null,
                FrameType.TASK,
                true,
                true,
                AND,
                "armorplus:coal_helmet",
                "armorplus:coal_chestplate",
                "armorplus:coal_leggings",
                "armorplus:coal_boots"
        );
        saver.accept(coalArmor);

        Advancement coalWeaponry = createAdvancement(
                "coal_weaponry",
                coalArmor,
                getAPItem("coal_sword"),
                null,
                FrameType.TASK,
                true,
                true,
                AND,
                "armorplus:coal_sword", "armorplus:coal_battle_axe", "armorplus:coal_mace"
        );
        saver.accept(coalWeaponry);

        Advancement lapisArmor = createAdvancement(
                "lapis_armor",
                root,
                getAPItem("lapis_chestplate"),
                null,
                FrameType.TASK,
                true,
                true,
                AND,
                "armorplus:lapis_helmet", "armorplus:lapis_chestplate",
                "armorplus:lapis_leggings", "armorplus:lapis_boots"
        );
        saver.accept(lapisArmor);

        Advancement lapisWeaponry = createAdvancement(
                "lapis_weaponry",
                lapisArmor,
                getAPItem("lapis_sword"),
                null,
                FrameType.TASK,
                true,
                true,
                AND,
                "armorplus:lapis_sword", "armorplus:lapis_battle_axe", "armorplus:lapis_mace"
        );
        saver.accept(lapisWeaponry);

        // ── Redstone Armor & Weaponry ──
        Advancement redstoneArmor = createAdvancement(
                "redstone_armor",
                root,
                getAPItem("redstone_chestplate"),
                null,
                FrameType.TASK,
                true,
                true,
                AND,
                "armorplus:redstone_helmet", "armorplus:redstone_chestplate",
                "armorplus:redstone_leggings", "armorplus:redstone_boots"
        );
        saver.accept(redstoneArmor);

        Advancement redstoneWeaponry = createAdvancement(
                "redstone_weaponry",
                redstoneArmor,
                getAPItem("redstone_sword"),
                null,
                FrameType.TASK,
                true,
                true,
                AND,
                "armorplus:redstone_sword", "armorplus:redstone_battle_axe", "armorplus:redstone_mace"
        );
        saver.accept(redstoneWeaponry);

        // ── Emerald Armor & Weaponry ──
        Advancement emeraldArmor = createAdvancement(
                "emerald_armor",
                root,
                getAPItem("emerald_chestplate"),
                null,
                FrameType.TASK,
                true,
                true,
                AND,
                "armorplus:emerald_helmet",
                "armorplus:emerald_chestplate",
                "armorplus:emerald_leggings",
                "armorplus:emerald_boots"
        );
        saver.accept(emeraldArmor);

        Advancement emeraldWeaponry = createAdvancement(
                "emerald_weaponry",
                emeraldArmor,
                getAPItem("emerald_sword"),
                null,
                FrameType.TASK,
                true,
                true,
                AND,
                "armorplus:emerald_sword",
                "armorplus:emerald_battle_axe",
                "armorplus:emerald_mace"
        );
        saver.accept(emeraldWeaponry);


        // ── Obsidian Armor & Weaponry ──
        Advancement obsidianArmor = createAdvancement(
                "obsidian_armor",
                root,
                getAPItem("obsidian_chestplate"),
                null,
                FrameType.TASK,
                true,
                true,
                AND,
                "armorplus:obsidian_helmet",
                "armorplus:obsidian_chestplate",
                "armorplus:obsidian_leggings",
                "armorplus:obsidian_boots"
        );
        saver.accept(obsidianArmor);

        Advancement obsidianWeaponry = createAdvancement(
                "obsidian_weaponry",
                obsidianArmor,
                getAPItem("obsidian_sword"),
                null,
                FrameType.TASK,
                true,
                true,
                AND,
                "armorplus:obsidian_sword",
                "armorplus:obsidian_battle_axe",
                "armorplus:obsidian_mace"
        );
        saver.accept(obsidianWeaponry);

        // ── Infused Lava Armor & Weaponry ──
        Advancement lavaCrystal = createAdvancement(
                "obtained_lava_crystal",
                root,
                getAPItem("infused_lava_crystal"),
                null,
                FrameType.GOAL,
                true,
                true,
                OR,
                "armorplus:infused_lava_crystal"
        );
        saver.accept(lavaCrystal);

        Advancement soulBox = createAdvancement(
                "craft_soul_box",
                root,
                getAPItem("soul_box"),
                null,
                FrameType.TASK,
                true,
                true,
                OR,
                "armorplus:soul_box"
        );
        saver.accept(soulBox);

        Advancement lavaArmor = createAdvancement(
                "infused_lava_armor",
                lavaCrystal,
                getAPItem("infused_lava_chestplate"),
                null,
                FrameType.TASK,
                true,
                true,
                AND,
                "armorplus:infused_lava_helmet", "armorplus:infused_lava_chestplate",
                "armorplus:infused_lava_leggings", "armorplus:infused_lava_boots"
        );
        saver.accept(lavaArmor);

        Advancement lavaWeaponry = createAdvancement(
                "infused_lava_weaponry",
                lavaArmor,
                getAPItem("infused_lava_sword"),
                null,
                FrameType.TASK,
                true,
                true,
                AND,
                "armorplus:infused_lava_sword", "armorplus:infused_lava_battle_axe", "armorplus:infused_lava_mace"
        );
        saver.accept(lavaWeaponry);

        Advancement guardianSoulObtained = createAdvancement(
                "obtained_guardian_soul",
                lavaCrystal,
                getAPItem("soul_elder_guardian"),
                null,
                FrameType.GOAL,
                true,
                true,
                OR,
                "armorplus:soul_guardian", "armorplus:soul_elder_guardian"
        );
        saver.accept(guardianSoulObtained);

        Advancement witherSoulObtained = createAdvancement(
                "obtained_wither_soul",
                guardianSoulObtained,
                getAPItem("soul_wither_boss"),
                null,
                FrameType.GOAL,
                true,
                true,
                OR,
                "armorplus:soul_wither_boss", "armorplus:soul_wither_skeleton"
        );
        saver.accept(witherSoulObtained);


        Advancement dragonSoulObtained = createAdvancement(
                "obtained_ender_dragon_soul",
                witherSoulObtained,
                getAPItem("soul_ender_dragon"),
                null,
                FrameType.GOAL,
                true,
                true,
                OR,
                "armorplus:soul_ender_dragon", "armorplus:soul_enderman"
        );
        saver.accept(dragonSoulObtained);

        // ── Guardian Armor & Weaponry ──
        Advancement guardianArmor = createAdvancement(
                "guardian_armor",
                guardianSoulObtained,
                getAPItem("guardian_chestplate"),
                null,
                FrameType.CHALLENGE,
                true,
                true,
                AND,
                "armorplus:guardian_helmet", "armorplus:guardian_chestplate",
                "armorplus:guardian_leggings", "armorplus:guardian_boots"
        );
        saver.accept(guardianArmor);

        Advancement guardianWeaponry = createAdvancement(
                "guardian_weaponry",
                guardianArmor,
                getAPItem("guardian_sword"),
                null,
                FrameType.CHALLENGE,
                true,
                true,
                AND,
                "armorplus:guardian_sword", "armorplus:guardian_battle_axe", "armorplus:guardian_mace"
        );
        saver.accept(guardianWeaponry);


        // ── Super Star Armor & Weaponry ──
        Advancement superArmor = createAdvancement(
                "super_star_armor",
                witherSoulObtained,
                getAPItem("super_star_chestplate"),
                null,
                FrameType.CHALLENGE,
                true,
                true,
                AND,
                "armorplus:super_star_helmet", "armorplus:super_star_chestplate",
                "armorplus:super_star_leggings", "armorplus:super_star_boots"
        );
        saver.accept(superArmor);

        Advancement superWeaponry = createAdvancement(
                "super_star_weaponry",
                superArmor,
                getAPItem("super_star_sword"),
                null,
                FrameType.CHALLENGE,
                true,
                true,
                AND,
                "armorplus:super_star_sword", "armorplus:super_star_battle_axe", "armorplus:super_star_mace"
        );
        saver.accept(superWeaponry);


        // ── Ender Dragon Armor & Weaponry ──
        Advancement enderArmor = createAdvancement(
                "ender_dragon_armor",
                dragonSoulObtained,
                getAPItem("ender_dragon_chestplate"),
                null,
                FrameType.CHALLENGE,
                true,
                true,
                AND,
                "armorplus:ender_dragon_helmet", "armorplus:ender_dragon_chestplate",
                "armorplus:ender_dragon_leggings", "armorplus:ender_dragon_boots"
        );
        saver.accept(enderArmor);

        Advancement enderWeaponry = createAdvancement(
                "ender_dragon_weaponry",
                enderArmor,
                getAPItem("ender_dragon_sword"),
                null,
                FrameType.CHALLENGE,
                true,
                true,
                AND,
                "armorplus:ender_dragon_sword", "armorplus:ender_dragon_battle_axe", "armorplus:ender_dragon_mace"
        );
        saver.accept(enderWeaponry);

        // ── Slayer Armor ──
        Advancement slayerSoulCrafted = createAdvancement(
                "obtained_slayer_soul",
                dragonSoulObtained,
                getAPItem("soul_slayer"),
                null,
                FrameType.GOAL,
                true,
                true,
                OR,
                "armorplus:soul_slayer"
        );
        saver.accept(slayerSoulCrafted);

        Advancement slayerArmor = createAdvancement(
                "slayer_armor",
                slayerSoulCrafted,
                getAPItem("slayer_chestplate"),
                null,
                FrameType.CHALLENGE,
                true,
                true,
                AND,
                "armorplus:slayer_helmet",
                "armorplus:slayer_chestplate",
                "armorplus:slayer_leggings",
                "armorplus:slayer_boots"
        );
        saver.accept(slayerArmor);

        Advancement slayerWeaponry = createAdvancement(
                "slayer_weaponry",
                slayerArmor,
                getAPItem("slayer_sword"),
                null,
                FrameType.CHALLENGE,
                true,
                true,
                AND,
                "armorplus:slayer_sword", "armorplus:slayer_battle_axe", "armorplus:slayer_mace"
        );
        saver.accept(slayerWeaponry);


        // ── Thank You Advancement ──
        Advancement thankYou = createAdvancement(
                "thank_you",
                root,
                getAPItem("thank_you"),
                null,
                FrameType.TASK,
                true,
                false,
                OR,
                "armorplus:redstone_chestplate"
        );
        saver.accept(thankYou);
    }

    private Advancement createAdvancement(
            String id,
            Advancement parent,
            ItemLike iconItem,
            String backgroundPath,
            FrameType frame,
            boolean showToast,
            boolean announceToChat,
            RequirementsStrategy strategy,
            String... criterionItems
    ) {

        String titleKey = "advancements.armorplus.story." + id + ".title";
        String descKey = "advancements.armorplus.story." + id + ".description";
        DisplayInfo display = new DisplayInfo(
                new ItemStack(iconItem),
                Component.translatable(titleKey),
                Component.translatable(descKey),
                backgroundPath != null ? ResourceLocation.fromNamespaceAndPath(MODID, backgroundPath) : null,
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
//    private Advancement createAdvancement(
//            String id,
//            Advancement parent,
//            String titleKey,
//            String descKey,
//            ItemLike iconItem,
//            String backgroundPath,
//            FrameType frame,
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
