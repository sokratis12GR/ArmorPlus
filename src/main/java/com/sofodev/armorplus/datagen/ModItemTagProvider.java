package com.sofodev.armorplus.datagen;

import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.item.armor.APArmorItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static com.sofodev.armorplus.ArmorPlus.MODID;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            CompletableFuture<TagLookup<Block>> blockTags,
            ExistingFileHelper existingFileHelper
    ) {
        super(output, lookupProvider, blockTags, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookup) {
        TagKey<Item> ARMORPLUS_SWORDS = modTag("swords");
        TagKey<Item> ARMORPLUS_BATTLE_AXES = modTag("battle_axes");
        TagKey<Item> ARMORPLUS_PICKAXES = modTag("pickaxes");
        TagKey<Item> ARMORPLUS_BOWS = modTag("bows");
        TagKey<Item> ARMORPLUS_MACES = modTag("maces");

        addAllItems(ARMORPLUS_SWORDS, ModItems.SWORDS);
        addAllItems(ARMORPLUS_BATTLE_AXES, ModItems.BATTLE_AXES);
        addAllItems(ARMORPLUS_PICKAXES, ModItems.PICKAXES);
        addAllItems(ARMORPLUS_BOWS, ModItems.BOWS);
        addAllItems(ARMORPLUS_MACES, ModItems.MACES);

        mirrorMinecraftTag("enchantable/weapon", ARMORPLUS_SWORDS, ARMORPLUS_BATTLE_AXES, ARMORPLUS_MACES);
        mirrorMinecraftTag("enchantable/sword", ARMORPLUS_SWORDS);
        mirrorMinecraftTag("enchantable/bow", ARMORPLUS_BOWS);
        mirrorMinecraftTag("enchantable/mining", ARMORPLUS_PICKAXES, ARMORPLUS_BATTLE_AXES);
        mirrorMinecraftTag("enchantable/mining_loot", ARMORPLUS_PICKAXES, ARMORPLUS_BATTLE_AXES);
        mirrorMinecraftTag("enchantable/sharp_weapon", ARMORPLUS_SWORDS, ARMORPLUS_BATTLE_AXES);


        TagKey<Item> ARMORPLUS_HELMETS = modTag("helmets");
        TagKey<Item> ARMORPLUS_CHESTPLATES = modTag("chestplates");
        TagKey<Item> ARMORPLUS_LEGGINGS = modTag("leggings");
        TagKey<Item> ARMORPLUS_BOOTS = modTag("boots");

        addAllItems(ARMORPLUS_HELMETS, ModItems.HELMETS);
        addAllItems(ARMORPLUS_CHESTPLATES, ModItems.CHESTPLATES);
        addAllItems(ARMORPLUS_LEGGINGS, ModItems.LEGGINGS);
        addAllItems(ARMORPLUS_BOOTS, ModItems.BOOTS);

        mirrorMinecraftTag("enchantable/head_armor", ARMORPLUS_HELMETS);
        mirrorMinecraftTag("enchantable/chest_armor", ARMORPLUS_CHESTPLATES);
        mirrorMinecraftTag("enchantable/leg_armor", ARMORPLUS_LEGGINGS);
        mirrorMinecraftTag("enchantable/foot_armor", ARMORPLUS_BOOTS);

        mirrorMinecraftTag("enchantable/armor",
                ARMORPLUS_HELMETS,
                ARMORPLUS_CHESTPLATES,
                ARMORPLUS_LEGGINGS,
                ARMORPLUS_BOOTS);

        mirrorMinecraftTag("enchantable/equippable",
                ARMORPLUS_HELMETS,
                ARMORPLUS_CHESTPLATES,
                ARMORPLUS_LEGGINGS,
                ARMORPLUS_BOOTS);

        mirrorMinecraftTag("trimmable_armor",
                ARMORPLUS_HELMETS,
                ARMORPLUS_CHESTPLATES,
                ARMORPLUS_LEGGINGS,
                ARMORPLUS_BOOTS
        );

        mirrorMinecraftTag("enchantable/durability",
                ARMORPLUS_HELMETS,
                ARMORPLUS_CHESTPLATES,
                ARMORPLUS_LEGGINGS,
                ARMORPLUS_BOOTS,
                ARMORPLUS_SWORDS,
                ARMORPLUS_BATTLE_AXES,
                ARMORPLUS_PICKAXES,
                ARMORPLUS_BOWS,
                ARMORPLUS_MACES);

        mirrorMinecraftTag("enchantables",
                "enchantable/weapon",
                "enchantable/sword",
                "enchantable/bow",
                "enchantable/mining",
                "enchantable/mining_loot",
                "enchantable/sharp_weapon",
                "enchantable/head_armor",
                "enchantable/chest_armor",
                "enchantable/leg_armor",
                "enchantable/foot_armor",
                "enchantable/armor",
                "enchantable/durability"
        );

        TagKey<Item> ARMORPLUS_ARROWS = modTag("arrows");

        addAllItems(ARMORPLUS_ARROWS, ModItems.ITEM_COAL_ARROW,
                ModItems.ITEM_LAPIS_ARROW,
                ModItems.ITEM_REDSTONE_ARROW,
                ModItems.ITEM_EMERALD_ARROW,
                ModItems.ITEM_OBSIDIAN_ARROW,
                ModItems.ITEM_INFUSED_LAVA_ARROW,
                ModItems.ITEM_GUARDIAN_ARROW,
                ModItems.ITEM_SUPER_STAR_ARROW,
                ModItems.ITEM_ENDER_DRAGON_ARROW
        );
        mirrorMinecraftTag("arrows", ARMORPLUS_ARROWS);

    }

    private TagKey<Item> modTag(String path) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, path));
    }

    private TagKey<Item> mirrorMinecraftTag(String path, TagKey<Item>... mirrors) {
        TagKey<Item> tagKey = TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace(path));

        if (mirrors != null) {
            for (TagKey<Item> mirror : mirrors) {
                tag(tagKey).addTag(mirror);
            }
        }

        return tagKey;
    }

    private TagKey<Item> mirrorMinecraftTag(String path, String... mirrors) {
        TagKey<Item> tagKey = TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace(path));

        if (mirrors != null) {
            for (String mirror : mirrors) {
                ResourceLocation loc = mirror.contains(":")
                        ? ResourceLocation.parse(mirror)
                        : ResourceLocation.withDefaultNamespace(mirror);

                tag(tagKey).addTag(TagKey.create(Registries.ITEM, loc));
            }
        }

        return tagKey;
    }

    @SafeVarargs
    private void addAllItems(TagAppender<Item> tag, DeferredHolder<? extends Item, ? extends Item>... items) {
        for (DeferredHolder<? extends Item, ? extends Item> obj : items) {
            tag.add(ResourceKey.create(Registries.ITEM, obj.getId()));
        }
    }

    private void addAllItems(
            TagAppender<Item> tag,
            Set<? extends DeferredHolder<Item, ? extends APArmorItem>> items
    ) {
        for (DeferredHolder<Item, ? extends APArmorItem> obj : items) {
            tag.add(ResourceKey.create(Registries.ITEM, obj.getId()));
        }
    }

    @SafeVarargs
    private void addAllItems(TagKey<Item> tagKey, DeferredHolder<? extends Item, ? extends Item>... items) {
        for (DeferredHolder<? extends Item, ? extends Item> obj : items) {
            tag(tagKey).add(ResourceKey.create(Registries.ITEM, obj.getId()));
        }
    }

    private void addAllItems(
            TagKey<Item> tagKey,
            Iterable<? extends DeferredHolder<Item, ? extends Item>> items
    ) {
        for (DeferredHolder<Item, ? extends Item> obj : items) {
            tag(tagKey).add(ResourceKey.create(Registries.ITEM, obj.getId()));
        }
    }

}