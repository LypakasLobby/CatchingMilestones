package com.lypaka.catchingmilestones.Listeners;

import com.lypaka.catchingmilestones.CatchingMilestones;
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
        Account account = new Account(player, null);
        AccountHandler.accountMap.put(player.getUUID(), account);

    }

    @SubscribeEvent
    public static void onLogoff (PlayerEvent.PlayerLoggedOutEvent event) {

        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        AccountHandler.accountMap.entrySet().removeIf(e -> e.getKey().toString().equalsIgnoreCase(player.getUUID().toString()));

    }

}
