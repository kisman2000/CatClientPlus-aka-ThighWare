package me.dev.thighware.impl.modules.combat;

import me.dev.thighware.Thighware;
import me.dev.thighware.api.module.Category;
import me.dev.thighware.api.module.Module;
import me.dev.thighware.api.setting.Setting;
import me.dev.thighware.util.MathUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nonnull;

public class KillAura extends Module {

    public Setting<Double> range = this.register("Range",this,6.0,0,7.0);
    public Setting<Boolean> rotate = this.register("Rotate",this,false);
    public Setting<Boolean> packet = this.register("Packet",this,true);

    private boolean rotating;

    public KillAura() {
        super("KillAura", Category.COMBAT, "attacks enemies");
    }

    @SubscribeEvent
    public void onUpdate(TickEvent.ClientTickEvent event) {
        if (mc.player != null || mc.world != null) {
            for (EntityPlayer target : mc.world.playerEntities) {
                if (target != mc.player) {
                    if (mc.player.getDistance(target) < this.range.getValue()) {
                        //if (Thighware.friendManager.isFriend(target.getName())) {
                        //    return;
                        //}
                        if (target.isDead || target.getHealth() > 0.0f) {
                            if (this.rotating && this.rotate.getValue()) {
                                final float[] calcAngle = MathUtil.calcAngle(mc.player.getPositionEyes(mc.getRenderPartialTicks()), target.getPositionVector());
                                mc.player.rotationYaw = calcAngle[0];
                            }
                            attack(target, packet.getValue(), true);
                        }
                        target = target;
                    }
                    else {
                        this.rotating = false;
                    }
                }
            }
        }
    }

    public static void attack(Entity entity, boolean packet, boolean swingArm) {
        if (packet) {
            mc.player.connection.sendPacket(new CPacketUseEntity(entity));
        } else {
            mc.playerController.attackEntity(mc.player, entity);
        }
        if (swingArm) {
            mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }
}

