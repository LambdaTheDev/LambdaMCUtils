package pl.lambdathedev.lambdautils.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.messaging.MessagingUtil;
import pl.lambdathedev.lambdautils.utils.playerdata.PlayerData;

public class OnPlayerMove implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent e)
    {
        PlayerData data = LambdaUtils.getInstance().getPlayerData().get(e.getPlayer().getUniqueId());
        if(!data.isLoggedIn())
        {
            e.setCancelled(true);
            e.getPlayer().sendMessage(MessagingUtil.parseMessage("&cYou need to /login or /register to move!"));
        }
    }
}
