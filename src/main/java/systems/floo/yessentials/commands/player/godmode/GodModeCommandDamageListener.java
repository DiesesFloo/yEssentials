package systems.floo.yessentials.commands.player.godmode;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class GodModeCommandDamageListener implements Listener {

    /**
     * Method executed on player damage
     *
     * @param event Damage event
     */
    @EventHandler
    public void handleDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();

        if (GodModeCommandProvider.isGodPlayer(player)) event.setCancelled(true);
    }

}
