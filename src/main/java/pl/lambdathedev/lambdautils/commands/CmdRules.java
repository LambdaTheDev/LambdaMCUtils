package pl.lambdathedev.lambdautils.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.messaging.MessagingUtil;

import java.util.Collection;

public class CmdRules implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(command.getName().equalsIgnoreCase("rules"))
        {
            Collection<String> rules = LambdaUtils.getInstance().getConfigManager().getDefaultConfig().getConfig().getStringList("rules");

            StringBuilder message = new StringBuilder(ChatColor.GREEN + "Rules on this server:\n\n");

            int i = 0;
            for(String rule : rules)
            {
                ChatColor color = ChatColor.YELLOW;
                if(i % 2 == 0)
                {
                    color = ChatColor.RED;
                }

                message.append(color).append(rule).append("\n");
                i++;
            }

            sender.sendMessage(message.toString());
        }
        return false;
    }
}
