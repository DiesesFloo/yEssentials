package systems.floo.yessentials.commands.broadcast;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import systems.floo.yessentials.messages.MessageProvider;

import java.util.Arrays;

public class BroadcastCommand extends Command {

    /**
     * Defines command information
     */
    public BroadcastCommand() {
        super("broadcast",
                "Broadcasts a message to all player.",
                "",
                Arrays.asList(new String[]{"broadcastmessage", "announce"}));
    }

    /**
     * Method executed on command execute
     *
     * @param sender       Source object which is executing this command
     * @param commandLabel The alias of the command used
     * @param args         All arguments passed to the command, split via ' '
     * @return Value if the command is successful
     */
    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission("essentials.broadcast")){
            sender.sendMessage(MessageProvider.getMessage("noperm"));
            return false;
        }

        if (args.length < 1){
            sender.sendMessage(MessageProvider.getMessage("broadcasterror"));
            return false;
        }

        String broadcastMessage = "";

        for (int i = 0; i < args.length; i++){
            broadcastMessage += (args[i] + " ");
        }

        Bukkit.broadcastMessage(MessageProvider.getPrefix("broadcast") + broadcastMessage);
        sender.sendMessage(MessageProvider.getMessage("broadcastsent"));

        return true;
    }
}
