package automation.flow;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

abstract class OrangeHRMWebBasePage
{
    @FindBy(name = "username")
    protected WebElement usernameInput;

    @FindBy(name = "password")
    protected WebElement passwordInput;

    @FindBy(xpath = "//button[text()=' Login ']")
    protected WebElement loginButton;

    @FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
    protected WebElement userDropDown;

    @FindBy(xpath = "//*[text()='Logout']")
    protected WebElement logoutOption;
}
