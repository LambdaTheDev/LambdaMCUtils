package pl.lambdathedev.lambdautils.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.lambdathedev.lambdautils.utils.config.custom.NicknamesConfig;
import pl.lambdathedev.lambdautils.utils.messaging.MessagingUtil;
import pl.lambdathedev.lambdautils.utils.nicknames.NicknamesManager;
import pl.lambdathedev.lambdautils.utils.punishments.Punishment;
import pl.lambdathedev.lambdautils.utils.punishments.PunishmentsManager;

import java.util.UUID;

public class CmdUnBan implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(command.getName().equalsIgnoreCase("unban"))
        {
            if(!sender.hasPermission("lambdautils.unban"))
            {
                sender.sendMessage(MessagingUtil.parseMessage("&4You do not have permission to use this command!"));
                return false;
            }

            if(args.length != 1)
            {
                sender.sendMessage(MessagingUtil.parseMessage("&cInvalid usage! Correct: /unban <nick>!"));
                return false;
            }

            UUID playerUUID = NicknamesManager.getUUID(args[0]);
            if(playerUUID == null)
            {
                sender.sendMessage(MessagingUtil.parseMessage("&cCould not find " + args[0] + "'s UUID in UUID cache module. Check if nickname is correct or ask player to connect the server!"));
                return false;
            }

            Punishment ban = PunishmentsManager.getBan(playerUUID);
            if(ban == null)
            {
                sender.sendMessage(MessagingUtil.parseMessage("&aThis player is not banned!"));
                return false;
            }

            ban.delete();
            sender.sendMessage(MessagingUtil.parseMessage("&aPlayer " + args[0] + " has been unbanned successfully!"));
        }
        return false;
    }
}
