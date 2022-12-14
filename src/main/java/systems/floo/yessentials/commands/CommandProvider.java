package systems.floo.yessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import systems.floo.yessentials.EssentialsPlugin;

import java.lang.reflect.Field;
import java.util.Objects;

public class CommandProvider {

    private static final JavaPlugin PLUGIN = EssentialsPlugin.getPlugin();

    /**
     * Returns the Bukkit server command map
     *
     * @return Bukkit server command map
     */
    public static CommandMap getCommandMap() {
        try {
            final Field bukkitCmdMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCmdMap.setAccessible(true);

            return (CommandMap) bukkitCmdMap.get(Bukkit.getServer());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Registers a command to the Bukkit server command map
     *
     * @param command The command to add
     */
    public static void registerCommand(Command command) {
        Objects.requireNonNull(getCommandMap()).register(PLUGIN.getName(), command);
    }
}
