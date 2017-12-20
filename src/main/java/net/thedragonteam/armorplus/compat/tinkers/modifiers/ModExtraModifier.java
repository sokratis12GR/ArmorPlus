/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.tinkers.modifiers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;

import java.util.Optional;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class ModExtraModifier extends AbstractTrait {

    public ModExtraModifier() {
        super("ultimatemod", 0xFFFFFF);
        this.addAspects(new ModifierAspect.SingleAspect(this));
    }

    @Override
    public Optional<RecipeMatch.Match> matches(NonNullList<ItemStack> stacks) {
        return Optional.empty();
    }

    @Override
    public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
        ToolNBT data = TagUtil.getToolStats(rootCompound);
        data.modifiers++;
        TagUtil.setToolTag(rootCompound, data.get());
    }

}