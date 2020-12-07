package pl.lambdathedev.lambdautils.utils.punishments;

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

    public boolean create()
    {
        return false;
    }

    public void delete()
    {

    }
}
