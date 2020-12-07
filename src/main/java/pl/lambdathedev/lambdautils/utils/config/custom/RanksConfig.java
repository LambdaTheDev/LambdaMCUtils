package pl.lambdathedev.lambdautils.utils.config.custom;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.config.IConfig;
import pl.lambdathedev.lambdautils.utils.messaging.MessagingUtil;

import java.io.File;
import java.io.IOException;

public class RanksConfig implements IConfig
{
    private LambdaUtils plugin;
    private FileConfiguration config;
    private File configFile;

    public RanksConfig(LambdaUtils plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public void setup()
    {
        configFile = new File(plugin.getDataFolder(), getName());
        if(!configFile.exists())
        {
            try
            {
                configFile.createNewFile();
                config = YamlConfiguration.loadConfiguration(configFile);
            }
            catch (IOException e)
            {
                MessagingUtil.sendMessageToConsole(ChatColor.RED + "Could not create " + getName() + " file!");
            }
        }
    }

    @Override
    public String getName()
    {
        return "ranks.yml";
    }

    @Override
    public FileConfiguration getConfig() {
        return null;
    }

    @Override
    public void reload()
    {
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    @Override
    public void save()
    {
        try
        {
            config.save(configFile);
        }
        catch (IOException e)
        {
            MessagingUtil.sendMessageToConsole(ChatColor.RED + "Could not save " + getName() + " file!");
        }
    }
}
