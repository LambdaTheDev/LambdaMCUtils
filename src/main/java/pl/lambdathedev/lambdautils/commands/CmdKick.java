package pl.lambdathedev.lambdautils.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.lambdathedev.lambdautils.utils.messaging.MessagingUtil;

public class CmdKick implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(command.getName().equalsIgnoreCase("kick"))
        {
            if(!sender.hasPermission("lambdautils.kick"))
            {
                sender.sendMessage(MessagingUtil.parseMessage("&4You do not have permission to use this command!"));
                return false;
            }

            if(args.length < 2)
            {
                sender.sendMessage(MessagingUtil.parseMessage("&cInvalid usage! Correct: /kick <nick> <reason>."));
                return false;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if(target == null)
            {
                sender.sendMessage(MessagingUtil.parseMessage("&cThis player is not online!"));
                return false;
            }

            StringBuilder reason = new StringBuilder();
            for(int i = 1; i < args.length; i++)
            {
                reason.append(args[i]).append(" ");
            }

            target.kickPlayer(MessagingUtil.parseMessage("&cYou have been kicked from the server!\n\n" +
                    "By: " + sender.getName() + "\n" +
                    "Reason: " + reason.toString()));

            MessagingUtil.sendMessageToEveryone(
                    MessagingUtil.parseMessage("&cPlayer " + target.getName() + " has been kicked from the server by " + sender.getName() + " for: " + reason.toString() + "!")
            );
        }
        return false;
    }
}
