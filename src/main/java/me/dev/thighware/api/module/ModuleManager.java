package me.dev.thighware.api.module;

import me.dev.thighware.impl.modules.client.GUI;
import me.dev.thighware.impl.modules.client.HUD;
import me.dev.thighware.impl.modules.combat.Criticals;
import me.dev.thighware.impl.modules.combat.KillAura;
import me.dev.thighware.impl.modules.misc.AutoRespawn;
import me.dev.thighware.impl.modules.misc.Chat;
import me.dev.thighware.impl.modules.misc.FastThrow;
import me.dev.thighware.impl.modules.misc.Spammer;
import me.dev.thighware.impl.modules.movement.AutoWalk;
import me.dev.thighware.impl.modules.movement.ReverseStep;
import me.dev.thighware.impl.modules.movement.Sprint;
import me.dev.thighware.impl.modules.movement.Step;
import me.dev.thighware.impl.modules.render.CustomFOV;
import me.dev.thighware.impl.modules.render.FakePlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ModuleManager {

    public ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager() {
        MinecraftForge.EVENT_BUS.register(this);
        this.init();
    }

    public void init() {
        modules.addAll(Arrays.asList(
                // CLIENT
                new HUD(),
                new GUI(),
                // CHAT
                // COMBAT
                // new Surround(), needs fixed
                new KillAura(),
                new Criticals(),
                // new Velocity(), needs fixed
                // EXPLOIT
                // MISC
                new AutoRespawn(),
                new Chat(),
                new FastThrow(),
                new Spammer(),
                // MOVEMENT
                new AutoWalk(),
                new ReverseStep(),
                new Step(),
                new Sprint(),
                // PLAYER
                // RENDER
                new FakePlayer(),
                new CustomFOV()
        ));
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }

    public Module getModule(String name) {
        for (Module module : modules) {
            if (module.getName().equals(name)) {
                return module;
            }
        }
        return null;
    }

    public ArrayList<Module> getModulesInCategory(Category c) {
        return (ArrayList<Module>) modules.stream().filter(h -> h.getCategory().equals(c)).collect(Collectors.toList());
    }

    @SubscribeEvent
    public void onKey(@Nonnull final InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState())
            for (Module m : modules)
                if (m.getKey() == Keyboard.getEventKey())
                    m.toggle();
    }
}
