package com.sofodev.armorplus.registry.items.tools;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.tools.properties.tool.IAPTool;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Rarity;

import static com.sofodev.armorplus.registry.items.tools.properties.tool.APToolType.PICKAXE;

public class APPickaxeItem extends PickaxeItem {

    private final IAPTool mat;

    public APPickaxeItem(IAPTool mat) {
        super(mat.get(), (int) (mat.get().getAttackDamageBonus() + PICKAXE.getDmg()), PICKAXE.getAttackSpeed(), new Item.Properties().tab(ArmorPlus.AP_WEAPON_GROUP));
        this.mat = mat;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return mat.getRarity();
    }
}