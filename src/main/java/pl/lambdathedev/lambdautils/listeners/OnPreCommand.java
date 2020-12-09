package pl.lambdathedev.lambdautils.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.messaging.MessagingUtil;
import pl.lambdathedev.lambdautils.utils.playerdata.PlayerData;

public class OnPreCommand implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPreCommand(PlayerCommandPreprocessEvent e)
    {
        String command = e.getMessage();

        PlayerData data = LambdaUtils.getInstance().getPlayerData().get(e.getPlayer().getUniqueId());
        if(data.isLoggedIn()) return;

        if(!command.startsWith("/login") && !command.startsWith("/register"))
        {
            e.setCancelled(true);
            e.getPlayer().sendMessage(MessagingUtil.parseMessage("&cYou need to /login or /register to use commands!"));
        }
    }
}
