package systems.floo.yessentials.commands.fly;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import systems.floo.yessentials.messages.MessageProvider;

import java.util.Arrays;
import java.util.List;

public class FlyCommand extends Command {
    public FlyCommand() {
        super("fly",
                "Sets a player into fly mode.",
                "",
                Arrays.asList(new String[]{"flymode"}));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if (!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;

        if (args.length >= 1){
            if (!player.hasPermission("essentials.fly.others")){
                player.sendMessage(MessageProvider.getMessage("noperm"));
                return false;
            }

            String playerName = args[0];

            if (Bukkit.getPlayer(playerName) == null){
                player.sendMessage(MessageProvider.getMessage("playernotfound"));
                return false;
            }

            Player target = Bukkit.getPlayer(playerName);

            if (FlyCommandProvider.isFlyer(target)) {
                FlyCommandProvider.removeFlyer(target);
                player.sendMessage(
                        MessageProvider.getMessage("disabledflyothers")
                                .replaceAll("%player%", target.getDisplayName())
                );
            }
            else {
                FlyCommandProvider.addFlyer(target);
                player.sendMessage(
                        MessageProvider.getMessage("enabledflyothers")
                                .replaceAll("%player%", target.getDisplayName())
                );
            }

        }else {

            if (!player.hasPermission("essentials.fly.self")){
                player.sendMessage(MessageProvider.getMessage("noperm"));
                return false;
            }

            if (FlyCommandProvider.isFlyer(player)) {
                FlyCommandProvider.removeFlyer(player);
                player.sendMessage(
                        MessageProvider.getMessage("enabledflyself")
                                .replaceAll("%player%", player.getDisplayName())
                );
            }
            else {
                FlyCommandProvider.addFlyer(player);
                player.sendMessage(
                        MessageProvider.getMessage("enabledflyself")
                                .replaceAll("%player%", player.getDisplayName())
                );
            }

        }

        return false;
    }
}
