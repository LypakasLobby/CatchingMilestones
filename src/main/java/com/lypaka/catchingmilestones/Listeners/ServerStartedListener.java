package com.lypaka.catchingmilestones.Listeners;

import com.lypaka.catchingmilestones.CatchingMilestones;
import com.pixelmonmod.pixelmon.Pixelmon;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

@Mod.EventBusSubscriber(modid = CatchingMilestones.MOD_ID)
public class ServerStartedListener {

    @SubscribeEvent
    public static void onServerStarted (FMLServerStartedEvent event) {

        Pixelmon.EVENT_BUS.register(new CatchingListener());

    }

}
