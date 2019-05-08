/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.items.base;

import com.sofodev.armorplus.common.registry.items.weapons.Swords;
import com.sofodev.armorplus.common.util.ArmorPlusItemUtils;
import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.*;
import static com.sofodev.armorplus.common.util.EnumHelperUtil.addMaterial;
import static net.minecraft.init.Blocks.OBSIDIAN;
import static net.minecraft.init.Items.*;
import static net.minecraft.item.crafting.Ingredient.fromItems;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ItemSpecialSword extends ItemSword {

    public static final IItemTier SWORD_COAL_MATERIAL = addMaterial(1, coal.weapons.sword.durability, 1.0F, (float) coal.weapons.sword.damage, 15, () -> () -> fromItems(COAL));
    public static final IItemTier SWORD_LAPIS_MATERIAL = addMaterial(1, lapis.weapons.sword.durability, 1.0F, (float) lapis.weapons.sword.damage, 30, () -> () -> fromItems(LAPIS_LAZULI));
    public static final IItemTier SWORD_REDSTONE_MATERIAL = addMaterial(1, redstone.weapons.sword.durability, 1.0F, (float) redstone.weapons.sword.damage, 20, () -> () -> fromItems(REDSTONE));
    public static final IItemTier SWORD_EMERALD_MATERIAL = addMaterial(1, emerald.weapons.sword.durability, 1.0F, (float) emerald.weapons.sword.damage, 20, () -> () -> fromItems(EMERALD));
    public static final IItemTier SWORD_OBSIDIAN_MATERIAL = addMaterial(1, obsidian.weapons.sword.durability, 1.0F, (float) obsidian.weapons.sword.damage, 20, () -> () -> fromItems(OBSIDIAN));
    public static final IItemTier SWORD_LAVA_MATERIAL = addMaterial(1, lava.weapons.sword.durability, 1.0F, (float) lava.weapons.sword.damage, 20, () -> () -> fromItems(Utils.getItem("infused_lava_crystal")));
    public static final IItemTier SWORD_GUARDIAN_MATERIAL = addMaterial(1, guardian.weapons.sword.durability, 1.0F, (float) guardian.weapons.sword.damage, 30, () -> () -> fromItems(Utils.getItem("guardian_scale")));
    public static final IItemTier SWORD_SUPER_STAR_MATERIAL = addMaterial(1, super_star.weapons.sword.durability, 1.0F, (float) super_star.weapons.sword.damage, 20, () -> () -> fromItems(Utils.getItem("wither_bone")));
    public static final IItemTier SWORD_ENDER_DRAGON_MATERIAL = addMaterial(1, ender_dragon.weapons.sword.durability, 1.0F, (float) ender_dragon.weapons.sword.damage, 20, () -> () -> fromItems(Utils.getItem("ender_dragon_scale")));

    public ItemStack itemExpert;
    public TextFormatting formatting;
    public List<String> effect;
    public String itemName;
    private Swords swords;

    public ItemSpecialSword(Swords swords, Properties properties) {
        super(swords.getToolMaterial(), 3, (float) -2.4D, properties);
        this.swords = swords;
        this.itemName = swords.getName();
        this.itemExpert = swords.getRepairStack();
        this.formatting = swords.getTextFormatting();
        this.effect = swords.getEffects();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        swords.addInformation(tooltip);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        return swords.hitEntity(stack, target, attacker);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return ArmorPlusItemUtils.isItemRepairable(repair, itemExpert);
    }
}