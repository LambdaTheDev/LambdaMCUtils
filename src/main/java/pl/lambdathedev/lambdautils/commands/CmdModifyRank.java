package pl.lambdathedev.lambdautils.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.messaging.MessagingUtil;
import pl.lambdathedev.lambdautils.utils.ranks.Rank;

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
                sender.sendMessage(MessagingUtil.parseMessage("&cInvalid usage! Correct: /modifyrank <rank name> <addPermission|removePermission|delete|create|info|setPrefix> [values...]!"));
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

                sender.sendMessage(MessagingUtil.parseMessage("&cInvalid usage! Correct: /modifyrank <rank name> <addPermission|removePermission|delete|create|info|setPrefix> [values...]!"));
                return false;
            }

            Rank rank = LambdaUtils.getInstance().getRanks().getOrDefault(args[0], null);

            if(args[1].equalsIgnoreCase("addPermission"))
            {
                String permission = args[2];
                rank.addPermission(permission);

                sender.sendMessage(MessagingUtil.parseMessage("&aAdded permission " + permission + " to the " + rank.getName() + " rank!"));
                return true;
            }
            else if(args[1].equalsIgnoreCase("removePermission"))
            {
                String permission = args[2];
                rank.removePermission(permission);

                sender.sendMessage(MessagingUtil.parseMessage("&cRemoved permission " + permission + " from the " + rank.getName() + " rank!"));
                return true;
            }
            else if(args[1].equalsIgnoreCase("delete"))
            {
                rank.delete();
                sender.sendMessage(MessagingUtil.parseMessage("&cDeleted " + rank.getName() + " rank successfully!"));
                LambdaUtils.getInstance().reloadRanks();
            }
            else if(args[1].equalsIgnoreCase("create"))
            {
                sender.sendMessage(MessagingUtil.parseMessage("&cThis feature is in development. Right now, create ranks manually in the config file."));
                return false;
            }
            else if(args[1].equalsIgnoreCase("info"))
            {
                StringBuilder permissions = new StringBuilder();
                for(String permission : rank.getPermissions())
                {
                    permissions.append("- ").append(permission).append("\n");
                }

                sender.sendMessage(MessagingUtil.parseMessage("&a" + rank.getName() + " info:\n\n" +
                        "&bPrefix: &r" + rank.getPrefix()) + "\n" +
                        "&bPermissions: " + permissions.toString());
            }
            else if(args[1].equalsIgnoreCase("setPrefix"))
            {
                String prefix = args[2];
                rank.setPrefix(prefix);
                sender.sendMessage(MessagingUtil.parseMessage("&aChanged " + rank.getName() + " prefix successfully!"));
            }
            else
            {
                sender.sendMessage(MessagingUtil.parseMessage("&cInvalid usage! Correct: /modifyrank <rank name> <addPermission|removePermission|delete|create|info|setPrefix> [values...]!"));
                return false;
            }
        }
        return false;
    }
}
