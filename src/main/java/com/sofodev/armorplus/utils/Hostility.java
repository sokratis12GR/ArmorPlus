package com.sofodev.armorplus.utils;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;

/**
 * Who fights whom, for abilities that pick their own targets (Seeker, Ricochet, ...):
 * <ul>
 *     <li>Players and friendly mobs go after hostile mobs. Neutral mobs are left alone until they are angry at the shooter.</li>
 *     <li>Hostile mobs (and neutral ones angry at a player) go after players in survival and adventure, never other monsters.</li>
 *     <li>Whatever the shooter is fighting, or is fighting the shooter, is always fair game.</li>
 * </ul>
 */
public final class Hostility {

    private Hostility() {
    }

    /** @return true if the entity fights players: a monster, or a neutral mob angry at a player */
    public static boolean isHostile(LivingEntity entity) {
        if (entity instanceof Player) return false;
        if (entity instanceof NeutralMob) return entity instanceof Mob mob && mob.getTarget() instanceof Player;
        return entity instanceof Enemy;
    }

    /** @return true if an ability of the shooter may go after the candidate */
    public static boolean isTarget(LivingEntity shooter, LivingEntity candidate) {
        if (candidate == shooter || !candidate.isAlive() || candidate.isAlliedTo(shooter)) return false;
        if (shooter instanceof Mob mob && mob.getTarget() == candidate) return true;
        if (candidate instanceof Mob mob && mob.getTarget() == shooter) return true;
        if (isHostile(shooter)) return candidate instanceof Player player && !player.isCreative() && !player.isSpectator();
        return isHostile(candidate);
    }

    /** @return the strength of the shooter's ranged runes: full for players, "mobRangedEffectiveness" for mobs */
    public static double rangedEffectiveness(LivingEntity shooter) {
        return shooter instanceof Player ? 1.0 : ArmorPlusConfig.mobRangedEffectiveness.get();
    }

    /** @return the damage multiplier scaled to the shooter's strength, only the bonus part is scaled */
    public static double scaleBonus(double multiplier, LivingEntity shooter) {
        return 1.0 + (multiplier - 1.0) * rangedEffectiveness(shooter);
    }
}
