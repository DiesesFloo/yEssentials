package systems.floo.yessentials.instances;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import systems.floo.yessentials.EssentialsPlugin;
import systems.floo.yessentials.commands.CommandProvider;
import systems.floo.yessentials.commands.economy.balance.BalanceCommand;
import systems.floo.yessentials.commands.economy.pay.PayCommand;
import systems.floo.yessentials.commands.player.vanish.VanishCommand;
import systems.floo.yessentials.commands.player.vanish.VanishCommandJoinListener;
import systems.floo.yessentials.commands.server.broadcast.BroadcastCommand;
import systems.floo.yessentials.commands.player.feed.FeedCommand;
import systems.floo.yessentials.commands.player.fly.FlyCommand;
import systems.floo.yessentials.commands.player.fly.FlyCommandJoinListener;
import systems.floo.yessentials.commands.player.godmode.GodModeCommand;
import systems.floo.yessentials.commands.player.godmode.GodModeCommandDamageListener;
import systems.floo.yessentials.commands.player.godmode.GodModeCommandFoodListener;
import systems.floo.yessentials.commands.player.heal.HealCommand;
import systems.floo.yessentials.commands.player.repair.RepairAllCommand;
import systems.floo.yessentials.commands.player.repair.RepairCommand;
import systems.floo.yessentials.commands.vanilla.tell.TellCommand;
import systems.floo.yessentials.commands.vanilla.tell.TellCommandQuitListener;
import systems.floo.yessentials.config.ConfigStartupListener;
import systems.floo.yessentials.economy.EconomyJoinListener;
import systems.floo.yessentials.instances.interfaces.IStartupHandler;

import java.util.ArrayList;

public class InstanceProvider {

    private static final JavaPlugin PLUGIN = EssentialsPlugin.getPlugin();
    private static ArrayList<Object> classes = new ArrayList<>();

    /**
     * Registers all commands, events and StartupListener
     */
    public static void registerAllInstances() {
        register(new FlyCommand());
        register(new FlyCommandJoinListener());
        register(new GodModeCommand());
        register(new GodModeCommandDamageListener());
        register(new GodModeCommandFoodListener());
        register(new HealCommand());
        register(new RepairAllCommand());
        register(new RepairCommand());
        register(new FeedCommand());
        register(new BroadcastCommand());
        register(new PayCommand());
        register(new EconomyJoinListener());
        register(new ConfigStartupListener());
        register(new BalanceCommand());
        register(new VanishCommand());
        register(new VanishCommandJoinListener());
        register(new TellCommand());
        register(new TellCommandQuitListener());

    }

    /**
     * Registers an object in the array and* registers it in the {@link org.bukkit.command.CommandMap},
     * {@link org.bukkit.plugin.PluginManager}
     * and executes the {@link IStartupHandler}
     *
     * @param o The object to register
     */
    public static void register(Object o) {
        if (o instanceof Listener) {
            Bukkit.getPluginManager().registerEvents((Listener) o, PLUGIN);
        }

        if (o instanceof Command) {
            CommandProvider.registerCommand((Command) o);
        }

        if (o instanceof IStartupHandler) {
            ((IStartupHandler) o).handleStart();
        }

        classes.add(o);
    }

    /**
     * Returns a class by its type
     *
     * @param clazz The type of class to get
     * @return The class
     */
    public static <T> T getClass(Class<T> clazz) {
        if (!classExists(clazz.getName())) {
            return null;
        }

        try {
            return clazz.cast(getObject(clazz.getName()));
        } catch (ClassCastException e) {
            return null;
        }

    }

    /**
     * Returns an object from the object
     * array by its name
     *
     * @param name The name of the object to get
     * @return The object from the array
     */
    public static Object getObject(String name) {
        for (Object objects : classes) {
            if (objects.getClass().getName().equals(name)) {
                return objects;
            }
        }

        return null;
    }

    /**
     * Checks if class is registered in object array
     *
     * @param name The name of the object
     * @return Value if the class exists
     */
    public static boolean classExists(String name) {
        return getObject(name) != null;
    }

}
