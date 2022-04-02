package me.dev.thighware.api.mixin.mixins.accessors;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author v1_2.
 * @since 3/1/2022.
 */
@Mixin(value = Minecraft.class)
public interface InnerMinecraft {

    @Accessor("rightClickDelayTimer")
    void setRightClickDelayTimer(int rightClickDelayTimer);
}
