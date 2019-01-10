/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.materials;

import com.sofodev.armorplus.events.RegistryEventHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.datafix.IFixableData;

import java.util.stream.IntStream;

public class ItemRename implements IFixableData {
    private static final String OLD = "armorplus:material";

    private static final String[] REMAP_TO = new String[]{"chainmail", "guardian_scale", "wither_bone", "ender_dragon_scale", "the_ultimate_material"};

    @Override
    public int getFixVersion() {
        return RegistryEventHandler.DATA_FIXER_VERSION;
    }

    @Override
    public NBTTagCompound fixTagCompound(NBTTagCompound nbt) {
        if (OLD.equals(nbt.getString("id"))) {
            IntStream.range(0, REMAP_TO.length).filter(i -> REMAP_TO[i] != null).forEach(i -> rename(nbt, i, REMAP_TO[i]));
        }
        return nbt;
    }

    private void rename(NBTTagCompound compound, int dmg, String regName) {
        if (compound.getShort("Damage") == dmg) {
            compound.setString("id", "armorplus:" + regName);
            compound.setShort("Damage", (short) 0);
        }
    }
}