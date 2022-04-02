package me.dev.thighware.api.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.dev.thighware.Thighware;
import me.dev.thighware.api.command.Command;

public class PrefixCommand extends Command {
    public PrefixCommand() {
        super("prefix", new String[]{"<newprefix>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            Command.sendMessage(ChatFormatting.GREEN + "Current prefix is " + Thighware.commandManager.getPrefix());
            return;
        }
        Thighware.commandManager.setPrefix(commands[0]);
        Command.sendMessage("Prefix changed to " + ChatFormatting.GRAY + commands[0]);
    }
}

