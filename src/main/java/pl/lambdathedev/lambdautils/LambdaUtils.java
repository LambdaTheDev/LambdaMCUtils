package pl.lambdathedev.lambdautils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.lambdathedev.lambdautils.listeners.*;
import pl.lambdathedev.lambdautils.utils.config.ConfigManager;
import pl.lambdathedev.lambdautils.utils.playerdata.PlayerData;
import pl.lambdathedev.lambdautils.utils.ranks.Rank;
import pl.lambdathedev.lambdautils.utils.ranks.RanksManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public final class LambdaUtils extends JavaPlugin
{
    private static LambdaUtils instance;

    private HashMap<String, Rank> ranks;
    private HashMap<UUID, PlayerData> playerData;
    private ConfigManager configManager;

    @Override
    public void onEnable()
    {
        ranks = new HashMap<>();
        playerData = new HashMap<>();
        configManager = new ConfigManager(this);

        registerCommands();
        registerListeners();
        loadRanks();

        instance = this;
    }

    private void registerListeners()
    {
        getServer().getPluginManager().registerEvents(new OnPlayerChat(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerConnect(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerQuit(), this);
        getServer().getPluginManager().registerEvents(new OnServerPing(), this);
    }

    private void registerCommands()
    {
        //todo: When commands are ready
    }

    private void loadRanks()
    {
        Collection<Rank> ranks = RanksManager.getAllRanks();
        for(Rank rank : ranks)
        {
            this.ranks.put(rank.getName(), rank);
        }
    }

    @Override
    public void onDisable()
    {
    }

    public static LambdaUtils getInstance()
    {
        return instance;
    }

    public HashMap<UUID, PlayerData> getPlayerData()
    {
        return playerData;
    }

    public HashMap<String, Rank> getRanks()
    {
        return ranks;
    }

    public ConfigManager getConfigManager()
    {
        return configManager;
    }
}
