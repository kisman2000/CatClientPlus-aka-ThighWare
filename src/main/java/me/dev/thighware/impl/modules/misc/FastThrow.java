package me.dev.thighware.impl.modules.misc;

import me.dev.thighware.api.mixin.mixins.accessors.InnerMinecraft;
import me.dev.thighware.api.module.Category;
import me.dev.thighware.api.module.Module;
import me.dev.thighware.api.setting.Setting;
import me.dev.thighware.util.InventoryUtils;
import net.minecraft.item.ItemExpBottle;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * @author v1_2.
 * @since 2/25/2022.
 */
public class FastThrow extends Module {
    public Setting<Boolean> xp = this.register("XpBottle", this, false);
    public FastThrow() {
        super("FastThrow", Category.MISC, "does stuff faster");
    }
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (InventoryUtils.isHolding(ItemExpBottle.class) && xp.getValue()) {
            ((InnerMinecraft)mc).setRightClickDelayTimer(0);
        }
    }
}
