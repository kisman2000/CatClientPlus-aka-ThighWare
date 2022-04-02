package me.dev.thighware.api.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.dev.thighware.Thighware;
import me.dev.thighware.api.command.Command;
import me.dev.thighware.api.module.Module;
import org.lwjgl.input.Keyboard;

public class BindCommand extends Command {

    public BindCommand() {
        super("bind", new String[]{"[module]", "[key]"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            Command.sendMessage(getCommandPrefix() + "bind " + ChatFormatting.AQUA + "[module] [key]");
            return;
        }

        String rkey = commands[1];
        String moduleName = commands[0];
        Module module = Thighware.moduleManager.getModule(moduleName);

        if (module == null) {
            Command.sendMessage("Thats not a module :/");
            return;
        }

        if (rkey == null) {
            Command.sendMessage(getCommandPrefix() + "bind " + ChatFormatting.AQUA + "[module] [key]");
            return;
        }

        int key = Keyboard.getKeyIndex(rkey.toUpperCase());

        if (rkey.equalsIgnoreCase("none")) {
            key = 0;
        }

        if (key == 0) {
            module.setKey(key);
            Command.sendMessage(module.getName() + " keybind has been set to Nothing.");
            return;
        }

        module.setKey(key);
        Command.sendMessage(module.getName() + " keybind has been set to " + rkey.toUpperCase() + ".");
    }
}
