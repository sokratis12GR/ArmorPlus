package com.sofodev.armorplus.registry.items.tools;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.tools.properties.APToolMaterial;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;

import java.util.Arrays;

import static com.sofodev.armorplus.registry.items.tools.properties.APToolType.BATTLE_AXE;

public class APBattleAxeItem extends AxeItem {

    private final APToolMaterial mat;

    public APBattleAxeItem(APToolMaterial mat) {
        super(mat.get(), mat.get().getAttackDamage() + BATTLE_AXE.getDmg(), BATTLE_AXE.getAttackSpeed(), new Item.Properties().group(ArmorPlus.AP_WEAPON_GROUP));
        this.mat = mat;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return mat.get().getRarity();
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.world.isRemote) {
            Arrays.stream(mat.getBuffInstances()).forEach(instance -> instance.hitEntity(stack, target, attacker));
        }
        return super.hitEntity(stack, target, attacker);
    }
}
