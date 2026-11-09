package com.sofodev.armorplus.registry.block.special;

import com.sofodev.armorplus.registry.rune.RitualMenu;
import com.sofodev.armorplus.registry.rune.RuneAltarMenu;
import com.sofodev.armorplus.registry.rune.RuneRitual;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

/**
 * Opens the {@link RuneAltarMenu}, where buffs are extracted from ArmorPlus equipment into runes. With a complete circle of
 * pedestals around it (see {@link RuneRitual}) it opens the {@link RitualMenu} instead, where runes are socketed and upgraded.
 */
public class RuneAltarBlock extends Block {

    private static final Component CONTAINER_TITLE = Component.translatable("container.armorplus.rune_altar");
    private static final Component RITUAL_TITLE = Component.translatable("container.armorplus.ritual_altar");

    public RuneAltarBlock() {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            openMenu(player, level, pos, RuneRitual.isActive(level, pos));
        }
        return InteractionResult.SUCCESS;
    }

    /** Opens either form of the altar, the client gets the altar's position to look at its pedestals. */
    public static void openMenu(Player player, Level level, BlockPos pos, boolean ritual) {
        player.openMenu(menuProvider(level, pos, ritual), pos);
    }

    private static MenuProvider menuProvider(Level level, BlockPos pos, boolean ritual) {
        ContainerLevelAccess access = ContainerLevelAccess.create(level, pos);
        if (ritual) return new SimpleMenuProvider((containerId, inventory, player) -> new RitualMenu(containerId, inventory, access, pos), RITUAL_TITLE);
        return new SimpleMenuProvider((containerId, inventory, player) -> new RuneAltarMenu(containerId, inventory, access, pos), CONTAINER_TITLE);
    }

    @Override
    protected @Nullable MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return menuProvider(level, pos, RuneRitual.isActive(level, pos));
    }

    /** Power flows from the pedestals into the altar while the circle is complete. */
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (!RuneRitual.isActive(level, pos)) return;
        for (BlockPos pedestal : RuneRitual.pedestalPositions(pos)) {
            if (random.nextInt(4) != 0) continue;
            level.addParticle(ParticleTypes.ENCHANT, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5,
                    pedestal.getX() - pos.getX() + random.nextFloat() - 0.5F, -0.5F - random.nextFloat() * 0.5F, pedestal.getZ() - pos.getZ() + random.nextFloat() - 0.5F);
        }
    }
}
