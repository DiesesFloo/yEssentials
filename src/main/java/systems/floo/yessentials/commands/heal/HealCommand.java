package systems.floo.yessentials.commands.heal;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import systems.floo.yessentials.messages.MessageProvider;

import java.util.Arrays;
import java.util.List;

public class HealCommand extends Command {
    public HealCommand() {
        super("heal",
                "Heals a player",
                "",
                Arrays.asList(new String[]{"healplayer"}));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length >= 1){
            if (!sender.hasPermission("essentials.heal.others")){
                sender.sendMessage(MessageProvider.getMessage("noperm"));
                return false;
            }

            String targetName = args[0];

            if (Bukkit.getPlayer(targetName) == null){
                sender.sendMessage(MessageProvider.getMessage("playernotfound"));
                return false;
            }

            Player target = Bukkit.getPlayer(targetName);

            target.setHealth(20);

            sender.sendMessage(MessageProvider.getMessage("healothers")
                    .replaceAll("%player%", target.getDisplayName()));

            target.sendMessage(MessageProvider.getMessage("healself")
                    .replaceAll("%player%", target.getDisplayName()));
        }else {
            if (!(sender instanceof Player)){
                return false;
            }

            Player player = (Player) sender;

            if (!player.hasPermission("essentials.heal.self")){
                player.sendMessage(MessageProvider.getMessage("noperm"));
                return false;
            }

            player.setHealth(20);
            player.sendMessage(MessageProvider.getMessage("healself")
                    .replaceAll("%player%", player.getDisplayName()));
        }

        return true;
    }
}
