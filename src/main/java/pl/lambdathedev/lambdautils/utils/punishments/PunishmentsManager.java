package pl.lambdathedev.lambdautils.utils.punishments;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import pl.lambdathedev.lambdautils.LambdaUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class PunishmentsManager
{
    public static Punishment getBan(UUID uuid)
    {
        FileConfiguration cfg = LambdaUtils.getInstance().getConfigManager().getPunishmentsConfig().getConfig();
        if(!cfg.contains("bans." + uuid.toString()))
        {
            return null;
        }

        String issuerUUIDStr = cfg.getString("bans." + uuid.toString() + ".issuer");
        String reason = cfg.getString("bans." + uuid.toString() + ".reason");
        String expiryDateStr = cfg.getString("bans." + uuid.toString() + ".expiryDate");

        UUID issuerUUID = UUID.fromString(issuerUUIDStr);
        Date expiryDate = null;
        if(expiryDateStr != null)
        {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try
            {
                expiryDate = format.parse(expiryDateStr);
            }
            catch (ParseException e)
            {
                Bukkit.getConsoleSender().sendMessage("ERROR: INVALID EXPIRY DATE IN " + uuid.toString() + " BAN! IGNORING...");
            }
        }

        PunishmentType type = expiryDate == null ? PunishmentType.BAN : PunishmentType.TEMP_BAN;
        return new Punishment(uuid, issuerUUID, type, expiryDate, reason);
    }

    public static Punishment getMute(UUID uuid)
    {
        FileConfiguration cfg = LambdaUtils.getInstance().getConfigManager().getPunishmentsConfig().getConfig();
        if(!cfg.contains("bans." + uuid.toString()))
        {
            return null;
        }

        String issuerUUIDStr = cfg.getString("bans." + uuid.toString() + ".issuer");
        String reason = cfg.getString("bans." + uuid.toString() + ".reason");
        String expiryDateStr = cfg.getString("bans." + uuid.toString() + ".expiryDate");

        UUID issuerUUID = UUID.fromString(issuerUUIDStr);
        Date expiryDate = null;
        if(expiryDateStr != null)
        {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try
            {
                expiryDate = format.parse(expiryDateStr);
            }
            catch (ParseException e)
            {
                Bukkit.getConsoleSender().sendMessage("ERROR: INVALID EXPIRY DATE IN " + uuid.toString() + " BAN! IGNORING...");
            }
        }

        PunishmentType type = expiryDate == null ? PunishmentType.MUTE : PunishmentType.TEMP_MUTE;
        return new Punishment(uuid, issuerUUID, type, expiryDate, reason);
    }

    public static boolean punish(UUID uuid, UUID issuer, PunishmentType type, Date expiryDate, String reason)
    {
        FileConfiguration cfg = LambdaUtils.getInstance().getConfigManager().getPunishmentsConfig().getConfig();
        String typeStr = "bans";
        if(type == PunishmentType.MUTE || type == PunishmentType.TEMP_MUTE)
        {
            typeStr = "mutes";
        }

        if(cfg.contains(typeStr + "." + uuid.toString()))
        {
            return false;
        }

        String expiryDateStr = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(expiryDate != null)
        {
            expiryDateStr = format.format(expiryDate);
        }

        cfg.set(typeStr + "." + uuid.toString() + ".issuer", issuer.toString());
        cfg.set(typeStr + "." + uuid.toString() + ".reason", reason);
        cfg.set(typeStr + "." + uuid.toString() + ".expiryDate", expiryDateStr);
        LambdaUtils.getInstance().getConfigManager().getPunishmentsConfig().save();

        return true;
    }
}
