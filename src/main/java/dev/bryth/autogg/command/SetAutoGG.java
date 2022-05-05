package dev.bryth.autogg.command;

import dev.bryth.autogg.util.NumUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import dev.bryth.autogg.handler.ConfigHandler;
import dev.bryth.autogg.util.MessageUtil;

public class SetAutoGG extends CommandBase {
    @Override
    public String getCommandName() {
        return "autogg";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "autogg [delay]";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 0) {
            Integer delay = NumUtil.tryParseInt(args[0]);

            if (delay == null) {
                MessageUtil.sendMessage("§cDelay must be a valid integer!");
                return;
            }

            ConfigHandler.setDelay(delay);
            ConfigHandler.setEnabled(true);
        } else {
            ConfigHandler.setEnabled(!ConfigHandler.enabled);
        }

        MessageUtil.sendMessage(String.format("§dAutoGG %s! §7(delay: %ss)", (ConfigHandler.enabled ? "enabled" : "disabled"), ConfigHandler.delay / 1000));
    }
}
