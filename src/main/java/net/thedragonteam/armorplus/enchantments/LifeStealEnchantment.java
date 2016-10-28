/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.MinecraftForge;
import net.thedragonteam.thedragonlib.util.LogHelper;

import static net.thedragonteam.armorplus.ARPConfig.debugMode;
import static net.thedragonteam.armorplus.ARPConfig.debugModeEnchantments;

public class LifeStealEnchantment extends Enchantment {

    public LifeStealEnchantment() {
        super(Rarity.RARE, EnumEnchantmentType.ALL, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        setRegistryName("life_steal");
        setName("life_steal");
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel) {
        return enchantmentLevel * 10;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 15;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    public boolean isTreasureEnchantment() {
        return true;
    }


    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench);
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return super.canApply(stack);
    }

    private float damageDealt;

    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {
        if (user.getHeldItemMainhand() != null && !(user.getHeldItemMainhand().getItem() instanceof ItemTool) || user.getHeldItemOffhand() != null && !(user.getHeldItemOffhand().getItem() instanceof ItemTool) || user.getHeldItemMainhand() != null && !(user.getHeldItemMainhand().getItem() instanceof ItemSword) || user.getHeldItemOffhand() != null && !(user.getHeldItemOffhand().getItem() instanceof ItemSword)) {
            if (level == 1) {
                user.heal(0.5F);
            }
            if (level == 2) {
                user.heal(1.5F);
            }
            if (level == 3) {
                user.heal(2.5F);
            }
        }
        if (user.getHeldItemMainhand() != null && user.getHeldItemMainhand().getItem() instanceof ItemTool || user.getHeldItemOffhand() != null && user.getHeldItemOffhand().getItem() instanceof ItemTool) {
            damageDealt = ((ItemTool) user.getHeldItemMainhand().getItem()).getToolMaterial().getDamageVsEntity();
            float damageDealtTool = damageDealt / 0.5F;
            if (level == 1 && damageDealtTool >= 0.0F) {
                float damageGained = damageDealtTool + 0.5F;
                float healedDamage = 0.0F;
                if (damageGained <= 4.0F) {
                    healedDamage = ((0.5F * damageGained) + 0.5F) - 0.312F;
                } else if (damageGained >= 4.5F && damageGained < 9.5F) {
                    healedDamage = (((0.5F * damageGained + 1.0F) / 2F) + 0.272F);
                } else if (damageGained >= 9.5F && damageGained < 14.5F) {
                    healedDamage = (((0.5F * damageGained - 5.0F) / 2F) + 0.411F);
                } else if (damageGained >= 14.5F && damageGained < 19.5F) {
                    healedDamage = (((0.5F * damageGained - 10.0F) / 2F) + 0.914F);
                } else if (damageGained >= 19.5F) {
                    healedDamage = (((0.5F * damageGained - 15.0F) / 2F) + 1.21F);
                }
                user.heal(healedDamage);
                if (debugMode && debugModeEnchantments) {
                    LogHelper.info("Level 1 Dealt Damage: " + damageDealt + " Final Damage: " + damageDealtTool + " Gained Damage: " + damageGained + " Healed Damage: " + healedDamage);
                }
            }
            if (level == 2 && damageDealtTool >= 0.0F) {
                float damageGained = damageDealtTool + 1.5F;
                float healedDamage = 0.0F;
                if (damageGained <= 4.0F) {
                    healedDamage = ((1.5F * damageGained) + 0.312F) - 0.34F;
                } else if (damageGained >= 4.5F && damageGained < 9.5F) {
                    healedDamage = (((1.5F * damageGained - 1.0F) / 2F) - 0.82F);
                } else if (damageGained >= 9.5F && damageGained < 14.5F) {
                    healedDamage = (((1.5F * damageGained - 4.0F) / 2F) - 1.11F);
                } else if (damageGained >= 14.5F && damageGained < 19.5F) {
                    healedDamage = (((1.5F * damageGained - 10.0F) / 2F) - 3.0F);
                } else if (damageGained >= 19.5F) {
                    healedDamage = (((1.5F * damageGained - 15.0F) / 2F) - 5.23F);
                }
                user.heal(healedDamage);
                if (debugMode && debugModeEnchantments) {
                    LogHelper.info("Level 2 Dealt Damage: " + damageDealt + " Final Damage: " + damageDealtTool + " Gained Damage: " + damageGained + " Healed Damage: " + healedDamage);
                }
            }
            if (level == 3 && damageDealtTool >= 0.0F) {
                float damageGained = damageDealtTool + 2.5F;
                float healedDamage = 0.0F;
                if (damageGained <= 4.0F) {
                    healedDamage = ((2.5F * damageGained) - 2.0F);
                } else if (damageGained >= 4.5F && damageGained < 9.5F) {
                    healedDamage = (((2.5F * damageGained - 3.0F) / 2F) - 2.53F);
                } else if (damageGained >= 9.5F && damageGained < 14.5F) {
                    healedDamage = (((2.5F * damageGained - 4.0F) / 2F) - 3.4F);
                } else if (damageGained >= 14.5F && damageGained < 19.5F) {
                    healedDamage = (((2.5F * damageGained - 10.0F) / 2F) - 6.5F);
                } else if (damageGained >= 19.5F) {
                    healedDamage = (((2.5F * damageGained - 15.0F) / 2F) - 10.5F);
                }
                user.heal(healedDamage);
                if (debugMode && debugModeEnchantments) {
                    LogHelper.info("Level 3 Dealt Damage: " + damageDealt + " Final Damage: " + damageDealtTool + " Gained Damage: " + damageGained + " Healed Damage: " + healedDamage);
                }
            }
        }
        if (user.getHeldItemMainhand() != null && user.getHeldItemMainhand().getItem() instanceof ItemSword || user.getHeldItemOffhand() != null && user.getHeldItemOffhand().getItem() instanceof ItemSword) {
            damageDealt = ((ItemSword) user.getHeldItemMainhand().getItem()).getDamageVsEntity();
            float damageDealtSword = damageDealt / 0.5F;
            if (level == 1 && damageDealtSword >= 0.0F) {
                float damageGained = damageDealtSword + 0.5F;
                float healedDamage = 0.0F;
                if (damageGained <= 4.5F) {
                    healedDamage = ((0.5F * damageGained) + 0.534F) + 0.135F;
                } else if (damageGained >= 5.0F && damageGained < 10.0F) {
                    healedDamage = (((0.5F * damageGained + 1.0F) / 2F) + 0.84F);
                } else if (damageGained >= 10.0F && damageGained < 15.0F) {
                    healedDamage = (((0.5F * damageGained - 4.0F) / 2F) + 0.525F);
                } else if (damageGained >= 15.0F && damageGained < 20.0F) {
                    healedDamage = (((0.5F * damageGained - 6.0F) / 2F) + 2.125F);
                } else if (damageGained >= 20.0F) {
                    healedDamage = (((0.5F * damageGained - 12.0F) / 2F) + 2.5F);
                }
                user.heal(healedDamage);
                if (debugMode && debugModeEnchantments) {
                    LogHelper.info("Level 1 Dealt Damage: " + damageDealt + " Final Damage: " + damageDealtSword + " Gained Damage: " + damageGained + " Healed Damage: " + healedDamage);
                }
            }
            if (level == 2 && damageDealtSword >= 0.0F) {
                float damageGained = damageDealtSword + 1.5F;
                float healedDamage = 0.0F;
                if (damageGained <= 4.5F) {
                    healedDamage = ((1.5F * damageGained) - 1.0F);
                } else if (damageGained >= 5.0F && damageGained < 10.0F) {
                    healedDamage = (((1.5F * damageGained - 1.0F) / 2F) - 1.0F);
                } else if (damageGained >= 10.0F && damageGained < 15.0F) {
                    healedDamage = (((1.5F * damageGained - 4.0F) / 2F) - 1.2F);
                } else if (damageGained >= 15.0F && damageGained < 20.0F) {
                    healedDamage = (((1.5F * damageGained - 10.0F) / 2F) - 3.0F);
                } else if (damageGained >= 20.0F) {
                    healedDamage = (((1.5F * damageGained - 15.0F) / 2F) - 3.3F);
                }
                user.heal(healedDamage);
                if (debugMode && debugModeEnchantments) {
                    LogHelper.info("Level 2 Dealt Damage: " + damageDealt + " Final Damage: " + damageDealtSword + " Gained Damage: " + damageGained + " Healed Damage: " + healedDamage);
                }
            }
            if (level == 3 && damageDealtSword >= 0.0F) {
                float damageGained = damageDealtSword + 2.5F;
                float healedDamage = 0.0F;
                if (damageGained <= 4.5F) {
                    healedDamage = ((2.5F * damageGained) - 2.0F);
                } else if (damageGained >= 5.0F && damageGained < 10.0F) {
                    healedDamage = (((2.5F * damageGained - 3.0F) / 2F) - 2.52F);
                } else if (damageGained >= 10.0F && damageGained < 15.0F) {
                    healedDamage = (((2.5F * damageGained - 4.0F) / 2F) - 5.5F);
                } else if (damageGained >= 15.0F && damageGained < 20.0F) {
                    healedDamage = (((2.5F * damageGained - 10.0F) / 2F) - 6.3F);
                } else if (damageGained >= 20.0F) {
                    healedDamage = (((2.5F * damageGained - 15.0F) / 2F) - 13.22F);
                }
                user.heal(healedDamage);
                if (debugMode && debugModeEnchantments) {
                    LogHelper.info("Level 3 Dealt Damage: " + damageDealt + " Final Damage: " + damageDealtSword + " Gained Damage: " + damageGained + " Healed Damage: " + healedDamage);
                }
            }
        }
    }
}
