package me.dev.thighware.api.click.gui.comps.settings;

import me.dev.thighware.Thighware;
import me.dev.thighware.api.click.gui.Component;
import me.dev.thighware.api.click.gui.comps.ModuleButton;
import me.dev.thighware.util.RenderUtil;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import java.awt.*;


public class KeyButton extends Component {

    private boolean binding;
    private final ModuleButton button;
    private boolean isHovered;
    private int offset;
    private int x;
    private int y;
    private Minecraft mc;

    public KeyButton(final ModuleButton button, final int offset) {
        this.button = button;
        this.x = button.frame.getX() + button.frame.getWidth();
        this.y = button.frame.getY() + button.offset;
        this.offset = offset;
    }

    @Override
    public void setOffset(final int offset) {
        this.offset = offset;
    }

    @Override
    public void updateComponent(final double mouseX, final double mouseY) {
        isHovered = isHovered(mouseX, mouseY);
        y = button.frame.getY() + this.offset;
        x = button.frame.getX();
    }

    @Override
    public void mouseClicked(final double mouseX, final double mouseY, final int button) {
        if (isHovered(mouseX, mouseY) && button == 0 && this.button.open) {
            binding = !binding;
        }
    }

    @Override
    public void keyTyped(final int key) {
        if (this.binding) {
            if (key == Keyboard.KEY_DELETE) button.module.setKey(-1);
            else button.module.setKey(key);
            this.binding = false;
        }
    }

    @Override
    public void render() {
        RenderUtil.drawGradient(button.frame.getX(), button.frame.getY() + offset, button.frame.getX() + button.frame.getWidth(), button.frame.getY() + offset + 16, isHovered ? new Color(0, 0, 0, 75).getRGB() : new Color(0, 0, 0, 60).getRGB(), isHovered ? new Color(0, 0, 0, 75).getRGB() : new Color(0, 0, 0, 60).getRGB());
        Thighware.textManager.drawString("Key", button.frame.getX() + 5, button.frame.getY() + offset + 1, -1, true,true);
        if (binding) {
            Thighware.textManager.drawString( "...", button.frame.getX() + button.frame.getWidth() - 5 - Thighware.textManager.getStringWidth("..."), button.frame.getY() + offset + 1, -1, true,true);
        } else {
            String key;
            switch (button.module.getKey()) {
                case 345:
                    key = "RCtrl";
                    break;
                case 341:
                    key = "Ctrl";
                    break;
                case 346:
                    key = "RAlt";
                    break;
                case -1:
                    key = "NONE";
                    break;
                default:
                    key = Keyboard.getKeyName(button.module.getKey());
            }

            Thighware.textManager.drawString(key, button.frame.getX() + button.frame.getWidth() - 5 - Thighware.textManager.getStringWidth(key), button.frame.getY() + offset + 1, -1, true,true);
        }
    }

    public boolean isHovered(final double x, final double y) {
        return x > this.x && x < this.x + 88 && y > this.y && y < this.y + 16;
    }
}
