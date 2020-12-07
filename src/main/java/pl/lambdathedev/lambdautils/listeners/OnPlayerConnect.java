package pl.lambdathedev.lambdautils.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import pl.lambdathedev.lambdautils.utils.messaging.MessagingUtil;
import pl.lambdathedev.lambdautils.utils.punishments.Punishment;
import pl.lambdathedev.lambdautils.utils.punishments.PunishmentsManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class OnPlayerConnect implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerConnect(AsyncPlayerPreLoginEvent e)
    {
        UUID uuid = e.getUniqueId();
        Punishment ban = PunishmentsManager.getBan(uuid);
        if(ban != null)
        {
            Player issuer = (Player) Bukkit.getOfflinePlayer(ban.getIssuer());
            String reason = ban.getReason();
            Date now = new Date();

            if(ban.getExpiryDate() == null)
            {
                String banMessage = "&cYou are permanently banned from this server!&r\n\n" +
                        "&cBanned by: &e" + issuer.getName() + "\n" +
                        "&cReason: &e" + reason;

                reason = MessagingUtil.parseMessage(reason);

                e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, reason);
            }
            else if(now.after(ban.getExpiryDate()))
            {
                ban.delete();
            }
            else
            {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String banMessage = "&cYou are temporarily banned from this server!&r\n\n" +
                        "&cBanned by: &e" + issuer.getName() + "\n" +
                        "&cExpires" + format.format(ban.getExpiryDate()) +
                        "&cReason: &e" + reason;

                reason = MessagingUtil.parseMessage(reason);

                e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, reason);
            }
        }
    }
}
