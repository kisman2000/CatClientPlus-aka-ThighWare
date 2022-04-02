package me.dev.thighware.api.event.events;

import me.dev.thighware.api.event.EventStage;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.math.Vec3d;

public class Render3DEvent extends EventStage {

    private final Tessellator tessellator;
    private final Vec3d renderPos;
    private final float partialTicks;

    public Render3DEvent(Tessellator tessellator, Vec3d renderPos, float ticks) {
        this.tessellator = tessellator;
        this.renderPos = renderPos;
        partialTicks = ticks;
    }

    public BufferBuilder getBuffer() {
        return tessellator.getBuffer();
    }

    public void setTranslation(Vec3d translation) {
        getBuffer().setTranslation(-translation.x, -translation.y, -translation.z);
    }

    public void resetTranslation() {
        setTranslation(renderPos);
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}

