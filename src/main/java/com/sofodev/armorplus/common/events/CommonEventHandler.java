/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.common.config.ModConfig;
import com.sofodev.armorplus.common.registry.entities.mobs.dungeon.skeletalking.EntitySkeletalKing;
import com.sofodev.armorplus.common.registry.items.base.ItemSpecialPickaxe;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityElderGuardian;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.*;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.common.config.ModConfig.DebugConfig.debugMode;
import static com.sofodev.armorplus.common.config.ModConfig.EntitiesConfig.mob_drops;
import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.enchantments;
import static com.sofodev.armorplus.common.registry.ModItems.*;
import static com.sofodev.armorplus.common.registry.constants.APEnchantments.ENHANCE;
import static com.sofodev.armorplus.common.registry.items.ItemFragment.Fragments.*;
import static com.sofodev.armorplus.common.registry.items.base.special.Pickaxes.INFUSED_LAVA;
import static com.sofodev.armorplus.common.util.Utils.getItem;
import static com.sofodev.armorplus.common.util.Utils.setRL;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static net.minecraft.entity.EntityLiving.getSlotForItemStack;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.item.ItemArmor.ArmorMaterial;
import static net.minecraft.item.ItemArmor.ArmorMaterial.*;
import static net.minecraftforge.fml.common.registry.ForgeRegistries.BLOCKS;
import static net.minecraftforge.fml.common.registry.ForgeRegistries.ENCHANTMENTS;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
@Mod.EventBusSubscriber(modid = ArmorPlus.MODID)
public class CommonEventHandler {


