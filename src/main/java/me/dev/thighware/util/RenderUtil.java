package me.dev.thighware.util;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class RenderUtil extends Tessellator {

    public static final RenderUtil INSTANCE = new RenderUtil();
    public static float zLevel;
    private static final AxisAlignedBB DEFAULT_AABB = new AxisAlignedBB(0, 0, 0, 1, 1, 1);

    public RenderUtil() {
        super(0x200000);
    }

    public static void drawGradient(int left, int top, int right, int bottom, int startColor, int endColor) {
        drawGradientRect(left, top, right, bottom, startColor, endColor);
    }

    public static void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {
        float f = (float) (startColor >> 24 & 255) / 255.0F;
        float f1 = (float) (startColor >> 16 & 255) / 255.0F;
        float f2 = (float) (startColor >> 8 & 255) / 255.0F;
        float f3 = (float) (startColor & 255) / 255.0F;
        float f4 = (float) (endColor >> 24 & 255) / 255.0F;
        float f5 = (float) (endColor >> 16 & 255) / 255.0F;
        float f6 = (float) (endColor >> 8 & 255) / 255.0F;
        float f7 = (float) (endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double) right, (double) top, (double) zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double) left, (double) top, (double) zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double) left, (double) bottom, (double) zLevel).color(f5, f6, f7, f4).endVertex();
        bufferbuilder.pos((double) right, (double) bottom, (double) zLevel).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void releaseGL() {
        GlStateManager.enableCull();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
        GlStateManager.color(1, 1, 1);
        GL11.glColor4f(1, 1, 1, 1);
    }

    public static int rainbow(int delay) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 10.0);
        rainbowState %= 360;
        return Color.getHSBColor((float) (rainbowState / 360.0f), 1f, 1f).getRGB();
    }

    public static void drawLine(float x, float y, float x1, float y1, float thickness, int hex) {
        float red = (float) (hex >> 16 & 0xFF) / 255.0f;
        float green = (float) (hex >> 8 & 0xFF) / 255.0f;
        float blue = (float) (hex & 0xFF) / 255.0f;
        float alpha = (float) (hex >> 24 & 0xFF) / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((int) 770, (int) 771, (int) 1, (int) 0);
        GlStateManager.shadeModel((int) 7425);
        GL11.glLineWidth((float) thickness);
        GL11.glEnable((int) 2848);
        GL11.glHint((int) 3154, (int) 4354);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double) x, (double) y, 0.0).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos((double) x1, (double) y1, 0.0).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel((int) 7424);
        glDisable((int) 2848);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

    public static void drawOutlineRect(float x, float y, float w, float h, int color) {
        float alpha = (float) (color >> 24 & 0xFF) / 255.0f;
        float red = (float) (color >> 16 & 0xFF) / 255.0f;
        float green = (float) (color >> 8 & 0xFF) / 255.0f;
        float blue = (float) (color & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.glLineWidth((float) 1.0f);
        GlStateManager.tryBlendFuncSeparate((int) 770, (int) 771, (int) 1, (int) 0);
        bufferbuilder.begin(2, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double) x, (double) h, 0.0).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos((double) w, (double) h, 0.0).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos((double) w, (double) y, 0.0).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos((double) x, (double) y, 0.0).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawESP(AxisAlignedBB bb, float red, float green, float blue, float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        glDisable(3553);
        GL11.glEnable(2848);
        glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red / 255f, green / 255f, blue / 255f, alpha / 255f);
        drawBox(bb);
        glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        glDisable(3042);
        glPopMatrix();
        GL11.glColor4f(1f, 1f, 1f, 1f);
    }

    public static void drawBox(AxisAlignedBB boundingBox) {
        if (boundingBox == null) {
            return;
        }

        GlStateManager.glBegin(GL11.GL_QUADS);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.minY, (float) boundingBox.maxZ);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.minY, (float) boundingBox.maxZ);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.maxY, (float) boundingBox.maxZ);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.maxY, (float) boundingBox.maxZ);
        GlStateManager.glEnd();

        GlStateManager.glBegin(GL11.GL_QUADS);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.minY, (float) boundingBox.maxZ);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.minY, (float) boundingBox.maxZ);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.maxY, (float) boundingBox.maxZ);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.maxY, (float) boundingBox.maxZ);
        GlStateManager.glEnd();

        GlStateManager.glBegin(GL11.GL_QUADS);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.minY, (float) boundingBox.minZ);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.minY, (float) boundingBox.maxZ);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.maxY, (float) boundingBox.maxZ);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.maxY, (float) boundingBox.minZ);
        GlStateManager.glEnd();

        GlStateManager.glBegin(GL11.GL_QUADS);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.minY, (float) boundingBox.maxZ);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.minY, (float) boundingBox.minZ);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.maxY, (float) boundingBox.minZ);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.maxY, (float) boundingBox.maxZ);
        GlStateManager.glEnd();

        GlStateManager.glBegin(GL11.GL_QUADS);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.minY, (float) boundingBox.maxZ);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.minY, (float) boundingBox.minZ);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.maxY, (float) boundingBox.minZ);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.maxY, (float) boundingBox.maxZ);
        GlStateManager.glEnd();

        GlStateManager.glBegin(GL11.GL_QUADS);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.minY, (float) boundingBox.minZ);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.minY, (float) boundingBox.maxZ);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.maxY, (float) boundingBox.maxZ);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.maxY, (float) boundingBox.minZ);
        GlStateManager.glEnd();

        GlStateManager.glBegin(GL11.GL_QUADS);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.minY, (float) boundingBox.minZ);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.minY, (float) boundingBox.minZ);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.maxY, (float) boundingBox.minZ);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.maxY, (float) boundingBox.minZ);
        GlStateManager.glEnd();

        GlStateManager.glBegin(GL11.GL_QUADS);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.minY, (float) boundingBox.minZ);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.minY, (float) boundingBox.minZ);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.maxY, (float) boundingBox.minZ);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.maxY, (float) boundingBox.minZ);
        GlStateManager.glEnd();

        GlStateManager.glBegin(GL11.GL_QUADS);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.maxY, (float) boundingBox.minZ);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.maxY, (float) boundingBox.minZ);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.maxY, (float) boundingBox.maxZ);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.maxY, (float) boundingBox.maxZ);
        GlStateManager.glEnd();

        GlStateManager.glBegin(GL11.GL_QUADS);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.maxY, (float) boundingBox.minZ);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.maxY, (float) boundingBox.minZ);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.maxY, (float) boundingBox.maxZ);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.maxY, (float) boundingBox.maxZ);
        GlStateManager.glEnd();

        GlStateManager.glBegin(GL11.GL_QUADS);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.minY, (float) boundingBox.minZ);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.minY, (float) boundingBox.minZ);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.minY, (float) boundingBox.maxZ);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.minY, (float) boundingBox.maxZ);
        GlStateManager.glEnd();

        GlStateManager.glBegin(GL11.GL_QUADS);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.minY, (float) boundingBox.minZ);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.minY, (float) boundingBox.minZ);
        GlStateManager.glVertex3f((float) boundingBox.minX, (float) boundingBox.minY, (float) boundingBox.maxZ);
        GlStateManager.glVertex3f((float) boundingBox.maxX, (float) boundingBox.minY, (float) boundingBox.maxZ);
        GlStateManager.glEnd();
    }

    public static void drawBox(BlockPos blockPos, double height, Colour color, int sides) {
        drawBox(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1, height, 1, color, color.getAlpha(), sides);
    }

    public static void drawBox(AxisAlignedBB bb, boolean check, double height, Colour color, int sides) {
        drawBox(bb, check, height, color, color.getAlpha(), sides);
    }

    public static void drawBox(AxisAlignedBB bb, boolean check, double height, Colour color, int alpha, int sides) {
        if (check) {
            drawBox(bb.minX, bb.minY, bb.minZ, bb.maxX - bb.minX, bb.maxY - bb.minY, bb.maxZ - bb.minZ, color, alpha, sides);
        } else {
            drawBox(bb.minX, bb.minY, bb.minZ, bb.maxX - bb.minX, height, bb.maxZ - bb.minZ, color, alpha, sides);
        }
    }

    public static void drawBox(double x, double y, double z, double w, double h, double d, Colour color, int alpha, int sides) {
        glPushMatrix();
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_LINE_SMOOTH);
        glDisable(GL_TEXTURE_2D);
        glEnable(GL_CULL_FACE);
        glDisable(GL_DEPTH_TEST);
        glDisable(GL11.GL_LIGHTING);

        GlStateManager.disableAlpha();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        color.glColor();
        bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        doVerticies(new AxisAlignedBB(x, y, z, x + w, y + h, z + d), color, alpha, bufferbuilder, sides, false);
        tessellator.draw();
        GlStateManager.enableAlpha();

        glEnable(GL11.GL_LIGHTING);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);
        glDisable(GL_LINE_SMOOTH);
        glPopMatrix();
    }

    public static void drawBoundingBox(BlockPos bp, double height, float width, Colour color) {
        drawBoundingBox(getBoundingBox(bp, 1, height, 1), width, color, color.getAlpha());
    }

    public static void drawBoundingBox(AxisAlignedBB bb, float width, Colour color) {
        drawBoundingBox(bb, width, color, color.getAlpha());
    }

    public static void drawBoundingBox(AxisAlignedBB bb, float width, float red, float green, float blue) {
        glPushMatrix();
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_LINE_SMOOTH);
        glLineWidth(width);
        glDisable(GL_TEXTURE_2D);
        glEnable(GL_CULL_FACE);
        glDisable(GL_DEPTH_TEST);
        glDisable(GL11.GL_LIGHTING);

        glTranslated(bb.minX, bb.minY, bb.minZ);
        glTranslated(bb.maxX, bb.maxY, bb.maxZ);

        glColor4f(red, green, blue, 0.7F);
        drawOutlinedBox();

        glColor4f(1, 1, 1, 1);

        glEnable(GL11.GL_LIGHTING);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);
        glDisable(GL_LINE_SMOOTH);
        glPopMatrix();
    }

    public static void drawBoundingBox(AxisAlignedBB bb, float width, Colour color, int alpha) {
        drawBoundingBox(bb, width, color.r, color.g, color.b, alpha);
    }

    public static void drawBoundingBoxWithSides(BlockPos blockPos, int width, Colour color, int sides) {
        drawBoundingBoxWithSides(getBoundingBox(blockPos, 1, 1, 1), width, color, color.getAlpha(), sides);
    }

    public static void drawBoundingBoxWithSides(BlockPos blockPos, int width, Colour color, int alpha, int sides) {
        drawBoundingBoxWithSides(getBoundingBox(blockPos, 1, 1, 1), width, color, alpha, sides);
    }

    public static void drawBoundingBoxWithSides(AxisAlignedBB axisAlignedBB, int width, Colour color, int alpha, int sides) {
        drawBoundingBoxWithSides(axisAlignedBB, width, color, color.getAlpha(), sides);
    }

    public static void drawBoundingBoxWithSides(AxisAlignedBB axisAlignedBB, Double width, Colour color, int alpha, int sides) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.glLineWidth(Float.valueOf(String.valueOf(width)));
        bufferbuilder.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION_COLOR);
        doVerticies(axisAlignedBB, color, alpha, bufferbuilder, sides, true);
        tessellator.draw();
    }

    public static void drawBoundingBox(AxisAlignedBB bb, double width, float red, float green, float blue, int alpha) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.glLineWidth((float) width);
        bufferbuilder.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION_COLOR);
        colorVertex(bb.minX, bb.minY, bb.minZ, red, green, blue, alpha, bufferbuilder);
        colorVertex(bb.minX, bb.minY, bb.maxZ, red, green, blue, alpha, bufferbuilder);
        colorVertex(bb.maxX, bb.minY, bb.maxZ, red, green, blue, alpha, bufferbuilder);
        colorVertex(bb.maxX, bb.minY, bb.minZ, red, green, blue, alpha, bufferbuilder);
        colorVertex(bb.minX, bb.minY, bb.minZ, red, green, blue, alpha, bufferbuilder);
        colorVertex(bb.minX, bb.maxY, bb.minZ, red, green, blue, alpha, bufferbuilder);
        colorVertex(bb.minX, bb.maxY, bb.maxZ, red, green, blue, alpha, bufferbuilder);
        colorVertex(bb.minX, bb.minY, bb.maxZ, red, green, blue, alpha, bufferbuilder);
        colorVertex(bb.maxX, bb.minY, bb.maxZ, red, green, blue, alpha, bufferbuilder);
        colorVertex(bb.maxX, bb.maxY, bb.maxZ, red, green, blue, alpha, bufferbuilder);
        colorVertex(bb.minX, bb.maxY, bb.maxZ, red, green, blue, alpha, bufferbuilder);
        colorVertex(bb.maxX, bb.maxY, bb.maxZ, red, green, blue, alpha, bufferbuilder);
        colorVertex(bb.maxX, bb.maxY, bb.minZ, red, green, blue, alpha, bufferbuilder);
        colorVertex(bb.maxX, bb.minY, bb.minZ, red, green, blue, alpha, bufferbuilder);
        colorVertex(bb.maxX, bb.maxY, bb.minZ, red, green, blue, alpha, bufferbuilder);
        colorVertex(bb.minX, bb.maxY, bb.minZ, red, green, blue, alpha, bufferbuilder);
        tessellator.draw();
    }

    public static void drawOutlinedBox() {
        drawOutlinedBox(DEFAULT_AABB);
    }

    public static void drawOutlinedBox(AxisAlignedBB bb) {
        glBegin(GL_LINES);
        {
            glVertex3d(bb.minX, bb.minY, bb.minZ);
            glVertex3d(bb.maxX, bb.minY, bb.minZ);

            glVertex3d(bb.maxX, bb.minY, bb.minZ);
            glVertex3d(bb.maxX, bb.minY, bb.maxZ);

            glVertex3d(bb.maxX, bb.minY, bb.maxZ);
            glVertex3d(bb.minX, bb.minY, bb.maxZ);

            glVertex3d(bb.minX, bb.minY, bb.maxZ);
            glVertex3d(bb.minX, bb.minY, bb.minZ);

            glVertex3d(bb.minX, bb.minY, bb.minZ);
            glVertex3d(bb.minX, bb.maxY, bb.minZ);

            glVertex3d(bb.maxX, bb.minY, bb.minZ);
            glVertex3d(bb.maxX, bb.maxY, bb.minZ);

            glVertex3d(bb.maxX, bb.minY, bb.maxZ);
            glVertex3d(bb.maxX, bb.maxY, bb.maxZ);

            glVertex3d(bb.minX, bb.minY, bb.maxZ);
            glVertex3d(bb.minX, bb.maxY, bb.maxZ);

            glVertex3d(bb.minX, bb.maxY, bb.minZ);
            glVertex3d(bb.maxX, bb.maxY, bb.minZ);

            glVertex3d(bb.maxX, bb.maxY, bb.minZ);
            glVertex3d(bb.maxX, bb.maxY, bb.maxZ);

            glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
            glVertex3d(bb.minX, bb.maxY, bb.maxZ);

            glVertex3d(bb.minX, bb.maxY, bb.maxZ);
            glVertex3d(bb.minX, bb.maxY, bb.minZ);
        }
        glEnd();
    }

    private static void colorVertex(double x, double y, double z, float red, float green, float blue, float alpha, BufferBuilder bufferbuilder) {
        bufferbuilder.pos(x - Util.mc.getRenderManager().viewerPosX, y - Util.mc.getRenderManager().viewerPosY, z - Util.mc.getRenderManager().viewerPosZ).color(red, green, blue, alpha).endVertex();
    }

    private static void colorVertex(double x, double y, double z, Colour color, int alpha, BufferBuilder bufferbuilder) {
        bufferbuilder.pos(x - Util.mc.getRenderManager().viewerPosX, y - Util.mc.getRenderManager().viewerPosY, z - Util.mc.getRenderManager().viewerPosZ).color(color.r, color.g, color.b, alpha).endVertex();
    }

    private static void doVerticies(AxisAlignedBB axisAlignedBB, Colour color, int alpha, BufferBuilder bufferbuilder, int sides, boolean five) {
        if ((sides & GeometryMasks.Quad.EAST) != 0) {
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color, color.getAlpha(), bufferbuilder);
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferbuilder);
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, color, alpha, bufferbuilder);
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color, alpha, bufferbuilder);
            if (five)
                colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color, color.getAlpha(), bufferbuilder);
        }
        if ((sides & GeometryMasks.Quad.WEST) != 0) {
            colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferbuilder);
            colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color, color.getAlpha(), bufferbuilder);
            colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color, alpha, bufferbuilder);
            colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, color, alpha, bufferbuilder);
            if (five)
                colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferbuilder);
        }
        if ((sides & GeometryMasks.Quad.NORTH) != 0) {
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferbuilder);
            colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferbuilder);
            colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, color, alpha, bufferbuilder);
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, color, alpha, bufferbuilder);
            if (five)
                colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferbuilder);
        }
        if ((sides & GeometryMasks.Quad.SOUTH) != 0) {
            colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color, color.getAlpha(), bufferbuilder);
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color, color.getAlpha(), bufferbuilder);
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color, alpha, bufferbuilder);
            colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color, alpha, bufferbuilder);
            if (five)
                colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color, color.getAlpha(), bufferbuilder);
        }
        if ((sides & GeometryMasks.Quad.UP) != 0) {
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, color, alpha, bufferbuilder);
            colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, color, alpha, bufferbuilder);
            colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color, alpha, bufferbuilder);
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color, alpha, bufferbuilder);
            if (five)
                colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, color, alpha, bufferbuilder);
        }
        if ((sides & GeometryMasks.Quad.DOWN) != 0) {
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferbuilder);
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color, color.getAlpha(), bufferbuilder);
            colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color, color.getAlpha(), bufferbuilder);
            colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferbuilder);
            if (five)
                colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferbuilder);
        }
    }

    public static AxisAlignedBB getBoundingBox(BlockPos bp, double width, double height, double depth) {
        double x = bp.getX();
        double y = bp.getY();
        double z = bp.getZ();
        return new AxisAlignedBB(x, y, z, x + width, y + height, z + depth);
    }

    public static void drawOutlinedEntityESP(double x, double y, double z, double width, double height, float red,
                                             float green, float blue, float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glLineWidth(1.5f);
        GL11.glColor4f(red, green, blue, alpha);
        drawOutlinedBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    public static void drawOutlinedBoundingBox(AxisAlignedBB aa) {
        Tessellator tessellator = Tessellator.getInstance();
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.05f);
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        tessellator.draw();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        tessellator.draw();
        bufferBuilder.begin(1, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        tessellator.draw();
    }

}
