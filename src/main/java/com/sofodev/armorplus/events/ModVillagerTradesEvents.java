package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.providers.EnchantmentProvider;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

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
                    new FlexibleItemTrade(new ItemStack(EMERALD, 3 + rand.nextInt(5)), new ItemStack(LAVA_SHARD.get(), 3 + rand.nextInt(3)), 16, 2, 0.4f),
                    new FlexibleItemTrade(new ItemStack(EMERALD, 3 + rand.nextInt(5)), new ItemStack(FROST_SHARD.get(), 3 + rand.nextInt(3)), 16, 2, 0.4f),
                    new FlexibleItemTrade(new ItemStack(EMERALD, 3 + rand.nextInt(5)), new ItemStack(Items.BLAZE_POWDER, 3 + rand.nextInt(3)), 16, 2, 0.4f)
            ));
            e.getTrades().put(2, asList(
                    new FlexibleItemTrade(new ItemStack(EMERALD, 6 + rand.nextInt(4)), new ItemStack(LAVA_SHARD.get(), 5 + rand.nextInt(4)), 8, 6, 0.2f),
                    new FlexibleItemTrade(new ItemStack(EMERALD, 6 + rand.nextInt(4)), new ItemStack(FROST_SHARD.get(), 5 + rand.nextInt(4)), 8, 6, 0.2f),
                    new FlexibleItemTrade(new ItemStack(EMERALD, 6 + rand.nextInt(4)), new ItemStack(Items.BLAZE_ROD, 2 + rand.nextInt(2)), 8, 6, 0.2f),
                    new FlexibleItemTrade(new ItemStack(EMERALD, 8 + rand.nextInt(6)), new ItemStack(LAVA_CRYSTAL.get(), 1 + rand.nextInt(2)), 8, 10, 0.2f),
                    new FlexibleItemTrade(new ItemStack(EMERALD, 8 + rand.nextInt(6)), new ItemStack(FROST_CRYSTAL.get(), 1 + rand.nextInt(2)), 8, 10, 0.2f)
            ));
            e.getTrades().put(3, asList(
                    new FlexibleItemTrade(new ItemStack(LAVA_CRYSTAL.get(), 4 + rand.nextInt(20)), new ItemStack(SOUL_SAND, 4 + rand.nextInt(16)), new ItemStack(WITHER_SKELETON_SOUL.get()), 6, 15, 0.05f),
                    new FlexibleItemTrade(new ItemStack(LAVA_CRYSTAL.get(), 4 + rand.nextInt(20)), new ItemStack(SOUL_SAND, 4 + rand.nextInt(16)), new ItemStack(SLAYER_SOUL.get()), 6, 15, 0.05f),
                    new FlexibleItemTrade(new ItemStack(LAVA_CRYSTAL.get(), 4 + rand.nextInt(20)), new ItemStack(PRISMARINE, 4 + rand.nextInt(16)), new ItemStack(GUARDIAN_SOUL.get()), 6, 15, 0.05f),
                    new FlexibleItemTrade(new ItemStack(LAVA_CRYSTAL.get(), 4 + rand.nextInt(20)), new ItemStack(END_STONE, 4 + rand.nextInt(16)), new ItemStack(ENDERMAN_SOUL.get()), 6, 15, 0.05f)
            ));
//            ItemStack priceBook = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(SOUL_STEALER, 1));
            e.getTrades().put(4, asList(
                    new FlexibleItemTrade(new ItemStack(LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), witherSoul, elderGuardianSoul, 2, 20, 0.0f),
                    new FlexibleItemTrade(new ItemStack(LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), enderDragonSoul, elderGuardianSoul, 2, 20, 0.0f),
                    new FlexibleItemTrade(new ItemStack(LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), elderGuardianSoul, witherSoul, 2, 20, 0.0f),
                    new FlexibleItemTrade(new ItemStack(LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), enderDragonSoul, witherSoul, 2, 20, 0.0f),
                    new FlexibleItemTrade(new ItemStack(LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), witherSoul, enderDragonSoul, 2, 20, 0.0f),
                    new FlexibleItemTrade(new ItemStack(LAVA_CRYSTAL.get(), 6 + rand.nextInt(10)), elderGuardianSoul, enderDragonSoul, 2, 20, 0.0f)
//                    new FlexibleItemTrade(new ItemStack(LAVA_CRYSTAL.get(), 10 + rand.nextInt(10)), priceBook, 2, 20, 0.0f)
            ));
            //Tier Emerald Trades
            //Boss Soul for Boss Soul exchange without cost.
            //Soul Stealer enchanted gear.
            int bound = 5 + rand.nextInt(15);
            ItemStack priceILBA = EnchantmentHelper.enchantItem(randSource, new ItemStack(getAPItem("infused_lava_battle_axe")), bound, Stream.empty());
            ItemStack priceILS = EnchantmentHelper.enchantItem(randSource, new ItemStack(getAPItem("infused_lava_sword")), bound, Stream.empty());
            ItemStack priceDA = EnchantmentHelper.enchantItem(randSource, new ItemStack(Items.DIAMOND_AXE), bound, Stream.empty());
            ItemStack priceDS = EnchantmentHelper.enchantItem(randSource, new ItemStack(Items.DIAMOND_SWORD), bound, Stream.empty());
            ItemStack priceNA = EnchantmentHelper.enchantItem(randSource, new ItemStack(Items.NETHERITE_AXE), bound, Stream.empty());
            ItemStack priceNS = EnchantmentHelper.enchantItem(randSource, new ItemStack(Items.NETHERITE_SWORD), bound, Stream.empty());
