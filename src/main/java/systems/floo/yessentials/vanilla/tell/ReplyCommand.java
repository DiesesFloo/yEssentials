package systems.floo.yessentials.vanilla.tell;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import systems.floo.yessentials.messages.MessageProvider;

import java.util.Arrays;

public class ReplyCommand extends Command {
    public ReplyCommand() {
        super("reply",
                "Reply to a private message.",
                "",
                Arrays.asList(new String[]{"r"}));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;

        if (!TellCommandProvider.playerHastLastMessaged(player)){
            player.sendMessage(MessageProvider.getMessage("replyerror", player));
            return false;
        }

        if (args.length < 1){
            player.sendMessage(MessageProvider.getMessage("replymsgerror", player));
            return false;
        }

        StringBuilder msg = new StringBuilder();

        for (String arg : args) {
            msg.append(arg).append(" ");
        }

        TellCommandProvider.sendDirectMessage(player, TellCommandProvider.getLastMessaged(player), msg.toString());

        return true;
    }
}
