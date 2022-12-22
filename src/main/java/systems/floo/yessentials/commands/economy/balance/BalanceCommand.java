package systems.floo.yessentials.commands.economy.balance;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import systems.floo.yessentials.economy.EconomyProvider;
import systems.floo.yessentials.messages.MessageProvider;
import systems.floo.yessentials.messages.PrefixType;
import systems.floo.yessentials.mojang.UUIDFetcher;

import java.util.Arrays;
import java.util.UUID;

public class BalanceCommand extends Command {

    /**
     * Defines command information
     */
    public BalanceCommand() {
        super("balance",
                "Shows the balance of a player/yourself.",
                "",
                Arrays.asList(new String[]{"bal"}));
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
            if (!sender.hasPermission("essentials.balance.others")) {
                sender.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "noperm", sender));
                return false;
            }

            UUID targetUUID = UUIDFetcher.getUUID(args[0]);

            if (!EconomyProvider.isRegistered(targetUUID)) {
                sender.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "economynotregisteredothers", sender));
                return false;
            }

            OfflinePlayer targetOfflinePlayer = Bukkit.getOfflinePlayer(targetUUID);

            sender.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY,
                    "balanceothers",
                    sender.getName(),
                    targetOfflinePlayer.getName(),
                    EconomyProvider.getCoins(targetUUID)));

            return true;
        }

        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        player.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY,
                "balanceself",
                player,
                EconomyProvider.getCoins(uuid)));

        return true;
    }
}
