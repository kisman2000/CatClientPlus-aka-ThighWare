package me.dev.thighware.api.click.gui.comps;

import me.dev.thighware.Thighware;
import me.dev.thighware.api.click.gui.Component;
import me.dev.thighware.api.click.gui.Frame;
import me.dev.thighware.api.click.gui.comps.settings.BooleanButton;
import me.dev.thighware.api.click.gui.comps.settings.KeyButton;
import me.dev.thighware.api.click.gui.comps.settings.ModeButton;
import me.dev.thighware.api.click.gui.comps.settings.SliderButton;
import me.dev.thighware.api.module.Module;
import me.dev.thighware.api.setting.Setting;
import me.dev.thighware.util.RenderUtil;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

import java.awt.*;
import java.util.ArrayList;

public class ModuleButton extends Component {

    public Gui gui;
    public Module module;
    public me.dev.thighware.api.click.gui.Frame frame;
    public int offset;
    private boolean isHovered;
    private final ArrayList<Component> components;
    public boolean open;
    private final int height;

    public ModuleButton(Module module, Frame frame, int offset) {
        this.module = module;
        this.frame = frame;
        this.offset = offset;
        this.components = new ArrayList<>();
        this.open = false;
        this.height = 16;
        int settingY = this.offset + 16;
        if (!Thighware.settingManager.getSettings(module).isEmpty()) {
            for (Setting s : Thighware.settingManager.getSettings(module)) {
                switch (s.getType()) {
                    case BOOLEAN:
                        components.add(new BooleanButton(s, this, settingY));
                        continue;
                    case NUMBER:
                        components.add(new SliderButton(s, this, settingY));
                        continue;
                    case MODE:
                        components.add(new ModeButton(s, this, settingY));
                        continue;
                }
            }
        }
        components.add(new KeyButton(this, settingY));
    }

    @Override
    public void setOffset(final int offset) {
        this.offset = offset;
        int settingY = this.offset + 16;
        for (Component c : components) {
            c.setOffset(settingY);
            settingY += 16;
        }
    }

    @Override
    public int getHeight() {
        if (open) return 16 * (components.size() + 1);
        return 16;
    }

    @Override
    public void updateComponent(final double mouseX, final double mouseY) {
        isHovered = isHovered(mouseX, mouseY);
        if (!components.isEmpty()) {
            components.forEach(c -> {
                c.updateComponent(mouseX, mouseY);
            });
        }
    }

    @Override
    public void mouseClicked(final double mouseX, final double mouseY, final int button) {
        if (isHovered(mouseX, mouseY) && button == 0) {
            mc.getSoundHandler().playSound((ISound) PositionedSoundRecord.getMasterRecord((SoundEvent) SoundEvents.UI_BUTTON_CLICK, (float)1.0f));
            module.toggle();
        }
        if (isHovered(mouseX, mouseY) && button == 1) {
            open = !open;
            mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord((SoundEvent)SoundEvents.UI_BUTTON_CLICK, (float)1.0f));
            frame.update();
        }
        components.forEach(c -> {
            c.mouseClicked(mouseX, mouseY, button);
        });
    }

    @Override
    public void mouseReleased(final double mouseX, final double mouseY, final int mouseButton) {
        components.forEach(c -> {
            c.mouseReleased(mouseX, mouseY, mouseButton);
        });
    }

    @Override
    public void keyTyped(final int key) {
        components.forEach(c -> {
            c.keyTyped(key);
        });
    }

    @Override
    public void render() {
        RenderUtil.drawGradient(frame.getX(), frame.getY() + offset, frame.getX() + frame.getWidth(), frame.getY() + offset + 16, isHovered ? new Color(0, 0, 0, 75).getRGB() : new Color(0, 0, 0, 60).getRGB(), isHovered ? new Color(0, 0, 0, 75).getRGB() : new Color(0, 0, 0, 60).getRGB());
        Thighware.textManager.drawString(module.getName(), frame.getX() + 3, frame.getY() + offset + 1, module.isToggled() ? -1 : new Color(170, 170, 170).getRGB(), true,true);
        if (open) components.forEach(Component::render);
    }

    public boolean isHovered(final double x, final double y) {
        return x > this.frame.getX() && x < this.frame.getX() + this.frame.getWidth() && y > this.frame.getY() + this.offset && y < this.frame.getY() + 16 + this.offset;
    }

}
