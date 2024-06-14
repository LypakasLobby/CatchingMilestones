package com.lypaka.catchingmilestones.Listeners;

import com.lypaka.catchingmilestones.Milestones.Milestone;
import com.lypaka.catchingmilestones.Milestones.MilestoneHandler;
import com.lypaka.catchingmilestones.PlayerAccounts.Account;
import com.lypaka.catchingmilestones.PlayerAccounts.AccountHandler;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Element;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CatchingListener {

    @SubscribeEvent
    public void onCapture (CaptureEvent.SuccessfulCapture event) {

        ServerPlayerEntity player = event.getPlayer();
        Pokemon pokemon = event.getPokemon().getPokemon();
        Account account = AccountHandler.accountMap.get(player.getUUID());
        if (account.getMilestone() != null) {

            Milestone milestone = account.getMilestone();
            for (Element types : pokemon.getForm().getTypes()) {

                if (milestone.getType().equalsIgnoreCase(types.getName())) {

                    int progress = milestone.getProgress();
                    int updated = progress + 1;
                    if (updated >= milestone.getAmount()) {

                        MilestoneHandler.rewardPlayerAndClearMilestone(player);

                    } else {

                        milestone.setProgress(updated);

                    }

                }

            }

        }

    }

}
