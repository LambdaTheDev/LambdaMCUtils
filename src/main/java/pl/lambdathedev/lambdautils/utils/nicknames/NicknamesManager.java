package pl.lambdathedev.lambdautils.utils.nicknames;

import pl.lambdathedev.lambdautils.LambdaUtils;
import pl.lambdathedev.lambdautils.utils.config.custom.NicknamesConfig;

import java.util.UUID;

public class NicknamesManager
{
    public static UUID getUUID(String nickname)
    {
        NicknamesConfig cfg = LambdaUtils.getInstance().getConfigManager().getNicknamesConfig();
        String fetchedUUID = cfg.getConfig().getString("nicknames." + nickname.toLowerCase());
        if(fetchedUUID == null) return null;
        UUID result = null;
        try
        {
            result = UUID.fromString(fetchedUUID);
        }
        catch (IllegalArgumentException ignored) { }

        return result;
    }

    public static void updateNickname(UUID uuid, String nickname)
    {
        NicknamesConfig cfg = LambdaUtils.getInstance().getConfigManager().getNicknamesConfig();
        String fetchedUUID = cfg.getConfig().getString("nicknames." + nickname.toLowerCase());
        if(fetchedUUID == null || !fetchedUUID.equalsIgnoreCase(uuid.toString()))
        {
            cfg.getConfig().set("nicknames." + nickname.toLowerCase(), uuid.toString());
            cfg.save();
        }
    }
}
