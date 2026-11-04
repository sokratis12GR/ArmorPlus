package com.sofodev.armorplus.registry.block.special;

import com.sofodev.armorplus.registry.RegistryContext;
import com.sofodev.armorplus.registry.rune.RuneAltarMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
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
import org.jspecify.annotations.Nullable;

/**
 * Opens the {@link RuneAltarMenu}, where buffs are extracted from ArmorPlus equipment into runes.
 */
public class RuneAltarBlock extends Block {

    private static final Component CONTAINER_TITLE = Component.translatable("container.armorplus.rune_altar");

    public RuneAltarBlock() {
        super(RegistryContext.blockProperties(BlockBehaviour.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE)));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            player.openMenu(state.getMenuProvider(level, pos));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected @Nullable MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new SimpleMenuProvider((containerId, inventory, player) ->
                new RuneAltarMenu(containerId, inventory, ContainerLevelAccess.create(level, pos)), CONTAINER_TITLE);
    }
}
