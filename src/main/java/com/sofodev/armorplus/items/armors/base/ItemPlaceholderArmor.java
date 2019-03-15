package com.sofodev.armorplus.items.armors.base;

import com.sofodev.armorplus.items.armors.Tier;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;

import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.*;
import static com.sofodev.armorplus.util.EnumHelperUtil.addArmorMaterial;
import static net.minecraft.init.Blocks.OBSIDIAN;
import static net.minecraft.init.Items.*;
import static net.minecraft.init.SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
import static net.minecraft.item.crafting.Ingredient.fromItems;

public class ItemPlaceholderArmor extends ItemArmor {
    public static final IArmorMaterial COAL_ARMOR = addArmorMaterial("coal_armor", 2,
        coal.armor.protectionPoints, coal.armor.toughnessPoints, Tier.TIER_1, () -> () -> fromItems(COAL));
    public static final IArmorMaterial LAPIS_ARMOR = addArmorMaterial("lapis_armor", 11,
        lapis.armor.protectionPoints, lapis.armor.toughnessPoints, Tier.TIER_1, () -> () -> fromItems(LAPIS_LAZULI));
    public static final IArmorMaterial REDSTONE_ARMOR = addArmorMaterial("redstone_armor", 11,
        redstone.armor.protectionPoints, redstone.armor.toughnessPoints, Tier.TIER_1, () -> () -> fromItems(REDSTONE));
    public static final IArmorMaterial EMERALD_ARMOR = addArmorMaterial("emerald_armor", 35,
        emerald.armor.protectionPoints, emerald.armor.toughnessPoints, Tier.TIER_2, () -> () -> fromItems(EMERALD));
    public static final IArmorMaterial OBSIDIAN_ARMOR = addArmorMaterial("obsidian_armor", 40,
        obsidian.armor.protectionPoints, obsidian.armor.toughnessPoints, Tier.TIER_2, () -> () -> fromItems(OBSIDIAN));
    public static final IArmorMaterial LAVA_ARMOR = addArmorMaterial("lava_armor", 45,
        lava.armor.protectionPoints, lava.armor.toughnessPoints, Tier.TIER_2, () -> () -> fromItems(Utils.getItem("lava_crystal")));
    public static final IArmorMaterial GUARDIAN_ARMOR = addArmorMaterial("guardian_armor", 50,
        guardian.armor.protectionPoints, guardian.armor.toughnessPoints, Tier.TIER_3, () -> () -> fromItems(Utils.getItem("guardian_scale")));
    public static final IArmorMaterial SUPER_STAR_ARMOR = addArmorMaterial("super_star_armor", 50,
        super_star.armor.protectionPoints, super_star.armor.toughnessPoints, Tier.TIER_3, () -> () -> fromItems(Utils.getItem("wither_bone")));
    public static final IArmorMaterial ENDER_DRAGON_ARMOR = addArmorMaterial("ender_dragon_armor", 60,
        ender_dragon.armor.protectionPoints, ender_dragon.armor.toughnessPoints, Tier.TIER_3, () -> () -> fromItems(Utils.getItem("ender_dragon_scale")));
    public static final IArmorMaterial CHICKEN_ARMOR = addArmorMaterial("chicken_armor", 1,
        chicken.protectionPoints, chicken.toughnessPoints, Tier.TIER_1, () -> () -> fromItems(FEATHER));
    public static final IArmorMaterial SLIME_ARMOR = addArmorMaterial("slime_armor", 1,
        slime.protectionPoints, slime.toughnessPoints, Tier.TIER_1, () -> () -> fromItems(SLIME_BALL));
    public static final IArmorMaterial ARDITE_ARMOR = addArmorMaterial("ardite_armor", 55,
        ardite.protectionPoints, ardite.toughnessPoints, Tier.TIER_2, () -> () -> fromItems(Utils.getItem(new ResourceLocation("tconstruct:ardite_ingot"))));
    public static final IArmorMaterial COBALT_ARMOR = addArmorMaterial("cobalt_armor", 44,
        cobalt.protectionPoints, cobalt.toughnessPoints, Tier.TIER_2, () -> () -> fromItems(Utils.getItem(new ResourceLocation("tconstruct:cobalt_ingot"))));
    public static final IArmorMaterial KNIGHT_SLIME_ARMOR = addArmorMaterial("knight_slime_armor", 33,
        knight_slime.protectionPoints, knight_slime.toughnessPoints, Tier.TIER_2, () -> () -> fromItems(Utils.getItem(new ResourceLocation("tconstruct:knight_slime_ingot"))));
    public static final IArmorMaterial MANYULLYN_ARMOR = addArmorMaterial("manyullyn_armor", 66,
        manyullyn.protectionPoints, manyullyn.toughnessPoints, Tier.TIER_2, () -> () -> fromItems(Utils.getItem(new ResourceLocation("tconstruct:manyullyn_ingot"))));
    public static final IArmorMaterial PIG_IRON_ARMOR = addArmorMaterial("pig_iron_armor", 33,
        pig_iron.protectionPoints, pig_iron.toughnessPoints, Tier.TIER_2, () -> () -> fromItems(Utils.getItem(new ResourceLocation("tconstruct:pig_iron_ingot"))));
    public static final IArmorMaterial THE_ULTIMATE_ARMOR = addArmorMaterial("the_ultimate_armor", 160,
        ultimate.armor.protectionPoints, 1, ITEM_ARMOR_EQUIP_DIAMOND, ultimate.armor.toughnessPoints, () -> () -> fromItems(Utils.getItem("the_ultimate_material")));

    public ItemPlaceholderArmor(IArmorMaterial mat, EntityEquipmentSlot slot, Properties props) {
        super(mat, slot, props);
    }
}
