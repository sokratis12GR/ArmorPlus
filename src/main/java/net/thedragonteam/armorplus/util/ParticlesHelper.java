/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticlesHelper {

    public static void spawnParticle(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed) {
        entity.world.spawnParticle(particleType, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
    }

    public static void spawnParticle(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord) {
        spawnParticle(entity, particleType, xCoord, yCoord, zCoord, 0.0D, 0.0D, 0.0D);
    }

    public static void spawnParticle(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double allSpeed) {
        spawnParticle(entity, particleType, xCoord, yCoord, zCoord, allSpeed, allSpeed, allSpeed);
    }

    public static void spawnParticle(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double speed, double ySpeed, Pos pos) {
        switch (pos) {
            case POS_X:
                spawnParticle(entity, particleType, xCoord, yCoord, zCoord, speed, ySpeed, 0.0D);
                break;
            case POS_Z:
                spawnParticle(entity, particleType, xCoord, yCoord, zCoord, 0.0D, ySpeed, speed);
                break;
        }
    }

    public static void spawnParticle(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xSpeed, double zSpeed) {
        spawnParticle(entity, particleType, xCoord, yCoord, zCoord, xSpeed, 0.0D, zSpeed);
    }

    public static void spawnParticle(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xzSpeed, boolean xz) {
        if (xz)
            spawnParticle(entity, particleType, xCoord, yCoord, zCoord, xzSpeed, xzSpeed);
    }

    public static void spawnParticle(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double speed, Pos pos) {
        switch (pos) {
            case POS_X:
                spawnParticle(entity, particleType, xCoord, yCoord, zCoord, speed, 0.0D, 0.0D);
                break;
            case POS_Y:
                spawnParticle(entity, particleType, xCoord, yCoord, zCoord, 0.0D, speed, 0.0D);
                break;
            case POS_Z:
                spawnParticle(entity, particleType, xCoord, yCoord, zCoord, 0.0D, 0.0D, speed);
                break;
        }
    }

    public EnumParticleTypes getParticle(String name) {
        return EnumParticleTypes.getByName(name);
    }

    public EnumParticleTypes getParticle(int id) {
        return EnumParticleTypes.getParticleFromId(id);
    }

    public enum Pos {
        POS_X,
        POS_Y,
        POS_Z
    }
}
