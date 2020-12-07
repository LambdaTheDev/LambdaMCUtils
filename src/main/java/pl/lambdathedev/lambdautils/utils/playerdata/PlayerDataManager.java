package pl.lambdathedev.lambdautils.utils.playerdata;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.ranks.Rank;
import pl.lambdathedev.lambdautils.utils.ranks.RanksManager;

import java.util.UUID;

public class PlayerDataManager
{
    public static PlayerData getPlayerData(Player player)
    {
        UUID uuid = player.getUniqueId();

        FileConfiguration cfg = LambdaUtils.getInstance().getConfigManager().getPlayersConfig().getConfig();
        if(!cfg.contains("players." + uuid.toString())) return null;

        String password = cfg.getString("players." + uuid.toString() + ".password");
        String rankName = cfg.getString("players." + uuid.toString() + ".rank");
        Rank rank = RanksManager.getRank(rankName);

        return new PlayerData(player, password, rank);
    }

    public static PlayerData createPlayerData(Player player)
    {
        PlayerData data = new PlayerData(player, null, null);
        data.save();

        return data;
    }
}
