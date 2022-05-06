package dev.bryth.autogg;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.ClientCommandHandler;

import java.io.File;

import dev.bryth.autogg.handler.ConfigHandler;
import dev.bryth.autogg.handler.EventHandler;
import dev.bryth.autogg.command.*;

@Mod(modid = "autogg", name = "Auto GG", version = "1.0.1")
public class AutoGG {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        File configFile = new File(event.getModConfigurationDirectory(), "autogg.cfg");
        ConfigHandler.init(configFile);
    }

    @Mod.EventHandler
    public void onFMLInitialization(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        ClientCommandHandler.instance.registerCommand(new SetAutoGG());
    }
}
