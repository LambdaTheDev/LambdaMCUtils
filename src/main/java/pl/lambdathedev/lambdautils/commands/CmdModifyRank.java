package pl.lambdathedev.lambdautils.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.messaging.MessagingUtil;
import pl.lambdathedev.lambdautils.utils.ranks.Rank;
import pl.lambdathedev.lambdautils.utils.ranks.RanksManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CmdModifyRank implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(command.getName().equalsIgnoreCase("modifyrank"))
        {
            if(!sender.hasPermission("lambdautils.modifyrank"))
            {
                sender.sendMessage(MessagingUtil.parseMessage("&4You do not have permission to use this command!"));
                return false;
            }

            if(args.length == 0)
            {
                sender.sendMessage(getUsageMessage());
                return false;
            }

            else if(args.length == 1)
            {
                if(args[0].equalsIgnoreCase("reloadRanks"))
                {
                    LambdaUtils.getInstance().reloadRanks();
                    sender.sendMessage(MessagingUtil.parseMessage("&aAll ranks have been reloaded successfully!"));
                    return true;
                }

                sender.sendMessage(getUsageMessage());
                return false;
            }

            if(args[0].equalsIgnoreCase("create"))
            {
                if(args.length == 2)
                {
                    String name = args[1];
                    Rank rank = RanksManager.createRank(name);
                    if(rank == null)
                    {
                        sender.sendMessage(MessagingUtil.parseMessage("&cRank with this name already exists!"));
                        return false;
                    }

                    sender.sendMessage(MessagingUtil.parseMessage("&aRank " + rank.getName() + " has been created successfully!"));
                    return true;
                }
                else
                {
                    sender.sendMessage(getUsageMessage());
                    return false;
                }
            }

            Rank rank = LambdaUtils.getInstance().getRanks().getOrDefault(args[0], null);

            if(rank == null)
            {
                sender.sendMessage(MessagingUtil.parseMessage("&cRank with this name does not exist! Maybe you should reload ranks &b(/modifyrank reloadRanks)&c!"));
                return false;
            }

            if(args[1].equalsIgnoreCase("addPermission"))
            {
                if(!sender.hasPermission("lambdautils.modifyrank.managepermissions"))
                {
                    sender.sendMessage(MessagingUtil.parseMessage("&4You do not have permission to manage rank permissions!"));
                    return false;
                }

                if(args.length == 3)
                {
                    String permission = args[2];
                    rank.addPermission(permission);

                    sender.sendMessage(MessagingUtil.parseMessage("&aAdded permission " + permission + " to the " + rank.getName() + " rank!"));
                    return true;
                }
                else
                {
                    sender.sendMessage(MessagingUtil.parseMessage("&cInvalid usage! Correct: /modifyrank <rank name> addPermission <permission>!"));
                    return false;
                }
            }

            else if(args[1].equalsIgnoreCase("removePermission"))
            {
                if(!sender.hasPermission("lambdautils.modifyrank.managepermissions"))
                {
                    sender.sendMessage(MessagingUtil.parseMessage("&4You do not have permission to manage rank permissions!"));
                    return false;
                }

                if(args.length == 3)
                {
                    String permission = args[2];
                    rank.removePermission(permission);

                    sender.sendMessage(MessagingUtil.parseMessage("&cRemoved permission " + permission + " from the " + rank.getName() + " rank!"));
                    return true;
                }
                else
                {
                    sender.sendMessage(MessagingUtil.parseMessage("&cInvalid usage! Correct: /modifyrank <rank name> removePermission <permission>!"));
                    return false;
                }
            }

            else if(args[1].equalsIgnoreCase("delete"))
            {
                if(!sender.hasPermission("lambdautils.modifyrank.delete"))
                {
                    sender.sendMessage(MessagingUtil.parseMessage("&4You do not have permission to delete ranks!"));
                    return false;
                }

                rank.delete();
                sender.sendMessage(MessagingUtil.parseMessage("&cDeleted " + rank.getName() + " rank successfully!"));
                LambdaUtils.getInstance().reloadRanks();
                return true;
            }

            else if(args[1].equalsIgnoreCase("info"))
            {
                StringBuilder permsString = new StringBuilder();
                List<String> permissions = new ArrayList<>(rank.getPermissions());
                for(int i = 0; i < permissions.size(); i++)
                {
                    permsString.append("- ").append(permissions.get(i));
                    if(i++ < permissions.size())
                    {
                        permsString.append("\n");
                    }
                }

                sender.sendMessage(MessagingUtil.parseMessage("&aInfo about &6" + rank.getName() + " rank:\n" +
                        "&3Prefix: &r" + rank.getPrefix() + "\n" +
                        "&3Permissions: \n" + permsString.toString()));
                return true;
            }

            else if(args[1].equalsIgnoreCase("setPrefix"))
            {
                if(args.length == 3)
                {
                    String newPrefix = args[2];
                    rank.setPrefix(newPrefix);
                    sender.sendMessage(MessagingUtil.parseMessage("&aRank " + rank.getName() + "'s prefix is now: &r" + newPrefix + "&r!"));
                    return true;
                }
                else
                {
                    sender.sendMessage(getUsageMessage());
                    return false;
                }
            }
        }
        sender.sendMessage(getUsageMessage());
        return false;
    }

    private String getUsageMessage()
    {
        return (MessagingUtil.parseMessage("&cInvalid usage! Correct: /modifyrank <rank name>|create|reloadRanks <addPermission|removePermission|delete|create|info|setPrefix> [values...]!"));
    }
}
