package pl.lambdathedev.lambdautils.utils.config;

import org.bukkit.configuration.file.FileConfiguration;
import pl.lambdathedev.lambdautils.LambdaUtils;

import java.io.File;

public class DefaultConfig implements IConfig
{
    private LambdaUtils plugin;

    public DefaultConfig(LambdaUtils plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public void setup()
    {
        plugin.saveDefaultConfig();
    }

    @Override
    public FileConfiguration getConfig()
    {
        return plugin.getConfig();
    }

    @Override
    public String getName()
    {
        return "config.yml";
    }

    @Override
    public void reload()
    {
        plugin.reloadConfig();
    }

    @Override
    public void save()
    {
        plugin.saveConfig();
    }
}
