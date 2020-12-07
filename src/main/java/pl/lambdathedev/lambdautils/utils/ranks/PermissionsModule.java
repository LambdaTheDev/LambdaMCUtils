package pl.lambdathedev.lambdautils.utils.ranks;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.HashSet;
import java.util.Set;

public class PermissionsModule
{
    private Player player;
    private PermissionAttachment permissionAttachment;

    public PermissionsModule(Player player, PermissionAttachment permissionAttachment)
    {
        this.player = player;
        this.permissionAttachment = permissionAttachment;
    }

    public void addPermission(String permission)
    {
        if(player.hasPermission(permission)) return;
        permissionAttachment.setPermission(permission, true);
    }

    public void removePermission(String permission)
    {
        permissionAttachment.unsetPermission(permission);
    }

    public void clearPermissions()
    {
        Set<PermissionAttachmentInfo> playerPermissions = new HashSet<>(player.getEffectivePermissions());

        for (PermissionAttachmentInfo permissionInfo : playerPermissions)
        {
            String permission = permissionInfo.getPermission();
            removePermission(permission);
        }
    }
}
