package pl.lambdathedev.lambdautils.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.messaging.MessagingUtil;
import pl.lambdathedev.lambdautils.utils.playerdata.PlayerData;

public class CmdLogin implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(command.getName().equalsIgnoreCase("login"))
        {
            if(!(sender instanceof Player))
            {
                sender.sendMessage("Console cannot log in!");
                return false;
            }

            if(args.length != 1)
            {
                sender.sendMessage(MessagingUtil.parseMessage("&cInvalid usage! Correct: /login <password>!"));
                return false;
            }

            PlayerData data = LambdaUtils.getInstance().getPlayerData().get(((Player) sender).getUniqueId());

            if(data.isLoggedIn())
            {
                sender.sendMessage(MessagingUtil.parseMessage("&aYou are already logged in!"));
                return false;
            }

            if(!data.isRegistered())
            {
                sender.sendMessage(MessagingUtil.parseMessage("&cYou do not have an account! Get one using /register <password> <password>!"));
                return false;
            }

            String password = args[0];
            boolean validation = data.verifyPassword(password);
            if(!validation)
            {
                sender.sendMessage(MessagingUtil.parseMessage("&4Invalid password! Try again..."));
            }
            else
            {
                data.login();
                sender.sendMessage(MessagingUtil.parseMessage("&aYou logged in successfully!"));
            }
        }
        return false;
    }
}
