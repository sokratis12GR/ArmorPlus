package com.sofodev.armorplus.registry.items.armors;

import com.sofodev.armorplus.registry.items.extras.Buff;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import software.bernie.geckolib3.item.GeoArmorItem;

import java.util.Arrays;

import static com.sofodev.armorplus.utils.ItemArmorUtility.areExactMatch;

public class APArmorItem extends ArmorItem {

    private final IAPArmor mat;

    public APArmorItem(IAPArmor material, EquipmentSlotType slot) {
        super(material.get(), slot, material.getProperties());
        this.mat = material;
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (!world.isRemote) {
            Arrays.stream(mat.getBuffInstances()).forEach(instance -> {
                if (instance.getBuff() instanceof Buff) {
                    if (instance.getBuff().requiresFullSet()) {
                        if (areExactMatch(mat, player)) {
                            instance.onArmorTick(stack, world, player);
                        }
                    } else {
                        instance.onArmorTick(stack, world, player);
                    }
                }
            });
        }
    }

    public IAPArmor getMat() {
        return mat;
    }
}