package pl.lambdathedev.lambdautils.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.lambdathedev.lambdautils.LambdaUtils;

public class OnPlayerJoin implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        String joinMessage = LambdaUtils.getInstance().getConfigManager().getDefaultConfig().getConfig().getString("joinMessage");
        if(joinMessage == null)
        {
            return;
        }

        joinMessage = joinMessage.replace("{player}", e.getPlayer().getName());
        joinMessage = ChatColor.translateAlternateColorCodes('&', joinMessage);
        e.setJoinMessage(joinMessage);
    }
}
