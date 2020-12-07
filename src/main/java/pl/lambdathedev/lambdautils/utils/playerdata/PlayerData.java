package pl.lambdathedev.lambdautils.utils.playerdata;

import org.apache.commons.codec.digest.DigestUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.config.custom.PlayersConfig;
import pl.lambdathedev.lambdautils.utils.ranks.Rank;

import java.util.UUID;

public class PlayerData
{
    private UUID uuid;
    private Player player;
    private String password;
    private PermissionAttachment permissions;
    private Rank rank;
    private boolean loggedIn;

    public PlayerData(Player player, String password, Rank rank)
    {
        this.uuid = player.getUniqueId();
        this.player = player;
        this.password = password;
        this.permissions = player.addAttachment(LambdaUtils.getPlugin(LambdaUtils.class));
        this.rank = rank;
        loggedIn = false;
    }

    public UUID getUuid()
    {
        return uuid;
    }

    public Player getPlayer()
    {
        return player;
    }

    public PermissionAttachment getPermissions()
    {
        return permissions;
    }

    public Rank getRank()
    {
        return rank;
    }

    public void setRank(Rank rank)
    {
        this.rank = rank;
    }


    public boolean isLoggedIn()
    {
        return loggedIn;
    }

    public void login()
    {
        loggedIn = true;
    }

    public boolean verifyPassword(String input)
    {
        String salt = LambdaUtils.getInstance().getConfigManager().getDefaultConfig().getConfig().getString("password-salt");
        String hashedInput = DigestUtils.sha256Hex(input + salt);
        return hashedInput.equals(password);
    }

    public void setPassword(String plainPassword)
    {
        String salt = LambdaUtils.getInstance().getConfigManager().getDefaultConfig().getConfig().getString("password-salt");
        String hashedPassword = DigestUtils.sha256Hex(plainPassword + salt);
    }

    public void save()
    {
        FileConfiguration cfg = LambdaUtils.getInstance().getConfigManager().getPlayersConfig().getConfig();
        cfg.set("players." + uuid.toString() + ".rank", rank.getName());
        cfg.set("players." + uuid.toString() + ".password", password);
        LambdaUtils.getInstance().getConfigManager().getPlayersConfig().save();
    }
}
