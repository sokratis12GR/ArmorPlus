/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ParticlesHelper {
    private static Entity entity;

    public static void createParticleCircleTop(EnumParticleTypes particleTypes, World world, Entity entity) {
        ParticlesHelper.entity = entity;
        if (world.isRemote) {
            double y = entity.posY + 1.9D;
            // Starting Point
            spawnParticleFromPlayer(particleTypes, entity.posX, y, entity.posZ);
            // Positive X
            spawnParticleFromPlayer(particleTypes, entity.posX + 0.1, y + 0.1, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX + 0.2, y + 0.2, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX + 0.3, y + 0.3, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX + 0.4, y + 0.4, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX + 0.5, y + 0.5, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX + 0.4, y + 0.6, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX + 0.3, y + 0.7, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX + 0.2, y + 0.8, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX + 0.1, y + 0.9, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX, y + 0.1, entity.posZ + 0.1);
            spawnParticleFromPlayer(particleTypes, entity.posX, y + 0.2, entity.posZ + 0.2);
            spawnParticleFromPlayer(particleTypes, entity.posX, y + 0.3, entity.posZ + 0.3);
            spawnParticleFromPlayer(particleTypes, entity.posX, y + 0.4, entity.posZ + 0.4);
            spawnParticleFromPlayer(particleTypes, entity.posX, y + 0.5, entity.posZ + 0.5);
            spawnParticleFromPlayer(particleTypes, entity.posX, y + 0.6, entity.posZ + 0.4);
            spawnParticleFromPlayer(particleTypes, entity.posX, y + 0.7, entity.posZ + 0.3);
            spawnParticleFromPlayer(particleTypes, entity.posX, y + 0.8, entity.posZ + 0.2);
            spawnParticleFromPlayer(particleTypes, entity.posX, y + 0.9, entity.posZ + 0.1);
            spawnParticleFromPlayer(particleTypes, entity.posX - 0.1, y + 0.1, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX - 0.2, y + 0.2, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX - 0.3, y + 0.3, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX - 0.4, y + 0.4, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX - 0.5, y + 0.5, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX - 0.4, y + 0.6, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX - 0.3, y + 0.7, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX - 0.2, y + 0.8, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX - 0.1, y + 0.9, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX, y + 0.1, entity.posZ - 0.1);
            spawnParticleFromPlayer(particleTypes, entity.posX, y + 0.2, entity.posZ - 0.2);
            spawnParticleFromPlayer(particleTypes, entity.posX, y + 0.3, entity.posZ - 0.3);
            spawnParticleFromPlayer(particleTypes, entity.posX, y + 0.4, entity.posZ - 0.4);
            spawnParticleFromPlayer(particleTypes, entity.posX, y + 0.5, entity.posZ - 0.5);
            spawnParticleFromPlayer(particleTypes, entity.posX, y + 0.6, entity.posZ - 0.4);
            spawnParticleFromPlayer(particleTypes, entity.posX, y + 0.7, entity.posZ - 0.3);
            spawnParticleFromPlayer(particleTypes, entity.posX, y + 0.8, entity.posZ - 0.2);
            spawnParticleFromPlayer(particleTypes, entity.posX, y + 0.9, entity.posZ - 0.1);
            // Finish Point
            spawnParticleFromPlayer(particleTypes, entity.posX, y + 1.0, entity.posZ);
        }
    }

    public static void createParticleCircle(EnumParticleTypes particleTypes, World world, Entity entity) {
        ParticlesHelper.entity = entity;
        if (world.isRemote) {
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX + 0.1, entity.posY + 0.1, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX + 0.2, entity.posY + 0.2, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX + 0.3, entity.posY + 0.3, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX + 0.4, entity.posY + 0.4, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX + 0.5, entity.posY + 0.5, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX + 0.4, entity.posY + 0.6, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX + 0.3, entity.posY + 0.7, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX + 0.2, entity.posY + 0.8, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX + 0.1, entity.posY + 0.9, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY + 1.0, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY + 0.1, entity.posZ + 0.1);
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY + 0.2, entity.posZ + 0.2);
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY + 0.3, entity.posZ + 0.3);
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY + 0.4, entity.posZ + 0.4);
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY + 0.5, entity.posZ + 0.5);
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY + 0.6, entity.posZ + 0.4);
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY + 0.7, entity.posZ + 0.3);
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY + 0.8, entity.posZ + 0.2);
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY + 0.9, entity.posZ + 0.1);
            spawnParticleFromPlayer(particleTypes, entity.posX - 0.1, entity.posY + 0.1, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX - 0.2, entity.posY + 0.2, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX - 0.3, entity.posY + 0.3, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX - 0.4, entity.posY + 0.4, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX - 0.5, entity.posY + 0.5, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX - 0.4, entity.posY + 0.6, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX - 0.3, entity.posY + 0.7, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX - 0.2, entity.posY + 0.8, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX - 0.1, entity.posY + 0.9, entity.posZ);
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY + 0.1, entity.posZ - 0.1);
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY + 0.2, entity.posZ - 0.2);
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY + 0.3, entity.posZ - 0.3);
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY + 0.4, entity.posZ - 0.4);
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY + 0.5, entity.posZ - 0.5);
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY + 0.6, entity.posZ - 0.4);
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY + 0.7, entity.posZ - 0.3);
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY + 0.8, entity.posZ - 0.2);
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY + 0.9, entity.posZ - 0.1);
            spawnParticleFromPlayer(particleTypes, entity.posX, entity.posY + 1.0, entity.posZ);
        }
    }

    public static void spawnParticleFromPlayer(EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord) {
        entity.worldObj.spawnParticle(particleType, xCoord, yCoord, zCoord, 0.0D, 0.0D, 0.0D);
    }

    public static void spawnParticleFromPlayerNormal(EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed) {
        entity.worldObj.spawnParticle(particleType, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
    }

    public static void spawnParticleFromPlayerAll(EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double allSpeed) {
        entity.worldObj.spawnParticle(particleType, xCoord, yCoord, zCoord, allSpeed, allSpeed, allSpeed);
    }

    public static void spawnParticleFromPlayerVertical(EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double ySpeed) {
        entity.worldObj.spawnParticle(particleType, xCoord, yCoord, zCoord, 0.0D, ySpeed, 0.0D);
    }

    public static void spawnParticleFromPlayerLinearXY(EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double ySpeed, double xSpeed) {
        entity.worldObj.spawnParticle(particleType, xCoord, yCoord, zCoord, xSpeed, ySpeed, 0.0D);
    }

    public static void spawnParticleFromPlayerLinearZY(EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double ySpeed, double zSpeed) {
        entity.worldObj.spawnParticle(particleType, xCoord, yCoord, zCoord, 0.0D, ySpeed, zSpeed);
    }

    public static void spawnParticleFromPlayerHorizontalXZ(EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xSpeed, double zSpeed) {
        entity.worldObj.spawnParticle(particleType, xCoord, yCoord, zCoord, xSpeed, 0.0D, zSpeed);
    }

    public static void spawnParticleFromPlayerXZ(EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xzSpeed) {
        entity.worldObj.spawnParticle(particleType, xCoord, yCoord, zCoord, xzSpeed, 0.0D, xzSpeed);
    }

    public static void spawnParticleFromPlayerHorizontalZ(EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double zSpeed) {
        entity.worldObj.spawnParticle(particleType, xCoord, yCoord, zCoord, 0.0D, 0.0D, zSpeed);
    }

    public static void spawnParticleFromPlayerHorizontalX(EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xSpeed) {
        entity.worldObj.spawnParticle(particleType, xCoord, yCoord, zCoord, xSpeed, 0.0D, 0.0D);
    }
}
