package systems.floo.yessentials.commands.feed;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import systems.floo.yessentials.messages.MessageProvider;

import java.util.Arrays;

public class FeedCommand extends Command {
    public FeedCommand() {
        super("feed",
                "Feeds a player",
                "",
                Arrays.asList(new String[]{"feedplayer"}));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length >= 1){
            if (!sender.hasPermission("essentials.feed.others")){
                sender.sendMessage(MessageProvider.getMessage("noperm"));
                return false;
            }

            Player target = Bukkit.getPlayer(args[1]);

            if (target == null){
                sender.sendMessage(MessageProvider.getMessage("playernotfound"));
                return false;
            }

            target.setFoodLevel(20);

            sender.sendMessage(
                    MessageProvider.getMessage("feedothers")
                            .replaceAll("%player%", target.getDisplayName())
            );

            target.sendMessage(
                    MessageProvider.getMessage("feedself")
                            .replaceAll("%player%", target.getDisplayName())
            );

            return true;
        }

        if (!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("essentials.feed.self")){
            player.sendMessage(MessageProvider.getMessage("noperm"));
            return false;
        }

        player.setFoodLevel(20);

        player.sendMessage(
                MessageProvider.getMessage("feedothers")
                        .replaceAll("%player%", player.getDisplayName())
        );

        return true;
    }
}
