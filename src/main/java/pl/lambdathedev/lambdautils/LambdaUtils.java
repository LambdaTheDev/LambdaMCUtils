package pl.lambdathedev.lambdautils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.lambdathedev.lambdautils.commands.*;
import pl.lambdathedev.lambdautils.listeners.*;
import pl.lambdathedev.lambdautils.utils.AutoMessageUtil;
import pl.lambdathedev.lambdautils.utils.config.ConfigManager;
import pl.lambdathedev.lambdautils.utils.playerdata.PlayerData;
import pl.lambdathedev.lambdautils.utils.playerdata.PlayerDataManager;
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
        instance = this;

        configManager = new ConfigManager(this);
        ranks = new HashMap<>();
        playerData = new HashMap<>();

        registerCommands();
        registerListeners();
        loadRanks();

        new AutoMessageUtil(this);
    }

    private void registerListeners()
    {
        getServer().getPluginManager().registerEvents(new OnPlayerAttack(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerChat(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerConnect(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerMove(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerQuit(), this);
        getServer().getPluginManager().registerEvents(new OnPreCommand(), this);
        getServer().getPluginManager().registerEvents(new OnServerPing(), this);
    }

    private void registerCommands()
    {
        getCommand("ban").setExecutor(new CmdBan());
        getCommand("gamemode").setExecutor(new CmdGameMode());
        getCommand("kick").setExecutor(new CmdKick());
        getCommand("login").setExecutor(new CmdLogin());
        getCommand("modifyrank").setExecutor(new CmdModifyRank());
        getCommand("mute").setExecutor(new CmdMute());
        getCommand("setrank").setExecutor(new CmdSetRank());
        getCommand("register").setExecutor(new CmdRegister());
        getCommand("rules").setExecutor(new CmdRules());
        getCommand("unban").setExecutor(new CmdUnBan());
        getCommand("unmute").setExecutor(new CmdUnMute());
    }

    private void loadRanks()
    {
        Collection<Rank> ranks = RanksManager.getAllRanks();
        for(Rank rank : ranks)
        {
            this.ranks.put(rank.getName(), rank);
            rank.save();
        }
    }

    @Override
    public void onDisable()
    {
    }

    /*
    @Override
    public void onLoad()
    {
        playerData.clear();
        for(Player p : Bukkit.getServer().getOnlinePlayers())
        {
            PlayerData data = PlayerDataManager.getPlayerData(p);
            if(data == null)
            {
                data = PlayerDataManager.createPlayerData(p);
            }
            playerData.put(p.getUniqueId(), data);
        }
    }

     */

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

    public void reloadRanks()
    {
        loadRanks();
    }
}
