package me.dev.thighware.api.mixin;

import me.dev.thighware.Thighware;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import javax.annotation.Nullable;
import java.util.Map;

    @IFMLLoadingPlugin.Name(value = Thighware.NAME)
    @IFMLLoadingPlugin.MCVersion(value = "1.12.2")
    @IFMLLoadingPlugin.SortingIndex(value = Integer.MAX_VALUE)
    public class MixinLoader implements IFMLLoadingPlugin {


        public MixinLoader() {
            MixinBootstrap.init();
            Mixins.addConfiguration("mixins.catclientplus.json");
            System.out.println("i like men - v1");
            MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        }

        @Override
        public String[] getASMTransformerClass() {
            return new String[0];
        }

        @Override
        public String getModContainerClass() {
            return null;
        }

        @Nullable
        @Override
        public String getSetupClass() {
            return null;
        }

        @Override
        public void injectData(Map<String, Object> data) {

        }

        @Override
        public String getAccessTransformerClass() {
            return null;
        }
    }
