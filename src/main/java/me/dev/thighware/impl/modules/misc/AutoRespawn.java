package me.dev.thighware.impl.modules.misc;

import me.dev.thighware.api.module.Category;
import me.dev.thighware.api.module.Module;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

/**
 * @author v1_2.
 * @since 2/25/2022.
 */
public class AutoRespawn extends Module {
    public AutoRespawn() {
        super("AutoRespawn", Category.MISC, "auto respawns you");
    }
    @SubscribeEvent
    public void onDisplayDeathScreen(@Nonnull final GuiOpenEvent event) {
        if (event.getGui() instanceof GuiGameOver) {
            event.setCanceled(true);
            mc.player.respawnPlayer();
        }
    }
}
