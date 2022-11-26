package systems.floo.yessentials;

import org.bukkit.plugin.java.JavaPlugin;
import systems.floo.yessentials.instances.InstanceProvider;

public final class EssentialsPlugin extends JavaPlugin {

    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        InstanceProvider.registerAllInstances();
    }

    @Override
    public void onDisable() {

    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
