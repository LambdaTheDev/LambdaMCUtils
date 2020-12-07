package pl.lambdathedev.lambdautils.utils.config;

import org.bukkit.configuration.file.FileConfiguration;

public interface IConfig
{
    void setup();
    String getName();
    FileConfiguration getConfig();
    void reload();
    void save();
}
