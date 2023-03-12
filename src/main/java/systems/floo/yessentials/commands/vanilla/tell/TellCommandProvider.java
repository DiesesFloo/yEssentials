package systems.floo.yessentials.commands.vanilla.tell;

import org.bukkit.entity.Player;
import systems.floo.yessentials.messages.MessageProvider;

import java.util.HashMap;

public class TellCommandProvider {

    private static HashMap<Player, Player> lastMessagedMap = new HashMap<>();

    /**
     * Sets the last messaged player of a player
     *
     * @param player   The player
     * @param messaged The last messaged player
     */
    public static void setLastMessaged(Player player, Player messaged) {
        lastMessagedMap.put(player, messaged);
    }

    /**
     * Returns the map with the players and their last messaged player
     *
     * @return The map with the last messaged players
     */
    public static HashMap<Player, Player> getLastMessagedMap() {
        return lastMessagedMap;
    }

    /**
     * Returns the last messaged player of a player
     *
     * @param player The player
     * @return The last messaged player
     */
    public static Player getLastMessaged(Player player) {
        return lastMessagedMap.get(player);
    }

    /**
     * Removes a player completely from the map
     *
     * @param player The player
     */
    public static void removePlayerFromMap(Player player) {
        lastMessagedMap.remove(player);

        for (Player values : lastMessagedMap.values()) {
            if (lastMessagedMap.get(values).equals(player)) {
                lastMessagedMap.remove(values);
            }
        }
    }

    /**
     * Checks if the player is on the map
     *
     * @param player The player
     * @return If the map contains the player
     */
    public static boolean playerHastLastMessaged(Player player) {
        return lastMessagedMap.containsKey(player);
    }

    /**
     * Sends a private message to a player
     *
     * @param sender  The sender of the private message
     * @param target  The target of the private message
     * @param message The message content of the private message
     */
    public static void sendDirectMessage(Player sender, Player target, String message) {
        sender.sendMessage(MessageProvider.getMessage("privatemessagesent", sender, target).replace("%message%", message));
        target.sendMessage(MessageProvider.getMessage("privatemessagereceived", sender, target).replace("%message%", message));

        setLastMessaged(sender, target);
        setLastMessaged(target, sender);
    }

}
