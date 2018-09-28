/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.tinkers.modifiers;

import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.utils.TagUtil;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class ModExtraModifier extends ModifierTrait {

    public ModExtraModifier() {
        super("ultimatemod", 0xFFFFFF);
        this.addAspects(new ModifierAspect.SingleAspect(this));
    }

    @Override
    public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
        ToolNBT data = TagUtil.getToolStats(rootCompound);
        data.modifiers++;
        TagUtil.setToolTag(rootCompound, data.get());
    }

}