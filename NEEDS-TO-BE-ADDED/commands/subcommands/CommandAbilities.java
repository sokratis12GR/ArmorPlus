/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.commands.subcommands;

import com.sofodev.armorplus.caps.abilities.AbilityData;
import com.sofodev.armorplus.caps.abilities.AbilityDataHandler;
import com.sofodev.armorplus.caps.abilities.AbilityDataHandler.DefaultAbilityData;
import com.sofodev.armorplus.caps.abilities.AbilityDataHandler.IAbilityHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.Objects;

import static com.sofodev.armorplus.caps.abilities.AbilityData.*;
import static com.sofodev.armorplus.util.TextUtils.*;

public class CommandAbilities {

//    public CommandAbilities() {
//        super("abilities");
//    }
//
//    /**
//     * Check if the given ICommandSender has permission to execute this command
//     */
//    @Override
//    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
//        return sender.canUseCommand(this.getRequiredPermissionLevel(), this.getName());
//    }
//
//    @Override
//    public int getRequiredPermissionLevel() {
//        return 4;
//    }
//
//    @Override
//    public String getUsage(ICommandSender sender) {
//        return super.getUsage(sender);
//    }
//
//    /**
//     * Get a list of options for when the user presses the TAB key
//     */
//    @Override
//    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
//        if (args.length == 1) {
//            return getListOfStringsMatchingLastWord(args, "list", "limit", "add", "remove", "clear");
//        } else if (args.length == 2 && ("add".equals(args[0]) || "remove".equals(args[0]))) {
//            List<ResourceLocation> abilities = ABILITY_REGISTRY.getEntries().stream().map(Map.Entry::getKey).collect(Collectors.toList());
//            return getListOfStringsMatchingLastWord(args, abilities);
//        } else if (args.length == 3 && "add".equals(args[0])) {
//            return getListOfStringsMatchingLastWord(args, "hide");
//        } else if (args.length == 2 && "limit".equals(args[0])) {
//            return getListOfStringsMatchingLastWord(args, "set");
//        } else {
//            return Collections.emptyList();
//        }
//    }
//
//    @Override
//    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
//        if (!(sender.getCommandSenderEntity() instanceof EntityPlayer)) {
//            return;
//        }
//        EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();
//        ItemStack stack = player.getHeldItemMainhand();
//        IAbilityHandler handler = getHandler(stack);
//        if (handler == null) {
//            this.send(player, error("commands.armorplus.abilities.wrong_item"));
//        } else if (args.length > 0) {
//            String argOne = args[0];
//            int abilitySize = handler.getAbilities().size();
//            byte limit = handler.getLimit();
//            switch (argOne) {
//                case "show":
//                case "display":
//                case "list":
//                    this.send(player, "commands.armorplus.abilities.show", handler.getAbilities());
//                    break;
//                case "limit":
//                    this.limitCMD(handler, player, abilitySize, limit, args);
//                    break;
//                case "add":
//                    this.addCMD(handler, player, stack, abilitySize, limit, args);
//                    break;
//                case "clear":
//                    this.clearCMD(handler, player);
//                    break;
//                case "remove":
//                    this.removeCMD(handler, player, args);
//                    break;
//                default:
//                    this.send(player, error("commands.armorplus.abilities.usage"));
//                    break;
//            }
//        } else {
//            this.send(player, error("commands.armorplus.abilities.usage"));
//        }
//    }

    //Commands to be executed

    /**
     * Functionality: If the ability list isn't empty, the command will remove all entries from the ability list of the given handler,
     * otherwise will report a fail command hence there is nothing to clear
     */
    private void clearCMD(IAbilityHandler handler, EntityPlayer player) {
        if (hasAbilities(handler)) {
            handler.getAbilities().clear();
            this.send(player, success("commands.armorplus.abilities.clear.success"));
        } else {
            this.send(player, error("commands.armorplus.abilities.clear.fail"));
        }
    }

