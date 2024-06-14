package com.lypaka.catchingmilestones.Commands;

import com.lypaka.catchingmilestones.ConfigGetters;
import com.lypaka.catchingmilestones.Milestones.MilestoneHandler;
import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.MiscHandlers.PermissionHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;

// Command for assigning (giving) the player a milestone task to reach
public class AssignCommand {

    public AssignCommand (CommandDispatcher<CommandSource> dispatcher) {

        for (String a : CatchingMilestonesCommand.ALIASES) {

            dispatcher.register(
                    Commands.literal(a)
                            .then(
                                    Commands.literal("assign")
                                            .then(
                                                    Commands.argument("player", EntityArgument.players())
                                                            .then(
                                                                    Commands.argument("type", StringArgumentType.word())
                                                                            .suggests( // creates command suggestions based on what we have in the config
                                                                                    (context, builder) -> ISuggestionProvider.suggest(ConfigGetters.milestoneMap.keySet(), builder)
                                                                            )
                                                                            .executes(c -> {

                                                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                                                    if (!PermissionHandler.hasPermission(player, "catchingmilestones.command.admin")) {

                                                                                        player.sendMessage(FancyText.getFormattedText("&cYou don't have permission to use this command!"), player.getUUID());
                                                                                        return 0;

                                                                                    }

                                                                                }

                                                                                ServerPlayerEntity target = EntityArgument.getPlayer(c, "player");
                                                                                String type = StringArgumentType.getString(c, "type");
                                                                                MilestoneHandler.createNewMilestoneForPlayer(target, type);
                                                                                c.getSource().sendSuccess(FancyText.getFormattedText("&aSuccessfully created a new " + type + " milestone for " + target.getName().getString()), true);
                                                                                return 1;

                                                                            })
                                                            )
                                            )
                            )
            );

        }

    }

}
