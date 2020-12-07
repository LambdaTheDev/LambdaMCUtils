package pl.lambdathedev.lambdautils.utils.messaging;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MessagingUtil
{
    public static void sendMessageToConsole(String message)
    {
        Bukkit.getConsoleSender().sendMessage(message);
    }

    public static void sendMessageToSpecificPlayers(String message, String requiredPermission)
    {
        for(Player p : Bukkit.getServer().getOnlinePlayers())
        {
            if(p.hasPermission(requiredPermission))
            {
                p.sendMessage(message);
            }
        }
    }

    public static void sendMessageToEveryone(String message)
    {
        for(Player p : Bukkit.getServer().getOnlinePlayers())
        {
            p.sendMessage(message);
        }
    }
}
