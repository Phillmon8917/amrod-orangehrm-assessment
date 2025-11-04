package automation.assertions;

import automation.pages.LoginPage;
import automation.strategy.DriverSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class serves as a service to assist in asserting against landing page
 * scenarios such as being on logic page, etc
 */
@Service
public class LandingPageAssertion
{
    @Autowired
    private LoginPage loginPage;

    public boolean userIsOnTheLoginPage()
    {
        return loginPage.isUserOnTheLoginPage();
    }

    public boolean errorPageShouldLoad()
    {
        return !userIsOnTheLoginPage();
    }

    public boolean httpRedirectsToHttps()
    {
        String url = DriverSingleton.getDriver().getCurrentUrl();
        return url != null && url.toLowerCase().contains("https");
    }

    private LandingPageAssertion()
    {
    }
}
