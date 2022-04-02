package me.dev.thighware.api.friend;

import net.minecraft.entity.player.EntityPlayer;

import java.util.*;

public class FriendManager {

    public List<Friend> friends;

    public FriendManager() {
        friends = new ArrayList<>();
    }

    public void addFriend(String name) {
        if (!this.isFriend(name)) {
            this.friends.add(new Friend(name));
        }
    }

    public void removeFriend(String name) {
        this.friends.removeIf(player -> player.getName().equalsIgnoreCase(name));
    }

    public boolean isFriend(String name) {
        for (Friend player : this.friends) {
            if (player.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasFriends() {
        return !this.friends.isEmpty();
    }

    public List<Friend> getFriends() {
        return this.friends;
    }

    public void toggleFriend(String name) {
        if (this.isFriend(name)) {
            this.removeFriend(name);
        } else {
            this.addFriend(name);
        }
    }

    public void clear() {
        this.friends.clear();
    }

    public void setFriends(List<Friend> list) {
        this.friends = list;
    }

}