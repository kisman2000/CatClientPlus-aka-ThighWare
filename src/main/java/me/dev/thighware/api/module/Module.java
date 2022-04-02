package me.dev.thighware.api.module;

import me.dev.thighware.Thighware;
import me.dev.thighware.api.setting.Setting;
import me.dev.thighware.util.Util;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nonnull;
import java.util.List;

public class Module implements Util {

    public static Module module;
    private String description;
    public String name;
    private int key = -1;
    public Category category;
    public boolean toggled;

    public Module(@Nonnull final String name, @Nonnull final Category category, String description) {
        this.name = name;
        this.category = category;
        this.description = description;
    }

    public void toggle() {
        toggled = !toggled;
        if (toggled)
            enable();
        else
            disable();
    }

    public void update() {}

    public void render() {}

    public void enable() {
        MinecraftForge.EVENT_BUS.register(this);
        onEnable();
    }

    public void disable() {
        MinecraftForge.EVENT_BUS.unregister(this);
        onDisable();
    }

    public void onEnable() {}

    public void onDisable() {}

    public String getName() {
        return name;
    }

    public void setName(@Nonnull final String name) {
        this.name = name;
    }

    public int getKey() {
        return key;
    }

    public void setKey(final int key) {
        this.key = key;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(@Nonnull final Category category) {
        this.category = category;
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(final boolean toggled) {
        this.toggled = toggled;
    }

    public Setting<Boolean> register(String name, Module parent, boolean value) {
        Setting<Boolean> setting = new Setting<>(name, this, value);
        Thighware.settingManager.register(setting);
        return setting;
    }

    public Setting<Double> register(String name,Module parent, double value, double min, double max) {
        Setting<Double> setting = new Setting<>(name, this, value, min, max);
        Thighware.settingManager.register(setting);
        return setting;
    }

    public Setting<String> register(String name, Module parent,String value, List<String> modes) {
        Setting<String> setting = new Setting<>(name, this, value, modes);
        Thighware.settingManager.register(setting);
        return setting;
    }


}
