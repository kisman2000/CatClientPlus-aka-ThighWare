package me.dev.thighware.api.command.commands;

import me.dev.thighware.Thighware;
import me.dev.thighware.api.command.Command;
import me.dev.thighware.api.module.Module;

public class ToggleCommand extends Command {
    public ToggleCommand() {
        super("toggle");
    }
    @Override
    public void execute(String[] commands) {
        if (commands.length == 2) {
            String name = commands[0].replaceAll("_", " ");
            Module module = Thighware.moduleManager.getModule(name);
            if (module != null) {
                module.toggle();
            } else {
                Command.sendMessage("Can't find that module :(");
            }
        }
    }
}
