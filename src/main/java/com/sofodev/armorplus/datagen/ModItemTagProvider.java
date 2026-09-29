package com.sofodev.armorplus.datagen;

import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.item.armor.APArmorItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.KeyTagProvider;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * ArmorPlus item tags for Minecraft 26.1.x's KeyTagProvider API.
 */
public class ModItemTagProvider extends KeyTagProvider<Item> {
    public ModItemTagProvider(PackOutput output,
                              CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.ITEM, lookupProvider, MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookup) {
        TagKey<Item> swords = modTag("swords");
        TagKey<Item> battleAxes = modTag("battle_axes");
        TagKey<Item> pickaxes = modTag("pickaxes");
        TagKey<Item> bows = modTag("bows");
        TagKey<Item> maces = modTag("maces");

        addAllItems(swords, ModItems.SWORDS);
        addAllItems(battleAxes, ModItems.BATTLE_AXES);
        addAllItems(pickaxes, ModItems.PICKAXES);
        addAllItems(bows, ModItems.BOWS);
        addAllItems(maces, ModItems.MACES);

        addToMinecraftTag("enchantable/weapon", swords, battleAxes, maces);
        addToMinecraftTag("enchantable/sword", swords);
        addToMinecraftTag("enchantable/bow", bows);
        addToMinecraftTag("enchantable/mining", pickaxes, battleAxes);
        addToMinecraftTag("enchantable/mining_loot", pickaxes, battleAxes);
        addToMinecraftTag("enchantable/sharp_weapon", swords, battleAxes);

        TagKey<Item> helmets = modTag("helmets");
        TagKey<Item> chestplates = modTag("chestplates");
        TagKey<Item> leggings = modTag("leggings");
        TagKey<Item> boots = modTag("boots");

        addAllItems(helmets, ModItems.HELMETS);
        addAllItems(chestplates, ModItems.CHESTPLATES);
        addAllItems(leggings, ModItems.LEGGINGS);
        addAllItems(boots, ModItems.BOOTS);

        addToMinecraftTag("enchantable/head_armor", helmets);
        addToMinecraftTag("enchantable/chest_armor", chestplates);
        addToMinecraftTag("enchantable/leg_armor", leggings);
        addToMinecraftTag("enchantable/foot_armor", boots);
        addToMinecraftTag("enchantable/armor", helmets, chestplates, leggings, boots);
        addToMinecraftTag("enchantable/equippable", helmets, chestplates, leggings, boots);
        addToMinecraftTag("trimmable_armor", helmets, chestplates, leggings, boots);
        addToMinecraftTag("enchantable/durability", helmets, chestplates, leggings, boots,
                swords, battleAxes, pickaxes, bows, maces);

        TagKey<Item> arrows = modTag("arrows");
        addAllItems(arrows,
                ModItems.ITEM_COAL_ARROW,
                ModItems.ITEM_LAPIS_ARROW,
                ModItems.ITEM_REDSTONE_ARROW,
                ModItems.ITEM_EMERALD_ARROW,
                ModItems.ITEM_OBSIDIAN_ARROW,
                ModItems.ITEM_INFUSED_LAVA_ARROW,
                ModItems.ITEM_GUARDIAN_ARROW,
                ModItems.ITEM_SUPER_STAR_ARROW,
                ModItems.ITEM_ENDER_DRAGON_ARROW);
        addToMinecraftTag("arrows", arrows);
    }

    private TagKey<Item> modTag(String path) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MODID, path));
    }

    @SafeVarargs
    private final void addToMinecraftTag(String path, TagKey<Item>... includedTags) {
        TagAppender<ResourceKey<Item>, Item> appender = tag(TagKey.create(
                Registries.ITEM, Identifier.withDefaultNamespace(path)));
        for (TagKey<Item> included : includedTags) {
            appender.addTag(included);
        }
    }

    @SafeVarargs
    private final void addAllItems(TagKey<Item> tagKey, DeferredHolder<Item, ? extends Item>... items) {
        TagAppender<ResourceKey<Item>, Item> appender = tag(tagKey);
        for (DeferredHolder<Item, ? extends Item> obj : items) {
            appender.add(ResourceKey.create(Registries.ITEM, obj.getId()));
        }
    }

    private void addAllItems(TagKey<Item> tagKey, Set<DeferredHolder<Item, ? extends APArmorItem>> items) {
        TagAppender<ResourceKey<Item>, Item> appender = tag(tagKey);
        for (DeferredHolder<Item, ? extends APArmorItem> obj : items) {
            appender.add(ResourceKey.create(Registries.ITEM, obj.getId()));
        }
    }
}
