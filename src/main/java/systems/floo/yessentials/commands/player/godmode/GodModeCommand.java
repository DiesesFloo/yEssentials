package systems.floo.yessentials.commands.player.godmode;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import systems.floo.yessentials.messages.MessageProvider;

import java.util.Arrays;

public class GodModeCommand extends Command {

    /**
     * Defines command information
     */
    public GodModeCommand() {
        super("godmode",
                "Transforms a player into a god.",
                "",
                Arrays.asList(new String[]{"god"}));
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

            if (!sender.hasPermission("essentials.godmode.others")) {
                sender.sendMessage(MessageProvider.getMessage("noperm", sender));
                return false;
            }

            String targetName = args[0];

            Player target = Bukkit.getPlayer(targetName);

            if (target == null) {
                sender.sendMessage(MessageProvider.getMessage("playernotfound", sender));
                return false;
            }

            if (GodModeCommandProvider.isGodPlayer(target)) {
                GodModeCommandProvider.removeGodPlayer(target);

                sender.sendMessage(MessageProvider.getMessage("disabledgodmodeothers", sender, target));
                target.sendMessage(MessageProvider.getMessage("disabledgodmodeotherstarget", sender, target));

                return true;
            }

            GodModeCommandProvider.addGodPlayer(target);

            sender.sendMessage(MessageProvider.getMessage("enabledgodmodeothers", sender, target));
            target.sendMessage(MessageProvider.getMessage("enabledgodmodeotherstarget", sender, target));

            return true;

        }
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("essentials.godmode.self")) {
            player.sendMessage(MessageProvider.getMessage("noperm", player));
            return false;
        }

        if (GodModeCommandProvider.isGodPlayer(player)) {
            GodModeCommandProvider.removeGodPlayer(player);
            player.sendMessage(MessageProvider.getMessage("disabledgodmodeself", player));
        } else {
            GodModeCommandProvider.addGodPlayer(player);
            player.sendMessage(MessageProvider.getMessage("enabledgodmodeself", player));
        }

        return true;
    }
}
