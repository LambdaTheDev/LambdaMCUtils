package pl.lambdathedev.lambdautils.utils;

import org.bukkit.Bukkit;
import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.config.DefaultConfig;
import pl.lambdathedev.lambdautils.utils.messaging.MessagingUtil;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AutoMessageUtil
{
    private LambdaUtils plugin;

    public AutoMessageUtil(LambdaUtils plugin)
    {
        this.plugin = plugin;
        initialize();
    }

    private void initialize()
    {
        DefaultConfig cfg = LambdaUtils.getInstance().getConfigManager().getDefaultConfig();
        List<String> messages = cfg.getConfig().getStringList("automessageMessages");
        long delay = 20L * 60L * cfg.getConfig().getLong("automessageDelay");
        AtomicInteger currentMessage = new AtomicInteger();
        int allMessages = messages.size();

        if(allMessages == 0) return;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () ->
        {
            MessagingUtil.sendMessageToEveryone(MessagingUtil.parseMessage(messages.get(currentMessage.get())));
            currentMessage.getAndIncrement();
            if(currentMessage.getAndIncrement() >= allMessages)
            {
                currentMessage.set(0);
            }

        }, delay, delay);
    }
}
