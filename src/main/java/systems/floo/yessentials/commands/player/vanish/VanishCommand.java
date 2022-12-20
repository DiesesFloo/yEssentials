package systems.floo.yessentials.commands.player.vanish;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import systems.floo.yessentials.messages.MessageProvider;

import java.util.Arrays;

public class VanishCommand extends Command {
    public VanishCommand() {
        super("vanish",
                "Sets a player in vanish mode",
                "",
                Arrays.asList(new String[]{"v", "hideplayer", "vanishplayer"}));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length >= 1){
            if (!sender.hasPermission("essentials.vanish.others")) {
                sender.sendMessage(MessageProvider.getMessage("noperm"));
                return false;
            }

            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                sender.sendMessage(MessageProvider.getMessage("playernotfound"));
                return false;
            }

            if (VanishCommandProvider.isVanished(target)){
                VanishCommandProvider.unvanishPlayer(target);

                sender.sendMessage(MessageProvider.getMessage("unvanishothers")
                        .replaceAll("%player%", target.getDisplayName()));
                target.sendMessage(MessageProvider.getMessage("unvanishself")
                        .replaceAll("%player%", target.getDisplayName()));

                return true;
            }

            VanishCommandProvider.vanishPlayer(target);

            sender.sendMessage(MessageProvider.getMessage("vanishothers")
                    .replaceAll("%player%", target.getDisplayName()));
            target.sendMessage(MessageProvider.getMessage("vanishself")
                    .replaceAll("%player%", target.getDisplayName()));

            return true;
        }

        if (!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;

        if (VanishCommandProvider.isVanished(player)){
            VanishCommandProvider.unvanishPlayer(player);
            player.sendMessage(MessageProvider.getMessage("unvanishself")
                    .replaceAll("%player%", player.getDisplayName()));

            return true;
        }

        VanishCommandProvider.vanishPlayer(player);
        player.sendMessage(MessageProvider.getMessage("vanishself")
                .replaceAll("%player%", player.getDisplayName()));

        return true;
    }
}
