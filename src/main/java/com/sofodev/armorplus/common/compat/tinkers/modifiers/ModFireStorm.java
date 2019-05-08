/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.compat.tinkers.modifiers;

/**
 * @author Sokratis Fotkatzikis
 */
//public class ModFireStorm extends ModifierTrait {
//
//    public ModFireStorm() {
//        super("firestorm", 0xea0000, 3, 4);
//    }
//
//    @Override
//    public boolean hasTexturePerMaterial() {
//        return true;
//    }
//
//    @Override
//    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
//        this.dealFireDamage(tool, player, target);
//    }
//
//    private void dealFireDamage(ItemStack tool, EntityLivingBase attacker, EntityLivingBase target) {
//        NBTTagCompound tag = TinkerUtil.getModifierTag(tool, identifier);
//        ModifierNBT.IntegerNBT data = ModifierNBT.readInteger(tag);
//
//        int duration = getFireDuration(data);
//        target.setFire(duration);
//
//        float fireDamage = getFireDamage(data);
//
//        if (attackEntitySecondary(new EntityDamageSource("onFire", attacker).setFireDamage(), fireDamage, target, false, true)) {
//            int count = Math.round(fireDamage);
//            TinkerTools.proxy.spawnEffectParticle(ParticleEffect.Type.HEART_FIRE, target, count);
//        }
//    }
//
//    private float getFireDamage(ModifierNBT.IntegerNBT data) {
//        return (float) data.current / 15f;
//    }
//
//    private int getFireDuration(ModifierNBT.IntegerNBT data) {
//        return 1 + data.current / 4;
//    }
//
//    @Override
//    public List<String> getExtraInfo(ItemStack tool, NBTTagCompound modifierTag) {
//        String loc = Util.translateFormatted(LOC_Extra, getIdentifier());
//        ModifierNBT.IntegerNBT data = ModifierNBT.readInteger(modifierTag);
//        int duration = getFireDuration(data);
//        float dmg = getFireDamage(data);
//
//        return ImmutableList.of(
//            Util.translateFormatted(loc, Util.df.format(dmg)),
//            Util.translateFormatted(loc + 2, Util.df.format(duration))
//        );
//    }
//}
//