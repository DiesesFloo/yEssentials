package systems.floo.yessentials.commands.player.feed;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import systems.floo.yessentials.messages.MessageProvider;

import java.util.Arrays;

public class FeedCommand extends Command {

    /**
     * Defines command information
     */
    public FeedCommand() {
        super("feed",
                "Feeds a player",
                "",
                Arrays.asList(new String[]{"feedplayer"}));
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
        if (args.length >= 1){
            if (!sender.hasPermission("essentials.feed.others")){
                sender.sendMessage(MessageProvider.getMessage("noperm", sender));
                return false;
            }

            Player target = Bukkit.getPlayer(args[0]);

            if (target == null){
                sender.sendMessage(MessageProvider.getMessage("playernotfound", sender));
                return false;
            }

            target.setFoodLevel(20);

            sender.sendMessage(MessageProvider.getMessage("feedothers", sender, target));
            target.sendMessage(MessageProvider.getMessage("feedotherstarget", sender, target));

            return true;
        }

        if (!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("essentials.feed.self")){
            player.sendMessage(MessageProvider.getMessage("noperm", player));
            return false;
        }

        player.setFoodLevel(20);

        player.sendMessage(
                MessageProvider.getMessage("feedself", player));

        return true;
    }
}
