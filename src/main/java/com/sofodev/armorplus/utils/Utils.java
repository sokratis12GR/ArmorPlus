/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.utils;

import com.sofodev.armorplus.registry.items.armors.APRepair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.loading.FMLConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static net.minecraft.world.entity.EquipmentSlot.*;

/**
 * @author Sokratis Fotkatzikis
 **/
public final class Utils {

    public static ItemStack[] emptyArmor = new ItemStack[4];
    public static EquipmentSlot[] equipmentSlots = new EquipmentSlot[]{HEAD, CHEST, LEGS, FEET};

    static {
        Arrays.fill(emptyArmor, ItemStack.EMPTY);
    }

    public static List<String> boxList(String[] objects) {
        return stream(objects).collect(toList());
    }

    public static List<Integer> boxList(int[] objects) {
        return stream(objects).boxed().collect(toList());
    }

    public static int convertToSeconds(int seconds) {
        return seconds * 20;
    }

    public static ItemStack checkNBT(ItemStack stack) {
        if (stack.getTag() == null) {
            stack.setTag(new CompoundTag());
        }
        return stack;
    }

    public static ItemStack setUnbreakable(ItemStack stack) {
        checkNBT(stack);
        stack.getTag().putBoolean("Unbreakable", true);
        return stack;
    }

    public static ItemStack getStackFromSlot(Player player, EquipmentSlot slot) {
        return player.getItemBySlot(slot);
    }

    public static String setName(String name) {
        return format("%s.%s", MODID, name);
    }

    public static NonNullList<ItemStack> getItemStacks(Item... items) {
        NonNullList<ItemStack> list = NonNullList.create();
        if (items != null) Arrays.stream(items).map(Utils::getItemStack).forEachOrdered(list::add);
        return list;
    }

    public static NonNullList<ItemStack> getItemStacks(ItemStack... items) {
        NonNullList<ItemStack> list = NonNullList.create();
        list.addAll(Arrays.asList(items));
        return list;
    }

    public static ItemStack getItemStack(Object type, int amount) {
        if (type instanceof String) {
            return new ItemStack(Objects.requireNonNull(getItemByName((String) type)), amount);
        } else if (type instanceof Block) {
            return new ItemStack((Block) type, amount);
        } else {
            return type instanceof Item ? new ItemStack((Item) type, amount) : ItemStack.EMPTY;
        }
    }

    public static ItemStack getItemStack(Object type) {
        if (type instanceof String) {
            return getItemStack(getItemByName((String) type), 0);
        } else if (type instanceof Block) {
            return getItemStack(type, 0);
        } else if (type instanceof Item) {
            return getItemStack(type, 0);
        } else {
            return type instanceof ItemStack ? (ItemStack) type : ItemStack.EMPTY;
        }
    }

    public static String getUnlocalizedNames(ItemStack stack, String key, String... names) {
        for (int i = 0; i < names.length; i++) {
            if (stack.getDamageValue() == i) {
                return key + names[i];
            }
        }
        return key;
    }

    public static Item getItemByName(String name) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(name));
    }

    public static Item getAPItem(String name) {
        return ForgeRegistries.ITEMS.getValue(setRL(name));
    }

    public static Block getAPBlock(String name) {
        return ForgeRegistries.BLOCKS.getValue(setRL(name));
    }

    public static ResourceLocation setRL(String path) {
        return new ResourceLocation(MODID, path);
    }

    public static ResourceLocation setVanillaLocation(String path) {
        return new ResourceLocation(path);
    }

    public static String setLocation(String path) {
        return format("%s:%s", MODID, path);
    }

    public static boolean isNotEmpty(ItemStack stack) {
        return !stack.isEmpty();
    }

    public static boolean isEmpty(ItemStack stack) {
        return stack.isEmpty();
    }

    public static boolean isNotNull(Object object) {
        return object != null;
    }

    public static boolean areNotNull(Object object1, Object object2) {
        return object1 != null && object2 != null;
    }

    public static boolean isNotNullNorEmpty(String object) {
        return isNotNull(object) && !Objects.equals(object, "");
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNullOrEmpty(String object) {
        return isNull(object) || Objects.equals(object, "");
    }

    public static ItemStack getTCIngot(int meta) {
        if (Loader.TCONSTRUCT.isLoaded()) {
            Item ingot = ForgeRegistries.ITEMS.getValue(new ResourceLocation("tconstruct:ingots"));
            if (ingot != null) {
                return new ItemStack(ingot, 1);
            }
            return ItemStack.EMPTY;
        }
        return ItemStack.EMPTY;
    }

    public static String getNormalizedName(EquipmentSlot slot) {
        switch (slot) {
            case HEAD:
                return "helmet";
            case LEGS:
                return "leggings";
            case CHEST:
                return "chestplate";
            case FEET:
                return "boots";
        }
        return slot.getName();
    }

    public static Path getForgeConfig() {
        return FMLPaths.GAMEDIR.get().resolve(FMLConfig.defaultConfigPath());
    }

    public static Path getModConfig() {
        return getForgeConfig().resolve("armorplus.json");
    }

    public static boolean allowsFlightByDefault(Player player) {
        return player.getAbilities().instabuild || player.isSpectator();
    }

    public static List<ItemStack> getRepairStacks(APRepair repair) {
        boolean isString = !(repair.getRepair().isEmpty());
        boolean isStack = !(repair.getRepairStacks().isEmpty());
        boolean isItem = !(repair.getRepairItems().isEmpty());
        if (isString) {
            return repair.getRepair().stream().map(Utils::getAPItem).map(ItemStack::new).collect(toList());
        } else if (isItem) {
            return repair.getRepairItems().stream().map(ItemStack::new).collect(Collectors.toList());
        } else if (isStack) {
            return repair.getRepairStacks();
        }
        return emptyList();
    }

    @Nullable
    public static ItemEntity spawnAtLocation(Player player, ItemStack stack, BlockPos pos) {
        if (stack.isEmpty() || player.level.isClientSide) {
            return null;
        }
        ItemEntity itementity = new ItemEntity(player.level, pos.getX(), pos.getY(), pos.getZ(), stack);
        itementity.setDefaultPickUpDelay();
        if (player.captureDrops() != null) player.captureDrops().add(itementity);
        else {
            player.level.addFreshEntity(itementity);
        }
        return itementity;
    }

}