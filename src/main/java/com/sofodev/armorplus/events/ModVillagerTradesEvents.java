package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.ModItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

import static com.sofodev.armorplus.registry.ModBlocks.LAVA_CRYSTAL;
import static com.sofodev.armorplus.registry.ModEnchantments.SOUL_STEALER;
import static com.sofodev.armorplus.registry.ModItems.*;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.setRL;
import static java.util.Arrays.asList;
import static net.minecraft.world.item.Items.*;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID)
public class ModVillagerTradesEvents {

    @SubscribeEvent
    public static void onVillagerTradesEvent(VillagerTradesEvent e) {
        Random rand = new Random();
        RandomSource randSource = RandomSource.create();
        VillagerProfession type = e.getType();
        if (type.equals(ForgeRegistries.VILLAGER_PROFESSIONS.getValue(setRL("soul_exchanger")))) {
            ItemStack witherSoul = new ItemStack(getAPItem(WITHER_BOSS_SOUL.getKey()), 1);
            ItemStack enderDragonSoul = new ItemStack(getAPItem(ENDER_DRAGON_SOUL.getKey()), 1);
            ItemStack elderGuardianSoul = new ItemStack(getAPItem(ELDER_GUARDIAN_SOUL.getKey()), 1);
            e.getTrades().put(1, asList(
                    new BasicItemListing(new ItemStack(EMERALD, 3 + rand.nextInt(5)), new ItemStack(LAVA_SHARD.get(), 3 + rand.nextInt(3)), 16, 2, 0.4f),
                    new BasicItemListing(new ItemStack(EMERALD, 3 + rand.nextInt(5)), new ItemStack(FROST_SHARD.get(), 3 + rand.nextInt(3)), 16, 2, 0.4f),
                    new BasicItemListing(new ItemStack(EMERALD, 3 + rand.nextInt(5)), new ItemStack(Items.BLAZE_POWDER, 3 + rand.nextInt(3)), 16, 2, 0.4f)
            ));
            e.getTrades().put(2, asList(
                    new BasicItemListing(new ItemStack(EMERALD, 6 + rand.nextInt(4)), new ItemStack(LAVA_SHARD.get(), 5 + rand.nextInt(4)), 8, 6, 0.2f),
                    new BasicItemListing(new ItemStack(EMERALD, 6 + rand.nextInt(4)), new ItemStack(FROST_SHARD.get(), 5 + rand.nextInt(4)), 8, 6, 0.2f),
                    new BasicItemListing(new ItemStack(EMERALD, 6 + rand.nextInt(4)), new ItemStack(Items.BLAZE_ROD, 2 + rand.nextInt(2)), 8, 6, 0.2f),
                    new BasicItemListing(new ItemStack(EMERALD, 8 + rand.nextInt(6)), new ItemStack(LAVA_CRYSTAL.get(), 1 + rand.nextInt(2)), 8, 10, 0.2f),
                    new BasicItemListing(new ItemStack(EMERALD, 8 + rand.nextInt(6)), new ItemStack(FROST_CRYSTAL.get(), 1 + rand.nextInt(2)), 8, 10, 0.2f)
            ));
            e.getTrades().put(3, asList(
                    new BasicItemListing(new ItemStack(ModItems.LAVA_CRYSTAL.get(), 4 + rand.nextInt(20)), new ItemStack(SOUL_SAND, 4 + rand.nextInt(16)), new ItemStack(WITHER_SKELETON_SOUL.get()), 6, 15, 0.05f),
                    new BasicItemListing(new ItemStack(ModItems.LAVA_CRYSTAL.get(), 4 + rand.nextInt(20)), new ItemStack(PRISMARINE, 4 + rand.nextInt(16)), new ItemStack(GUARDIAN_SOUL.get()), 6, 15, 0.05f),
                    new BasicItemListing(new ItemStack(ModItems.LAVA_CRYSTAL.get(), 4 + rand.nextInt(20)), new ItemStack(END_STONE, 4 + rand.nextInt(16)), new ItemStack(ENDERMAN_SOUL.get()), 6, 15, 0.05f)
            ));
            ItemStack priceBook = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(SOUL_STEALER.get(), 1));
            e.getTrades().put(4, asList(
                    new BasicItemListing(new ItemStack(ModItems.LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), witherSoul, elderGuardianSoul, 2, 20, 0.0f),
                    new BasicItemListing(new ItemStack(ModItems.LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), enderDragonSoul, elderGuardianSoul, 2, 20, 0.0f),
                    new BasicItemListing(new ItemStack(ModItems.LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), elderGuardianSoul, witherSoul, 2, 20, 0.0f),
                    new BasicItemListing(new ItemStack(ModItems.LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), enderDragonSoul, witherSoul, 2, 20, 0.0f),
                    new BasicItemListing(new ItemStack(ModItems.LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), witherSoul, enderDragonSoul, 2, 20, 0.0f),
                    new BasicItemListing(new ItemStack(ModItems.LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), elderGuardianSoul, enderDragonSoul, 2, 20, 0.0f),
                    new BasicItemListing(new ItemStack(ModItems.LAVA_CRYSTAL.get(), 10 + rand.nextInt(10)), priceBook, 2, 20, 0.0f)
            ));
            //Tier Emerald Trades
            //Boss Soul for Boss Soul exchange without cost.
            //Soul Stealer enchanted gear.
            int bound = 5 + rand.nextInt(15);
            ItemStack priceILBA = EnchantmentHelper.enchantItem(randSource, new ItemStack(getAPItem("infused_lava_battle_axe")), bound, false);
            ItemStack priceILS = EnchantmentHelper.enchantItem(randSource, new ItemStack(getAPItem("infused_lava_sword")), bound, false);
            ItemStack priceDA = EnchantmentHelper.enchantItem(randSource, new ItemStack(Items.DIAMOND_AXE), bound, false);
            ItemStack priceDS = EnchantmentHelper.enchantItem(randSource, new ItemStack(Items.DIAMOND_SWORD), bound, false);
            ItemStack priceNA = EnchantmentHelper.enchantItem(randSource, new ItemStack(Items.NETHERITE_AXE), bound, false);
            ItemStack priceNS = EnchantmentHelper.enchantItem(randSource, new ItemStack(Items.NETHERITE_SWORD), bound, false);
            priceILBA.enchant(SOUL_STEALER.get(), 1);
            priceILS.enchant(SOUL_STEALER.get(), 1);
            priceDA.enchant(SOUL_STEALER.get(), 1);
            priceDS.enchant(SOUL_STEALER.get(), 1);
            priceNA.enchant(SOUL_STEALER.get(), 1);
            priceNS.enchant(SOUL_STEALER.get(), 1);
            int crystalAmount = Math.min(10 + bound, 64);
            ItemStack crystalCost = new ItemStack(LAVA_CRYSTAL.get(), crystalAmount);
            e.getTrades().put(5, asList(
                    new BasicItemListing(witherSoul, elderGuardianSoul, 3, 25, 0f),
                    new BasicItemListing(enderDragonSoul, elderGuardianSoul, 3, 25, 0f),
                    new BasicItemListing(elderGuardianSoul, witherSoul, 3, 25, 0f),
                    new BasicItemListing(enderDragonSoul, witherSoul, 3, 25, 0f),
                    new BasicItemListing(witherSoul, enderDragonSoul, 3, 25, 0f),
                    new BasicItemListing(elderGuardianSoul, enderDragonSoul, 3, 25, 0f),
                    new BasicItemListing(crystalCost, priceILBA, 1, 30, 0f),
                    new BasicItemListing(crystalCost, priceILS, 1, 30, 0f),
                    new BasicItemListing(crystalCost, priceDA, 1, 30, 0f),
                    new BasicItemListing(crystalCost, priceDS, 1, 30, 0f),
                    new BasicItemListing(crystalCost, priceNA, 1, 30, 0f),
                    new BasicItemListing(crystalCost, priceNS, 1, 30, 0f)
            ));
        }
    }
}
