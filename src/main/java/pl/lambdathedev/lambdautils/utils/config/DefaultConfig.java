package pl.lambdathedev.lambdautils.utils.config;

import org.bukkit.configuration.file.FileConfiguration;
import pl.lambdathedev.lambdautils.LambdaUtils;

import java.io.File;

public class DefaultConfig implements IConfig
{
    private File configFile;
    private FileConfiguration config;

    public DefaultConfig(LambdaUtils plugin)
    {
    }

    @Override
    public void setup()
    {

    }

    @Override
    public FileConfiguration getConfig()
    {
        return null;
    }

    @Override
    public String getName()
    {
        return "config.yml";
    }

    @Override
    public void reload()
    {

    }

    @Override
    public void save()
    {

    }
}
