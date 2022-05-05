package dev.bryth.autogg.handler;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class ConfigHandler {
    public static Configuration config;
    public static boolean enabled;
    public static int delay;

    public static void setEnabled(boolean enable) {
        Property isEnabled = config.get("config", "enabled", false);
        isEnabled.set(enabled);
        enabled = enable;
        config.save();
    }

    public static void setDelay(int newDelay) {
        Property delayProperty = config.get("config", "delay", "");
        delayProperty.set(newDelay);
        delay = newDelay;
        config.save();
    }

    public static void init(File file)  {
        config = new Configuration(file);
        config.load();

        enabled = config.getBoolean("enabled", "config", false, "Whether the mod is active or not");
        delay = config.getInt("delay", "config", 1000, 0, 30 * 1000, "Delay before sending gg");
    }
}
