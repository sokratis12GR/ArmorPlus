package com.sofodev.armorplus.registry.items.armors;

import com.sofodev.armorplus.registry.items.extras.Buff;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Arrays;

import static com.sofodev.armorplus.utils.Utils.getAPItemByName;
import static com.sofodev.armorplus.utils.Utils.getNormalizedName;
import static net.minecraft.inventory.EquipmentSlotType.*;

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
                        if (isExactMatch(player, HEAD) && isExactMatch(player, CHEST) && isExactMatch(player, LEGS) &&
                            isExactMatch(player, FEET)) {
                            instance.onArmorTick(stack, world, player);
                        }
                    } else {
                        instance.onArmorTick(stack, world, player);
                    }
                }
            });
        }
    }

    private boolean isExactMatch(PlayerEntity player, EquipmentSlotType slotType) {
        return player.hasItemInSlot(slotType) && player.getItemStackFromSlot(slotType).getItem() == getAPItemByName(String.format("%s_%s", mat.getName(), getNormalizedName(slotType)));
    }

    public IAPArmor getMat() {
        return mat;
    }
}
