/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.integration.tinkers;

import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.utils.TagUtil;

public class TiCExtraModifier extends Modifier {

    public TiCExtraModifier(String identifier) {
        super(identifier);
        this.addAspects(new ModifierAspect.SingleAspect(this));
    }

    @Override
    public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
        ToolNBT data = TagUtil.getToolStats(rootCompound);
        data.modifiers++;
        TagUtil.setToolTag(rootCompound, data.get());
    }

}