package systems.floo.yessentials.instances;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import systems.floo.yessentials.EssentialsPlugin;
import systems.floo.yessentials.commands.CommandProvider;
import systems.floo.yessentials.commands.fly.FlyCommand;
import systems.floo.yessentials.commands.fly.FlyCommandJoinListener;
import systems.floo.yessentials.commands.godmode.GodModeCommand;
import systems.floo.yessentials.commands.godmode.GodModeCommandDamageListener;
import systems.floo.yessentials.commands.heal.HealCommand;
import systems.floo.yessentials.instances.interfaces.IStartupHandler;

import java.util.ArrayList;

public class InstanceProvider {

    private static final JavaPlugin PLUGIN = EssentialsPlugin.getPlugin();
    private static ArrayList<Object> classes = new ArrayList<>();

    /**
     * Registers all commands, events and StartupListener
     */
    public static void registerAllInstances(){
        register(new FlyCommand());
        register(new FlyCommandJoinListener());

        register(new GodModeCommand());
        register(new GodModeCommandDamageListener());

        register(new HealCommand());
    }

    /**
     * Registers an object in the array and
     * registers it in the command map, plugin
     * manager and executes the IStartupHandler
     * @param o The object to register
     */
    public static void register(Object o){
        if (o instanceof Listener){
            Bukkit.getPluginManager().registerEvents((Listener) o, PLUGIN);
        }

        if (o instanceof Command){
            CommandProvider.registerCommand((Command) o);
        }

        if (o instanceof IStartupHandler){
            ((IStartupHandler) o).handleStart();
        }

        classes.add(o);
    }

    /**
     * Returns a class by its type
     * @param clazz The type of class to get
     * @return The class
     */
    public static  <T> T getClass(Class<T> clazz){
        if (!classExists(clazz.getName())){
            return null;
        }

        try {
            return clazz.cast(getObject(clazz.getName()));
        } catch (ClassCastException e){
            return null;
        }

    }

    /**
     * Returns an object from the object
     * array by its name
     * @param name The name of the object to get
     * @return The object from the array
     */
    public static Object getObject(String name){
        for (Object objects : classes){
            if (objects.getClass().getName().equals(name)){
                return objects;
            }
        }

        return null;
    }

    /**
     * Checks if class is registered in object array
     * @param name The name of the object
     * @return Value if the class exists
     */
    public static boolean classExists(String name){
        return getObject(name) != null;
    }

}
