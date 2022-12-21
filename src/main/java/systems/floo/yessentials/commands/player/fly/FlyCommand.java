package systems.floo.yessentials.commands.player.fly;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import systems.floo.yessentials.messages.MessageProvider;

import java.util.Arrays;

public class FlyCommand extends Command {
    /**
     * Defines command information
     */
    public FlyCommand() {
        super("fly",
                "Sets a player into fly mode.",
                "",
                Arrays.asList(new String[]{"flymode"}));
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
            if (!sender.hasPermission("essentials.fly.others")) {
                sender.sendMessage(MessageProvider.getMessage("noperm", sender));
                return false;
            }

            String targetName = args[0];

            Player target = Bukkit.getPlayer(targetName);

            if (target == null) {
                sender.sendMessage(MessageProvider.getMessage("playernotfound", sender));
                return false;
            }

            if (FlyCommandProvider.isFlyer(target)) {
                FlyCommandProvider.removeFlyer(target);
                sender.sendMessage(MessageProvider.getMessage("disabledflyothers", sender, target));
                target.sendMessage(MessageProvider.getMessage("disabledflyotherstarget", sender, target));
            } else {
                FlyCommandProvider.addFlyer(target);
                sender.sendMessage(MessageProvider.getMessage("enabledflyothers"));
                target.sendMessage(MessageProvider.getMessage("enabledflyotherstarget"));
            }

            return true;

        }

        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("essentials.fly.self")) {
            player.sendMessage(MessageProvider.getMessage("noperm", player));
            return false;
        }

        if (FlyCommandProvider.isFlyer(player)) {
            FlyCommandProvider.removeFlyer(player);
            player.sendMessage(MessageProvider.getMessage("disabledflyself", player));
        } else {
            FlyCommandProvider.addFlyer(player);
            player.sendMessage(MessageProvider.getMessage("enabledflyself", player));
        }


        return true;
    }
}
