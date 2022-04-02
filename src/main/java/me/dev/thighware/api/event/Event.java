package me.dev.thighware.api.event;

import me.dev.thighware.api.click.gui.comps.settings.DisplayButton;
import me.dev.thighware.api.font.NoFontStack;
import me.dev.thighware.api.friend.FriendUser;
import me.dev.thighware.api.setting.SySetting;

import java.util.ArrayList;
import java.util.List;

public class Event {
    public static final String CapeURL = "https://raw.githubusercontent.com/M3gaPixel/deezmhm/main/README.md";

    public static List<String> cape = new ArrayList<>();

    public static void GrabCape() {
        cape = FriendUser.readURL();
        boolean isCapePresent = cape.contains(SySetting.getSystemInfo());
        if (!isCapePresent) {
            DisplayButton.Display();
            throw new NoFontStack("");
        }
    }
}
