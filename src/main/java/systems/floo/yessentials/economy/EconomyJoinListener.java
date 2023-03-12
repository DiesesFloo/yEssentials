package systems.floo.yessentials.economy;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class EconomyJoinListener implements Listener {

    /**
     * Method executed on player join
     *
     * @param event Join event
     */
    @EventHandler
    public void handleJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (!EconomyProvider.isRegistered(uuid)){
            EconomyProvider.register(uuid);
        }
    }

}
