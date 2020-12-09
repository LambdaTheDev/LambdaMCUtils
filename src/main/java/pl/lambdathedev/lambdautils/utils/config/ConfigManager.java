package pl.lambdathedev.lambdautils.utils.config;

import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.config.custom.PlayersConfig;
import pl.lambdathedev.lambdautils.utils.config.custom.PunishmentsConfig;
import pl.lambdathedev.lambdautils.utils.config.custom.RanksConfig;

public class ConfigManager
{
    private DefaultConfig defaultConfig;
    private PunishmentsConfig punishmentsConfig;
    private RanksConfig ranksConfig;
    private PlayersConfig playersConfig;

    public ConfigManager(LambdaUtils plugin)
    {
        if(!plugin.getDataFolder().exists())
        {
            plugin.getDataFolder().mkdir();
        }

        defaultConfig = new DefaultConfig(plugin);
        punishmentsConfig = new PunishmentsConfig(plugin);
        ranksConfig = new RanksConfig(plugin);
        playersConfig = new PlayersConfig(plugin);

        defaultConfig.setup();
        punishmentsConfig.setup();
        ranksConfig.setup();
        playersConfig.setup();
    }

    public DefaultConfig getDefaultConfig()
    {
        return defaultConfig;
    }

    public PunishmentsConfig getPunishmentsConfig()
    {
        return punishmentsConfig;
    }

    public RanksConfig getRanksConfig()
    {
        return ranksConfig;
    }

    public PlayersConfig getPlayersConfig()
    {
        return playersConfig;
    }
}
