package me.dev.thighware.api.setting;

import me.dev.thighware.api.module.Module;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SettingManager {
    private final List<Setting> settings;

    public SettingManager() {
        settings = new ArrayList<>();
    }

    public void register(Setting setting) {
        settings.add(setting);
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public List<Setting> getSettings(Module module) {
        return settings.stream().filter(s -> s.getParent() == module).collect(Collectors.toList());
    }

    public Setting getSetting(String name, Module module) {
        return settings.stream().filter(s -> s.getParent() == module).filter(s -> s.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
