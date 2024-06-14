package com.lypaka.catchingmilestones.Milestones;

import com.lypaka.catchingmilestones.ConfigGetters;
import com.lypaka.catchingmilestones.PlayerAccounts.Account;
import com.lypaka.catchingmilestones.PlayerAccounts.AccountHandler;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class MilestoneHandler {

    public static void createNewMilestoneForPlayer (ServerPlayerEntity player, String type) {

        String randomType = type; // if a type is not specified when we call this method then we select one randomly
        if (!type.equalsIgnoreCase("")) {

            // get all our types from our config
            List<String> types = new ArrayList<>(ConfigGetters.milestoneMap.keySet());
            // get a random type from that map
            randomType = RandomHelper.getRandomElementFromList(types);

        }

        // get the list of amounts from the randomly selected type
        List<Integer> amounts = ConfigGetters.milestoneMap.get(randomType);
        // get a random amount from the randomly selected type's list of amounts
        int randomAmount = RandomHelper.getRandomElementFromList(amounts);

        String randomCommand = RandomHelper.getRandomElementFromList(ConfigGetters.rewards);

        Milestone milestone = new Milestone(randomType, randomAmount, randomCommand, 0);
        Account account = AccountHandler.accountMap.get(player.getUUID());
        account.setMilestone(milestone);

    }

    public static void rewardPlayerAndClearMilestone (ServerPlayerEntity player) {

        Account account = AccountHandler.accountMap.get(player.getUUID());
        String command = account.getMilestone().getCommandReward();

        player.getServer().getCommands().performCommand(player.getServer().createCommandSourceStack(), command.replace("%player%", player.getName().getString()));
        account.clearMilestone();

    }

}
