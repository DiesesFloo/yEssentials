package systems.floo.yessentials.commands.player.godmode;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class GodModeCommandFoodListener implements Listener {

    /**
     * Method executed on player damage
     *
     * @param event Food level change event
     */
    @EventHandler
    public void handleFoodLevelChange(FoodLevelChangeEvent event){
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();

        if (GodModeCommandProvider.isGodPlayer(player)) event.setCancelled(true);
    }

}
