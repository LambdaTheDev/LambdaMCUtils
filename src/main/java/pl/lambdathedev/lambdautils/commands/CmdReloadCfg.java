package pl.lambdathedev.lambdautils.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.messaging.MessagingUtil;

public class CmdReloadCfg implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(command.getName().equalsIgnoreCase("reloadCfg"))
        {
            if(!sender.hasPermission("lambdautils.reloadcfg"))
            {
                sender.sendMessage(MessagingUtil.parseMessage("&4You do not have permission to use this command!"));
                return false;
            }

            LambdaUtils.getInstance().reloadConfiguration();
            sender.sendMessage(MessagingUtil.parseMessage("&aConfiguration reloaded successfully!"));
        }
        return false;
    }
}
