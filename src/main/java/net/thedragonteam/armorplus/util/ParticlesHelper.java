/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ParticlesHelper {

    public static void createParticleCircleTopForLoop(EnumParticleTypes particleTypes, World world, Entity entity) {
        if (world.isRemote) {
            double y = entity.posY + 2.1D;
            double x = entity.posX;
            double z = entity.posZ;
            for (int i = 0; i <= 5; i++) {
                double iIn = i * 0.1D;
                double yPos = y + iIn;
                double xPositive = x + iIn;
                double zPositive = z + iIn;
                spawnParticle(entity, particleTypes, xPositive, yPos, z);
                spawnParticle(entity, particleTypes, x, yPos, zPositive);
                double xNegative = x - iIn;
                double zNegative = z - iIn;
                spawnParticle(entity, particleTypes, xNegative, yPos, z);
                spawnParticle(entity, particleTypes, x, yPos, zNegative);
            }
        }
    }

    public static void spawnParticle(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed) {
        entity.world.spawnParticle(particleType, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
    }

    public static void spawnParticle(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord) {
        spawnParticle(entity, particleType, xCoord, yCoord, zCoord, 0.0D, 0.0D, 0.0D);
    }

    public static void spawnParticle(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double allSpeed) {
        spawnParticle(entity, particleType, xCoord, yCoord, zCoord, allSpeed, allSpeed, allSpeed);
    }

    public static void spawnParticle(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double speed, double ySpeed, char xz) {
        switch (xz) {
            case 'x':
                spawnParticle(entity, particleType, xCoord, yCoord, zCoord, speed, ySpeed, 0.0D);
                break;
            case 'z':
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

    public static void spawnParticle(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double speed, char xyz) {
        switch (xyz) {
            case 'z':
                spawnParticle(entity, particleType, xCoord, yCoord, zCoord, 0.0D, 0.0D, speed);
                break;
            case 'x':
                spawnParticle(entity, particleType, xCoord, yCoord, zCoord, speed, 0.0D, 0.0D);
                break;
            case 'y':
                spawnParticle(entity, particleType, xCoord, yCoord, zCoord, 0.0D, speed, 0.0D);
                break;
        }
    }
}
