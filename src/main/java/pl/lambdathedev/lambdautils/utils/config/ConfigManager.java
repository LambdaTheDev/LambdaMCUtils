package pl.lambdathedev.lambdautils.utils.config;

import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.config.custom.NicknamesConfig;
import pl.lambdathedev.lambdautils.utils.config.custom.PlayersConfig;
import pl.lambdathedev.lambdautils.utils.config.custom.PunishmentsConfig;
import pl.lambdathedev.lambdautils.utils.config.custom.RanksConfig;

public class ConfigManager
{
    private final DefaultConfig defaultConfig;
    private final PunishmentsConfig punishmentsConfig;
    private final RanksConfig ranksConfig;
    private final PlayersConfig playersConfig;
    private final NicknamesConfig nicknamesConfig;

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
        nicknamesConfig = new NicknamesConfig(plugin);

        defaultConfig.setup();
        punishmentsConfig.setup();
        ranksConfig.setup();
        playersConfig.setup();
        nicknamesConfig.setup();
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

    public NicknamesConfig getNicknamesConfig()
    {
        return nicknamesConfig;
    }
}
