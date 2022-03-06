//package com.sofodev.armorplus.registry.entities.bosses.manager;
//
//import com.google.common.collect.*;
//import com.sofodev.armorplus.registry.ModEntities;
//import com.sofodev.armorplus.registry.entities.bosses.DemonicDragonEntity;
//import com.sofodev.armorplus.registry.entities.bosses.extras.SpecificServerBossInfo;
//import net.minecraft.advancements.CriteriaTriggers;
//import net.minecraft.block.Blocks;
//import net.minecraft.block.pattern.BlockMatcher;
//import net.minecraft.block.pattern.BlockPatternBuilder;
//import net.minecraft.core.BlockPos;
//import net.minecraft.entity.boss.dragon.EnderDragonEntity;
//import net.minecraft.entity.item.EnderCrystalEntity;
//import net.minecraft.entity.player.ServerPlayer;
//import net.minecraft.nbt.*;
//import net.minecraft.network.chat.TranslatableComponent;
//import net.minecraft.server.level.ServerBossEvent;
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.tileentity.EndPortalTileEntity;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.Unit;
//import net.minecraft.util.math.AxisAlignedBB;
//import net.minecraft.util.math.ChunkPos;
//import net.minecraft.util.math.MathHelper;
//import net.minecraft.world.BossEvent;
//import net.minecraft.world.chunk.Chunk;
//import net.minecraft.world.chunk.ChunkStatus;
//import net.minecraft.world.chunk.IChunk;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.gen.Heightmap;
//import net.minecraft.world.gen.feature.EndPodiumFeature;
//import net.minecraft.world.gen.feature.EndSpikeFeature;
//import net.minecraft.world.gen.feature.Features;
//import net.minecraft.world.gen.feature.IFeatureConfig;
//import net.minecraft.world.level.block.state.pattern.BlockPattern;
//import net.minecraft.world.server.ChunkHolder;
//import net.minecraft.world.server.TicketType;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.annotation.Nullable;
//import java.util.*;
//import java.util.function.Predicate;
//
//import static java.util.stream.Collectors.toList;
//
//public class DragonFightManager {
//    private static final Logger LOGGER = LogManager.getLogger();
//    private static final Predicate<Entity> VALID_PLAYER = EntityPredicates.ENTITY_STILL_ALIVE.and(EntityPredicates.withinDistance(0.0D, 128.0D, 0.0D, 192.0D));
//    private final SpecificServerBossInfo bossInfo = (SpecificServerBossInfo) (new ServerBossEvent(new TranslatableComponent("entity.armorplus.demonic_dragon"), BossEvent.BossBarColor.PINK, BossEvent.BossBarOverlay.PROGRESS)).setPlayBossMusic(true).setCreateWorldFog(true);
//    private final ServerLevel world;
//    private final List<Integer> gateways = Lists.newArrayList();
//    private final BlockPattern portalPattern;
//    private int ticksSinceDragonSeen;
//    private int aliveCrystals;
//    private int ticksSinceCrystalsScanned;
//    private int ticksSinceLastPlayerScan;
//    private boolean dragonKilled;
//    private boolean previouslyKilled;
//    private UUID dragonUniqueId;
//    private boolean scanForLegacyFight = true;
//    private BlockPos exitPortalLocation;
//    private DragonSpawnState respawnState;
//    private int respawnStateTicks;
//    private List<EnderCrystalEntity> crystals;
//
//    public DragonFightManager(ServerLevel world, long seed, CompoundTag dragonFightNBT) {
//        this.world = world;
//        if (dragonFightNBT.contains("DragonKilled", 99)) {
//            if (dragonFightNBT.hasUUID("Dragon")) {
//                this.dragonUniqueId = dragonFightNBT.getUUID("Dragon");
//            }
//
//            this.dragonKilled = dragonFightNBT.getBoolean("DragonKilled");
//            this.previouslyKilled = dragonFightNBT.getBoolean("PreviouslyKilled");
//            this.scanForLegacyFight = !dragonFightNBT.getBoolean("LegacyScanPerformed"); // Forge: fix MC-105080
//            if (dragonFightNBT.getBoolean("IsRespawning")) {
//                this.respawnState = DragonSpawnState.START;
//            }
//
//            if (dragonFightNBT.contains("ExitPortalLocation", 10)) {
//                this.exitPortalLocation = NbtUtils.readBlockPos(dragonFightNBT.getCompound("ExitPortalLocation"));
//            }
//        } else {
//            this.dragonKilled = true;
//            this.previouslyKilled = true;
//        }
//
//        if (dragonFightNBT.contains("Gateways", 9)) {
//            ListNBT listnbt = dragonFightNBT.getList("Gateways", 3);
//
//            for (int i = 0; i < listnbt.size(); ++i) {
//                this.gateways.add(listnbt.getInt(i));
//            }
//        } else {
//            this.gateways.addAll(ContiguousSet.create(Range.closedOpen(0, 20), DiscreteDomain.integers()));
//            Collections.shuffle(this.gateways, new Random(seed));
//        }
//
//        this.portalPattern = BlockPatternBuilder.start().aisle("       ", "       ", "       ", "   #   ", "       ", "       ", "       ").aisle("       ", "       ", "       ", "   #   ", "       ", "       ", "       ").aisle("       ", "       ", "       ", "   #   ", "       ", "       ", "       ").aisle("  ###  ", " #   # ", "#     #", "#  #  #", "#     #", " #   # ", "  ###  ").aisle("       ", "  ###  ", " ##### ", " ##### ", " ##### ", "  ###  ", "       ").where('#', CachedBlockInfo.hasState(BlockMatcher.forBlock(Blocks.BEDROCK))).build();
//    }
//
//    public CompoundTag write() {
//        CompoundTag CompoundTag = new CompoundTag();
//        if (this.dragonUniqueId != null) {
//            CompoundTag.putUUID("Dragon", this.dragonUniqueId);
//        }
//
//        CompoundTag.putBoolean("DragonKilled", this.dragonKilled);
//        CompoundTag.putBoolean("PreviouslyKilled", this.previouslyKilled);
//        CompoundTag.putBoolean("LegacyScanPerformed", !this.scanForLegacyFight); // Forge: fix MC-105080
//        if (this.exitPortalLocation != null) {
//            CompoundTag.put("ExitPortalLocation", NBTUtil.writeBlockPos(this.exitPortalLocation));
//        }
//
//        ListNBT listnbt = new ListNBT();
//
//        for (int i : this.gateways) {
//            listnbt.add(IntNBT.valueOf(i));
//        }
//
//        CompoundTag.put("Gateways", listnbt);
//        return CompoundTag;
//    }
//
//    public void tick() {
//        this.bossInfo.setVisible(!this.dragonKilled);
//        if (++this.ticksSinceLastPlayerScan >= 20) {
//            this.updatePlayers();
//            this.ticksSinceLastPlayerScan = 0;
//        }
//
//        if (!this.bossInfo.getPlayers().isEmpty()) {
//            this.world.getChunkSource().registerTickingTicket(TicketType.DRAGON, new ChunkPos(0, 0), 9, Unit.INSTANCE);
//            boolean flag = this.isFightAreaLoaded();
//            if (this.scanForLegacyFight && flag) {
//                this.scanForLegacyFight();
//                this.scanForLegacyFight = false;
//            }
//
//            if (this.respawnState != null) {
//                if (this.crystals == null && flag) {
//                    this.respawnState = null;
//                    this.tryRespawnDragon();
//                }
//
//                this.respawnState.process(this.world, this, this.crystals, this.respawnStateTicks++, this.exitPortalLocation);
//            }
//
//            if (!this.dragonKilled) {
//                if ((this.dragonUniqueId == null || ++this.ticksSinceDragonSeen >= 1200) && flag) {
//                    this.findOrCreateDragon();
//                    this.ticksSinceDragonSeen = 0;
//                }
//
//                if (++this.ticksSinceCrystalsScanned >= 100 && flag) {
//                    this.findAliveCrystals();
//                    this.ticksSinceCrystalsScanned = 0;
//                }
//            }
//        } else {
//            this.world.getChunkSource().releaseTickingTicket(TicketType.DRAGON, new ChunkPos(0, 0), 9, Unit.INSTANCE);
//        }
//
//    }
//
//    private void scanForLegacyFight() {
//        LOGGER.info("Scanning for legacy world dragon fight...");
//        boolean wasPreviouslyKilled = this.exitPortalExists();
//        if (wasPreviouslyKilled) {
//            LOGGER.info("Found that the dragon has been killed in this world already.");
//            this.previouslyKilled = true;
//        } else {
//            LOGGER.info("Found that the dragon has not yet been killed in this world.");
//            this.previouslyKilled = false;
//            if (this.findExitPortal() == null) {
//                this.generatePortal(false);
//            }
//        }
//
//        List<DemonicDragonEntity> list = this.getDragons(world);
//        if (list.isEmpty()) {
//            this.dragonKilled = true;
//        } else {
//            DemonicDragonEntity demonicDragonEntity = list.get(0);
//            this.dragonUniqueId = demonicDragonEntity.getUUID();
//            LOGGER.info("Found that there's a dragon still alive ({})", demonicDragonEntity);
//            this.dragonKilled = false;
//            if (!wasPreviouslyKilled) {
//                LOGGER.info("But we didn't have a portal, let's remove it.");
//                demonicDragonEntity.remove();
//                this.dragonUniqueId = null;
//            }
//        }
//
//        if (!this.previouslyKilled && this.dragonKilled) {
//            this.dragonKilled = false;
//        }
//
//    }
//
//    private void findOrCreateDragon() {
//        List<EnderDragonEntity> list = this.world.getDragons();
//        if (list.isEmpty()) {
//            LOGGER.debug("Haven't seen the dragon, respawning it");
//            this.createNewDragon();
//        } else {
//            LOGGER.debug("Haven't seen our dragon, but found another one to use.");
//            this.dragonUniqueId = list.get(0).getUUID();
//        }
//
//    }
//
//    protected void setRespawnState(DragonSpawnState state) {
//        if (this.respawnState == null) {
//            throw new IllegalStateException("Dragon respawn isn't in progress, can't skip ahead in the animation.");
//        } else {
//            this.respawnStateTicks = 0;
//            if (state == DragonSpawnState.END) {
//                this.respawnState = null;
//                this.dragonKilled = false;
//                DemonicDragonEntity demonicDragonEntity = this.createNewDragon();
//
//                for (ServerPlayer serverPlayer : this.bossInfo.getPlayers()) {
//                    CriteriaTriggers.SUMMONED_ENTITY.trigger(serverPlayer, demonicDragonEntity);
//                }
//            } else {
//                this.respawnState = state;
//            }
//
//        }
//    }
//
//    private boolean exitPortalExists() {
//        for (int i = -8; i <= 8; ++i) {
//            for (int j = -8; j <= 8; ++j) {
//                Chunk chunk = this.world.getChunk(i, j);
//
//                for (TileEntity tileentity : chunk.getBlockEntities().values()) {
//                    if (tileentity instanceof EndPortalTileEntity) {
//                        return true;
//                    }
//                }
//            }
//        }
//
//        return false;
//    }
//
//    @Nullable
//    private BlockPattern.PatternHelper findExitPortal() {
//        for (int i = -8; i <= 8; ++i) {
//            for (int j = -8; j <= 8; ++j) {
//                Chunk chunk = this.world.getChunk(i, j);
//
//                for (TileEntity blockEntity : chunk.getBlockEntities().values()) {
//                    if (blockEntity instanceof EndPortalTileEntity) {
//                        BlockPattern.PatternHelper blockpattern$patternhelper = this.portalPattern.find(this.world, blockEntity.getBlockPos());
//                        if (blockpattern$patternhelper != null) {
//                            BlockPos blockpos = blockpattern$patternhelper.getBlock(3, 3, 3).getPos();
//                            if (this.exitPortalLocation == null && blockpos.getX() == 0 && blockpos.getZ() == 0) {
//                                this.exitPortalLocation = blockpos;
//                            }
//
//                            return blockpattern$patternhelper;
//                        }
//                    }
//                }
//            }
//        }
//
//        int k = this.world.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, EndPodiumFeature.END_PODIUM_LOCATION).getY();
//
//        for (int l = k; l >= 0; --l) {
//            BlockPattern.PatternHelper blockpattern$patternhelper1 = this.portalPattern.find(this.world, new BlockPos(EndPodiumFeature.END_PODIUM_LOCATION.getX(), l, EndPodiumFeature.END_PODIUM_LOCATION.getZ()));
//            if (blockpattern$patternhelper1 != null) {
//                if (this.exitPortalLocation == null) {
//                    this.exitPortalLocation = blockpattern$patternhelper1.getBlock(3, 3, 3).getPos();
//                }
//
//                return blockpattern$patternhelper1;
//            }
//        }
//
//        return null;
//    }
//
//    private boolean isFightAreaLoaded() {
//        for (int i = -8; i <= 8; ++i) {
//            for (int j = 8; j <= 8; ++j) {
//                IChunk ichunk = this.world.getChunk(i, j, ChunkStatus.FULL, false);
//                if (!(ichunk instanceof Chunk)) {
//                    return false;
//                }
//
//                ChunkHolder.LocationType chunkholder$locationtype = ((Chunk) ichunk).getFullStatus();
//                if (!chunkholder$locationtype.isOrAfter(ChunkHolder.LocationType.TICKING)) {
//                    return false;
//                }
//            }
//        }
//
//        return true;
//    }
//
//    private void updatePlayers() {
//        Set<ServerPlayer> set = Sets.newHashSet();
//
//        for (ServerPlayer serverPlayer : this.world.getPlayers(VALID_PLAYER)) {
//            this.bossInfo.addPlayer(serverPlayer);
//            set.add(serverPlayer);
//        }
//
//        Set<ServerPlayer> set1 = Sets.newHashSet(this.bossInfo.getPlayers());
//        set1.removeAll(set);
//
//        for (ServerPlayer serverPlayer1 : set1) {
//            this.bossInfo.removePlayer(serverPlayer1);
//        }
//
//    }
//
//    private void findAliveCrystals() {
//        this.ticksSinceCrystalsScanned = 0;
//        this.aliveCrystals = 0;
//
//        for (EndSpikeFeature.EndSpike endspikefeature$endspike : EndSpikeFeature.getSpikesForLevel(this.world)) {
//            this.aliveCrystals += this.world.getEntitiesOfClass(EnderCrystalEntity.class, endspikefeature$endspike.getTopBoundingBox()).size();
//        }
//
//        LOGGER.debug("Found {} end crystals still alive", (int) this.aliveCrystals);
//    }
//
//    public void processDragonDeath(EnderDragonEntity dragon) {
//        if (dragon.getUUID().equals(this.dragonUniqueId)) {
//            this.bossInfo.setPercent(0.0F);
//            this.bossInfo.setVisible(false);
//            this.generatePortal(true);
//            this.spawnNewGateway();
//            if (!this.previouslyKilled) {
//                this.world.setBlockAndUpdate(this.world.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, EndPodiumFeature.END_PODIUM_LOCATION), Blocks.DRAGON_EGG.defaultBlockState());
//            }
//
//            this.previouslyKilled = true;
//            this.dragonKilled = true;
//        }
//
//    }
//
//    private void spawnNewGateway() {
//        if (!this.gateways.isEmpty()) {
//            int i = this.gateways.remove(this.gateways.size() - 1);
//            int j = MathHelper.floor(96.0D * Math.cos(2.0D * (-Math.PI + 0.15707963267948966D * (double) i)));
//            int k = MathHelper.floor(96.0D * Math.sin(2.0D * (-Math.PI + 0.15707963267948966D * (double) i)));
//            this.generateGateway(new BlockPos(j, 75, k));
//        }
//    }
//
//    private void generateGateway(BlockPos pos) {
//        this.world.levelEvent(3000, pos, 0);
//        Features.END_GATEWAY_DELAYED.place(this.world, this.world.getChunkSource().getGenerator(), new Random(), pos);
//    }
//
//    private void generatePortal(boolean active) {
//        EndPodiumFeature endpodiumfeature = new EndPodiumFeature(active);
//        if (this.exitPortalLocation == null) {
//            for (this.exitPortalLocation = this.world.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EndPodiumFeature.END_PODIUM_LOCATION).below(); this.world.getBlockState(this.exitPortalLocation).is(Blocks.BEDROCK) && this.exitPortalLocation.getY() > this.world.getSeaLevel(); this.exitPortalLocation = this.exitPortalLocation.below()) {
//            }
//        }
//
//        endpodiumfeature.configured(IFeatureConfig.NONE).place(this.world, this.world.getChunkSource().getGenerator(), new Random(), this.exitPortalLocation);
//    }
//
//    private DemonicDragonEntity createNewDragon() {
//        this.world.getChunkAt(new BlockPos(0, 128, 0));
//        DemonicDragonEntity demonicDragonEntity = ModEntities.DEMONIC_DRAGON.get().create(this.world);
//        ;
//        demonicDragonEntity.getPhaseManager().setPhase(PhaseType.HOLDING_PATTERN);
////        demonicDragonEntity.setLocationAndYaw(0.0D, 128.0D, 0.0D, this.world.random.nextFloat() * 360.0F, 0.0F);
//        this.world.addFreshEntity(demonicDragonEntity);
//        this.dragonUniqueId = demonicDragonEntity.getUUID();
//        return demonicDragonEntity;
//    }
//
//    public void dragonUpdate(EnderDragonEntity dragonIn) {
//        if (dragonIn.getUUID().equals(this.dragonUniqueId)) {
//            this.bossInfo.setPercent(dragonIn.getHealth() / dragonIn.getMaxHealth());
//            this.ticksSinceDragonSeen = 0;
//            if (dragonIn.hasCustomName()) {
//                this.bossInfo.setName(dragonIn.getDisplayName());
//            }
//        }
//
//    }
//
//    public int getNumAliveCrystals() {
//        return this.aliveCrystals;
//    }
//
//    public void onCrystalDestroyed(EnderCrystalEntity crystal, DamageSource dmgSrc) {
//        if (this.respawnState != null && this.crystals.contains(crystal)) {
//            LOGGER.debug("Aborting respawn sequence");
//            this.respawnState = null;
//            this.respawnStateTicks = 0;
//            this.resetSpikeCrystals();
//            this.generatePortal(true);
//        } else {
//            this.findAliveCrystals();
//            Entity entity = this.world.getEntity(this.dragonUniqueId);
//            if (entity instanceof EnderDragonEntity) {
//                ((EnderDragonEntity) entity).onCrystalDestroyed(crystal, crystal.blockPosition(), dmgSrc);
//            }
//        }
//
//    }
//
//    public boolean hasPreviouslyKilledDragon() {
//        return this.previouslyKilled;
//    }
//
//    public void tryRespawnDragon() {
//        if (this.dragonKilled && this.respawnState == null) {
//            BlockPos blockpos = this.exitPortalLocation;
//            if (blockpos == null) {
//                LOGGER.debug("Tried to respawn, but need to find the portal first.");
//                BlockPattern.PatternHelper blockpattern$patternhelper = this.findExitPortal();
//                if (blockpattern$patternhelper == null) {
//                    LOGGER.debug("Couldn't find a portal, so we made one.");
//                    this.generatePortal(true);
//                } else {
//                    LOGGER.debug("Found the exit portal & temporarily using it.");
//                }
//
//                blockpos = this.exitPortalLocation;
//            }
//
//            List<EnderCrystalEntity> list1 = Lists.newArrayList();
//            BlockPos blockpos1 = blockpos.above(1);
//
//            for (Direction direction : Direction.Plane.HORIZONTAL) {
//                List<EnderCrystalEntity> list = this.world.getEntitiesOfClass(EnderCrystalEntity.class, new AxisAlignedBB(blockpos1.relative(direction, 2)));
//                if (list.isEmpty()) {
//                    return;
//                }
//
//                list1.addAll(list);
//            }
//
//            LOGGER.debug("Found all crystals, respawning dragon.");
//            this.respawnDragon(list1);
//        }
//
//    }
//
//    private void respawnDragon(List<EnderCrystalEntity> crystalsIn) {
//        if (this.dragonKilled && this.respawnState == null) {
//            for (BlockPattern.PatternHelper blockpattern$patternhelper = this.findExitPortal(); blockpattern$patternhelper != null; blockpattern$patternhelper = this.findExitPortal()) {
//                for (int i = 0; i < this.portalPattern.getDepth(); ++i) {
//                    for (int j = 0; j < this.portalPattern.getHeight(); ++j) {
//                        for (int k = 0; k < this.portalPattern.getWidth(); ++k) {
//                            CachedBlockInfo cachedblockinfo = blockpattern$patternhelper.getBlock(i, j, k);
//                            if (cachedblockinfo.getState().is(Blocks.BEDROCK) || cachedblockinfo.getState().is(Blocks.END_PORTAL)) {
//                                this.world.setBlockAndUpdate(cachedblockinfo.getPos(), Blocks.END_STONE.defaultBlockState());
//                            }
//                        }
//                    }
//                }
//            }
//
//            this.respawnState = DragonSpawnState.START;
//            this.respawnStateTicks = 0;
//            this.generatePortal(false);
//            this.crystals = crystalsIn;
//        }
//
//    }
//
//    public void resetSpikeCrystals() {
//        for (EndSpikeFeature.EndSpike endspikefeature$endspike : EndSpikeFeature.getSpikesForLevel(this.world)) {
//            for (EnderCrystalEntity endercrystalentity : this.world.getEntitiesOfClass(EnderCrystalEntity.class, endspikefeature$endspike.getTopBoundingBox())) {
//                endercrystalentity.setInvulnerable(false);
//                endercrystalentity.setBeamTarget((BlockPos) null);
//            }
//        }
//    }
//
//    public void addPlayer(ServerPlayer player) {
//        this.bossInfo.addPlayer(player);
//    }
//
//    public void removePlayer(ServerPlayer player) {
//        this.bossInfo.removePlayer(player);
//    }
//
//    public List<DemonicDragonEntity> getDragons(ServerLevel world) {
//        List<DemonicDragonEntity> list = Lists.newArrayList();
//
//        for (Entity entity : world.getEntities().collect(toList())) {
//            if (entity instanceof DemonicDragonEntity && entity.isAlive()) {
//                list.add((DemonicDragonEntity) entity);
//            }
//        }
//
//        return list;
//    }
//}
