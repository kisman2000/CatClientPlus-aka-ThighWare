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
public class Step extends Module {
    public Setting<Double> stepHeight = this.register("StepHeight", this, 2.5, 0, 5);

    public Step() {
        super("Step", Category.MOVEMENT, "makes you go up/down blocks fast");
    }
    @SubscribeEvent
    public void onUpdate(TickEvent.ClientTickEvent event) {
        mc.player.stepHeight = stepHeight.getValue().floatValue();
    }
    @Override
    public void onDisable() {
        mc.player.stepHeight = 0.5f;
    }
}
