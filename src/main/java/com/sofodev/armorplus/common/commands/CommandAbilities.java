package com.sofodev.armorplus.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.sofodev.armorplus.common.caps.abilities.data.AbilityData;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.common.caps.abilities.CapabilityAbility.CAPABILITY_ABILITIES;
import static com.sofodev.armorplus.common.caps.abilities.data.AbilityData.*;
import static com.sofodev.armorplus.common.util.TextUtils.*;

@Mod.EventBusSubscriber(modid = MODID)
public class CommandAbilities {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("abilities")
            .requires(cs -> cs.hasPermissionLevel(0))
            .then(Commands.literal("add")
                .requires(ctx -> ctx.hasPermissionLevel(2))
                .then(Commands.argument("ability", AbilityArgument.ability())
                    .executes(ctx -> add(ctx.getSource(), AbilityArgument.getAbilityID(ctx, "ability"))))
            ).then(Commands.literal("list")
                .executes(ctx -> listAbilities(ctx.getSource()))
            ).then(Commands.literal("limit")
                .executes(ctx -> limit(ctx.getSource()))
                .then(Commands.literal("set")
                    .then(Commands.argument("amount", IntegerArgumentType.integer(0, 4))
                        .executes(ctx -> setLimit(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "amount")))
                    )
                )
            ).then(Commands.literal("clear")
                .executes(ctx -> clearAbilities(ctx.getSource()))
            ).then(Commands.literal("remove")
                .then(Commands.argument("ability", AbilityArgument.ability())
                    .executes(ctx -> removeAbility(ctx.getSource(), AbilityArgument.getAbilityID(ctx, "ability")))
                )
            )
        );
    }

    private static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(new TextComponentTranslation("commands.armorplus.error.usage"));

    private static int add(CommandSource source, ResourceLocation resLoc) throws CommandSyntaxException {
        CommandData arguments = new CommandData(source);
        AbilityData data = AbilityData.getData(resLoc);
        ItemStack stack = arguments.getStack();

        return arguments.getStack().getCapability(CAPABILITY_ABILITIES).filter(handler -> !stack.isEmpty() && !contains(stack, data)).map(handler -> {
            int i = 0;
            if (!contains(stack, data)) {
                if (canProvide(stack, data)) {
                    int abilitySize = handler.getAbilities().size();
                    int limit = handler.getLimit();
                    if (hasRoomForAbilities(handler) && !isEmpty(data)) {
                        handler.addAbility(data);
                        ++i;
                        send(arguments, success("commands.armorplus.abilities.add.success", data.getName()));
                    } else {
                        send(arguments, error("commands.armorplus.abilities.add.fail", limit, abilitySize, limit));
                    }
                } else {
                    send(arguments, error("commands.armorplus.abilities.add.incorrect_ability", data.getName()));
                }
            } else {
                send(arguments, error("commands.armorplus.abilities.add.fail.already_there"));
            }

            // if (i == 0) {
            //     try {
            //         throw FAILED_EXCEPTION.create();
            //     } catch (CommandSyntaxException e) {
            //         e.printStackTrace();
            //     }
            // }
            return i;
        }).orElse(1);
    }

    public static boolean isEmpty(AbilityData data) {
        return Objects.equals(data.getRegistryName(), new ResourceLocation("armorplus:empty"));
    }

    public static void send(CommandData player, String key, Object... args) {
        send(player, translate(key, args));
    }

    public static void send(CommandData player, ITextComponent component) {
        player.getSource().sendFeedback(component, true);
    }

    private static int removeAbility(CommandSource source, ResourceLocation resLoc) throws CommandSyntaxException {
        CommandData arguments = new CommandData(source);
        AbilityData data = AbilityData.getData(resLoc);
        ItemStack stack = arguments.getStack();

        return arguments.getStack().getCapability(CAPABILITY_ABILITIES).map(handler -> {
            int i = 0;

            if (hasAbilities(handler) && contains(stack, data)) {
                handler.removeAbility(data);
                send(arguments, success("commands.armorplus.abilities.remove", data.getName()));
                ++i;
            } else {
                send(arguments, error("commands.armorplus.abilities.remove.fail", data.getName()));
            }
            //  if (i == 0) {
            //      try {
            //          throw FAILED_EXCEPTION.create();
            //      } catch (CommandSyntaxException e) {
            //          e.printStackTrace();
            //      }
            //  }
            return i;
        }).orElse(1);
    }

    private static int clearAbilities(CommandSource source) throws CommandSyntaxException {
        CommandData arguments = new CommandData(source);
        return arguments.getStack().getCapability(CAPABILITY_ABILITIES).map(handler -> {
            int i = 0;
            if (hasAbilities(handler)) {
                handler.getAbilities().clear();
                send(arguments, success("commands.armorplus.abilities.clear.success"));
                ++i;
            } else {
                send(arguments, error("commands.armorplus.abilities.clear.fail"));
            }
            //   if (i == 0) {
            //       try {
            //           throw FAILED_EXCEPTION.create();
            //       } catch (CommandSyntaxException e) {
            //           e.printStackTrace();
            //       }
            //   }
            return i;
        }).orElse(1);
    }

    private static int setLimit(CommandSource source, int limit) throws CommandSyntaxException {
        CommandData arguments = new CommandData(source);
        return arguments.getStack().getCapability(CAPABILITY_ABILITIES).map(handler -> {
            int i = 0;
            byte wantedLimit = (byte) limit;
            handler.setLimit(wantedLimit);
            send(arguments, success("commands.armorplus.abilities.limit.set", handler.getAbilities().size(), handler.getLimit()));
            ++i;
            //  if (i == 0) {
            //      try {
            //          throw FAILED_EXCEPTION.create();
            //      } catch (CommandSyntaxException e) {
            //          e.printStackTrace();
            //      }
            //  }
            return i;
        }).orElse(1);
    }

    private static int limit(CommandSource source) throws CommandSyntaxException {
        CommandData arguments = new CommandData(source);
        return arguments.getStack().getCapability(CAPABILITY_ABILITIES).map(handler -> {
            int i = 0;
            send(arguments, "commands.armorplus.abilities.limit", handler.getAbilities().size(), handler.getLimit());
            ++i;

            //  if (i == 0) {
            //      try {
            //          throw FAILED_EXCEPTION.create();
            //      } catch (CommandSyntaxException e) {
            //          e.printStackTrace();
            //      }
            //  }
            return i;
        }).orElse(1);
    }

    private static int listAbilities(CommandSource source) throws CommandSyntaxException {
        CommandData arguments = new CommandData(source);
        return arguments.getStack().getCapability(CAPABILITY_ABILITIES).map(handler -> {
            int i = 0;
            send(arguments, "commands.armorplus.abilities.show", handler.getAbilities());
            ++i;
            //  if (i == 0) {
            //      try {
            //          throw FAILED_EXCEPTION.create();
            //      } catch (CommandSyntaxException e) {
            //          e.printStackTrace();
            //      }
            //  }
            return i;
        }).orElse(1);
    }
}
