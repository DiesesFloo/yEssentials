package systems.floo.yessentials.vanilla.tell;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class TellCommandQuitListener implements Listener {

    @EventHandler
    public void handleQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        if (TellCommandProvider.playerHastLastMessaged(player)){
            TellCommandProvider.removePlayerFromMap(player);
        }
    }

}