//            EnchantmentHelper.enchantItem(randSource, priceILBA, 1, Stream.empty());
//            EnchantmentHelper.enchantItem(randSource, priceDA, 1, Stream.empty());
//            EnchantmentHelper.enchantItem(randSource, priceDS, 1, Stream.empty());
//            EnchantmentHelper.enchantItem(randSource, priceNA, 1, Stream.empty());
//            EnchantmentHelper.enchantItem(randSource, priceNS, 1, Stream.empty());
//            priceILS.enchant(SOUL_STEALER.get(), 1);
//            priceDA.enchant(SOUL_STEALER.get(), 1);
//            priceDS.enchant(SOUL_STEALER.get(), 1);
//            priceNA.enchant(SOUL_STEALER.get(), 1);
//            priceNS.enchant(SOUL_STEALER.get(), 1);
            int crystalAmount = Math.min(10 + bound, 64);
            ItemStack crystalCost = new ItemStack(LAVA_CRYSTAL.get(), crystalAmount);
            e.getTrades().put(5, asList(
                    new FlexibleItemTrade(witherSoul, elderGuardianSoul, 3, 25, 0f),
                    new FlexibleItemTrade(enderDragonSoul, elderGuardianSoul, 3, 25, 0f),
                    new FlexibleItemTrade(elderGuardianSoul, witherSoul, 3, 25, 0f),
                    new FlexibleItemTrade(enderDragonSoul, witherSoul, 3, 25, 0f),
                    new FlexibleItemTrade(witherSoul, enderDragonSoul, 3, 25, 0f),
                    new FlexibleItemTrade(elderGuardianSoul, enderDragonSoul, 3, 25, 0f),
                    new FlexibleItemTrade(crystalCost, priceILBA, 1, 30, 0f),
                    new FlexibleItemTrade(crystalCost, priceILS, 1, 30, 0f),
                    new FlexibleItemTrade(crystalCost, priceDA, 1, 30, 0f),
                    new FlexibleItemTrade(crystalCost, priceDS, 1, 30, 0f),
                    new FlexibleItemTrade(crystalCost, priceNA, 1, 30, 0f),
                    new FlexibleItemTrade(crystalCost, priceNS, 1, 30, 0f)
            ));
        }
    }


    public static class FlexibleItemTrade implements VillagerTrades.ItemListing {
        private final ItemCost inputA;
        private final Optional<ItemCost> inputB;
        private final ItemStack result;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;
        private final Optional<ResourceKey<EnchantmentProvider>> enchantmentProvider;

        // Constructor for 1-item trades
        public FlexibleItemTrade(ItemStack inputA, ItemStack result, int maxUses, int villagerXp, float priceMultiplier) {
            this(inputA, null, result, maxUses, villagerXp, priceMultiplier, Optional.empty());
        }

        // Constructor for 2-item trades
        public FlexibleItemTrade(ItemStack inputA, ItemStack inputB, ItemStack result, int maxUses, int villagerXp, float priceMultiplier) {
            this(inputA, inputB, result, maxUses, villagerXp, priceMultiplier, Optional.empty());
        }

        // Full constructor
        public FlexibleItemTrade(ItemStack inputA, ItemStack inputB, ItemStack result, int maxUses, int villagerXp, float priceMultiplier, Optional<ResourceKey<EnchantmentProvider>> enchantmentProvider) {
            this.inputA = new ItemCost(inputA.getItem(), inputA.getCount());
            this.inputB = inputB == null ? Optional.empty() : Optional.of(new ItemCost(inputB.getItem(), inputB.getCount()));
            this.result = result.copy();
            this.maxUses = maxUses;
            this.villagerXp = villagerXp;
            this.priceMultiplier = priceMultiplier;
            this.enchantmentProvider = enchantmentProvider;
        }

        @Override
        public MerchantOffer getOffer(Entity entity, RandomSource rand) {
            ItemStack outputCopy = result.copy();
            Level level = entity.level();

            enchantmentProvider.ifPresent(provider ->
                    EnchantmentHelper.enchantItemFromProvider(
                            outputCopy, level.registryAccess(), provider, level.getCurrentDifficultyAt(entity.blockPosition()), rand
                    )
            );

            return new MerchantOffer(
                    inputA,
                    inputB,
                    outputCopy,
                    0,
                    maxUses,
                    villagerXp,
                    priceMultiplier
            );
        }
    }
}
