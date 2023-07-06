package systems.floo.yessentials;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import systems.floo.yessentials.instances.InstanceProvider;

public final class EssentialsPlugin extends JavaPlugin {

    @Getter
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
}
