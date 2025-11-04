package automation.basepage;

import automation.basepagelifecycle.BasePageLifeCycle;
import automation.strategy.DriverSingleton;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This is a parent to child relationship, it handles some operations for the child
 * Also reduces the need for the child perform operations all operations on its own
 * Called -> IS-A Relationship
 */
public abstract class LoginBasePage implements BasePageLifeCycle
{
    protected WebDriver driver;

    @FindBy(xpath = "//button[text()=' Login ']")
    protected WebElement loginButton;

    @FindBy(name = "username")
    protected WebElement usernameInput;

    @FindBy(xpath = "//p[normalize-space()='Forgot your password?']")
    protected WebElement resetPasswordLink;

    @FindBy(xpath = "//h6[normalize-space()='Reset Password link sent successfully']")
    protected WebElement resetLinkSentConfirmation;

    @FindBy(xpath = "//button[text()=' Reset Password ']")
    protected WebElement resetButton;

    @FindBy(xpath = "//h6[text()='Dashboard']")
    protected WebElement dashboardPageTitle;

    @PostConstruct
    public void init()
    {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }
}
