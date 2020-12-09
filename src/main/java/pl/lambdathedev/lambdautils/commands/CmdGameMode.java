package pl.lambdathedev.lambdautils.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import pl.lambdathedev.lambdautils.utils.messaging.MessagingUtil;

public class CmdGameMode implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(command.getName().equalsIgnoreCase("gamemode") || command.getName().equalsIgnoreCase("gm"))
        {
            if(!sender.hasPermission("lambdautils.gamemode"))
            {
                sender.sendMessage(MessagingUtil.parseMessage("&4You do not have permission to use this command!"));
                return false;
            }

            if(args.length == 0)
            {
                sender.sendMessage(MessagingUtil.parseMessage("&cInvalid usage! Correct: /gamemode <survival|creative|adventure|spectator|0|1|2|3|s|c|a|sp> [player]!"));
                return false;
            }

            if(!(sender instanceof Player) && args.length != 2 && args[1] == null)
            {
                sender.sendMessage("You can not set game mode for a console!");
                return false;
            }

            Player target = null;

            if(args.length == 2 && args[1] != null)
            {
                target = Bukkit.getPlayer(args[1]);
                if(target == null)
                {
                    sender.sendMessage(MessagingUtil.parseMessage("&cThis player is not online!"));
                    return false;
                }
            }
            else
            {
                target = (Player) sender;
            }

            switch (args[0])
            {
                case "survival":
                case "0":
                case "s":
                    target.setGameMode(GameMode.SURVIVAL);
                    break;

                case "creative":
                case "1":
                case "c":
                    target.setGameMode(GameMode.CREATIVE);
                    break;

                case "adventure":
                case "2":
                case "a":
                    target.setGameMode(GameMode.ADVENTURE);
                    break;

                case "spectator":
                case "3":
                case "sp":
                    target.setGameMode(GameMode.SPECTATOR);
                    break;

                default:
                    sender.sendMessage(MessagingUtil.parseMessage("&cInvalid usage! Correct: /gamemode <survival|creative|adventure|spectator|0|1|2|3|s|c|a|sp> [player]!"));
                    return false;
            }

            if(target.getName().equals(sender.getName()))
            {
                sender.sendMessage(MessagingUtil.parseMessage("&aYour game mode is now: " + target.getGameMode().name()));
            }
            else
            {
                sender.sendMessage(MessagingUtil.parseMessage("&aYou set game mode " + target.getGameMode().name() + " for: " + target.getName() + "!"));
                target.sendMessage(MessagingUtil.parseMessage("&a" + sender.getName() + " set your game mode to: " + target.getGameMode().name() + "!"));
            }
        }
        return false;
    }
}
