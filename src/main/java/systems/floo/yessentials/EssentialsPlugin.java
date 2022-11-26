package systems.floo.yessentials;

import org.bukkit.plugin.java.JavaPlugin;

public final class EssentialsPlugin extends JavaPlugin {

    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
    }

    @Override
    public void onDisable() {

    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
