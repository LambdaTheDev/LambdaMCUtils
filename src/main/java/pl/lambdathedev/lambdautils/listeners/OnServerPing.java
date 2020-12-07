package pl.lambdathedev.lambdautils.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import pl.lambdathedev.lambdautils.LambdaUtils;

public class OnServerPing implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void onServerPing(ServerListPingEvent e)
    {
        String motd = LambdaUtils.getInstance().getConfigManager().getDefaultConfig().getConfig().getString("motd");
        if(motd == null)
        {
            return;
        }

        motd = ChatColor.translateAlternateColorCodes('&', motd);

        e.setMotd(motd);
    }
}
