package dev.bryth.autogg.handler;

import dev.bryth.autogg.util.MessageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class EventHandler {
    private final Minecraft mc;
    private boolean onHypixel;
    private final ArrayList<String> triggers;

    public EventHandler() {
        this.mc = Minecraft.getMinecraft();
        this.onHypixel = false;
        this.triggers = getTriggers();
    }

    @SubscribeEvent
    public void playerLoggedIn(final FMLNetworkEvent.ClientConnectedToServerEvent event) {
        this.onHypixel = (!this.mc.isSingleplayer() && event.manager.getRemoteAddress().toString().toLowerCase().contains("hypixel.net"));
    }

    @SubscribeEvent
    public void playerLoggedOut(final FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        this.onHypixel = false;
    }

    @SubscribeEvent
    public void onChat(final ClientChatReceivedEvent event) {
        if (!this.onHypixel || !ConfigHandler.enabled) return;

        String message = event.message.getUnformattedText();
        String unformatted = EnumChatFormatting.getTextWithoutFormattingCodes(message);

        if (triggers == null) {
            MessageUtil.sendMessage("§eCouldn't get triggers");
            return;
        }

        boolean isEndMessage = triggers.stream().anyMatch(trigger -> unformatted.contains(trigger) && unformatted.startsWith(" "));
        if (!isEndMessage) return;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mc.thePlayer.sendChatMessage("gg");
            }
        }, ConfigHandler.delay);
    }

    private ArrayList<String> getTriggers() {
        // https://gist.githubusercontent.com/minemanpi/72c38b0023f5062a5f3eba02a5132603/raw/triggers.txt
        final String[] triggers = { "1er Killer - ", "1st Killer - ", "1er Tueur - ", "1ère Place - ", "1st Place - ", "Gagnant: ", "Winner: ", " - Dégâts infligés - ", "  - Damage Dealt - ", "Equipe gagnate -", "Winning Team -", "1er - ", "1st - ", "Gagnants: ", "Winners: ", "Equipe gagnante: ", "Winning Team: ", " gagné la partie!", " won the game!", "Top Seeker: ", "1ère Place: ", "1st Place: ", "Dernière équipe en vie!", "Last team standing!", "Vainqueur #1 (", "Winner #1 (", "Top des Survivants", "Top Survivors", "Gagnants - ", "Winners - ", "Sumo Duel - "};
        return new ArrayList<>(Arrays.asList(triggers));
    }
}
