/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.commands;

import com.sofodev.armorplus.common.registry.commands.subcommands.*;
import com.sofodev.armorplus.common.registry.commands.subcommands.teleporter.CommandTeleport;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.server.command.CommandTreeBase;
import net.minecraftforge.server.command.CommandTreeHelp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sokratis Fotkatzikis
 */
public class CommandArmorPlus extends CommandTreeBase {

    private ArrayList<String> aliases = new ArrayList<>();

    public CommandArmorPlus() {
        super.addSubcommand(new CommandDiscord());
        super.addSubcommand(new CommandInfo());
        super.addSubcommand(new CommandWiki());
        super.addSubcommand(new CommandNodecraft());
        super.addSubcommand(new CommandTreeHelp(this));
        super.addSubcommand(new CommandAbilities());
        super.addSubcommand(new CommandTeleport());
        this.aliases.add("arp");
        this.aliases.add("a+");
        this.aliases.add("armorplus");
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender instanceof EntityPlayer;
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        return super.getTabCompletions(server, sender, args, targetPos);
    }

    @Override
    public String getName() {
        return "ap";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "ArmorPlus Help";
    }
}