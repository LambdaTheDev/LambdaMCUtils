package pl.lambdathedev.lambdautils.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.messaging.MessagingUtil;
import pl.lambdathedev.lambdautils.utils.playerdata.PlayerData;

public class OnPlayerAttack implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerAttack(EntityDamageByEntityEvent e)
    {
        if(e.getEntity() instanceof Player)
        {
            PlayerData data = LambdaUtils.getInstance().getPlayerData().get(((Player) e.getEntity()).getUniqueId());
            if(!data.isLoggedIn())
            {
                e.setCancelled(true);
                e.getDamager().sendMessage(MessagingUtil.parseMessage("&cYou cannot attack player who is not logged in!"));
            }
        }
    }
}
