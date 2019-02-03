/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.commands.subcommands;

import com.sofodev.armorplus.caps.abilities.AbilityData;
import com.sofodev.armorplus.caps.abilities.AbilityDataHandler.IAbilityHandler;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.sofodev.armorplus.caps.abilities.AbilityData.canProvide;
import static com.sofodev.armorplus.caps.abilities.AbilityData.getData;
import static com.sofodev.armorplus.caps.abilities.AbilityDataHandler.getHandler;
import static com.sofodev.armorplus.caps.abilities.ImplementedAbilities.ABILITY_REGISTRY;
import static net.minecraft.util.text.TextFormatting.GREEN;
import static net.minecraft.util.text.TextFormatting.RED;

public class CommandAbilities extends CommandSubBase {

    public CommandAbilities() {
        super("abilities");
    }

    /**
     * Check if the given ICommandSender has permission to execute this command
     */
    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender.canUseCommand(this.getRequiredPermissionLevel(), this.getName());
    }

    private Style error = new Style().setColor(RED);
    private Style success = new Style().setColor(GREEN);

    @Override
    public int getRequiredPermissionLevel() {
        return 4;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return super.getUsage(sender);
    }

    /**
     * Get a list of options for when the user presses the TAB key
     */
    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "list", "limit", "add", "remove", "clear");
        } else if (args.length == 2 && ("add".equals(args[0]) || "remove".equals(args[0]))) {
            List<ResourceLocation> abilities = ABILITY_REGISTRY.getEntries().stream().map(Map.Entry::getKey).collect(Collectors.toList());
            return getListOfStringsMatchingLastWord(args, abilities);
        } else if (args.length == 3 && "add".equals(args[0])) {
            return getListOfStringsMatchingLastWord(args, "hide");
        } else if (args.length == 2 && "limit".equals(args[0])) {
            return getListOfStringsMatchingLastWord(args, "set");
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if (!(sender.getCommandSenderEntity() instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();
        ItemStack stack = player.getHeldItemMainhand();
        IAbilityHandler handler = getHandler(stack);
        if (handler == null) {
            player.sendMessage(new TextComponentTranslation("commands.armorplus.abilities.wrong_item").setStyle(error));
        } else if (args.length <= 0) {
            player.sendMessage(new TextComponentTranslation("commands.armorplus.abilities.usage").setStyle(error));
        } else {
            String argA = args[0];
            int abilitySize = handler.getAbilities().size();
            int limit = handler.getLimit();
            if ("show".equals(argA) || "display".equals(argA) || "list".equals(argA)) {
                player.sendMessage(new TextComponentTranslation("commands.armorplus.abilities.show", handler.getAbilities()));
            } else if ("limit".equals(argA)) {
                if (args.length == 1) {
                    player.sendMessage(new TextComponentTranslation("commands.armorplus.abilities.limit", abilitySize, limit));
                } else if (args[1].equals("set")) {
                    if (args.length == 3) {
                        byte wantedLimit = Byte.parseByte(args[2]);
                        handler.setLimit(wantedLimit);
                        player.sendMessage(new TextComponentTranslation("commands.armorplus.abilities.limit.set", abilitySize, limit).setStyle(success));
                    } else {
                        player.sendMessage(new TextComponentTranslation("commands.armorplus.abilities.limit.set.usage").setStyle(error));
                    }
                } else {
                    player.sendMessage(new TextComponentTranslation("commands.armorplus.abilities.limit.set.usage").setStyle(error));
                }
            } else if ("add".equals(argA)) {
                ResourceLocation id = new ResourceLocation(args[1]);
                AbilityData data = getData(id);
                if (!handler.getAbilities().contains(data)) {
                    if (canProvide(stack, data)) {
                        if (args.length == 2) {
                            this.addAbility(handler, player, data, false);
                        } else if (args.length == 3) {
                            //This is here just in case you want to silently add an ability
                            if (args[2].equals("hide")) {
                                this.addAbility(handler, player, data, true);
                            } else {
                                player.sendMessage(new TextComponentTranslation("commands.armorplus.abilities.add.cost.usage").setStyle(error));
                            }
                        }
                    } else {
                        player.sendMessage(new TextComponentTranslation("commands.armorplus.abilities.add.incorrect_ability", data.getName()).setStyle(error));
                    }
                } else {
                    player.sendMessage(new TextComponentTranslation("commands.armorplus.abilities.add.fail.already_there", abilitySize, limit).setStyle(error));
                }
            } else if ("clear".equals(argA)) {
                if (!handler.getAbilities().isEmpty()) {
                    handler.getAbilities().clear();
                    player.sendMessage(new TextComponentTranslation("commands.armorplus.abilities.clear.success").setStyle(success));
                } else {
                    player.sendMessage(new TextComponentTranslation("commands.armorplus.abilities.clear.fail").setStyle(error));
                }
            } else if ("remove".equals(argA)) {
                if (args.length > 1) {
                    ResourceLocation id = new ResourceLocation(args[1]);
                    AbilityData data = getData(id);
                    if (!handler.getAbilities().isEmpty() && handler.getAbilities().contains(data)) {
                        handler.removeAbility(data);
                    } else {
                        player.sendMessage(new TextComponentTranslation("commands.armorplus.abilities.remove.fail", data.getName()).setStyle(error));
                    }
                } else {
                    player.sendMessage(new TextComponentTranslation("commands.armorplus.abilities.remove.usage").setStyle(error));
                }
            } else {
                player.sendMessage(new TextComponentTranslation("commands.armorplus.abilities.usage").setStyle(error));
            }
        }

    }

    private void addAbility(IAbilityHandler handler, EntityPlayer player, AbilityData data, boolean hide) {
        int abilitySize = handler.getAbilities().size();
        int limit = handler.getLimit();
        if (abilitySize < limit) {
            if (!isEmpty(data)) {
                handler.addAbility(data);
                if (!hide) {
                    player.sendMessage(new TextComponentTranslation("commands.armorplus.abilities.add.success", data.getName()).setStyle(success));
                }
            }
        } else if (!hide) {
            player.sendMessage(new TextComponentTranslation("commands.armorplus.abilities.add.fail", limit, abilitySize, limit).setStyle(error));
        }
    }

    private boolean isEmpty(AbilityData data) {
        return Objects.equals(data.getRegistryName(), "armorplus:empty");
    }
}
