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
                sender.sendMessage(MessageProvider.getMessage("noperm", sender));
                return false;
            }

            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                sender.sendMessage(MessageProvider.getMessage("playernotfound", sender));
                return false;
            }

            if (VanishCommandProvider.isVanished(target)){
                VanishCommandProvider.unvanishPlayer(target);

                sender.sendMessage(MessageProvider.getMessage("unvanishothers", sender, target));
                target.sendMessage(MessageProvider.getMessage("unvanishotherstarget", sender, target));

                return true;
            }

            VanishCommandProvider.vanishPlayer(target);

            sender.sendMessage(MessageProvider.getMessage("vanishothers", sender, target));
            target.sendMessage(MessageProvider.getMessage("vanishotherstarget", sender, target));

            return true;
        }

        if (!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;

        if (VanishCommandProvider.isVanished(player)){
            VanishCommandProvider.unvanishPlayer(player);
            player.sendMessage(MessageProvider.getMessage("unvanishself", player));

            return true;
        }

        VanishCommandProvider.vanishPlayer(player);
        player.sendMessage(MessageProvider.getMessage("vanishself", player));

        return true;
    }
}
