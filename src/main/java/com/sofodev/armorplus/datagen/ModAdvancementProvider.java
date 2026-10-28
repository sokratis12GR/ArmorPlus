package com.sofodev.armorplus.datagen;

import net.minecraft.advancements.*;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.getItemByName;

public class ModAdvancementProvider implements AdvancementSubProvider {

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver) {

        AdvancementHolder root = createAdvancement(
                "root",
                null,
                getAPItem("redstone_chestplate"),
                "item/compressed_obsidian.png",
                AdvancementType.TASK,
                false,
                false,
                false,
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
                true,
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
                true,
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
                true,
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
                true,
                "armorplus:lapis_sword", "armorplus:lapis_battle_axe", "armorplus:lapis_mace"
        );
        saver.accept(lapisWeaponry);

        // ── Amethyst Armor & Weaponry ──
        AdvancementHolder amethystArmor = createAdvancement(
                "amethyst_armor",
                root,
                getAPItem("amethyst_chestplate"),
                null,
                AdvancementType.TASK,
                true,
                true,
                true,
                "armorplus:amethyst_helmet", "armorplus:amethyst_chestplate",
                "armorplus:amethyst_leggings", "armorplus:amethyst_boots"
        );
        saver.accept(amethystArmor);

        AdvancementHolder amethystWeaponry = createAdvancement(
                "amethyst_weaponry",
                amethystArmor,
                getAPItem("amethyst_sword"),
                null,
                AdvancementType.TASK,
                true,
                true,
                true,
                "armorplus:amethyst_sword", "armorplus:amethyst_battle_axe", "armorplus:amethyst_mace"
        );
        saver.accept(amethystWeaponry);

        // ── Quartz Armor & Weaponry ──
        AdvancementHolder quartzArmor = createAdvancement(
                "quartz_armor",
                root,
                getAPItem("quartz_chestplate"),
                null,
                AdvancementType.TASK,
                true,
                true,
                true,
                "armorplus:quartz_helmet", "armorplus:quartz_chestplate",
                "armorplus:quartz_leggings", "armorplus:quartz_boots"
        );
        saver.accept(quartzArmor);

        AdvancementHolder quartzWeaponry = createAdvancement(
                "quartz_weaponry",
                quartzArmor,
                getAPItem("quartz_sword"),
                null,
                AdvancementType.TASK,
                true,
                true,
                true,
                "armorplus:quartz_sword", "armorplus:quartz_battle_axe", "armorplus:quartz_mace"
        );
        saver.accept(quartzWeaponry);

        // ── Glowstone Armor & Weaponry ──
        AdvancementHolder glowstoneArmor = createAdvancement(
                "glowstone_armor",
                root,
                getAPItem("glowstone_chestplate"),
                null,
                AdvancementType.TASK,
                true,
                true,
                true,
                "armorplus:glowstone_helmet", "armorplus:glowstone_chestplate",
                "armorplus:glowstone_leggings", "armorplus:glowstone_boots"
        );
        saver.accept(glowstoneArmor);

        AdvancementHolder glowstoneWeaponry = createAdvancement(
                "glowstone_weaponry",
                glowstoneArmor,
                getAPItem("glowstone_sword"),
                null,
                AdvancementType.TASK,
                true,
                true,
                true,
                "armorplus:glowstone_sword", "armorplus:glowstone_battle_axe", "armorplus:glowstone_mace"
        );
        saver.accept(glowstoneWeaponry);

        // ── Parity sets (Chicken, Slime, Tinkers', Frost and Enhanced vanilla) ──
        for (String set : new String[]{"chicken", "slime", "ardite", "cobalt", "knight_slime", "pig_iron", "manyullyn", "frost", "frost_lava", "chainmail", "golden", "copper", "iron", "diamond", "netherite"}) {
            AdvancementHolder setArmor = createAdvancement(
                    set + "_armor", root, getAPItem(set + "_chestplate"), null,
                    AdvancementType.TASK, true, true, true,
                    "armorplus:" + set + "_helmet", "armorplus:" + set + "_chestplate",
                    "armorplus:" + set + "_leggings", "armorplus:" + set + "_boots");
            saver.accept(setArmor);
            saver.accept(createAdvancement(
                    set + "_weaponry", setArmor, getAPItem(set + "_sword"), null,
                    AdvancementType.TASK, true, true, true,
                    "armorplus:" + set + "_sword", "armorplus:" + set + "_battle_axe", "armorplus:" + set + "_mace"));
        }

        // ── Honey Armor & Weaponry ──
        AdvancementHolder honeyArmor = createAdvancement(
                "honey_armor",
                root,
                getAPItem("honey_chestplate"),
                null,
                AdvancementType.TASK,
                true,
                true,
                true,
                "armorplus:honey_helmet", "armorplus:honey_chestplate",
                "armorplus:honey_leggings", "armorplus:honey_boots"
        );
        saver.accept(honeyArmor);

        AdvancementHolder honeyWeaponry = createAdvancement(
                "honey_weaponry",
                honeyArmor,
                getAPItem("honey_sword"),
                null,
                AdvancementType.TASK,
                true,
                true,
                true,
                "armorplus:honey_sword", "armorplus:honey_battle_axe", "armorplus:honey_mace"
        );
        saver.accept(honeyWeaponry);

        // ── Redstone Armor & Weaponry ──
        AdvancementHolder redstoneArmor = createAdvancement(
                "redstone_armor",
                root,
                getAPItem("redstone_chestplate"),
                null,
                AdvancementType.TASK,
                true,
                true,
                true,
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
                true,
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
                true,
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
                true,
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
                true,
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
                true,
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
                false,
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
                false,
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
                true,
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
                true,
                "armorplus:infused_lava_sword", "armorplus:infused_lava_battle_axe", "armorplus:infused_lava_mace"
        );
        saver.accept(lavaWeaponry);

        AdvancementHolder guardianSoulObtained = createAdvancement(
                "obtained_guardian_soul",
                lavaCrystal,
                getAPItem("soul_elder_guardian"),
                null,
                AdvancementType.GOAL,
                true,
                true,
                false,
                "armorplus:soul_elder_guardian"
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
                false,
                "armorplus:soul_wither_boss"
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
                false,
                "armorplus:soul_ender_dragon"
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
                true,
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
                true,
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
                true,
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
                true,
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
                true,
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
                true,
                "armorplus:ender_dragon_sword", "armorplus:ender_dragon_battle_axe", "armorplus:ender_dragon_mace"
        );
        saver.accept(enderWeaponry);

        // ── Warden Armor ──
        AdvancementHolder wardenArmor = createAdvancement(
                "warden_armor",
                dragonSoulObtained,
                getAPItem("warden_chestplate"),
                null,
                AdvancementType.CHALLENGE,
                true,
                true,
                true,
                "armorplus:warden_helmet", "armorplus:warden_chestplate",
                "armorplus:warden_leggings", "armorplus:warden_boots"
        );
        saver.accept(wardenArmor);

        // ── Slayer Armor ──
        AdvancementHolder slayerSoulCrafted = createAdvancement(
                "obtained_slayer_soul",
                dragonSoulObtained,
                getAPItem("soul_slayer"),
                null,
                AdvancementType.GOAL,
                true,
                true,
                false,
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
                true,
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
                true,
                "armorplus:slayer_sword", "armorplus:slayer_battle_axe", "armorplus:slayer_mace"
        );
        saver.accept(slayerWeaponry);


        // ── Thank You Advancement ──
        AdvancementHolder thankYou = createAdvancement(
                "thank_you",
                root,
                getAPItem("thank_you"),
                null,
                AdvancementType.TASK,
                true,
                false,
                false,
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
            boolean requireAll,
            String... criterionItems
    ) {
        String titleKey = "advancements.armorplus.story." + id + ".title";
        String descKey = "advancements.armorplus.story." + id + ".description";

        Identifier background = backgroundPath == null
                ? null
                : Identifier.fromNamespaceAndPath(
                MODID,
                backgroundPath.endsWith(".png")
                        ? backgroundPath.substring(0, backgroundPath.length() - 4)
                        : backgroundPath
        );

        int experience = 10;

        Advancement.Builder builder = Advancement.Builder.advancement()
                .display(
                        iconItem,
                        Component.translatable(titleKey),
                        Component.translatable(descKey),
                        background,
                        frame,
                        showToast,
                        announceToChat,
                        false
                )
                .rewards(AdvancementRewards.Builder.experience(experience).build());

        builder.requirements(requireAll
                ? AdvancementRequirements.allOf(java.util.Arrays.asList(criterionItems))
                : AdvancementRequirements.anyOf(java.util.Arrays.asList(criterionItems)));

        for (String item : criterionItems) {
            builder.addCriterion(
                    item,
                    InventoryChangeTrigger.TriggerInstance.hasItems(getItemByName(item))
            );
        }

        if (parent != null) {
            builder.parent(parent);
        }

        return builder.build(
                Identifier.fromNamespaceAndPath(MODID, "story/" + id)
        );
    }
}
