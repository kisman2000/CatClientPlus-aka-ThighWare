package me.dev.thighware.api.click.gui;

import me.dev.thighware.Thighware;
import me.dev.thighware.api.click.gui.comps.ModuleButton;
import me.dev.thighware.api.module.Category;
import me.dev.thighware.api.module.Module;
import me.dev.thighware.util.RenderUtil;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;

public class Frame extends GuiScreen {

    public Category category;
    public ArrayList<me.dev.thighware.api.click.gui.Component> components;
    private boolean open;
    private final int width;
    private int y;
    private int x;
    private final int barHeight;
    private boolean isDragging;
    public int dragX;
    public int dragY;
    private int height;
    private Module module;

    public Frame(Category category) {
        this.category = category;
        this.components = new ArrayList<>();
        this.width = 90;
        this.x = 5;
        this.y = 5;
        this.barHeight = 15;
        this.dragX = 0;
        this.open = true;
        this.isDragging = false;
        int componentY = this.barHeight;
        for (Module m : Thighware.moduleManager.getModulesInCategory(category)) {
            ModuleButton moduleButton = new ModuleButton(m, this, componentY);
            components.add(moduleButton);
            componentY += 16;
        }
        update();
    }

    public void renderFrame() {
        RenderUtil.drawOutlineRect(x, y, x + width, y + barHeight, ClickGuiMainScreen.color.getRGB());
        Thighware.textManager.drawString( category.name(), x + width / 2 - Thighware.textManager.getStringWidth(category.name()) / 2, y + 1, -1, true,true);
        if (open && !components.isEmpty()) {
            components.forEach(me.dev.thighware.api.click.gui.Component::render);
        }
    }

    public ArrayList<me.dev.thighware.api.click.gui.Component> getComponents() {
        return this.components;
    }

    public void setX(final int newX) {
        this.x = newX;
    }

    public void setY(final int newY) {
        this.y = newY;
    }

    public void setDrag(final boolean drag) {
        this.isDragging = drag;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(final boolean open) {
        this.open = open;
    }

    public void update() {
        int off = this.barHeight;
        for (final Component comp : this.components) {
            comp.setOffset(off);
            off += comp.getHeight();
        }
        this.height = off;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public void updatePosition(final int mouseX, final int mouseY) {
        if (this.isDragging) {
            this.setX(mouseX - this.dragX);
            this.setY(mouseY - this.dragY);
        }
    }

    public boolean isHover(final double x, final double y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.barHeight;
    }

}