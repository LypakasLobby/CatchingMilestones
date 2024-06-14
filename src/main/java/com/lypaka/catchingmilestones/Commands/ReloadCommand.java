package com.lypaka.catchingmilestones.Commands;

import com.lypaka.catchingmilestones.CatchingMilestones;
import com.lypaka.catchingmilestones.ConfigGetters;
import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.MiscHandlers.PermissionHandler;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

// Command to apply changes made in our config file to the server without having to restart the server
public class ReloadCommand {

    public ReloadCommand (CommandDispatcher<CommandSource> dispatcher) {

        for (String a : CatchingMilestonesCommand.ALIASES) { // what this is actually doing is creating a new command for each command alias we have

            dispatcher.register(
                    Commands.literal(a)
                            .then(
                                    Commands.literal("reload") // the name of our command (i.e. this creates "/cmiles reload")
                                            .executes(c -> {

                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) { // if its a player using the command

                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                    if (!PermissionHandler.hasPermission(player, "catchingmilestones.command.admin")) { // if player does not have permission

                                                        player.sendMessage(FancyText.getFormattedText("&cYou don't have permission to use this command!"), player.getUUID());
                                                        return 0; // player doesn't have permission, so sends a message saying so and stops running the code

                                                    }

                                                }

                                                // if we get to here, the command source is either not a player (executing from console) or the player has the appropriate permission
                                                try {

                                                    CatchingMilestones.configManager.load();
                                                    ConfigGetters.load();
                                                    c.getSource().sendSuccess(FancyText.getFormattedText("&aSuccessfully reloaded CatchingMilestones configuration!"), true);

                                                } catch (ObjectMappingException e) {

                                                    throw new RuntimeException(e);

                                                }

                                                return 1;

                                            })
                            )
            );

        }

    }

}
