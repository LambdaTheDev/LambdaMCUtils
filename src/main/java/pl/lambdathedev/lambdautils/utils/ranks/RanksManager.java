package pl.lambdathedev.lambdautils.utils.ranks;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import pl.lambdathedev.lambdautils.LambdaUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RanksManager
{
    public static Rank getRank(String name)
    {
        FileConfiguration cfg = LambdaUtils.getInstance().getConfigManager().getRanksConfig().getConfig();
        if(!cfg.contains("ranks." + name)) return null;

        String prefix = cfg.getString("ranks." + name + ".prefix");
        List<String> permissions = cfg.getStringList("ranks." + name + ".permissions");

        return new Rank(name, prefix, permissions);
    }

    public static Collection<Rank> getAllRanks()
    {
        FileConfiguration cfg = LambdaUtils.getInstance().getConfigManager().getRanksConfig().getConfig();
        Collection<Rank> result = new ArrayList<>();

        ConfigurationSection section = cfg.getConfigurationSection("ranks");
        if(section == null) return result;

        for(String key : section.getKeys(false))
        {
            result.add(getRank(key));
        }

        return result;
    }
}
