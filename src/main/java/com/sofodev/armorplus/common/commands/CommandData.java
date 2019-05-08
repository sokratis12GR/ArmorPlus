package com.sofodev.armorplus.common.commands;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.sofodev.armorplus.common.caps.abilities.CapabilityAbility;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class CommandData {
    private final EntityPlayerMP playerMP;
    private final ItemStack stack;
    private final CommandSource source;

    public CommandData(CommandSource source) throws CommandSyntaxException {
        playerMP = source.asPlayer();
        stack = source.asPlayer().getHeldItemMainhand();
        this.source = source;
    }

    public CommandSource getSource() {
        return source;
    }

    public EntityPlayerMP getPlayer() {
        return playerMP;
    }

    public ItemStack getStack() {
        return stack;
    }

}
