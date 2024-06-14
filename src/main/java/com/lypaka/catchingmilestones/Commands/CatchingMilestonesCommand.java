package com.lypaka.catchingmilestones.Commands;

import com.lypaka.catchingmilestones.CatchingMilestones;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = CatchingMilestones.MOD_ID)
public class CatchingMilestonesCommand {

    public static List<String> ALIASES = Arrays.asList("catchingmilestones", "cmiles", "milestones"); // command aliases for our command(s) we create

    // Creates our command(s) so we can actually use them
    @SubscribeEvent
    public static void onCommandRegistration (RegisterCommandsEvent event) {

        new AssignCommand(event.getDispatcher()); // creates and registers the "/catchingmilestones assign" command (and all the ALIASES ^)
        new InfoCommand(event.getDispatcher());
        new ReloadCommand(event.getDispatcher()); // creates and registers the "/catchingmilestones reload" command

    }

}
