package me.dev.thighware.api.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.dev.thighware.Thighware;
import me.dev.thighware.api.command.Command;
import me.dev.thighware.api.friend.Friend;

//TODO: fix this later

public class FriendCommand extends Command {
    public FriendCommand() {
        super("friend", new String[]{"[add]", "[del]", "[list]"});
    }

    private Friend friend;

    @Override
    public void execute(String[] var1) {
        if (commands.length == 1) {
            if (commands[0].equalsIgnoreCase("list")) {
                if (Thighware.friendManager.hasFriends()) {
                    Command.sendMessage(ChatFormatting.BOLD + "Listing friends");
                    for (Friend player : Thighware.friendManager.getFriends()) {
                        Command.sendMessage(player.getName());
                    }
                } else {
                    Command.sendMessage("u got no friends :(");
                }
            } else if (commands[0].equalsIgnoreCase("clear")) {
                Thighware.friendManager.clear();
                Command.sendMessage("Cleared friends list");
            } else {
                Command.sendMessage("Friend <add/del/list/clear>");
            }
            return;
        }

      String word1 = commands[0];
        String word2 = commands[1];
        if (word1 == null) return;
        switch (word1) {
            case "add":
                if (word2 == null) {
                    Command.sendMessage("need name");
                    return;
                }
                Thighware.friendManager.addFriend(word2);
                Command.sendMessage(ChatFormatting.GREEN + word2 + ChatFormatting.RESET + " is now your friend");
                break;
            case "del":
                Thighware.friendManager.removeFriend(word2);
                Command.sendMessage(ChatFormatting.RED + word2 + ChatFormatting.RESET + " is no longer your friend");
                if (word2 == null) {
                    Command.sendMessage("need name");
                    return;
                }
                break;
        }
    }
}
