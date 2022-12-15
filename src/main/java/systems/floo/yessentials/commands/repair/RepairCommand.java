package systems.floo.yessentials.commands.repair;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import systems.floo.yessentials.messages.MessageProvider;

import java.util.Arrays;

public class RepairCommand extends Command {

    /**
     * Defines command information
     */
    public RepairCommand() {
        super("repair",
                "Repairs the durability of an item",
                "",
                Arrays.asList(new String[]{}));
    }

    /**
     * Method executed on command execute
     * @param sender Source object which is executing this command
     * @param commandLabel The alias of the command used
     * @param args All arguments passed to the command, split via ' '
     * @return Value if the command is successful
     */
    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if (args.length >= 1){
            if (args[0].equalsIgnoreCase("all")){
                if (args.length >= 2){
                    if (!sender.hasPermission("essentials.repairall.others")){
                        sender.sendMessage(MessageProvider.getMessage("noperm"));
                        return false;
                    }

                    Player target = Bukkit.getPlayer(args[1]);

                    if (target == null){
                        sender.sendMessage(MessageProvider.getMessage("playernotfound"));
                        return false;
                    }

                    RepairCommandProvider.repairInventory(target);
                    sender.sendMessage(
                            MessageProvider.getMessage("repairallothers")
                                    .replaceAll("%player%", target.getDisplayName())
                    );

                    target.sendMessage(
                            MessageProvider.getMessage("repairallself")
                                    .replaceAll("%player%", target.getDisplayName())
                    );

                    return true;
                }

                if (!(sender instanceof Player)){
                    return false;
                }

                Player player = (Player) sender;

                if (!player.hasPermission("essentials.repairall.self")){
                    player.sendMessage(MessageProvider.getMessage("noperm"));
                    return false;
                }

                RepairCommandProvider.repairInventory(player);
                player.sendMessage(MessageProvider.getMessage("repairallself")
                        .replaceAll("%player%", player.getDisplayName()));

                return true;

            }

            if (!sender.hasPermission("essentials.repair.others")){
                sender.sendMessage(MessageProvider.getMessage("noperm"));
                return false;
            }

            Player target = Bukkit.getPlayer(args[0]);

            if (target == null){
                sender.sendMessage(MessageProvider.getMessage("playernotfound"));
                return false;
            }

            RepairCommandProvider.repairItemInHand(target);

            sender.sendMessage(
                    MessageProvider.getMessage("repairothers")
                            .replaceAll("%player%", target.getDisplayName())
            );

            target.sendMessage(
                    MessageProvider.getMessage("repairself")
                            .replaceAll("%player%", target.getDisplayName())
            );

        }

        if (!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("essentials.repair.self")){
            player.sendMessage(MessageProvider.getMessage("noperm"));
            return false;
        }

        RepairCommandProvider.repairItemInHand(player);

        player.sendMessage(
                MessageProvider.getMessage("repairself")
                        .replaceAll("%player%", player.getDisplayName())
        );

        return true;
    }
}
