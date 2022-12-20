package systems.floo.yessentials.economy.vault;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;
import systems.floo.yessentials.EssentialsPlugin;
import systems.floo.yessentials.economy.EconomyProvider;
import systems.floo.yessentials.mojang.UUIDFetcher;

import java.util.List;
import java.util.UUID;

public class VaultEconomyProvider implements Economy {
    @Override
    public boolean isEnabled() {
        return EssentialsPlugin.getPlugin().isEnabled();
    }

    @Override
    public String getName() {
        return "yEssentials Economy";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double amount) {
        return null;
    }

    @Override
    public String currencyNamePlural() {
        return currencyNameSingular();
    }

    @Override
    public String currencyNameSingular() {
        return "$";
    }

    @Override
    public boolean hasAccount(String playerName) {
        UUID uuid = UUIDFetcher.getUUID(playerName);
        if (uuid == null) return false;
        return EconomyProvider.isRegistered(uuid);
    }

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        return EconomyProvider.isRegistered(player.getUniqueId());
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        UUID uuid = UUIDFetcher.getUUID(playerName);
        if (uuid == null) return false;
        return EconomyProvider.isRegistered(uuid);
    }

    @Override
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        return EconomyProvider.isRegistered(player.getUniqueId());
    }

    @Override
    public double getBalance(String playerName) {
        UUID uuid = UUIDFetcher.getUUID(playerName);
        if (uuid == null) return 0;
        return EconomyProvider.getCoins(uuid);
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        return EconomyProvider.getCoins(player.getUniqueId());
    }

    @Override
    public double getBalance(String playerName, String world) {
        UUID uuid = UUIDFetcher.getUUID(playerName);
        if (uuid == null) return 0;
        return EconomyProvider.getCoins(uuid);
    }

    @Override
    public double getBalance(OfflinePlayer player, String world) {
        return EconomyProvider.getCoins(player.getUniqueId());
    }

    @Override
    public boolean has(String playerName, double amount) {
        UUID uuid = UUIDFetcher.getUUID(playerName);
        if (uuid == null) return false;
        return EconomyProvider.has(uuid, amount);
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        return EconomyProvider.has(player.getUniqueId(), amount);
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        UUID uuid = UUIDFetcher.getUUID(playerName);
        if (uuid == null) return false;
        return EconomyProvider.has(uuid, amount);
    }

    @Override
    public boolean has(OfflinePlayer player, String worldName, double amount) {
        return EconomyProvider.has(player.getUniqueId(), amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        UUID uuid = UUIDFetcher.getUUID(playerName);

        if (uuid == null) return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Player doesn't exist.");
        if (!EconomyProvider.isRegistered(uuid)) return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Player doesn't exist.");

        EconomyProvider.removeCoins(uuid, amount);

        return new EconomyResponse(amount, getBalance(playerName), EconomyResponse.ResponseType.SUCCESS,null);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        UUID uuid = player.getUniqueId();

        if (!EconomyProvider.isRegistered(uuid)) return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Player doesn't exist.");

        EconomyProvider.removeCoins(uuid, amount);

        return new EconomyResponse(amount, getBalance(player), EconomyResponse.ResponseType.SUCCESS,null);
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        UUID uuid = UUIDFetcher.getUUID(playerName);

        if (uuid == null) return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Player doesn't exist.");
        if (!EconomyProvider.isRegistered(uuid)) return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Player doesn't exist.");

        EconomyProvider.removeCoins(uuid, amount);

        return new EconomyResponse(amount, getBalance(playerName), EconomyResponse.ResponseType.SUCCESS,null);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        UUID uuid = player.getUniqueId();

        if (!EconomyProvider.isRegistered(uuid)) return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Player doesn't exist.");

        EconomyProvider.removeCoins(uuid, amount);

        return new EconomyResponse(amount, getBalance(player), EconomyResponse.ResponseType.SUCCESS,null);
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        UUID uuid = UUIDFetcher.getUUID(playerName);
        if (uuid == null) return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Player doesn't exist.");
        if (!EconomyProvider.isRegistered(uuid)) return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Player doesn't exist.");

        EconomyProvider.addCoins(uuid, amount);

        return new EconomyResponse(amount, getBalance(playerName), EconomyResponse.ResponseType.SUCCESS,null);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        UUID uuid = player.getUniqueId();

        if (!EconomyProvider.isRegistered(uuid)) return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Player doesn't exist.");

        EconomyProvider.addCoins(uuid, amount);

        return new EconomyResponse(amount, getBalance(player), EconomyResponse.ResponseType.SUCCESS,null);
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        UUID uuid = UUIDFetcher.getUUID(playerName);
        if (uuid == null) return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Player doesn't exist.");
        if (!EconomyProvider.isRegistered(uuid)) return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Player doesn't exist.");

        EconomyProvider.addCoins(uuid, amount);

        return new EconomyResponse(amount, getBalance(playerName), EconomyResponse.ResponseType.SUCCESS,null);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        UUID uuid = player.getUniqueId();

        if (!EconomyProvider.isRegistered(uuid)) return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Player doesn't exist.");

        EconomyProvider.addCoins(uuid, amount);

        return new EconomyResponse(amount, getBalance(player), EconomyResponse.ResponseType.SUCCESS,null);
    }

    @Override
    public EconomyResponse createBank(String name, String player) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String name, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String name, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        UUID uuid = UUIDFetcher.getUUID(playerName);
        if (uuid == null) return false;
        if (EconomyProvider.isRegistered(uuid)) return false;

        EconomyProvider.register(uuid);

        return true;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        UUID uuid = player.getUniqueId();
        if (EconomyProvider.isRegistered(uuid)) return false;

        EconomyProvider.register(uuid);

        return true;
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        UUID uuid = UUIDFetcher.getUUID(playerName);
        if (uuid == null) return false;
        if (EconomyProvider.isRegistered(uuid)) return false;

        EconomyProvider.register(uuid);

        return true;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        UUID uuid = player.getUniqueId();
        if (EconomyProvider.isRegistered(uuid)) return false;

        EconomyProvider.register(uuid);

        return true;
    }
}
