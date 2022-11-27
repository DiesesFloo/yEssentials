package systems.floo.yessentials.commands.godmode;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class GodModeCommandDamageListener implements Listener {

    @EventHandler
    public void handleDamage(EntityDamageEvent e){
        if (!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();

        if (GodModeCommandProvider.isGodPlayer(player)) e.setCancelled(true);
    }

}
