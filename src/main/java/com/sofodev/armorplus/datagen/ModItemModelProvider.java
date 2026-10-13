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
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;
import java.util.Set;

import static com.sofodev.armorplus.ArmorPlus.MODID;

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
        generateHandheldParents();
        // All registered block items, including stairs, slabs, towers and walls.
        // Wall inventory models have a different name from their blockstate models.
        for (var block : ModBlocks.BLOCKS.getEntries()) {
            String name = block.getId().getPath();
            if (name.equals("soul_box")) continue; // special block-item rendering
            String modelName = name.endsWith("_wall") ? name + "_inventory" : name;
            getBuilder(name).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + modelName)));
        }
        getBuilder("soul_box").parent(new ModelFile.UncheckedModelFile(modLoc("block/soul_box")));

        generateArmorModels(ModItems.HELMETS);
        generateArmorModels(ModItems.CHESTPLATES);
        generateArmorModels(ModItems.LEGGINGS);
        generateArmorModels(ModItems.BOOTS);

        for (var item : ModItems.ITEMS.getEntries()) {
            Item actual = item.get();
            String name = item.getId().getPath();
            if (actual instanceof net.minecraft.world.item.BlockItem) continue;
            if (actual instanceof APArmorItem) {
                continue;
            } else if (actual instanceof com.sofodev.armorplus.registry.item.tool.APMaceItem) {
                generateGeckoLibMace(name);
                continue;
            } else if (name.endsWith("_bow")) {
                generateBowModel(name);
            } else if (name.endsWith("_sword")) {
                withExistingParent(name, modLoc("handheld_sword")).texture("layer0", modLoc("item/" + name));
            } else if (name.endsWith("_battle_axe")) {
                withExistingParent(name, modLoc("handheld_battle_axe")).texture("layer0", modLoc("item/" + name));
            } else if (name.endsWith("_pickaxe") || name.endsWith("_shovel")) {
                withExistingParent(name, mcLoc("item/handheld")).texture("layer0", modLoc("item/" + name));
            } else {
                generateItemModel(item);
            }
        }
    }

    private void generateBowModel(String name) {
        ItemModelBuilder bow = withExistingParent(name, mcLoc("item/generated")).texture("layer0", modLoc("item/" + name));
        for (int i = 0; i < 3; i++) {
            String variant = name + "_pulling_" + i;
            getBuilder(variant).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", modLoc("item/" + variant));
            bow.override().predicate(mcLoc("pulling"), 1.0F).predicate(mcLoc("pull"), i == 0 ? 0.0F : (i == 1 ? 0.65F : 0.9F)).model(new ModelFile.UncheckedModelFile(modLoc("item/" + variant))).end();
        }
    }

    private void generateItemModel(RegistryObject<? extends Item> item) {
        ResourceLocation itemId = item.getId();
        String path = itemId.getPath();
        ResourceLocation textureID = itemId;
        if (path.contains("enhanced")) {
            String enhanced = path.replace("enhanced_", "");
            if (!path.contains("chainmail")) {
                if (path.contains("iron") || path.contains("gold") || path.contains("netherite")) {
                    enhanced = enhanced + "_ingot";
                }
                textureID = ResourceLocation.withDefaultNamespace(enhanced);
            } else {
                textureID = ResourceLocation.tryBuild(MODID, enhanced);
            }
        }
        if (textureID != null) {
            this.getBuilder(itemId.toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", textureID.withPrefix("item/"));
        }
    }

    @SafeVarargs
    private void generateItemModel(RegistryObject<Item>... items) {
        for (RegistryObject<Item> item : items) {
            generateItemModel(item);
        }
    }

    private void generateItemModel(Set<RegistryObject<Item>> items) {
        for (RegistryObject<Item> item : items) {
            generateItemModel(item);
        }
    }

    private void generateSwordModel(RegistryObject<Item> item) {
        this.withExistingParent(item.getId().getPath(), modLoc("handheld_sword")).texture("layer0", modLoc("item/" + item.getId().getPath()));
    }

    @SafeVarargs
    private void generateSwordModel(RegistryObject<Item>... items) {
        for (RegistryObject<Item> item : items) {
            generateSwordModel(item);
        }
    }

    private void generateBattleAxeModel(RegistryObject<Item> item) {
        this.withExistingParent(item.getId().getPath(), modLoc("handheld_battle_axe")).texture("layer0", modLoc("item/" + item.getId().getPath()));
    }

    @SafeVarargs
    private void generateBattleAxeModel(RegistryObject<Item>... items) {
        for (RegistryObject<Item> item : items) {
            generateBattleAxeModel(item);
        }
    }

    private void generateArmorModels(Set<RegistryObject<? extends APArmorItem>> items) {
        for (RegistryObject<? extends APArmorItem> obj : items) {
            generateTrimmableItemModel(obj);
        }
    }


    private void generateTrimmableItemModel(RegistryObject<? extends APArmorItem> itemRegistryObject) {
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

            existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

            // Trimmed armor model
            getBuilder(currentTrimName).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", layer0).texture("layer1", trimResLoc);

            // Non-trimmed armor model (normal variant)
            this.withExistingParent(itemName, mcLoc("item/generated")).override().model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace() + ":item/" + trimNameResLoc.getPath())).predicate(mcLoc("trim_type"), trimValue).end().texture("layer0", layer0);
        });
    }

    private void generateHandheldParents() {
        getBuilder("handheld_sword").parent(new ModelFile.UncheckedModelFile("minecraft:item/handheld")).transforms().transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND).rotation(0, -90, 55).translation(0, 6.0F, 0.5F).scale(1.1F).end().transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND).rotation(0, -90, 25).translation(1.13F, 4.0F, 1.13F).scale(0.9F).end().end();

        getBuilder("handheld_battle_axe").parent(new ModelFile.UncheckedModelFile("minecraft:item/handheld")).transforms().transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND).rotation(0, -90, 55).translation(0, 8.0F, 0.5F).scale(1.5F).end().transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND).rotation(0, -90, 25).translation(1.13F, 3.2F, 1.13F).scale(0.9F).end().end();
    }


    private void generateGeckoLibMace(String name) {
        getBuilder(name).parent(new ModelFile.UncheckedModelFile(modLoc("item/mace.item")));
    }


    private ItemModelBuilder generateBlockItemModel(RegistryObject<? extends Block> block) {

        String name = block.getId().getPath();

        return getBuilder(name).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + name)));
    }
}
