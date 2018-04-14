package net.thedragonteam.armorplus.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

import static net.thedragonteam.armorplus.enchantments.EnchantmentBase.Levels.*;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class LifeStealEnchantment extends EnchantmentBase {

    public LifeStealEnchantment() {
        super("life_steal", Enchantment.Rarity.RARE, EnumEnchantmentType.BREAKABLE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND},
            1, 3, 10, 15, true, true
        );
    }

    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {
        Levels levelIn = values()[level];
        float damageDealt;
        if (user != null) {
            if (((user.getHeldItemMainhand().getCount() > 0) && !(user.getHeldItemMainhand().getItem() instanceof
                ItemTool)) || ((user.getHeldItemOffhand().getCount() > 0) && !(user.getHeldItemOffhand().getItem() instanceof
                ItemTool)) || ((user.getHeldItemMainhand().getCount() > 0) && !(user.getHeldItemMainhand().getItem() instanceof
                ItemSword)) || ((user.getHeldItemOffhand().getCount() > 0) && !(user.getHeldItemOffhand().getItem() instanceof ItemSword))) {
                switch (levelIn) {
                    case ONE:
                        user.heal(0.5f);
                        break;
                    case TWO:
                        user.heal(1.5f);
                        break;
                    case THREE:
                        user.heal(2.5f);
                        break;
                }
            } else if (((user.getHeldItemMainhand().getCount() > 0) && (user.getHeldItemMainhand().getItem() instanceof ItemTool)) ||
                ((user.getHeldItemOffhand().getCount() > 0) && (user.getHeldItemOffhand().getItem() instanceof ItemTool))) {
                damageDealt = ((ItemTool) user.getHeldItemMainhand().getItem()).toolMaterial.getAttackDamage();
                float damageDealtTool = damageDealt / 0.5f;
                if (levelIn == Levels.ONE) {
                    if (damageDealtTool >= 0.0f) {
                        float damageGained = damageDealtTool + 0.5f;
                        float healedDamage = (damageGained <= 4.0f) ? 0.5f * damageGained + 0.5f - 0.312f
                            : (damageGained >= 4.5f && damageGained < 9.5f) ? (0.5f * damageGained + 1.0f) / 2f + 0.272f
                            : (damageGained >= 9.5f && damageGained < 14.5f) ? (0.5f * damageGained - 5.0f) / 2f + 0.411f
                            : (damageGained >= 14.5f && damageGained < 19.5f) ? (0.5f * damageGained - 10.0f) / 2f + 0.914f
                            : (damageGained >= 19.5f) ? (0.5f * damageGained - 15.0f) / 2f + 1.21f : 0.0f;
                        user.heal(healedDamage);
                    }

                } else if (levelIn == Levels.TWO) {
                    if (damageDealtTool >= 0.0f) {
                        float damageGained = damageDealtTool + 1.5f;
                        float healedDamage = (damageGained <= 4.0f) ? 1.5f * damageGained + 0.312f - 0.34f
                            : (damageGained >= 4.5f && damageGained < 9.5f) ? (1.5f * damageGained - 1.0f) / 2f - 0.82f
                            : (damageGained >= 9.5f && damageGained < 14.5f) ? (1.5f * damageGained - 4.0f) / 2f - 1.11f
                            : (damageGained >= 14.5f && damageGained < 19.5f) ? (1.5f * damageGained - 10.0f) / 2f - 3.0f
                            : (damageGained >= 19.5f) ? (1.5f * damageGained - 15.0f) / 2f - 5.23f
                            : 0.0f;
                        user.heal(healedDamage);
                    }

                } else if (levelIn == Levels.THREE) {
                    if (damageDealtTool >= 0.0f) {
                        float damageGained = damageDealtTool + 2.5f;
                        float healedDamage = (damageGained <= 4.0f) ? 2.5f * damageGained - 2.0f
                            : (damageGained >= 4.5f && damageGained < 9.5f) ? (2.5f * damageGained - 3.0f) / 2f - 2.53f
                            : (damageGained >= 9.5f && damageGained < 14.5f) ? (2.5f * damageGained - 4.0f) / 2f - 3.4f
                            : (damageGained >= 14.5f && damageGained < 19.5f) ? (2.5f * damageGained - 10.0f) / 2f - 6.5f
                            : (damageGained >= 19.5f) ? (2.5f * damageGained - 15.0f) / 2f - 10.5f
                            : 0.0f;
                        user.heal(healedDamage);
                    }
                }
            } else if (user.getHeldItemMainhand().getCount() > 0 && user.getHeldItemMainhand().getItem() instanceof ItemSword ||
                user.getHeldItemOffhand().getCount() > 0 && user.getHeldItemOffhand().getItem() instanceof ItemSword) {
                damageDealt = ((ItemSword) user.getHeldItemMainhand().getItem()).getAttackDamage();
                float damageDealtSword = damageDealt / 0.5f;
                if (levelIn == ONE) {
                    if (damageDealtSword >= 0.0f) {
                        float damageGained = damageDealtSword + 0.5f;
                        float healedDamage = (damageGained <= 4.5f) ? 0.5f * damageGained + 0.534f + 0.135f
                            : (damageGained >= 5.0f && damageGained < 10.0f) ? (0.5f * damageGained + 1.0f) / 2f + 0.84f
                            : (damageGained >= 10.0f && damageGained < 15.0f) ? (0.5f * damageGained - 4.0f) / 2f + 0.525f
                            : (damageGained >= 15.0f && damageGained < 20.0f) ? (0.5f * damageGained - 6.0f) / 2f + 2.125f
                            : (damageGained >= 20.0f) ? (0.5f * damageGained - 12.0f) / 2f + 2.5f
                            : 0.0f;
                        user.heal(healedDamage);
                    }
                } else if (levelIn == TWO) {
                    if (damageDealtSword >= 0.0f) {
                        float damageGained = damageDealtSword + 1.5f;
                        float healedDamage = (damageGained <= 4.5f) ? 1.5f * damageGained - 1.0f
                            : (damageGained >= 5.0f && damageGained < 10.0f) ? (1.5f * damageGained - 1.0f) / 2f - 1.0f
                            : (damageGained >= 10.0f && damageGained < 15.0f) ? (1.5f * damageGained - 4.0f) / 2f - 1.2f
                            : (damageGained >= 15.0f && damageGained < 20.0f) ? (1.5f * damageGained - 10.0f) / 2f - 3.0f
                            : (damageGained >= 20.0f) ? (1.5f * damageGained - 15.0f) / 2f - 3.3f
                            : 0.0f;
                        user.heal(healedDamage);
                    }
                } else if (levelIn == THREE) {
                    if (damageDealtSword >= 0.0f) {
                        float damageGained = damageDealtSword + 2.5f;
                        float healedDamage = (damageGained <= 4.5f) ? 2.5f * damageGained - 2.0f
                            : (damageGained >= 5.0f && damageGained < 10.0f) ? (2.5f * damageGained - 3.0f) / 2f - 2.52f
                            : (damageGained >= 10.0f && damageGained < 15.0f) ? (2.5f * damageGained - 4.0f) / 2f - 5.5f
                            : (damageGained >= 15.0f && damageGained < 20.0f) ? (2.5f * damageGained - 10.0f) / 2f - 6.3f
                            : (damageGained >= 20.0f) ? (2.5f * damageGained - 15.0f) / 2f - 13.22f
                            : 0.0f;
                        user.heal(healedDamage);
                    }
                }
            }
        }
    }
}
