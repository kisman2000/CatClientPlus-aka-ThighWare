package me.dev.thighware.impl.modules.movement;

import me.dev.thighware.api.module.Category;
import me.dev.thighware.api.module.Module;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * @author v1_2.
 * @since 2/26/2022.
 */
public class Sprint extends Module {
    public Sprint() {
        super("Sprint", Category.MOVEMENT, "auto sprints");
    }
    @SubscribeEvent
    public void onUpdate(TickEvent.ClientTickEvent event) {
        mc.player.setSprinting(true);
    }
}
