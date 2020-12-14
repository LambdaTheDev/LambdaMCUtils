package pl.lambdathedev.lambdautils.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.lambdathedev.lambdautils.utils.messaging.MessagingUtil;
import pl.lambdathedev.lambdautils.utils.punishments.PunishmentType;
import pl.lambdathedev.lambdautils.utils.punishments.PunishmentsManager;

import java.util.UUID;

public class CmdBan implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(command.getName().equalsIgnoreCase("ban"))
        {
            if(!sender.hasPermission("lambdautils.ban"))
            {
                sender.sendMessage(MessagingUtil.parseMessage("&4You do not have permission to use this command!"));
                return false;
            }

            if(args.length < 2)
            {
                sender.sendMessage(MessagingUtil.parseMessage("&cInvalid usage! Correct: /ban <nick> <reason...>."));
                return false;
            }

            String targetPlayerNickname = args[0];
            Player targetPlayer = Bukkit.getPlayer(targetPlayerNickname);
            if(targetPlayer == null)
            {
                sender.sendMessage(MessagingUtil.parseMessage("&cCan not punish player with this nickname. Probably offline."));
                return false;
            }

            if(targetPlayer.hasPermission("lambdautils.preventpunishments") && !sender.isOp())
            {
                sender.sendMessage(MessagingUtil.parseMessage("&cYou are not allowed to punish " + targetPlayer.getName() + "!"));
                return false;
            }

            StringBuilder reason = new StringBuilder();
            for(int i = 1; i < args.length; i++)
            {
                reason.append(args[i]).append(" ");
            }

            targetPlayer.kickPlayer("Disconnected. Rejoin for more information.");

            PunishmentsManager.punish(targetPlayer.getUniqueId(), sender.getName(), PunishmentType.BAN, null, reason.toString());
            MessagingUtil.sendMessageToEveryone(
                    MessagingUtil.parseMessage("&4" + targetPlayer.getName() + " got permanently banned by "
                                    + sender.getName() + "! Reason: " + reason.toString())
            );
        }
        return false;
    }
}
