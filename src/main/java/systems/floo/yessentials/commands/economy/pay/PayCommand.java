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
    public PayCommand() {
        super("pay",
                "Pays money to another player.",
                "",
                Arrays.asList(new String[]{"paymoney"}));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        if (!EconomyProvider.isRegistered(uuid)){
            player.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "economynotregisteredself"));
            return false;
        }

        if (args.length < 2){
            player.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "wronguse"));
            return false;
        }

        if (!player.hasPermission("essentials.pay")){
            player.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "noperm"));
            return false;
        }

        UUID targetUUID = UUIDFetcher.getUUID(args[0]);
        OfflinePlayer targetOfflinePlayer = Bukkit.getOfflinePlayer(targetUUID);

        if (!EconomyProvider.isRegistered(targetUUID)){
            player.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "economynotregisteredothers")
                    .replaceAll("%player%", targetOfflinePlayer.getName()));
            return false;
        }

        String amountString = args[1];

        if(!NumberUtils.isNumber(amountString)){
            player.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "economynonumber"));
            return false;
        }

        double amount = Double.parseDouble(amountString);

        if (!EconomyProvider.has(uuid, amount)){
            player.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "economynotenoughmoney"));
            return false;
        }

        EconomyProvider.removeCoins(uuid, amount);
        EconomyProvider.addCoins(targetUUID, amount);

        player.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "paysent")
                .replaceAll("%player1%", player.getDisplayName())
                .replaceAll("%player2%", targetOfflinePlayer.getName()));

        if (!targetOfflinePlayer.isOnline()){
            return true;
        }

        Player targetPlayer = targetOfflinePlayer.getPlayer();

        targetPlayer.sendMessage(MessageProvider.getMessage(PrefixType.ECONOMY, "payreceived")
                .replaceAll("%player1%", player.getDisplayName())
                .replaceAll("%player2%", targetOfflinePlayer.getName()));

        return true;
    }
}
