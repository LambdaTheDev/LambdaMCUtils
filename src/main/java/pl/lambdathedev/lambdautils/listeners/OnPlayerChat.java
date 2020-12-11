package pl.lambdathedev.lambdautils.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.messaging.MessagingUtil;
import pl.lambdathedev.lambdautils.utils.playerdata.PlayerData;
import pl.lambdathedev.lambdautils.utils.punishments.Punishment;
import pl.lambdathedev.lambdautils.utils.punishments.PunishmentsManager;
import pl.lambdathedev.lambdautils.utils.ranks.Rank;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class OnPlayerChat implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChat(AsyncPlayerChatEvent e)
    {
        e.setCancelled(true);

        Player p = e.getPlayer();
        Punishment mute = PunishmentsManager.getMute(p.getUniqueId());
        if(mute != null)
        {
            Date now = new Date();
            Player issuer = (Player) Bukkit.getOfflinePlayer(mute.getUuid());

            if(mute.getExpiryDate() == null)
            {
                p.sendMessage(MessagingUtil.parseMessage("&cYou are muted by: " + issuer.getName() + " for permanent! Reason: " + mute.getReason() + "."));
                return;
            }
            else if(now.after(mute.getExpiryDate()))
            {
                mute.delete();
            }
            else
            {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-MM-dd HH:mm:ss");
                p.sendMessage(MessagingUtil.parseMessage("&cYou are muted by: " + issuer.getName() + " until " + dateFormat.format(mute.getExpiryDate()) + "! Reason: " + mute.getReason() + "."));
                return;
            }
        }

        PlayerData data = LambdaUtils.getInstance().getPlayerData().get(p.getUniqueId());
        Rank rank = data.getRank();

        if(rank == null)
        {
            MessagingUtil.sendMessageToEveryone(
                    ChatColor.translateAlternateColorCodes('&', "&7" + p.getName() + " &r>> &7" + e.getMessage())
            );
        }
        else
        {
            MessagingUtil.sendMessageToEveryone(
                    ChatColor.translateAlternateColorCodes('&', rank.getPrefix() + " &f" + p.getName() + " &r>> &f" + e.getMessage())
            );
        }
    }
}
