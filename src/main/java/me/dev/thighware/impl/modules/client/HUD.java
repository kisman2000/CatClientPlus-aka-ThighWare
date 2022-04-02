package me.dev.thighware.impl.modules.client;

import me.dev.thighware.Thighware;
import me.dev.thighware.api.module.Category;
import me.dev.thighware.api.module.Module;
import me.dev.thighware.api.setting.Setting;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author v1_2.
 * @since 2/24/2022.
 */
public class HUD extends Module {
    public Setting<Boolean> watermark = this.register("Watermark", this, false);
    public Setting<Boolean> fps = this.register("Fps", this, false);
    public Setting<Double> x = this.register("XLevel", this, 1, 1, 1000);
    public Setting<Double> y = this.register("YLevel", this, 1, 1, 1000);

    public HUD() {
        super("HUD", Category.CLIENT, "amogus");
    }
    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Text event) {
        if (mc.world != null && mc.player != null) {
            // final int[] counter = {1};
            if (this.watermark.getValue()) {
                mc.fontRenderer.drawStringWithShadow(Thighware.NAME + Thighware.VERSION, this.x.getValue().floatValue(), this.y.getValue().floatValue(), -1);
            }
            if (this.fps.getValue()) {
                mc.fontRenderer.drawStringWithShadow("FPS: " + Minecraft.getDebugFPS(), 1, 1, -1);
            }
        }
    }
}
