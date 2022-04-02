package me.dev.thighware.api.friend;

import me.dev.thighware.api.event.Event;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FriendUser {

    /**
     * Opens and reads the URL.
     */

    public static List<String> readURL() {
        List<String> s = new ArrayList<>();
        try {
            final URL url = new URL(Event.CapeURL);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String cape;
            while ((cape = bufferedReader.readLine()) != null) {
                s.add(cape);
            }
        } catch (Exception e) {

            /**
             * Optional logging below.
             */

            //FMLLog.log.info(e);
        }
        return s;
    }
}
