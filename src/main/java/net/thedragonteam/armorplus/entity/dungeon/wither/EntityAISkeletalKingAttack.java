package net.thedragonteam.armorplus.entity.dungeon.wither;

import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackRanged;

/**
 * ArmorPlus - Kotlin created by sokratis12GR
 * - TheDragonTeam
 */
public class EntityAISkeletalKingAttack extends EntityAIAttackRanged {

    public EntityAISkeletalKingAttack(IRangedAttackMob attacker, double movespeed, float maxAttackDistanceIn) {
        super(attacker, movespeed, 120, maxAttackDistanceIn);
    }

}
