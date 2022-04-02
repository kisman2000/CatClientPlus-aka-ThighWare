package me.dev.thighware.util;

import me.dev.thighware.Thighware;
import net.minecraft.client.Minecraft;

public interface Util {

    /**
     * This is an instance of {@link Minecraft}.
     */
    Minecraft mc = Minecraft.getMinecraft();

    /**
     * This is a static instance of {@link Thighware}.
     */
    Thighware thighware = Thighware.INSTANCE;

    /**
     * Checks and returns whether the player or world is null as sometimes u need to.
     *
     * @return Whether the player or world is null.
     */
    static boolean fullNullCheck() {
        return mc.player == null || mc.world == null;
    }
}
