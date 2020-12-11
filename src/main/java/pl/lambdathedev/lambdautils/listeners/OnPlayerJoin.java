package pl.lambdathedev.lambdautils.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.messaging.MessagingUtil;
import pl.lambdathedev.lambdautils.utils.playerdata.PlayerData;
import pl.lambdathedev.lambdautils.utils.playerdata.PlayerDataManager;

public class OnPlayerJoin implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        PlayerData data = PlayerDataManager.getPlayerData(e.getPlayer());
        if(data == null)
        {
            data = PlayerDataManager.createPlayerData(e.getPlayer());
        }

        LambdaUtils.getInstance().getPlayerData().put(e.getPlayer().getUniqueId(), data);
        if(data.getRank() != null && LambdaUtils.getInstance().getRanks().getOrDefault(data.getRank().getName(), null) != null)
        {
            data.getRank().applyPermissions(e.getPlayer().getUniqueId());
        }

        String joinMessage = LambdaUtils.getInstance().getConfigManager().getDefaultConfig().getConfig().getString("joinMessage");
        if(joinMessage == null)
        {
            return;
        }

        joinMessage = joinMessage.replace("{player}", e.getPlayer().getName());
        e.setJoinMessage(MessagingUtil.parseMessage(joinMessage));

        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                PlayerData data = LambdaUtils.getInstance().getPlayerData().getOrDefault(e.getPlayer().getUniqueId(), null);
                if(data != null && !data.isLoggedIn())
                {
                    data.getPlayer().kickPlayer(MessagingUtil.parseMessage("&cYou did not log in in time!"));
                }
            }
        }.runTaskLater(LambdaUtils.getPlugin(LambdaUtils.class), 20 * 60 * 3); //3 minutes
    }
}
