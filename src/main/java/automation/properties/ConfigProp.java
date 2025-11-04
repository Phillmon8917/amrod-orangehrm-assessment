package automation.properties;

import org.aeonbits.owner.Config;

/**
 * Owner Api allows us to read data from config in easy steps to setup
 */
@Config.Sources("classpath:configs/config.properties")
public interface ConfigProp extends Config
{
    @Key("toEmail")
    String toEmail();

    @Key("ccEmail")
    String ccEmail();

    @Key("sendEmail")
    String sendEmail();
}