    @SubscribeEvent
    public void onLootTablesLoaded(LootTableLoadEvent event) {
        boolean isSDC = event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON);
        boolean isDPC = event.getName().equals(LootTableList.CHESTS_DESERT_PYRAMID);
        boolean isWMC = event.getName().equals(LootTableList.CHESTS_WOODLAND_MANSION);
        boolean isECC = event.getName().equals(LootTableList.CHESTS_END_CITY_TREASURE);
        boolean isNBC = event.getName().equals(LootTableList.CHESTS_NETHER_BRIDGE);
        if (isSDC || isDPC || isWMC || isECC || isNBC) {

            final LootPool secondaryPool = event.getTable().getPool("pool1");
            final LootPool mainPool = event.getTable().getPool("main");

            // pool2.addEntry(new LootEntryItem(ITEM, WEIGHT, QUALITY, FUNCTIONS, CONDITIONS, NAME));
            if (mainPool != null) {
                addLootTable(mainPool, "guardian", 5, 2, 0.25f);
                addLootTable(mainPool, "super_star", 5, 2, 0.25f);
                addLootTable(mainPool, "ender_dragon", 5, 2, 0.25f);
                //Lower
                addLootTable(mainPool, "emerald", 7, 1, 0.5f);
                addLootTable(mainPool, "obsidian", 7, 1, 0.5f);
                addLootTable(mainPool, "infused_lava", 7, 1, 0.5f);
            }
            if (secondaryPool != null) {
                addLootTable(secondaryPool, "coal", 10, 0, 0.75f);
                addLootTable(secondaryPool, "lapis", 10, 0, 0.75f);
                addLootTable(secondaryPool, "redstone", 10, 0, 0.75f);
            }
        }
    }

    public static void addLootTable(LootPool pool, String itemName, int weight, int quality, float chance) {
        pool.addEntry(new LootEntryItem(getHA(itemName), weight, quality, new LootFunction[0], new LootCondition[]{new RandomChance(chance)}, "armorplus:" + itemName + "_horse_armor"));
    }

    public static Item getHA(String name) {
        return ForgeRegistries.ITEMS.getValue(setRL(name + "_horse_armor"));
    }

    public static Random random = new Random();

    @SubscribeEvent
    public static void onEnchantEvent(TickEvent.PlayerTickEvent event) {
        if (event.player instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) event.player;
            if (enchantments.enableArmorEnhancement && event.phase == TickEvent.Phase.END && !player.world.isRemote) {
                NonNullList<ItemStack> armorInventory = player.inventory.armorInventory;
                IntStream.range(0, armorInventory.size()).forEach(index -> {
                    ItemStack stack = armorInventory.get(index);
                    Item item = stack.getItem();
                    if (!(item instanceof ItemArmor)) {
                        return;
                    }
                    ItemArmor armor = (ItemArmor) item;
                    Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(stack);
                    boolean enhanceable = enchants.containsKey(ENCHANTMENTS.getValue(setRL("enhanced")));
                    ArmorMaterial armorMaterial = armor.getArmorMaterial();
                    if (enhanceable) {
                        if (armorMaterial == CHAIN) {
                            enhance(armorInventory, enchants, stack, chain, index);
                        } else if (armorMaterial == IRON) {
                            enhance(armorInventory, enchants, stack, iron, index);
                        } else if (armorMaterial == GOLD) {
                            enhance(armorInventory, enchants, stack, gold, index);
                        } else if (armorMaterial == DIAMOND) {
                            enhance(armorInventory, enchants, stack, diamond, index);
                        } else {
                            enchants.remove(ENHANCE);
                        }
                    }
                });
            }
        }
    }

    public static void enhance
        (NonNullList<ItemStack> armorInventory, Map<Enchantment, Integer> enchants, ItemStack stack, ItemArmor[]
            array, int i) {
        enchants.remove(ENHANCE);
        EnchantmentHelper.setEnchantments(enchants, stack);
        NBTTagCompound oldCompound = stack.getTagCompound();
        ItemStack iron = ItemStack.EMPTY;
        if (getSlotForItemStack(stack) == HEAD) {
            iron = new ItemStack(array[0]);
        } else if (getSlotForItemStack(stack) == CHEST) {
            iron = new ItemStack(array[1]);
        } else if (getSlotForItemStack(stack) == LEGS) {
            iron = new ItemStack(array[2]);
        } else if (getSlotForItemStack(stack) == FEET) {
            iron = new ItemStack(array[3]);
        }
        if (oldCompound != null) {
            iron.setTagCompound(oldCompound);
        }
        armorInventory.set(i, iron);
    }

    @SubscribeEvent
    public static void blockHarvestDrops(BlockEvent.HarvestDropsEvent event) {
        if (!event.getWorld().isRemote && event.getHarvester() != null) {
            ItemStack tool = event.getHarvester().getHeldItemMainhand();
            if (!tool.isEmpty()) {
                Item item = tool.getItem();
                if (item instanceof ItemSpecialPickaxe && ((ItemSpecialPickaxe) item).pickaxes == INFUSED_LAVA && item.getDestroySpeed(tool, event.getState()) >= 1.0f) {
                    // go through the drops and replace them with their furnace'd variant if applicable
                    ListIterator<ItemStack> iter = event.getDrops().listIterator();
                    while (iter.hasNext()) {
                        ItemStack drop = iter.next();
                        ItemStack furnace = FurnaceRecipes.instance().getSmeltingResult(drop);
                        ItemStack result;
                        if (!furnace.isEmpty() && isNotForbidden(furnace)) {
                            result = furnace;
                        } else {
                            result = ItemStack.EMPTY;
                        }
                        if (!result.isEmpty()) {
                            convertDrop(tool, result, drop, iter);
                        }
                    }
                }
            }
        }
    }

    private static Set<Item> forbiddenList = new HashSet<>();

    static {
        Arrays.stream(ModConfig.MainConfig.lavaPickaxe.blacklist)
            .filter(entry -> getItem(entry) != null || getItem(entry) != Items.AIR || entry != null)
            .forEach(entry -> forbiddenList.add(getItem(entry)));
    }

    private static boolean isNotForbidden(ItemStack firstStack) {
        Item item = firstStack.getItem();
        return !forbiddenList.contains(item);
    }

    private static void convertDrop(ItemStack tool, ItemStack result, ItemStack
        drop, ListIterator<ItemStack> iter) {
        result = result.copy();
        result.setCount(drop.getCount());
        int fortune = getEnchantmentLevel(Enchantments.FORTUNE, tool);
        if (fortune > 0) {
            result.setCount(result.getCount() * random.nextInt(fortune + 1) + 1);
        }
        iter.set(result);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingDrops(LivingDropsEvent event) {
        int min = 0;
        int max = 1;
        int randomDrop = random.nextInt(max - min + 1) + min;
        Entity entity = event.getEntity();
        if (entity instanceof EntityDragon) {
            registerMobDrop(event, mob_drops.ender_dragon_scale.drop, getItemStack(materials, mob_drops.ender_dragon_scale.dropAmount, 3));
            registerTrophyDrop(event, mob_drops.trophy.enableVanillaTrophyDrops, "ender_dragon");
            registerMobDrop(event, true, getItemStack(fragments[MIDNIGHT.getIndex()]));
        }
        if (entity instanceof EntityWither) {
            registerMobDrop(event, mob_drops.wither_bone.bossDrop, getItemStack(materials, mob_drops.wither_bone.dropAmount, 2));
            registerTrophyDrop(event, mob_drops.trophy.enableVanillaTrophyDrops, "wither_boss");
            registerMobDrop(event, true, getItemStack(fragments[NOON.getIndex()]));
        }
        if (entity instanceof EntityWitherSkeleton) {
            registerMobDrop(event, mob_drops.wither_bone.witherSkeletonDrop, getItemStack(materials, randomDrop, 2));
        }
        if (entity instanceof EntityGuardian) {
            registerMobDrop(event, mob_drops.guardian_scale.guardianDrop, getItemStack(materials, randomDrop, 1));
        }
        if (entity instanceof EntityElderGuardian) {
            registerMobDrop(event, mob_drops.guardian_scale.elderDrop, getItemStack(materials, mob_drops.guardian_scale.dropAmount, 1));
            registerTrophyDrop(event, mob_drops.trophy.enableVanillaTrophyDrops, "elder_guardian");
            registerMobDrop(event, true, getItemStack(fragments[DAWN.getIndex()]));
        }
        if (entity instanceof EntitySkeletalKing) {
            registerTrophyDrop(event, mob_drops.trophy.enableAPBossTrophyDrops, "skeletal_king");
        }
    }

    private static void registerTrophyDrop(LivingDropsEvent event, boolean flag, String mob) {
        registerMobDrop(event, flag, getItemStack(BLOCKS.getValue(setRL(mob + "_trophy"))));
    }

    private static void registerMobDrop(LivingDropsEvent event, boolean enableDrop, ItemStack drop) {
        if (enableDrop && !event.getEntityLiving().world.isRemote) {
            event.getEntityLiving().entityDropItem(drop, 0.0f);
            if (debugMode) {
                LogHelper.info(event.getEntity().getName() + " dropped:" + drop + " x " + drop.getCount());
            }
        }
    }
}
