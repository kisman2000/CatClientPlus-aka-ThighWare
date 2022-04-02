package me.dev.thighware.impl.modules.combat;

import me.dev.thighware.api.event.events.PacketEvent;
import me.dev.thighware.api.module.Category;
import me.dev.thighware.api.module.Module;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author v1_2.
 * @since 2/25/2022.
 */
public class Velocity extends Module {
    public Velocity() {
        super("Velocity", Category.COMBAT, "fuck you knockbackkkk");
    }
    @SubscribeEvent
    public void onPush(PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketEntityVelocity || event.getPacket() instanceof SPacketExplosion) {
            event.setCanceled(true);
        }
    }
}
