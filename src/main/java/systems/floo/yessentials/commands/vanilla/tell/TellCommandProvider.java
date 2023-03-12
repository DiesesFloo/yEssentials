package systems.floo.yessentials.commands.vanilla.tell;

import org.apache.logging.log4j.message.Message;
import org.bukkit.entity.Player;
import systems.floo.yessentials.messages.MessageProvider;

import java.util.HashMap;

public class TellCommandProvider {

    private static HashMap<Player, Player> lastMessagedMap = new HashMap<>();

    public static void setLastMessaged(Player player, Player messaged){
        lastMessagedMap.put(player, messaged);
    }

    public static HashMap<Player, Player> getLastMessagedMap() {
        return lastMessagedMap;
    }

    public static Player getLastMessaged(Player player){
        return lastMessagedMap.get(player);
    }

    public static void removePlayerFromMap(Player player){
        lastMessagedMap.remove(player);

        for (Player values : lastMessagedMap.values()){
            if (lastMessagedMap.get(values).equals(player)){
                lastMessagedMap.remove(values);
            }
        }
    }

    public static boolean playerHastLastMessaged(Player player){
        return lastMessagedMap.containsKey(player);
    }

    public static void sendDirectMessage(Player sender, Player target, String message){
        sender.sendMessage(MessageProvider.getMessage("privatemessagesent", sender, target).replace("%message%", message));
        target.sendMessage(MessageProvider.getMessage("privatemessagereceived", sender, target).replace("%message%", message));

        setLastMessaged(sender, target);
        setLastMessaged(target, sender);
    }

}
