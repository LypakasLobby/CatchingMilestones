package com.lypaka.catchingmilestones.Listeners;

import com.lypaka.catchingmilestones.CatchingMilestones;
import com.lypaka.catchingmilestones.Milestones.Milestone;
import com.lypaka.catchingmilestones.PlayerAccounts.Account;
import com.lypaka.catchingmilestones.PlayerAccounts.AccountHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CatchingMilestones.MOD_ID)
public class ConnectionListener {

    @SubscribeEvent
    public static void onLogin (PlayerEvent.PlayerLoggedInEvent event) {

        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        CatchingMilestones.playerConfigManager.loadPlayer(player.getUUID());
        Account account = new Account(player, null);
        AccountHandler.accountMap.put(player.getUUID(), account);

        // if the player has an active milestone in their account config, we need to pull that information and set the milestone object in memory so progress on the milestone
        // resumes as normal

        String type = CatchingMilestones.playerConfigManager.getPlayerConfigNode(player.getUUID(), "Type").getString();
        if (!type.equalsIgnoreCase("none")) { // player has an active milestone

            int progress = CatchingMilestones.playerConfigManager.getPlayerConfigNode(player.getUUID(), "Progress").getInt();
            int amount = CatchingMilestones.playerConfigManager.getPlayerConfigNode(player.getUUID(), "Amount").getInt();
            String commandReward = CatchingMilestones.playerConfigManager.getPlayerConfigNode(player.getUUID(), "Command-Reward").getString();
            Milestone milestone = account.getMilestone();

            if (milestone == null) {

                milestone = new Milestone(type, amount, commandReward, progress);

            } else {

                milestone.setProgress(progress);
                milestone.setType(type);
                milestone.setAmount(amount);
                milestone.setCommandReward(commandReward);

            }

            account.setMilestone(milestone);
            AccountHandler.accountMap.put(player.getUUID(), account);

        }

    }

    @SubscribeEvent
    public static void onLogoff (PlayerEvent.PlayerLoggedOutEvent event) {

        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        AccountHandler.saveAccount(player); // this needs to be called before we remove the account from memory so we can access the data we need to access
        AccountHandler.accountMap.entrySet().removeIf(e -> e.getKey().toString().equalsIgnoreCase(player.getUUID().toString()));

    }

}
