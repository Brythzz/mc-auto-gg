package dev.bryth.autogg.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class MessageUtil {
    public static void sendMessage(String content) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(content));
    }
}
