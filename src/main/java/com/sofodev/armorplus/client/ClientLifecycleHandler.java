package com.sofodev.armorplus.client;

import com.sofodev.armorplus.common.registry.entity.dungeon.guardianoverlord.EntityGuardianOverlord;
import com.sofodev.armorplus.common.registry.entity.dungeon.guardianoverlord.RenderGuardianOverlord;
import com.sofodev.armorplus.common.registry.entity.dungeon.guardianoverlord.projectile.EntityFreezeBomb;
import com.sofodev.armorplus.common.registry.entity.dungeon.guardianoverlord.projectile.RenderFreezeBomb;
import com.sofodev.armorplus.common.registry.entity.dungeon.skeletalking.EntitySkeletalKing;
import com.sofodev.armorplus.common.registry.entity.dungeon.skeletalking.RenderSkeletalKing;
import com.sofodev.armorplus.common.registry.entity.dungeon.skeletalking.projectile.EntityWitherMinion;
import com.sofodev.armorplus.common.registry.entity.dungeon.skeletalking.projectile.RenderWitherMinion;
import com.sofodev.armorplus.common.registry.entity.entityarrow.*;
import com.sofodev.armorplus.common.registry.entity.mobs.EntityEnderDragonZombie;
import com.sofodev.armorplus.common.registry.entity.mobs.EntityIceGolem;
import com.sofodev.armorplus.common.registry.entity.render.RenderEnderDragonZombie;
import com.sofodev.armorplus.common.registry.entity.render.RenderIceGolem;
import com.sofodev.armorplus.common.registry.entity.render.RenderModdedArrow;
import com.sofodev.armorplus.common.registry.tileentity.TESRTrophy;
import com.sofodev.armorplus.common.registry.tileentity.TileTrophy;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientLifecycleHandler {

    public void clientSetup(final FMLClientSetupEvent event) {
        //TileEntities
        ClientRegistry.bindTileEntitySpecialRenderer(TileTrophy.class, new TESRTrophy());
        //Mobs
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderDragonZombie.class, RenderEnderDragonZombie::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityIceGolem.class, RenderIceGolem::new);
        //Arrows
        registerRenderingHandler(EntityCoalArrow.class, "coal");
        registerRenderingHandler(EntityLapisArrow.class, "lapis");
        registerRenderingHandler(EntityRedstoneArrow.class, "redstone");
        registerRenderingHandler(EntityLavaArrow.class, "lava");
        registerRenderingHandler(EntityEnderDragonArrow.class, "ender_dragon");
        //Bosses
        RenderingRegistry.registerEntityRenderingHandler(EntityGuardianOverlord.class, RenderGuardianOverlord::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySkeletalKing.class, RenderSkeletalKing::new);
        //Boss Projectiles
        RenderingRegistry.registerEntityRenderingHandler(EntityFreezeBomb.class, RenderFreezeBomb::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityWitherMinion.class, RenderWitherMinion::new);
    }

    private static void registerRenderingHandler(Class<? extends EntityArrow> entityClass, String name) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, rm -> new RenderModdedArrow<>(rm, name));
    }
}
