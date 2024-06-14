package com.lypaka.catchingmilestones.PlayerAccounts;

import com.lypaka.catchingmilestones.CatchingMilestones;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountHandler {

    public static Map<UUID, Account> accountMap = new HashMap<>();

    public static void saveAccount (ServerPlayerEntity player) {

        Account account = accountMap.get(player.getUUID());
        String type = account.getMilestone().getType();
        int amount = account.getMilestone().getAmount();
        int progress = account.getMilestone().getProgress();
        String commandReward = account.getMilestone().getCommandReward();

        CatchingMilestones.playerConfigManager.getPlayerConfigNode(player.getUUID(), "Amount").setValue(amount);
        CatchingMilestones.playerConfigManager.getPlayerConfigNode(player.getUUID(), "Command-Reward").setValue(commandReward);
        CatchingMilestones.playerConfigManager.getPlayerConfigNode(player.getUUID(), "Progress").setValue(progress);
        CatchingMilestones.playerConfigManager.getPlayerConfigNode(player.getUUID(), "Type").setValue(type);
        CatchingMilestones.playerConfigManager.savePlayer(player.getUUID());

    }

}
