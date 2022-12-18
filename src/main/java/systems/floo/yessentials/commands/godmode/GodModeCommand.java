package systems.floo.yessentials.commands.godmode;

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
                sender.sendMessage(MessageProvider.getMessage("noperm"));
                return false;
            }

            String targetName = args[0];

            if (Bukkit.getPlayer(targetName) == null) {
                sender.sendMessage(MessageProvider.getMessage("playernotfound"));
                return false;
            }

            Player target = Bukkit.getPlayer(targetName);

            if (GodModeCommandProvider.isGodPlayer(target)) {
                GodModeCommandProvider.removeGodPlayer(target);
                target.sendMessage(
                        MessageProvider.getMessage("disabledgodmodeothers")
                                .replaceAll("%player%", target.getDisplayName())
                );

                return true;
            }

            GodModeCommandProvider.addGodPlayer(target);
            target.sendMessage(
                    MessageProvider.getMessage("enabledgodmodeothers")
                            .replaceAll("%player%", target.getDisplayName())
            );

            return true;

        }
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("essentials.godmode.self")) {
            player.sendMessage(MessageProvider.getMessage("noperm"));
            return false;
        }

        if (GodModeCommandProvider.isGodPlayer(player)) {
            GodModeCommandProvider.removeGodPlayer(player);
            player.sendMessage(
                    MessageProvider.getMessage("disabledgodmodeself")
                            .replaceAll("%player%", player.getDisplayName())
            );
        } else {
            GodModeCommandProvider.addGodPlayer(player);
            player.sendMessage(
                    MessageProvider.getMessage("enabledgodmodeself")
                            .replaceAll("%player%", player.getDisplayName())
            );
        }

        return true;
    }
}
