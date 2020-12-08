package pl.lambdathedev.lambdautils.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.messaging.MessagingUtil;
import pl.lambdathedev.lambdautils.utils.playerdata.PlayerData;
import pl.lambdathedev.lambdautils.utils.ranks.Rank;

import java.lang.reflect.Member;

public class CmdSetRank implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(command.getName().equalsIgnoreCase("setRank"))
        {
            if(!sender.hasPermission("lambdutils.setrank"))
            {
                sender.sendMessage(MessagingUtil.parseMessage("&4You do not have permission to use this command!"));
                return false;
            }

            if(args.length != 2)
            {
                sender.sendMessage(MessagingUtil.parseMessage("&cInvalid usage! Correct: /setrank <nick> <rank name|none>!"));
                return false;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if(target == null)
            {
                sender.sendMessage(MessagingUtil.parseMessage("&cThis player is not online!"));
                return false;
            }

            Rank rank = LambdaUtils.getInstance().getRanks().get(args[1]);
            PlayerData targetData = LambdaUtils.getInstance().getPlayerData().get(target.getUniqueId());
            if(rank != null)
            {
                targetData.setRank(rank);
                sender.sendMessage(MessagingUtil.parseMessage("&a" + target.getName() + "'s rank is now: " + rank.getName() + "!"));
                target.sendMessage(MessagingUtil.parseMessage("&aYour rank is now: " + rank.getName() + "!"));
                return true;
            }
            else if(args[1].equalsIgnoreCase("none"))
            {
                targetData.setRank(null);
                sender.sendMessage(MessagingUtil.parseMessage("&c" + target.getName() + "'s rank is now cleared."));
                target.sendMessage(MessagingUtil.parseMessage("&cYour rank is now cleared."));
                return true;
            }
            else
            {
                sender.sendMessage(MessagingUtil.parseMessage("&cRank with that name does not exist!"));
                return false;
            }
        }
        return false;
    }
}
