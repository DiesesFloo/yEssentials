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
                sender.sendMessage(MessageProvider.getMessage("noperm"));
                return false;
            }

            String targetName = args[0];

            Player target = Bukkit.getPlayer(targetName);

            if (target == null) {
                sender.sendMessage(MessageProvider.getMessage("playernotfound"));
                return false;
            }

            if (FlyCommandProvider.isFlyer(target)) {
                FlyCommandProvider.removeFlyer(target);
                sender.sendMessage(
                        MessageProvider.getMessage("disabledflyothers")
                                .replaceAll("%player%", target.getDisplayName())
                );

                target.sendMessage(
                        MessageProvider.getMessage("disabledflyself")
                                .replaceAll("%player%", target.getDisplayName())
                );
            } else {

                FlyCommandProvider.addFlyer(target);
                sender.sendMessage(
                        MessageProvider.getMessage("enabledflyothers")
                                .replaceAll("%player%", target.getDisplayName())
                );
                target.sendMessage(
                        MessageProvider.getMessage("enabledflyself")
                                .replaceAll("%player%", target.getDisplayName())
                );
            }

            return true;

        }

        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("essentials.fly.self")) {
            player.sendMessage(MessageProvider.getMessage("noperm"));
            return false;
        }

        if (FlyCommandProvider.isFlyer(player)) {
            FlyCommandProvider.removeFlyer(player);
            player.sendMessage(
                    MessageProvider.getMessage("disabledflyself")
                            .replaceAll("%player%", player.getDisplayName())
            );
        } else {
            FlyCommandProvider.addFlyer(player);
            player.sendMessage(
                    MessageProvider.getMessage("enabledflyself")
                            .replaceAll("%player%", player.getDisplayName())
            );
        }


        return true;
    }
}
