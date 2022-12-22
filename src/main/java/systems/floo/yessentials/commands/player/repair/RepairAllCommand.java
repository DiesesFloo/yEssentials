package systems.floo.yessentials.commands.player.repair;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import systems.floo.yessentials.messages.MessageProvider;

import java.util.Arrays;

public class RepairAllCommand extends Command {


    /**
     * Defines command information
     */
    public RepairAllCommand() {
        super("repairall",
                "Repairs the durability of all items in inventory",
                "",
                Arrays.asList(new String[]{"repairinventory"}));
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
            if (!sender.hasPermission("essentials.repairall.others")) {
                sender.sendMessage(MessageProvider.getMessage("noperm", sender));
                return false;
            }

            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                sender.sendMessage(MessageProvider.getMessage("playernotfound", sender));
                return false;
            }

            RepairCommandProvider.repairInventory(target);
            sender.sendMessage(MessageProvider.getMessage("repairallothers", sender, target));
            target.sendMessage(MessageProvider.getMessage("repairallotherstarget", sender, target));

            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("essentials.repairall.self")) {
            player.sendMessage(MessageProvider.getMessage("noperm", player));
            return false;
        }

        RepairCommandProvider.repairInventory(player);
        player.sendMessage(MessageProvider.getMessage("repairallself", player));

        return true;
    }
}
