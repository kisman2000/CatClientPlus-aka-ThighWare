package me.dev.thighware.api.command.commands;

import me.dev.thighware.api.command.Command;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class NameMcCommand extends Command {
    public NameMcCommand() {
        super("namemc", new String[]{"name"});
    }

    @Override
    public void execute(String[] commands) {
        String name = commands[0];
        try {
            Desktop.getDesktop().browse(URI.create("https://namemc.com/profile/" + name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
