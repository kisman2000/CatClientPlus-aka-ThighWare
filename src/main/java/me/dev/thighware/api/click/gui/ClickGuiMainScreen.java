package me.dev.thighware.api.click.gui;

import me.dev.thighware.api.module.Category;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClickGuiMainScreen extends GuiScreen {

    public static List<Frame> frames;
    public static Color color;
    private ArrayList<Component> components = new ArrayList<>();
    public ClickGuiMainScreen() {
        frames = new ArrayList<>();
        int frameX = 10;

        for (final Category category : Category.values()) {
            final Frame frame = new Frame(category);
            frame.setX(frameX);
            ClickGuiMainScreen.frames.add(frame);
            frameX += frame.getWidth() + 10;
        }
    }

    @Override
    public void onGuiClosed() {
        // TODO Auto-generated method stub
        for(Frame fr : frames) {
            fr.setY(5);
        }
        super.onGuiClosed();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        color = new Color(0, 0, 0, 255);
        drawDefaultBackground();
        frames.forEach(frame -> {
            frame.renderFrame();
            frame.updatePosition(mouseX, mouseY);
            frame.getComponents().forEach(c -> c.updateComponent(mouseX, mouseY));
        });
    }

    public void handleMouseInput() throws IOException {
        // TODO Auto-generated method stub
        double scrollspeed = 50;
        float Scroll =+ Math.signum(Mouse.getEventDWheel());
        for(Frame f : frames)
        {
            if(Scroll>0)
            {
                if((f.getY()+(int)scrollspeed)<=5)
                {

                    f.setY(f.getY()+(int)scrollspeed);
                }
            }
            if(Scroll<0)
            {
                f.setY(f.getY()-(int)scrollspeed);
            }


        }
        super.handleMouseInput();
    }


    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for (final Frame frame : frames) {
            if (frame.isHover(mouseX, mouseY) && mouseButton == 0) {
                frame.setDrag(true);
                frame.dragX = mouseX - frame.getX();
                frame.dragY = mouseY - frame.getY();
            }
            if (frame.isHover(mouseX, mouseY) && mouseButton == 1) frame.setOpen(!frame.isOpen());

            if (frame.isOpen() && !frame.getComponents().isEmpty()) {
                for (final Component component : frame.getComponents()) {
                    component.mouseClicked(mouseX, mouseY, mouseButton);
                }
            }
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        for (final Frame frame : frames) {
            frame.setDrag(false);
        }
        for (final Frame frame : frames) {
            if (frame.isOpen() && !frame.getComponents().isEmpty()) {
                for (final Component component : frame.getComponents()) {
                    component.mouseReleased(mouseX, mouseY, state);
                }
            }
        }
    }

    public void drawGradient(int left, int top, int right, int bottom, int startColor, int endColor) {
        drawGradientRect(left, top, right, bottom, startColor, endColor);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        for (final Frame frame : ClickGuiMainScreen.frames) {
            if (frame.isOpen() && keyCode != 1 && !frame.getComponents().isEmpty()) {
                for (final Component component : frame.getComponents()) {
                    component.keyTyped(keyCode);
                }
            }
        }

        System.out.println(typedChar);



        if (keyCode == Keyboard.KEY_DOWN) {
            for (Frame frame : frames) {
                frame.setY(frame.getY() -1);
            }
        }

        if (keyCode == Keyboard.KEY_UP) {
            for (Frame frame : frames) {
                if(frame.getY() +1<=5)
                {
                    frame.setY(frame.getY() +1);
                }

            }
        }

        if (keyCode == Keyboard.KEY_ESCAPE) {
            mc.displayGuiScreen(null);

            if (mc.currentScreen == null) {
                mc.setIngameFocus();
            }
        }
    }
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}