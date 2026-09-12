package com.sofodev.armorplus.datagen;

import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.item.armor.APArmorItem;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.LinkedHashMap;
import java.util.Set;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.registry.ModItems.*;

public class ModItemModelProvider extends ItemModelProvider {

    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();

    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }


    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        generateItemModel(THANK_YOU);
        generateItemModel(CHAINMAIL,
                ENHANCED_CHAINMAIL, ENHANCED_IRON, ENHANCED_GOLD,
                ENHANCED_DIAMOND, ENHANCED_NETHERITE,
                ENHANCED_CHAINMAIL);
        generateItemModel(GUARDIAN_SOUL, ELDER_GUARDIAN_SOUL, GUARDIAN_SCALE, GUARDIAN_BOW_BASE, GUARDIAN_SWORD_BASE, GUARDIAN_PICKAXE_BASE, GUARDIAN_BATTLE_AXE_BASE);
        generateItemModel(WITHER_BOSS_SOUL, WITHER_SKELETON_SOUL, WITHER_BONE, SUPER_STAR_BOW_BASE, SUPER_STAR_SWORD_BASE, SUPER_STAR_PICKAXE_BASE, SUPER_STAR_BATTLE_AXE_BASE);
        generateItemModel(ENDER_DRAGON_SOUL, ENDERMAN_SOUL, ENDER_DRAGON_SCALE, ENDER_DRAGON_BOW_BASE, ENDER_DRAGON_SWORD_BASE, ENDER_DRAGON_PICKAXE_BASE, ENDER_DRAGON_BATTLE_AXE_BASE);
        generateItemModel(LAVA_SHARD, LAVA_CRYSTAL, INFUSED_LAVA_CRYSTAL);
        generateItemModel(FROST_SHARD, FROST_CRYSTAL, INFUSED_FROST_CRYSTAL);
        generateItemModel(INFUSED_FROST_LAVA_CRYSTAL, THE_ULTIMATE_MATERIAL);
        generateItemModel(SLAYER_SOUL, SLAYER_BOW_BASE, SLAYER_SWORD_BASE, SLAYER_PICKAXE_BASE, SLAYER_BATTLE_AXE_BASE);
        generateArmorModels(ModItems.HELMETS);
        generateArmorModels(ModItems.CHESTPLATES);
        generateArmorModels(ModItems.LEGGINGS);
        generateArmorModels(ModItems.BOOTS);
        generateSwordModel(SWORDS);
        generateBattleAxeModel(BATTLE_AXES);
        generateItemModel(PICKAXES);
        generateItemModel(GUARDIAN_BASES);
        generateItemModel(SUPER_STAR_BASES);
        generateItemModel(ENDER_DRAGON_BASES);
        generateItemModel(SLAYER_BASES);
        generateBlockItemModel(ModBlocks.SOUL_BOX);
    }

    private void generateItemModel(DeferredHolder<Item, Item> item) {
        ResourceLocation itemId = item.getId();
        String path = itemId.getPath();
        ResourceLocation textureID = itemId;
        if (path.contains("enhanced")) {
            String enhanced = path.replace("enhanced_", "");
            if (!path.contains("chainmail")) {
                if (path.contains("iron") || path.contains("gold") || path.contains("netherite")) {
                    enhanced = enhanced + "_ingot";
                }
                textureID = ResourceLocation.tryParse(enhanced);
            } else {
                textureID = ResourceLocation.tryBuild(MODID, enhanced);
            }
        }
        if (textureID != null) {
            this.getBuilder(itemId.toString())
                    .parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", textureID.withPrefix("item/"));
        }
    }

    @SafeVarargs
    private void generateItemModel(DeferredHolder<Item, Item>... items) {
        for (DeferredHolder<Item, Item> item : items) {
            generateItemModel(item);
        }
    }

    private void generateItemModel(Set<DeferredHolder<Item, Item>> items) {
        for (DeferredHolder<Item, Item> item : items) {
            generateItemModel(item);
        }
    }

    private void generateSwordModel(DeferredHolder<Item, Item> item) {
        this.withExistingParent(item.getId().getPath(), modLoc("handheld_sword"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()));
    }

    @SafeVarargs
    private void generateSwordModel(DeferredHolder<Item, Item>... items) {
        for (DeferredHolder<Item, Item> item : items) {
            generateSwordModel(item);
        }
    }

    private void generateBattleAxeModel(DeferredHolder<Item, Item> item) {
        this.withExistingParent(item.getId().getPath(), modLoc("handheld_battle_axe"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()));
    }

    @SafeVarargs
    private void generateBattleAxeModel(DeferredHolder<Item, Item>... items) {
        for (DeferredHolder<Item, Item> item : items) {
            generateBattleAxeModel(item);
        }
    }

    private void generateArmorModels(Set<DeferredHolder<Item, ? extends APArmorItem>> items) {
        for (DeferredHolder<Item, ? extends APArmorItem> obj : items) {
            generateTrimmableItemModel(obj);
        }
    }


    private void generateTrimmableItemModel(DeferredHolder<Item, ? extends APArmorItem> itemRegistryObject) {
        if (!(itemRegistryObject.get() instanceof ArmorItem armorItem)) return;

        String itemName = itemRegistryObject.getId().getPath();

        // Determine layer0: mod asset first, fallback to vanilla
        String layer0;
        ResourceLocation modTextureCheck = modLoc("textures/item/" + itemName + ".png");
        if (existingFileHelper.exists(modTextureCheck, PackType.CLIENT_RESOURCES)) {
            layer0 = modLoc("item/" + itemName).toString();
        } else {
            ResourceLocation mcTextureCheck = mcLoc("textures/item/" + itemName + ".png");
            if (existingFileHelper.exists(mcTextureCheck, PackType.CLIENT_RESOURCES)) {
                layer0 = mcLoc("item/" + itemName).toString();
            } else {
                System.out.println("Warning: No texture found for " + itemName + ", skipping model generation.");
                return;
            }
        }

        // Generate trimmed models for each trim
        trimMaterials.forEach((trimMaterial, trimValue) -> {
            String armorType = switch (armorItem.getEquipmentSlot()) {
                case HEAD -> "helmet";
                case CHEST -> "chestplate";
                case LEGS -> "leggings";
                case FEET -> "boots";
                default -> "";
            };

            String currentTrimName = itemName + "_" + trimMaterial.location().getPath() + "_trim";
            ResourceLocation trimResLoc = ResourceLocation.withDefaultNamespace("trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath());
            ResourceLocation trimNameResLoc = ResourceLocation.fromNamespaceAndPath(MODID, currentTrimName);

            // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
            // avoid an IllegalArgumentException
            existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

            // Trimmed armor model
            getBuilder(currentTrimName)
                    .parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", layer0)
                    .texture("layer1", trimResLoc);

            // Non-trimmed armor model (normal variant)
            this.withExistingParent(itemName, mcLoc("item/generated"))
                    .override()
                    .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace() + ":item/" + trimNameResLoc.getPath()))
                    .predicate(mcLoc("trim_type"), trimValue).end()
                    .texture("layer0", layer0);
        });
    }

    private ItemModelBuilder generateBlockItemModel(DeferredHolder<? extends Block, ? extends Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.fromNamespaceAndPath(MODID, "block/" + item.getId().getPath()));
    }
}
