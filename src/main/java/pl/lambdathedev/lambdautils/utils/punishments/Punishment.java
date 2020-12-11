package pl.lambdathedev.lambdautils.utils.punishments;

import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.config.custom.PunishmentsConfig;

import java.util.Date;
import java.util.UUID;

public class Punishment
{
    private UUID uuid;
    private UUID issuer;
    private PunishmentType type;
    private Date expiryDate;
    private String reason;

    public Punishment(UUID uuid, UUID issuer, PunishmentType type, Date expiryDate, String reason)
    {
        this.uuid = uuid;
        this.issuer = issuer;
        this.type = type;
        this.expiryDate = expiryDate;
        this.reason = reason;
    }

    public void delete()
    {
        PunishmentsConfig cfg = LambdaUtils.getInstance().getConfigManager().getPunishmentsConfig();
        String hook = "mutes.";
        if(type == PunishmentType.BAN || type == PunishmentType.TEMP_BAN) hook = "bans.";

        cfg.getConfig().set(hook + uuid.toString(), null);
        cfg.save();
    }

    public UUID getUuid()
    {
        return uuid;
    }

    public UUID getIssuer()
    {
        return issuer;
    }

    public PunishmentType getType()
    {
        return type;
    }

    public Date getExpiryDate()
    {
        return expiryDate;
    }

    public String getReason()
    {
        return reason;
    }
}
