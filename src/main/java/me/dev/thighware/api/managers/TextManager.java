package me.dev.thighware.api.managers;

import me.dev.thighware.api.font.CustomFontRenderer;
import me.dev.thighware.util.MathUtil;
import me.dev.thighware.util.Util;

import java.awt.*;

public class TextManager implements Util {


    private CustomFontRenderer customFont = new CustomFontRenderer(new Font("Futura", Font.ITALIC, 18), true, true);


    public void drawStringwithshadow(String text, float x, float y, int color) {
        mc.fontRenderer.drawStringWithShadow(text,x,y,color);
    }

    public void drawString(String text, float x, float y, int color, boolean shadow, boolean custom) {
        if (custom) {
            if (shadow) {
                this.customFont.drawStringWithShadow(text, x, y, color);
                return;
            }
            this.customFont.drawString(text, x, y, color);
            return;
        }
        mc.fontRenderer.drawString(text, x, y, color, shadow);
    }

    public float drawString(String text, float x, float y, int color, boolean shadow) {
        mc.fontRenderer.drawString(text, x, y, color, shadow);
        return x;
    }
    public void drawRainbowString(String text, float x, float y, int startColor, float factor, boolean shadow) {
        Color currentColor = new Color(startColor);
        float hueIncrement = 1.0f / factor;
        String[] rainbowStrings = text.split("\u00a7.");
        float currentHue = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), null)[0];
        float saturation = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), null)[1];
        float brightness = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), null)[2];
        int currentWidth = 0;
        boolean shouldRainbow = true;
        boolean shouldContinue = false;
        for (int i = 0; i < text.length(); ++i) {
            char currentChar = text.charAt(i);
            char nextChar = text.charAt(MathUtil.clamp(i + 1, 0, text.length() - 1));
            if ((String.valueOf(currentChar) + nextChar).equals("\u00a7r")) {
                shouldRainbow = false;
            } else if ((String.valueOf(currentChar) + nextChar).equals("\u00a7+")) {
                shouldRainbow = true;
            }
            if (shouldContinue) {
                shouldContinue = false;
                continue;
            }
            if ((String.valueOf(currentChar) + nextChar).equals("\u00a7r")) {
                String escapeString = text.substring(i);
                this.drawString(escapeString, x + (float) currentWidth, y, Color.WHITE.getRGB(), shadow);
                break;
            }
            this.drawString(String.valueOf(currentChar).equals("\u00a7") ? "" : String.valueOf(currentChar), x + (float) currentWidth, y, shouldRainbow ? currentColor.getRGB() : Color.WHITE.getRGB(), shadow);
            if (String.valueOf(currentChar).equals("\u00a7")) {
                shouldContinue = true;
            }
            currentWidth += this.getStringWidth(String.valueOf(currentChar));
            if (String.valueOf(currentChar).equals(" ")) continue;
            currentColor = new Color(Color.HSBtoRGB(currentHue, saturation, brightness));
            currentHue += hueIncrement;
        }
    }

    public int getStringWidth(String text) {
        return TextManager.mc.fontRenderer.getStringWidth(text);
    }

    public int getFontHeight() {
        return TextManager.mc.fontRenderer.FONT_HEIGHT;
    }


}
