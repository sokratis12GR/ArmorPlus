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
                double ZPositive = z + iIn;
                spawnParticle(entity, particleTypes, xPositive, yPos, z);
                spawnParticle(entity, particleTypes, x, yPos, ZPositive);
                double xNegative = x - iIn;
                double ZNegative = z - iIn;
                spawnParticle(entity, particleTypes, xNegative, yPos, z);
                spawnParticle(entity, particleTypes, x, yPos, ZNegative);
            }
        }
    }

    public static void createParticleCircleTop(EnumParticleTypes particleTypes, World world, Entity entity) {
        if (world.isRemote) {
            double y = entity.posY + 2.1D;
            // Starting Point
            spawnParticle(entity, particleTypes, entity.posX, y, entity.posZ);
            // Positive X
            spawnParticle(entity, particleTypes, entity.posX + 0.1, y + 0.1, entity.posZ);
            spawnParticle(entity, particleTypes, entity.posX + 0.2, y + 0.2, entity.posZ);
            spawnParticle(entity, particleTypes, entity.posX + 0.3, y + 0.3, entity.posZ);
            spawnParticle(entity, particleTypes, entity.posX + 0.4, y + 0.4, entity.posZ);
            spawnParticle(entity, particleTypes, entity.posX + 0.5, y + 0.5, entity.posZ);
            spawnParticle(entity, particleTypes, entity.posX + 0.4, y + 0.6, entity.posZ);
            spawnParticle(entity, particleTypes, entity.posX + 0.3, y + 0.7, entity.posZ);
            spawnParticle(entity, particleTypes, entity.posX + 0.2, y + 0.8, entity.posZ);
            spawnParticle(entity, particleTypes, entity.posX + 0.1, y + 0.9, entity.posZ);
            spawnParticle(entity, particleTypes, entity.posX, y + 0.1, entity.posZ + 0.1);
            spawnParticle(entity, particleTypes, entity.posX, y + 0.2, entity.posZ + 0.2);
            spawnParticle(entity, particleTypes, entity.posX, y + 0.3, entity.posZ + 0.3);
            spawnParticle(entity, particleTypes, entity.posX, y + 0.4, entity.posZ + 0.4);
            spawnParticle(entity, particleTypes, entity.posX, y + 0.5, entity.posZ + 0.5);
            spawnParticle(entity, particleTypes, entity.posX, y + 0.6, entity.posZ + 0.4);
            spawnParticle(entity, particleTypes, entity.posX, y + 0.7, entity.posZ + 0.3);
            spawnParticle(entity, particleTypes, entity.posX, y + 0.8, entity.posZ + 0.2);
            spawnParticle(entity, particleTypes, entity.posX, y + 0.9, entity.posZ + 0.1);
            spawnParticle(entity, particleTypes, entity.posX - 0.1, y + 0.1, entity.posZ);
            spawnParticle(entity, particleTypes, entity.posX - 0.2, y + 0.2, entity.posZ);
            spawnParticle(entity, particleTypes, entity.posX - 0.3, y + 0.3, entity.posZ);
            spawnParticle(entity, particleTypes, entity.posX - 0.4, y + 0.4, entity.posZ);
            spawnParticle(entity, particleTypes, entity.posX - 0.5, y + 0.5, entity.posZ);
            spawnParticle(entity, particleTypes, entity.posX - 0.4, y + 0.6, entity.posZ);
            spawnParticle(entity, particleTypes, entity.posX - 0.3, y + 0.7, entity.posZ);
            spawnParticle(entity, particleTypes, entity.posX - 0.2, y + 0.8, entity.posZ);
            spawnParticle(entity, particleTypes, entity.posX - 0.1, y + 0.9, entity.posZ);
            spawnParticle(entity, particleTypes, entity.posX, y + 0.1, entity.posZ - 0.1);
            spawnParticle(entity, particleTypes, entity.posX, y + 0.2, entity.posZ - 0.2);
            spawnParticle(entity, particleTypes, entity.posX, y + 0.3, entity.posZ - 0.3);
            spawnParticle(entity, particleTypes, entity.posX, y + 0.4, entity.posZ - 0.4);
            spawnParticle(entity, particleTypes, entity.posX, y + 0.5, entity.posZ - 0.5);
            spawnParticle(entity, particleTypes, entity.posX, y + 0.6, entity.posZ - 0.4);
            spawnParticle(entity, particleTypes, entity.posX, y + 0.7, entity.posZ - 0.3);
            spawnParticle(entity, particleTypes, entity.posX, y + 0.8, entity.posZ - 0.2);
            spawnParticle(entity, particleTypes, entity.posX, y + 0.9, entity.posZ - 0.1);
            // Finish Point
            spawnParticle(entity, particleTypes, entity.posX, y + 1.0, entity.posZ);
        }
    }

    public static void spawnParticle(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord) {
        entity.worldObj.spawnParticle(particleType, xCoord, yCoord, zCoord, 0.0D, 0.0D, 0.0D);
    }

    public static void spawnParticleNormal(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed) {
        entity.worldObj.spawnParticle(particleType, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
    }

    public static void spawnParticleAll(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double allSpeed) {
        entity.worldObj.spawnParticle(particleType, xCoord, yCoord, zCoord, allSpeed, allSpeed, allSpeed);
    }

    public static void spawnParticleVertical(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double ySpeed) {
        entity.worldObj.spawnParticle(particleType, xCoord, yCoord, zCoord, 0.0D, ySpeed, 0.0D);
    }

    public static void spawnParticleLinearXY(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double ySpeed, double xSpeed) {
        entity.worldObj.spawnParticle(particleType, xCoord, yCoord, zCoord, xSpeed, ySpeed, 0.0D);
    }

    public static void spawnParticleLinearZY(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double ySpeed, double zSpeed) {
        entity.worldObj.spawnParticle(particleType, xCoord, yCoord, zCoord, 0.0D, ySpeed, zSpeed);
    }

    public static void spawnParticleHorizontalXZ(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xSpeed, double zSpeed) {
        entity.worldObj.spawnParticle(particleType, xCoord, yCoord, zCoord, xSpeed, 0.0D, zSpeed);
    }

    public static void spawnParticleXZ(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xzSpeed) {
        entity.worldObj.spawnParticle(particleType, xCoord, yCoord, zCoord, xzSpeed, 0.0D, xzSpeed);
    }

    public static void spawnParticleHorizontalZ(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double zSpeed) {
        entity.worldObj.spawnParticle(particleType, xCoord, yCoord, zCoord, 0.0D, 0.0D, zSpeed);
    }

    public static void spawnParticleHorizontalX(Entity entity, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xSpeed) {
        entity.worldObj.spawnParticle(particleType, xCoord, yCoord, zCoord, xSpeed, 0.0D, 0.0D);
    }
}
