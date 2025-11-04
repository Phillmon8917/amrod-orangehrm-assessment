package automation.flow;

import automation.strategy.DriverSingleton;
import automation.utils.WaitUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class OrangeHRMWeb extends OrangeHRMWebBasePage
{
    public void login(String username, String password)
    {
        try
        {
            WaitUtil.waitUntilVisible(usernameInput);
            usernameInput.clear();
            usernameInput.sendKeys(username.trim());

            WaitUtil.waitUntilVisible(passwordInput);
            passwordInput.clear();
            passwordInput.sendKeys(password.trim());

            loginButton.click();
        } catch (Exception ex)
        {
            log.error("An error has occurred", ex);
        }
    }

    public void logOut()
    {
        try
        {
            WaitUtil.waitUntilVisible(userDropDown);
            userDropDown.click();

            WaitUtil.waitUntilVisible(logoutOption);
            logoutOption.click();
        } catch (Exception ex)
        {
            log.error("An error has occurred", ex);
        }
    }


    @PostConstruct
    public void init()
    {
        WebDriver driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

}