    /**
     * Functionality: if only "limit" is present, limit shows the abilityLimit {@link DefaultAbilityData#getLimit()} for the current item,
     * if followed by {@code set (value)} will set change the held item's limit to the one provided {@link AbilityDataHandler.DefaultAbilityData#setLimit(byte)},
     * otherwise fails with an error.
     */
    private void limitCMD(IAbilityHandler handler, EntityPlayer player, int abilitySize, byte limit, String[] args) {
        if (args.length == 2) {
            this.send(player, "commands.armorplus.abilities.limit", abilitySize, limit);
        } else if (args.length > 2 && args[1].equals("set")) {
            if (args.length > 3) {
                byte wantedLimit = Byte.parseByte(args[2]);
                handler.setLimit(wantedLimit);
                this.send(player, success("commands.armorplus.abilities.limit.set", abilitySize, limit));
            } else {
                this.send(player, error("commands.armorplus.abilities.limit.set.usage"));
            }
        } else {
            this.send(player, error("commands.armorplus.abilities.limit.set.usage"));
        }
    }

    /**
     * The command which is used to actually provide the abilities.
     * Functionality: if the provided {@param id} is not null and can be provided by the ItemStack, the ability will be added to the list of abilities (a message will popup),
     * if {@code (hide)} is provided there will be no messages,
     * otherwise fails with an error.
     */
    private void addCMD(IAbilityHandler handler, EntityPlayer player, ItemStack stack, int abilitySize, byte limit, String[] args) {
        if (args.length <= 1) {
            return;
        }
        ResourceLocation id = new ResourceLocation(args[1]);
        AbilityData data = getData(id);
        if (!contains(handler, data)) {
            if (canProvide(stack, data)) {
                if (args.length == 2) {
                    this.addAbility(handler, player, data, false);
                } else if (args.length == 3) {
                    //This is here just in case you want to silently add an ability
                    if (args[2].equals("hide")) {
                        this.addAbility(handler, player, data, true);
                    } else {
                        this.send(player, error("commands.armorplus.abilities.add.hide.usage"));
                    }
                }
            } else {
                this.send(player, error("commands.armorplus.abilities.add.incorrect_ability", data.getName()));
            }
        } else {
            this.send(player, error("commands.armorplus.abilities.add.fail.already_there", abilitySize, limit));
        }
    }

    /**
     * Provides the functionality of {@link this#addCMD(IAbilityHandler, EntityPlayer, ItemStack, int, byte, String[])}
     */
    private void addAbility(IAbilityHandler handler, EntityPlayer player, AbilityData data, boolean hide) {
        int abilitySize = handler.getAbilities().size();
        int limit = handler.getLimit();
        if (hasRoomForAbilities(handler)) {
            if (!isEmpty(data)) {
                handler.addAbility(data);
                if (!hide) {
                    this.send(player, success("commands.armorplus.abilities.add.success", data.getName()));
                }
            }
        } else if (!hide) {
            this.send(player, error("commands.armorplus.abilities.add.fail", limit, abilitySize, limit));
        }
    }

    /**
     * Functionality: Removes an ability from the list of abilities the handler provides, if not present in the list this fails with an error
     */
    private void removeCMD(IAbilityHandler handler, EntityPlayer player, String[] args) {
        if (args.length > 1) {
            ResourceLocation id = new ResourceLocation(args[1]);
            AbilityData data = getData(id);
            if (hasAbilities(handler) && contains(handler, data)) {
                handler.removeAbility(data);
            } else {
                this.send(player, error("commands.armorplus.abilities.remove.fail", data.getName()));
            }
        } else {
            this.send(player, error("commands.armorplus.abilities.remove.usage"));
        }
    }

    //Utils

    private boolean isEmpty(AbilityData data) {
        return Objects.equals(data.getRegistryName(), new ResourceLocation("armorplus:empty"));
    }

    private void send(EntityPlayer player, String key, Object... args) {
        send(player, translate(key, args));
    }

    private void send(EntityPlayer player, ITextComponent component) {
        player.sendMessage(component);
    }
}
