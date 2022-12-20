package systems.floo.yessentials.commands.vanish;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import systems.floo.yessentials.EssentialsPlugin;

import java.util.UUID;

public class VanishCommandJoinListener implements Listener {

    private static final JavaPlugin PLUGIN = EssentialsPlugin.getPlugin();

    /**
     * Method executed on player join
     *
     * @param event Join event
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if(VanishCommandProvider.isVanished(player)){
            for (Player all : Bukkit.getOnlinePlayers()){
                if (all.hasPermission("essentials.vanish.bypass")){
                    continue;
                }

                all.hidePlayer(PLUGIN, player);
            }
        }

        if (player.hasPermission("essentials.vanish.bypass")){
            return;
        }

        for (UUID vanished : VanishCommandProvider.getVanishedPlayers()){
            Player vanishedPlayer = Bukkit.getPlayer(vanished);

            if (vanishedPlayer == null){
                continue;
            }

            player.hidePlayer(vanishedPlayer);
        }
    }
}
