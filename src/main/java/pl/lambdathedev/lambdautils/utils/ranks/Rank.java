package pl.lambdathedev.lambdautils.utils.ranks;

import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.playerdata.PlayerData;

import java.util.Collection;
import java.util.UUID;

public class Rank
{
    private String name;
    private String prefix;
    private Collection<String> permissions;

    public Rank(String name, String prefix, Collection<String> permissions)
    {
        this.name = name;
        this.prefix = prefix;
        this.permissions = permissions;
    }

    public String getName()
    {
        return name;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public Collection<String> getPermissions()
    {
        return permissions;
    }

    public void addPermission(String permission)
    {
        if(permissions.contains(permission)) return;
        permissions.add(permission);
    }

    public void removePermission(String permission)
    {
        permissions.remove(permission);
    }

    public void applyPermissions(UUID uuid)
    {
        PlayerData data = LambdaUtils.getInstance().getPlayerData().get(uuid);
        PermissionsModule permsMod = new PermissionsModule(data.getPlayer(), data.getPermissions());
        permsMod.clearPermissions();
        for(String permission : permissions)
        {
            permsMod.addPermission(permission);
        }
    }
}
