package com.sofodev.armorplus.registry.items.tools;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.tools.properties.tool.IAPTool;
import com.sofodev.armorplus.registry.items.tools.properties.tool.Tool;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.ShovelItem;

import static com.sofodev.armorplus.registry.items.tools.properties.tool.APToolType.SHOVEL;

public class APShovelItem extends ShovelItem implements Tool {

    private final IAPTool mat;

    public APShovelItem(IAPTool mat) {
        super(mat.get(), mat.get().getAttackDamageBonus() + SHOVEL.getDmg(), SHOVEL.getAttackSpeed(), new Properties().tab(ArmorPlus.AP_WEAPON_GROUP));
        this.mat = mat;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return mat.getRarity();
    }

    @Override
    public IAPTool getMat() {
        return null;
    }
}