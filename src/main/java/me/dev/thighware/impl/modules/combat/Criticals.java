package me.dev.thighware.impl.modules.combat;

import me.dev.thighware.api.module.Category;
import me.dev.thighware.api.module.Module;
import me.dev.thighware.api.setting.Setting;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author v1_2.
 * @since 2/25/2022.
 */
public class Criticals extends Module {
        public Setting<Boolean> packet = this.register("Packet", this, true);
    public Criticals() {
        super("Criticals", Category.COMBAT, "crits on every hit");
    }

    @SubscribeEvent
    public void onAttack(AttackEntityEvent event) {
        if (Criticals.mc.player.isInWater() || Criticals.mc.player.isInLava()) {
            return;
        }
        if (Criticals.mc.player.onGround) {
            if (this.packet.getValue()) {
                Criticals.mc.player.connection.sendPacket((Packet) new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.1625, Criticals.mc.player.posZ, false));
                Criticals.mc.player.connection.sendPacket((Packet) new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                Criticals.mc.player.onCriticalHit(event.getTarget());
            }
        }
    }
}