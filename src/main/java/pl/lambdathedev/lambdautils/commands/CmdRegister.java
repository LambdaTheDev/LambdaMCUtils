package pl.lambdathedev.lambdautils.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.messaging.MessagingUtil;
import pl.lambdathedev.lambdautils.utils.playerdata.PlayerData;

public class CmdRegister implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(command.getName().equalsIgnoreCase("register"))
        {
            if(!(sender instanceof Player))
            {
                sender.sendMessage("Console cannot register!");
                return false;
            }

            if(args.length != 2)
            {
                sender.sendMessage(MessagingUtil.parseMessage("&cInvalid usage! Correct: /register <password> <repeat>!"));
                return false;
            }

            PlayerData data = LambdaUtils.getInstance().getPlayerData().get(((Player) sender).getUniqueId());

            if(data.isLoggedIn())
            {
                sender.sendMessage(MessagingUtil.parseMessage("&aYou are already logged in!"));
                return false;
            }

            if(data.isRegistered())
            {
                sender.sendMessage(MessagingUtil.parseMessage("&cYou already have an account! Login using /login <password>!"));
                return false;
            }

            String password = args[0];
            String repeat = args[1];

            if(!password.equals(repeat))
            {
                sender.sendMessage(MessagingUtil.parseMessage("&cPasswords do not match! Try again!"));
                return false;
            }

            data.setPassword(password);
            data.save();
            data.login();

            sender.sendMessage(MessagingUtil.parseMessage("&aYou have successfully registered and logged in!"));
        }
        return false;
    }
}
