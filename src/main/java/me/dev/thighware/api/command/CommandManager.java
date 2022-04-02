package me.dev.thighware.api.command;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.dev.thighware.api.command.commands.*;
import me.dev.thighware.util.Util;

import java.util.ArrayList;
import java.util.LinkedList;

public class CommandManager implements Util {

    private final ArrayList<Command> commands = new ArrayList();
    private String clientMessage = "{CatClient+}";
    private String prefix = "-";

    public CommandManager() {
        this.commands.add(new BindCommand());
        this.commands.add(new PrefixCommand());
        this.commands.add(new FriendCommand());
        this.commands.add(new WhoAmICommand());
        this.commands.add(new HelpCommand());
        this.commands.add(new NameMcCommand());
        this.commands.add(new ToggleCommand());
    }

    public static String[] removeElement(String[] input, int indexToDelete) {
        LinkedList<String> result = new LinkedList<String>();
        for (int i = 0; i < input.length; ++i) {
            if (i == indexToDelete) continue;
            result.add(input[i]);
        }
        return result.toArray(input);
    }

    private static String strip(String str, String key) {
        if (str.startsWith(key) && str.endsWith(key)) {
            return str.substring(key.length(), str.length() - key.length());
        }
        return str;
    }

    public void executeCommand(String command) {
        String[] parts = command.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        String name = parts[0].substring(1);
        String[] args = CommandManager.removeElement(parts, 0);
        for (int i = 0; i < args.length; ++i) {
            if (args[i] == null) continue;
            args[i] = CommandManager.strip(args[i], "\"");
        }
        for (Command c : this.commands) {
            if (!c.getName().equalsIgnoreCase(name)) continue;
            c.execute(parts);
            return;
        }
        Command.sendMessage(ChatFormatting.GRAY + "Command doesnt exist, you can do '.help' for all of the commands.");
    }

    public Command getCommandByName(String name) {
        for (Command command : this.commands) {
            if (!command.getName().equals(name)) continue;
            return command;
        }
        return null;
    }

    public ArrayList<Command> getCommands() {
        return this.commands;
    }

    public String getClientMessage() {
        return this.clientMessage;
    }

    public void setClientMessage(String clientMessage) {
        this.clientMessage = clientMessage;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

}
