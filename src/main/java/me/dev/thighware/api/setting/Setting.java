package me.dev.thighware.api.setting;

import me.dev.thighware.api.module.Module;
import me.dev.thighware.util.Util;

import java.util.List;

public class Setting<T> implements Util {

    public String name;

    public Module parent;

    public T value;

    public double min, max;

    public List<String> modes;

    public T defaultValue;

    public Type type;

    public Setting(String name, Module parent, T value) {
        this.name = name;
        this.parent = parent;
        this.value = value;

        this.type = Type.BOOLEAN;
    }

    public Setting(String name, Module parent, T value, double min, double max) {
        this.name = name;
        this.parent = parent;
        this.value = value;
        this.min = min;
        this.max = max;

        this.type = Type.NUMBER;
    }

    public Setting(String name, Module parent, T value, List<String> modes) {
        this.name = name;
        this.parent = parent;
        this.value = value;
        this.modes = modes;

        this.type =Type.MODE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Module getParent() {
        return parent;
    }

    public void setParent(Module parent) {
        this.parent = parent;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public List<String> getModes() {
        return modes;
    }

    public void setModes(List<String> modes) {
        this.modes = modes;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        BOOLEAN,
        NUMBER,
        MODE
    }

}
