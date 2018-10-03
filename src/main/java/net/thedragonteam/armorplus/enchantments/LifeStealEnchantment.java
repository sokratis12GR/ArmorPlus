package net.thedragonteam.armorplus.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

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
        Levels lvl = Levels.values()[level];
        float effectPower = lvl.healingFactor;
        float damageDealt;
        if (user == null) {
            return;
        }
        ItemStack mainHand = user.getHeldItemMainhand();
        ItemStack offHand = user.getHeldItemOffhand();
        if (mainHand.isEmpty() || offHand.isEmpty()) return;
        if (!isCorrectItem(mainHand.getItem()) || !isCorrectItem(offHand.getItem())) {
            user.heal(lvl.healingFactor);
        } else if (offHand.getItem() instanceof ItemTool || mainHand.getItem() instanceof ItemTool) {
            damageDealt = ((ItemTool) offHand.getItem()).toolMaterial.getAttackDamage();
            float damageDealtTool = damageDealt / 0.5f;
            float damageGained = damageDealtTool + effectPower;
            float healedDamage = lifeStealObtained(lvl, damageGained);
            user.heal(healedDamage);
        }
    }

    private float lifeStealObtained(Levels lvl, float damageGained) {
        float weakest = 4.5f, weak = 5.0f, average = 10.0f, strong = 15.0f, strongest = 20.0f;
        if (damageGained <= weakest) {
            return calcLifeObtained(lvl, damageGained, 0);
        } else if (damageGained >= weak && damageGained < average) {
            return calcLifeObtained(lvl, damageGained, 1) / capped(0);
        } else if (damageGained >= average && damageGained < strong) {
            return calcLifeObtained(lvl, damageGained, 2) / capped(1);
        } else if (damageGained >= strong && damageGained < strongest) {
            return calcLifeObtained(lvl, damageGained, 3) / capped(2);
        } else if (damageGained >= strongest) {
            return calcLifeObtained(lvl, damageGained, 4) / capped(3);
        }
        return 0.0f;
    }

    private float capped(int power) {
        float[] reductions = new float[]{1f, 1.5f, 3f, 6f};
        return 2f - reductions[power];
    }

    private float calcLifeObtained(Levels lvl, float damageGained, int power) {
        float effectPower = lvl.healingFactor;
        return effectPower * damageGained - lvl.getReductions()[power];
    }

    private boolean isCorrectItem(Item item) {
        return item instanceof ItemTool || item instanceof ItemSword;
    }

    public enum Levels {
        ZERO(0.0f),
        ONE(0.5f),
        TWO(1.5f),
        THREE(2.5f),
        ;

        public final float healingFactor;

        Levels(float healingFactor) {
            this.healingFactor = healingFactor;
        }

        public float[] getReductions() {
            return new float[]{1f, 2f, 4f, 10f, 15f};
        }
    }
}
