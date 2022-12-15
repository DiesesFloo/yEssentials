package systems.floo.yessentials.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import systems.floo.yessentials.EssentialsPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigProvider {

    private static final JavaPlugin PLUGIN = EssentialsPlugin.getPlugin();

    /**
     * Returns an YamlConfiguration object by its path
     *
     * @param path The path of the config
     * @return The config to get
     */
    public static YamlConfiguration getCustomConfig(String path) {
        File configFile = new File(PLUGIN.getDataFolder(), path);

        if (!configFile.exists()) {
            PLUGIN.saveResource(path, false);
        }

        YamlConfiguration config = new YamlConfiguration();

        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        return config;
    }

}
