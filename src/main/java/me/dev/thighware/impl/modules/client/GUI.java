package me.dev.thighware.impl.modules.client;

import me.dev.thighware.Thighware;
import me.dev.thighware.api.module.Category;
import me.dev.thighware.api.module.Module;
import me.dev.thighware.api.setting.Setting;
import org.lwjgl.input.Keyboard;

/**
 * @author v1_2.
 * @since 2/25/2022.
 */
public class GUI extends Module {
    public Setting<Double> r = this.register("Red", this, 255, 0, 255);
    public Setting<Double> g = this.register("Green", this, 255, 0, 255);
    public Setting<Double> b = this.register("Blue", this, 255, 0, 255);
    public GUI() {
        super("GUI", Category.CLIENT, "opens up the clickgui");
        setKey(Keyboard.KEY_RSHIFT);
    }
    @Override
    public void onEnable() {
        mc.displayGuiScreen(Thighware.gui);
        toggle();
    }
}
