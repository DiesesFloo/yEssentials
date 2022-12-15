package systems.floo.yessentials.commands.fly;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FlyCommandJoinListener implements Listener {

    /**
     * Method executed on player join
     *
     * @param event Join event
     */
    @EventHandler
    public void handleJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (FlyCommandProvider.isFlyer(player)) {
            player.setAllowFlight(true);
            player.setFlying(true);
        }

    }

}
