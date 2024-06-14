package com.lypaka.catchingmilestones;

import com.lypaka.lypakautils.ConfigurationLoaders.BasicConfigManager;
import com.lypaka.lypakautils.ConfigurationLoaders.ConfigUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("catchingmilestones")
public class CatchingMilestones {

    public static final String MOD_ID = "catchingmilestones";
    public static final String MOD_NAME = "CatchingMilestones";
    public static final Logger logger = LogManager.getLogger(MOD_NAME);
    public static BasicConfigManager configManager;

    public CatchingMilestones() throws ObjectMappingException {

        // Creating our configuration system
        Path dir = ConfigUtils.checkDir(Paths.get("./config/catchingmilestones")); // This creates the config folder in the server's config folder
        String[] files = new String[]{"catchingmilestones.conf"}; // the config files we want to have
        configManager = new BasicConfigManager(files, dir, CatchingMilestones.class, MOD_NAME, MOD_ID, logger); // the configuration manager
        configManager.init(); // creating the actual configuration manager so we can access our stuff in our configs

        // Loading the values we created in our config into the mod so things actually work
        ConfigGetters.load();

    }

}
