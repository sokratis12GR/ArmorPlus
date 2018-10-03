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
        float damageDealt;
        if (user == null) {
            return;
        }
        ItemStack mainHand = user.getHeldItemMainhand();
        Item handItem = mainHand.getItem();
        if (mainHand.isEmpty()) return;
        if (!isCorrectItem(handItem)) {
            user.heal(lvl.healingFactor);
        } else if (handItem instanceof ItemTool) {
            damageDealt = ((ItemTool) handItem).toolMaterial.getAttackDamage();
            user.heal(level * softCap(damageDealt, 10, 1) / 4);
        } else if (handItem instanceof ItemSword) {
            damageDealt = ((ItemSword) handItem).getAttackDamage();
            user.heal(level * softCap(damageDealt, 10, 1) / 4);
        }
    }

    ///
    ///julian's function for softCap
    ///
    private float softCap(float value, float max, float scale) {
        if (value <= max) {
            return value;
        }
        float space = max * scale;
        float offset = value - max;
        return max + space * offset / (space + offset);
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
    }
}
