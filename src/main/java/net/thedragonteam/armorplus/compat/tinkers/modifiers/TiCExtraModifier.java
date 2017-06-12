/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

//package net.thedragonteam.armorplus.compat.tinkers.modifiers;

//public class TiCExtraModifier extends AbstractTrait {
//
//    public TiCExtraModifier() {
//        super("ultimatemod", 0xFFFFFF);
//        this.addAspects(new ModifierAspect.SingleAspect(this));
//    }
//
//    @Override
//    public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
//        ToolNBT data = TagUtil.getToolStats(rootCompound);
//        data.modifiers++;
//        TagUtil.setToolTag(rootCompound, data.get());
//    }
//
//}