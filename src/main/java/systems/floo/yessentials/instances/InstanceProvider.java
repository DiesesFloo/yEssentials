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

import java.util.ArrayList;

public class InstanceProvider {

    private static final JavaPlugin PLUGIN = EssentialsPlugin.getPlugin();
    private static ArrayList<Object> classes = new ArrayList<>();

    public static void registerAllInstances(){
        register(new FlyCommand());
        register(new FlyCommandJoinListener());

        register(new GodModeCommand());
        register(new GodModeCommandDamageListener());
    }

    public static void register(Object o){
        if (o instanceof Listener){
            Bukkit.getPluginManager().registerEvents((Listener) o, PLUGIN);
        }

        if (o instanceof Command){
            CommandProvider.registerCommand((Command) o);
        }

        classes.add(o);
    }

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

    public static Object getObject(String name){
        for (Object objects : classes){
            if (objects.getClass().getName().equals(name)){
                return objects;
            }
        }

        return null;
    }

    public static boolean classExists(String name){
        return getObject(name) != null;
    }

}
