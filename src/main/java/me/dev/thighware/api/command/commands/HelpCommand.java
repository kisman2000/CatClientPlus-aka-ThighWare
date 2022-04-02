package me.dev.thighware.api.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.dev.thighware.Thighware;
import me.dev.thighware.api.command.Command;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            Command.sendMessage(ChatFormatting.GREEN + "CommandList:");
            for (Command command : Thighware.commandManager.getCommands()) {
                HelpCommand.sendMessage(ChatFormatting.GRAY + Thighware.commandManager.getPrefix() + command.getName());
            }
        }
    }
}
