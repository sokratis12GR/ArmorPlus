package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.registry.enchantment.FuriousEnchantmentEffect;
import com.sofodev.armorplus.registry.enchantment.LifeStealEnchantmentEffect;
import com.sofodev.armorplus.registry.enchantment.SoulStealerEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.ConditionalEffect;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;

import static com.sofodev.armorplus.utils.Utils.setRL;

public class ModEnchantments {

    public static final ResourceKey<Enchantment> FURY = ResourceKey.create(Registries.ENCHANTMENT,
            setRL("furious"));
    public static final ResourceKey<Enchantment> LIFE_STEAL = ResourceKey.create(Registries.ENCHANTMENT,
            setRL("life_steal"));
    public static final ResourceKey<Enchantment> SOUL_STEALER = ResourceKey.create(Registries.ENCHANTMENT,
            setRL("soul_stealer"));
    public static final ResourceKey<Enchantment> UNKNOWN = ResourceKey.create(Registries.ENCHANTMENT,
            setRL("unknown"));
    public static final ResourceKey<Enchantment> SOUL_HARDEN = ResourceKey.create(Registries.ENCHANTMENT,
            setRL("soul_harden"));

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        register(context, SOUL_STEALER, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                        5,
                        1,
                        Enchantment.dynamicCost(25, 25),
                        Enchantment.dynamicCost(50, 25),
                        10,
                        EquipmentSlotGroup.MAINHAND))
                .withEffect(EnchantmentEffectComponents.POST_ATTACK,
                        EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM,
                        new SoulStealerEnchantmentEffect())
        );
        register(context, LIFE_STEAL, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                        5,
                        1,
                        Enchantment.dynamicCost(25, 25),
                        Enchantment.dynamicCost(50, 25),
                        10,
                        EquipmentSlotGroup.MAINHAND))
                .withEffect(EnchantmentEffectComponents.POST_ATTACK,
                        EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM,
                        new LifeStealEnchantmentEffect())
        );
        register(context, FURY, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
                        5,
                        1,
                        Enchantment.dynamicCost(10, 1),
                        Enchantment.dynamicCost(10, 1),
                        10,
                        EquipmentSlotGroup.ANY))
                .withEffect(EnchantmentEffectComponents.POST_ATTACK,
                        EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM,
                        new FuriousEnchantmentEffect())
        );
    }

    public static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }
}