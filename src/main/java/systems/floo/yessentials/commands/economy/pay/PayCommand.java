package systems.floo.yessentials.commands.economy.pay;

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

public class PayCommand extends Command {
    /**
     * Defines command information
     */
    public PayCommand() {
        super("pay",
                "Pays money to another player.",
                "",
                Arrays.asList(new String[]{"paymoney"}));
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
        if (!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        if (!EconomyProvider.isRegistered(uuid)){
            player.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "economynotregisteredself", player));
            return false;
        }

        if (args.length < 2){
            player.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "wronguse", player));
            return false;
        }

        if (!player.hasPermission("essentials.pay")){
            player.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "noperm", player));
            return false;
        }

        UUID targetUUID = UUIDFetcher.getUUID(args[0]);
        OfflinePlayer targetOfflinePlayer = Bukkit.getOfflinePlayer(targetUUID);

        if (!EconomyProvider.isRegistered(targetUUID)){
            player.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "economynotregisteredothers", player));
            return false;
        }

        String amountString = args[1];

        if(!NumberUtils.isNumber(amountString)){
            player.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "economynonumber", player));
            return false;
        }

        double amount = Double.parseDouble(amountString);

        if (!EconomyProvider.has(uuid, amount)){
            player.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "economynotenoughmoney", player, amount));
            return false;
        }

        EconomyProvider.removeCoins(uuid, amount);
        EconomyProvider.addCoins(targetUUID, amount);

        player.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "paysent", player.getName(), targetOfflinePlayer.getName(), amount));

        if (!targetOfflinePlayer.isOnline()){
            return true;
        }

        Player targetPlayer = targetOfflinePlayer.getPlayer();

        targetPlayer.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "payreceived", player.getName(), targetOfflinePlayer.getName(), amount));

        return true;
    }
}
