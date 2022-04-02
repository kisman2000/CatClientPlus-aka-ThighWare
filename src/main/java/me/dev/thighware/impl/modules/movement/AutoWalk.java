package me.dev.thighware.impl.modules.movement;

import me.dev.thighware.api.module.Category;
import me.dev.thighware.api.module.Module;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

/**
 * @author v1_2.
 * @since 2/24/2022.
 */
public class AutoWalk extends Module {
    public AutoWalk() {
        super("AutoWalk", Category.MOVEMENT, "amogus");
    }
    @SubscribeEvent
    public void onUpdateInput(InputUpdateEvent event) {
        event.getMovementInput().moveForward = 1.0f;
        if (mc.player.isInWater()) {
            mc.player.jump();
        }
    }
}
