package systems.floo.yessentials;

import org.bukkit.plugin.java.JavaPlugin;
import systems.floo.yessentials.instances.InstanceProvider;

public final class EssentialsPlugin extends JavaPlugin {

    private static JavaPlugin plugin;

    /**
     * Method executed on plugin startup:
     * Sets the plugin var and executes
     * the register method in {@link InstanceProvider}
     */
    @Override
    public void onEnable() {
        plugin = this;

        InstanceProvider.registerAllInstances();
    }

    /**
     * Returns the main class in form of {@link JavaPlugin} object.
     * @return
     */
    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
