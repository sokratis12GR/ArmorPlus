package com.sofodev.armorplus.registry.items.tools;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.tools.properties.APToolMaterial;
import com.sofodev.armorplus.registry.items.tools.properties.APToolProperties;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Rarity;

import static com.sofodev.armorplus.registry.items.tools.properties.APToolType.PICKAXE;

public class APPickaxeItem extends PickaxeItem {

    private final APToolMaterial mat;

    public APPickaxeItem(APToolMaterial mat) {
        super(mat.get(), (int) (mat.get().getAttackDamage() + PICKAXE.getDmg()), PICKAXE.getAttackSpeed(), new Item.Properties().group(ArmorPlus.AP_WEAPON_GROUP));
        this.mat = mat;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return mat.get().getRarity();
    }
}
