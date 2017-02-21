/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.entity.entityarrow.*;
import net.thedragonteam.armorplus.entity.entityzombie.EntityEnderDragonZombie;
import net.thedragonteam.armorplus.entity.render.*;

import static net.thedragonteam.armorplus.APConfig.enableEnderDragonZombieSpawnEnd;

/**
 * net.thedragonteam.armorplus.entity
 * ArmorPlus created by sokratis12GR on 8/21/2016.
 * - TheDragonTeam
 */
public class ModEntities {

    //Arrows ID 0 to 5
    private static final int COAL_ARROW = 0;
    private static final int LAPIS_ARROW = 1;
    private static final int REDSTONE_ARROW = 2;
    private static final int LAVA_ARROW = 3;
    private static final int ENDER_DRAGON_ARROW = 4;
    //Mobs ID from 21 and up
    private static final int ENDER_DRAGON_ZOMBIE = 21;

    public static void init() {
        // Every entity in ArmorPlus has an ID (local to this mod)
        EntityRegistry.registerModEntity(EntityEnderDragonZombie.class, "ender_dragon_zombie", ENDER_DRAGON_ZOMBIE, ArmorPlus.instance, 64, 1, true, 0x721164, 0x00ff00);
        EntityRegistry.registerModEntity(EntityCoalArrow.class, "coal_arrow", COAL_ARROW, ArmorPlus.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityLapisArrow.class, "lapis_arrow", LAPIS_ARROW, ArmorPlus.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityRedstoneArrow.class, "redstone_arrow", REDSTONE_ARROW, ArmorPlus.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityLavaArrow.class, "lava_arrow", LAVA_ARROW, ArmorPlus.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityEnderDragonArrow.class, "ender_dragon_arrow", ENDER_DRAGON_ARROW, ArmorPlus.instance, 64, 1, true);

        // The mobs wont spawn automatically if we don't define biomes to spawn in
        // but it can still be spawned manually
        if (enableEnderDragonZombieSpawnEnd)
            EntityRegistry.addSpawn(EntityEnderDragonZombie.class, 1, 0, 1, EnumCreatureType.MONSTER, Biomes.SKY);

        // This is the loot table for the mobs
        LootTableList.register(EntityEnderDragonZombie.LOOT);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        //Mobs
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderDragonZombie.class, RenderEnderDragonZombie::new);
        //Arrows
        RenderingRegistry.registerEntityRenderingHandler(EntityCoalArrow.class, RenderCoalArrow::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityLapisArrow.class, RenderLapisArrow::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRedstoneArrow.class, RenderRedstoneArrow::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityLavaArrow.class, RenderLavaArrow::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderDragonArrow.class, RenderEnderDragonArrow::new);
    }
}