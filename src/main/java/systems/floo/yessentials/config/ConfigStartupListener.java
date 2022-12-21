package systems.floo.yessentials.config;

import org.bukkit.plugin.java.JavaPlugin;
import systems.floo.yessentials.EssentialsPlugin;
import systems.floo.yessentials.instances.interfaces.IStartupHandler;

public class ConfigStartupListener implements IStartupHandler {

    private static final JavaPlugin PLUGIN = EssentialsPlugin.getPlugin();

    /**
     * Method executed on plugin enable
     */
    @Override
    public void handleStart() {
        ConfigProvider.getCustomConfig("strings.yml");
        ConfigProvider.getCustomConfig("economy.yml");
    }

}
