package me.dev.thighware.impl.modules.misc;

import me.dev.thighware.api.module.Category;
import me.dev.thighware.api.module.Module;
import me.dev.thighware.api.setting.Setting;
import me.dev.thighware.util.Timer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * @author v1_2.
 * @since 3/4/2022.
 */
public class Spammer extends Module {
    public Timer timer = new Timer();
    public int message = 1;
    public Setting<Double> delay = this.register("Delay", this, 1, 1, 10);

    public Spammer() {
        super("Spammer", Category.MISC, "spams");
    }
    @SubscribeEvent
    public void onUpdate(TickEvent.ClientTickEvent event) {
        if (this.timer.passedMs(this.delay.getValue().longValue() * 1000L)) {
            mc.player.sendChatMessage("x");
            timer.reset();
        }
    }
}



