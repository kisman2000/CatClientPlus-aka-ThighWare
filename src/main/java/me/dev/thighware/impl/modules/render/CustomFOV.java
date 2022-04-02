package me.dev.thighware.impl.modules.render;

import me.dev.thighware.api.module.Category;
import me.dev.thighware.api.module.Module;
import me.dev.thighware.api.setting.Setting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nonnull;

public class CustomFOV extends Module {
    public CustomFOV() {
        super("CustomFOV", Category.RENDER, "changes ur fov");
    }

    public Setting<Double> amount = this.register("Amount",this,130,0,150);

    @SubscribeEvent
    public void onUpdate(TickEvent.ClientTickEvent event) {
        mc.gameSettings.fovSetting = amount.getValue().floatValue();
    }
}
