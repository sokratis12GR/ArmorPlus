/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.caps.abilities;

import net.minecraft.inventory.EntityEquipmentSlot;

public class EquipmentSlot {

    private final EntityEquipmentSlot[] slots;

    public EquipmentSlot(EntityEquipmentSlot[] slots) {
        this.slots = slots;
    }

    public EquipmentSlot(EntityEquipmentSlot a, EntityEquipmentSlot b, EntityEquipmentSlot c, EntityEquipmentSlot d) {
        this(new EntityEquipmentSlot[]{a, b, c, d});
    }

    public EquipmentSlot(EntityEquipmentSlot a, EntityEquipmentSlot b, EntityEquipmentSlot c) {
        this(a, b, c, null);
    }

    public EquipmentSlot(EntityEquipmentSlot a, EntityEquipmentSlot b) {
        this(a, b, null, null);
    }

    public EquipmentSlot(EntityEquipmentSlot a) {
        this(a, null, null, null);
    }

    public EntityEquipmentSlot[] getSlots() {
        return slots;
    }
}
