package me.dev.thighware;

import me.dev.thighware.api.click.gui.ClickGuiMainScreen;
import me.dev.thighware.api.command.CommandManager;
import me.dev.thighware.api.friend.FriendManager;
import me.dev.thighware.api.managers.EventManager;
import me.dev.thighware.api.managers.TextManager;
import me.dev.thighware.api.module.ModuleManager;
import me.dev.thighware.api.setting.SettingManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(modid = Thighware.MODID, name = Thighware.NAME, version = Thighware.VERSION)
public class Thighware {
    public static EventBus EVENTBUS = new EventBus();
    public static final String MODID = "thighware";
    public static final String NAME = "CatClient+";
    public static final String VERSION = "b1";
    public static CommandManager commandManager;
    public static SettingManager settingManager;
    public static ModuleManager moduleManager;
    public static FriendManager friendManager;
    public static EventManager eventManager;
    public static TextManager textManager;
    public static ClickGuiMainScreen gui;
    private static Logger logger;
    @Mod.Instance
    public static Thighware INSTANCE;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        MinecraftForge.EVENT_BUS.register(new EventManager());
        EVENTBUS.register(this);
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        commandManager = new CommandManager();
        settingManager = new SettingManager();
        friendManager = new FriendManager();
        moduleManager = new ModuleManager();
        eventManager = new EventManager();
        textManager = new TextManager();
        gui = new ClickGuiMainScreen();
        Display.setTitle("CatClient+");
        MinecraftForge.EVENT_BUS.register(this);
    }
}
