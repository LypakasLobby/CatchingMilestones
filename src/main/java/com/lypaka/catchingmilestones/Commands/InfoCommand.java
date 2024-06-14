package com.lypaka.catchingmilestones.Commands;

import com.lypaka.catchingmilestones.Milestones.Milestone;
import com.lypaka.catchingmilestones.PlayerAccounts.Account;
import com.lypaka.catchingmilestones.PlayerAccounts.AccountHandler;
import com.lypaka.lypakautils.FancyText;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;

// Command for players to type to be told what milestone they have (i.e. tells them what type and how many)
public class InfoCommand {

    public InfoCommand (CommandDispatcher<CommandSource> dispatcher) {

        for (String a : CatchingMilestonesCommand.ALIASES) {

            dispatcher.register(
                    Commands.literal(a)
                            .then(
                                    Commands.literal("info")
                                            .executes(c -> {

                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                    Account account = AccountHandler.accountMap.get(player.getUUID());

                                                    Milestone milestone = account.getMilestone();
                                                    if (milestone == null) { // if player doesn't have milestone

                                                        player.sendMessage(FancyText.getFormattedText("&cYou don't have an active milestone!"), player.getUUID());
                                                        return 0;

                                                    }

                                                    String type = account.getMilestone().getType();
                                                    if (type.equalsIgnoreCase("none")) { // if player doesn't have milestone

                                                        player.sendMessage(FancyText.getFormattedText("&cYou don't have an active milestone!"), player.getUUID());
                                                        return 0;

                                                    }

                                                    int amount = milestone.getAmount();
                                                    int progress = milestone.getProgress();

                                                    player.sendMessage(FancyText.getFormattedText("&eYou have caught &b" + progress + "&e/&b" +
                                                            amount + " &eof the required &b" + type + " &ePokemon to complete your milestone!"), player.getUUID());

                                                }

                                                return 0;

                                            })
                            )
            );

        }

    }

}
