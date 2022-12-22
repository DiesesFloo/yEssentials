package systems.floo.yessentials.commands.player.heal;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import systems.floo.yessentials.messages.MessageProvider;

import java.util.Arrays;

public class HealCommand extends Command {

    /**
     * Defines command information
     */
    public HealCommand() {
        super("heal",
                "Heals a player",
                "",
                Arrays.asList(new String[]{"healplayer"}));
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
        if (args.length >= 1) {
            if (!sender.hasPermission("essentials.heal.others")) {
                sender.sendMessage(MessageProvider.getMessage("noperm", sender));
                return false;
            }

            String targetName = args[0];

            Player target = Bukkit.getPlayer(targetName);

            if (target == null) {
                sender.sendMessage(MessageProvider.getMessage("playernotfound", sender));
                return false;
            }

            target.setHealth(20);

            sender.sendMessage(MessageProvider.getMessage("healothers", sender, target));
            target.sendMessage(MessageProvider.getMessage("healotherstarget", sender, target));

            return true;
        }
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("essentials.heal.self")) {
            player.sendMessage(MessageProvider.getMessage("noperm", player));
            return false;
        }

        player.setHealth(20);
        player.sendMessage(MessageProvider.getMessage("healself", player));

        return true;
    }
}
