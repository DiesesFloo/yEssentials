package systems.floo.yessentials.commands.economy.setmoney;

import org.apache.commons.lang.math.NumberUtils;
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

public class SetMoneyCommand extends Command {

    /**
     * Defines command information
     */
    public SetMoneyCommand() {
        super("setmoney",
                "Sets the money of a player to a specific number.",
                "",
                Arrays.asList(new String[]{"seteco", "seteconomy", "setcash"}));
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
        if (args.length < 1){
            sender.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "wronguse", sender));

            return false;
        }

        if (args.length >= 2){
            UUID targetUUID = UUIDFetcher.getUUID(args[0]);
            OfflinePlayer targetOfflinePlayer = Bukkit.getOfflinePlayer(targetUUID);

            if (!EconomyProvider.isRegistered(targetUUID)){
                sender.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "economynotregisteredothers", sender));
                return false;
            }

            String amountString = args[1];

            if(!NumberUtils.isNumber(amountString)){
                sender.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "economynonumber", sender));
                return false;
            }

            double amount = Double.parseDouble(amountString);

            EconomyProvider.setCoins(targetUUID, amount);

            sender.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "addmoneyothers", sender.getName(), targetOfflinePlayer.getName()));

            if (!targetOfflinePlayer.isOnline()){
                return true;
            }

            Player targetPlayer = targetOfflinePlayer.getPlayer();
            targetPlayer.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "addmoneyotherstarget", sender, targetPlayer));

            return true;
        }

        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        if (!EconomyProvider.isRegistered(uuid)){
            player.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "economynotregisteredself", player));
            return false;
        }

        String amountString = args[0];

        if(!NumberUtils.isNumber(amountString)){
            player.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "economynonumber", player));
            return false;
        }

        double amount = Double.parseDouble(amountString);

        EconomyProvider.setCoins(uuid, amount);

        player.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "setmoneyself", player));

        return true;
    }
}
