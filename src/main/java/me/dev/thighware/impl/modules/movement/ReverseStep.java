package me.dev.thighware.impl.modules.movement;

import me.dev.thighware.api.module.Category;
import me.dev.thighware.api.module.Module;
import me.dev.thighware.api.setting.Setting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * @author v1_2.
 * @since 2/25/2022.
 */
public class ReverseStep extends Module {
    public Setting<Double> stepHeight = this.register("StepHeight", this, 2.5, 0, 5);
    public ReverseStep() {
        super("ReverseStep", Category.MOVEMENT, "makes you go up/down blocks fast");
    }
    @SubscribeEvent
    public void onUpdate(TickEvent.ClientTickEvent event) {
        if(mc.player.onGround && !mc.player.isInWater() && !mc.player.isInLava()) {
            mc.player.motionY -= stepHeight.getValue();
        }
    }
}
