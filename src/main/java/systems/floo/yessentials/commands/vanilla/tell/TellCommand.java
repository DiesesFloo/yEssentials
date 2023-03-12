package systems.floo.yessentials.commands.vanilla.tell;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import systems.floo.yessentials.messages.MessageProvider;

import java.util.Arrays;

public class TellCommand extends Command {
    public TellCommand() {
        super("tell",
                "Sends a private message to a player",
                "",
                Arrays.asList(new String[]{"msg", "message", "whisper", "w"}));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if (!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;

        if (args.length < 1){
            player.sendMessage(MessageProvider.getMessage("wronguse", player));
            return false;
        }

        if (args.length < 2){
            player.sendMessage(MessageProvider.getMessage("wronguse", player));
            return false;
        }

        String targetName = args[0];
        Player target = Bukkit.getPlayer(targetName);

        if (target == null){
            player.sendMessage(MessageProvider.getMessage("playernotfound", player));
            return false;
        }

        StringBuilder msg = new StringBuilder();

        for (int i = 1; i < args.length; i++){
            msg.append(args[i]).append(" ");
        }

        TellCommandProvider.sendDirectMessage(player, target, msg.toString());

        return true;
    }
}
