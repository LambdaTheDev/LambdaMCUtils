package pl.lambdathedev.lambdautils.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.lambdathedev.lambdautils.LambdaUtils;

public class OnPlayerQuit implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerQuit(PlayerQuitEvent e)
    {
        String leaveMessage = LambdaUtils.getInstance().getConfigManager().getDefaultConfig().getConfig().getString("leaveMessage");
        if(leaveMessage == null)
        {
            return;
        }

        leaveMessage = leaveMessage.replace("{player}", e.getPlayer().getName());
        leaveMessage = ChatColor.translateAlternateColorCodes('&', leaveMessage);
        e.setQuitMessage(leaveMessage);
    }
}
