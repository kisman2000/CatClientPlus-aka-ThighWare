package me.dev.thighware.api.managers;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.dev.thighware.Thighware;
import me.dev.thighware.api.command.Command;
import me.dev.thighware.api.module.Module;
import me.dev.thighware.util.Timer;
import me.dev.thighware.util.Util;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class EventManager implements Util {

    private final Timer logoutTimer = new Timer();
    private Timer timer = new Timer();
    private final AtomicBoolean tickOngoing;
    private Module module;
    private ArrayList<Module> modules = new ArrayList<>();

    public EventManager() {
        this.tickOngoing = new AtomicBoolean(false);
    }

    public void init() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public boolean ticksOngoing() {
        return this.tickOngoing.get();
    }

    public void onUnload() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    public void onPacketReceived() {
        this.timer.reset();
    }

    @SubscribeEvent
    public void onUpdate(LivingEvent.LivingUpdateEvent event) {
        if (this == null && (event.getEntity().getEntityWorld()).isRemote && event.getEntityLiving().equals(mc.player)) {
            module.update();
        }
    }
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChatSent(ClientChatEvent event) {
        if (event.getMessage().startsWith(Command.getCommandPrefix())) {
            event.setCanceled(true);
            try {
                mc.ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
                if (event.getMessage().length() > 1) {
                    Thighware.commandManager.executeCommand(event.getMessage().substring(Command.getCommandPrefix().length() - 1));
                } else {
                    Command.sendMessage("Please enter a command.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Command.sendMessage(ChatFormatting.RED + "An error occurred while running this command. Check the log!");
            }
        }
    }
}