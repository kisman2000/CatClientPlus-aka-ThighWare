package me.dev.thighware.impl.modules.misc;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.dev.thighware.api.module.Category;
import me.dev.thighware.api.module.Module;
import me.dev.thighware.api.setting.Setting;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author v1_2.
 * @since 2/25/2022.
 */
public class Chat extends Module {
    public Setting<Boolean> timestamps = this.register("TimeStamps", this, false);
    public Chat() {
        super("Chat", Category.MISC, "changes up chat stuff");
    }
    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        SimpleDateFormat timeformat = new SimpleDateFormat("H:m");
        String time = timeformat.format(new Date());
        if (this.timestamps.getValue()) {
            event.setMessage(new TextComponentString(ChatFormatting.GRAY + "<" + ChatFormatting.DARK_PURPLE + time + ChatFormatting.GRAY + "> " + ChatFormatting.RESET).appendSibling(event.getMessage()));
        }
    }
}
