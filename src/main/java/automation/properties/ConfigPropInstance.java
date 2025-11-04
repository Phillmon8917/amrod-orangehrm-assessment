package automation.properties;

import org.aeonbits.owner.ConfigCache;

/**
 * Catches the interface from config catch, this is for performance
 * When need a config instead of new one, it can return one from Catch
 */
public class ConfigPropInstance
{
    public static ConfigProp getConfig()
    {
        return ConfigCache.getOrCreate(ConfigProp.class);
    }

    private ConfigPropInstance()
    {
    }
}
