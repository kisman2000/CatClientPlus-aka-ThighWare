package me.dev.thighware.api.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.dev.thighware.api.command.Command;
import net.minecraft.client.Minecraft;

public class WhoAmICommand extends Command {
    public WhoAmICommand() {
        super("whoami");
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            Command.sendMessage(ChatFormatting.GREEN + "Your username is " + Minecraft.getMinecraft().getSession().getUsername());
        }
    }
}